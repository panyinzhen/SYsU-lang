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
		RULE_iterationStatement = 45, RULE_jumpStatement = 46, RULE_compilationUnit = 47, 
		RULE_translationUnit = 48, RULE_externalDeclaration = 49, RULE_functionDefinition = 50, 
		RULE_declarationList = 51;
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
			"expressionStatement", "selectionStatement", "iterationStatement", "jumpStatement", 
			"compilationUnit", "translationUnit", "externalDeclaration", "functionDefinition", 
			"declarationList"
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
		try {
			int _alt;
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				match(Identifier);
				}
				break;
			case Constant:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(Constant);
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(107); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(106);
						match(StringLiteral);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(109); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case LeftParen:
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				match(LeftParen);
				setState(112);
				expression();
				setState(113);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(117);
			primaryExpression();
			}
			setState(129);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(127);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case LeftBracket:
						{
						setState(118);
						match(LeftBracket);
						setState(119);
						expression();
						setState(120);
						match(RightBracket);
						}
						break;
					case LeftParen:
						{
						setState(122);
						match(LeftParen);
						setState(124);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
							{
							setState(123);
							argumentExpressionList();
							}
						}

						setState(126);
						match(RightParen);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(131);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
			setState(132);
			assignmentExpression();
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(133);
				match(Comma);
				setState(134);
				assignmentExpression();
				}
				}
				setState(139);
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
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParen:
			case Identifier:
			case Constant:
			case StringLiteral:
				{
				setState(140);
				postfixExpression();
				}
				break;
			case Plus:
			case Minus:
			case Not:
				{
				setState(141);
				unaryOperator();
				setState(142);
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
			setState(146);
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
			setState(148);
			unaryExpression();
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Star) | (1L << Div) | (1L << Mod))) != 0)) {
				{
				{
				setState(149);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Star) | (1L << Div) | (1L << Mod))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(150);
				unaryExpression();
				}
				}
				setState(155);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			multiplicativeExpression();
			setState(161);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(157);
					_la = _input.LA(1);
					if ( !(_la==Plus || _la==Minus) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(158);
					multiplicativeExpression();
					}
					} 
				}
				setState(163);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
			setState(164);
			additiveExpression();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) {
				{
				{
				setState(165);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(166);
				additiveExpression();
				}
				}
				setState(171);
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
			setState(172);
			relationalExpression();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Equal || _la==NotEqual) {
				{
				{
				setState(173);
				_la = _input.LA(1);
				if ( !(_la==Equal || _la==NotEqual) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(174);
				relationalExpression();
				}
				}
				setState(179);
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
			setState(180);
			equalityExpression();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AndAnd) {
				{
				{
				setState(181);
				match(AndAnd);
				setState(182);
				equalityExpression();
				}
				}
				setState(187);
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
			setState(188);
			logicalAndExpression();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OrOr) {
				{
				{
				setState(189);
				match(OrOr);
				setState(190);
				logicalAndExpression();
				}
				}
				setState(195);
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
			setState(201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				logicalOrExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(197);
				unaryExpression();
				setState(198);
				match(Assign);
				setState(199);
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
			setState(203);
			assignmentExpression();
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(204);
				match(Comma);
				setState(205);
				assignmentExpression();
				}
				}
				setState(210);
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
			setState(211);
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
			setState(213);
			declarationSpecifiers();
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Star - 45)) | (1L << (Caret - 45)) | (1L << (Identifier - 45)))) != 0)) {
				{
				setState(214);
				initDeclaratorList();
				}
			}

			setState(217);
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
			setState(220); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(219);
				declarationSpecifier();
				}
				}
				setState(222); 
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
			setState(225); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(224);
				declarationSpecifier();
				}
				}
				setState(227); 
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
			setState(231);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Char:
			case Int:
			case Long:
			case Void:
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				typeSpecifier();
				}
				break;
			case Const:
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
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
			setState(233);
			initDeclarator();
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(234);
				match(Comma);
				setState(235);
				initDeclarator();
				}
				}
				setState(240);
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
			setState(241);
			declarator();
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(242);
				match(Assign);
				setState(243);
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
			setState(246);
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
			setState(248);
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
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Star || _la==Caret) {
				{
				setState(250);
				pointer();
				}
			}

			setState(253);
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
			setState(261);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				{
				setState(256);
				match(Identifier);
				}
				break;
			case LeftParen:
				{
				setState(257);
				match(LeftParen);
				setState(258);
				declarator();
				setState(259);
				match(RightParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(277);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(275);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
						setState(263);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(264);
						match(LeftBracket);
						setState(266);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
							{
							setState(265);
							assignmentExpression();
							}
						}

						setState(268);
						match(RightBracket);
						}
						break;
					case 2:
						{
						_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
						setState(269);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(270);
						match(LeftParen);
						setState(272);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0)) {
							{
							setState(271);
							parameterTypeList();
							}
						}

						setState(274);
						match(RightParen);
						}
						break;
					}
					} 
				}
				setState(279);
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
			setState(284); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(280);
				_la = _input.LA(1);
				if ( !(_la==Star || _la==Caret) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Const) {
					{
					setState(281);
					typeQualifierList();
					}
				}

				}
				}
				setState(286); 
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
			setState(289); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(288);
				typeQualifier();
				}
				}
				setState(291); 
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
			setState(293);
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
			setState(295);
			parameterDeclaration();
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(296);
				match(Comma);
				setState(297);
				parameterDeclaration();
				}
				}
				setState(302);
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
			setState(310);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				declarationSpecifiers();
				setState(304);
				declarator();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				declarationSpecifiers2();
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (LeftBracket - 45)) | (1L << (Star - 45)) | (1L << (Caret - 45)))) != 0)) {
					{
					setState(307);
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
			setState(312);
			match(Identifier);
			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(313);
				match(Comma);
				setState(314);
				match(Identifier);
				}
				}
				setState(319);
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
			setState(325);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				pointer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Star || _la==Caret) {
					{
					setState(321);
					pointer();
					}
				}

				setState(324);
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
			setState(342);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(328);
				match(LeftParen);
				setState(329);
				abstractDeclarator();
				setState(330);
				match(RightParen);
				}
				break;
			case 2:
				{
				setState(332);
				match(LeftBracket);
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
					{
					setState(333);
					assignmentExpression();
					}
				}

				setState(336);
				match(RightBracket);
				}
				break;
			case 3:
				{
				setState(337);
				match(LeftParen);
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0)) {
					{
					setState(338);
					parameterTypeList();
					}
				}

				setState(341);
				match(RightParen);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(358);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(356);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
					case 1:
						{
						_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
						setState(344);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(345);
						match(LeftBracket);
						setState(347);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
							{
							setState(346);
							assignmentExpression();
							}
						}

						setState(349);
						match(RightBracket);
						}
						break;
					case 2:
						{
						_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
						setState(350);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(351);
						match(LeftParen);
						setState(353);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0)) {
							{
							setState(352);
							parameterTypeList();
							}
						}

						setState(355);
						match(RightParen);
						}
						break;
					}
					} 
				}
				setState(360);
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
			setState(369);
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
				setState(361);
				assignmentExpression();
				}
				break;
			case LeftBrace:
				enterOuterAlt(_localctx, 2);
				{
				setState(362);
				match(LeftBrace);
				setState(363);
				initializerList();
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(364);
					match(Comma);
					}
				}

				setState(367);
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
			setState(372);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LeftBracket) {
				{
				setState(371);
				designation();
				}
			}

			setState(374);
			initializer();
			setState(382);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(375);
					match(Comma);
					setState(377);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LeftBracket) {
						{
						setState(376);
						designation();
						}
					}

					setState(379);
					initializer();
					}
					} 
				}
				setState(384);
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
			setState(385);
			designatorList();
			setState(386);
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
			setState(389); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(388);
				designator();
				}
				}
				setState(391); 
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
			setState(393);
			match(LeftBracket);
			setState(394);
			constantExpression();
			setState(395);
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
			setState(397);
			match(StaticAssert);
			setState(398);
			match(LeftParen);
			setState(399);
			constantExpression();
			setState(400);
			match(Comma);
			setState(402); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(401);
				match(StringLiteral);
				}
				}
				setState(404); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==StringLiteral );
			setState(406);
			match(RightParen);
			setState(407);
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
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
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
			setState(414);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftBrace:
				enterOuterAlt(_localctx, 1);
				{
				setState(409);
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
				setState(410);
				expressionStatement();
				}
				break;
			case If:
				enterOuterAlt(_localctx, 3);
				{
				setState(411);
				selectionStatement();
				}
				break;
			case Do:
			case While:
				enterOuterAlt(_localctx, 4);
				{
				setState(412);
				iterationStatement();
				}
				break;
			case Break:
			case Continue:
			case Return:
				enterOuterAlt(_localctx, 5);
				{
				setState(413);
				jumpStatement();
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
			setState(427);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(416);
				match(Identifier);
				setState(417);
				match(Colon);
				setState(418);
				statement();
				}
				break;
			case Case:
				enterOuterAlt(_localctx, 2);
				{
				setState(419);
				match(Case);
				setState(420);
				constantExpression();
				setState(421);
				match(Colon);
				setState(422);
				statement();
				}
				break;
			case Default:
				enterOuterAlt(_localctx, 3);
				{
				setState(424);
				match(Default);
				setState(425);
				match(Colon);
				setState(426);
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
			setState(429);
			match(LeftBrace);
			setState(431);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Break) | (1L << Char) | (1L << Const) | (1L << Continue) | (1L << Do) | (1L << If) | (1L << Int) | (1L << Long) | (1L << Return) | (1L << Void) | (1L << While) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus))) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (Not - 69)) | (1L << (Semi - 69)) | (1L << (Identifier - 69)) | (1L << (Constant - 69)) | (1L << (StringLiteral - 69)))) != 0)) {
				{
				setState(430);
				blockItemList();
				}
			}

			setState(433);
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
			setState(436); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(435);
				blockItem();
				}
				}
				setState(438); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Break) | (1L << Char) | (1L << Const) | (1L << Continue) | (1L << Do) | (1L << If) | (1L << Int) | (1L << Long) | (1L << Return) | (1L << Void) | (1L << While) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus))) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (Not - 69)) | (1L << (Semi - 69)) | (1L << (Identifier - 69)) | (1L << (Constant - 69)) | (1L << (StringLiteral - 69)))) != 0) );
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
			setState(442);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Break:
			case Continue:
			case Do:
			case If:
			case Return:
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
				setState(440);
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
				setState(441);
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
			setState(445);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (LeftParen - 45)) | (1L << (Plus - 45)) | (1L << (Minus - 45)) | (1L << (Not - 45)) | (1L << (Identifier - 45)) | (1L << (Constant - 45)) | (1L << (StringLiteral - 45)))) != 0)) {
				{
				setState(444);
				expression();
				}
			}

			setState(447);
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
			setState(449);
			match(If);
			setState(450);
			match(LeftParen);
			setState(451);
			expression();
			setState(452);
			match(RightParen);
			setState(453);
			statement();
			setState(456);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(454);
				match(Else);
				setState(455);
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
			setState(472);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case While:
				enterOuterAlt(_localctx, 1);
				{
				setState(458);
				match(While);
				setState(459);
				match(LeftParen);
				setState(460);
				expression();
				setState(461);
				match(RightParen);
				setState(462);
				statement();
				}
				break;
			case Do:
				enterOuterAlt(_localctx, 2);
				{
				setState(464);
				match(Do);
				setState(465);
				statement();
				setState(466);
				match(While);
				setState(467);
				match(LeftParen);
				setState(468);
				expression();
				setState(469);
				match(RightParen);
				setState(470);
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

	public static class JumpStatementContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(CParser.Continue, 0); }
		public TerminalNode Break() { return getToken(CParser.Break, 0); }
		public TerminalNode Return() { return getToken(CParser.Return, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_jumpStatement);
		int _la;
		try {
			setState(479);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Break:
			case Continue:
				enterOuterAlt(_localctx, 1);
				{
				setState(474);
				_la = _input.LA(1);
				if ( !(_la==Break || _la==Continue) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case Return:
				enterOuterAlt(_localctx, 2);
				{
				setState(475);
				match(Return);
				setState(477);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(476);
					expression();
					}
					break;
				}
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
		enterRule(_localctx, 94, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0) || _la==Semi) {
				{
				setState(481);
				translationUnit();
				}
			}

			setState(484);
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
		enterRule(_localctx, 96, RULE_translationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(486);
				externalDeclaration();
				}
				}
				setState(489); 
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
		enterRule(_localctx, 98, RULE_externalDeclaration);
		try {
			setState(494);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(491);
				functionDefinition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(492);
				declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(493);
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
		enterRule(_localctx, 100, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496);
			declarationSpecifiers();
			setState(497);
			directDeclarator(0);
			setState(498);
			match(LeftParen);
			setState(500);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Char) | (1L << Const) | (1L << Int) | (1L << Long) | (1L << Void))) != 0)) {
				{
				setState(499);
				parameterTypeList();
				}
			}

			setState(502);
			match(RightParen);
			setState(503);
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
		enterRule(_localctx, 102, RULE_declarationList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(505);
				declaration();
				}
				}
				setState(508); 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3j\u0201\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\3\2\3\2\3\2\6\2n\n\2\r\2\16\2o\3\2\3\2\3\2\3\2\5\2v\n\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\177\n\3\3\3\7\3\u0082\n\3\f\3\16\3\u0085"+
		"\13\3\3\4\3\4\3\4\7\4\u008a\n\4\f\4\16\4\u008d\13\4\3\5\3\5\3\5\3\5\5"+
		"\5\u0093\n\5\3\6\3\6\3\7\3\7\3\7\7\7\u009a\n\7\f\7\16\7\u009d\13\7\3\b"+
		"\3\b\3\b\7\b\u00a2\n\b\f\b\16\b\u00a5\13\b\3\t\3\t\3\t\7\t\u00aa\n\t\f"+
		"\t\16\t\u00ad\13\t\3\n\3\n\3\n\7\n\u00b2\n\n\f\n\16\n\u00b5\13\n\3\13"+
		"\3\13\3\13\7\13\u00ba\n\13\f\13\16\13\u00bd\13\13\3\f\3\f\3\f\7\f\u00c2"+
		"\n\f\f\f\16\f\u00c5\13\f\3\r\3\r\3\r\3\r\3\r\5\r\u00cc\n\r\3\16\3\16\3"+
		"\16\7\16\u00d1\n\16\f\16\16\16\u00d4\13\16\3\17\3\17\3\20\3\20\5\20\u00da"+
		"\n\20\3\20\3\20\3\21\6\21\u00df\n\21\r\21\16\21\u00e0\3\22\6\22\u00e4"+
		"\n\22\r\22\16\22\u00e5\3\23\3\23\5\23\u00ea\n\23\3\24\3\24\3\24\7\24\u00ef"+
		"\n\24\f\24\16\24\u00f2\13\24\3\25\3\25\3\25\5\25\u00f7\n\25\3\26\3\26"+
		"\3\27\3\27\3\30\5\30\u00fe\n\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\5\31\u0108\n\31\3\31\3\31\3\31\5\31\u010d\n\31\3\31\3\31\3\31\3\31\5"+
		"\31\u0113\n\31\3\31\7\31\u0116\n\31\f\31\16\31\u0119\13\31\3\32\3\32\5"+
		"\32\u011d\n\32\6\32\u011f\n\32\r\32\16\32\u0120\3\33\6\33\u0124\n\33\r"+
		"\33\16\33\u0125\3\34\3\34\3\35\3\35\3\35\7\35\u012d\n\35\f\35\16\35\u0130"+
		"\13\35\3\36\3\36\3\36\3\36\3\36\5\36\u0137\n\36\5\36\u0139\n\36\3\37\3"+
		"\37\3\37\7\37\u013e\n\37\f\37\16\37\u0141\13\37\3 \3 \5 \u0145\n \3 \5"+
		" \u0148\n \3!\3!\3!\3!\3!\3!\3!\5!\u0151\n!\3!\3!\3!\5!\u0156\n!\3!\5"+
		"!\u0159\n!\3!\3!\3!\5!\u015e\n!\3!\3!\3!\3!\5!\u0164\n!\3!\7!\u0167\n"+
		"!\f!\16!\u016a\13!\3\"\3\"\3\"\3\"\5\"\u0170\n\"\3\"\3\"\5\"\u0174\n\""+
		"\3#\5#\u0177\n#\3#\3#\3#\5#\u017c\n#\3#\7#\u017f\n#\f#\16#\u0182\13#\3"+
		"$\3$\3$\3%\6%\u0188\n%\r%\16%\u0189\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\6"+
		"\'\u0195\n\'\r\'\16\'\u0196\3\'\3\'\3\'\3(\3(\3(\3(\3(\5(\u01a1\n(\3)"+
		"\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u01ae\n)\3*\3*\5*\u01b2\n*\3*\3*\3+"+
		"\6+\u01b7\n+\r+\16+\u01b8\3,\3,\5,\u01bd\n,\3-\5-\u01c0\n-\3-\3-\3.\3"+
		".\3.\3.\3.\3.\3.\5.\u01cb\n.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3"+
		"/\5/\u01db\n/\3\60\3\60\3\60\5\60\u01e0\n\60\5\60\u01e2\n\60\3\61\5\61"+
		"\u01e5\n\61\3\61\3\61\3\62\6\62\u01ea\n\62\r\62\16\62\u01eb\3\63\3\63"+
		"\3\63\5\63\u01f1\n\63\3\64\3\64\3\64\3\64\5\64\u01f7\n\64\3\64\3\64\3"+
		"\64\3\65\6\65\u01fd\n\65\r\65\16\65\u01fe\3\65\2\4\60@\66\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^"+
		"`bdfh\2\n\5\2;;==GG\3\2?A\4\2;;==\3\2\658\3\2XY\5\2\6\6\24\25\"\"\4\2"+
		"??FF\4\2\4\4\b\b\2\u0215\2u\3\2\2\2\4w\3\2\2\2\6\u0086\3\2\2\2\b\u0092"+
		"\3\2\2\2\n\u0094\3\2\2\2\f\u0096\3\2\2\2\16\u009e\3\2\2\2\20\u00a6\3\2"+
		"\2\2\22\u00ae\3\2\2\2\24\u00b6\3\2\2\2\26\u00be\3\2\2\2\30\u00cb\3\2\2"+
		"\2\32\u00cd\3\2\2\2\34\u00d5\3\2\2\2\36\u00d7\3\2\2\2 \u00de\3\2\2\2\""+
		"\u00e3\3\2\2\2$\u00e9\3\2\2\2&\u00eb\3\2\2\2(\u00f3\3\2\2\2*\u00f8\3\2"+
		"\2\2,\u00fa\3\2\2\2.\u00fd\3\2\2\2\60\u0107\3\2\2\2\62\u011e\3\2\2\2\64"+
		"\u0123\3\2\2\2\66\u0127\3\2\2\28\u0129\3\2\2\2:\u0138\3\2\2\2<\u013a\3"+
		"\2\2\2>\u0147\3\2\2\2@\u0158\3\2\2\2B\u0173\3\2\2\2D\u0176\3\2\2\2F\u0183"+
		"\3\2\2\2H\u0187\3\2\2\2J\u018b\3\2\2\2L\u018f\3\2\2\2N\u01a0\3\2\2\2P"+
		"\u01ad\3\2\2\2R\u01af\3\2\2\2T\u01b6\3\2\2\2V\u01bc\3\2\2\2X\u01bf\3\2"+
		"\2\2Z\u01c3\3\2\2\2\\\u01da\3\2\2\2^\u01e1\3\2\2\2`\u01e4\3\2\2\2b\u01e9"+
		"\3\2\2\2d\u01f0\3\2\2\2f\u01f2\3\2\2\2h\u01fc\3\2\2\2jv\7]\2\2kv\7^\2"+
		"\2ln\7`\2\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2pv\3\2\2\2qr\7/\2\2"+
		"rs\5\32\16\2st\7\60\2\2tv\3\2\2\2uj\3\2\2\2uk\3\2\2\2um\3\2\2\2uq\3\2"+
		"\2\2v\3\3\2\2\2w\u0083\5\2\2\2xy\7\61\2\2yz\5\32\16\2z{\7\62\2\2{\u0082"+
		"\3\2\2\2|~\7/\2\2}\177\5\6\4\2~}\3\2\2\2~\177\3\2\2\2\177\u0080\3\2\2"+
		"\2\u0080\u0082\7\60\2\2\u0081x\3\2\2\2\u0081|\3\2\2\2\u0082\u0085\3\2"+
		"\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\5\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0086\u008b\5\30\r\2\u0087\u0088\7L\2\2\u0088\u008a\5\30\r\2"+
		"\u0089\u0087\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c"+
		"\3\2\2\2\u008c\7\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u0093\5\4\3\2\u008f"+
		"\u0090\5\n\6\2\u0090\u0091\5\b\5\2\u0091\u0093\3\2\2\2\u0092\u008e\3\2"+
		"\2\2\u0092\u008f\3\2\2\2\u0093\t\3\2\2\2\u0094\u0095\t\2\2\2\u0095\13"+
		"\3\2\2\2\u0096\u009b\5\b\5\2\u0097\u0098\t\3\2\2\u0098\u009a\5\b\5\2\u0099"+
		"\u0097\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\r\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u00a3\5\f\7\2\u009f\u00a0"+
		"\t\4\2\2\u00a0\u00a2\5\f\7\2\u00a1\u009f\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\17\3\2\2\2\u00a5\u00a3\3\2\2"+
		"\2\u00a6\u00ab\5\16\b\2\u00a7\u00a8\t\5\2\2\u00a8\u00aa\5\16\b\2\u00a9"+
		"\u00a7\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2"+
		"\2\2\u00ac\21\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00b3\5\20\t\2\u00af\u00b0"+
		"\t\6\2\2\u00b0\u00b2\5\20\t\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2"+
		"\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\23\3\2\2\2\u00b5\u00b3"+
		"\3\2\2\2\u00b6\u00bb\5\22\n\2\u00b7\u00b8\7D\2\2\u00b8\u00ba\5\22\n\2"+
		"\u00b9\u00b7\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc"+
		"\3\2\2\2\u00bc\25\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00c3\5\24\13\2\u00bf"+
		"\u00c0\7E\2\2\u00c0\u00c2\5\24\13\2\u00c1\u00bf\3\2\2\2\u00c2\u00c5\3"+
		"\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\27\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c6\u00cc\5\26\f\2\u00c7\u00c8\5\b\5\2\u00c8\u00c9\7"+
		"M\2\2\u00c9\u00ca\5\30\r\2\u00ca\u00cc\3\2\2\2\u00cb\u00c6\3\2\2\2\u00cb"+
		"\u00c7\3\2\2\2\u00cc\31\3\2\2\2\u00cd\u00d2\5\30\r\2\u00ce\u00cf\7L\2"+
		"\2\u00cf\u00d1\5\30\r\2\u00d0\u00ce\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\33\3\2\2\2\u00d4\u00d2\3\2\2"+
		"\2\u00d5\u00d6\5\26\f\2\u00d6\35\3\2\2\2\u00d7\u00d9\5 \21\2\u00d8\u00da"+
		"\5&\24\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2\2\2\u00db"+
		"\u00dc\7K\2\2\u00dc\37\3\2\2\2\u00dd\u00df\5$\23\2\u00de\u00dd\3\2\2\2"+
		"\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1!\3"+
		"\2\2\2\u00e2\u00e4\5$\23\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6#\3\2\2\2\u00e7\u00ea\5*\26\2"+
		"\u00e8\u00ea\5,\27\2\u00e9\u00e7\3\2\2\2\u00e9\u00e8\3\2\2\2\u00ea%\3"+
		"\2\2\2\u00eb\u00f0\5(\25\2\u00ec\u00ed\7L\2\2\u00ed\u00ef\5(\25\2\u00ee"+
		"\u00ec\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f1\'\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f6\5.\30\2\u00f4\u00f5"+
		"\7M\2\2\u00f5\u00f7\5B\"\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		")\3\2\2\2\u00f8\u00f9\t\7\2\2\u00f9+\3\2\2\2\u00fa\u00fb\7\7\2\2\u00fb"+
		"-\3\2\2\2\u00fc\u00fe\5\62\32\2\u00fd\u00fc\3\2\2\2\u00fd\u00fe\3\2\2"+
		"\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\5\60\31\2\u0100/\3\2\2\2\u0101\u0102"+
		"\b\31\1\2\u0102\u0108\7]\2\2\u0103\u0104\7/\2\2\u0104\u0105\5.\30\2\u0105"+
		"\u0106\7\60\2\2\u0106\u0108\3\2\2\2\u0107\u0101\3\2\2\2\u0107\u0103\3"+
		"\2\2\2\u0108\u0117\3\2\2\2\u0109\u010a\f\4\2\2\u010a\u010c\7\61\2\2\u010b"+
		"\u010d\5\30\r\2\u010c\u010b\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\3"+
		"\2\2\2\u010e\u0116\7\62\2\2\u010f\u0110\f\3\2\2\u0110\u0112\7/\2\2\u0111"+
		"\u0113\5\66\34\2\u0112\u0111\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\3"+
		"\2\2\2\u0114\u0116\7\60\2\2\u0115\u0109\3\2\2\2\u0115\u010f\3\2\2\2\u0116"+
		"\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\61\3\2\2"+
		"\2\u0119\u0117\3\2\2\2\u011a\u011c\t\b\2\2\u011b\u011d\5\64\33\2\u011c"+
		"\u011b\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011f\3\2\2\2\u011e\u011a\3\2"+
		"\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\63\3\2\2\2\u0122\u0124\5,\27\2\u0123\u0122\3\2\2\2\u0124\u0125\3\2\2"+
		"\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\65\3\2\2\2\u0127\u0128"+
		"\58\35\2\u0128\67\3\2\2\2\u0129\u012e\5:\36\2\u012a\u012b\7L\2\2\u012b"+
		"\u012d\5:\36\2\u012c\u012a\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2"+
		"\2\2\u012e\u012f\3\2\2\2\u012f9\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0132"+
		"\5 \21\2\u0132\u0133\5.\30\2\u0133\u0139\3\2\2\2\u0134\u0136\5\"\22\2"+
		"\u0135\u0137\5> \2\u0136\u0135\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0139"+
		"\3\2\2\2\u0138\u0131\3\2\2\2\u0138\u0134\3\2\2\2\u0139;\3\2\2\2\u013a"+
		"\u013f\7]\2\2\u013b\u013c\7L\2\2\u013c\u013e\7]\2\2\u013d\u013b\3\2\2"+
		"\2\u013e\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140="+
		"\3\2\2\2\u0141\u013f\3\2\2\2\u0142\u0148\5\62\32\2\u0143\u0145\5\62\32"+
		"\2\u0144\u0143\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0148"+
		"\5@!\2\u0147\u0142\3\2\2\2\u0147\u0144\3\2\2\2\u0148?\3\2\2\2\u0149\u014a"+
		"\b!\1\2\u014a\u014b\7/\2\2\u014b\u014c\5> \2\u014c\u014d\7\60\2\2\u014d"+
		"\u0159\3\2\2\2\u014e\u0150\7\61\2\2\u014f\u0151\5\30\r\2\u0150\u014f\3"+
		"\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0159\7\62\2\2\u0153"+
		"\u0155\7/\2\2\u0154\u0156\5\66\34\2\u0155\u0154\3\2\2\2\u0155\u0156\3"+
		"\2\2\2\u0156\u0157\3\2\2\2\u0157\u0159\7\60\2\2\u0158\u0149\3\2\2\2\u0158"+
		"\u014e\3\2\2\2\u0158\u0153\3\2\2\2\u0159\u0168\3\2\2\2\u015a\u015b\f\4"+
		"\2\2\u015b\u015d\7\61\2\2\u015c\u015e\5\30\r\2\u015d\u015c\3\2\2\2\u015d"+
		"\u015e\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0167\7\62\2\2\u0160\u0161\f"+
		"\3\2\2\u0161\u0163\7/\2\2\u0162\u0164\5\66\34\2\u0163\u0162\3\2\2\2\u0163"+
		"\u0164\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0167\7\60\2\2\u0166\u015a\3"+
		"\2\2\2\u0166\u0160\3\2\2\2\u0167\u016a\3\2\2\2\u0168\u0166\3\2\2\2\u0168"+
		"\u0169\3\2\2\2\u0169A\3\2\2\2\u016a\u0168\3\2\2\2\u016b\u0174\5\30\r\2"+
		"\u016c\u016d\7\63\2\2\u016d\u016f\5D#\2\u016e\u0170\7L\2\2\u016f\u016e"+
		"\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0172\7\64\2\2"+
		"\u0172\u0174\3\2\2\2\u0173\u016b\3\2\2\2\u0173\u016c\3\2\2\2\u0174C\3"+
		"\2\2\2\u0175\u0177\5F$\2\u0176\u0175\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\u0178\3\2\2\2\u0178\u0180\5B\"\2\u0179\u017b\7L\2\2\u017a\u017c\5F$\2"+
		"\u017b\u017a\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017f"+
		"\5B\"\2\u017e\u0179\3\2\2\2\u017f\u0182\3\2\2\2\u0180\u017e\3\2\2\2\u0180"+
		"\u0181\3\2\2\2\u0181E\3\2\2\2\u0182\u0180\3\2\2\2\u0183\u0184\5H%\2\u0184"+
		"\u0185\7M\2\2\u0185G\3\2\2\2\u0186\u0188\5J&\2\u0187\u0186\3\2\2\2\u0188"+
		"\u0189\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018aI\3\2\2\2"+
		"\u018b\u018c\7\61\2\2\u018c\u018d\5\34\17\2\u018d\u018e\7\62\2\2\u018e"+
		"K\3\2\2\2\u018f\u0190\7-\2\2\u0190\u0191\7/\2\2\u0191\u0192\5\34\17\2"+
		"\u0192\u0194\7L\2\2\u0193\u0195\7`\2\2\u0194\u0193\3\2\2\2\u0195\u0196"+
		"\3\2\2\2\u0196\u0194\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0198\3\2\2\2\u0198"+
		"\u0199\7\60\2\2\u0199\u019a\7K\2\2\u019aM\3\2\2\2\u019b\u01a1\5R*\2\u019c"+
		"\u01a1\5X-\2\u019d\u01a1\5Z.\2\u019e\u01a1\5\\/\2\u019f\u01a1\5^\60\2"+
		"\u01a0\u019b\3\2\2\2\u01a0\u019c\3\2\2\2\u01a0\u019d\3\2\2\2\u01a0\u019e"+
		"\3\2\2\2\u01a0\u019f\3\2\2\2\u01a1O\3\2\2\2\u01a2\u01a3\7]\2\2\u01a3\u01a4"+
		"\7J\2\2\u01a4\u01ae\5N(\2\u01a5\u01a6\7\5\2\2\u01a6\u01a7\5\34\17\2\u01a7"+
		"\u01a8\7J\2\2\u01a8\u01a9\5N(\2\u01a9\u01ae\3\2\2\2\u01aa\u01ab\7\t\2"+
		"\2\u01ab\u01ac\7J\2\2\u01ac\u01ae\5N(\2\u01ad\u01a2\3\2\2\2\u01ad\u01a5"+
		"\3\2\2\2\u01ad\u01aa\3\2\2\2\u01aeQ\3\2\2\2\u01af\u01b1\7\63\2\2\u01b0"+
		"\u01b2\5T+\2\u01b1\u01b0\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b3\3\2\2"+
		"\2\u01b3\u01b4\7\64\2\2\u01b4S\3\2\2\2\u01b5\u01b7\5V,\2\u01b6\u01b5\3"+
		"\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9"+
		"U\3\2\2\2\u01ba\u01bd\5N(\2\u01bb\u01bd\5\36\20\2\u01bc\u01ba\3\2\2\2"+
		"\u01bc\u01bb\3\2\2\2\u01bdW\3\2\2\2\u01be\u01c0\5\32\16\2\u01bf\u01be"+
		"\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1\u01c2\7K\2\2\u01c2"+
		"Y\3\2\2\2\u01c3\u01c4\7\22\2\2\u01c4\u01c5\7/\2\2\u01c5\u01c6\5\32\16"+
		"\2\u01c6\u01c7\7\60\2\2\u01c7\u01ca\5N(\2\u01c8\u01c9\7\f\2\2\u01c9\u01cb"+
		"\5N(\2\u01ca\u01c8\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb[\3\2\2\2\u01cc\u01cd"+
		"\7$\2\2\u01cd\u01ce\7/\2\2\u01ce\u01cf\5\32\16\2\u01cf\u01d0\7\60\2\2"+
		"\u01d0\u01d1\5N(\2\u01d1\u01db\3\2\2\2\u01d2\u01d3\7\n\2\2\u01d3\u01d4"+
		"\5N(\2\u01d4\u01d5\7$\2\2\u01d5\u01d6\7/\2\2\u01d6\u01d7\5\32\16\2\u01d7"+
		"\u01d8\7\60\2\2\u01d8\u01d9\7K\2\2\u01d9\u01db\3\2\2\2\u01da\u01cc\3\2"+
		"\2\2\u01da\u01d2\3\2\2\2\u01db]\3\2\2\2\u01dc\u01e2\t\t\2\2\u01dd\u01df"+
		"\7\30\2\2\u01de\u01e0\5\32\16\2\u01df\u01de\3\2\2\2\u01df\u01e0\3\2\2"+
		"\2\u01e0\u01e2\3\2\2\2\u01e1\u01dc\3\2\2\2\u01e1\u01dd\3\2\2\2\u01e2_"+
		"\3\2\2\2\u01e3\u01e5\5b\62\2\u01e4\u01e3\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5"+
		"\u01e6\3\2\2\2\u01e6\u01e7\7\2\2\3\u01e7a\3\2\2\2\u01e8\u01ea\5d\63\2"+
		"\u01e9\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01e9\3\2\2\2\u01eb\u01ec"+
		"\3\2\2\2\u01ecc\3\2\2\2\u01ed\u01f1\5f\64\2\u01ee\u01f1\5\36\20\2\u01ef"+
		"\u01f1\7K\2\2\u01f0\u01ed\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f0\u01ef\3\2"+
		"\2\2\u01f1e\3\2\2\2\u01f2\u01f3\5 \21\2\u01f3\u01f4\5\60\31\2\u01f4\u01f6"+
		"\7/\2\2\u01f5\u01f7\5\66\34\2\u01f6\u01f5\3\2\2\2\u01f6\u01f7\3\2\2\2"+
		"\u01f7\u01f8\3\2\2\2\u01f8\u01f9\7\60\2\2\u01f9\u01fa\5R*\2\u01fag\3\2"+
		"\2\2\u01fb\u01fd\5\36\20\2\u01fc\u01fb\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe"+
		"\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ffi\3\2\2\2Cou~\u0081\u0083\u008b"+
		"\u0092\u009b\u00a3\u00ab\u00b3\u00bb\u00c3\u00cb\u00d2\u00d9\u00e0\u00e5"+
		"\u00e9\u00f0\u00f6\u00fd\u0107\u010c\u0112\u0115\u0117\u011c\u0120\u0125"+
		"\u012e\u0136\u0138\u013f\u0144\u0147\u0150\u0155\u0158\u015d\u0163\u0166"+
		"\u0168\u016f\u0173\u0176\u017b\u0180\u0189\u0196\u01a0\u01ad\u01b1\u01b8"+
		"\u01bc\u01bf\u01ca\u01da\u01df\u01e1\u01e4\u01eb\u01f0\u01f6\u01fe";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}