#include "Ast2Asg.hpp"
#include <unordered_map>

#define self (*this)

namespace asg {

struct Ast2Asg::Symtbl : public std::unordered_map<std::string, Decl*>
{
  Ast2Asg& m;
  Symtbl* mPrev;

  Symtbl(Ast2Asg& m)
    : m(m)
    , mPrev(m.mSymtbl)
  {
    m.mSymtbl = this;
  }

  ~Symtbl() { m.mSymtbl = mPrev; }

  Decl* resolve(const std::string& name);
};

Decl*
Ast2Asg::Symtbl::resolve(const std::string& name)
{
  auto iter = find(name);
  if (iter != end())
    return iter->second;
  ASSERT(mPrev != nullptr); // 标识符未定义
  return mPrev->resolve(name);
}

struct Ast2Asg::CurrentLoop
{
  Ast2Asg& m;
  Stmt* mPrev;

  CurrentLoop(Ast2Asg& m, Stmt* loop)
    : m(m)
    , mPrev(m.mCurrentLoop)
  {
    m.mCurrentLoop = loop;
  }

  ~CurrentLoop() { m.mCurrentLoop = mPrev; }
};

TranslationUnit
Ast2Asg::operator()(ast::TranslationUnitContext* ctx)
{
  TranslationUnit ret;
  if (ctx == nullptr)
    return ret;

  Symtbl localDecls(self);

  for (auto&& i : ctx->externalDeclaration()) {
    if (auto p = i->declaration()) {
      auto decls = self(p);
      ret.insert(ret.end(),
                 std::make_move_iterator(decls.begin()),
                 std::make_move_iterator(decls.end()));
    }

    else if (auto p = i->functionDefinition()) {
      auto funcDecl = self(p);
      ret.push_back(funcDecl);

      // 添加到声明表
      localDecls[funcDecl->name] = funcDecl;
    }

    else if (auto p = i->Semi())
      ret.push_back(&make<Decl>());

    else
      ABORT();
  }

  return ret;
}

//==============================================================================
// 类型
//==============================================================================

Ast2Asg::SpecQual
Ast2Asg::operator()(ast::DeclarationSpecifiersContext* ctx)
{
  SpecQual ret = { Type::Spec::kINVALID, Type::Qual::kNone };

  for (auto&& i : ctx->declarationSpecifier()) {
    if (auto p = i->typeSpecifier()) {
      if (ret.first == Type::Spec::kINVALID) {
        if (p->Void())
          ret.first = Type::Spec::kVoid;
        else if (p->Char())
          ret.first = Type::Spec::kChar;
        else if (p->Int())
          ret.first = Type::Spec::kInt;
        else if (p->Long())
          ret.first = Type::Spec::kLong;
        else
          ABORT(); // 未知的类型说明符
      }

      else if (ret.first == Type::Spec::kLong) {
        if (p->Long())
          ret.first = Type::Spec::kLongLong;
        else
          ABORT(); // 类型说明符无效
      }

      else
        ABORT(); // 未知的类型说明符
    }

    else if (auto p = i->typeQualifier()) {
      // 限定符可以重复出现
      if (p->Const() &&
          (ret.second == Type::Qual::kNone || ret.second == Type::Qual::kConst))
        ret.second = Type::Qual::kConst;
      else
        ABORT(); // 类型限定符无效
    }

    else
      ABORT();
  }

  return ret;
}

Ast2Asg::SpecQual
Ast2Asg::operator()(ast::DeclarationSpecifiers2Context* ctx)
{
  SpecQual ret = { Type::Spec::kINVALID, Type::Qual::kNone };

  for (auto&& i : ctx->declarationSpecifier()) {
    if (auto p = i->typeSpecifier()) {
      if (ret.first == Type::Spec::kINVALID) {
        if (p->Void())
          ret.first = Type::Spec::kVoid;
        else if (p->Char())
          ret.first = Type::Spec::kChar;
        else if (p->Int())
          ret.first = Type::Spec::kInt;
        else if (p->Long())
          ret.first = Type::Spec::kLong;
        else
          ABORT(); // 未知的类型说明符
      }

      else if (ret.first == Type::Spec::kLong) {
        if (p->Long())
          ret.first = Type::Spec::kLongLong;
        else
          ABORT(); // 类型说明符无效
      }

      else
        ABORT(); // 未知的类型说明符
    }

    else if (auto p = i->typeQualifier()) {
      // 限定符可以重复出现
      if (p->Const() &&
          (ret.second == Type::Qual::kNone || ret.second == Type::Qual::kConst))
        ret.second = Type::Qual::kConst;
      else
        ABORT(); // 类型限定符无效
    }

    else
      ABORT();
  }

  return ret;
}

std::pair<TypeExpr*, std::string>
Ast2Asg::operator()(ast::DeclaratorContext* ctx, TypeExpr* sub)
{
  return self(ctx->directDeclarator(), sub);
}

static int
eval_arrlen(Expr* expr)
{
  if (auto p = expr->dcst<IntegerLiteral>())
    return p->val;

  if (auto p = expr->dcst<DeclRefExpr>()) {
    if (p->decl == nullptr)
      ABORT();

    auto var = p->decl->dcst<VarDecl>();
    if (!var || var->type.qual != Type::Qual::kConst)
      ABORT(); // 数组长度必须是编译期常量

    switch (var->type.spec) {
      case Type::Spec::kChar:
      case Type::Spec::kInt:
      case Type::Spec::kLong:
      case Type::Spec::kLongLong:
        return eval_arrlen(var->init);

      default:
        ABORT(); // 长度表达式必须是数值类型
    }
  }

  if (auto p = expr->dcst<UnaryExpr>()) {
    auto sub = eval_arrlen(p->sub);

    switch (p->op) {
      case UnaryExpr::kPos:
        return sub;

      case UnaryExpr::kNeg:
        return -sub;

      case UnaryExpr::kNot:
        return !sub;

      default:
        ABORT();
    }
  }

  if (auto p = expr->dcst<BinaryExpr>()) {
    auto lft = eval_arrlen(p->lft);
    auto rht = eval_arrlen(p->rht);

    switch (p->op) {
      case BinaryExpr::kMul:
        return lft * rht;

      case BinaryExpr::kDiv:
        return lft / rht;

      case BinaryExpr::kMod:
        return lft % rht;

      case BinaryExpr::kAdd:
        return lft + rht;

      case BinaryExpr::kSub:
        return lft - rht;

      case BinaryExpr::kGt:
        return lft > rht;

      case BinaryExpr::kLt:
        return lft < rht;

      case BinaryExpr::kGe:
        return lft >= rht;

      case BinaryExpr::kLe:
        return lft <= rht;

      case BinaryExpr::kEq:
        return lft == rht;

      case BinaryExpr::kNe:
        return lft != rht;

      case BinaryExpr::kAnd:
        return lft && rht;

      case BinaryExpr::kOr:
        return lft || rht;

      default:
        ABORT();
    }
  }

  if (auto p = expr->dcst<InitListExpr>()) {
    if (p->list.empty())
      return 0;
    return eval_arrlen(p->list[0]);
  }

  ABORT();
}

std::pair<TypeExpr*, std::string>
Ast2Asg::operator()(ast::DirectDeclaratorContext* ctx, TypeExpr* sub)
{
  if (auto p = ctx->Identifier())
    return { sub, p->getText() };

  if (auto p = ctx->declarator())
    return self(p, sub);

  if (ctx->LeftBracket()) {
    auto& arrayType = make<ArrayType>();
    arrayType.sub = sub;

    if (auto p = ctx->assignmentExpression())
      arrayType.len = eval_arrlen(self(p));
    else
      arrayType.len = ArrayType::kUnLen;

    return self(ctx->directDeclarator(), &arrayType);
  }

  if (ctx->LeftParen()) {
    auto& funcType = make<FunctionType>();
    funcType.sub = sub;

    if (auto p = ctx->parameterTypeList()) {
      for (auto&& i : p->parameterList()->parameterDeclaration())
        funcType.params.push_back(self(i));
    }

    return self(ctx->directDeclarator(), &funcType);
  }

  ABORT();
}

TypeExpr*
Ast2Asg::operator()(ast::AbstractDeclaratorContext* ctx, TypeExpr* sub)
{
  return self(ctx->directAbstractDeclarator(), sub);
}

TypeExpr*
Ast2Asg::operator()(ast::DirectAbstractDeclaratorContext* ctx, TypeExpr* sub)
{
  if (auto p = ctx->abstractDeclarator())
    return self(p, sub);

  if (ctx->LeftBracket()) {
    auto& arrayType = make<ArrayType>();
    arrayType.sub = sub;

    if (auto p = ctx->assignmentExpression())
      arrayType.len = eval_arrlen(self(p));
    else
      arrayType.len = ArrayType::kUnLen;

    sub = &arrayType;
  }

  else if (ctx->LeftParen()) {
    auto& funcType = make<FunctionType>();
    funcType.sub = sub;

    if (auto p = ctx->parameterTypeList()) {
      for (auto&& i : p->parameterList()->parameterDeclaration())
        funcType.params.push_back(self(i));
    }

    sub = &funcType;
  }

  else
    ABORT();

  if (auto p = ctx->directAbstractDeclarator())
    return self(p, sub);
  return sub;
}

//==============================================================================
// 表达式
//==============================================================================

Expr*
Ast2Asg::operator()(ast::ExpressionContext* ctx)
{
  auto list = ctx->assignmentExpression();
  Expr* ret = self(list[0]);

  for (unsigned i = 1; i < list.size(); ++i) {
    auto& node = make<BinaryExpr>();
    node.op = node.kComma;
    node.lft = ret;
    node.rht = self(list[i]);
    ret = &node;
  }

  return ret;
}

Expr*
Ast2Asg::operator()(ast::AssignmentExpressionContext* ctx)
{
  if (auto p = ctx->logicalOrExpression())
    return self(p);

  auto& ret = make<BinaryExpr>();
  ret.op = ret.kAssign;
  ret.lft = self(ctx->unaryExpression());
  ret.rht = self(ctx->assignmentExpression());
  return &ret;
}

Expr*
Ast2Asg::operator()(ast::LogicalOrExpressionContext* ctx)
{
  auto list = ctx->logicalAndExpression();
  Expr* ret = self(list[0]);

  for (unsigned i = 1; i < list.size(); ++i) {
    auto& node = make<BinaryExpr>();
    node.op = node.kOr;
    node.lft = ret;
    node.rht = self(list[i]);
    ret = &node;
  }

  return ret;
}

Expr*
Ast2Asg::operator()(ast::LogicalAndExpressionContext* ctx)
{
  auto list = ctx->equalityExpression();
  Expr* ret = self(list[0]);

  for (unsigned i = 1; i < list.size(); ++i) {
    auto& node = make<BinaryExpr>();
    node.op = node.kAnd;
    node.lft = ret;
    node.rht = self(list[i]);
    ret = &node;
  }

  return ret;
}

Expr*
Ast2Asg::operator()(ast::EqualityExpressionContext* ctx)
{
  auto& children = ctx->children;
  Expr* ret =
    self(dynamic_cast<ast::RelationalExpressionContext*>(children[0]));

  for (unsigned i = 1; i < children.size(); ++i) {
    auto& node = make<BinaryExpr>();

    auto token = dynamic_cast<antlr4::tree::TerminalNode*>(children[i])
                   ->getSymbol()
                   ->getType();
    switch (token) {
      case ast::Equal:
        node.op = node.kEq;
        break;

      case ast::NotEqual:
        node.op = node.kNe;
        break;

      default:
        ABORT();
    }

    node.lft = ret;
    node.rht =
      self(dynamic_cast<ast::RelationalExpressionContext*>(children[++i]));
    ret = &node;
  }

  return ret;
}

Expr*
Ast2Asg::operator()(ast::RelationalExpressionContext* ctx)
{
  auto& children = ctx->children;
  Expr* ret = self(dynamic_cast<ast::AdditiveExpressionContext*>(children[0]));

  for (unsigned i = 1; i < children.size(); ++i) {
    auto& node = make<BinaryExpr>();

    auto token = dynamic_cast<antlr4::tree::TerminalNode*>(children[i])
                   ->getSymbol()
                   ->getType();
    switch (token) {
      case ast::Less:
        node.op = node.kLt;
        break;

      case ast::LessEqual:
        node.op = node.kLe;
        break;

      case ast::Greater:
        node.op = node.kGt;
        break;

      case ast::GreaterEqual:
        node.op = node.kGe;
        break;

      default:
        ABORT();
    }

    node.lft = ret;
    node.rht =
      self(dynamic_cast<ast::AdditiveExpressionContext*>(children[++i]));
    ret = &node;
  }

  return ret;
}

Expr*
Ast2Asg::operator()(ast::AdditiveExpressionContext* ctx)
{
  auto& children = ctx->children;
  Expr* ret =
    self(dynamic_cast<ast::MultiplicativeExpressionContext*>(children[0]));

  for (unsigned i = 1; i < children.size(); ++i) {
    auto& node = make<BinaryExpr>();

    auto token = dynamic_cast<antlr4::tree::TerminalNode*>(children[i])
                   ->getSymbol()
                   ->getType();
    switch (token) {
      case ast::Plus:
        node.op = node.kAdd;
        break;

      case ast::Minus:
        node.op = node.kSub;
        break;

      default:
        ABORT();
    }

    node.lft = ret;
    node.rht =
      self(dynamic_cast<ast::MultiplicativeExpressionContext*>(children[++i]));
    ret = &node;
  }

  return ret;
}

Expr*
Ast2Asg::operator()(ast::MultiplicativeExpressionContext* ctx)
{
  auto& children = ctx->children;
  Expr* ret = self(dynamic_cast<ast::UnaryExpressionContext*>(children[0]));

  for (unsigned i = 1; i < children.size(); ++i) {
    auto& node = make<BinaryExpr>();

    auto token = dynamic_cast<antlr4::tree::TerminalNode*>(children[i])
                   ->getSymbol()
                   ->getType();
    switch (token) {
      case ast::Star:
        node.op = node.kMul;
        break;

      case ast::Div:
        node.op = node.kDiv;
        break;

      case ast::Mod:
        node.op = node.kMod;
        break;

      default:
        ABORT();
    }

    node.lft = ret;
    node.rht = self(dynamic_cast<ast::UnaryExpressionContext*>(children[++i]));
    ret = &node;
  }

  return ret;
}

Expr*
Ast2Asg::operator()(ast::UnaryExpressionContext* ctx)
{
  if (auto p = ctx->postfixExpression())
    return self(p);

  auto& ret = make<UnaryExpr>();

  switch (
    dynamic_cast<antlr4::tree::TerminalNode*>(ctx->unaryOperator()->children[0])
      ->getSymbol()
      ->getType()) {
    case ast::Plus:
      ret.op = ret.kPos;
      break;

    case ast::Minus:
      ret.op = ret.kNeg;
      break;

    case ast::Not:
      ret.op = ret.kNot;
      break;

    default:
      ABORT();
  }

  ret.sub = self(ctx->unaryExpression());

  return &ret;
}

Expr*
Ast2Asg::operator()(ast::PostfixExpressionContext* ctx)
{
  auto& children = ctx->children;
  auto sub = self(dynamic_cast<ast::PrimaryExpressionContext*>(children[0]));

  int i = 1;
  while (i < children.size()) {
    auto token = dynamic_cast<antlr4::tree::TerminalNode*>(children[i])
                   ->getSymbol()
                   ->getType();
    switch (token) {
      case ast::LeftBracket: {
        ++i;
        auto& ret = make<BinaryExpr>();
        ret.op = ret.kIndex;
        ret.lft = sub;
        ret.rht = self(dynamic_cast<ast::ExpressionContext*>(children[i]));
        i += 2;
        sub = &ret;
      } break;

      case ast::LeftParen: {
        ++i;
        auto& ret = make<CallExpr>();
        ret.head = sub;
        if (auto p =
              dynamic_cast<ast::ArgumentExpressionListContext*>(children[i])) {
          for (auto&& i : p->assignmentExpression())
            ret.args.push_back(self(i));
          ++i;
        }
        ++i;
        sub = &ret;
      } break;

      default:
        ABORT();
    }
  }

  return sub;
}

Expr*
Ast2Asg::operator()(ast::PrimaryExpressionContext* ctx)
{
  if (auto p = ctx->expression()) {
    auto& ret = make<ParenExpr>();
    ret.sub = self(p);
    return &ret;
  }

  if (auto p = ctx->Identifier()) {
    auto name = p->getText();
    auto& ret = make<DeclRefExpr>();
    ret.decl = mSymtbl->resolve(name);
    return &ret;
  }

  if (auto p = ctx->Constant()) {
    auto text = p->getText();

    auto& ret = make<IntegerLiteral>();

    ASSERT(!text.empty());
    if (text[0] != '0')
      ret.val = std::stoll(text);

    else if (text.size() == 1)
      ret.val = 0;

    else if (text[1] == 'x' || text[1] == 'X')
      ret.val = std::stoll(text.substr(2), nullptr, 16);

    else
      ret.val = std::stoll(text.substr(1), nullptr, 8);

    return &ret;
  }

  auto stringLiteral = ctx->StringLiteral();
  if (stringLiteral.size() != 0) {
    auto& ret = make<StringLiteral>();

    for (auto&& j : stringLiteral) {
      auto s = j->getText();
      std::size_t i = 1;
      while (i < s.size() - 1) {
        if (s[i] == '\\') {
          switch (s[++i]) {
            case '\n':
              break;

            case '\r':
              ++i;
              ASSERT(s[i] == '\n');
              break;

            case '\'':
              ret.val.push_back('\'');
              break;

            case '"':
              ret.val.push_back('"');
              break;

            case '?':
              ret.val.push_back('\?');
              break;

            case '\\':
              ret.val.push_back('\\');
              break;

            case 'a':
              ret.val.push_back('\a');
              break;

            case 'b':
              ret.val.push_back('\b');
              break;

            case 'f':
              ret.val.push_back('\f');
              break;

            case 'n':
              ret.val.push_back('\n');
              break;

            case 'r':
              ret.val.push_back('\r');
              break;

            case 't':
              ret.val.push_back('\t');
              break;

            case 'v':
              ret.val.push_back('\v');
              break;

            default:
              ABORT();
          }
        }

        else {
          ret.val.push_back(s[i]);
        }

        ++i;
      }
    }

    return &ret;
  }

  ABORT();
}

Expr*
Ast2Asg::operator()(ast::InitializerContext* ctx)
{
  if (auto p = ctx->assignmentExpression())
    return self(p);

  auto& ret = make<InitListExpr>();

  if (auto p = ctx->initializerList()) {
    for (auto&& i : p->initializer())
      ret.list.push_back(self(i));
  }

  return &ret;
}

//==============================================================================
// 语句
//==============================================================================

Stmt*
Ast2Asg::operator()(ast::StatementContext* ctx)
{
  if (auto p = ctx->compoundStatement())
    return self(p);

  if (auto p = ctx->expressionStatement())
    return self(p);

  if (auto p = ctx->selectionStatement())
    return self(p);

  if (auto p = ctx->iterationStatement())
    return self(p);

  if (auto p = ctx->jumpStatement())
    return self(p);

  ABORT();
}

CompoundStmt*
Ast2Asg::operator()(ast::CompoundStatementContext* ctx)
{
  auto& ret = make<CompoundStmt>();

  if (auto p = ctx->blockItemList()) {
    Symtbl localDecls(self);

    for (auto&& i : p->blockItem()) {
      if (auto q = i->declaration()) {
        auto& sub = make<DeclStmt>();
        sub.decls = self(q);
        ret.subs.push_back(&sub);
      }

      else if (auto q = i->statement())
        ret.subs.push_back(self(q));

      else
        ABORT();
    }
  }

  return &ret;
}

Stmt*
Ast2Asg::operator()(ast::ExpressionStatementContext* ctx)
{
  if (auto p = ctx->expression()) {
    auto& ret = make<ExprStmt>();
    ret.expr = self(p);
    return &ret;
  }

  return &make<Stmt>();
}

Stmt*
Ast2Asg::operator()(ast::SelectionStatementContext* ctx)
{
  auto& ret = make<IfStmt>();
  ret.cond = self(ctx->expression());

  auto subs = ctx->statement();
  ret.then = self(subs[0]);

  if (subs.size() > 1)
    ret.else_ = self(subs[1]);
  else
    ret.else_ = nullptr;

  return &ret;
}

Stmt*
Ast2Asg::operator()(ast::IterationStatementContext* ctx)
{
  if (ctx->Do()) {
    auto& ret = make<DoStmt>();

    {
      CurrentLoop guard(self, &ret);
      ret.body = self(ctx->statement());
    }

    ret.cond = self(ctx->expression());

    return &ret;
  }

  else {
    auto& ret = make<WhileStmt>();

    ret.cond = self(ctx->expression());

    {
      CurrentLoop guard(self, &ret);
      ret.body = self(ctx->statement());
    }

    return &ret;
  }
}

Stmt*
Ast2Asg::operator()(ast::JumpStatementContext* ctx)
{
  if (ctx->Continue()) {
    auto& ret = make<ContinueStmt>();
    ASSERT(mCurrentLoop != nullptr);
    ret.loop = mCurrentLoop;
    return &ret;
  }

  if (ctx->Break()) {
    auto& ret = make<BreakStmt>();
    ASSERT(mCurrentLoop != nullptr);
    ret.loop = mCurrentLoop;
    return &ret;
  }

  if (ctx->Return()) {
    auto& ret = make<ReturnStmt>();
    ret.func = mCurrentFunc;
    if (auto p = ctx->expression())
      ret.expr = self(p);
    return &ret;
  }

  ABORT();
}

//==============================================================================
// 声明
//==============================================================================

std::vector<Decl*>
Ast2Asg::operator()(ast::DeclarationContext* ctx)
{
  std::vector<Decl*> ret;

  auto specs = self(ctx->declarationSpecifiers());

  if (auto p = ctx->initDeclaratorList()) {
    for (auto&& j : p->initDeclarator())
      ret.push_back(self(j, specs));
  }

  // 如果 initDeclaratorList 为空则这行声明语句无意义
  return ret;
}

FunctionDecl*
Ast2Asg::operator()(ast::FunctionDefinitionContext* ctx)
{
  auto& ret = make<FunctionDecl>();
  mCurrentFunc = &ret;

  auto sq = self(ctx->declarationSpecifiers());
  ret.type.spec = sq.first, ret.type.qual = sq.second;

  auto [texp, name] = self(ctx->directDeclarator(), nullptr);
  auto& funcType = make<FunctionType>();
  funcType.sub = texp;
  ret.type.texp = &funcType;
  ret.name = std::move(name);

  Symtbl localDecls(self);
  if (auto p = ctx->parameterTypeList()) {
    for (auto&& i : p->parameterList()->parameterDeclaration()) {
      auto varDecl = self(i);
      ret.params.push_back(varDecl);
      funcType.params.push_back(varDecl);

      localDecls[varDecl->name] = varDecl; // !
    }
  }

  // 函数定义在签名之后就加入符号表，以允许递归调用
  (*mSymtbl)[ret.name] = &ret;

  ret.body = self(ctx->compoundStatement());

  return &ret;
}

Decl*
Ast2Asg::operator()(ast::InitDeclaratorContext* ctx, SpecQual sq)
{
  auto [texp, name] = self(ctx->declarator(), nullptr);
  Decl* ret;

  if (auto funcType = texp->dcst<FunctionType>()) {
    auto& fdecl = make<FunctionDecl>();
    fdecl.type.spec = sq.first;
    fdecl.type.qual = sq.second;
    fdecl.type.texp = funcType;
    fdecl.name = std::move(name);
    fdecl.params = funcType->params;

    if (ctx->initializer())
      ABORT();
    fdecl.body = nullptr;

    ret = &fdecl;
  }

  else {
    auto& vdecl = make<VarDecl>();
    vdecl.type.spec = sq.first;
    vdecl.type.qual = sq.second;
    vdecl.type.texp = texp;
    vdecl.name = std::move(name);

    if (auto p = ctx->initializer())
      vdecl.init = self(p);
    else
      vdecl.init = nullptr;

    ret = &vdecl;
  }

  // 这个实现允许符号重复定义，新定义会取代旧定义
  (*mSymtbl)[ret->name] = ret;
  return ret;
}

VarDecl*
Ast2Asg::operator()(ast::ParameterDeclarationContext* ctx)
{
  auto& ret = make<VarDecl>();

  if (auto p = ctx->declarationSpecifiers()) {
    auto sp = self(p);
    ret.type.spec = sp.first, ret.type.qual = sp.second;

    auto [texp, name] = self(ctx->declarator(), nullptr);
    ret.type.texp = texp;

    ret.name = std::move(name);
    ret.init = nullptr;
  }

  else if (auto p = ctx->declarationSpecifiers2()) {
    auto sp = self(p);
    ret.type.spec = sp.first, ret.type.qual = sp.second;

    if (auto q = ctx->abstractDeclarator())
      ret.type.texp = self(q, nullptr);
    else
      ret.type.texp = nullptr;

    ret.name = std::string();
    ret.init = nullptr;
  }

  else
    ABORT();

  return &ret;
}

} // namespace asg
