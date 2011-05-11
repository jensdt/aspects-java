// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g 2011-05-10 00:56:10

package aspectsjava.input;

import aspectsjava.model.pointcut.expression.methodinvocation.annotated.AnnotatedMethodInvocationExpression;
import aspectsjava.model.pointcut.expression.methodinvocation.annotated.AnnotationReference;
import aspectsjava.model.pointcut.expression.methodinvocation.signature.MethodReference;
import aspectsjava.model.pointcut.expression.methodinvocation.signature.QualifiedMethodHeader;
import aspectsjava.model.pointcut.expression.methodinvocation.signature.SignatureMethodInvocationPointcutExpression;
import aspectsjava.model.pointcut.expression.methodinvocation.signature.SimpleNameDeclarationWithParameterTypesHeader;
import chameleon.aspects.*;
import chameleon.aspects.advice.*;
import chameleon.aspects.advice.AdviceReturnStatement;
import chameleon.aspects.advice.types.*;
import chameleon.aspects.pointcut.*;
import chameleon.aspects.pointcut.expression.*;

import chameleon.aspects.pointcut.expression.generic.*;
import chameleon.aspects.pointcut.expression.namedpointcut.*;
import chameleon.aspects.pointcut.expression.dynamicexpression.*;
import chameleon.aspects.pointcut.expression.staticexpression.catchclause.*;
import chameleon.aspects.pointcut.expression.staticexpression.fieldAccess.*;
import chameleon.aspects.pointcut.expression.staticexpression.cast.*;

import chameleon.exception.ModelException;
import chameleon.exception.ChameleonProgrammerException;

import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.LookupStrategyFactory;

import chameleon.core.compilationunit.CompilationUnit;

import chameleon.core.declaration.CompositeQualifiedName;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.declaration.TargetDeclaration;
import chameleon.core.declaration.QualifiedName;
import chameleon.core.declaration.Signature;

import chameleon.core.element.Element;

import chameleon.core.reference.SimpleReference;
import chameleon.core.expression.Expression;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.expression.Literal;
import chameleon.core.expression.Assignable;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.TargetedExpression;
import chameleon.core.expression.VariableReference;

import chameleon.core.language.Language;

import chameleon.core.member.Member;

import chameleon.core.method.Method;
import chameleon.core.method.Implementation;
import chameleon.core.method.RegularImplementation;

import chameleon.core.method.exception.ExceptionClause;
import chameleon.core.method.exception.TypeExceptionDeclaration;

import chameleon.core.modifier.Modifier;

import chameleon.core.namespace.Namespace;
import chameleon.core.namespace.RootNamespace;
import chameleon.core.namespace.NamespaceOrTypeReference;
import chameleon.core.namespace.NamespaceReference;

import chameleon.core.namespacepart.NamespacePart;
import chameleon.core.namespacepart.Import;
import chameleon.core.namespacepart.TypeImport;
import chameleon.core.namespacepart.DemandImport;

import chameleon.core.reference.CrossReference;

import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;

import chameleon.core.variable.VariableDeclaration;
import chameleon.core.variable.VariableDeclarator;

import chameleon.oo.type.ClassBody;
import chameleon.oo.type.RegularType;
import chameleon.oo.type.Type;
import chameleon.oo.type.TypeWithBody;
import chameleon.oo.type.TypeReference;
import chameleon.oo.type.TypeElement;

import chameleon.oo.type.generics.TypeParameter;
import chameleon.oo.type.generics.FormalTypeParameter;
import chameleon.oo.type.generics.ActualTypeArgument;
import chameleon.oo.type.generics.BasicTypeArgument;
import chameleon.oo.type.generics.TypeConstraint;
import chameleon.oo.type.generics.ExtendsConstraint;
import chameleon.oo.type.generics.ExtendsWildcard;
import chameleon.oo.type.generics.SuperWildcard;

import chameleon.oo.type.inheritance.SubtypeRelation;

import chameleon.core.variable.Variable;
import chameleon.core.variable.FormalParameter;

import chameleon.input.InputProcessor;
import chameleon.input.Position2D;

import chameleon.support.expression.RegularLiteral;
import chameleon.support.expression.NullLiteral;
import chameleon.support.expression.AssignmentExpression;
import chameleon.support.expression.ConditionalExpression;
import chameleon.support.expression.ConditionalAndExpression;
import chameleon.support.expression.ConditionalOrExpression;
import chameleon.support.expression.InstanceofExpression;
import chameleon.support.expression.ThisLiteral;
import chameleon.support.expression.FilledArrayIndex;
import chameleon.support.expression.EmptyArrayIndex;
import chameleon.support.expression.ArrayIndex;
import chameleon.support.expression.ClassCastExpression;
import chameleon.support.expression.SuperTarget;

import chameleon.support.member.simplename.method.NormalMethod;
import chameleon.support.member.simplename.variable.MemberVariableDeclarator;
import chameleon.support.member.simplename.operator.infix.InfixOperatorInvocation;
import chameleon.support.member.simplename.operator.prefix.PrefixOperatorInvocation;
import chameleon.support.member.simplename.operator.postfix.PostfixOperatorInvocation;
import chameleon.support.member.simplename.method.RegularMethodInvocation;

import chameleon.support.modifier.Abstract;
import chameleon.support.modifier.Final;
import chameleon.support.modifier.Private;
import chameleon.support.modifier.Protected;
import chameleon.support.modifier.Public;
import chameleon.support.modifier.Static;
import chameleon.support.modifier.Native;
import chameleon.support.modifier.Enum;
import chameleon.support.modifier.Interface;

import chameleon.support.statement.StatementExpression;
import chameleon.support.statement.LocalClassStatement;
import chameleon.support.statement.AssertStatement;
import chameleon.support.statement.IfThenElseStatement;
import chameleon.support.statement.ForStatement;
import chameleon.support.statement.ForControl;
import chameleon.support.statement.ForInit;
import chameleon.support.statement.SimpleForControl;
import chameleon.support.statement.EnhancedForControl;
import chameleon.support.statement.StatementExprList;
import chameleon.support.statement.TryStatement;
import chameleon.support.statement.CatchClause;
import chameleon.support.statement.FinallyClause;
import chameleon.support.statement.DoStatement;
import chameleon.support.statement.WhileStatement;
import chameleon.support.statement.SwitchStatement;
import chameleon.support.statement.SwitchCase;
import chameleon.support.statement.SwitchLabel;
import chameleon.support.statement.CaseLabel;
import chameleon.support.statement.DefaultLabel;
import chameleon.support.statement.EnumLabel;
import chameleon.support.statement.ReturnStatement;
import chameleon.support.statement.ThrowStatement;
import chameleon.support.statement.BreakStatement;
import chameleon.support.statement.ContinueStatement;
import chameleon.support.statement.SynchronizedStatement;
import chameleon.support.statement.EmptyStatement;
import chameleon.support.statement.LabeledStatement;

import chameleon.support.type.EmptyTypeElement;
import chameleon.support.type.StaticInitializer;

import chameleon.support.variable.LocalVariableDeclarator;

import chameleon.support.input.ChameleonParser;

import chameleon.util.Util;

import jnome.core.expression.ArrayInitializer;
import jnome.core.expression.ClassLiteral;
import jnome.core.expression.ArrayAccessExpression;
import jnome.core.expression.ArrayCreationExpression;
import jnome.core.expression.invocation.ConstructorInvocation;
import jnome.core.expression.invocation.JavaMethodInvocation;
import jnome.core.expression.invocation.SuperConstructorDelegation;
import jnome.core.expression.invocation.ThisConstructorDelegation;

import jnome.core.imports.SingleStaticImport;

import jnome.core.language.Java;

import jnome.core.modifier.StrictFP;
import jnome.core.modifier.Transient;
import jnome.core.modifier.Volatile;
import jnome.core.modifier.Synchronized;
import jnome.core.modifier.JavaConstructor;
import jnome.core.modifier.Implements;
import jnome.core.modifier.AnnotationModifier;
import jnome.core.modifier.AnnotationType;

import jnome.core.type.JavaTypeReference;
import jnome.core.type.ArrayTypeReference;
import jnome.core.type.BasicJavaTypeReference;
import jnome.core.type.JavaIntersectionTypeReference;
import jnome.core.type.PureWildcard;

import jnome.core.enumeration.EnumConstant;

import jnome.core.variable.JavaVariableDeclaration;
import jnome.core.variable.MultiFormalParameter;

import jnome.input.JavaFactory;

