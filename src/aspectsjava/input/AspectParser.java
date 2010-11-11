// $ANTLR 3.2 Sep 23, 2009 12:02:23 C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g 2010-11-11 15:03:41

package aspectsjava.input;

import chameleon.aspects.Advice;
import chameleon.aspects.Aspect;
import chameleon.aspects.pointcut.CrossReferencePointcut;
import chameleon.aspects.pointcut.MethodReference;
import chameleon.aspects.pointcut.PointcutHeader;
import chameleon.aspects.pointcut.PointcutMethodHeader;
import chameleon.aspects.pointcut.QualifiedMethodHeader;
import chameleon.aspects.pointcut.expression.*;

import chameleon.exception.ModelException;
import chameleon.exception.ChameleonProgrammerException;

import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.LookupStrategyFactory;

import chameleon.core.compilationunit.CompilationUnit;

import chameleon.core.declaration.CompositeQualifiedName;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.declaration.TargetDeclaration;
import chameleon.core.declaration.QualifiedName;
import chameleon.core.declaration.Signature;

import chameleon.core.element.Element;

import chameleon.core.reference.SimpleReference;
import chameleon.core.expression.Expression;
import chameleon.core.expression.Invocation;
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
import chameleon.core.method.MethodHeader;
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
import chameleon.support.expression.ThisConstructorDelegation;
import chameleon.support.expression.SuperConstructorDelegation;
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
import chameleon.support.member.simplename.SimpleNameMethodHeader;
import chameleon.support.member.simplename.variable.MemberVariableDeclarator;
import chameleon.support.member.simplename.operator.infix.InfixOperatorInvocation;
import chameleon.support.member.simplename.operator.prefix.PrefixOperatorInvocation;
import chameleon.support.member.simplename.operator.postfix.PostfixOperatorInvocation;
import chameleon.support.member.simplename.method.RegularMethodInvocation;
import chameleon.support.member.simplename.SimpleNameMethodSignature;

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Identifier", "ENUM", "FloatingPointLiteral", "CharacterLiteral", "StringLiteral", "HexLiteral", "OctalLiteral", "DecimalLiteral", "ASSERT", "HexDigit", "IntegerTypeSuffix", "Exponent", "FloatTypeSuffix", "EscapeSequence", "UnicodeEscape", "OctalEscape", "Letter", "JavaIDDigit", "WS", "COMMENT", "LINE_COMMENT", "'package'", "';'", "'import'", "'static'", "'.'", "'*'", "'public'", "'protected'", "'private'", "'abstract'", "'final'", "'strictfp'", "'class'", "'extends'", "'implements'", "'<'", "','", "'>'", "'&'", "'{'", "'}'", "'interface'", "'void'", "'['", "']'", "'throws'", "'='", "'native'", "'synchronized'", "'transient'", "'volatile'", "'boolean'", "'char'", "'byte'", "'short'", "'int'", "'long'", "'float'", "'double'", "'?'", "'super'", "'('", "')'", "'...'", "'this'", "'null'", "'true'", "'false'", "'@'", "'default'", "':'", "'if'", "'else'", "'for'", "'while'", "'do'", "'try'", "'finally'", "'switch'", "'return'", "'throw'", "'break'", "'continue'", "'catch'", "'case'", "'+='", "'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'%='", "'||'", "'&&'", "'|'", "'^'", "'=='", "'!='", "'instanceof'", "'+'", "'-'", "'/'", "'%'", "'++'", "'--'", "'~'", "'!'", "'new'", "'aspect'", "'pointcut'", "'call'", "'before_'", "'after_'"
    };
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int FloatTypeSuffix=16;
    public static final int T__25=25;
    public static final int OctalLiteral=10;
    public static final int EOF=-1;
    public static final int Identifier=4;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__90=90;
    public static final int COMMENT=23;
    public static final int T__99=99;
    public static final int T__98=98;
    public static final int T__97=97;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int LINE_COMMENT=24;
    public static final int IntegerTypeSuffix=14;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int ASSERT=12;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int WS=22;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int FloatingPointLiteral=6;
    public static final int JavaIDDigit=21;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int Letter=20;
    public static final int EscapeSequence=17;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__118=118;
    public static final int CharacterLiteral=7;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int Exponent=15;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int HexDigit=13;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__59=59;
    public static final int T__103=103;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int T__113=113;
    public static final int T__112=112;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int HexLiteral=9;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__102=102;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int DecimalLiteral=11;
    public static final int StringLiteral=8;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int ENUM=5;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int UnicodeEscape=18;
    public static final int OctalEscape=19;

    // delegates
    public Aspect_JavaP gJavaP;
    // delegators


        public AspectParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public AspectParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[442+1];
             
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
          if(type.inheritanceRelations().isEmpty() && (! fqn.equals("java.lang.Object"))){
            type.addInheritanceRelation(new SubtypeRelation(createTypeReference(new NamespaceOrTypeReference("java.lang"),"Object")));
          }
        }

      }
      
        public JavaTypeReference createTypeReference(CrossReference<?, ?, ? extends TargetDeclaration> target, String name) {
        return ((Java)language()).createTypeReference(target,name);
      }
      
      public JavaTypeReference createTypeReference(CrossReference<?, ?, ? extends TargetDeclaration> target, SimpleNameSignature signature) {
        return ((Java)language()).createTypeReference(target,signature);
      }

      public JavaTypeReference createTypeReference(NamedTarget target) {
        return ((Java)language()).createTypeReference(target);
      }



    public static class compilationUnit_return extends ParserRuleReturnScope {
        public CompilationUnit element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "compilationUnit"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:276:1: compilationUnit returns [CompilationUnit element] : ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* ) ;
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:281:5: ( ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* ) )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:281:8: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* )
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:281:8: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* )
            int alt8=2;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:281:10: annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
                    {
                    pushFollow(FOLLOW_annotations_in_compilationUnit80);
                    annotations1=annotations();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotations1.getTree());
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:282:9: (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==25) ) {
                        alt4=1;
                    }
                    else if ( (LA4_0==ENUM||LA4_0==28||(LA4_0>=31 && LA4_0<=37)||LA4_0==46||LA4_0==73) ) {
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
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:282:13: np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )*
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
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:287:13: (imp= importDeclaration )*
                            loop1:
                            do {
                                int alt1=2;
                                int LA1_0 = input.LA(1);

                                if ( (LA1_0==27) ) {
                                    alt1=1;
                                }


                                switch (alt1) {
                            	case 1 :
                            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:287:14: imp= importDeclaration
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

                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:288:13: (typech= typeDeclaration )*
                            loop2:
                            do {
                                int alt2=2;
                                int LA2_0 = input.LA(1);

                                if ( (LA2_0==ENUM||LA2_0==26||LA2_0==28||(LA2_0>=31 && LA2_0<=37)||LA2_0==46||LA2_0==73) ) {
                                    alt2=1;
                                }


                                switch (alt2) {
                            	case 1 :
                            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:288:14: typech= typeDeclaration
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
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:292:13: cd= classOrInterfaceDeclaration (typech= typeDeclaration )*
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
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:298:13: (typech= typeDeclaration )*
                            loop3:
                            do {
                                int alt3=2;
                                int LA3_0 = input.LA(1);

                                if ( (LA3_0==ENUM||LA3_0==26||LA3_0==28||(LA3_0>=31 && LA3_0<=37)||LA3_0==46||LA3_0==73) ) {
                                    alt3=1;
                                }


                                switch (alt3) {
                            	case 1 :
                            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:298:14: typech= typeDeclaration
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:303:9: (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )*
                    {
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:303:9: (np= packageDeclaration )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==25) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:303:10: np= packageDeclaration
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:315:9: (imp= importDeclaration )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==27) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:315:10: imp= importDeclaration
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

                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:318:9: (typech= typeDeclaration | ad= aspect )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==ENUM||LA7_0==26||LA7_0==28||(LA7_0>=31 && LA7_0<=37)||LA7_0==46||LA7_0==73) ) {
                            alt7=1;
                        }
                        else if ( (LA7_0==114) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:318:10: typech= typeDeclaration
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
                    	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:323:11: ad= aspect
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:330:1: aspect returns [Aspect element] : asp= 'aspect' name= Identifier '{' ( (adv= advice ) | (ptc= pointcut ) )* '}' ;
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:332:2: (asp= 'aspect' name= Identifier '{' ( (adv= advice ) | (ptc= pointcut ) )* '}' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:332:4: asp= 'aspect' name= Identifier '{' ( (adv= advice ) | (ptc= pointcut ) )* '}'
            {
            root_0 = (Object)adaptor.nil();

            asp=(Token)match(input,114,FOLLOW_114_in_aspect465); if (state.failed) return retval;
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
            char_literal2=(Token)match(input,44,FOLLOW_44_in_aspect475); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal2_tree = (Object)adaptor.create(char_literal2);
            adaptor.addChild(root_0, char_literal2_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:334:2: ( (adv= advice ) | (ptc= pointcut ) )*
            loop9:
            do {
                int alt9=3;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=117 && LA9_0<=118)) ) {
                    alt9=1;
                }
                else if ( (LA9_0==115) ) {
                    alt9=2;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:334:3: (adv= advice )
            	    {
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:334:3: (adv= advice )
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:334:4: adv= advice
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
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:334:56: (ptc= pointcut )
            	    {
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:334:56: (ptc= pointcut )
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:334:57: ptc= pointcut
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

            char_literal3=(Token)match(input,45,FOLLOW_45_in_aspect499); if (state.failed) return retval;
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
        public CrossReferencePointcut element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointcut"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:342:1: pointcut returns [CrossReferencePointcut element] : pct= 'pointcut' decl= pointcutDecl pars= formalParameters ':' expr= pointcutExpression ';' ;
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:344:2: (pct= 'pointcut' decl= pointcutDecl pars= formalParameters ':' expr= pointcutExpression ';' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:344:4: pct= 'pointcut' decl= pointcutDecl pars= formalParameters ':' expr= pointcutExpression ';'
            {
            root_0 = (Object)adaptor.nil();

            pct=(Token)match(input,115,FOLLOW_115_in_pointcut526); if (state.failed) return retval;
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
            char_literal4=(Token)match(input,75,FOLLOW_75_in_pointcut536); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal4_tree = (Object)adaptor.create(char_literal4);
            adaptor.addChild(root_0, char_literal4_tree);
            }
            pushFollow(FOLLOW_pointcutExpression_in_pointcut540);
            expr=pointcutExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
            char_literal5=(Token)match(input,26,FOLLOW_26_in_pointcut542); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal5_tree = (Object)adaptor.create(char_literal5);
            adaptor.addChild(root_0, char_literal5_tree);
            }
            if ( state.backtracking==0 ) {

              		PointcutHeader header = new PointcutHeader(decl.element);
              		header.addFormalParameters(pars.element);
              		retval.element = new CrossReferencePointcut(header, expr.element);
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:354:1: pointcutDecl returns [String element] : name= Identifier ;
    public final AspectParser.pointcutDecl_return pointcutDecl() throws RecognitionException {
        AspectParser.pointcutDecl_return retval = new AspectParser.pointcutDecl_return();
        retval.start = input.LT(1);
        int pointcutDecl_StartIndex = input.index();
        Object root_0 = null;

        Token name=null;

        Object name_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 411) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:355:2: (name= Identifier )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:355:4: name= Identifier
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:358:1: pointcutExpression returns [PointcutExpression element] : (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression | expr= pointcutExpressionOr );
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:360:2: (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression | expr= pointcutExpressionOr )
            int alt10=2;
            switch ( input.LA(1) ) {
            case 116:
                {
                int LA10_1 = input.LA(2);

                if ( (synpred12_Aspect()) ) {
                    alt10=1;
                }
                else if ( (true) ) {
                    alt10=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
                }
                break;
            case 112:
                {
                int LA10_2 = input.LA(2);

                if ( (synpred12_Aspect()) ) {
                    alt10=1;
                }
                else if ( (true) ) {
                    alt10=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 2, input);

                    throw nvae;
                }
                }
                break;
            case 66:
                {
                int LA10_3 = input.LA(2);

                if ( (synpred12_Aspect()) ) {
                    alt10=1;
                }
                else if ( (true) ) {
                    alt10=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:360:4: expr1= pointcutExpressionOr '&&' expr2= pointcutExpression
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pointcutExpressionOr_in_pointcutExpression588);
                    expr1=pointcutExpressionOr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr1.getTree());
                    string_literal6=(Token)match(input,99,FOLLOW_99_in_pointcutExpression590); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal6_tree = (Object)adaptor.create(string_literal6);
                    adaptor.addChild(root_0, string_literal6_tree);
                    }
                    pushFollow(FOLLOW_pointcutExpression_in_pointcutExpression594);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:361:4: expr= pointcutExpressionOr
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pointcutExpressionOr_in_pointcutExpression603);
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:364:1: pointcutExpressionOr returns [PointcutExpression element] : (expr1= pointcutAtom '||' expr2= pointcutExpressionOr | expr= pointcutAtom );
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:366:2: (expr1= pointcutAtom '||' expr2= pointcutExpressionOr | expr= pointcutAtom )
            int alt11=2;
            switch ( input.LA(1) ) {
            case 116:
                {
                int LA11_1 = input.LA(2);

                if ( (synpred13_Aspect()) ) {
                    alt11=1;
                }
                else if ( (true) ) {
                    alt11=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;
                }
                }
                break;
            case 112:
                {
                int LA11_2 = input.LA(2);

                if ( (synpred13_Aspect()) ) {
                    alt11=1;
                }
                else if ( (true) ) {
                    alt11=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 2, input);

                    throw nvae;
                }
                }
                break;
            case 66:
                {
                int LA11_3 = input.LA(2);

                if ( (synpred13_Aspect()) ) {
                    alt11=1;
                }
                else if ( (true) ) {
                    alt11=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:366:4: expr1= pointcutAtom '||' expr2= pointcutExpressionOr
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pointcutAtom_in_pointcutExpressionOr626);
                    expr1=pointcutAtom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr1.getTree());
                    string_literal7=(Token)match(input,98,FOLLOW_98_in_pointcutExpressionOr628); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal7_tree = (Object)adaptor.create(string_literal7);
                    adaptor.addChild(root_0, string_literal7_tree);
                    }
                    pushFollow(FOLLOW_pointcutExpressionOr_in_pointcutExpressionOr632);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:367:4: expr= pointcutAtom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pointcutAtom_in_pointcutExpressionOr641);
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

    public static class pointcutAtom_return extends ParserRuleReturnScope {
        public PointcutExpression element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointcutAtom"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:371:1: pointcutAtom returns [PointcutExpression element] : (cl= 'call' '(' metref= methodReference ')' | '!' expr1= pointcutAtom | '(' expr2= pointcutExpression ')' );
    public final AspectParser.pointcutAtom_return pointcutAtom() throws RecognitionException {
        AspectParser.pointcutAtom_return retval = new AspectParser.pointcutAtom_return();
        retval.start = input.LT(1);
        int pointcutAtom_StartIndex = input.index();
        Object root_0 = null;

        Token cl=null;
        Token char_literal8=null;
        Token char_literal9=null;
        Token char_literal10=null;
        Token char_literal11=null;
        Token char_literal12=null;
        AspectParser.methodReference_return metref = null;

        AspectParser.pointcutAtom_return expr1 = null;

        AspectParser.pointcutExpression_return expr2 = null;


        Object cl_tree=null;
        Object char_literal8_tree=null;
        Object char_literal9_tree=null;
        Object char_literal10_tree=null;
        Object char_literal11_tree=null;
        Object char_literal12_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 414) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:373:2: (cl= 'call' '(' metref= methodReference ')' | '!' expr1= pointcutAtom | '(' expr2= pointcutExpression ')' )
            int alt12=3;
            switch ( input.LA(1) ) {
            case 116:
                {
                alt12=1;
                }
                break;
            case 112:
                {
                alt12=2;
                }
                break;
            case 66:
                {
                alt12=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:373:4: cl= 'call' '(' metref= methodReference ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    cl=(Token)match(input,116,FOLLOW_116_in_pointcutAtom666); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    cl_tree = (Object)adaptor.create(cl);
                    adaptor.addChild(root_0, cl_tree);
                    }
                    char_literal8=(Token)match(input,66,FOLLOW_66_in_pointcutAtom668); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal8_tree = (Object)adaptor.create(char_literal8);
                    adaptor.addChild(root_0, char_literal8_tree);
                    }
                    pushFollow(FOLLOW_methodReference_in_pointcutAtom672);
                    metref=methodReference();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, metref.getTree());
                    char_literal9=(Token)match(input,67,FOLLOW_67_in_pointcutAtom674); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal9_tree = (Object)adaptor.create(char_literal9);
                    adaptor.addChild(root_0, char_literal9_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.element = new CrossReferencePointcutExpression(metref.element); setKeyword(retval.element, cl);
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:374:4: '!' expr1= pointcutAtom
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal10=(Token)match(input,112,FOLLOW_112_in_pointcutAtom681); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal10_tree = (Object)adaptor.create(char_literal10);
                    adaptor.addChild(root_0, char_literal10_tree);
                    }
                    pushFollow(FOLLOW_pointcutAtom_in_pointcutAtom685);
                    expr1=pointcutAtom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr1.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = new PointcutExpressionNot(expr1.element);
                    }

                    }
                    break;
                case 3 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:375:4: '(' expr2= pointcutExpression ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal11=(Token)match(input,66,FOLLOW_66_in_pointcutAtom692); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal11_tree = (Object)adaptor.create(char_literal11);
                    adaptor.addChild(root_0, char_literal11_tree);
                    }
                    pushFollow(FOLLOW_pointcutExpression_in_pointcutAtom696);
                    expr2=pointcutExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr2.getTree());
                    char_literal12=(Token)match(input,67,FOLLOW_67_in_pointcutAtom698); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal12_tree = (Object)adaptor.create(char_literal12);
                    adaptor.addChild(root_0, char_literal12_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 414, pointcutAtom_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "pointcutAtom"

    public static class advice_return extends ParserRuleReturnScope {
        public Advice element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "advice"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:378:1: advice returns [Advice element] : advtype= ( 'before_' | 'after_' ) ':' decl= pointcutDecl '(' ( Identifier )? ( ',' Identifier )* ')' bdy= methodBody ;
    public final AspectParser.advice_return advice() throws RecognitionException {
        AspectParser.advice_return retval = new AspectParser.advice_return();
        retval.start = input.LT(1);
        int advice_StartIndex = input.index();
        Object root_0 = null;

        Token advtype=null;
        Token char_literal13=null;
        Token char_literal14=null;
        Token Identifier15=null;
        Token char_literal16=null;
        Token Identifier17=null;
        Token char_literal18=null;
        AspectParser.pointcutDecl_return decl = null;

        Aspect_JavaP.methodBody_return bdy = null;


        Object advtype_tree=null;
        Object char_literal13_tree=null;
        Object char_literal14_tree=null;
        Object Identifier15_tree=null;
        Object char_literal16_tree=null;
        Object Identifier17_tree=null;
        Object char_literal18_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 415) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:380:2: (advtype= ( 'before_' | 'after_' ) ':' decl= pointcutDecl '(' ( Identifier )? ( ',' Identifier )* ')' bdy= methodBody )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:380:5: advtype= ( 'before_' | 'after_' ) ':' decl= pointcutDecl '(' ( Identifier )? ( ',' Identifier )* ')' bdy= methodBody
            {
            root_0 = (Object)adaptor.nil();

            advtype=(Token)input.LT(1);
            if ( (input.LA(1)>=117 && input.LA(1)<=118) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(advtype));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            char_literal13=(Token)match(input,75,FOLLOW_75_in_advice730); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal13_tree = (Object)adaptor.create(char_literal13);
            adaptor.addChild(root_0, char_literal13_tree);
            }
            pushFollow(FOLLOW_pointcutDecl_in_advice734);
            decl=pointcutDecl();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, decl.getTree());
            char_literal14=(Token)match(input,66,FOLLOW_66_in_advice736); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal14_tree = (Object)adaptor.create(char_literal14);
            adaptor.addChild(root_0, char_literal14_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:380:62: ( Identifier )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==Identifier) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:0:0: Identifier
                    {
                    Identifier15=(Token)match(input,Identifier,FOLLOW_Identifier_in_advice738); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Identifier15_tree = (Object)adaptor.create(Identifier15);
                    adaptor.addChild(root_0, Identifier15_tree);
                    }

                    }
                    break;

            }

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:380:74: ( ',' Identifier )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==41) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:380:75: ',' Identifier
            	    {
            	    char_literal16=(Token)match(input,41,FOLLOW_41_in_advice742); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal16_tree = (Object)adaptor.create(char_literal16);
            	    adaptor.addChild(root_0, char_literal16_tree);
            	    }
            	    Identifier17=(Token)match(input,Identifier,FOLLOW_Identifier_in_advice744); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    Identifier17_tree = (Object)adaptor.create(Identifier17);
            	    adaptor.addChild(root_0, Identifier17_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            char_literal18=(Token)match(input,67,FOLLOW_67_in_advice748); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal18_tree = (Object)adaptor.create(char_literal18);
            adaptor.addChild(root_0, char_literal18_tree);
            }
            if ( state.backtracking==0 ) {

              		retval.element=new Advice();
              		SimpleReference<CrossReferencePointcut> ref = new SimpleReference<CrossReferencePointcut>(decl.element, CrossReferencePointcut.class); 
              		retval.element.setPointcutReference(ref);
              		setLocation(ref, decl.start, decl.stop);
              	
            }
            pushFollow(FOLLOW_methodBody_in_advice759);
            bdy=methodBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bdy.getTree());
            if ( state.backtracking==0 ) {

              		retval.element.setBody(bdy.element);
              		setKeyword(retval.element, advtype);
              	
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
            if ( state.backtracking>0 ) { memoize(input, 415, advice_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "advice"

    public static class methodReference_return extends ParserRuleReturnScope {
        public MethodReference element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "methodReference"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:395:1: methodReference returns [MethodReference element] : t= methodReferenceType name= fqn ;
    public final AspectParser.methodReference_return methodReference() throws RecognitionException {
        AspectParser.methodReference_return retval = new AspectParser.methodReference_return();
        retval.start = input.LT(1);
        int methodReference_StartIndex = input.index();
        Object root_0 = null;

        AspectParser.methodReferenceType_return t = null;

        AspectParser.fqn_return name = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 416) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:397:2: (t= methodReferenceType name= fqn )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:397:4: t= methodReferenceType name= fqn
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_methodReferenceType_in_methodReference783);
            t=methodReferenceType();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
            pushFollow(FOLLOW_fqn_in_methodReference787);
            name=fqn();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, name.getTree());
            if ( state.backtracking==0 ) {
              retval.element = new MethodReference(t.element, name.element);
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
            if ( state.backtracking>0 ) { memoize(input, 416, methodReference_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "methodReference"

    public static class methodReferenceType_return extends ParserRuleReturnScope {
        public JavaTypeReference element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "methodReferenceType"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:400:1: methodReferenceType returns [JavaTypeReference element] : (vt= voidType | t= type );
    public final AspectParser.methodReferenceType_return methodReferenceType() throws RecognitionException {
        AspectParser.methodReferenceType_return retval = new AspectParser.methodReferenceType_return();
        retval.start = input.LT(1);
        int methodReferenceType_StartIndex = input.index();
        Object root_0 = null;

        Aspect_JavaP.voidType_return vt = null;

        Aspect_JavaP.type_return t = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 417) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:401:2: (vt= voidType | t= type )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==47) ) {
                alt15=1;
            }
            else if ( (LA15_0==Identifier||(LA15_0>=56 && LA15_0<=63)) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:401:4: vt= voidType
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_voidType_in_methodReferenceType807);
                    vt=voidType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, vt.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = vt.element;
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:402:4: t= type
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_type_in_methodReferenceType816);
                    t=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = t.element;
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
            if ( state.backtracking>0 ) { memoize(input, 417, methodReferenceType_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "methodReferenceType"

    public static class fqn_return extends ParserRuleReturnScope {
        public QualifiedMethodHeader element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fqn"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:406:1: fqn returns [QualifiedMethodHeader element] : (id= Identifier '.' )* mth= simpleMethodHeader ;
    public final AspectParser.fqn_return fqn() throws RecognitionException {
        AspectParser.fqn_return retval = new AspectParser.fqn_return();
        retval.start = input.LT(1);
        int fqn_StartIndex = input.index();
        Object root_0 = null;

        Token id=null;
        Token char_literal19=null;
        AspectParser.simpleMethodHeader_return mth = null;


        Object id_tree=null;
        Object char_literal19_tree=null;

        CompositeQualifiedName prefixes = new CompositeQualifiedName();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 418) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:409:2: ( (id= Identifier '.' )* mth= simpleMethodHeader )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:409:4: (id= Identifier '.' )* mth= simpleMethodHeader
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:409:4: (id= Identifier '.' )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==Identifier) ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1==29) ) {
                        alt16=1;
                    }


                }


                switch (alt16) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:409:5: id= Identifier '.'
            	    {
            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_fqn854); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    id_tree = (Object)adaptor.create(id);
            	    adaptor.addChild(root_0, id_tree);
            	    }
            	    char_literal19=(Token)match(input,29,FOLLOW_29_in_fqn856); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal19_tree = (Object)adaptor.create(char_literal19);
            	    adaptor.addChild(root_0, char_literal19_tree);
            	    }
            	    if ( state.backtracking==0 ) {
            	      prefixes.append(new SimpleNameSignature((id!=null?id.getText():null)));
            	    }

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            pushFollow(FOLLOW_simpleMethodHeader_in_fqn864);
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
            if ( state.backtracking>0 ) { memoize(input, 418, fqn_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "fqn"

    public static class simpleMethodHeader_return extends ParserRuleReturnScope {
        public PointcutMethodHeader element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "simpleMethodHeader"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:413:1: simpleMethodHeader returns [PointcutMethodHeader element] : name= Identifier pars= formalParameterTypes ;
    public final AspectParser.simpleMethodHeader_return simpleMethodHeader() throws RecognitionException {
        AspectParser.simpleMethodHeader_return retval = new AspectParser.simpleMethodHeader_return();
        retval.start = input.LT(1);
        int simpleMethodHeader_StartIndex = input.index();
        Object root_0 = null;

        Token name=null;
        AspectParser.formalParameterTypes_return pars = null;


        Object name_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 419) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:415:9: (name= Identifier pars= formalParameterTypes )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:415:11: name= Identifier pars= formalParameterTypes
            {
            root_0 = (Object)adaptor.nil();

            name=(Token)match(input,Identifier,FOLLOW_Identifier_in_simpleMethodHeader903); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            name_tree = (Object)adaptor.create(name);
            adaptor.addChild(root_0, name_tree);
            }
            pushFollow(FOLLOW_formalParameterTypes_in_simpleMethodHeader907);
            pars=formalParameterTypes();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, pars.getTree());
            if ( state.backtracking==0 ) {
              retval.element = new PointcutMethodHeader((name!=null?name.getText():null), pars.element); 
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
            if ( state.backtracking>0 ) { memoize(input, 419, simpleMethodHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "simpleMethodHeader"

    public static class formalParameterTypes_return extends ParserRuleReturnScope {
        public List<TypeReference> element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "formalParameterTypes"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:418:1: formalParameterTypes returns [List<TypeReference> element] : '(' (pars= formalParameterTypeDecls )? ')' ;
    public final AspectParser.formalParameterTypes_return formalParameterTypes() throws RecognitionException {
        AspectParser.formalParameterTypes_return retval = new AspectParser.formalParameterTypes_return();
        retval.start = input.LT(1);
        int formalParameterTypes_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal20=null;
        Token char_literal21=null;
        AspectParser.formalParameterTypeDecls_return pars = null;


        Object char_literal20_tree=null;
        Object char_literal21_tree=null;

        retval.element = new ArrayList<TypeReference>();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 420) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:420:5: ( '(' (pars= formalParameterTypeDecls )? ')' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:420:9: '(' (pars= formalParameterTypeDecls )? ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal20=(Token)match(input,66,FOLLOW_66_in_formalParameterTypes949); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal20_tree = (Object)adaptor.create(char_literal20);
            adaptor.addChild(root_0, char_literal20_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:420:13: (pars= formalParameterTypeDecls )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==Identifier||(LA17_0>=56 && LA17_0<=63)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:420:14: pars= formalParameterTypeDecls
                    {
                    pushFollow(FOLLOW_formalParameterTypeDecls_in_formalParameterTypes954);
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

            char_literal21=(Token)match(input,67,FOLLOW_67_in_formalParameterTypes960); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal21_tree = (Object)adaptor.create(char_literal21);
            adaptor.addChild(root_0, char_literal21_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 420, formalParameterTypes_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:423:1: formalParameterTypeDecls returns [List<TypeReference> element] : t= type ( ',' decls= formalParameterTypeDecls )? ;
    public final AspectParser.formalParameterTypeDecls_return formalParameterTypeDecls() throws RecognitionException {
        AspectParser.formalParameterTypeDecls_return retval = new AspectParser.formalParameterTypeDecls_return();
        retval.start = input.LT(1);
        int formalParameterTypeDecls_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal22=null;
        Aspect_JavaP.type_return t = null;

        AspectParser.formalParameterTypeDecls_return decls = null;


        Object char_literal22_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 421) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:424:5: (t= type ( ',' decls= formalParameterTypeDecls )? )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:424:9: t= type ( ',' decls= formalParameterTypeDecls )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_type_in_formalParameterTypeDecls985);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:424:16: ( ',' decls= formalParameterTypeDecls )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==41) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:424:17: ',' decls= formalParameterTypeDecls
                    {
                    char_literal22=(Token)match(input,41,FOLLOW_41_in_formalParameterTypeDecls988); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal22_tree = (Object)adaptor.create(char_literal22);
                    adaptor.addChild(root_0, char_literal22_tree);
                    }
                    pushFollow(FOLLOW_formalParameterTypeDecls_in_formalParameterTypeDecls992);
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
            if ( state.backtracking>0 ) { memoize(input, 421, formalParameterTypeDecls_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "formalParameterTypeDecls"

    // $ANTLR start synpred5_Aspect
    public final void synpred5_Aspect_fragment() throws RecognitionException {   
        Aspect_JavaP.packageDeclaration_return np = null;

        Aspect_JavaP.importDeclaration_return imp = null;

        Aspect_JavaP.typeDeclaration_return typech = null;

        Aspect_JavaP.classOrInterfaceDeclaration_return cd = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:281:10: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:281:10: annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
        {
        pushFollow(FOLLOW_annotations_in_synpred5_Aspect80);
        annotations();

        state._fsp--;
        if (state.failed) return ;
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:282:9: (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
        int alt24=2;
        int LA24_0 = input.LA(1);

        if ( (LA24_0==25) ) {
            alt24=1;
        }
        else if ( (LA24_0==ENUM||LA24_0==28||(LA24_0>=31 && LA24_0<=37)||LA24_0==46||LA24_0==73) ) {
            alt24=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 24, 0, input);

            throw nvae;
        }
        switch (alt24) {
            case 1 :
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:282:13: np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )*
                {
                pushFollow(FOLLOW_packageDeclaration_in_synpred5_Aspect96);
                np=packageDeclaration();

                state._fsp--;
                if (state.failed) return ;
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:287:13: (imp= importDeclaration )*
                loop21:
                do {
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==27) ) {
                        alt21=1;
                    }


                    switch (alt21) {
                	case 1 :
                	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:287:14: imp= importDeclaration
                	    {
                	    pushFollow(FOLLOW_importDeclaration_in_synpred5_Aspect132);
                	    imp=importDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop21;
                    }
                } while (true);

                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:288:13: (typech= typeDeclaration )*
                loop22:
                do {
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==ENUM||LA22_0==26||LA22_0==28||(LA22_0>=31 && LA22_0<=37)||LA22_0==46||LA22_0==73) ) {
                        alt22=1;
                    }


                    switch (alt22) {
                	case 1 :
                	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:288:14: typech= typeDeclaration
                	    {
                	    pushFollow(FOLLOW_typeDeclaration_in_synpred5_Aspect153);
                	    typech=typeDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop22;
                    }
                } while (true);


                }
                break;
            case 2 :
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:292:13: cd= classOrInterfaceDeclaration (typech= typeDeclaration )*
                {
                pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred5_Aspect202);
                cd=classOrInterfaceDeclaration();

                state._fsp--;
                if (state.failed) return ;
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:298:13: (typech= typeDeclaration )*
                loop23:
                do {
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==ENUM||LA23_0==26||LA23_0==28||(LA23_0>=31 && LA23_0<=37)||LA23_0==46||LA23_0==73) ) {
                        alt23=1;
                    }


                    switch (alt23) {
                	case 1 :
                	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:298:14: typech= typeDeclaration
                	    {
                	    pushFollow(FOLLOW_typeDeclaration_in_synpred5_Aspect237);
                	    typech=typeDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop23;
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


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:360:4: (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:360:4: expr1= pointcutExpressionOr '&&' expr2= pointcutExpression
        {
        pushFollow(FOLLOW_pointcutExpressionOr_in_synpred12_Aspect588);
        expr1=pointcutExpressionOr();

        state._fsp--;
        if (state.failed) return ;
        match(input,99,FOLLOW_99_in_synpred12_Aspect590); if (state.failed) return ;
        pushFollow(FOLLOW_pointcutExpression_in_synpred12_Aspect594);
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


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:366:4: (expr1= pointcutAtom '||' expr2= pointcutExpressionOr )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:366:4: expr1= pointcutAtom '||' expr2= pointcutExpressionOr
        {
        pushFollow(FOLLOW_pointcutAtom_in_synpred13_Aspect626);
        expr1=pointcutAtom();

        state._fsp--;
        if (state.failed) return ;
        match(input,98,FOLLOW_98_in_synpred13_Aspect628); if (state.failed) return ;
        pushFollow(FOLLOW_pointcutExpressionOr_in_synpred13_Aspect632);
        expr2=pointcutExpressionOr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_Aspect

    // Delegated rules
    public Aspect_JavaP.localVariableDeclaration_return localVariableDeclaration() throws RecognitionException { return gJavaP.localVariableDeclaration(); }
    public Aspect_JavaP.integerLiteral_return integerLiteral() throws RecognitionException { return gJavaP.integerLiteral(); }
    public Aspect_JavaP.methodDeclaration_return methodDeclaration() throws RecognitionException { return gJavaP.methodDeclaration(); }
    public Aspect_JavaP.interfaceBodyDeclaration_return interfaceBodyDeclaration() throws RecognitionException { return gJavaP.interfaceBodyDeclaration(); }
    public Aspect_JavaP.memberDeclaration_return memberDeclaration() throws RecognitionException { return gJavaP.memberDeclaration(); }
    public Aspect_JavaP.blockStatement_return blockStatement() throws RecognitionException { return gJavaP.blockStatement(); }
    public Aspect_JavaP.fieldDeclaration_return fieldDeclaration() throws RecognitionException { return gJavaP.fieldDeclaration(); }
    public Aspect_JavaP.nonTargetPrimary_return nonTargetPrimary() throws RecognitionException { return gJavaP.nonTargetPrimary(); }
    public Aspect_JavaP.moreIdentifierSuffixRubbish_return moreIdentifierSuffixRubbish() throws RecognitionException { return gJavaP.moreIdentifierSuffixRubbish(); }
    public Aspect_JavaP.interfaceGenericMethodDecl_return interfaceGenericMethodDecl() throws RecognitionException { return gJavaP.interfaceGenericMethodDecl(); }
    public Aspect_JavaP.classOrInterfaceType_return classOrInterfaceType() throws RecognitionException { return gJavaP.classOrInterfaceType(); }
    public Aspect_JavaP.classBodyDeclaration_return classBodyDeclaration() throws RecognitionException { return gJavaP.classBodyDeclaration(); }
    public Aspect_JavaP.qualifiedName_return qualifiedName() throws RecognitionException { return gJavaP.qualifiedName(); }
    public Aspect_JavaP.formalParameter_return formalParameter() throws RecognitionException { return gJavaP.formalParameter(); }
    public Aspect_JavaP.relationalExpression_return relationalExpression() throws RecognitionException { return gJavaP.relationalExpression(); }
    public Aspect_JavaP.primitiveType_return primitiveType() throws RecognitionException { return gJavaP.primitiveType(); }
    public Aspect_JavaP.explicitGenericInvocation_return explicitGenericInvocation() throws RecognitionException { return gJavaP.explicitGenericInvocation(); }
    public Aspect_JavaP.equalityExpression_return equalityExpression() throws RecognitionException { return gJavaP.equalityExpression(); }
    public Aspect_JavaP.voidInterfaceMethodDeclaratorRest_return voidInterfaceMethodDeclaratorRest() throws RecognitionException { return gJavaP.voidInterfaceMethodDeclaratorRest(); }
    public Aspect_JavaP.annotations_return annotations() throws RecognitionException { return gJavaP.annotations(); }
    public Aspect_JavaP.annotation_return annotation() throws RecognitionException { return gJavaP.annotation(); }
    public Aspect_JavaP.forUpdate_return forUpdate() throws RecognitionException { return gJavaP.forUpdate(); }
    public Aspect_JavaP.relationalOp_return relationalOp() throws RecognitionException { return gJavaP.relationalOp(); }
    public Aspect_JavaP.primary_return primary() throws RecognitionException { return gJavaP.primary(); }
    public Aspect_JavaP.enumConstantName_return enumConstantName() throws RecognitionException { return gJavaP.enumConstantName(); }
    public Aspect_JavaP.arguments_return arguments() throws RecognitionException { return gJavaP.arguments(); }
    public Aspect_JavaP.argumentsSuffixRubbish_return argumentsSuffixRubbish() throws RecognitionException { return gJavaP.argumentsSuffixRubbish(); }
    public Aspect_JavaP.normalInterfaceDeclaration_return normalInterfaceDeclaration() throws RecognitionException { return gJavaP.normalInterfaceDeclaration(); }
    public Aspect_JavaP.parExpression_return parExpression() throws RecognitionException { return gJavaP.parExpression(); }
    public Aspect_JavaP.block_return block() throws RecognitionException { return gJavaP.block(); }
    public Aspect_JavaP.selector_return selector() throws RecognitionException { return gJavaP.selector(); }
    public Aspect_JavaP.booleanLiteral_return booleanLiteral() throws RecognitionException { return gJavaP.booleanLiteral(); }
    public Aspect_JavaP.variableInitializer_return variableInitializer() throws RecognitionException { return gJavaP.variableInitializer(); }
    public Aspect_JavaP.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException { return gJavaP.multiplicativeExpression(); }
    public Aspect_JavaP.memberDecl_return memberDecl() throws RecognitionException { return gJavaP.memberDecl(); }
    public Aspect_JavaP.elementValue_return elementValue() throws RecognitionException { return gJavaP.elementValue(); }
    public Aspect_JavaP.classOrInterfaceDeclaration_return classOrInterfaceDeclaration() throws RecognitionException { return gJavaP.classOrInterfaceDeclaration(); }
    public Aspect_JavaP.type_return type() throws RecognitionException { return gJavaP.type(); }
    public Aspect_JavaP.constructorBody_return constructorBody() throws RecognitionException { return gJavaP.constructorBody(); }
    public Aspect_JavaP.methodDeclaratorRest_return methodDeclaratorRest() throws RecognitionException { return gJavaP.methodDeclaratorRest(); }
    public Aspect_JavaP.formalParameterDecls_return formalParameterDecls() throws RecognitionException { return gJavaP.formalParameterDecls(); }
    public Aspect_JavaP.exclusiveOrExpression_return exclusiveOrExpression() throws RecognitionException { return gJavaP.exclusiveOrExpression(); }
    public Aspect_JavaP.classDeclaration_return classDeclaration() throws RecognitionException { return gJavaP.classDeclaration(); }
    public Aspect_JavaP.interfaceBody_return interfaceBody() throws RecognitionException { return gJavaP.interfaceBody(); }
    public Aspect_JavaP.conditionalOrExpression_return conditionalOrExpression() throws RecognitionException { return gJavaP.conditionalOrExpression(); }
    public Aspect_JavaP.enumConstant_return enumConstant() throws RecognitionException { return gJavaP.enumConstant(); }
    public Aspect_JavaP.switchCase_return switchCase() throws RecognitionException { return gJavaP.switchCase(); }
    public Aspect_JavaP.packageDeclaration_return packageDeclaration() throws RecognitionException { return gJavaP.packageDeclaration(); }
    public Aspect_JavaP.enhancedForControl_return enhancedForControl() throws RecognitionException { return gJavaP.enhancedForControl(); }
    public Aspect_JavaP.voidType_return voidType() throws RecognitionException { return gJavaP.voidType(); }
    public Aspect_JavaP.nonWildcardTypeArguments_return nonWildcardTypeArguments() throws RecognitionException { return gJavaP.nonWildcardTypeArguments(); }
    public Aspect_JavaP.expressionList_return expressionList() throws RecognitionException { return gJavaP.expressionList(); }
    public Aspect_JavaP.voidMethodDeclaration_return voidMethodDeclaration() throws RecognitionException { return gJavaP.voidMethodDeclaration(); }
    public Aspect_JavaP.enumDeclaration_return enumDeclaration() throws RecognitionException { return gJavaP.enumDeclaration(); }
    public Aspect_JavaP.enumBody_return enumBody() throws RecognitionException { return gJavaP.enumBody(); }
    public Aspect_JavaP.inclusiveOrExpression_return inclusiveOrExpression() throws RecognitionException { return gJavaP.inclusiveOrExpression(); }
    public Aspect_JavaP.shiftOp_return shiftOp() throws RecognitionException { return gJavaP.shiftOp(); }
    public Aspect_JavaP.qualifiedNameList_return qualifiedNameList() throws RecognitionException { return gJavaP.qualifiedNameList(); }
    public Aspect_JavaP.expression_return expression() throws RecognitionException { return gJavaP.expression(); }
    public Aspect_JavaP.voidInterfaceMethodDeclaration_return voidInterfaceMethodDeclaration() throws RecognitionException { return gJavaP.voidInterfaceMethodDeclaration(); }
    public Aspect_JavaP.enumConstants_return enumConstants() throws RecognitionException { return gJavaP.enumConstants(); }
    public Aspect_JavaP.defaultValue_return defaultValue() throws RecognitionException { return gJavaP.defaultValue(); }
    public Aspect_JavaP.interfaceMemberDecl_return interfaceMemberDecl() throws RecognitionException { return gJavaP.interfaceMemberDecl(); }
    public Aspect_JavaP.typeDeclaration_return typeDeclaration() throws RecognitionException { return gJavaP.typeDeclaration(); }
    public Aspect_JavaP.variableModifiers_return variableModifiers() throws RecognitionException { return gJavaP.variableModifiers(); }
    public Aspect_JavaP.genericMethodOrConstructorDecl_return genericMethodOrConstructorDecl() throws RecognitionException { return gJavaP.genericMethodOrConstructorDecl(); }
    public Aspect_JavaP.arrayAccessSuffixRubbish_return arrayAccessSuffixRubbish() throws RecognitionException { return gJavaP.arrayAccessSuffixRubbish(); }
    public Aspect_JavaP.statement_return statement() throws RecognitionException { return gJavaP.statement(); }
    public Aspect_JavaP.innerCreator_return innerCreator() throws RecognitionException { return gJavaP.innerCreator(); }
    public Aspect_JavaP.annotationTypeElementRest_return annotationTypeElementRest() throws RecognitionException { return gJavaP.annotationTypeElementRest(); }
    public Aspect_JavaP.interfaceMethodOrFieldDecl_return interfaceMethodOrFieldDecl() throws RecognitionException { return gJavaP.interfaceMethodOrFieldDecl(); }
    public Aspect_JavaP.annotationConstantRest_return annotationConstantRest(TypeReference type) throws RecognitionException { return gJavaP.annotationConstantRest(type); }
    public Aspect_JavaP.variableDeclarator_return variableDeclarator() throws RecognitionException { return gJavaP.variableDeclarator(); }
    public Aspect_JavaP.methodBody_return methodBody() throws RecognitionException { return gJavaP.methodBody(); }
    public Aspect_JavaP.interfaceMethodDeclaratorRest_return interfaceMethodDeclaratorRest() throws RecognitionException { return gJavaP.interfaceMethodDeclaratorRest(); }
    public Aspect_JavaP.interfaceDeclaration_return interfaceDeclaration() throws RecognitionException { return gJavaP.interfaceDeclaration(); }
    public Aspect_JavaP.forControl_return forControl() throws RecognitionException { return gJavaP.forControl(); }
    public Aspect_JavaP.constantExpression_return constantExpression() throws RecognitionException { return gJavaP.constantExpression(); }
    public Aspect_JavaP.identifierSuffixRubbush_return identifierSuffixRubbush() throws RecognitionException { return gJavaP.identifierSuffixRubbush(); }
    public Aspect_JavaP.typeName_return typeName() throws RecognitionException { return gJavaP.typeName(); }
    public Aspect_JavaP.variableModifier_return variableModifier() throws RecognitionException { return gJavaP.variableModifier(); }
    public Aspect_JavaP.creator_return creator() throws RecognitionException { return gJavaP.creator(); }
    public Aspect_JavaP.annotationTypeElementDeclaration_return annotationTypeElementDeclaration() throws RecognitionException { return gJavaP.annotationTypeElementDeclaration(); }
    public Aspect_JavaP.interfaceConstant_return interfaceConstant() throws RecognitionException { return gJavaP.interfaceConstant(); }
    public Aspect_JavaP.classBody_return classBody() throws RecognitionException { return gJavaP.classBody(); }
    public Aspect_JavaP.catches_return catches() throws RecognitionException { return gJavaP.catches(); }
    public Aspect_JavaP.shiftExpression_return shiftExpression() throws RecognitionException { return gJavaP.shiftExpression(); }
    public Aspect_JavaP.typeArgument_return typeArgument() throws RecognitionException { return gJavaP.typeArgument(); }
    public Aspect_JavaP.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus() throws RecognitionException { return gJavaP.unaryExpressionNotPlusMinus(); }
    public Aspect_JavaP.importDeclaration_return importDeclaration() throws RecognitionException { return gJavaP.importDeclaration(); }
    public Aspect_JavaP.castExpression_return castExpression() throws RecognitionException { return gJavaP.castExpression(); }
    public Aspect_JavaP.elementValueArrayInitializer_return elementValueArrayInitializer() throws RecognitionException { return gJavaP.elementValueArrayInitializer(); }
    public Aspect_JavaP.variableDeclarators_return variableDeclarators() throws RecognitionException { return gJavaP.variableDeclarators(); }
    public Aspect_JavaP.elementValuePairs_return elementValuePairs() throws RecognitionException { return gJavaP.elementValuePairs(); }
    public Aspect_JavaP.annotationName_return annotationName() throws RecognitionException { return gJavaP.annotationName(); }
    public Aspect_JavaP.enumBodyDeclarations_return enumBodyDeclarations() throws RecognitionException { return gJavaP.enumBodyDeclarations(); }
    public Aspect_JavaP.classCreatorRest_return classCreatorRest() throws RecognitionException { return gJavaP.classCreatorRest(); }
    public Aspect_JavaP.typeParameter_return typeParameter() throws RecognitionException { return gJavaP.typeParameter(); }
    public Aspect_JavaP.normalClassDeclaration_return normalClassDeclaration() throws RecognitionException { return gJavaP.normalClassDeclaration(); }
    public Aspect_JavaP.forInit_return forInit() throws RecognitionException { return gJavaP.forInit(); }
    public Aspect_JavaP.instanceOfExpression_return instanceOfExpression() throws RecognitionException { return gJavaP.instanceOfExpression(); }
    public Aspect_JavaP.constructorDeclaration_return constructorDeclaration() throws RecognitionException { return gJavaP.constructorDeclaration(); }
    public Aspect_JavaP.additiveExpression_return additiveExpression() throws RecognitionException { return gJavaP.additiveExpression(); }
    public Aspect_JavaP.typeArguments_return typeArguments() throws RecognitionException { return gJavaP.typeArguments(); }
    public Aspect_JavaP.variableDeclaratorId_return variableDeclaratorId() throws RecognitionException { return gJavaP.variableDeclaratorId(); }
    public Aspect_JavaP.andExpression_return andExpression() throws RecognitionException { return gJavaP.andExpression(); }
    public Aspect_JavaP.typeBound_return typeBound() throws RecognitionException { return gJavaP.typeBound(); }
    public Aspect_JavaP.assignmentOperator_return assignmentOperator() throws RecognitionException { return gJavaP.assignmentOperator(); }
    public Aspect_JavaP.arrayInitializer_return arrayInitializer() throws RecognitionException { return gJavaP.arrayInitializer(); }
    public Aspect_JavaP.annotationMethodRest_return annotationMethodRest(TypeReference type) throws RecognitionException { return gJavaP.annotationMethodRest(type); }
    public Aspect_JavaP.createClassHereBecauseANTLRisAnnoying_return createClassHereBecauseANTLRisAnnoying() throws RecognitionException { return gJavaP.createClassHereBecauseANTLRisAnnoying(); }
    public Aspect_JavaP.modifiers_return modifiers() throws RecognitionException { return gJavaP.modifiers(); }
    public Aspect_JavaP.catchClause_return catchClause() throws RecognitionException { return gJavaP.catchClause(); }
    public Aspect_JavaP.modifier_return modifier() throws RecognitionException { return gJavaP.modifier(); }
    public Aspect_JavaP.annotationMethodOrConstantRest_return annotationMethodOrConstantRest(TypeReference type) throws RecognitionException { return gJavaP.annotationMethodOrConstantRest(type); }
    public Aspect_JavaP.literal_return literal() throws RecognitionException { return gJavaP.literal(); }
    public Aspect_JavaP.classOrInterfaceModifier_return classOrInterfaceModifier() throws RecognitionException { return gJavaP.classOrInterfaceModifier(); }
    public Aspect_JavaP.genericMethodOrConstructorRest_return genericMethodOrConstructorRest() throws RecognitionException { return gJavaP.genericMethodOrConstructorRest(); }
    public Aspect_JavaP.superSuffix_return superSuffix() throws RecognitionException { return gJavaP.superSuffix(); }
    public Aspect_JavaP.unaryExpression_return unaryExpression() throws RecognitionException { return gJavaP.unaryExpression(); }
    public Aspect_JavaP.nameAndParams_return nameAndParams() throws RecognitionException { return gJavaP.nameAndParams(); }
    public Aspect_JavaP.localVariableDeclarationStatement_return localVariableDeclarationStatement() throws RecognitionException { return gJavaP.localVariableDeclarationStatement(); }
    public Aspect_JavaP.classOrInterfaceModifiers_return classOrInterfaceModifiers() throws RecognitionException { return gJavaP.classOrInterfaceModifiers(); }
    public Aspect_JavaP.createdName_return createdName() throws RecognitionException { return gJavaP.createdName(); }
    public Aspect_JavaP.interfaceMethod_return interfaceMethod() throws RecognitionException { return gJavaP.interfaceMethod(); }
    public Aspect_JavaP.switchLabel_return switchLabel() throws RecognitionException { return gJavaP.switchLabel(); }
    public Aspect_JavaP.switchBlockStatementGroups_return switchBlockStatementGroups() throws RecognitionException { return gJavaP.switchBlockStatementGroups(); }
    public Aspect_JavaP.formalParameters_return formalParameters() throws RecognitionException { return gJavaP.formalParameters(); }
    public Aspect_JavaP.elementValuePair_return elementValuePair() throws RecognitionException { return gJavaP.elementValuePair(); }
    public Aspect_JavaP.conditionalAndExpression_return conditionalAndExpression() throws RecognitionException { return gJavaP.conditionalAndExpression(); }
    public Aspect_JavaP.typeList_return typeList() throws RecognitionException { return gJavaP.typeList(); }
    public Aspect_JavaP.typeParameters_return typeParameters() throws RecognitionException { return gJavaP.typeParameters(); }
    public Aspect_JavaP.constantDeclarator_return constantDeclarator() throws RecognitionException { return gJavaP.constantDeclarator(); }
    public Aspect_JavaP.voidMethodDeclaratorRest_return voidMethodDeclaratorRest() throws RecognitionException { return gJavaP.voidMethodDeclaratorRest(); }
    public Aspect_JavaP.explicitConstructorInvocation_return explicitConstructorInvocation() throws RecognitionException { return gJavaP.explicitConstructorInvocation(); }
    public Aspect_JavaP.constructorDeclaratorRest_return constructorDeclaratorRest() throws RecognitionException { return gJavaP.constructorDeclaratorRest(); }
    public Aspect_JavaP.conditionalExpression_return conditionalExpression() throws RecognitionException { return gJavaP.conditionalExpression(); }
    public Aspect_JavaP.statementExpression_return statementExpression() throws RecognitionException { return gJavaP.statementExpression(); }
    public Aspect_JavaP.annotationTypeDeclaration_return annotationTypeDeclaration() throws RecognitionException { return gJavaP.annotationTypeDeclaration(); }
    public Aspect_JavaP.annotationTypeBody_return annotationTypeBody() throws RecognitionException { return gJavaP.annotationTypeBody(); }

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
    static final String DFA8_eotS =
        "\22\uffff";
    static final String DFA8_eofS =
        "\1\2\21\uffff";
    static final String DFA8_minS =
        "\1\5\1\0\20\uffff";
    static final String DFA8_maxS =
        "\1\162\1\0\20\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\2\16\uffff\1\1";
    static final String DFA8_specialS =
        "\1\uffff\1\0\20\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\2\23\uffff\4\2\2\uffff\7\2\10\uffff\1\2\32\uffff\1\1\50"+
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
            return "281:8: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* )";
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
 

    public static final BitSet FOLLOW_annotations_in_compilationUnit80 = new BitSet(new long[]{0x0000403F92000020L,0x0000000000000200L});
    public static final BitSet FOLLOW_packageDeclaration_in_compilationUnit96 = new BitSet(new long[]{0x0000403F9E000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_importDeclaration_in_compilationUnit132 = new BitSet(new long[]{0x0000403F9E000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit153 = new BitSet(new long[]{0x0000403F96000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_compilationUnit202 = new BitSet(new long[]{0x0000403F96000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit237 = new BitSet(new long[]{0x0000403F96000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_packageDeclaration_in_compilationUnit292 = new BitSet(new long[]{0x0000403F9E000022L,0x0004000000000200L});
    public static final BitSet FOLLOW_importDeclaration_in_compilationUnit341 = new BitSet(new long[]{0x0000403F9E000022L,0x0004000000000200L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit378 = new BitSet(new long[]{0x0000403F96000022L,0x0004000000000200L});
    public static final BitSet FOLLOW_aspect_in_compilationUnit416 = new BitSet(new long[]{0x0000403F96000022L,0x0004000000000200L});
    public static final BitSet FOLLOW_114_in_aspect465 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_Identifier_in_aspect469 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_aspect475 = new BitSet(new long[]{0x0000200000000000L,0x0068000000000000L});
    public static final BitSet FOLLOW_advice_in_aspect484 = new BitSet(new long[]{0x0000200000000000L,0x0068000000000000L});
    public static final BitSet FOLLOW_pointcut_in_aspect491 = new BitSet(new long[]{0x0000200000000000L,0x0068000000000000L});
    public static final BitSet FOLLOW_45_in_aspect499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_115_in_pointcut526 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_pointcutDecl_in_pointcut530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_formalParameters_in_pointcut534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_pointcut536 = new BitSet(new long[]{0x0000000000000000L,0x0011000000000004L});
    public static final BitSet FOLLOW_pointcutExpression_in_pointcut540 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_pointcut542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_pointcutDecl564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_pointcutExpression588 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_pointcutExpression590 = new BitSet(new long[]{0x0000000000000000L,0x0011000000000004L});
    public static final BitSet FOLLOW_pointcutExpression_in_pointcutExpression594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_pointcutExpression603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutAtom_in_pointcutExpressionOr626 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_98_in_pointcutExpressionOr628 = new BitSet(new long[]{0x0000000000000000L,0x0011000000000004L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_pointcutExpressionOr632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutAtom_in_pointcutExpressionOr641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_pointcutAtom666 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_pointcutAtom668 = new BitSet(new long[]{0xFF00800000000010L});
    public static final BitSet FOLLOW_methodReference_in_pointcutAtom672 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_112_in_pointcutAtom681 = new BitSet(new long[]{0x0000000000000000L,0x0011000000000004L});
    public static final BitSet FOLLOW_pointcutAtom_in_pointcutAtom685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_pointcutAtom692 = new BitSet(new long[]{0x0000000000000000L,0x0011000000000004L});
    public static final BitSet FOLLOW_pointcutExpression_in_pointcutAtom696 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_advice722 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_advice730 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_pointcutDecl_in_advice734 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_advice736 = new BitSet(new long[]{0x0000020000000010L,0x0000000000000008L});
    public static final BitSet FOLLOW_Identifier_in_advice738 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_41_in_advice742 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_Identifier_in_advice744 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_advice748 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_methodBody_in_advice759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodReferenceType_in_methodReference783 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_fqn_in_methodReference787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_voidType_in_methodReferenceType807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_methodReferenceType816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_fqn854 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_fqn856 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_simpleMethodHeader_in_fqn864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_simpleMethodHeader903 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_formalParameterTypes_in_simpleMethodHeader907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_formalParameterTypes949 = new BitSet(new long[]{0xFF00800000000010L,0x0000000000000008L});
    public static final BitSet FOLLOW_formalParameterTypeDecls_in_formalParameterTypes954 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_formalParameterTypes960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameterTypeDecls985 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_formalParameterTypeDecls988 = new BitSet(new long[]{0xFF00800000000010L});
    public static final BitSet FOLLOW_formalParameterTypeDecls_in_formalParameterTypeDecls992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotations_in_synpred5_Aspect80 = new BitSet(new long[]{0x0000403F92000020L,0x0000000000000200L});
    public static final BitSet FOLLOW_packageDeclaration_in_synpred5_Aspect96 = new BitSet(new long[]{0x0000403F9E000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_importDeclaration_in_synpred5_Aspect132 = new BitSet(new long[]{0x0000403F9E000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Aspect153 = new BitSet(new long[]{0x0000403F96000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred5_Aspect202 = new BitSet(new long[]{0x0000403F96000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Aspect237 = new BitSet(new long[]{0x0000403F96000022L,0x0000000000000200L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_synpred12_Aspect588 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_synpred12_Aspect590 = new BitSet(new long[]{0x0000000000000000L,0x0011000000000004L});
    public static final BitSet FOLLOW_pointcutExpression_in_synpred12_Aspect594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutAtom_in_synpred13_Aspect626 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_98_in_synpred13_Aspect628 = new BitSet(new long[]{0x0000000000000000L,0x0011000000000004L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_synpred13_Aspect632 = new BitSet(new long[]{0x0000000000000002L});

}