// Generated from /root/SYsU-lang/parser/C.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Auto=1, Break=2, Case=3, Char=4, Const=5, Continue=6, Default=7, Do=8, 
		Double=9, Else=10, Enum=11, Extern=12, Float=13, For=14, Goto=15, If=16, 
		Inline=17, Int=18, Long=19, Register=20, Restrict=21, Return=22, Short=23, 
		Signed=24, Sizeof=25, Static=26, Struct=27, Switch=28, Typedef=29, Union=30, 
		Unsigned=31, Void=32, Volatile=33, While=34, Alignas=35, Alignof=36, Atomic=37, 
		Bool=38, Complex=39, Generic=40, Imaginary=41, Noreturn=42, StaticAssert=43, 
		ThreadLocal=44, LeftParen=45, RightParen=46, LeftBracket=47, RightBracket=48, 
		LeftBrace=49, RightBrace=50, Less=51, LessEqual=52, Greater=53, GreaterEqual=54, 
		LeftShift=55, RightShift=56, Plus=57, PlusPlus=58, Minus=59, MinusMinus=60, 
		Star=61, Div=62, Mod=63, And=64, Or=65, AndAnd=66, OrOr=67, Caret=68, 
		Not=69, Tilde=70, Question=71, Colon=72, Semi=73, Comma=74, Assign=75, 
		StarAssign=76, DivAssign=77, ModAssign=78, PlusAssign=79, MinusAssign=80, 
		LeftShiftAssign=81, RightShiftAssign=82, AndAssign=83, XorAssign=84, OrAssign=85, 
		Equal=86, NotEqual=87, Arrow=88, Dot=89, Ellipsis=90, Identifier=91, Constant=92, 
		DigitSequence=93, StringLiteral=94, ComplexDefine=95, IncludeDirective=96, 
		AsmBlock=97, LineAfterPreprocessing=98, LineDirective=99, PragmaDirective=100, 
		Whitespace=101, Newline=102, BlockComment=103, LineComment=104;
	public static final int
		RULE_primaryExpression = 0, RULE_postfixExpression = 1, RULE_argumentExpressionList = 2, 
		RULE_unaryExpression = 3, RULE_unaryOperator = 4, RULE_multiplicativeExpression = 5, 
		RULE_additiveExpression = 6, RULE_relationalExpression = 7, RULE_equalityExpression = 8, 
		RULE_logicalAndExpression = 9, RULE_logicalOrExpression = 10, RULE_assignmentExpression = 11, 
		RULE_expression = 12, RULE_constantExpression = 13, RULE_declaration = 14, 
		RULE_declarationSpecifiers = 15, RULE_declarationSpecifiers2 = 16, RULE_declarationSpecifier = 17, 
		RULE_initDeclaratorList = 18, RULE_initDeclarator = 19, RULE_typeSpecifier = 20, 
		RULE_typeQualifier = 21, RULE_declarator = 22, RULE_directDeclarator = 23, 
		RULE_pointer = 24, RULE_typeQualifierList = 25, RULE_parameterTypeList = 26, 
		RULE_parameterList = 27, RULE_parameterDeclaration = 28, RULE_identifierList = 29, 
		RULE_abstractDeclarator = 30, RULE_directAbstractDeclarator = 31, RULE_initializer = 32, 
		RULE_initializerList = 33, RULE_designation = 34, RULE_designatorList = 35, 
		RULE_designator = 36, RULE_staticAssertDeclaration = 37, RULE_statement = 38, 
		RULE_labeledStatement = 39, RULE_compoundStatement = 40, RULE_blockItemList = 41, 
		RULE_blockItem = 42, RULE_expressionStatement = 43, RULE_selectionStatement = 44, 
		RULE_iterationStatement = 45, RULE_compilationUnit = 46, RULE_translationUnit = 47, 
		RULE_externalDeclaration = 48, RULE_functionDefinition = 49, RULE_declarationList = 50;
	private static String[] makeRuleNames() {
		return new String[] {
			"primaryExpression", "postfixExpression", "argumentExpressionList", "unaryExpression", 
			"unaryOperator", "multiplicativeExpression", "additiveExpression", "relationalExpression", 
			"equalityExpression", "logicalAndExpression", "logicalOrExpression", 
			"assignmentExpression", "expression", "constantExpression", "declaration", 
			"declarationSpecifiers", "declarationSpecifiers2", "declarationSpecifier", 
			"initDeclaratorList", "initDeclarator", "typeSpecifier", "typeQualifier", 
			"declarator", "directDeclarator", "pointer", "typeQualifierList", "parameterTypeList", 
			"parameterList", "parameterDeclaration", "identifierList", "abstractDeclarator", 
			"directAbstractDeclarator", "initializer", "initializerList", "designation", 
			"designatorList", "designator", "staticAssertDeclaration", "statement", 
			"labeledStatement", "compoundStatement", "blockItemList", "blockItem", 
			"expressionStatement", "selectionStatement", "iterationStatement", "compilationUnit", 
			"translationUnit", "externalDeclaration", "functionDefinition", "declarationList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'auto'", "'break'", "'case'", "'char'", "'const'", "'continue'", 
			"'default'", "'do'", "'double'", "'else'", "'enum'", "'extern'", "'float'", 
			"'for'", "'goto'", "'if'", "'inline'", "'int'", "'long'", "'register'", 
			"'restrict'", "'return'", "'short'", "'signed'", "'sizeof'", "'static'", 
			"'struct'", "'switch'", "'typedef'", "'union'", "'unsigned'", "'void'", 
			"'volatile'", "'while'", "'_Alignas'", "'_Alignof'", "'_Atomic'", "'_Bool'", 
			"'_Complex'", "'_Generic'", "'_Imaginary'", "'_Noreturn'", "'_Static_assert'", 
			"'_Thread_local'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'<'", "'<='", 
			"'>'", "'>='", "'<<'", "'>>'", "'+'", "'++'", "'-'", "'--'", "'*'", "'/'", 
			"'%'", "'&'", "'|'", "'&&'", "'||'", "'^'", "'!'", "'~'", "'?'", "':'", 
			"';'", "','", "'='", "'*='", "'/='", "'%='", "'+='", "'-='", "'<<='", 
			"'>>='", "'&='", "'^='", "'|='", "'=='", "'!='", "'->'", "'.'", "'...'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Auto", "Break", "Case", "Char", "Const", "Continue", "Default", 
			"Do", "Double", "Else", "Enum", "Extern", "Float", "For", "Goto", "If", 
			"Inline", "Int", "Long", "Register", "Restrict", "Return", "Short", "Signed", 
			"Sizeof", "Static", "Struct", "Switch", "Typedef", "Union", "Unsigned", 
			"Void", "Volatile", "While", "Alignas", "Alignof", "Atomic", "Bool", 
			"Complex", "Generic", "Imaginary", "Noreturn", "StaticAssert", "ThreadLocal", 
			"LeftParen", "RightParen", "LeftBracket", "RightBracket", "LeftBrace", 
			"RightBrace", "Less", "LessEqual", "Greater", "GreaterEqual", "LeftShift", 
			"RightShift", "Plus", "PlusPlus", "Minus", "MinusMinus", "Star", "Div", 
			"Mod", "And", "Or", "AndAnd", "OrOr", "Caret", "Not", "Tilde", "Question", 
			"Colon", "Semi", "Comma", "Assign", "StarAssign", "DivAssign", "ModAssign", 
			"PlusAssign", "MinusAssign", "LeftShiftAssign", "RightShiftAssign", "AndAssign", 
			"XorAssign", "OrAssign", "Equal", "NotEqual", "Arrow", "Dot", "Ellipsis", 
			"Identifier", "Constant", "DigitSequence", "StringLiteral", "ComplexDefine", 
			"IncludeDirective", "AsmBlock", "LineAfterPreprocessing", "LineDirective", 
			"PragmaDirective", "Whitespace", "Newline", "BlockComment", "LineComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "C.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(CParser.Identifier, 0); }
		public TerminalNode Constant() { return getToken(CParser.Constant, 0); }
		public List<TerminalNode> StringLiteral() { return getTokens(CParser.StringLiteral); }
		public TerminalNode StringLiteral(int i) {
			return getToken(CParser.StringLiteral, i);
		}
		public TerminalNode LeftParen() { return getToken(CParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(CParser.RightParen, 0); }
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_primaryExpression);
		int _la;
		try {
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				match(Identifier);
				}
				break;
			case Constant:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(Constant);
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(105); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(104);
					match(StringLiteral);
					}
					}
					setState(107); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==StringLiteral );
				}
				break;
			case LeftParen:
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				match(LeftParen);
				setState(110);
				expression();
				setState(111);
				match(RightParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostfixExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(CParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(CParser.LeftBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(CParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(CParser.RightBracket, i);
		}
		public List<TerminalNode> LeftParen() { return getTokens(CParser.LeftParen); }
		public TerminalNode LeftParen(int i) {
			return getToken(CParser.LeftParen, i);
		}
		public List<TerminalNode> RightParen() { return getTokens(CParser.RightParen); }
		public TerminalNode RightParen(int i) {
			return getToken(CParser.RightParen, i);
		}
		public List<ArgumentExpressionListContext> argumentExpressionList() {
			return getRuleContexts(ArgumentExpressionListContext.class);
		}
		public ArgumentExpressionListContext argumentExpressionList(int i) {
			return getRuleContext(ArgumentExpressionListContext.class,i);
		}
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_postfixExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(115);
			primaryExpression();
			}
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LeftParen || _la==LeftBracket) {
				{
				setState(125);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LeftBracket:
					{
					setState(116);
					match(LeftBracket);
					setState(117);
					expression();
					setState(118);
					match(RightBracket);
					}
					break;
				case LeftParen:
					{
					setState(120);
					match(LeftParen);
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
						{
						setState(121);
						argumentExpressionList();
						}
					}

					setState(124);
					match(RightParen);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentExpressionListContext extends ParserRuleContext {
		public List<AssignmentExpressionContext> assignmentExpression() {
			return getRuleContexts(AssignmentExpressionContext.class);
		}
		public AssignmentExpressionContext assignmentExpression(int i) {
			return getRuleContext(AssignmentExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CParser.Comma, i);
		}
		public ArgumentExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentExpressionList; }
	}

	public final ArgumentExpressionListContext argumentExpressionList() throws RecognitionException {
		ArgumentExpressionListContext _localctx = new ArgumentExpressionListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_argumentExpressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			assignmentExpression();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(131);
				match(Comma);
				setState(132);
				assignmentExpression();
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_unaryExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParen:
			case Identifier:
			case Constant:
			case StringLiteral:
				{
				setState(138);
				postfixExpression();
				}
				break;
			case Plus:
			case Minus:
			case Not:
				{
				setState(139);
				unaryOperator();
				setState(140);
				unaryExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOperatorContext extends ParserRuleContext {
		public TerminalNode Plus() { return getToken(CParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(CParser.Minus, 0); }
		public TerminalNode Not() { return getToken(CParser.Not, 0); }
		public UnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOperator; }
	}

	public final UnaryOperatorContext unaryOperator() throws RecognitionException {
		UnaryOperatorContext _localctx = new UnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_unaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			_la = _input.LA(1);
			if ( !(((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (Plus - 57)) | (1L << (Minus - 57)) | (1L << (Not - 57)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public List<TerminalNode> Star() { return getTokens(CParser.Star); }
		public TerminalNode Star(int i) {
			return getToken(CParser.Star, i);
		}
		public List<TerminalNode> Div() { return getTokens(CParser.Div); }
		public TerminalNode Div(int i) {
			return getToken(CParser.Div, i);
		}
		public List<TerminalNode> Mod() { return getTokens(CParser.Mod); }
		public TerminalNode Mod(int i) {
			return getToken(CParser.Mod, i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			unaryExpression();
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Star) | (1L << Div) | (1L << Mod))) != 0)) {
				{
				{
				setState(147);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Star) | (1L << Div) | (1L << Mod))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(148);
				unaryExpression();
				}
				}
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<TerminalNode> Plus() { return getTokens(CParser.Plus); }
		public TerminalNode Plus(int i) {
			return getToken(CParser.Plus, i);
		}
		public List<TerminalNode> Minus() { return getTokens(CParser.Minus); }
		public TerminalNode Minus(int i) {
			return getToken(CParser.Minus, i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			multiplicativeExpression();
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Plus || _la==Minus) {
				{
				{
				setState(155);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(156);
				multiplicativeExpression();
				}
				}
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalExpressionContext extends ParserRuleContext {
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<TerminalNode> Less() { return getTokens(CParser.Less); }
		public TerminalNode Less(int i) {
			return getToken(CParser.Less, i);
		}
		public List<TerminalNode> Greater() { return getTokens(CParser.Greater); }
		public TerminalNode Greater(int i) {
			return getToken(CParser.Greater, i);
		}
		public List<TerminalNode> LessEqual() { return getTokens(CParser.LessEqual); }
		public TerminalNode LessEqual(int i) {
			return getToken(CParser.LessEqual, i);
		}
		public List<TerminalNode> GreaterEqual() { return getTokens(CParser.GreaterEqual); }
		public TerminalNode GreaterEqual(int i) {
			return getToken(CParser.GreaterEqual, i);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_relationalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			additiveExpression();
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) {
				{
				{
				setState(163);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(164);
				additiveExpression();
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualityExpressionContext extends ParserRuleContext {
		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}
		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class,i);
		}
		public List<TerminalNode> Equal() { return getTokens(CParser.Equal); }
		public TerminalNode Equal(int i) {
			return getToken(CParser.Equal, i);
		}
		public List<TerminalNode> NotEqual() { return getTokens(CParser.NotEqual); }
		public TerminalNode NotEqual(int i) {
			return getToken(CParser.NotEqual, i);
		}
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_equalityExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			relationalExpression();
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Equal || _la==NotEqual) {
				{
				{
				setState(171);
				_la = _input.LA(1);
				if ( !(_la==Equal || _la==NotEqual) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(172);
				relationalExpression();
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalAndExpressionContext extends ParserRuleContext {
		public List<EqualityExpressionContext> equalityExpression() {
			return getRuleContexts(EqualityExpressionContext.class);
		}
		public EqualityExpressionContext equalityExpression(int i) {
			return getRuleContext(EqualityExpressionContext.class,i);
		}
		public List<TerminalNode> AndAnd() { return getTokens(CParser.AndAnd); }
		public TerminalNode AndAnd(int i) {
			return getToken(CParser.AndAnd, i);
		}
		public LogicalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAndExpression; }
	}

	public final LogicalAndExpressionContext logicalAndExpression() throws RecognitionException {
		LogicalAndExpressionContext _localctx = new LogicalAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_logicalAndExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			equalityExpression();
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AndAnd) {
				{
				{
				setState(179);
				match(AndAnd);
				setState(180);
				equalityExpression();
				}
				}
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalOrExpressionContext extends ParserRuleContext {
		public List<LogicalAndExpressionContext> logicalAndExpression() {
			return getRuleContexts(LogicalAndExpressionContext.class);
		}
		public LogicalAndExpressionContext logicalAndExpression(int i) {
			return getRuleContext(LogicalAndExpressionContext.class,i);
		}
		public List<TerminalNode> OrOr() { return getTokens(CParser.OrOr); }
		public TerminalNode OrOr(int i) {
			return getToken(CParser.OrOr, i);
		}
		public LogicalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOrExpression; }
	}

	public final LogicalOrExpressionContext logicalOrExpression() throws RecognitionException {
		LogicalOrExpressionContext _localctx = new LogicalOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_logicalOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			logicalAndExpression();
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OrOr) {
				{
				{
				setState(187);
				match(OrOr);
				setState(188);
				logicalAndExpression();
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentExpressionContext extends ParserRuleContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode Assign() { return getToken(CParser.Assign, 0); }
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpression; }
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_assignmentExpression);
		try {
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(194);
				logicalOrExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				unaryExpression();
				setState(196);
				match(Assign);
				setState(197);
				assignmentExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public List<AssignmentExpressionContext> assignmentExpression() {
			return getRuleContexts(AssignmentExpressionContext.class);
		}
		public AssignmentExpressionContext assignmentExpression(int i) {
			return getRuleContext(AssignmentExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CParser.Comma, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			assignmentExpression();
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(202);
				match(Comma);
				setState(203);
				assignmentExpression();
				}
				}
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantExpressionContext extends ParserRuleContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantExpression; }
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_constantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			logicalOrExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationSpecifiersContext declarationSpecifiers() {
			return getRuleContext(DeclarationSpecifiersContext.class,0);
		}
		public TerminalNode Semi() { return getToken(CParser.Semi, 0); }
		public InitDeclaratorListContext initDeclaratorList() {
			return getRuleContext(InitDeclaratorListContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			declarationSpecifiers();
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Star - 45)) | (1L << (Caret - 45)) | (1L << (Identifier - 45)))) != 0)) {
				{
				setState(212);
				initDeclaratorList();
				}
			}

			setState(215);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationSpecifiersContext extends ParserRuleContext {
		public List<DeclarationSpecifierContext> declarationSpecifier() {
			return getRuleContexts(DeclarationSpecifierContext.class);
		}
		public DeclarationSpecifierContext declarationSpecifier(int i) {
			return getRuleContext(DeclarationSpecifierContext.class,i);
		}
		public DeclarationSpecifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationSpecifiers; }
	}

	public final DeclarationSpecifiersContext declarationSpecifiers() throws RecognitionException {
		DeclarationSpecifiersContext _localctx = new DeclarationSpecifiersContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_declarationSpecifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(217);
				declarationSpecifier();
				}
				}
				setState(220); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationSpecifiers2Context extends ParserRuleContext {
		public List<DeclarationSpecifierContext> declarationSpecifier() {
			return getRuleContexts(DeclarationSpecifierContext.class);
		}
		public DeclarationSpecifierContext declarationSpecifier(int i) {
			return getRuleContext(DeclarationSpecifierContext.class,i);
		}
		public DeclarationSpecifiers2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationSpecifiers2; }
	}

	public final DeclarationSpecifiers2Context declarationSpecifiers2() throws RecognitionException {
		DeclarationSpecifiers2Context _localctx = new DeclarationSpecifiers2Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_declarationSpecifiers2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(222);
				declarationSpecifier();
				}
				}
				setState(225); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationSpecifierContext extends ParserRuleContext {
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public TypeQualifierContext typeQualifier() {
			return getRuleContext(TypeQualifierContext.class,0);
		}
		public DeclarationSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationSpecifier; }
	}

	public final DeclarationSpecifierContext declarationSpecifier() throws RecognitionException {
		DeclarationSpecifierContext _localctx = new DeclarationSpecifierContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_declarationSpecifier);
		try {
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Char:
			case Int:
			case Long:
			case Void:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				typeSpecifier();
				}
				break;
			case Const:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				typeQualifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitDeclaratorListContext extends ParserRuleContext {
		public List<InitDeclaratorContext> initDeclarator() {
			return getRuleContexts(InitDeclaratorContext.class);
		}
		public InitDeclaratorContext initDeclarator(int i) {
			return getRuleContext(InitDeclaratorContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CParser.Comma, i);
		}
		public InitDeclaratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initDeclaratorList; }
	}

	public final InitDeclaratorListContext initDeclaratorList() throws RecognitionException {
		InitDeclaratorListContext _localctx = new InitDeclaratorListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_initDeclaratorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			initDeclarator();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(232);
				match(Comma);
				setState(233);
				initDeclarator();
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitDeclaratorContext extends ParserRuleContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public TerminalNode Assign() { return getToken(CParser.Assign, 0); }
		public InitializerContext initializer() {
			return getRuleContext(InitializerContext.class,0);
		}
		public InitDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initDeclarator; }
	}

	public final InitDeclaratorContext initDeclarator() throws RecognitionException {
		InitDeclaratorContext _localctx = new InitDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_initDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			declarator();
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(240);
				match(Assign);
				setState(241);
				initializer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSpecifierContext extends ParserRuleContext {
		public TerminalNode Void() { return getToken(CParser.Void, 0); }
		public TerminalNode Char() { return getToken(CParser.Char, 0); }
		public TerminalNode Int() { return getToken(CParser.Int, 0); }
		public TerminalNode Long() { return getToken(CParser.Long, 0); }
		public TypeSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSpecifier; }
	}

	public final TypeSpecifierContext typeSpecifier() throws RecognitionException {
		TypeSpecifierContext _localctx = new TypeSpecifierContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_typeSpecifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Int) | (1L << Long) | (1L << Void))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeQualifierContext extends ParserRuleContext {
		public TerminalNode Const() { return getToken(CParser.Const, 0); }
		public TypeQualifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeQualifier; }
	}

	public final TypeQualifierContext typeQualifier() throws RecognitionException {
		TypeQualifierContext _localctx = new TypeQualifierContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_typeQualifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(Const);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaratorContext extends ParserRuleContext {
		public DirectDeclaratorContext directDeclarator() {
			return getRuleContext(DirectDeclaratorContext.class,0);
		}
		public PointerContext pointer() {
			return getRuleContext(PointerContext.class,0);
		}
		public DeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarator; }
	}

	public final DeclaratorContext declarator() throws RecognitionException {
		DeclaratorContext _localctx = new DeclaratorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Star || _la==Caret) {
				{
				setState(248);
				pointer();
				}
			}

			setState(251);
			directDeclarator(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectDeclaratorContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(CParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(CParser.LeftParen, 0); }
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(CParser.RightParen, 0); }
		public DirectDeclaratorContext directDeclarator() {
			return getRuleContext(DirectDeclaratorContext.class,0);
		}
		public TerminalNode LeftBracket() { return getToken(CParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(CParser.RightBracket, 0); }
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class,0);
		}
		public DirectDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directDeclarator; }
	}

	public final DirectDeclaratorContext directDeclarator() throws RecognitionException {
		return directDeclarator(0);
	}

	private DirectDeclaratorContext directDeclarator(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DirectDeclaratorContext _localctx = new DirectDeclaratorContext(_ctx, _parentState);
		DirectDeclaratorContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_directDeclarator, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				setState(254);
				match(Identifier);
				}
				break;
			case LeftParen:
				{
				setState(255);
				match(LeftParen);
				setState(256);
				declarator();
				setState(257);
				match(RightParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(275);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(273);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
						setState(261);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(262);
						match(LeftBracket);
						setState(264);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
							{
							setState(263);
							assignmentExpression();
							}
						}

						setState(266);
						match(RightBracket);
						}
						break;
					case 2:
						{
						_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
						setState(267);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(268);
						match(LeftParen);
						setState(270);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0)) {
							{
							setState(269);
							parameterTypeList();
							}
						}

						setState(272);
						match(RightParen);
						}
						break;
					}
					} 
				}
				setState(277);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PointerContext extends ParserRuleContext {
		public List<TerminalNode> Star() { return getTokens(CParser.Star); }
		public TerminalNode Star(int i) {
			return getToken(CParser.Star, i);
		}
		public List<TerminalNode> Caret() { return getTokens(CParser.Caret); }
		public TerminalNode Caret(int i) {
			return getToken(CParser.Caret, i);
		}
		public List<TypeQualifierListContext> typeQualifierList() {
			return getRuleContexts(TypeQualifierListContext.class);
		}
		public TypeQualifierListContext typeQualifierList(int i) {
			return getRuleContext(TypeQualifierListContext.class,i);
		}
		public PointerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointer; }
	}

	public final PointerContext pointer() throws RecognitionException {
		PointerContext _localctx = new PointerContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_pointer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(278);
				_la = _input.LA(1);
				if ( !(_la==Star || _la==Caret) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Const) {
					{
					setState(279);
					typeQualifierList();
					}
				}

				}
				}
				setState(284); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Star || _la==Caret );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeQualifierListContext extends ParserRuleContext {
		public List<TypeQualifierContext> typeQualifier() {
			return getRuleContexts(TypeQualifierContext.class);
		}
		public TypeQualifierContext typeQualifier(int i) {
			return getRuleContext(TypeQualifierContext.class,i);
		}
		public TypeQualifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeQualifierList; }
	}

	public final TypeQualifierListContext typeQualifierList() throws RecognitionException {
		TypeQualifierListContext _localctx = new TypeQualifierListContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_typeQualifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(286);
				typeQualifier();
				}
				}
				setState(289); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Const );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterTypeListContext extends ParserRuleContext {
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ParameterTypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterTypeList; }
	}

	public final ParameterTypeListContext parameterTypeList() throws RecognitionException {
		ParameterTypeListContext _localctx = new ParameterTypeListContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_parameterTypeList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			parameterList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterDeclarationContext> parameterDeclaration() {
			return getRuleContexts(ParameterDeclarationContext.class);
		}
		public ParameterDeclarationContext parameterDeclaration(int i) {
			return getRuleContext(ParameterDeclarationContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			parameterDeclaration();
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(294);
				match(Comma);
				setState(295);
				parameterDeclaration();
				}
				}
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterDeclarationContext extends ParserRuleContext {
		public DeclarationSpecifiersContext declarationSpecifiers() {
			return getRuleContext(DeclarationSpecifiersContext.class,0);
		}
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public DeclarationSpecifiers2Context declarationSpecifiers2() {
			return getRuleContext(DeclarationSpecifiers2Context.class,0);
		}
		public AbstractDeclaratorContext abstractDeclarator() {
			return getRuleContext(AbstractDeclaratorContext.class,0);
		}
		public ParameterDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDeclaration; }
	}

	public final ParameterDeclarationContext parameterDeclaration() throws RecognitionException {
		ParameterDeclarationContext _localctx = new ParameterDeclarationContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_parameterDeclaration);
		int _la;
		try {
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(301);
				declarationSpecifiers();
				setState(302);
				declarator();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(304);
				declarationSpecifiers2();
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (LeftBracket - 45)) | (1L << (Star - 45)) | (1L << (Caret - 45)))) != 0)) {
					{
					setState(305);
					abstractDeclarator();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierListContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(CParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(CParser.Identifier, i);
		}
		public List<TerminalNode> Comma() { return getTokens(CParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CParser.Comma, i);
		}
		public IdentifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierList; }
	}

	public final IdentifierListContext identifierList() throws RecognitionException {
		IdentifierListContext _localctx = new IdentifierListContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_identifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(Identifier);
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(311);
				match(Comma);
				setState(312);
				match(Identifier);
				}
				}
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbstractDeclaratorContext extends ParserRuleContext {
		public PointerContext pointer() {
			return getRuleContext(PointerContext.class,0);
		}
		public DirectAbstractDeclaratorContext directAbstractDeclarator() {
			return getRuleContext(DirectAbstractDeclaratorContext.class,0);
		}
		public AbstractDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abstractDeclarator; }
	}

	public final AbstractDeclaratorContext abstractDeclarator() throws RecognitionException {
		AbstractDeclaratorContext _localctx = new AbstractDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_abstractDeclarator);
		int _la;
		try {
			setState(323);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(318);
				pointer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Star || _la==Caret) {
					{
					setState(319);
					pointer();
					}
				}

				setState(322);
				directAbstractDeclarator(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectAbstractDeclaratorContext extends ParserRuleContext {
		public TerminalNode LeftParen() { return getToken(CParser.LeftParen, 0); }
		public AbstractDeclaratorContext abstractDeclarator() {
			return getRuleContext(AbstractDeclaratorContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(CParser.RightParen, 0); }
		public TerminalNode LeftBracket() { return getToken(CParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(CParser.RightBracket, 0); }
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class,0);
		}
		public DirectAbstractDeclaratorContext directAbstractDeclarator() {
			return getRuleContext(DirectAbstractDeclaratorContext.class,0);
		}
		public DirectAbstractDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directAbstractDeclarator; }
	}

	public final DirectAbstractDeclaratorContext directAbstractDeclarator() throws RecognitionException {
		return directAbstractDeclarator(0);
	}

	private DirectAbstractDeclaratorContext directAbstractDeclarator(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DirectAbstractDeclaratorContext _localctx = new DirectAbstractDeclaratorContext(_ctx, _parentState);
		DirectAbstractDeclaratorContext _prevctx = _localctx;
		int _startState = 62;
		enterRecursionRule(_localctx, 62, RULE_directAbstractDeclarator, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(326);
				match(LeftParen);
				setState(327);
				abstractDeclarator();
				setState(328);
				match(RightParen);
				}
				break;
			case 2:
				{
				setState(330);
				match(LeftBracket);
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
					{
					setState(331);
					assignmentExpression();
					}
				}

				setState(334);
				match(RightBracket);
				}
				break;
			case 3:
				{
				setState(335);
				match(LeftParen);
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0)) {
					{
					setState(336);
					parameterTypeList();
					}
				}

				setState(339);
				match(RightParen);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(356);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(354);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
					case 1:
						{
						_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
						setState(342);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(343);
						match(LeftBracket);
						setState(345);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
							{
							setState(344);
							assignmentExpression();
							}
						}

						setState(347);
						match(RightBracket);
						}
						break;
					case 2:
						{
						_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
						setState(348);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(349);
						match(LeftParen);
						setState(351);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0)) {
							{
							setState(350);
							parameterTypeList();
							}
						}

						setState(353);
						match(RightParen);
						}
						break;
					}
					} 
				}
				setState(358);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class InitializerContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public TerminalNode LeftBrace() { return getToken(CParser.LeftBrace, 0); }
		public InitializerListContext initializerList() {
			return getRuleContext(InitializerListContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(CParser.RightBrace, 0); }
		public TerminalNode Comma() { return getToken(CParser.Comma, 0); }
		public InitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializer; }
	}

	public final InitializerContext initializer() throws RecognitionException {
		InitializerContext _localctx = new InitializerContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_initializer);
		int _la;
		try {
			setState(367);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParen:
			case Plus:
			case Minus:
			case Not:
			case Identifier:
			case Constant:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(359);
				assignmentExpression();
				}
				break;
			case LeftBrace:
				enterOuterAlt(_localctx, 2);
				{
				setState(360);
				match(LeftBrace);
				setState(361);
				initializerList();
				setState(363);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(362);
					match(Comma);
					}
				}

				setState(365);
				match(RightBrace);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitializerListContext extends ParserRuleContext {
		public List<InitializerContext> initializer() {
			return getRuleContexts(InitializerContext.class);
		}
		public InitializerContext initializer(int i) {
			return getRuleContext(InitializerContext.class,i);
		}
		public List<DesignationContext> designation() {
			return getRuleContexts(DesignationContext.class);
		}
		public DesignationContext designation(int i) {
			return getRuleContext(DesignationContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CParser.Comma, i);
		}
		public InitializerListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializerList; }
	}

	public final InitializerListContext initializerList() throws RecognitionException {
		InitializerListContext _localctx = new InitializerListContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_initializerList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LeftBracket) {
				{
				setState(369);
				designation();
				}
			}

			setState(372);
			initializer();
			setState(380);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(373);
					match(Comma);
					setState(375);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LeftBracket) {
						{
						setState(374);
						designation();
						}
					}

					setState(377);
					initializer();
					}
					} 
				}
				setState(382);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DesignationContext extends ParserRuleContext {
		public DesignatorListContext designatorList() {
			return getRuleContext(DesignatorListContext.class,0);
		}
		public TerminalNode Assign() { return getToken(CParser.Assign, 0); }
		public DesignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_designation; }
	}

	public final DesignationContext designation() throws RecognitionException {
		DesignationContext _localctx = new DesignationContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_designation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			designatorList();
			setState(384);
			match(Assign);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DesignatorListContext extends ParserRuleContext {
		public List<DesignatorContext> designator() {
			return getRuleContexts(DesignatorContext.class);
		}
		public DesignatorContext designator(int i) {
			return getRuleContext(DesignatorContext.class,i);
		}
		public DesignatorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_designatorList; }
	}

	public final DesignatorListContext designatorList() throws RecognitionException {
		DesignatorListContext _localctx = new DesignatorListContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_designatorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(386);
				designator();
				}
				}
				setState(389); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LeftBracket );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DesignatorContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(CParser.LeftBracket, 0); }
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public TerminalNode RightBracket() { return getToken(CParser.RightBracket, 0); }
		public DesignatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_designator; }
	}

	public final DesignatorContext designator() throws RecognitionException {
		DesignatorContext _localctx = new DesignatorContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_designator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			match(LeftBracket);
			setState(392);
			constantExpression();
			setState(393);
			match(RightBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StaticAssertDeclarationContext extends ParserRuleContext {
		public TerminalNode StaticAssert() { return getToken(CParser.StaticAssert, 0); }
		public TerminalNode LeftParen() { return getToken(CParser.LeftParen, 0); }
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public TerminalNode Comma() { return getToken(CParser.Comma, 0); }
		public TerminalNode RightParen() { return getToken(CParser.RightParen, 0); }
		public TerminalNode Semi() { return getToken(CParser.Semi, 0); }
		public List<TerminalNode> StringLiteral() { return getTokens(CParser.StringLiteral); }
		public TerminalNode StringLiteral(int i) {
			return getToken(CParser.StringLiteral, i);
		}
		public StaticAssertDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staticAssertDeclaration; }
	}

	public final StaticAssertDeclarationContext staticAssertDeclaration() throws RecognitionException {
		StaticAssertDeclarationContext _localctx = new StaticAssertDeclarationContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_staticAssertDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			match(StaticAssert);
			setState(396);
			match(LeftParen);
			setState(397);
			constantExpression();
			setState(398);
			match(Comma);
			setState(400); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(399);
				match(StringLiteral);
				}
				}
				setState(402); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==StringLiteral );
			setState(404);
			match(RightParen);
			setState(405);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_statement);
		try {
			setState(411);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftBrace:
				enterOuterAlt(_localctx, 1);
				{
				setState(407);
				compoundStatement();
				}
				break;
			case LeftParen:
			case Plus:
			case Minus:
			case Not:
			case Semi:
			case Identifier:
			case Constant:
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(408);
				expressionStatement();
				}
				break;
			case If:
				enterOuterAlt(_localctx, 3);
				{
				setState(409);
				selectionStatement();
				}
				break;
			case Do:
			case While:
				enterOuterAlt(_localctx, 4);
				{
				setState(410);
				iterationStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabeledStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(CParser.Identifier, 0); }
		public TerminalNode Colon() { return getToken(CParser.Colon, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Case() { return getToken(CParser.Case, 0); }
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public TerminalNode Default() { return getToken(CParser.Default, 0); }
		public LabeledStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledStatement; }
	}

	public final LabeledStatementContext labeledStatement() throws RecognitionException {
		LabeledStatementContext _localctx = new LabeledStatementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_labeledStatement);
		try {
			setState(424);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(413);
				match(Identifier);
				setState(414);
				match(Colon);
				setState(415);
				statement();
				}
				break;
			case Case:
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				match(Case);
				setState(417);
				constantExpression();
				setState(418);
				match(Colon);
				setState(419);
				statement();
				}
				break;
			case Default:
				enterOuterAlt(_localctx, 3);
				{
				setState(421);
				match(Default);
				setState(422);
				match(Colon);
				setState(423);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(CParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(CParser.RightBrace, 0); }
		public BlockItemListContext blockItemList() {
			return getRuleContext(BlockItemListContext.class,0);
		}
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_compoundStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(LeftBrace);
			setState(428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Do) | (1L << If) | (1L << Int) | (1L << Long) | (1L << Void) | (1L << While) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus))) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (Not - 69)) | (1L << (Semi - 69)) | (1L << (Identifier - 69)) | (1L << (Constant - 69)) | (1L << (StringLiteral - 69)))) != 0)) {
				{
				setState(427);
				blockItemList();
				}
			}

			setState(430);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockItemListContext extends ParserRuleContext {
		public List<BlockItemContext> blockItem() {
			return getRuleContexts(BlockItemContext.class);
		}
		public BlockItemContext blockItem(int i) {
			return getRuleContext(BlockItemContext.class,i);
		}
		public BlockItemListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockItemList; }
	}

	public final BlockItemListContext blockItemList() throws RecognitionException {
		BlockItemListContext _localctx = new BlockItemListContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_blockItemList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(432);
				blockItem();
				}
				}
				setState(435); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Do) | (1L << If) | (1L << Int) | (1L << Long) | (1L << Void) | (1L << While) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus))) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (Not - 69)) | (1L << (Semi - 69)) | (1L << (Identifier - 69)) | (1L << (Constant - 69)) | (1L << (StringLiteral - 69)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockItemContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public BlockItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockItem; }
	}

	public final BlockItemContext blockItem() throws RecognitionException {
		BlockItemContext _localctx = new BlockItemContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_blockItem);
		try {
			setState(439);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Do:
			case If:
			case While:
			case LeftParen:
			case LeftBrace:
			case Plus:
			case Minus:
			case Not:
			case Semi:
			case Identifier:
			case Constant:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(437);
				statement();
				}
				break;
			case Char:
			case Const:
			case Int:
			case Long:
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(438);
				declaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(CParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_expressionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
				{
				setState(441);
				expression();
				}
			}

			setState(444);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(CParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(CParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(CParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(CParser.Else, 0); }
		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectionStatement; }
	}

	public final SelectionStatementContext selectionStatement() throws RecognitionException {
		SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_selectionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			match(If);
			setState(447);
			match(LeftParen);
			setState(448);
			expression();
			setState(449);
			match(RightParen);
			setState(450);
			statement();
			setState(453);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(451);
				match(Else);
				setState(452);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(CParser.While, 0); }
		public TerminalNode LeftParen() { return getToken(CParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(CParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Do() { return getToken(CParser.Do, 0); }
		public TerminalNode Semi() { return getToken(CParser.Semi, 0); }
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_iterationStatement);
		try {
			setState(469);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case While:
				enterOuterAlt(_localctx, 1);
				{
				setState(455);
				match(While);
				setState(456);
				match(LeftParen);
				setState(457);
				expression();
				setState(458);
				match(RightParen);
				setState(459);
				statement();
				}
				break;
			case Do:
				enterOuterAlt(_localctx, 2);
				{
				setState(461);
				match(Do);
				setState(462);
				statement();
				setState(463);
				match(While);
				setState(464);
				match(LeftParen);
				setState(465);
				expression();
				setState(466);
				match(RightParen);
				setState(467);
				match(Semi);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CParser.EOF, 0); }
		public TranslationUnitContext translationUnit() {
			return getRuleContext(TranslationUnitContext.class,0);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0) || _la==Semi) {
				{
				setState(471);
				translationUnit();
				}
			}

			setState(474);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TranslationUnitContext extends ParserRuleContext {
		public List<ExternalDeclarationContext> externalDeclaration() {
			return getRuleContexts(ExternalDeclarationContext.class);
		}
		public ExternalDeclarationContext externalDeclaration(int i) {
			return getRuleContext(ExternalDeclarationContext.class,i);
		}
		public TranslationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_translationUnit; }
	}

	public final TranslationUnitContext translationUnit() throws RecognitionException {
		TranslationUnitContext _localctx = new TranslationUnitContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_translationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(477); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(476);
				externalDeclaration();
				}
				}
				setState(479); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0) || _la==Semi );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExternalDeclarationContext extends ParserRuleContext {
		public FunctionDefinitionContext functionDefinition() {
			return getRuleContext(FunctionDefinitionContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public TerminalNode Semi() { return getToken(CParser.Semi, 0); }
		public ExternalDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externalDeclaration; }
	}

	public final ExternalDeclarationContext externalDeclaration() throws RecognitionException {
		ExternalDeclarationContext _localctx = new ExternalDeclarationContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_externalDeclaration);
		try {
			setState(484);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(481);
				functionDefinition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(482);
				declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(483);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public DeclarationSpecifiersContext declarationSpecifiers() {
			return getRuleContext(DeclarationSpecifiersContext.class,0);
		}
		public DirectDeclaratorContext directDeclarator() {
			return getRuleContext(DirectDeclaratorContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(CParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(CParser.RightParen, 0); }
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class,0);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			declarationSpecifiers();
			setState(487);
			directDeclarator(0);
			setState(488);
			match(LeftParen);
			setState(490);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0)) {
				{
				setState(489);
				parameterTypeList();
				}
			}

			setState(492);
			match(RightParen);
			setState(493);
			compoundStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationListContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public DeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationList; }
	}

	public final DeclarationListContext declarationList() throws RecognitionException {
		DeclarationListContext _localctx = new DeclarationListContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_declarationList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(495);
				declaration();
				}
				}
				setState(498); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 23:
			return directDeclarator_sempred((DirectDeclaratorContext)_localctx, predIndex);
		case 31:
			return directAbstractDeclarator_sempred((DirectAbstractDeclaratorContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean directDeclarator_sempred(DirectDeclaratorContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean directAbstractDeclarator_sempred(DirectAbstractDeclaratorContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3j\u01f7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\3\2\3\2\3\2\6\2l\n\2\r\2\16\2m\3\2\3\2\3\2\3\2\5\2t\n\2\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\5\3}\n\3\3\3\7\3\u0080\n\3\f\3\16\3\u0083\13\3\3\4"+
		"\3\4\3\4\7\4\u0088\n\4\f\4\16\4\u008b\13\4\3\5\3\5\3\5\3\5\5\5\u0091\n"+
		"\5\3\6\3\6\3\7\3\7\3\7\7\7\u0098\n\7\f\7\16\7\u009b\13\7\3\b\3\b\3\b\7"+
		"\b\u00a0\n\b\f\b\16\b\u00a3\13\b\3\t\3\t\3\t\7\t\u00a8\n\t\f\t\16\t\u00ab"+
		"\13\t\3\n\3\n\3\n\7\n\u00b0\n\n\f\n\16\n\u00b3\13\n\3\13\3\13\3\13\7\13"+
		"\u00b8\n\13\f\13\16\13\u00bb\13\13\3\f\3\f\3\f\7\f\u00c0\n\f\f\f\16\f"+
		"\u00c3\13\f\3\r\3\r\3\r\3\r\3\r\5\r\u00ca\n\r\3\16\3\16\3\16\7\16\u00cf"+
		"\n\16\f\16\16\16\u00d2\13\16\3\17\3\17\3\20\3\20\5\20\u00d8\n\20\3\20"+
		"\3\20\3\21\6\21\u00dd\n\21\r\21\16\21\u00de\3\22\6\22\u00e2\n\22\r\22"+
		"\16\22\u00e3\3\23\3\23\5\23\u00e8\n\23\3\24\3\24\3\24\7\24\u00ed\n\24"+
		"\f\24\16\24\u00f0\13\24\3\25\3\25\3\25\5\25\u00f5\n\25\3\26\3\26\3\27"+
		"\3\27\3\30\5\30\u00fc\n\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\5\31"+
		"\u0106\n\31\3\31\3\31\3\31\5\31\u010b\n\31\3\31\3\31\3\31\3\31\5\31\u0111"+
		"\n\31\3\31\7\31\u0114\n\31\f\31\16\31\u0117\13\31\3\32\3\32\5\32\u011b"+
		"\n\32\6\32\u011d\n\32\r\32\16\32\u011e\3\33\6\33\u0122\n\33\r\33\16\33"+
		"\u0123\3\34\3\34\3\35\3\35\3\35\7\35\u012b\n\35\f\35\16\35\u012e\13\35"+
		"\3\36\3\36\3\36\3\36\3\36\5\36\u0135\n\36\5\36\u0137\n\36\3\37\3\37\3"+
		"\37\7\37\u013c\n\37\f\37\16\37\u013f\13\37\3 \3 \5 \u0143\n \3 \5 \u0146"+
		"\n \3!\3!\3!\3!\3!\3!\3!\5!\u014f\n!\3!\3!\3!\5!\u0154\n!\3!\5!\u0157"+
		"\n!\3!\3!\3!\5!\u015c\n!\3!\3!\3!\3!\5!\u0162\n!\3!\7!\u0165\n!\f!\16"+
		"!\u0168\13!\3\"\3\"\3\"\3\"\5\"\u016e\n\"\3\"\3\"\5\"\u0172\n\"\3#\5#"+
		"\u0175\n#\3#\3#\3#\5#\u017a\n#\3#\7#\u017d\n#\f#\16#\u0180\13#\3$\3$\3"+
		"$\3%\6%\u0186\n%\r%\16%\u0187\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\6\'\u0193"+
		"\n\'\r\'\16\'\u0194\3\'\3\'\3\'\3(\3(\3(\3(\5(\u019e\n(\3)\3)\3)\3)\3"+
		")\3)\3)\3)\3)\3)\3)\5)\u01ab\n)\3*\3*\5*\u01af\n*\3*\3*\3+\6+\u01b4\n"+
		"+\r+\16+\u01b5\3,\3,\5,\u01ba\n,\3-\5-\u01bd\n-\3-\3-\3.\3.\3.\3.\3.\3"+
		".\3.\5.\u01c8\n.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\5/\u01d8\n"+
		"/\3\60\5\60\u01db\n\60\3\60\3\60\3\61\6\61\u01e0\n\61\r\61\16\61\u01e1"+
		"\3\62\3\62\3\62\5\62\u01e7\n\62\3\63\3\63\3\63\3\63\5\63\u01ed\n\63\3"+
		"\63\3\63\3\63\3\64\6\64\u01f3\n\64\r\64\16\64\u01f4\3\64\2\4\60@\65\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL"+
		"NPRTVXZ\\^`bdf\2\t\5\2;;==GG\3\2?A\4\2;;==\3\2\658\3\2XY\5\2\6\6\24\25"+
		"\"\"\4\2??FF\2\u0209\2s\3\2\2\2\4u\3\2\2\2\6\u0084\3\2\2\2\b\u0090\3\2"+
		"\2\2\n\u0092\3\2\2\2\f\u0094\3\2\2\2\16\u009c\3\2\2\2\20\u00a4\3\2\2\2"+
		"\22\u00ac\3\2\2\2\24\u00b4\3\2\2\2\26\u00bc\3\2\2\2\30\u00c9\3\2\2\2\32"+
		"\u00cb\3\2\2\2\34\u00d3\3\2\2\2\36\u00d5\3\2\2\2 \u00dc\3\2\2\2\"\u00e1"+
		"\3\2\2\2$\u00e7\3\2\2\2&\u00e9\3\2\2\2(\u00f1\3\2\2\2*\u00f6\3\2\2\2,"+
		"\u00f8\3\2\2\2.\u00fb\3\2\2\2\60\u0105\3\2\2\2\62\u011c\3\2\2\2\64\u0121"+
		"\3\2\2\2\66\u0125\3\2\2\28\u0127\3\2\2\2:\u0136\3\2\2\2<\u0138\3\2\2\2"+
		">\u0145\3\2\2\2@\u0156\3\2\2\2B\u0171\3\2\2\2D\u0174\3\2\2\2F\u0181\3"+
		"\2\2\2H\u0185\3\2\2\2J\u0189\3\2\2\2L\u018d\3\2\2\2N\u019d\3\2\2\2P\u01aa"+
		"\3\2\2\2R\u01ac\3\2\2\2T\u01b3\3\2\2\2V\u01b9\3\2\2\2X\u01bc\3\2\2\2Z"+
		"\u01c0\3\2\2\2\\\u01d7\3\2\2\2^\u01da\3\2\2\2`\u01df\3\2\2\2b\u01e6\3"+
		"\2\2\2d\u01e8\3\2\2\2f\u01f2\3\2\2\2ht\7]\2\2it\7^\2\2jl\7`\2\2kj\3\2"+
		"\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2nt\3\2\2\2op\7/\2\2pq\5\32\16\2qr\7"+
		"\60\2\2rt\3\2\2\2sh\3\2\2\2si\3\2\2\2sk\3\2\2\2so\3\2\2\2t\3\3\2\2\2u"+
		"\u0081\5\2\2\2vw\7\61\2\2wx\5\32\16\2xy\7\62\2\2y\u0080\3\2\2\2z|\7/\2"+
		"\2{}\5\6\4\2|{\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\u0080\7\60\2\2\177v\3\2\2"+
		"\2\177z\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2"+
		"\2\2\u0082\5\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0089\5\30\r\2\u0085\u0086"+
		"\7L\2\2\u0086\u0088\5\30\r\2\u0087\u0085\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\7\3\2\2\2\u008b\u0089\3\2\2\2"+
		"\u008c\u0091\5\4\3\2\u008d\u008e\5\n\6\2\u008e\u008f\5\b\5\2\u008f\u0091"+
		"\3\2\2\2\u0090\u008c\3\2\2\2\u0090\u008d\3\2\2\2\u0091\t\3\2\2\2\u0092"+
		"\u0093\t\2\2\2\u0093\13\3\2\2\2\u0094\u0099\5\b\5\2\u0095\u0096\t\3\2"+
		"\2\u0096\u0098\5\b\5\2\u0097\u0095\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097"+
		"\3\2\2\2\u0099\u009a\3\2\2\2\u009a\r\3\2\2\2\u009b\u0099\3\2\2\2\u009c"+
		"\u00a1\5\f\7\2\u009d\u009e\t\4\2\2\u009e\u00a0\5\f\7\2\u009f\u009d\3\2"+
		"\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\17\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a9\5\16\b\2\u00a5\u00a6\t\5\2"+
		"\2\u00a6\u00a8\5\16\b\2\u00a7\u00a5\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\21\3\2\2\2\u00ab\u00a9\3\2\2"+
		"\2\u00ac\u00b1\5\20\t\2\u00ad\u00ae\t\6\2\2\u00ae\u00b0\5\20\t\2\u00af"+
		"\u00ad\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\23\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b9\5\22\n\2\u00b5\u00b6"+
		"\7D\2\2\u00b6\u00b8\5\22\n\2\u00b7\u00b5\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\25\3\2\2\2\u00bb\u00b9\3\2\2"+
		"\2\u00bc\u00c1\5\24\13\2\u00bd\u00be\7E\2\2\u00be\u00c0\5\24\13\2\u00bf"+
		"\u00bd\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2"+
		"\2\2\u00c2\27\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00ca\5\26\f\2\u00c5\u00c6"+
		"\5\b\5\2\u00c6\u00c7\7M\2\2\u00c7\u00c8\5\30\r\2\u00c8\u00ca\3\2\2\2\u00c9"+
		"\u00c4\3\2\2\2\u00c9\u00c5\3\2\2\2\u00ca\31\3\2\2\2\u00cb\u00d0\5\30\r"+
		"\2\u00cc\u00cd\7L\2\2\u00cd\u00cf\5\30\r\2\u00ce\u00cc\3\2\2\2\u00cf\u00d2"+
		"\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\33\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d3\u00d4\5\26\f\2\u00d4\35\3\2\2\2\u00d5\u00d7\5 \21"+
		"\2\u00d6\u00d8\5&\24\2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9"+
		"\3\2\2\2\u00d9\u00da\7K\2\2\u00da\37\3\2\2\2\u00db\u00dd\5$\23\2\u00dc"+
		"\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2"+
		"\2\2\u00df!\3\2\2\2\u00e0\u00e2\5$\23\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3"+
		"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4#\3\2\2\2\u00e5"+
		"\u00e8\5*\26\2\u00e6\u00e8\5,\27\2\u00e7\u00e5\3\2\2\2\u00e7\u00e6\3\2"+
		"\2\2\u00e8%\3\2\2\2\u00e9\u00ee\5(\25\2\u00ea\u00eb\7L\2\2\u00eb\u00ed"+
		"\5(\25\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\'\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f4\5.\30\2"+
		"\u00f2\u00f3\7M\2\2\u00f3\u00f5\5B\"\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5)\3\2\2\2\u00f6\u00f7\t\7\2\2\u00f7+\3\2\2\2\u00f8\u00f9"+
		"\7\7\2\2\u00f9-\3\2\2\2\u00fa\u00fc\5\62\32\2\u00fb\u00fa\3\2\2\2\u00fb"+
		"\u00fc\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\5\60\31\2\u00fe/\3\2\2"+
		"\2\u00ff\u0100\b\31\1\2\u0100\u0106\7]\2\2\u0101\u0102\7/\2\2\u0102\u0103"+
		"\5.\30\2\u0103\u0104\7\60\2\2\u0104\u0106\3\2\2\2\u0105\u00ff\3\2\2\2"+
		"\u0105\u0101\3\2\2\2\u0106\u0115\3\2\2\2\u0107\u0108\f\4\2\2\u0108\u010a"+
		"\7\61\2\2\u0109\u010b\5\30\r\2\u010a\u0109\3\2\2\2\u010a\u010b\3\2\2\2"+
		"\u010b\u010c\3\2\2\2\u010c\u0114\7\62\2\2\u010d\u010e\f\3\2\2\u010e\u0110"+
		"\7/\2\2\u010f\u0111\5\66\34\2\u0110\u010f\3\2\2\2\u0110\u0111\3\2\2\2"+
		"\u0111\u0112\3\2\2\2\u0112\u0114\7\60\2\2\u0113\u0107\3\2\2\2\u0113\u010d"+
		"\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"\61\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u011a\t\b\2\2\u0119\u011b\5\64\33"+
		"\2\u011a\u0119\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011d\3\2\2\2\u011c\u0118"+
		"\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f"+
		"\63\3\2\2\2\u0120\u0122\5,\27\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2"+
		"\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\65\3\2\2\2\u0125\u0126"+
		"\58\35\2\u0126\67\3\2\2\2\u0127\u012c\5:\36\2\u0128\u0129\7L\2\2\u0129"+
		"\u012b\5:\36\2\u012a\u0128\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3\2"+
		"\2\2\u012c\u012d\3\2\2\2\u012d9\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130"+
		"\5 \21\2\u0130\u0131\5.\30\2\u0131\u0137\3\2\2\2\u0132\u0134\5\"\22\2"+
		"\u0133\u0135\5> \2\u0134\u0133\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0137"+
		"\3\2\2\2\u0136\u012f\3\2\2\2\u0136\u0132\3\2\2\2\u0137;\3\2\2\2\u0138"+
		"\u013d\7]\2\2\u0139\u013a\7L\2\2\u013a\u013c\7]\2\2\u013b\u0139\3\2\2"+
		"\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e="+
		"\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0146\5\62\32\2\u0141\u0143\5\62\32"+
		"\2\u0142\u0141\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0146"+
		"\5@!\2\u0145\u0140\3\2\2\2\u0145\u0142\3\2\2\2\u0146?\3\2\2\2\u0147\u0148"+
		"\b!\1\2\u0148\u0149\7/\2\2\u0149\u014a\5> \2\u014a\u014b\7\60\2\2\u014b"+
		"\u0157\3\2\2\2\u014c\u014e\7\61\2\2\u014d\u014f\5\30\r\2\u014e\u014d\3"+
		"\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0157\7\62\2\2\u0151"+
		"\u0153\7/\2\2\u0152\u0154\5\66\34\2\u0153\u0152\3\2\2\2\u0153\u0154\3"+
		"\2\2\2\u0154\u0155\3\2\2\2\u0155\u0157\7\60\2\2\u0156\u0147\3\2\2\2\u0156"+
		"\u014c\3\2\2\2\u0156\u0151\3\2\2\2\u0157\u0166\3\2\2\2\u0158\u0159\f\4"+
		"\2\2\u0159\u015b\7\61\2\2\u015a\u015c\5\30\r\2\u015b\u015a\3\2\2\2\u015b"+
		"\u015c\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u0165\7\62\2\2\u015e\u015f\f"+
		"\3\2\2\u015f\u0161\7/\2\2\u0160\u0162\5\66\34\2\u0161\u0160\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0165\7\60\2\2\u0164\u0158\3"+
		"\2\2\2\u0164\u015e\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0164\3\2\2\2\u0166"+
		"\u0167\3\2\2\2\u0167A\3\2\2\2\u0168\u0166\3\2\2\2\u0169\u0172\5\30\r\2"+
		"\u016a\u016b\7\63\2\2\u016b\u016d\5D#\2\u016c\u016e\7L\2\2\u016d\u016c"+
		"\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\7\64\2\2"+
		"\u0170\u0172\3\2\2\2\u0171\u0169\3\2\2\2\u0171\u016a\3\2\2\2\u0172C\3"+
		"\2\2\2\u0173\u0175\5F$\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175"+
		"\u0176\3\2\2\2\u0176\u017e\5B\"\2\u0177\u0179\7L\2\2\u0178\u017a\5F$\2"+
		"\u0179\u0178\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017d"+
		"\5B\"\2\u017c\u0177\3\2\2\2\u017d\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e"+
		"\u017f\3\2\2\2\u017fE\3\2\2\2\u0180\u017e\3\2\2\2\u0181\u0182\5H%\2\u0182"+
		"\u0183\7M\2\2\u0183G\3\2\2\2\u0184\u0186\5J&\2\u0185\u0184\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188I\3\2\2\2"+
		"\u0189\u018a\7\61\2\2\u018a\u018b\5\34\17\2\u018b\u018c\7\62\2\2\u018c"+
		"K\3\2\2\2\u018d\u018e\7-\2\2\u018e\u018f\7/\2\2\u018f\u0190\5\34\17\2"+
		"\u0190\u0192\7L\2\2\u0191\u0193\7`\2\2\u0192\u0191\3\2\2\2\u0193\u0194"+
		"\3\2\2\2\u0194\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0196\3\2\2\2\u0196"+
		"\u0197\7\60\2\2\u0197\u0198\7K\2\2\u0198M\3\2\2\2\u0199\u019e\5R*\2\u019a"+
		"\u019e\5X-\2\u019b\u019e\5Z.\2\u019c\u019e\5\\/\2\u019d\u0199\3\2\2\2"+
		"\u019d\u019a\3\2\2\2\u019d\u019b\3\2\2\2\u019d\u019c\3\2\2\2\u019eO\3"+
		"\2\2\2\u019f\u01a0\7]\2\2\u01a0\u01a1\7J\2\2\u01a1\u01ab\5N(\2\u01a2\u01a3"+
		"\7\5\2\2\u01a3\u01a4\5\34\17\2\u01a4\u01a5\7J\2\2\u01a5\u01a6\5N(\2\u01a6"+
		"\u01ab\3\2\2\2\u01a7\u01a8\7\t\2\2\u01a8\u01a9\7J\2\2\u01a9\u01ab\5N("+
		"\2\u01aa\u019f\3\2\2\2\u01aa\u01a2\3\2\2\2\u01aa\u01a7\3\2\2\2\u01abQ"+
		"\3\2\2\2\u01ac\u01ae\7\63\2\2\u01ad\u01af\5T+\2\u01ae\u01ad\3\2\2\2\u01ae"+
		"\u01af\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01b1\7\64\2\2\u01b1S\3\2\2\2"+
		"\u01b2\u01b4\5V,\2\u01b3\u01b2\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b3"+
		"\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6U\3\2\2\2\u01b7\u01ba\5N(\2\u01b8\u01ba"+
		"\5\36\20\2\u01b9\u01b7\3\2\2\2\u01b9\u01b8\3\2\2\2\u01baW\3\2\2\2\u01bb"+
		"\u01bd\5\32\16\2\u01bc\u01bb\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01be\3"+
		"\2\2\2\u01be\u01bf\7K\2\2\u01bfY\3\2\2\2\u01c0\u01c1\7\22\2\2\u01c1\u01c2"+
		"\7/\2\2\u01c2\u01c3\5\32\16\2\u01c3\u01c4\7\60\2\2\u01c4\u01c7\5N(\2\u01c5"+
		"\u01c6\7\f\2\2\u01c6\u01c8\5N(\2\u01c7\u01c5\3\2\2\2\u01c7\u01c8\3\2\2"+
		"\2\u01c8[\3\2\2\2\u01c9\u01ca\7$\2\2\u01ca\u01cb\7/\2\2\u01cb\u01cc\5"+
		"\32\16\2\u01cc\u01cd\7\60\2\2\u01cd\u01ce\5N(\2\u01ce\u01d8\3\2\2\2\u01cf"+
		"\u01d0\7\n\2\2\u01d0\u01d1\5N(\2\u01d1\u01d2\7$\2\2\u01d2\u01d3\7/\2\2"+
		"\u01d3\u01d4\5\32\16\2\u01d4\u01d5\7\60\2\2\u01d5\u01d6\7K\2\2\u01d6\u01d8"+
		"\3\2\2\2\u01d7\u01c9\3\2\2\2\u01d7\u01cf\3\2\2\2\u01d8]\3\2\2\2\u01d9"+
		"\u01db\5`\61\2\u01da\u01d9\3\2\2\2\u01da\u01db\3\2\2\2\u01db\u01dc\3\2"+
		"\2\2\u01dc\u01dd\7\2\2\3\u01dd_\3\2\2\2\u01de\u01e0\5b\62\2\u01df\u01de"+
		"\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2"+
		"a\3\2\2\2\u01e3\u01e7\5d\63\2\u01e4\u01e7\5\36\20\2\u01e5\u01e7\7K\2\2"+
		"\u01e6\u01e3\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e6\u01e5\3\2\2\2\u01e7c\3"+
		"\2\2\2\u01e8\u01e9\5 \21\2\u01e9\u01ea\5\60\31\2\u01ea\u01ec\7/\2\2\u01eb"+
		"\u01ed\5\66\34\2\u01ec\u01eb\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ee\3"+
		"\2\2\2\u01ee\u01ef\7\60\2\2\u01ef\u01f0\5R*\2\u01f0e\3\2\2\2\u01f1\u01f3"+
		"\5\36\20\2\u01f2\u01f1\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f2\3\2\2\2"+
		"\u01f4\u01f5\3\2\2\2\u01f5g\3\2\2\2Ams|\177\u0081\u0089\u0090\u0099\u00a1"+
		"\u00a9\u00b1\u00b9\u00c1\u00c9\u00d0\u00d7\u00de\u00e3\u00e7\u00ee\u00f4"+
		"\u00fb\u0105\u010a\u0110\u0113\u0115\u011a\u011e\u0123\u012c\u0134\u0136"+
		"\u013d\u0142\u0145\u014e\u0153\u0156\u015b\u0161\u0164\u0166\u016d\u0171"+
		"\u0174\u0179\u017e\u0187\u0194\u019d\u01aa\u01ae\u01b5\u01b9\u01bc\u01c7"+
		"\u01d7\u01da\u01e1\u01e6\u01ec\u01f4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}