import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class AspectParser extends ChameleonParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Identifier", "ENUM", "FloatingPointLiteral", "CharacterLiteral", "StringLiteral", "HexLiteral", "OctalLiteral", "DecimalLiteral", "ASSERT", "HexDigit", "IntegerTypeSuffix", "Exponent", "FloatTypeSuffix", "EscapeSequence", "UnicodeEscape", "OctalEscape", "Letter", "JavaIDDigit", "IdentifierWithWC", "WS", "COMMENT", "LINE_COMMENT", "'package'", "';'", "'import'", "'static'", "'.'", "'*'", "'public'", "'protected'", "'private'", "'abstract'", "'final'", "'strictfp'", "'class'", "'extends'", "'implements'", "'<'", "','", "'>'", "'&'", "'{'", "'}'", "'interface'", "'void'", "'['", "']'", "'throws'", "'='", "'native'", "'synchronized'", "'transient'", "'volatile'", "'boolean'", "'char'", "'byte'", "'short'", "'int'", "'long'", "'float'", "'double'", "'?'", "'super'", "'('", "')'", "'...'", "'this'", "'null'", "'true'", "'false'", "'@'", "'default'", "':'", "'if'", "'else'", "'for'", "'while'", "'do'", "'try'", "'finally'", "'switch'", "'return'", "'throw'", "'break'", "'continue'", "'catch'", "'case'", "'+='", "'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'%='", "'||'", "'&&'", "'|'", "'^'", "'=='", "'!='", "'instanceof'", "'+'", "'-'", "'/'", "'%'", "'++'", "'--'", "'~'", "'!'", "'new'", "'aspect'", "'pointcut'", "'call'", "'callAnnotated'", "'emptyCatch'", "'fieldRead'", "'handler'", "'cast'", "'arguments'", "'thisType'", "'targetType'", "'before_'", "'after_'", "'around_'", "'returning'", "'throwing'", "'proceed'"
    };
    public static final int EOF=-1;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__90=90;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__99=99;
    public static final int T__100=100;
    public static final int T__101=101;
    public static final int T__102=102;
    public static final int T__103=103;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__110=110;
    public static final int T__111=111;
    public static final int T__112=112;
    public static final int T__113=113;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__120=120;
    public static final int T__121=121;
    public static final int T__122=122;
    public static final int T__123=123;
    public static final int T__124=124;
    public static final int T__125=125;
    public static final int T__126=126;
    public static final int T__127=127;
    public static final int T__128=128;
    public static final int T__129=129;
    public static final int T__130=130;
    public static final int T__131=131;
    public static final int Identifier=4;
    public static final int ENUM=5;
    public static final int FloatingPointLiteral=6;
    public static final int CharacterLiteral=7;
    public static final int StringLiteral=8;
    public static final int HexLiteral=9;
    public static final int OctalLiteral=10;
    public static final int DecimalLiteral=11;
    public static final int ASSERT=12;
    public static final int HexDigit=13;
    public static final int IntegerTypeSuffix=14;
    public static final int Exponent=15;
    public static final int FloatTypeSuffix=16;
    public static final int EscapeSequence=17;
    public static final int UnicodeEscape=18;
    public static final int OctalEscape=19;
    public static final int Letter=20;
    public static final int JavaIDDigit=21;
    public static final int IdentifierWithWC=22;
    public static final int WS=23;
    public static final int COMMENT=24;
    public static final int LINE_COMMENT=25;

    // delegates
    public Aspect_JavaP gJavaP;
    // delegators


        public AspectParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public AspectParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[487+1];
             
            gJavaP = new Aspect_JavaP(input, state, this);         
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
        gJavaP.setTreeAdaptor(this.adaptor);
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return AspectParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g"; }



      @Override
      public void setLanguage(Language language) {
        gJavaP.setLanguage(language);
      }
      
      @Override
      public Language language() {
        return gJavaP.language();
      }
      
      public CompilationUnit getCompilationUnit() {
        return gJavaP.getCompilationUnit();
      }
    	   
      public void setCompilationUnit(CompilationUnit compilationUnit) {
        gJavaP.setCompilationUnit(compilationUnit);
      }
      
      public Namespace getDefaultNamespace() {
        return gJavaP.getDefaultNamespace();
      }

      public void setFactory(JavaFactory factory) {
        gJavaP.setFactory(factory);
      }
      
      public JavaFactory factory() {
        return gJavaP.factory();
      }
      
        public void processType(NamespacePart np, Type type){
        if(np == null) {throw new IllegalArgumentException("namespace part given to processType is null.");}
        if(type == null) {return;}  //throw new IllegalArgumentException("type given to processType is null.");}
        np.add(type);
        // inherit from java.lang.Object if there is no explicit extends relation
        String fqn = type.getFullyQualifiedName();
        if(fqn != null) {
          if(type.nonMemberInheritanceRelations().isEmpty() && (! fqn.equals("java.lang.Object"))){
            type.addInheritanceRelation(new SubtypeRelation(createTypeReference(new NamespaceOrTypeReference("java.lang"),"Object")));
          }
        }

      }
      
        public JavaTypeReference createTypeReference(CrossReference<?, ? extends TargetDeclaration> target, String name) {
        return ((Java)language()).createTypeReference(target,name);
      }
      
      public JavaTypeReference createTypeReference(CrossReference<?, ? extends TargetDeclaration> target, SimpleNameSignature signature) {
        return ((Java)language()).createTypeReference(target,signature);
      }

      public JavaTypeReference createTypeReference(NamedTarget target) {
        return ((Java)language()).createTypeReference(target);
      }
      
      public JavaTypeReference typeRef(String qn) {
        return ((Java)language()).createTypeReference(qn);
      }



    public static class compilationUnit_return extends ParserRuleReturnScope {
        public CompilationUnit element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "compilationUnit"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:289:1: compilationUnit returns [CompilationUnit element] : ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* ) ;
    public final AspectParser.compilationUnit_return compilationUnit() throws RecognitionException {
        AspectParser.compilationUnit_return retval = new AspectParser.compilationUnit_return();
        retval.start = input.LT(1);
        int compilationUnit_StartIndex = input.index();
        Object root_0 = null;

        Aspect_JavaP.packageDeclaration_return np = null;

        Aspect_JavaP.importDeclaration_return imp = null;

        Aspect_JavaP.typeDeclaration_return typech = null;

        Aspect_JavaP.classOrInterfaceDeclaration_return cd = null;

        AspectParser.aspect_return ad = null;

        Aspect_JavaP.annotations_return annotations1 = null;



         
        NamespacePart npp = null;
        retval.element = getCompilationUnit();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 408) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:294:5: ( ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* ) )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:294:8: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* )
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:294:8: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* )
            int alt8=2;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:294:10: annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
                    {
                    pushFollow(FOLLOW_annotations_in_compilationUnit80);
                    annotations1=annotations();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotations1.getTree());
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:295:9: (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==26) ) {
                        alt4=1;
                    }
                    else if ( (LA4_0==ENUM||LA4_0==29||(LA4_0>=32 && LA4_0<=38)||LA4_0==47||LA4_0==74) ) {
                        alt4=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 0, input);

                        throw nvae;
                    }
                    switch (alt4) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:295:13: np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )*
                            {
                            pushFollow(FOLLOW_packageDeclaration_in_compilationUnit96);
                            np=packageDeclaration();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, np.getTree());
                            if ( state.backtracking==0 ) {
                              npp=np.element;
                                               retval.element.add(npp);
                                               npp.addImport(new DemandImport(new NamespaceReference("java.lang")));
                                              
                            }
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:300:13: (imp= importDeclaration )*
                            loop1:
                            do {
                                int alt1=2;
                                int LA1_0 = input.LA(1);

                                if ( (LA1_0==28) ) {
                                    alt1=1;
                                }


                                switch (alt1) {
                            	case 1 :
                            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:300:14: imp= importDeclaration
                            	    {
                            	    pushFollow(FOLLOW_importDeclaration_in_compilationUnit132);
                            	    imp=importDeclaration();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, imp.getTree());
                            	    if ( state.backtracking==0 ) {
                            	      npp.addImport(imp.element);
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop1;
                                }
                            } while (true);

                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:301:13: (typech= typeDeclaration )*
                            loop2:
                            do {
                                int alt2=2;
                                int LA2_0 = input.LA(1);

                                if ( (LA2_0==ENUM||LA2_0==27||LA2_0==29||(LA2_0>=32 && LA2_0<=38)||LA2_0==47||LA2_0==74) ) {
                                    alt2=1;
                                }


                                switch (alt2) {
                            	case 1 :
                            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:301:14: typech= typeDeclaration
                            	    {
                            	    pushFollow(FOLLOW_typeDeclaration_in_compilationUnit153);
                            	    typech=typeDeclaration();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typech.getTree());
                            	    if ( state.backtracking==0 ) {
                            	      processType(npp,typech.element);
                            	                      
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop2;
                                }
                            } while (true);


                            }
                            break;
                        case 2 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:305:13: cd= classOrInterfaceDeclaration (typech= typeDeclaration )*
                            {
                            pushFollow(FOLLOW_classOrInterfaceDeclaration_in_compilationUnit202);
                            cd=classOrInterfaceDeclaration();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, cd.getTree());
                            if ( state.backtracking==0 ) {
                              npp = new NamespacePart(language().defaultNamespace());
                                              retval.element.add(npp);
                                              npp.addImport(new DemandImport(new NamespaceReference("java.lang")));
                                              processType(npp,cd.element);
                                             
                            }
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:311:13: (typech= typeDeclaration )*
                            loop3:
                            do {
                                int alt3=2;
                                int LA3_0 = input.LA(1);

                                if ( (LA3_0==ENUM||LA3_0==27||LA3_0==29||(LA3_0>=32 && LA3_0<=38)||LA3_0==47||LA3_0==74) ) {
                                    alt3=1;
                                }


                                switch (alt3) {
                            	case 1 :
                            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:311:14: typech= typeDeclaration
                            	    {
                            	    pushFollow(FOLLOW_typeDeclaration_in_compilationUnit237);
                            	    typech=typeDeclaration();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typech.getTree());
                            	    if ( state.backtracking==0 ) {
                            	      processType(npp,typech.element);
                            	                     
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    break loop3;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:316:9: (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )*
                    {
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:316:9: (np= packageDeclaration )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==26) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:316:10: np= packageDeclaration
                            {
                            pushFollow(FOLLOW_packageDeclaration_in_compilationUnit292);
                            np=packageDeclaration();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, np.getTree());
                            if ( state.backtracking==0 ) {

                                            npp=np.element;
                                          
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                               if(npp == null) {
                                 npp = new NamespacePart(language().defaultNamespace());
                               }
                               npp.addImport(new DemandImport(new NamespaceReference("java.lang")));
                               retval.element.add(npp);
                              
                    }
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:328:9: (imp= importDeclaration )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==28) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:328:10: imp= importDeclaration
                    	    {
                    	    pushFollow(FOLLOW_importDeclaration_in_compilationUnit341);
                    	    imp=importDeclaration();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, imp.getTree());
                    	    if ( state.backtracking==0 ) {
                    	      npp.addImport(imp.element);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:331:9: (typech= typeDeclaration | ad= aspect )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==ENUM||LA7_0==27||LA7_0==29||(LA7_0>=32 && LA7_0<=38)||LA7_0==47||LA7_0==74) ) {
                            alt7=1;
                        }
                        else if ( (LA7_0==115) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:331:10: typech= typeDeclaration
                    	    {
                    	    pushFollow(FOLLOW_typeDeclaration_in_compilationUnit378);
                    	    typech=typeDeclaration();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typech.getTree());
                    	    if ( state.backtracking==0 ) {

                    	                 processType(npp,typech.element);
                    	                
                    	    }

                    	    }
                    	    break;
                    	case 2 :
                    	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:336:11: ad= aspect
                    	    {
                    	    pushFollow(FOLLOW_aspect_in_compilationUnit416);
                    	    ad=aspect();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, ad.getTree());
                    	    if ( state.backtracking==0 ) {

                    	             		 npp.add(ad.element);       	
                    	             	  
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 408, compilationUnit_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "compilationUnit"

    public static class aspect_return extends ParserRuleReturnScope {
        public Aspect element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "aspect"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:343:1: aspect returns [Aspect element] : asp= 'aspect' name= Identifier '{' ( (adv= advice ) | (ptc= pointcut ) )* '}' ;
    public final AspectParser.aspect_return aspect() throws RecognitionException {
        AspectParser.aspect_return retval = new AspectParser.aspect_return();
        retval.start = input.LT(1);
        int aspect_StartIndex = input.index();
        Object root_0 = null;

        Token asp=null;
        Token name=null;
        Token char_literal2=null;
        Token char_literal3=null;
        AspectParser.advice_return adv = null;

        AspectParser.pointcut_return ptc = null;


        Object asp_tree=null;
        Object name_tree=null;
        Object char_literal2_tree=null;
        Object char_literal3_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 409) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:345:2: (asp= 'aspect' name= Identifier '{' ( (adv= advice ) | (ptc= pointcut ) )* '}' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:345:4: asp= 'aspect' name= Identifier '{' ( (adv= advice ) | (ptc= pointcut ) )* '}'
            {
            root_0 = (Object)adaptor.nil();

            asp=(Token)match(input,115,FOLLOW_115_in_aspect465); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            asp_tree = (Object)adaptor.create(asp);
            adaptor.addChild(root_0, asp_tree);
            }
            name=(Token)match(input,Identifier,FOLLOW_Identifier_in_aspect469); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            name_tree = (Object)adaptor.create(name);
            adaptor.addChild(root_0, name_tree);
            }
            if ( state.backtracking==0 ) {
              retval.element = new Aspect((name!=null?name.getText():null));
            }
            char_literal2=(Token)match(input,45,FOLLOW_45_in_aspect475); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal2_tree = (Object)adaptor.create(char_literal2);
            adaptor.addChild(root_0, char_literal2_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:347:2: ( (adv= advice ) | (ptc= pointcut ) )*
            loop9:
            do {
                int alt9=3;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==Identifier||LA9_0==48||(LA9_0>=57 && LA9_0<=64)||(LA9_0>=126 && LA9_0<=128)) ) {
                    alt9=1;
                }
                else if ( (LA9_0==116) ) {
                    alt9=2;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:347:3: (adv= advice )
            	    {
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:347:3: (adv= advice )
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:347:4: adv= advice
            	    {
            	    pushFollow(FOLLOW_advice_in_aspect484);
            	    adv=advice();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, adv.getTree());
            	    if ( state.backtracking==0 ) {
            	      retval.element.addAdvice(adv.element);
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:347:56: (ptc= pointcut )
            	    {
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:347:56: (ptc= pointcut )
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:347:57: ptc= pointcut
            	    {
            	    pushFollow(FOLLOW_pointcut_in_aspect491);
            	    ptc=pointcut();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, ptc.getTree());
            	    if ( state.backtracking==0 ) {
            	      retval.element.addPointcut(ptc.element);
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            char_literal3=(Token)match(input,46,FOLLOW_46_in_aspect499); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal3_tree = (Object)adaptor.create(char_literal3);
            adaptor.addChild(root_0, char_literal3_tree);
            }
            if ( state.backtracking==0 ) {

              		setKeyword(retval.element, asp);
              	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 409, aspect_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "aspect"

    public static class pointcut_return extends ParserRuleReturnScope {
        public Pointcut element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointcut"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:355:1: pointcut returns [Pointcut element] : pct= 'pointcut' decl= pointcutDecl pars= formalParameters ':' expr= pointcutExpression ';' ;
    public final AspectParser.pointcut_return pointcut() throws RecognitionException {
        AspectParser.pointcut_return retval = new AspectParser.pointcut_return();
        retval.start = input.LT(1);
        int pointcut_StartIndex = input.index();
        Object root_0 = null;

        Token pct=null;
        Token char_literal4=null;
        Token char_literal5=null;
        AspectParser.pointcutDecl_return decl = null;

        Aspect_JavaP.formalParameters_return pars = null;

        AspectParser.pointcutExpression_return expr = null;


        Object pct_tree=null;
        Object char_literal4_tree=null;
        Object char_literal5_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 410) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:357:2: (pct= 'pointcut' decl= pointcutDecl pars= formalParameters ':' expr= pointcutExpression ';' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:357:4: pct= 'pointcut' decl= pointcutDecl pars= formalParameters ':' expr= pointcutExpression ';'
            {
            root_0 = (Object)adaptor.nil();

            pct=(Token)match(input,116,FOLLOW_116_in_pointcut526); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            pct_tree = (Object)adaptor.create(pct);
            adaptor.addChild(root_0, pct_tree);
            }
            pushFollow(FOLLOW_pointcutDecl_in_pointcut530);
            decl=pointcutDecl();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, decl.getTree());
            pushFollow(FOLLOW_formalParameters_in_pointcut534);
            pars=formalParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, pars.getTree());
            char_literal4=(Token)match(input,76,FOLLOW_76_in_pointcut536); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal4_tree = (Object)adaptor.create(char_literal4);
            adaptor.addChild(root_0, char_literal4_tree);
            }
            pushFollow(FOLLOW_pointcutExpression_in_pointcut540);
            expr=pointcutExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
            char_literal5=(Token)match(input,27,FOLLOW_27_in_pointcut542); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal5_tree = (Object)adaptor.create(char_literal5);
            adaptor.addChild(root_0, char_literal5_tree);
            }
            if ( state.backtracking==0 ) {

              		SimpleNameDeclarationWithParametersHeader header = new SimpleNameDeclarationWithParametersHeader(decl.element);
              		header.addFormalParameters(pars.element);
              		setLocation(header, decl.start, pars.stop);
              		retval.element = new Pointcut(header, expr.element);
              		setKeyword(retval.element, pct);
              	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, decl.start, decl.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 410, pointcut_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "pointcut"

    public static class pointcutDecl_return extends ParserRuleReturnScope {
        public String element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointcutDecl"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:368:1: pointcutDecl returns [String element] : name= Identifier ;
    public final AspectParser.pointcutDecl_return pointcutDecl() throws RecognitionException {
        AspectParser.pointcutDecl_return retval = new AspectParser.pointcutDecl_return();
        retval.start = input.LT(1);
        int pointcutDecl_StartIndex = input.index();
        Object root_0 = null;

        Token name=null;

        Object name_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 411) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:369:2: (name= Identifier )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:369:4: name= Identifier
            {
            root_0 = (Object)adaptor.nil();

            name=(Token)match(input,Identifier,FOLLOW_Identifier_in_pointcutDecl564); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            name_tree = (Object)adaptor.create(name);
            adaptor.addChild(root_0, name_tree);
            }
            if ( state.backtracking==0 ) {
              retval.element = (name!=null?name.getText():null);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 411, pointcutDecl_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "pointcutDecl"

    public static class pointcutExpression_return extends ParserRuleReturnScope {
        public PointcutExpression element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointcutExpression"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:372:1: pointcutExpression returns [PointcutExpression element] : (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression | expr= pointcutExpressionOr );
    public final AspectParser.pointcutExpression_return pointcutExpression() throws RecognitionException {
        AspectParser.pointcutExpression_return retval = new AspectParser.pointcutExpression_return();
        retval.start = input.LT(1);
        int pointcutExpression_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal6=null;
        AspectParser.pointcutExpressionOr_return expr1 = null;

        AspectParser.pointcutExpression_return expr2 = null;

        AspectParser.pointcutExpressionOr_return expr = null;


        Object string_literal6_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 412) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:374:2: (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression | expr= pointcutExpressionOr )
            int alt10=2;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:374:4: expr1= pointcutExpressionOr '&&' expr2= pointcutExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pointcutExpressionOr_in_pointcutExpression589);
                    expr1=pointcutExpressionOr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr1.getTree());
                    string_literal6=(Token)match(input,100,FOLLOW_100_in_pointcutExpression591); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal6_tree = (Object)adaptor.create(string_literal6);
                    adaptor.addChild(root_0, string_literal6_tree);
                    }
                    pushFollow(FOLLOW_pointcutExpression_in_pointcutExpression595);
                    expr2=pointcutExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr2.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = new PointcutExpressionAnd(expr1.element, expr2.element);
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:375:4: expr= pointcutExpressionOr
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pointcutExpressionOr_in_pointcutExpression604);
                    expr=pointcutExpressionOr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = expr.element;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 412, pointcutExpression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "pointcutExpression"

    public static class pointcutExpressionOr_return extends ParserRuleReturnScope {
        public PointcutExpression element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointcutExpressionOr"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:378:1: pointcutExpressionOr returns [PointcutExpression element] : (expr1= pointcutAtom '||' expr2= pointcutExpressionOr | expr= pointcutAtom );
    public final AspectParser.pointcutExpressionOr_return pointcutExpressionOr() throws RecognitionException {
        AspectParser.pointcutExpressionOr_return retval = new AspectParser.pointcutExpressionOr_return();
        retval.start = input.LT(1);
        int pointcutExpressionOr_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal7=null;
        AspectParser.pointcutAtom_return expr1 = null;

        AspectParser.pointcutExpressionOr_return expr2 = null;

        AspectParser.pointcutAtom_return expr = null;


        Object string_literal7_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 413) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:380:2: (expr1= pointcutAtom '||' expr2= pointcutExpressionOr | expr= pointcutAtom )
            int alt11=2;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:380:4: expr1= pointcutAtom '||' expr2= pointcutExpressionOr
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pointcutAtom_in_pointcutExpressionOr627);
                    expr1=pointcutAtom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr1.getTree());
                    string_literal7=(Token)match(input,99,FOLLOW_99_in_pointcutExpressionOr629); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal7_tree = (Object)adaptor.create(string_literal7);
                    adaptor.addChild(root_0, string_literal7_tree);
                    }
                    pushFollow(FOLLOW_pointcutExpressionOr_in_pointcutExpressionOr633);
                    expr2=pointcutExpressionOr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr2.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = new PointcutExpressionOr(expr1.element, expr2.element);
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:381:4: expr= pointcutAtom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pointcutAtom_in_pointcutExpressionOr642);
                    expr=pointcutAtom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = expr.element;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 413, pointcutExpressionOr_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "pointcutExpressionOr"

    public static class subtypeMarker_return extends ParserRuleReturnScope {
        public SubtypeMarker element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "subtypeMarker"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:384:1: subtypeMarker returns [SubtypeMarker element] : '+' ;
    public final AspectParser.subtypeMarker_return subtypeMarker() throws RecognitionException {
        AspectParser.subtypeMarker_return retval = new AspectParser.subtypeMarker_return();
        retval.start = input.LT(1);
        int subtypeMarker_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal8=null;

        Object char_literal8_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 414) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:385:2: ( '+' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:385:4: '+'
            {
            root_0 = (Object)adaptor.nil();

            char_literal8=(Token)match(input,106,FOLLOW_106_in_subtypeMarker659); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal8_tree = (Object)adaptor.create(char_literal8);
            adaptor.addChild(root_0, char_literal8_tree);
            }
            if ( state.backtracking==0 ) {
              retval.element = new SubtypeMarker();
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 414, subtypeMarker_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "subtypeMarker"

    public static class pointcutAtom_return extends ParserRuleReturnScope {
        public PointcutExpression element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointcutAtom"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:387:1: pointcutAtom returns [PointcutExpression element] : (cl= 'call' '(' metref= methodReference ')' | clA= 'callAnnotated' '(' annot= Identifier ')' | emptyCatch= 'emptyCatch' | fieldRead= 'fieldRead' '(' fieldreadtype= type fieldref= fieldReference ')' | handler= 'handler' '(' exceptionType= type (includeSub= subtypeMarker )? ')' | cast= 'cast' '(' castType= type ')' | namedRef= namedPointcutReference | getargs= 'arguments' t= typesOrParameters | thisType= 'thisType' '(' exp= expression ')' | targetType= 'targetType' '(' exp= expression ')' | ifCheck= 'if' '(' exp= expression ')' | '!' expr1= pointcutAtom | '(' expr2= pointcutExpression ')' );
    public final AspectParser.pointcutAtom_return pointcutAtom() throws RecognitionException {
        AspectParser.pointcutAtom_return retval = new AspectParser.pointcutAtom_return();
        retval.start = input.LT(1);
        int pointcutAtom_StartIndex = input.index();
        Object root_0 = null;

        Token cl=null;
        Token clA=null;
        Token annot=null;
        Token emptyCatch=null;
        Token fieldRead=null;
        Token handler=null;
        Token cast=null;
        Token getargs=null;
        Token thisType=null;
        Token targetType=null;
        Token ifCheck=null;
        Token char_literal9=null;
        Token char_literal10=null;
        Token char_literal11=null;
        Token char_literal12=null;
        Token char_literal13=null;
        Token char_literal14=null;
        Token char_literal15=null;
        Token char_literal16=null;
        Token char_literal17=null;
        Token char_literal18=null;
        Token char_literal19=null;
        Token char_literal20=null;
        Token char_literal21=null;
        Token char_literal22=null;
        Token char_literal23=null;
        Token char_literal24=null;
        Token char_literal25=null;
        Token char_literal26=null;
        Token char_literal27=null;
        AspectParser.methodReference_return metref = null;

        Aspect_JavaP.type_return fieldreadtype = null;

        AspectParser.fieldReference_return fieldref = null;

        Aspect_JavaP.type_return exceptionType = null;

        AspectParser.subtypeMarker_return includeSub = null;

        Aspect_JavaP.type_return castType = null;

        AspectParser.namedPointcutReference_return namedRef = null;

        AspectParser.typesOrParameters_return t = null;

        AspectParser.expression_return exp = null;

        AspectParser.pointcutAtom_return expr1 = null;

        AspectParser.pointcutExpression_return expr2 = null;


        Object cl_tree=null;
        Object clA_tree=null;
        Object annot_tree=null;
        Object emptyCatch_tree=null;
        Object fieldRead_tree=null;
        Object handler_tree=null;
        Object cast_tree=null;
        Object getargs_tree=null;
        Object thisType_tree=null;
        Object targetType_tree=null;
        Object ifCheck_tree=null;
        Object char_literal9_tree=null;
        Object char_literal10_tree=null;
        Object char_literal11_tree=null;
        Object char_literal12_tree=null;
        Object char_literal13_tree=null;
        Object char_literal14_tree=null;
        Object char_literal15_tree=null;
        Object char_literal16_tree=null;
        Object char_literal17_tree=null;
        Object char_literal18_tree=null;
        Object char_literal19_tree=null;
        Object char_literal20_tree=null;
        Object char_literal21_tree=null;
        Object char_literal22_tree=null;
        Object char_literal23_tree=null;
        Object char_literal24_tree=null;
        Object char_literal25_tree=null;
        Object char_literal26_tree=null;
        Object char_literal27_tree=null;

        SubtypeMarker marker = null;
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 415) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:390:2: (cl= 'call' '(' metref= methodReference ')' | clA= 'callAnnotated' '(' annot= Identifier ')' | emptyCatch= 'emptyCatch' | fieldRead= 'fieldRead' '(' fieldreadtype= type fieldref= fieldReference ')' | handler= 'handler' '(' exceptionType= type (includeSub= subtypeMarker )? ')' | cast= 'cast' '(' castType= type ')' | namedRef= namedPointcutReference | getargs= 'arguments' t= typesOrParameters | thisType= 'thisType' '(' exp= expression ')' | targetType= 'targetType' '(' exp= expression ')' | ifCheck= 'if' '(' exp= expression ')' | '!' expr1= pointcutAtom | '(' expr2= pointcutExpression ')' )
            int alt13=13;
            switch ( input.LA(1) ) {
            case 117:
                {
                alt13=1;
                }
                break;
            case 118:
                {
                alt13=2;
                }
                break;
            case 119:
                {
                alt13=3;
                }
                break;
            case 120:
                {
                alt13=4;
                }
                break;
            case 121:
                {
                alt13=5;
                }
                break;
            case 122:
                {
                alt13=6;
                }
                break;
            case Identifier:
                {
                alt13=7;
                }
                break;
            case 123:
                {
                alt13=8;
                }
                break;
            case 124:
                {
                alt13=9;
                }
                break;
            case 125:
                {
                alt13=10;
                }
                break;
            case 77:
                {
                alt13=11;
                }
                break;
            case 113:
                {
                alt13=12;
                }
                break;
            case 67:
                {
                alt13=13;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:390:4: cl= 'call' '(' metref= methodReference ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    cl=(Token)match(input,117,FOLLOW_117_in_pointcutAtom685); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    cl_tree = (Object)adaptor.create(cl);
                    adaptor.addChild(root_0, cl_tree);
                    }
                    char_literal9=(Token)match(input,67,FOLLOW_67_in_pointcutAtom687); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal9_tree = (Object)adaptor.create(char_literal9);
                    adaptor.addChild(root_0, char_literal9_tree);
                    }
                    pushFollow(FOLLOW_methodReference_in_pointcutAtom691);
                    metref=methodReference();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, metref.getTree());
                    char_literal10=(Token)match(input,68,FOLLOW_68_in_pointcutAtom693); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal10_tree = (Object)adaptor.create(char_literal10);
                    adaptor.addChild(root_0, char_literal10_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.element = new SignatureMethodInvocationPointcutExpression(metref.element); setKeyword(retval.element, cl);
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:391:4: clA= 'callAnnotated' '(' annot= Identifier ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    clA=(Token)match(input,118,FOLLOW_118_in_pointcutAtom702); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    clA_tree = (Object)adaptor.create(clA);
                    adaptor.addChild(root_0, clA_tree);
                    }
                    char_literal11=(Token)match(input,67,FOLLOW_67_in_pointcutAtom704); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal11_tree = (Object)adaptor.create(char_literal11);
                    adaptor.addChild(root_0, char_literal11_tree);
                    }
                    annot=(Token)match(input,Identifier,FOLLOW_Identifier_in_pointcutAtom708); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    annot_tree = (Object)adaptor.create(annot);
                    adaptor.addChild(root_0, annot_tree);
                    }
                    char_literal12=(Token)match(input,68,FOLLOW_68_in_pointcutAtom710); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal12_tree = (Object)adaptor.create(char_literal12);
                    adaptor.addChild(root_0, char_literal12_tree);
                    }
                    if ( state.backtracking==0 ) {
                      AnnotatedMethodInvocationExpression result = new AnnotatedMethodInvocationExpression(); result.setReference(new AnnotationReference((annot!=null?annot.getText():null))); retval.element = result; setKeyword(retval.element, clA);
                    }

                    }
                    break;
                case 3 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:392:4: emptyCatch= 'emptyCatch'
                    {
                    root_0 = (Object)adaptor.nil();

                    emptyCatch=(Token)match(input,119,FOLLOW_119_in_pointcutAtom719); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    emptyCatch_tree = (Object)adaptor.create(emptyCatch);
                    adaptor.addChild(root_0, emptyCatch_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.element = new EmptyCatchClausePointcutExpression(); setKeyword(retval.element, emptyCatch); 
                    }

                    }
                    break;
                case 4 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:393:4: fieldRead= 'fieldRead' '(' fieldreadtype= type fieldref= fieldReference ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    fieldRead=(Token)match(input,120,FOLLOW_120_in_pointcutAtom728); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    fieldRead_tree = (Object)adaptor.create(fieldRead);
                    adaptor.addChild(root_0, fieldRead_tree);
                    }
                    char_literal13=(Token)match(input,67,FOLLOW_67_in_pointcutAtom730); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal13_tree = (Object)adaptor.create(char_literal13);
                    adaptor.addChild(root_0, char_literal13_tree);
                    }
                    pushFollow(FOLLOW_type_in_pointcutAtom734);
                    fieldreadtype=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fieldreadtype.getTree());
                    pushFollow(FOLLOW_fieldReference_in_pointcutAtom738);
                    fieldref=fieldReference();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fieldref.getTree());
                    char_literal14=(Token)match(input,68,FOLLOW_68_in_pointcutAtom740); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal14_tree = (Object)adaptor.create(char_literal14);
                    adaptor.addChild(root_0, char_literal14_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.element = new FieldReadPointcutExpression(fieldreadtype.element, fieldref.element); setKeyword(retval.element, fieldRead); 
                    }

                    }
                    break;
                case 5 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:394:4: handler= 'handler' '(' exceptionType= type (includeSub= subtypeMarker )? ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    handler=(Token)match(input,121,FOLLOW_121_in_pointcutAtom749); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    handler_tree = (Object)adaptor.create(handler);
                    adaptor.addChild(root_0, handler_tree);
                    }
                    char_literal15=(Token)match(input,67,FOLLOW_67_in_pointcutAtom751); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal15_tree = (Object)adaptor.create(char_literal15);
                    adaptor.addChild(root_0, char_literal15_tree);
                    }
                    pushFollow(FOLLOW_type_in_pointcutAtom755);
                    exceptionType=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exceptionType.getTree());
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:394:45: (includeSub= subtypeMarker )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==106) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:394:46: includeSub= subtypeMarker
                            {
                            pushFollow(FOLLOW_subtypeMarker_in_pointcutAtom760);
                            includeSub=subtypeMarker();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, includeSub.getTree());
                            if ( state.backtracking==0 ) {
                              marker=includeSub.element;
                            }

                            }
                            break;

                    }

                    char_literal16=(Token)match(input,68,FOLLOW_68_in_pointcutAtom766); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal16_tree = (Object)adaptor.create(char_literal16);
                    adaptor.addChild(root_0, char_literal16_tree);
                    }
                    if ( state.backtracking==0 ) {
                      TypeCatchClausePointcutExpression catchHandler = new TypeCatchClausePointcutExpression(); catchHandler.setExceptionType(exceptionType.element); catchHandler.setSubtypeMarker(marker); retval.element = catchHandler; setKeyword(retval.element, handler); 
                    }

                    }
                    break;
                case 6 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:395:4: cast= 'cast' '(' castType= type ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    cast=(Token)match(input,122,FOLLOW_122_in_pointcutAtom775); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    cast_tree = (Object)adaptor.create(cast);
                    adaptor.addChild(root_0, cast_tree);
                    }
                    char_literal17=(Token)match(input,67,FOLLOW_67_in_pointcutAtom777); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal17_tree = (Object)adaptor.create(char_literal17);
                    adaptor.addChild(root_0, char_literal17_tree);
                    }
                    pushFollow(FOLLOW_type_in_pointcutAtom781);
                    castType=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, castType.getTree());
                    char_literal18=(Token)match(input,68,FOLLOW_68_in_pointcutAtom783); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal18_tree = (Object)adaptor.create(char_literal18);
                    adaptor.addChild(root_0, char_literal18_tree);
                    }
                    if ( state.backtracking==0 ) {
                       retval.element = new CastPointcutExpression(castType.element); setKeyword(retval.element, cast); 
                    }

                    }
                    break;
                case 7 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:396:4: namedRef= namedPointcutReference
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_namedPointcutReference_in_pointcutAtom792);
                    namedRef=namedPointcutReference();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, namedRef.getTree());
                    if ( state.backtracking==0 ) {
                      NamedPointcutExpression named = new NamedPointcutExpression(); named.setPointcutReference(namedRef.element); retval.element = named;
                    }

                    }
                    break;
                case 8 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:398:4: getargs= 'arguments' t= typesOrParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    getargs=(Token)match(input,123,FOLLOW_123_in_pointcutAtom803); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    getargs_tree = (Object)adaptor.create(getargs);
                    adaptor.addChild(root_0, getargs_tree);
                    }
                    pushFollow(FOLLOW_typesOrParameters_in_pointcutAtom807);
                    t=typesOrParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if ( state.backtracking==0 ) {
                      ArgsPointcutExpression expr = new ArgsPointcutExpression(); expr.addAll(t.element); retval.element = expr; setKeyword(retval.element, getargs); 
                    }

                    }
                    break;
                case 9 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:399:4: thisType= 'thisType' '(' exp= expression ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    thisType=(Token)match(input,124,FOLLOW_124_in_pointcutAtom816); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    thisType_tree = (Object)adaptor.create(thisType);
                    adaptor.addChild(root_0, thisType_tree);
                    }
                    char_literal19=(Token)match(input,67,FOLLOW_67_in_pointcutAtom818); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal19_tree = (Object)adaptor.create(char_literal19);
                    adaptor.addChild(root_0, char_literal19_tree);
                    }
                    pushFollow(FOLLOW_expression_in_pointcutAtom822);
                    exp=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
                    char_literal20=(Token)match(input,68,FOLLOW_68_in_pointcutAtom824); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal20_tree = (Object)adaptor.create(char_literal20);
                    adaptor.addChild(root_0, char_literal20_tree);
                    }
                    if ( state.backtracking==0 ) {
                      ThisTypePointcutExpression expr = new ThisTypePointcutExpression((NamedTargetExpression) exp.element); retval.element = expr; setKeyword(retval.element, thisType); 
                    }

                    }
                    break;
                case 10 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:400:4: targetType= 'targetType' '(' exp= expression ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    targetType=(Token)match(input,125,FOLLOW_125_in_pointcutAtom833); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    targetType_tree = (Object)adaptor.create(targetType);
                    adaptor.addChild(root_0, targetType_tree);
                    }
                    char_literal21=(Token)match(input,67,FOLLOW_67_in_pointcutAtom835); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal21_tree = (Object)adaptor.create(char_literal21);
                    adaptor.addChild(root_0, char_literal21_tree);
                    }
                    pushFollow(FOLLOW_expression_in_pointcutAtom839);
                    exp=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
                    char_literal22=(Token)match(input,68,FOLLOW_68_in_pointcutAtom841); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal22_tree = (Object)adaptor.create(char_literal22);
                    adaptor.addChild(root_0, char_literal22_tree);
                    }
                    if ( state.backtracking==0 ) {
                      TargetTypePointcutExpression expr = new TargetTypePointcutExpression((NamedTargetExpression) exp.element); retval.element = expr; setKeyword(retval.element, targetType); 
                    }

                    }
                    break;
                case 11 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:401:4: ifCheck= 'if' '(' exp= expression ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    ifCheck=(Token)match(input,77,FOLLOW_77_in_pointcutAtom850); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ifCheck_tree = (Object)adaptor.create(ifCheck);
                    adaptor.addChild(root_0, ifCheck_tree);
                    }
                    char_literal23=(Token)match(input,67,FOLLOW_67_in_pointcutAtom852); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal23_tree = (Object)adaptor.create(char_literal23);
                    adaptor.addChild(root_0, char_literal23_tree);
                    }
                    pushFollow(FOLLOW_expression_in_pointcutAtom856);
                    exp=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
                    char_literal24=(Token)match(input,68,FOLLOW_68_in_pointcutAtom858); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal24_tree = (Object)adaptor.create(char_literal24);
                    adaptor.addChild(root_0, char_literal24_tree);
                    }
                    if ( state.backtracking==0 ) {
                      IfPointcutExpression expr = new IfPointcutExpression(exp.element); retval.element = expr; setKeyword(retval.element, ifCheck);
                    }

                    }
                    break;
                case 12 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:402:4: '!' expr1= pointcutAtom
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal25=(Token)match(input,113,FOLLOW_113_in_pointcutAtom865); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal25_tree = (Object)adaptor.create(char_literal25);
                    adaptor.addChild(root_0, char_literal25_tree);
                    }
                    pushFollow(FOLLOW_pointcutAtom_in_pointcutAtom869);
                    expr1=pointcutAtom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr1.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = new PointcutExpressionNot(expr1.element);
                    }

                    }
                    break;
                case 13 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:403:4: '(' expr2= pointcutExpression ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal26=(Token)match(input,67,FOLLOW_67_in_pointcutAtom876); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal26_tree = (Object)adaptor.create(char_literal26);
                    adaptor.addChild(root_0, char_literal26_tree);
                    }
                    pushFollow(FOLLOW_pointcutExpression_in_pointcutAtom880);
                    expr2=pointcutExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr2.getTree());
                    char_literal27=(Token)match(input,68,FOLLOW_68_in_pointcutAtom882); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal27_tree = (Object)adaptor.create(char_literal27);
                    adaptor.addChild(root_0, char_literal27_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.element = expr2.element;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 415, pointcutAtom_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "pointcutAtom"

    public static class namedPointcutReference_return extends ParserRuleReturnScope {
        public PointcutReference element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "namedPointcutReference"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:406:1: namedPointcutReference returns [PointcutReference element] : decl= pointcutDecl '(' (params= argParams )? end= ')' ;
    public final AspectParser.namedPointcutReference_return namedPointcutReference() throws RecognitionException {
        AspectParser.namedPointcutReference_return retval = new AspectParser.namedPointcutReference_return();
        retval.start = input.LT(1);
        int namedPointcutReference_StartIndex = input.index();
        Object root_0 = null;

        Token end=null;
        Token char_literal28=null;
        AspectParser.pointcutDecl_return decl = null;

        AspectParser.argParams_return params = null;


        Object end_tree=null;
        Object char_literal28_tree=null;

        List<NamedTargetExpression> arguments = new ArrayList<NamedTargetExpression>();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 416) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:408:2: (decl= pointcutDecl '(' (params= argParams )? end= ')' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:408:4: decl= pointcutDecl '(' (params= argParams )? end= ')'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_pointcutDecl_in_namedPointcutReference905);
            decl=pointcutDecl();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, decl.getTree());
            char_literal28=(Token)match(input,67,FOLLOW_67_in_namedPointcutReference907); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal28_tree = (Object)adaptor.create(char_literal28);
            adaptor.addChild(root_0, char_literal28_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:408:26: (params= argParams )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==Identifier) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:408:27: params= argParams
                    {
                    pushFollow(FOLLOW_argParams_in_namedPointcutReference912);
                    params=argParams();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, params.getTree());
                    if ( state.backtracking==0 ) {
                      arguments = params.element;
                    }

                    }
                    break;

            }

            end=(Token)match(input,68,FOLLOW_68_in_namedPointcutReference920); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            end_tree = (Object)adaptor.create(end);
            adaptor.addChild(root_0, end_tree);
            }
            if ( state.backtracking==0 ) {
              PointcutReference ref = new PointcutReference(decl.element); ref.addAllArguments(arguments); retval.element = ref;
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 416, namedPointcutReference_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "namedPointcutReference"

    public static class fieldReference_return extends ParserRuleReturnScope {
        public FieldReference element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fieldReference"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:411:1: fieldReference returns [FieldReference element] : (initName= Identifier ( '.' appendName= Identifier )* ) ;
    public final AspectParser.fieldReference_return fieldReference() throws RecognitionException {
        AspectParser.fieldReference_return retval = new AspectParser.fieldReference_return();
        retval.start = input.LT(1);
        int fieldReference_StartIndex = input.index();
        Object root_0 = null;

        Token initName=null;
        Token appendName=null;
        Token char_literal29=null;

        Object initName_tree=null;
        Object appendName_tree=null;
        Object char_literal29_tree=null;

        String fullName = "";
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 417) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:413:2: ( (initName= Identifier ( '.' appendName= Identifier )* ) )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:413:4: (initName= Identifier ( '.' appendName= Identifier )* )
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:413:4: (initName= Identifier ( '.' appendName= Identifier )* )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:413:5: initName= Identifier ( '.' appendName= Identifier )*
            {
            initName=(Token)match(input,Identifier,FOLLOW_Identifier_in_fieldReference945); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            initName_tree = (Object)adaptor.create(initName);
            adaptor.addChild(root_0, initName_tree);
            }
            if ( state.backtracking==0 ) {
              fullName = (initName!=null?initName.getText():null);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:413:54: ( '.' appendName= Identifier )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==30) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:413:55: '.' appendName= Identifier
            	    {
            	    char_literal29=(Token)match(input,30,FOLLOW_30_in_fieldReference950); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal29_tree = (Object)adaptor.create(char_literal29);
            	    adaptor.addChild(root_0, char_literal29_tree);
            	    }
            	    appendName=(Token)match(input,Identifier,FOLLOW_Identifier_in_fieldReference954); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    appendName_tree = (Object)adaptor.create(appendName);
            	    adaptor.addChild(root_0, appendName_tree);
            	    }
            	    if ( state.backtracking==0 ) {
            	      fullName += "." + (appendName!=null?appendName.getText():null); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {
              retval.element = new FieldReference(fullName);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 417, fieldReference_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "fieldReference"

    public static class argParams_return extends ParserRuleReturnScope {
        public List<NamedTargetExpression> element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "argParams"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:417:1: argParams returns [List<NamedTargetExpression> element] : name= Identifier ( ',' otherparams= argParams )? ;
    public final AspectParser.argParams_return argParams() throws RecognitionException {
        AspectParser.argParams_return retval = new AspectParser.argParams_return();
        retval.start = input.LT(1);
        int argParams_StartIndex = input.index();
        Object root_0 = null;

        Token name=null;
        Token char_literal30=null;
        AspectParser.argParams_return otherparams = null;


        Object name_tree=null;
        Object char_literal30_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 418) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:418:2: (name= Identifier ( ',' otherparams= argParams )? )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:418:4: name= Identifier ( ',' otherparams= argParams )?
            {
            root_0 = (Object)adaptor.nil();

            name=(Token)match(input,Identifier,FOLLOW_Identifier_in_argParams981); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            name_tree = (Object)adaptor.create(name);
            adaptor.addChild(root_0, name_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:418:20: ( ',' otherparams= argParams )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==42) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:418:21: ',' otherparams= argParams
                    {
                    char_literal30=(Token)match(input,42,FOLLOW_42_in_argParams984); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal30_tree = (Object)adaptor.create(char_literal30);
                    adaptor.addChild(root_0, char_literal30_tree);
                    }
                    pushFollow(FOLLOW_argParams_in_argParams988);
                    otherparams=argParams();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, otherparams.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element=otherparams.element; 
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              if(retval.element == null) {
                       	retval.element=new ArrayList<NamedTargetExpression>();
              	  }
              	  retval.element.add(0, new NamedTargetExpression((name!=null?name.getText():null)));
                       
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 418, argParams_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "argParams"

    public static class advice_return extends ParserRuleReturnScope {
        public Advice element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "advice"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:426:1: advice returns [Advice element] : (t= type | 'void' )? advtype= adviceTypeModifier pars= formalParameters (advtypespec= adviceTypeModifierSpecifier )? ':' pointcutExpr= pointcutExpression bdy= adviceBody ;
    public final AspectParser.advice_return advice() throws RecognitionException {
        AspectParser.advice_return retval = new AspectParser.advice_return();
        retval.start = input.LT(1);
        int advice_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal31=null;
        Token char_literal32=null;
        Aspect_JavaP.type_return t = null;

        AspectParser.adviceTypeModifier_return advtype = null;

        Aspect_JavaP.formalParameters_return pars = null;

        AspectParser.adviceTypeModifierSpecifier_return advtypespec = null;

        AspectParser.pointcutExpression_return pointcutExpr = null;

        AspectParser.adviceBody_return bdy = null;


        Object string_literal31_tree=null;
        Object char_literal32_tree=null;

        TypeReference tref = null; List<NamedTargetExpression> arguments = new ArrayList<NamedTargetExpression>(); Modifier adviceTypeSpecifier = null;
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 419) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:429:2: ( (t= type | 'void' )? advtype= adviceTypeModifier pars= formalParameters (advtypespec= adviceTypeModifierSpecifier )? ':' pointcutExpr= pointcutExpression bdy= adviceBody )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:429:4: (t= type | 'void' )? advtype= adviceTypeModifier pars= formalParameters (advtypespec= adviceTypeModifierSpecifier )? ':' pointcutExpr= pointcutExpression bdy= adviceBody
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:429:4: (t= type | 'void' )?
            int alt17=3;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==Identifier||(LA17_0>=57 && LA17_0<=64)) ) {
                alt17=1;
            }
            else if ( (LA17_0==48) ) {
                alt17=2;
            }
            switch (alt17) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:429:5: t= type
                    {
                    pushFollow(FOLLOW_type_in_advice1022);
                    t=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if ( state.backtracking==0 ) {
                      tref=t.element;
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:429:31: 'void'
                    {
                    string_literal31=(Token)match(input,48,FOLLOW_48_in_advice1027); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal31_tree = (Object)adaptor.create(string_literal31);
                    adaptor.addChild(root_0, string_literal31_tree);
                    }
                    if ( state.backtracking==0 ) {
                      tref = typeRef("void");
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_adviceTypeModifier_in_advice1035);
            advtype=adviceTypeModifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, advtype.getTree());
            pushFollow(FOLLOW_formalParameters_in_advice1039);
            pars=formalParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, pars.getTree());
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:429:115: (advtypespec= adviceTypeModifierSpecifier )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=129 && LA18_0<=130)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:429:116: advtypespec= adviceTypeModifierSpecifier
                    {
                    pushFollow(FOLLOW_adviceTypeModifierSpecifier_in_advice1044);
                    advtypespec=adviceTypeModifierSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, advtypespec.getTree());
                    if ( state.backtracking==0 ) {
                      adviceTypeSpecifier = advtypespec.element; 
                    }

                    }
                    break;

            }

            char_literal32=(Token)match(input,76,FOLLOW_76_in_advice1050); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal32_tree = (Object)adaptor.create(char_literal32);
            adaptor.addChild(root_0, char_literal32_tree);
            }
            pushFollow(FOLLOW_pointcutExpression_in_advice1054);
            pointcutExpr=pointcutExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, pointcutExpr.getTree());
            if ( state.backtracking==0 ) {

              		retval.element=new Advice(tref);
              		retval.element.setPointcutExpression(pointcutExpr.element);
              		retval.element.addModifier(advtype.element);
              		retval.element.addModifier(adviceTypeSpecifier);
              		retval.element.addFormalParameters(pars.element);
              	
            }
            pushFollow(FOLLOW_adviceBody_in_advice1065);
            bdy=adviceBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bdy.getTree());
            if ( state.backtracking==0 ) {

              		retval.element.setBody(bdy.element);
              	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 419, advice_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "advice"

    public static class adviceBody_return extends ParserRuleReturnScope {
        public Block element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "adviceBody"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:444:1: adviceBody returns [Block element] : b= adviceBlock ;
    public final AspectParser.adviceBody_return adviceBody() throws RecognitionException {
        AspectParser.adviceBody_return retval = new AspectParser.adviceBody_return();
        retval.start = input.LT(1);
        int adviceBody_StartIndex = input.index();
        Object root_0 = null;

        AspectParser.adviceBlock_return b = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 420) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:445:5: (b= adviceBlock )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:445:9: b= adviceBlock
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_adviceBlock_in_adviceBody1091);
            b=adviceBlock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, b.getTree());
            if ( state.backtracking==0 ) {
              retval.element = b.element;
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 420, adviceBody_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "adviceBody"

    public static class adviceBlock_return extends ParserRuleReturnScope {
        public Block element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "adviceBlock"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:448:1: adviceBlock returns [Block element] : '{' (stat= adviceBlockStatement )* '}' ;
    public final AspectParser.adviceBlock_return adviceBlock() throws RecognitionException {
        AspectParser.adviceBlock_return retval = new AspectParser.adviceBlock_return();
        retval.start = input.LT(1);
        int adviceBlock_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal33=null;
        Token char_literal34=null;
        AspectParser.adviceBlockStatement_return stat = null;


        Object char_literal33_tree=null;
        Object char_literal34_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 421) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:449:5: ( '{' (stat= adviceBlockStatement )* '}' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:449:9: '{' (stat= adviceBlockStatement )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal33=(Token)match(input,45,FOLLOW_45_in_adviceBlock1120); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal33_tree = (Object)adaptor.create(char_literal33);
            adaptor.addChild(root_0, char_literal33_tree);
            }
            if ( state.backtracking==0 ) {
              retval.element = new Block();
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:449:45: (stat= adviceBlockStatement )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=Identifier && LA19_0<=ASSERT)||LA19_0==27||LA19_0==29||(LA19_0>=32 && LA19_0<=38)||LA19_0==45||(LA19_0>=47 && LA19_0<=48)||LA19_0==54||(LA19_0>=57 && LA19_0<=64)||(LA19_0>=66 && LA19_0<=67)||(LA19_0>=70 && LA19_0<=74)||LA19_0==77||(LA19_0>=79 && LA19_0<=82)||(LA19_0>=84 && LA19_0<=88)||(LA19_0>=106 && LA19_0<=107)||(LA19_0>=110 && LA19_0<=114)||LA19_0==131) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:449:46: stat= adviceBlockStatement
            	    {
            	    pushFollow(FOLLOW_adviceBlockStatement_in_adviceBlock1127);
            	    stat=adviceBlockStatement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stat.getTree());
            	    if ( state.backtracking==0 ) {
            	      if(stat != null) {retval.element.addStatement(stat.element);}
            	    }

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            char_literal34=(Token)match(input,46,FOLLOW_46_in_adviceBlock1133); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal34_tree = (Object)adaptor.create(char_literal34);
            adaptor.addChild(root_0, char_literal34_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 421, adviceBlock_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "adviceBlock"

    public static class adviceBlockStatement_return extends ParserRuleReturnScope {
        public Statement element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "adviceBlockStatement"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:452:1: adviceBlockStatement returns [Statement element] : (local= localVariableDeclarationStatement | cd= classOrInterfaceDeclaration | specialReturn= adviceReturnStatement | stat= statement );
    public final AspectParser.adviceBlockStatement_return adviceBlockStatement() throws RecognitionException {
        AspectParser.adviceBlockStatement_return retval = new AspectParser.adviceBlockStatement_return();
        retval.start = input.LT(1);
        int adviceBlockStatement_StartIndex = input.index();
        Object root_0 = null;

        Aspect_JavaP.localVariableDeclarationStatement_return local = null;

        Aspect_JavaP.classOrInterfaceDeclaration_return cd = null;

        AspectParser.adviceReturnStatement_return specialReturn = null;

        Aspect_JavaP.statement_return stat = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 422) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:454:5: (local= localVariableDeclarationStatement | cd= classOrInterfaceDeclaration | specialReturn= adviceReturnStatement | stat= statement )
            int alt20=4;
            alt20 = dfa20.predict(input);
            switch (alt20) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:454:9: local= localVariableDeclarationStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_localVariableDeclarationStatement_in_adviceBlockStatement1166);
                    local=localVariableDeclarationStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, local.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = local.element;
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:455:9: cd= classOrInterfaceDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_classOrInterfaceDeclaration_in_adviceBlockStatement1180);
                    cd=classOrInterfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, cd.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = new LocalClassStatement(cd.element);
                    }

                    }
                    break;
                case 3 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:456:7: specialReturn= adviceReturnStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_adviceReturnStatement_in_adviceBlockStatement1192);
                    specialReturn=adviceReturnStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, specialReturn.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = specialReturn.element;
                    }

                    }
                    break;
                case 4 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:457:9: stat= statement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_statement_in_adviceBlockStatement1206);
                    stat=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, stat.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = stat.element;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              assert(retval.element != null);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 422, adviceBlockStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "adviceBlockStatement"

    public static class adviceReturnStatement_return extends ParserRuleReturnScope {
        public Statement element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "adviceReturnStatement"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:460:1: adviceReturnStatement returns [Statement element] : retkey= 'return' (retex= expression )? ';' ;
    public final AspectParser.adviceReturnStatement_return adviceReturnStatement() throws RecognitionException {
        AspectParser.adviceReturnStatement_return retval = new AspectParser.adviceReturnStatement_return();
        retval.start = input.LT(1);
        int adviceReturnStatement_StartIndex = input.index();
        Object root_0 = null;

        Token retkey=null;
        Token char_literal35=null;
        AspectParser.expression_return retex = null;


        Object retkey_tree=null;
        Object char_literal35_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 423) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:462:5: (retkey= 'return' (retex= expression )? ';' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:462:7: retkey= 'return' (retex= expression )? ';'
            {
            root_0 = (Object)adaptor.nil();

            retkey=(Token)match(input,85,FOLLOW_85_in_adviceReturnStatement1239); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            retkey_tree = (Object)adaptor.create(retkey);
            adaptor.addChild(root_0, retkey_tree);
            }
            if ( state.backtracking==0 ) {
              retval.element = new AdviceReturnStatement();
                     setKeyword(retval.element,retkey);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:465:8: (retex= expression )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==Identifier||(LA21_0>=FloatingPointLiteral && LA21_0<=DecimalLiteral)||LA21_0==48||(LA21_0>=57 && LA21_0<=64)||(LA21_0>=66 && LA21_0<=67)||(LA21_0>=70 && LA21_0<=73)||(LA21_0>=106 && LA21_0<=107)||(LA21_0>=110 && LA21_0<=114)||LA21_0==131) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:465:9: retex= expression
                    {
                    pushFollow(FOLLOW_expression_in_adviceReturnStatement1261);
                    retex=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, retex.getTree());
                    if ( state.backtracking==0 ) {
                      ((ReturnStatement)retval.element).setExpression(retex.element);
                            
                    }

                    }
                    break;

            }

            char_literal35=(Token)match(input,27,FOLLOW_27_in_adviceReturnStatement1267); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal35_tree = (Object)adaptor.create(char_literal35);
            adaptor.addChild(root_0, char_literal35_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, (CommonToken)retval.start, (CommonToken)retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 423, adviceReturnStatement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "adviceReturnStatement"

    public static class adviceTypeModifier_return extends ParserRuleReturnScope {
        public Modifier element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "adviceTypeModifier"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:470:1: adviceTypeModifier returns [Modifier element] : (bf= 'before_' | af= 'after_' | ar= 'around_' );
    public final AspectParser.adviceTypeModifier_return adviceTypeModifier() throws RecognitionException {
        AspectParser.adviceTypeModifier_return retval = new AspectParser.adviceTypeModifier_return();
        retval.start = input.LT(1);
        int adviceTypeModifier_StartIndex = input.index();
        Object root_0 = null;

        Token bf=null;
        Token af=null;
        Token ar=null;

        Object bf_tree=null;
        Object af_tree=null;
        Object ar_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 424) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:471:2: (bf= 'before_' | af= 'after_' | ar= 'around_' )
            int alt22=3;
            switch ( input.LA(1) ) {
            case 126:
                {
                alt22=1;
                }
                break;
            case 127:
                {
                alt22=2;
                }
                break;
            case 128:
                {
                alt22=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:471:4: bf= 'before_'
                    {
                    root_0 = (Object)adaptor.nil();

                    bf=(Token)match(input,126,FOLLOW_126_in_adviceTypeModifier1292); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    bf_tree = (Object)adaptor.create(bf);
                    adaptor.addChild(root_0, bf_tree);
                    }
                    if ( state.backtracking==0 ) {
                       retval.element = new Before(); setKeyword(retval.element, bf); 
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:472:4: af= 'after_'
                    {
                    root_0 = (Object)adaptor.nil();

                    af=(Token)match(input,127,FOLLOW_127_in_adviceTypeModifier1301); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    af_tree = (Object)adaptor.create(af);
                    adaptor.addChild(root_0, af_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.element = new After(); setKeyword(retval.element, af); 
                    }

                    }
                    break;
                case 3 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:473:4: ar= 'around_'
                    {
                    root_0 = (Object)adaptor.nil();

                    ar=(Token)match(input,128,FOLLOW_128_in_adviceTypeModifier1310); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ar_tree = (Object)adaptor.create(ar);
                    adaptor.addChild(root_0, ar_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.element = new Around(); setKeyword(retval.element, ar); 
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 424, adviceTypeModifier_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "adviceTypeModifier"

    public static class adviceTypeModifierSpecifier_return extends ParserRuleReturnScope {
        public Modifier element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "adviceTypeModifierSpecifier"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:476:1: adviceTypeModifierSpecifier returns [Modifier element] : (rt= 'returning' ( '(' (retparam= formalParameter )? ')' )? | tw= 'throwing' ( '(' (throwabletype= formalParameter )? ')' )? );
    public final AspectParser.adviceTypeModifierSpecifier_return adviceTypeModifierSpecifier() throws RecognitionException {
        AspectParser.adviceTypeModifierSpecifier_return retval = new AspectParser.adviceTypeModifierSpecifier_return();
        retval.start = input.LT(1);
        int adviceTypeModifierSpecifier_StartIndex = input.index();
        Object root_0 = null;

        Token rt=null;
        Token tw=null;
        Token char_literal36=null;
        Token char_literal37=null;
        Token char_literal38=null;
        Token char_literal39=null;
        Aspect_JavaP.formalParameter_return retparam = null;

        Aspect_JavaP.formalParameter_return throwabletype = null;


        Object rt_tree=null;
        Object tw_tree=null;
        Object char_literal36_tree=null;
        Object char_literal37_tree=null;
        Object char_literal38_tree=null;
        Object char_literal39_tree=null;

        FormalParameter fp = null; FormalParameter exceptionParam = null;
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 425) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:478:2: (rt= 'returning' ( '(' (retparam= formalParameter )? ')' )? | tw= 'throwing' ( '(' (throwabletype= formalParameter )? ')' )? )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==129) ) {
                alt27=1;
            }
            else if ( (LA27_0==130) ) {
                alt27=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:478:4: rt= 'returning' ( '(' (retparam= formalParameter )? ')' )?
                    {
                    root_0 = (Object)adaptor.nil();

                    rt=(Token)match(input,129,FOLLOW_129_in_adviceTypeModifierSpecifier1334); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    rt_tree = (Object)adaptor.create(rt);
                    adaptor.addChild(root_0, rt_tree);
                    }
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:478:19: ( '(' (retparam= formalParameter )? ')' )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==67) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:478:20: '(' (retparam= formalParameter )? ')'
                            {
                            char_literal36=(Token)match(input,67,FOLLOW_67_in_adviceTypeModifierSpecifier1337); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal36_tree = (Object)adaptor.create(char_literal36);
                            adaptor.addChild(root_0, char_literal36_tree);
                            }
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:478:24: (retparam= formalParameter )?
                            int alt23=2;
                            int LA23_0 = input.LA(1);

                            if ( (LA23_0==Identifier||LA23_0==36||(LA23_0>=57 && LA23_0<=64)||LA23_0==74) ) {
                                alt23=1;
                            }
                            switch (alt23) {
                                case 1 :
                                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:478:25: retparam= formalParameter
                                    {
                                    pushFollow(FOLLOW_formalParameter_in_adviceTypeModifierSpecifier1342);
                                    retparam=formalParameter();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, retparam.getTree());
                                    if ( state.backtracking==0 ) {
                                      fp = retparam.element;
                                    }

                                    }
                                    break;

                            }

                            char_literal37=(Token)match(input,68,FOLLOW_68_in_adviceTypeModifierSpecifier1348); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal37_tree = (Object)adaptor.create(char_literal37);
                            adaptor.addChild(root_0, char_literal37_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                       Returning result = new Returning(); result.setReturnParameter(fp); retval.element = result; setKeyword(retval.element, rt); 
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:479:4: tw= 'throwing' ( '(' (throwabletype= formalParameter )? ')' )?
                    {
                    root_0 = (Object)adaptor.nil();

                    tw=(Token)match(input,130,FOLLOW_130_in_adviceTypeModifierSpecifier1359); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    tw_tree = (Object)adaptor.create(tw);
                    adaptor.addChild(root_0, tw_tree);
                    }
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:479:18: ( '(' (throwabletype= formalParameter )? ')' )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==67) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:479:19: '(' (throwabletype= formalParameter )? ')'
                            {
                            char_literal38=(Token)match(input,67,FOLLOW_67_in_adviceTypeModifierSpecifier1362); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal38_tree = (Object)adaptor.create(char_literal38);
                            adaptor.addChild(root_0, char_literal38_tree);
                            }
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:479:23: (throwabletype= formalParameter )?
                            int alt25=2;
                            int LA25_0 = input.LA(1);

                            if ( (LA25_0==Identifier||LA25_0==36||(LA25_0>=57 && LA25_0<=64)||LA25_0==74) ) {
                                alt25=1;
                            }
                            switch (alt25) {
                                case 1 :
                                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:479:24: throwabletype= formalParameter
                                    {
                                    pushFollow(FOLLOW_formalParameter_in_adviceTypeModifierSpecifier1367);
                                    throwabletype=formalParameter();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, throwabletype.getTree());
                                    if ( state.backtracking==0 ) {
                                      exceptionParam = throwabletype.element;
                                    }

                                    }
                                    break;

                            }

                            char_literal39=(Token)match(input,68,FOLLOW_68_in_adviceTypeModifierSpecifier1373); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal39_tree = (Object)adaptor.create(char_literal39);
                            adaptor.addChild(root_0, char_literal39_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                       Throwing result = new Throwing(); result.setExceptionParameter(exceptionParam); retval.element = result; setKeyword(retval.element, tw); 
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 425, adviceTypeModifierSpecifier_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "adviceTypeModifierSpecifier"

    public static class methodReference_return extends ParserRuleReturnScope {
        public MethodReference element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "methodReference"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:482:1: methodReference returns [MethodReference element] : (t= typeOrVoid | twc= IdentifierWithWC ) name= fqn ;
    public final AspectParser.methodReference_return methodReference() throws RecognitionException {
        AspectParser.methodReference_return retval = new AspectParser.methodReference_return();
        retval.start = input.LT(1);
        int methodReference_StartIndex = input.index();
        Object root_0 = null;

        Token twc=null;
        AspectParser.typeOrVoid_return t = null;

        AspectParser.fqn_return name = null;


        Object twc_tree=null;

        JavaTypeReference type = null; String typeWithWC = null;
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 426) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:485:2: ( (t= typeOrVoid | twc= IdentifierWithWC ) name= fqn )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:485:4: (t= typeOrVoid | twc= IdentifierWithWC ) name= fqn
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:485:4: (t= typeOrVoid | twc= IdentifierWithWC )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==Identifier||LA28_0==48||(LA28_0>=57 && LA28_0<=64)) ) {
                alt28=1;
            }
            else if ( (LA28_0==IdentifierWithWC) ) {
                alt28=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:485:5: t= typeOrVoid
                    {
                    pushFollow(FOLLOW_typeOrVoid_in_methodReference1411);
                    t=typeOrVoid();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if ( state.backtracking==0 ) {
                      type = t.element; 
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:485:39: twc= IdentifierWithWC
                    {
                    twc=(Token)match(input,IdentifierWithWC,FOLLOW_IdentifierWithWC_in_methodReference1417); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    twc_tree = (Object)adaptor.create(twc);
                    adaptor.addChild(root_0, twc_tree);
                    }
                    if ( state.backtracking==0 ) {
                      typeWithWC = (twc!=null?twc.getText():null);
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_fqn_in_methodReference1424);
            name=fqn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, name.getTree());
            if ( state.backtracking==0 ) {
              retval.element = new MethodReference(name.element, type, typeWithWC);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 426, methodReference_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "methodReference"

    public static class typeOrVoid_return extends ParserRuleReturnScope {
        public JavaTypeReference element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeOrVoid"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:488:1: typeOrVoid returns [JavaTypeReference element] : (t= type | v= voidType );
    public final AspectParser.typeOrVoid_return typeOrVoid() throws RecognitionException {
        AspectParser.typeOrVoid_return retval = new AspectParser.typeOrVoid_return();
        retval.start = input.LT(1);
        int typeOrVoid_StartIndex = input.index();
        Object root_0 = null;

        Aspect_JavaP.type_return t = null;

        Aspect_JavaP.voidType_return v = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 427) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:489:2: (t= type | v= voidType )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==Identifier||(LA29_0>=57 && LA29_0<=64)) ) {
                alt29=1;
            }
            else if ( (LA29_0==48) ) {
                alt29=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:489:4: t= type
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_type_in_typeOrVoid1444);
                    t=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element=t.element;
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:490:4: v= voidType
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_voidType_in_typeOrVoid1453);
                    v=voidType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, v.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element=v.element;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 427, typeOrVoid_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typeOrVoid"

    public static class fqn_return extends ParserRuleReturnScope {
        public QualifiedMethodHeader element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fqn"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:493:1: fqn returns [QualifiedMethodHeader element] : (id= ( IdentifierWithWC | Identifier ) '.' )* mth= simpleMethodHeader ;
    public final AspectParser.fqn_return fqn() throws RecognitionException {
        AspectParser.fqn_return retval = new AspectParser.fqn_return();
        retval.start = input.LT(1);
        int fqn_StartIndex = input.index();
        Object root_0 = null;

        Token id=null;
        Token char_literal40=null;
        AspectParser.simpleMethodHeader_return mth = null;


        Object id_tree=null;
        Object char_literal40_tree=null;

        CompositeQualifiedName prefixes = new CompositeQualifiedName();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 428) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:496:2: ( (id= ( IdentifierWithWC | Identifier ) '.' )* mth= simpleMethodHeader )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:496:4: (id= ( IdentifierWithWC | Identifier ) '.' )* mth= simpleMethodHeader
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:496:4: (id= ( IdentifierWithWC | Identifier ) '.' )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==Identifier||LA30_0==IdentifierWithWC) ) {
                    int LA30_1 = input.LA(2);

                    if ( (LA30_1==30) ) {
                        alt30=1;
                    }


                }


                switch (alt30) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:496:5: id= ( IdentifierWithWC | Identifier ) '.'
            	    {
            	    id=(Token)input.LT(1);
            	    if ( input.LA(1)==Identifier||input.LA(1)==IdentifierWithWC ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(id));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    char_literal40=(Token)match(input,30,FOLLOW_30_in_fqn1495); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal40_tree = (Object)adaptor.create(char_literal40);
            	    adaptor.addChild(root_0, char_literal40_tree);
            	    }
            	    if ( state.backtracking==0 ) {
            	      prefixes.append(new SimpleNameSignature((id!=null?id.getText():null)));
            	    }

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            pushFollow(FOLLOW_simpleMethodHeader_in_fqn1503);
            mth=simpleMethodHeader();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mth.getTree());
            if ( state.backtracking==0 ) {
              retval.element = new QualifiedMethodHeader(mth.element); retval.element.setPrefixes(prefixes);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 428, fqn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "fqn"

    public static class simpleMethodHeader_return extends ParserRuleReturnScope {
        public SimpleNameDeclarationWithParameterTypesHeader element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "simpleMethodHeader"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:500:1: simpleMethodHeader returns [SimpleNameDeclarationWithParameterTypesHeader element] : name= ( IdentifierWithWC | Identifier ) pars= formalParameterTypes ;
    public final AspectParser.simpleMethodHeader_return simpleMethodHeader() throws RecognitionException {
        AspectParser.simpleMethodHeader_return retval = new AspectParser.simpleMethodHeader_return();
        retval.start = input.LT(1);
        int simpleMethodHeader_StartIndex = input.index();
        Object root_0 = null;

        Token name=null;
        AspectParser.formalParameterTypes_return pars = null;


        Object name_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 429) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:502:9: (name= ( IdentifierWithWC | Identifier ) pars= formalParameterTypes )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:502:11: name= ( IdentifierWithWC | Identifier ) pars= formalParameterTypes
            {
            root_0 = (Object)adaptor.nil();

            name=(Token)input.LT(1);
            if ( input.LA(1)==Identifier||input.LA(1)==IdentifierWithWC ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(name));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            pushFollow(FOLLOW_formalParameterTypes_in_simpleMethodHeader1550);
            pars=formalParameterTypes();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, pars.getTree());
            if ( state.backtracking==0 ) {
              retval.element = new SimpleNameDeclarationWithParameterTypesHeader((name!=null?name.getText():null)); retval.element.addAll(pars.element); 
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              setLocation(retval.element, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 429, simpleMethodHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "simpleMethodHeader"

    public static class typesOrParameters_return extends ParserRuleReturnScope {
        public List<NamedTargetExpression> element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typesOrParameters"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:505:1: typesOrParameters returns [List<NamedTargetExpression> element] : '(' (pars= typesOrParameterDecls )? ')' ;
    public final AspectParser.typesOrParameters_return typesOrParameters() throws RecognitionException {
        AspectParser.typesOrParameters_return retval = new AspectParser.typesOrParameters_return();
        retval.start = input.LT(1);
        int typesOrParameters_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal41=null;
        Token char_literal42=null;
        AspectParser.typesOrParameterDecls_return pars = null;


        Object char_literal41_tree=null;
        Object char_literal42_tree=null;

        retval.element = new ArrayList<NamedTargetExpression>();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 430) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:507:5: ( '(' (pars= typesOrParameterDecls )? ')' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:507:9: '(' (pars= typesOrParameterDecls )? ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal41=(Token)match(input,67,FOLLOW_67_in_typesOrParameters1592); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal41_tree = (Object)adaptor.create(char_literal41);
            adaptor.addChild(root_0, char_literal41_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:507:13: (pars= typesOrParameterDecls )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==Identifier||(LA31_0>=FloatingPointLiteral && LA31_0<=DecimalLiteral)||LA31_0==48||(LA31_0>=57 && LA31_0<=64)||(LA31_0>=66 && LA31_0<=67)||(LA31_0>=70 && LA31_0<=73)||(LA31_0>=106 && LA31_0<=107)||(LA31_0>=110 && LA31_0<=114)||LA31_0==131) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:507:14: pars= typesOrParameterDecls
                    {
                    pushFollow(FOLLOW_typesOrParameterDecls_in_typesOrParameters1597);
                    pars=typesOrParameterDecls();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pars.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element=pars.element;
                    }

                    }
                    break;

            }

            char_literal42=(Token)match(input,68,FOLLOW_68_in_typesOrParameters1603); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal42_tree = (Object)adaptor.create(char_literal42);
            adaptor.addChild(root_0, char_literal42_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 430, typesOrParameters_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typesOrParameters"

    public static class typesOrParameterDecls_return extends ParserRuleReturnScope {
        public List<NamedTargetExpression> element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typesOrParameterDecls"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:510:1: typesOrParameterDecls returns [List<NamedTargetExpression> element] : exp= expression ( ',' decls= typesOrParameterDecls )? ;
    public final AspectParser.typesOrParameterDecls_return typesOrParameterDecls() throws RecognitionException {
        AspectParser.typesOrParameterDecls_return retval = new AspectParser.typesOrParameterDecls_return();
        retval.start = input.LT(1);
        int typesOrParameterDecls_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal43=null;
        AspectParser.expression_return exp = null;

        AspectParser.typesOrParameterDecls_return decls = null;


        Object char_literal43_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 431) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:511:5: (exp= expression ( ',' decls= typesOrParameterDecls )? )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:511:7: exp= expression ( ',' decls= typesOrParameterDecls )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_expression_in_typesOrParameterDecls1626);
            exp=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:511:22: ( ',' decls= typesOrParameterDecls )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==42) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:511:23: ',' decls= typesOrParameterDecls
                    {
                    char_literal43=(Token)match(input,42,FOLLOW_42_in_typesOrParameterDecls1629); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal43_tree = (Object)adaptor.create(char_literal43);
                    adaptor.addChild(root_0, char_literal43_tree);
                    }
                    pushFollow(FOLLOW_typesOrParameterDecls_in_typesOrParameterDecls1633);
                    decls=typesOrParameterDecls();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, decls.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element=decls.element; 
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              if(retval.element == null) {
              	 	retval.element=new ArrayList<NamedTargetExpression>();
                       } 
                       retval.element.add(0, (NamedTargetExpression) exp.element);
                       
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 431, typesOrParameterDecls_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "typesOrParameterDecls"

    public static class formalParameterTypes_return extends ParserRuleReturnScope {
        public List<TypeReference> element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "formalParameterTypes"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:519:1: formalParameterTypes returns [List<TypeReference> element] : '(' (pars= formalParameterTypeDecls )? ')' ;
    public final AspectParser.formalParameterTypes_return formalParameterTypes() throws RecognitionException {
        AspectParser.formalParameterTypes_return retval = new AspectParser.formalParameterTypes_return();
        retval.start = input.LT(1);
        int formalParameterTypes_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal44=null;
        Token char_literal45=null;
        AspectParser.formalParameterTypeDecls_return pars = null;


        Object char_literal44_tree=null;
        Object char_literal45_tree=null;

        retval.element = new ArrayList<TypeReference>();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 432) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:521:5: ( '(' (pars= formalParameterTypeDecls )? ')' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:521:9: '(' (pars= formalParameterTypeDecls )? ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal44=(Token)match(input,67,FOLLOW_67_in_formalParameterTypes1680); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal44_tree = (Object)adaptor.create(char_literal44);
            adaptor.addChild(root_0, char_literal44_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:521:13: (pars= formalParameterTypeDecls )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==Identifier||(LA33_0>=57 && LA33_0<=64)) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:521:14: pars= formalParameterTypeDecls
                    {
                    pushFollow(FOLLOW_formalParameterTypeDecls_in_formalParameterTypes1685);
                    pars=formalParameterTypeDecls();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pars.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element=pars.element;
                    }

                    }
                    break;

            }

            char_literal45=(Token)match(input,68,FOLLOW_68_in_formalParameterTypes1691); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal45_tree = (Object)adaptor.create(char_literal45);
            adaptor.addChild(root_0, char_literal45_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 432, formalParameterTypes_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "formalParameterTypes"

    public static class formalParameterTypeDecls_return extends ParserRuleReturnScope {
        public List<TypeReference> element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "formalParameterTypeDecls"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:524:1: formalParameterTypeDecls returns [List<TypeReference> element] : t= type ( ',' decls= formalParameterTypeDecls )? ;
    public final AspectParser.formalParameterTypeDecls_return formalParameterTypeDecls() throws RecognitionException {
        AspectParser.formalParameterTypeDecls_return retval = new AspectParser.formalParameterTypeDecls_return();
        retval.start = input.LT(1);
        int formalParameterTypeDecls_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal46=null;
        Aspect_JavaP.type_return t = null;

        AspectParser.formalParameterTypeDecls_return decls = null;


        Object char_literal46_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 433) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:525:5: (t= type ( ',' decls= formalParameterTypeDecls )? )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:525:9: t= type ( ',' decls= formalParameterTypeDecls )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_type_in_formalParameterTypeDecls1716);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:525:16: ( ',' decls= formalParameterTypeDecls )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==42) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:525:17: ',' decls= formalParameterTypeDecls
                    {
                    char_literal46=(Token)match(input,42,FOLLOW_42_in_formalParameterTypeDecls1719); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal46_tree = (Object)adaptor.create(char_literal46);
                    adaptor.addChild(root_0, char_literal46_tree);
                    }
                    pushFollow(FOLLOW_formalParameterTypeDecls_in_formalParameterTypeDecls1723);
                    decls=formalParameterTypeDecls();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, decls.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element=decls.element; 
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              if(retval.element == null) {
                       retval.element=new ArrayList<TypeReference>();}
                       retval.element.add(0, t.element);
                       
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 433, formalParameterTypeDecls_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "formalParameterTypeDecls"

    public static class expression_return extends ParserRuleReturnScope {
        public Expression element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:533:1: expression returns [Expression element] : (ex= conditionalExpression (op= assignmentOperator exx= expression )? | prcd= 'proceed' args= arguments );
    public final AspectParser.expression_return expression() throws RecognitionException {
        AspectParser.expression_return retval = new AspectParser.expression_return();
        retval.start = input.LT(1);
        int expression_StartIndex = input.index();
        Object root_0 = null;

        Token prcd=null;
        Aspect_JavaP.conditionalExpression_return ex = null;

        Aspect_JavaP.assignmentOperator_return op = null;

        AspectParser.expression_return exx = null;

        Aspect_JavaP.arguments_return args = null;


        Object prcd_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 434) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:534:5: (ex= conditionalExpression (op= assignmentOperator exx= expression )? | prcd= 'proceed' args= arguments )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==Identifier||(LA36_0>=FloatingPointLiteral && LA36_0<=DecimalLiteral)||LA36_0==48||(LA36_0>=57 && LA36_0<=64)||(LA36_0>=66 && LA36_0<=67)||(LA36_0>=70 && LA36_0<=73)||(LA36_0>=106 && LA36_0<=107)||(LA36_0>=110 && LA36_0<=114)) ) {
                alt36=1;
            }
            else if ( (LA36_0==131) ) {
                alt36=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:534:9: ex= conditionalExpression (op= assignmentOperator exx= expression )?
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_conditionalExpression_in_expression1767);
                    ex=conditionalExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ex.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element=ex.element;
                    }
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:534:63: (op= assignmentOperator exx= expression )?
                    int alt35=2;
                    alt35 = dfa35.predict(input);
                    switch (alt35) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:534:64: op= assignmentOperator exx= expression
                            {
                            pushFollow(FOLLOW_assignmentOperator_in_expression1774);
                            op=assignmentOperator();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, op.getTree());
                            pushFollow(FOLLOW_expression_in_expression1778);
                            exx=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, exx.getTree());
                            if ( state.backtracking==0 ) {
                              String txt = (op!=null?input.toString(op.start,op.stop):null); 
                                       if(txt.equals("=")) {
                                         retval.element = new AssignmentExpression(ex.element,exx.element);
                                       } else {
                                         retval.element = new InfixOperatorInvocation((op!=null?input.toString(op.start,op.stop):null),ex.element);
                                         ((InfixOperatorInvocation)retval.element).addArgument(exx.element);
                                       }
                                       setLocation(retval.element,op.start,op.stop,"__NAME");
                                       setLocation(retval.element,retval.start,exx.stop);
                                      
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:546:11: prcd= 'proceed' args= arguments
                    {
                    root_0 = (Object)adaptor.nil();

                    prcd=(Token)match(input,131,FOLLOW_131_in_expression1814); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    prcd_tree = (Object)adaptor.create(prcd);
                    adaptor.addChild(root_0, prcd_tree);
                    }
                    pushFollow(FOLLOW_arguments_in_expression1818);
                    args=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, args.getTree());
                    if ( state.backtracking==0 ) {

                                ProceedCall result = new ProceedCall();
                                result.addAllArguments(args.element);
                                retval.element = result;
                                setKeyword(retval.element, prcd);
                                setLocation(retval.element,prcd,prcd);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 434, expression_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "expression"

    // $ANTLR start synpred5_Aspect
    public final void synpred5_Aspect_fragment() throws RecognitionException {   
        Aspect_JavaP.packageDeclaration_return np = null;

        Aspect_JavaP.importDeclaration_return imp = null;

        Aspect_JavaP.typeDeclaration_return typech = null;

        Aspect_JavaP.classOrInterfaceDeclaration_return cd = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:294:10: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:294:10: annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
        {
        pushFollow(FOLLOW_annotations_in_synpred5_Aspect80);
        annotations();

        state._fsp--;
        if (state.failed) return ;
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:295:9: (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
        int alt42=2;
        int LA42_0 = input.LA(1);

        if ( (LA42_0==26) ) {
            alt42=1;
        }
        else if ( (LA42_0==ENUM||LA42_0==29||(LA42_0>=32 && LA42_0<=38)||LA42_0==47||LA42_0==74) ) {
            alt42=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 42, 0, input);

            throw nvae;
        }
        switch (alt42) {
            case 1 :
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:295:13: np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )*
                {
                pushFollow(FOLLOW_packageDeclaration_in_synpred5_Aspect96);
                np=packageDeclaration();

                state._fsp--;
                if (state.failed) return ;
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:300:13: (imp= importDeclaration )*
                loop39:
                do {
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==28) ) {
                        alt39=1;
                    }


                    switch (alt39) {
                	case 1 :
                	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:300:14: imp= importDeclaration
                	    {
                	    pushFollow(FOLLOW_importDeclaration_in_synpred5_Aspect132);
                	    imp=importDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop39;
                    }
                } while (true);

                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:301:13: (typech= typeDeclaration )*
                loop40:
                do {
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==ENUM||LA40_0==27||LA40_0==29||(LA40_0>=32 && LA40_0<=38)||LA40_0==47||LA40_0==74) ) {
                        alt40=1;
                    }


                    switch (alt40) {
                	case 1 :
                	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:301:14: typech= typeDeclaration
                	    {
                	    pushFollow(FOLLOW_typeDeclaration_in_synpred5_Aspect153);
                	    typech=typeDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop40;
                    }
                } while (true);


                }
                break;
            case 2 :
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:305:13: cd= classOrInterfaceDeclaration (typech= typeDeclaration )*
                {
                pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred5_Aspect202);
                cd=classOrInterfaceDeclaration();

                state._fsp--;
                if (state.failed) return ;
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:311:13: (typech= typeDeclaration )*
                loop41:
                do {
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==ENUM||LA41_0==27||LA41_0==29||(LA41_0>=32 && LA41_0<=38)||LA41_0==47||LA41_0==74) ) {
                        alt41=1;
                    }


                    switch (alt41) {
                	case 1 :
                	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:311:14: typech= typeDeclaration
                	    {
                	    pushFollow(FOLLOW_typeDeclaration_in_synpred5_Aspect237);
                	    typech=typeDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop41;
                    }
                } while (true);


                }
                break;

        }


        }
    }
    // $ANTLR end synpred5_Aspect

    // $ANTLR start synpred12_Aspect
    public final void synpred12_Aspect_fragment() throws RecognitionException {   
        AspectParser.pointcutExpressionOr_return expr1 = null;

        AspectParser.pointcutExpression_return expr2 = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:374:4: (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:374:4: expr1= pointcutExpressionOr '&&' expr2= pointcutExpression
        {
        pushFollow(FOLLOW_pointcutExpressionOr_in_synpred12_Aspect589);
        expr1=pointcutExpressionOr();

        state._fsp--;
        if (state.failed) return ;
        match(input,100,FOLLOW_100_in_synpred12_Aspect591); if (state.failed) return ;
        pushFollow(FOLLOW_pointcutExpression_in_synpred12_Aspect595);
        expr2=pointcutExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_Aspect

    // $ANTLR start synpred13_Aspect
    public final void synpred13_Aspect_fragment() throws RecognitionException {   
        AspectParser.pointcutAtom_return expr1 = null;

        AspectParser.pointcutExpressionOr_return expr2 = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:380:4: (expr1= pointcutAtom '||' expr2= pointcutExpressionOr )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:380:4: expr1= pointcutAtom '||' expr2= pointcutExpressionOr
        {
        pushFollow(FOLLOW_pointcutAtom_in_synpred13_Aspect627);
        expr1=pointcutAtom();

        state._fsp--;
        if (state.failed) return ;
        match(input,99,FOLLOW_99_in_synpred13_Aspect629); if (state.failed) return ;
        pushFollow(FOLLOW_pointcutExpressionOr_in_synpred13_Aspect633);
        expr2=pointcutExpressionOr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_Aspect

    // $ANTLR start synpred34_Aspect
    public final void synpred34_Aspect_fragment() throws RecognitionException {   
        Aspect_JavaP.localVariableDeclarationStatement_return local = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:454:9: (local= localVariableDeclarationStatement )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:454:9: local= localVariableDeclarationStatement
        {
        pushFollow(FOLLOW_localVariableDeclarationStatement_in_synpred34_Aspect1166);
        local=localVariableDeclarationStatement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred34_Aspect

    // $ANTLR start synpred35_Aspect
    public final void synpred35_Aspect_fragment() throws RecognitionException {   
        Aspect_JavaP.classOrInterfaceDeclaration_return cd = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:455:9: (cd= classOrInterfaceDeclaration )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:455:9: cd= classOrInterfaceDeclaration
        {
        pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred35_Aspect1180);
        cd=classOrInterfaceDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred35_Aspect

    // $ANTLR start synpred36_Aspect
    public final void synpred36_Aspect_fragment() throws RecognitionException {   
        AspectParser.adviceReturnStatement_return specialReturn = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:456:7: (specialReturn= adviceReturnStatement )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:456:7: specialReturn= adviceReturnStatement
        {
        pushFollow(FOLLOW_adviceReturnStatement_in_synpred36_Aspect1192);
        specialReturn=adviceReturnStatement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred36_Aspect

    // $ANTLR start synpred54_Aspect
    public final void synpred54_Aspect_fragment() throws RecognitionException {   
        Aspect_JavaP.assignmentOperator_return op = null;

        AspectParser.expression_return exx = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:534:64: (op= assignmentOperator exx= expression )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:534:64: op= assignmentOperator exx= expression
        {
        pushFollow(FOLLOW_assignmentOperator_in_synpred54_Aspect1774);
        op=assignmentOperator();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred54_Aspect1778);
        exx=expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred54_Aspect

    // Delegated rules
    public Aspect_JavaP.nameAndParams_return nameAndParams() throws RecognitionException { return gJavaP.nameAndParams(); }
    public Aspect_JavaP.superSuffix_return superSuffix() throws RecognitionException { return gJavaP.superSuffix(); }
    public Aspect_JavaP.voidMethodDeclaration_return voidMethodDeclaration() throws RecognitionException { return gJavaP.voidMethodDeclaration(); }
    public Aspect_JavaP.qualifiedName_return qualifiedName() throws RecognitionException { return gJavaP.qualifiedName(); }
    public Aspect_JavaP.voidInterfaceMethodDeclaratorRest_return voidInterfaceMethodDeclaratorRest() throws RecognitionException { return gJavaP.voidInterfaceMethodDeclaratorRest(); }
    public Aspect_JavaP.arrayInitializer_return arrayInitializer() throws RecognitionException { return gJavaP.arrayInitializer(); }
    public Aspect_JavaP.annotationMethodOrConstantRest_return annotationMethodOrConstantRest(TypeReference type) throws RecognitionException { return gJavaP.annotationMethodOrConstantRest(type); }
    public Aspect_JavaP.explicitConstructorInvocation_return explicitConstructorInvocation() throws RecognitionException { return gJavaP.explicitConstructorInvocation(); }
    public Aspect_JavaP.variableModifiers_return variableModifiers() throws RecognitionException { return gJavaP.variableModifiers(); }
    public Aspect_JavaP.memberDeclaration_return memberDeclaration() throws RecognitionException { return gJavaP.memberDeclaration(); }
    public Aspect_JavaP.block_return block() throws RecognitionException { return gJavaP.block(); }
    public Aspect_JavaP.classCreatorRest_return classCreatorRest() throws RecognitionException { return gJavaP.classCreatorRest(); }
    public Aspect_JavaP.statementExpression_return statementExpression() throws RecognitionException { return gJavaP.statementExpression(); }
    public Aspect_JavaP.elementValuePair_return elementValuePair() throws RecognitionException { return gJavaP.elementValuePair(); }
    public Aspect_JavaP.classBodyDeclaration_return classBodyDeclaration() throws RecognitionException { return gJavaP.classBodyDeclaration(); }
    public Aspect_JavaP.genericMethodOrConstructorRest_return genericMethodOrConstructorRest() throws RecognitionException { return gJavaP.genericMethodOrConstructorRest(); }
    public Aspect_JavaP.annotationTypeDeclaration_return annotationTypeDeclaration() throws RecognitionException { return gJavaP.annotationTypeDeclaration(); }
    public Aspect_JavaP.normalClassDeclaration_return normalClassDeclaration() throws RecognitionException { return gJavaP.normalClassDeclaration(); }
    public Aspect_JavaP.relationalExpression_return relationalExpression() throws RecognitionException { return gJavaP.relationalExpression(); }
    public Aspect_JavaP.creator_return creator() throws RecognitionException { return gJavaP.creator(); }
    public Aspect_JavaP.interfaceMethod_return interfaceMethod() throws RecognitionException { return gJavaP.interfaceMethod(); }
    public Aspect_JavaP.variableDeclaratorId_return variableDeclaratorId() throws RecognitionException { return gJavaP.variableDeclaratorId(); }
    public Aspect_JavaP.importDeclaration_return importDeclaration() throws RecognitionException { return gJavaP.importDeclaration(); }
    public Aspect_JavaP.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus() throws RecognitionException { return gJavaP.unaryExpressionNotPlusMinus(); }
    public Aspect_JavaP.constructorDeclaration_return constructorDeclaration() throws RecognitionException { return gJavaP.constructorDeclaration(); }
    public Aspect_JavaP.castExpression_return castExpression() throws RecognitionException { return gJavaP.castExpression(); }
    public Aspect_JavaP.innerCreator_return innerCreator() throws RecognitionException { return gJavaP.innerCreator(); }
    public Aspect_JavaP.interfaceConstant_return interfaceConstant() throws RecognitionException { return gJavaP.interfaceConstant(); }
    public Aspect_JavaP.methodBody_return methodBody() throws RecognitionException { return gJavaP.methodBody(); }
    public Aspect_JavaP.variableDeclarators_return variableDeclarators() throws RecognitionException { return gJavaP.variableDeclarators(); }
    public Aspect_JavaP.annotationName_return annotationName() throws RecognitionException { return gJavaP.annotationName(); }
    public Aspect_JavaP.constructorBody_return constructorBody() throws RecognitionException { return gJavaP.constructorBody(); }
    public Aspect_JavaP.primary_return primary() throws RecognitionException { return gJavaP.primary(); }
    public Aspect_JavaP.interfaceBody_return interfaceBody() throws RecognitionException { return gJavaP.interfaceBody(); }
    public Aspect_JavaP.variableDeclarator_return variableDeclarator() throws RecognitionException { return gJavaP.variableDeclarator(); }
    public Aspect_JavaP.conditionalOrExpression_return conditionalOrExpression() throws RecognitionException { return gJavaP.conditionalOrExpression(); }
    public Aspect_JavaP.literal_return literal() throws RecognitionException { return gJavaP.literal(); }
    public Aspect_JavaP.constantExpression_return constantExpression() throws RecognitionException { return gJavaP.constantExpression(); }
    public Aspect_JavaP.defaultValue_return defaultValue() throws RecognitionException { return gJavaP.defaultValue(); }
    public Aspect_JavaP.formalParameterDecls_return formalParameterDecls() throws RecognitionException { return gJavaP.formalParameterDecls(); }
    public Aspect_JavaP.parExpression_return parExpression() throws RecognitionException { return gJavaP.parExpression(); }
    public Aspect_JavaP.switchCase_return switchCase() throws RecognitionException { return gJavaP.switchCase(); }
    public Aspect_JavaP.identifierSuffixRubbush_return identifierSuffixRubbush() throws RecognitionException { return gJavaP.identifierSuffixRubbush(); }
    public Aspect_JavaP.typeParameters_return typeParameters() throws RecognitionException { return gJavaP.typeParameters(); }
    public Aspect_JavaP.andExpression_return andExpression() throws RecognitionException { return gJavaP.andExpression(); }
    public Aspect_JavaP.statement_return statement() throws RecognitionException { return gJavaP.statement(); }
    public Aspect_JavaP.annotationMethodRest_return annotationMethodRest(TypeReference type) throws RecognitionException { return gJavaP.annotationMethodRest(type); }
    public Aspect_JavaP.methodDeclaration_return methodDeclaration() throws RecognitionException { return gJavaP.methodDeclaration(); }
    public Aspect_JavaP.typeList_return typeList() throws RecognitionException { return gJavaP.typeList(); }
    public Aspect_JavaP.variableInitializer_return variableInitializer() throws RecognitionException { return gJavaP.variableInitializer(); }
    public Aspect_JavaP.interfaceMethodDeclaratorRest_return interfaceMethodDeclaratorRest() throws RecognitionException { return gJavaP.interfaceMethodDeclaratorRest(); }
    public Aspect_JavaP.equalityExpression_return equalityExpression() throws RecognitionException { return gJavaP.equalityExpression(); }
    public Aspect_JavaP.nonTargetPrimary_return nonTargetPrimary() throws RecognitionException { return gJavaP.nonTargetPrimary(); }
    public Aspect_JavaP.unaryExpression_return unaryExpression() throws RecognitionException { return gJavaP.unaryExpression(); }
    public Aspect_JavaP.formalParameter_return formalParameter() throws RecognitionException { return gJavaP.formalParameter(); }
    public Aspect_JavaP.enumBodyDeclarations_return enumBodyDeclarations() throws RecognitionException { return gJavaP.enumBodyDeclarations(); }
    public Aspect_JavaP.shiftOp_return shiftOp() throws RecognitionException { return gJavaP.shiftOp(); }
    public Aspect_JavaP.interfaceBodyDeclaration_return interfaceBodyDeclaration() throws RecognitionException { return gJavaP.interfaceBodyDeclaration(); }
    public Aspect_JavaP.catches_return catches() throws RecognitionException { return gJavaP.catches(); }
    public Aspect_JavaP.annotation_return annotation() throws RecognitionException { return gJavaP.annotation(); }
    public Aspect_JavaP.argumentsSuffixRubbish_return argumentsSuffixRubbish() throws RecognitionException { return gJavaP.argumentsSuffixRubbish(); }
    public Aspect_JavaP.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException { return gJavaP.multiplicativeExpression(); }
    public Aspect_JavaP.methodDeclaratorRest_return methodDeclaratorRest() throws RecognitionException { return gJavaP.methodDeclaratorRest(); }
    public Aspect_JavaP.inclusiveOrExpression_return inclusiveOrExpression() throws RecognitionException { return gJavaP.inclusiveOrExpression(); }
    public Aspect_JavaP.packageDeclaration_return packageDeclaration() throws RecognitionException { return gJavaP.packageDeclaration(); }
    public Aspect_JavaP.forInit_return forInit() throws RecognitionException { return gJavaP.forInit(); }
    public Aspect_JavaP.selector_return selector() throws RecognitionException { return gJavaP.selector(); }
    public Aspect_JavaP.enumConstantName_return enumConstantName() throws RecognitionException { return gJavaP.enumConstantName(); }
    public Aspect_JavaP.localVariableDeclarationStatement_return localVariableDeclarationStatement() throws RecognitionException { return gJavaP.localVariableDeclarationStatement(); }
    public Aspect_JavaP.elementValueArrayInitializer_return elementValueArrayInitializer() throws RecognitionException { return gJavaP.elementValueArrayInitializer(); }
    public Aspect_JavaP.formalParameters_return formalParameters() throws RecognitionException { return gJavaP.formalParameters(); }
    public Aspect_JavaP.modifiers_return modifiers() throws RecognitionException { return gJavaP.modifiers(); }
    public Aspect_JavaP.arrayAccessSuffixRubbish_return arrayAccessSuffixRubbish() throws RecognitionException { return gJavaP.arrayAccessSuffixRubbish(); }
    public Aspect_JavaP.normalInterfaceDeclaration_return normalInterfaceDeclaration() throws RecognitionException { return gJavaP.normalInterfaceDeclaration(); }
    public Aspect_JavaP.annotationTypeElementRest_return annotationTypeElementRest() throws RecognitionException { return gJavaP.annotationTypeElementRest(); }
    public Aspect_JavaP.voidInterfaceMethodDeclaration_return voidInterfaceMethodDeclaration() throws RecognitionException { return gJavaP.voidInterfaceMethodDeclaration(); }
    public Aspect_JavaP.elementValuePairs_return elementValuePairs() throws RecognitionException { return gJavaP.elementValuePairs(); }
    public Aspect_JavaP.moreIdentifierSuffixRubbish_return moreIdentifierSuffixRubbish() throws RecognitionException { return gJavaP.moreIdentifierSuffixRubbish(); }
    public Aspect_JavaP.classOrInterfaceDeclaration_return classOrInterfaceDeclaration() throws RecognitionException { return gJavaP.classOrInterfaceDeclaration(); }
    public Aspect_JavaP.enhancedForControl_return enhancedForControl() throws RecognitionException { return gJavaP.enhancedForControl(); }
    public Aspect_JavaP.createClassHereBecauseANTLRisAnnoying_return createClassHereBecauseANTLRisAnnoying() throws RecognitionException { return gJavaP.createClassHereBecauseANTLRisAnnoying(); }
    public Aspect_JavaP.classOrInterfaceModifiers_return classOrInterfaceModifiers() throws RecognitionException { return gJavaP.classOrInterfaceModifiers(); }
    public Aspect_JavaP.typeArguments_return typeArguments() throws RecognitionException { return gJavaP.typeArguments(); }
    public Aspect_JavaP.annotationTypeBody_return annotationTypeBody() throws RecognitionException { return gJavaP.annotationTypeBody(); }
    public Aspect_JavaP.classOrInterfaceModifier_return classOrInterfaceModifier() throws RecognitionException { return gJavaP.classOrInterfaceModifier(); }
    public Aspect_JavaP.nonWildcardTypeArguments_return nonWildcardTypeArguments() throws RecognitionException { return gJavaP.nonWildcardTypeArguments(); }
    public Aspect_JavaP.enumConstant_return enumConstant() throws RecognitionException { return gJavaP.enumConstant(); }
    public Aspect_JavaP.instanceOfExpression_return instanceOfExpression() throws RecognitionException { return gJavaP.instanceOfExpression(); }
    public Aspect_JavaP.localVariableDeclaration_return localVariableDeclaration() throws RecognitionException { return gJavaP.localVariableDeclaration(); }
    public Aspect_JavaP.annotationTypeElementDeclaration_return annotationTypeElementDeclaration() throws RecognitionException { return gJavaP.annotationTypeElementDeclaration(); }
    public Aspect_JavaP.qualifiedNameList_return qualifiedNameList() throws RecognitionException { return gJavaP.qualifiedNameList(); }
    public Aspect_JavaP.blockStatement_return blockStatement() throws RecognitionException { return gJavaP.blockStatement(); }
    public Aspect_JavaP.modifier_return modifier() throws RecognitionException { return gJavaP.modifier(); }
    public Aspect_JavaP.additiveExpression_return additiveExpression() throws RecognitionException { return gJavaP.additiveExpression(); }
    public Aspect_JavaP.explicitGenericInvocation_return explicitGenericInvocation() throws RecognitionException { return gJavaP.explicitGenericInvocation(); }
    public Aspect_JavaP.classOrInterfaceType_return classOrInterfaceType() throws RecognitionException { return gJavaP.classOrInterfaceType(); }
    public Aspect_JavaP.constructorDeclaratorRest_return constructorDeclaratorRest() throws RecognitionException { return gJavaP.constructorDeclaratorRest(); }
    public Aspect_JavaP.relationalOp_return relationalOp() throws RecognitionException { return gJavaP.relationalOp(); }
    public Aspect_JavaP.shiftExpression_return shiftExpression() throws RecognitionException { return gJavaP.shiftExpression(); }
    public Aspect_JavaP.annotations_return annotations() throws RecognitionException { return gJavaP.annotations(); }
    public Aspect_JavaP.typeDeclaration_return typeDeclaration() throws RecognitionException { return gJavaP.typeDeclaration(); }
    public Aspect_JavaP.interfaceMemberDecl_return interfaceMemberDecl() throws RecognitionException { return gJavaP.interfaceMemberDecl(); }
    public Aspect_JavaP.voidType_return voidType() throws RecognitionException { return gJavaP.voidType(); }
    public Aspect_JavaP.fieldDeclaration_return fieldDeclaration() throws RecognitionException { return gJavaP.fieldDeclaration(); }
    public Aspect_JavaP.voidMethodDeclaratorRest_return voidMethodDeclaratorRest() throws RecognitionException { return gJavaP.voidMethodDeclaratorRest(); }
    public Aspect_JavaP.variableModifier_return variableModifier() throws RecognitionException { return gJavaP.variableModifier(); }
    public Aspect_JavaP.genericMethodOrConstructorDecl_return genericMethodOrConstructorDecl() throws RecognitionException { return gJavaP.genericMethodOrConstructorDecl(); }
    public Aspect_JavaP.catchClause_return catchClause() throws RecognitionException { return gJavaP.catchClause(); }
    public Aspect_JavaP.typeArgument_return typeArgument() throws RecognitionException { return gJavaP.typeArgument(); }
    public Aspect_JavaP.annotationConstantRest_return annotationConstantRest(TypeReference type) throws RecognitionException { return gJavaP.annotationConstantRest(type); }
    public Aspect_JavaP.enumConstants_return enumConstants() throws RecognitionException { return gJavaP.enumConstants(); }
    public Aspect_JavaP.typeParameter_return typeParameter() throws RecognitionException { return gJavaP.typeParameter(); }
    public Aspect_JavaP.elementValue_return elementValue() throws RecognitionException { return gJavaP.elementValue(); }
    public Aspect_JavaP.createdName_return createdName() throws RecognitionException { return gJavaP.createdName(); }
    public Aspect_JavaP.memberDecl_return memberDecl() throws RecognitionException { return gJavaP.memberDecl(); }
    public Aspect_JavaP.enumDeclaration_return enumDeclaration() throws RecognitionException { return gJavaP.enumDeclaration(); }
    public Aspect_JavaP.constantDeclarator_return constantDeclarator() throws RecognitionException { return gJavaP.constantDeclarator(); }
    public Aspect_JavaP.assignmentOperator_return assignmentOperator() throws RecognitionException { return gJavaP.assignmentOperator(); }
    public Aspect_JavaP.classDeclaration_return classDeclaration() throws RecognitionException { return gJavaP.classDeclaration(); }
    public Aspect_JavaP.conditionalAndExpression_return conditionalAndExpression() throws RecognitionException { return gJavaP.conditionalAndExpression(); }
    public Aspect_JavaP.typeName_return typeName() throws RecognitionException { return gJavaP.typeName(); }
    public Aspect_JavaP.primitiveType_return primitiveType() throws RecognitionException { return gJavaP.primitiveType(); }
    public Aspect_JavaP.typeBound_return typeBound() throws RecognitionException { return gJavaP.typeBound(); }
    public Aspect_JavaP.switchBlockStatementGroups_return switchBlockStatementGroups() throws RecognitionException { return gJavaP.switchBlockStatementGroups(); }
    public Aspect_JavaP.classBody_return classBody() throws RecognitionException { return gJavaP.classBody(); }
    public Aspect_JavaP.switchLabel_return switchLabel() throws RecognitionException { return gJavaP.switchLabel(); }
    public Aspect_JavaP.conditionalExpression_return conditionalExpression() throws RecognitionException { return gJavaP.conditionalExpression(); }
    public Aspect_JavaP.booleanLiteral_return booleanLiteral() throws RecognitionException { return gJavaP.booleanLiteral(); }
    public Aspect_JavaP.integerLiteral_return integerLiteral() throws RecognitionException { return gJavaP.integerLiteral(); }
    public Aspect_JavaP.forControl_return forControl() throws RecognitionException { return gJavaP.forControl(); }
    public Aspect_JavaP.interfaceMethodOrFieldDecl_return interfaceMethodOrFieldDecl() throws RecognitionException { return gJavaP.interfaceMethodOrFieldDecl(); }
    public Aspect_JavaP.expressionList_return expressionList() throws RecognitionException { return gJavaP.expressionList(); }
    public Aspect_JavaP.interfaceGenericMethodDecl_return interfaceGenericMethodDecl() throws RecognitionException { return gJavaP.interfaceGenericMethodDecl(); }
    public Aspect_JavaP.interfaceDeclaration_return interfaceDeclaration() throws RecognitionException { return gJavaP.interfaceDeclaration(); }
    public Aspect_JavaP.arguments_return arguments() throws RecognitionException { return gJavaP.arguments(); }
    public Aspect_JavaP.type_return type() throws RecognitionException { return gJavaP.type(); }
    public Aspect_JavaP.forUpdate_return forUpdate() throws RecognitionException { return gJavaP.forUpdate(); }
    public Aspect_JavaP.enumBody_return enumBody() throws RecognitionException { return gJavaP.enumBody(); }
    public Aspect_JavaP.exclusiveOrExpression_return exclusiveOrExpression() throws RecognitionException { return gJavaP.exclusiveOrExpression(); }

    public final boolean synpred54_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred54_Aspect_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_Aspect_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred36_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred36_Aspect_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_Aspect_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_Aspect_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_Aspect_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_Aspect_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA8 dfa8 = new DFA8(this);
    protected DFA10 dfa10 = new DFA10(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA20 dfa20 = new DFA20(this);
    protected DFA35 dfa35 = new DFA35(this);
    static final String DFA8_eotS =
        "\22\uffff";
    static final String DFA8_eofS =
        "\1\2\21\uffff";
    static final String DFA8_minS =
        "\1\5\1\0\20\uffff";
    static final String DFA8_maxS =
        "\1\163\1\0\20\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\2\16\uffff\1\1";
    static final String DFA8_specialS =
        "\1\uffff\1\0\20\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\2\24\uffff\4\2\2\uffff\7\2\10\uffff\1\2\32\uffff\1\1\50"+
            "\uffff\1\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "294:8: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_1 = input.LA(1);

                         
                        int index8_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Aspect()) ) {s = 17;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index8_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA10_eotS =
        "\20\uffff";
    static final String DFA10_eofS =
        "\20\uffff";
    static final String DFA10_minS =
        "\1\4\15\0\2\uffff";
    static final String DFA10_maxS =
        "\1\175\15\0\2\uffff";
    static final String DFA10_acceptS =
        "\16\uffff\1\1\1\2";
    static final String DFA10_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\2\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\7\76\uffff\1\15\11\uffff\1\13\43\uffff\1\14\3\uffff\1\1"+
            "\1\2\1\3\1\4\1\5\1\6\1\10\1\11\1\12",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "372:1: pointcutExpression returns [PointcutExpression element] : (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression | expr= pointcutExpressionOr );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA10_1 = input.LA(1);

                         
                        int index10_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA10_2 = input.LA(1);

                         
                        int index10_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA10_3 = input.LA(1);

                         
                        int index10_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA10_4 = input.LA(1);

                         
                        int index10_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA10_5 = input.LA(1);

                         
                        int index10_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA10_6 = input.LA(1);

                         
                        int index10_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA10_7 = input.LA(1);

                         
                        int index10_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA10_8 = input.LA(1);

                         
                        int index10_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA10_9 = input.LA(1);

                         
                        int index10_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA10_10 = input.LA(1);

                         
                        int index10_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA10_11 = input.LA(1);

                         
                        int index10_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA10_12 = input.LA(1);

                         
                        int index10_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA10_13 = input.LA(1);

                         
                        int index10_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index10_13);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 10, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA11_eotS =
        "\20\uffff";
    static final String DFA11_eofS =
        "\20\uffff";
    static final String DFA11_minS =
        "\1\4\15\0\2\uffff";
    static final String DFA11_maxS =
        "\1\175\15\0\2\uffff";
    static final String DFA11_acceptS =
        "\16\uffff\1\1\1\2";
    static final String DFA11_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\2\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\7\76\uffff\1\15\11\uffff\1\13\43\uffff\1\14\3\uffff\1\1"+
            "\1\2\1\3\1\4\1\5\1\6\1\10\1\11\1\12",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "378:1: pointcutExpressionOr returns [PointcutExpression element] : (expr1= pointcutAtom '||' expr2= pointcutExpressionOr | expr= pointcutAtom );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA11_1 = input.LA(1);

                         
                        int index11_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA11_2 = input.LA(1);

                         
                        int index11_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA11_3 = input.LA(1);

                         
                        int index11_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA11_4 = input.LA(1);

                         
                        int index11_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA11_5 = input.LA(1);

                         
                        int index11_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA11_6 = input.LA(1);

                         
                        int index11_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA11_7 = input.LA(1);

                         
                        int index11_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA11_8 = input.LA(1);

                         
                        int index11_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA11_9 = input.LA(1);

                         
                        int index11_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA11_10 = input.LA(1);

                         
                        int index11_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA11_11 = input.LA(1);

                         
                        int index11_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA11_12 = input.LA(1);

                         
                        int index11_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA11_13 = input.LA(1);

                         
                        int index11_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index11_13);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 11, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA20_eotS =
        "\72\uffff";
    static final String DFA20_eofS =
        "\72\uffff";
    static final String DFA20_minS =
        "\1\4\13\0\11\uffff\1\0\44\uffff";
    static final String DFA20_maxS =
        "\1\u0083\13\0\11\uffff\1\0\44\uffff";
    static final String DFA20_acceptS =
        "\14\uffff\1\2\11\uffff\1\4\41\uffff\1\1\1\3";
    static final String DFA20_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\11\uffff"+
        "\1\13\44\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\3\1\14\7\26\16\uffff\1\26\1\uffff\1\14\2\uffff\4\14\1\1"+
            "\2\14\6\uffff\1\26\1\uffff\1\14\1\26\5\uffff\1\26\2\uffff\1"+
            "\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\uffff\2\26\2\uffff\4\26"+
            "\1\2\2\uffff\1\26\1\uffff\4\26\1\uffff\1\26\1\25\3\26\21\uffff"+
            "\2\26\2\uffff\5\26\20\uffff\1\26",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "452:1: adviceBlockStatement returns [Statement element] : (local= localVariableDeclarationStatement | cd= classOrInterfaceDeclaration | specialReturn= adviceReturnStatement | stat= statement );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA20_1 = input.LA(1);

                         
                        int index20_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (synpred35_Aspect()) ) {s = 12;}

                         
                        input.seek(index20_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA20_2 = input.LA(1);

                         
                        int index20_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (synpred35_Aspect()) ) {s = 12;}

                         
                        input.seek(index20_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA20_3 = input.LA(1);

                         
                        int index20_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA20_4 = input.LA(1);

                         
                        int index20_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA20_5 = input.LA(1);

                         
                        int index20_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA20_6 = input.LA(1);

                         
                        int index20_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA20_7 = input.LA(1);

                         
                        int index20_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA20_8 = input.LA(1);

                         
                        int index20_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA20_9 = input.LA(1);

                         
                        int index20_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA20_10 = input.LA(1);

                         
                        int index20_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA20_11 = input.LA(1);

                         
                        int index20_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA20_21 = input.LA(1);

                         
                        int index20_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 57;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index20_21);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 20, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA35_eotS =
        "\16\uffff";
    static final String DFA35_eofS =
        "\1\14\15\uffff";
    static final String DFA35_minS =
        "\1\33\13\0\2\uffff";
    static final String DFA35_maxS =
        "\1\142\13\0\2\uffff";
    static final String DFA35_acceptS =
        "\14\uffff\1\2\1\1";
    static final String DFA35_specialS =
        "\1\uffff\1\11\1\3\1\12\1\6\1\1\1\5\1\0\1\7\1\2\1\4\1\10\2\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\14\15\uffff\1\12\1\14\1\13\2\uffff\1\14\3\uffff\1\14\1\uffff"+
            "\1\1\17\uffff\1\14\7\uffff\1\14\16\uffff\1\2\1\3\1\4\1\5\1\6"+
            "\1\7\1\10\1\11",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "534:63: (op= assignmentOperator exx= expression )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA35_7 = input.LA(1);

                         
                        int index35_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_7);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA35_5 = input.LA(1);

                         
                        int index35_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA35_9 = input.LA(1);

                         
                        int index35_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_9);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA35_2 = input.LA(1);

                         
                        int index35_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_2);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA35_10 = input.LA(1);

                         
                        int index35_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_10);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA35_6 = input.LA(1);

                         
                        int index35_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA35_4 = input.LA(1);

                         
                        int index35_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_4);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA35_8 = input.LA(1);

                         
                        int index35_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA35_11 = input.LA(1);

                         
                        int index35_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA35_1 = input.LA(1);

                         
                        int index35_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_1);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA35_3 = input.LA(1);

                         
                        int index35_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred54_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index35_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 35, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_annotations_in_compilationUnit80 = new BitSet(new long[]{0x0000807F24000020L,0x0000000000000400L});
    public static final BitSet FOLLOW_packageDeclaration_in_compilationUnit96 = new BitSet(new long[]{0x0000807F3C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_importDeclaration_in_compilationUnit132 = new BitSet(new long[]{0x0000807F3C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit153 = new BitSet(new long[]{0x0000807F2C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_compilationUnit202 = new BitSet(new long[]{0x0000807F2C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit237 = new BitSet(new long[]{0x0000807F2C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_packageDeclaration_in_compilationUnit292 = new BitSet(new long[]{0x0000807F3C000022L,0x0008000000000400L});
    public static final BitSet FOLLOW_importDeclaration_in_compilationUnit341 = new BitSet(new long[]{0x0000807F3C000022L,0x0008000000000400L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit378 = new BitSet(new long[]{0x0000807F2C000022L,0x0008000000000400L});
    public static final BitSet FOLLOW_aspect_in_compilationUnit416 = new BitSet(new long[]{0x0000807F2C000022L,0x0008000000000400L});
    public static final BitSet FOLLOW_115_in_aspect465 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_Identifier_in_aspect469 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_aspect475 = new BitSet(new long[]{0xFE01400000000010L,0xC010000000000001L,0x0000000000000001L});
    public static final BitSet FOLLOW_advice_in_aspect484 = new BitSet(new long[]{0xFE01400000000010L,0xC010000000000001L,0x0000000000000001L});
    public static final BitSet FOLLOW_pointcut_in_aspect491 = new BitSet(new long[]{0xFE01400000000010L,0xC010000000000001L,0x0000000000000001L});
    public static final BitSet FOLLOW_46_in_aspect499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_pointcut526 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_pointcutDecl_in_pointcut530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_formalParameters_in_pointcut534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_pointcut536 = new BitSet(new long[]{0x0000000000000010L,0x3FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_pointcut540 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_pointcut542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_pointcutDecl564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_pointcutExpression589 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_pointcutExpression591 = new BitSet(new long[]{0x0000000000000010L,0x3FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_pointcutExpression595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_pointcutExpression604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutAtom_in_pointcutExpressionOr627 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_pointcutExpressionOr629 = new BitSet(new long[]{0x0000000000000010L,0x3FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_pointcutExpressionOr633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutAtom_in_pointcutExpressionOr642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_subtypeMarker659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_pointcutAtom685 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom687 = new BitSet(new long[]{0xFE01000000400010L,0x0000000000000001L});
    public static final BitSet FOLLOW_methodReference_in_pointcutAtom691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_118_in_pointcutAtom702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom704 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_Identifier_in_pointcutAtom708 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_119_in_pointcutAtom719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_120_in_pointcutAtom728 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom730 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_type_in_pointcutAtom734 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_fieldReference_in_pointcutAtom738 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_121_in_pointcutAtom749 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom751 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_type_in_pointcutAtom755 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000010L});
    public static final BitSet FOLLOW_subtypeMarker_in_pointcutAtom760 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_122_in_pointcutAtom775 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom777 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_type_in_pointcutAtom781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_namedPointcutReference_in_pointcutAtom792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_123_in_pointcutAtom803 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_typesOrParameters_in_pointcutAtom807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_pointcutAtom816 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom818 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_pointcutAtom822 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_125_in_pointcutAtom833 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom835 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_pointcutAtom839 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_pointcutAtom850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom852 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_pointcutAtom856 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_pointcutAtom865 = new BitSet(new long[]{0x0000000000000010L,0x3FE2000000002008L});
    public static final BitSet FOLLOW_pointcutAtom_in_pointcutAtom869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_pointcutAtom876 = new BitSet(new long[]{0x0000000000000010L,0x3FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_pointcutAtom880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutDecl_in_namedPointcutReference905 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_namedPointcutReference907 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000010L});
    public static final BitSet FOLLOW_argParams_in_namedPointcutReference912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_namedPointcutReference920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_fieldReference945 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_fieldReference950 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_Identifier_in_fieldReference954 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_Identifier_in_argParams981 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_argParams984 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_argParams_in_argParams988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_advice1022 = new BitSet(new long[]{0xFE01000000000010L,0xC000000000000001L,0x0000000000000001L});
    public static final BitSet FOLLOW_48_in_advice1027 = new BitSet(new long[]{0xFE01000000000010L,0xC000000000000001L,0x0000000000000001L});
    public static final BitSet FOLLOW_adviceTypeModifier_in_advice1035 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_formalParameters_in_advice1039 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L,0x0000000000000006L});
    public static final BitSet FOLLOW_adviceTypeModifierSpecifier_in_advice1044 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_advice1050 = new BitSet(new long[]{0x0000000000000010L,0x3FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_advice1054 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_adviceBody_in_advice1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adviceBlock_in_adviceBody1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_adviceBlock1120 = new BitSet(new long[]{0xFE41E07F2C001FF0L,0x0007CC0001F7A7CDL,0x0000000000000008L});
    public static final BitSet FOLLOW_adviceBlockStatement_in_adviceBlock1127 = new BitSet(new long[]{0xFE41E07F2C001FF0L,0x0007CC0001F7A7CDL,0x0000000000000008L});
    public static final BitSet FOLLOW_46_in_adviceBlock1133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclarationStatement_in_adviceBlockStatement1166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_adviceBlockStatement1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adviceReturnStatement_in_adviceBlockStatement1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_adviceBlockStatement1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_adviceReturnStatement1239 = new BitSet(new long[]{0xFE01000008000FD0L,0x0007CC00000003CDL,0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_adviceReturnStatement1261 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_adviceReturnStatement1267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_126_in_adviceTypeModifier1292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_127_in_adviceTypeModifier1301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_128_in_adviceTypeModifier1310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_adviceTypeModifierSpecifier1334 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_adviceTypeModifierSpecifier1337 = new BitSet(new long[]{0xFE00001000000010L,0x0000000000000411L});
    public static final BitSet FOLLOW_formalParameter_in_adviceTypeModifierSpecifier1342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_adviceTypeModifierSpecifier1348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_130_in_adviceTypeModifierSpecifier1359 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_adviceTypeModifierSpecifier1362 = new BitSet(new long[]{0xFE00001000000010L,0x0000000000000411L});
    public static final BitSet FOLLOW_formalParameter_in_adviceTypeModifierSpecifier1367 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_adviceTypeModifierSpecifier1373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeOrVoid_in_methodReference1411 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_IdentifierWithWC_in_methodReference1417 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_fqn_in_methodReference1424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeOrVoid1444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_voidType_in_typeOrVoid1453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_fqn1489 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_fqn1495 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_simpleMethodHeader_in_fqn1503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_simpleMethodHeader1542 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_formalParameterTypes_in_simpleMethodHeader1550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_typesOrParameters1592 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003DDL,0x0000000000000008L});
    public static final BitSet FOLLOW_typesOrParameterDecls_in_typesOrParameters1597 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_typesOrParameters1603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_typesOrParameterDecls1626 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_typesOrParameterDecls1629 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000008L});
    public static final BitSet FOLLOW_typesOrParameterDecls_in_typesOrParameterDecls1633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_formalParameterTypes1680 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000011L});
    public static final BitSet FOLLOW_formalParameterTypeDecls_in_formalParameterTypes1685 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_formalParameterTypes1691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameterTypeDecls1716 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_formalParameterTypeDecls1719 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_formalParameterTypeDecls_in_formalParameterTypeDecls1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_expression1767 = new BitSet(new long[]{0x00100A0000000002L,0x00000007F8000000L});
    public static final BitSet FOLLOW_assignmentOperator_in_expression1774 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_expression1778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_131_in_expression1814 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_arguments_in_expression1818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotations_in_synpred5_Aspect80 = new BitSet(new long[]{0x0000807F24000020L,0x0000000000000400L});
    public static final BitSet FOLLOW_packageDeclaration_in_synpred5_Aspect96 = new BitSet(new long[]{0x0000807F3C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_importDeclaration_in_synpred5_Aspect132 = new BitSet(new long[]{0x0000807F3C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Aspect153 = new BitSet(new long[]{0x0000807F2C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred5_Aspect202 = new BitSet(new long[]{0x0000807F2C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Aspect237 = new BitSet(new long[]{0x0000807F2C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_synpred12_Aspect589 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_synpred12_Aspect591 = new BitSet(new long[]{0x0000000000000010L,0x3FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_synpred12_Aspect595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutAtom_in_synpred13_Aspect627 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_synpred13_Aspect629 = new BitSet(new long[]{0x0000000000000010L,0x3FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_synpred13_Aspect633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclarationStatement_in_synpred34_Aspect1166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred35_Aspect1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adviceReturnStatement_in_synpred36_Aspect1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentOperator_in_synpred54_Aspect1774 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_synpred54_Aspect1778 = new BitSet(new long[]{0x0000000000000002L});

}