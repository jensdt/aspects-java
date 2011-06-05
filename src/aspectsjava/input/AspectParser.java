// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g 2011-05-28 18:41:05

package aspectsjava.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jnome.core.language.Java;
import jnome.core.type.JavaTypeReference;
import jnome.input.JavaFactory;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

import aspectsjava.model.advice.JavaAdvice;
import aspectsjava.model.pointcut.expression.methodinvocation.annotated.AnnotatedMethodInvocationExpression;
import aspectsjava.model.pointcut.expression.methodinvocation.annotated.AnnotationReference;
import aspectsjava.model.pointcut.expression.methodinvocation.signature.MethodReference;
import aspectsjava.model.pointcut.expression.methodinvocation.signature.QualifiedMethodHeader;
import aspectsjava.model.pointcut.expression.methodinvocation.signature.SignatureMethodInvocationPointcutExpression;
import aspectsjava.model.pointcut.expression.methodinvocation.signature.SimpleNameDeclarationWithParameterTypesHeader;
import aspectsjava.model.pointcut.expression.within.WithinMethodPointcutExpression;
import chameleon.aspects.Aspect;
import chameleon.aspects.advice.Advice;
import chameleon.aspects.advice.AdviceReturnStatement;
import chameleon.aspects.advice.types.After;
import chameleon.aspects.advice.types.Around;
import chameleon.aspects.advice.types.Before;
import chameleon.aspects.advice.types.ProceedCall;
import chameleon.aspects.advice.types.Returning;
import chameleon.aspects.advice.types.Throwing;
import chameleon.aspects.pointcut.Pointcut;
import chameleon.aspects.pointcut.PointcutReference;
import chameleon.aspects.pointcut.expression.PointcutExpression;
import chameleon.aspects.pointcut.expression.SubtypeMarker;
import chameleon.aspects.pointcut.expression.dynamicexpression.ArgsPointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.IfPointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.TargetTypePointcutExpression;
import chameleon.aspects.pointcut.expression.dynamicexpression.ThisTypePointcutExpression;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionAnd;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionNot;
import chameleon.aspects.pointcut.expression.generic.PointcutExpressionOr;
import chameleon.aspects.pointcut.expression.namedpointcut.NamedPointcutExpression;
import chameleon.aspects.pointcut.expression.staticexpression.MemberReference;
import chameleon.aspects.pointcut.expression.staticexpression.cast.CastPointcutExpression;
import chameleon.aspects.pointcut.expression.staticexpression.catchclause.EmptyCatchClausePointcutExpression;
import chameleon.aspects.pointcut.expression.staticexpression.catchclause.TypeCatchClausePointcutExpression;
import chameleon.aspects.pointcut.expression.staticexpression.fieldAccess.FieldReadPointcutExpression;
import chameleon.aspects.pointcut.expression.staticexpression.within.WithinPointcutExpression;
import chameleon.aspects.pointcut.expression.staticexpression.within.WithinTypePointcutExpression;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.declaration.CompositeQualifiedName;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersHeader;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.declaration.TargetDeclaration;
import chameleon.core.expression.Expression;
import chameleon.core.expression.NamedTarget;
import chameleon.core.expression.NamedTargetExpression;
import chameleon.core.language.Language;
import chameleon.core.modifier.Modifier;
import chameleon.core.namespace.Namespace;
import chameleon.core.namespace.NamespaceOrTypeReference;
import chameleon.core.namespace.NamespaceReference;
import chameleon.core.namespacepart.DemandImport;
import chameleon.core.namespacepart.NamespacePart;
import chameleon.core.reference.CrossReference;
import chameleon.core.statement.Block;
import chameleon.core.statement.Statement;
import chameleon.core.variable.FormalParameter;
import chameleon.oo.type.Type;
import chameleon.oo.type.TypeReference;
import chameleon.oo.type.inheritance.SubtypeRelation;
import chameleon.support.expression.AssignmentExpression;
import chameleon.support.input.ChameleonParser;
import chameleon.support.member.simplename.operator.infix.InfixOperatorInvocation;
import chameleon.support.statement.LocalClassStatement;
import chameleon.support.statement.ReturnStatement;

public class AspectParser extends ChameleonParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Identifier", "ENUM", "FloatingPointLiteral", "CharacterLiteral", "StringLiteral", "HexLiteral", "OctalLiteral", "DecimalLiteral", "ASSERT", "HexDigit", "IntegerTypeSuffix", "Exponent", "FloatTypeSuffix", "EscapeSequence", "UnicodeEscape", "OctalEscape", "Letter", "JavaIDDigit", "IdentifierWithWC", "WS", "COMMENT", "LINE_COMMENT", "'package'", "';'", "'import'", "'static'", "'.'", "'*'", "'public'", "'protected'", "'private'", "'abstract'", "'final'", "'strictfp'", "'class'", "'extends'", "'implements'", "'<'", "','", "'>'", "'&'", "'{'", "'}'", "'interface'", "'void'", "'['", "']'", "'throws'", "'='", "'native'", "'synchronized'", "'transient'", "'volatile'", "'boolean'", "'char'", "'byte'", "'short'", "'int'", "'long'", "'float'", "'double'", "'?'", "'super'", "'('", "')'", "'...'", "'this'", "'null'", "'true'", "'false'", "'@'", "'default'", "':'", "'if'", "'else'", "'for'", "'while'", "'do'", "'try'", "'finally'", "'switch'", "'return'", "'throw'", "'break'", "'continue'", "'catch'", "'case'", "'+='", "'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'%='", "'||'", "'&&'", "'|'", "'^'", "'=='", "'!='", "'instanceof'", "'+'", "'-'", "'/'", "'%'", "'++'", "'--'", "'~'", "'!'", "'new'", "'aspect'", "'pointcut'", "'within'", "'call'", "'callAnnotated'", "'emptyHandler'", "'fieldRead'", "'typeHandler'", "'cast'", "'arguments'", "'thisType'", "'targetType'", "'before_'", "'after_'", "'around_'", "'returning'", "'throwing'", "'proceed'"
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
    public static final int T__132=132;
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
            this.state.ruleMemo = new HashMap[492+1];
             
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:292:1: compilationUnit returns [CompilationUnit element] : ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* ) ;
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:297:5: ( ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* ) )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:297:8: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* )
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:297:8: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* )
            int alt8=2;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:297:10: annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
                    {
                    pushFollow(FOLLOW_annotations_in_compilationUnit80);
                    annotations1=annotations();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotations1.getTree());
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:298:9: (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
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
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:298:13: np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )*
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
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:303:13: (imp= importDeclaration )*
                            loop1:
                            do {
                                int alt1=2;
                                int LA1_0 = input.LA(1);

                                if ( (LA1_0==28) ) {
                                    alt1=1;
                                }


                                switch (alt1) {
                            	case 1 :
                            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:303:14: imp= importDeclaration
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

                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:304:13: (typech= typeDeclaration )*
                            loop2:
                            do {
                                int alt2=2;
                                int LA2_0 = input.LA(1);

                                if ( (LA2_0==ENUM||LA2_0==27||LA2_0==29||(LA2_0>=32 && LA2_0<=38)||LA2_0==47||LA2_0==74) ) {
                                    alt2=1;
                                }


                                switch (alt2) {
                            	case 1 :
                            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:304:14: typech= typeDeclaration
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
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:308:13: cd= classOrInterfaceDeclaration (typech= typeDeclaration )*
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
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:314:13: (typech= typeDeclaration )*
                            loop3:
                            do {
                                int alt3=2;
                                int LA3_0 = input.LA(1);

                                if ( (LA3_0==ENUM||LA3_0==27||LA3_0==29||(LA3_0>=32 && LA3_0<=38)||LA3_0==47||LA3_0==74) ) {
                                    alt3=1;
                                }


                                switch (alt3) {
                            	case 1 :
                            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:314:14: typech= typeDeclaration
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:319:9: (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )*
                    {
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:319:9: (np= packageDeclaration )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==26) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:319:10: np= packageDeclaration
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:331:9: (imp= importDeclaration )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==28) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:331:10: imp= importDeclaration
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

                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:334:9: (typech= typeDeclaration | ad= aspect )*
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
                    	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:334:10: typech= typeDeclaration
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
                    	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:339:11: ad= aspect
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:346:1: aspect returns [Aspect element] : asp= 'aspect' name= Identifier '{' ( (adv= advice ) | (ptc= pointcut ) )* '}' ;
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:348:2: (asp= 'aspect' name= Identifier '{' ( (adv= advice ) | (ptc= pointcut ) )* '}' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:348:4: asp= 'aspect' name= Identifier '{' ( (adv= advice ) | (ptc= pointcut ) )* '}'
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:350:2: ( (adv= advice ) | (ptc= pointcut ) )*
            loop9:
            do {
                int alt9=3;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==Identifier||LA9_0==48||(LA9_0>=57 && LA9_0<=64)||(LA9_0>=127 && LA9_0<=129)) ) {
                    alt9=1;
                }
                else if ( (LA9_0==116) ) {
                    alt9=2;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:350:3: (adv= advice )
            	    {
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:350:3: (adv= advice )
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:350:4: adv= advice
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
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:350:56: (ptc= pointcut )
            	    {
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:350:56: (ptc= pointcut )
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:350:57: ptc= pointcut
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:358:1: pointcut returns [Pointcut element] : pct= 'pointcut' decl= pointcutDecl pars= formalParameters ':' expr= pointcutExpression ';' ;
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:360:2: (pct= 'pointcut' decl= pointcutDecl pars= formalParameters ':' expr= pointcutExpression ';' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:360:4: pct= 'pointcut' decl= pointcutDecl pars= formalParameters ':' expr= pointcutExpression ';'
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:371:1: pointcutDecl returns [String element] : name= Identifier ;
    public final AspectParser.pointcutDecl_return pointcutDecl() throws RecognitionException {
        AspectParser.pointcutDecl_return retval = new AspectParser.pointcutDecl_return();
        retval.start = input.LT(1);
        int pointcutDecl_StartIndex = input.index();
        Object root_0 = null;

        Token name=null;

        Object name_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 411) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:372:2: (name= Identifier )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:372:4: name= Identifier
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:375:1: pointcutExpression returns [PointcutExpression element] : (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression | expr= pointcutExpressionOr );
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:377:2: (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression | expr= pointcutExpressionOr )
            int alt10=2;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:377:4: expr1= pointcutExpressionOr '&&' expr2= pointcutExpression
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:378:4: expr= pointcutExpressionOr
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:381:1: pointcutExpressionOr returns [PointcutExpression element] : (expr1= pointcutAtom '||' expr2= pointcutExpressionOr | expr= pointcutAtom );
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
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:383:2: (expr1= pointcutAtom '||' expr2= pointcutExpressionOr | expr= pointcutAtom )
            int alt11=2;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:383:4: expr1= pointcutAtom '||' expr2= pointcutExpressionOr
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:384:4: expr= pointcutAtom
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:387:1: subtypeMarker returns [SubtypeMarker element] : '+' ;
    public final AspectParser.subtypeMarker_return subtypeMarker() throws RecognitionException {
        AspectParser.subtypeMarker_return retval = new AspectParser.subtypeMarker_return();
        retval.start = input.LT(1);
        int subtypeMarker_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal8=null;

        Object char_literal8_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 414) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:388:2: ( '+' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:388:4: '+'
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

    public static class withinType_return extends ParserRuleReturnScope {
        public WithinTypePointcutExpression element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "withinType"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:390:1: withinType returns [WithinTypePointcutExpression element] : withinToken= 'within' '(' withinTypeRef= type (marker= subtypeMarker )? ')' ;
    public final AspectParser.withinType_return withinType() throws RecognitionException {
        AspectParser.withinType_return retval = new AspectParser.withinType_return();
        retval.start = input.LT(1);
        int withinType_StartIndex = input.index();
        Object root_0 = null;

        Token withinToken=null;
        Token char_literal9=null;
        Token char_literal10=null;
        Aspect_JavaP.type_return withinTypeRef = null;

        AspectParser.subtypeMarker_return marker = null;


        Object withinToken_tree=null;
        Object char_literal9_tree=null;
        Object char_literal10_tree=null;

        WithinTypePointcutExpression within = new WithinTypePointcutExpression();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 415) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:392:2: (withinToken= 'within' '(' withinTypeRef= type (marker= subtypeMarker )? ')' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:392:4: withinToken= 'within' '(' withinTypeRef= type (marker= subtypeMarker )? ')'
            {
            root_0 = (Object)adaptor.nil();

            withinToken=(Token)match(input,117,FOLLOW_117_in_withinType681); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            withinToken_tree = (Object)adaptor.create(withinToken);
            adaptor.addChild(root_0, withinToken_tree);
            }
            char_literal9=(Token)match(input,67,FOLLOW_67_in_withinType683); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal9_tree = (Object)adaptor.create(char_literal9);
            adaptor.addChild(root_0, char_literal9_tree);
            }
            pushFollow(FOLLOW_type_in_withinType687);
            withinTypeRef=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, withinTypeRef.getTree());
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:392:48: (marker= subtypeMarker )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==106) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:392:49: marker= subtypeMarker
                    {
                    pushFollow(FOLLOW_subtypeMarker_in_withinType692);
                    marker=subtypeMarker();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, marker.getTree());
                    if ( state.backtracking==0 ) {
                      within.setSubtypeMarker(marker.element);
                    }

                    }
                    break;

            }

            char_literal10=(Token)match(input,68,FOLLOW_68_in_withinType698); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal10_tree = (Object)adaptor.create(char_literal10);
            adaptor.addChild(root_0, char_literal10_tree);
            }
            if ( state.backtracking==0 ) {
              retval.element = within; within.setTypeReference(withinTypeRef.element); setKeyword(retval.element, withinToken); 
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
            if ( state.backtracking>0 ) { memoize(input, 415, withinType_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "withinType"

    public static class withinMethod_return extends ParserRuleReturnScope {
        public WithinMethodPointcutExpression element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "withinMethod"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:394:1: withinMethod returns [WithinMethodPointcutExpression element] : withinToken= 'within' '(' metref= methodReference ')' ;
    public final AspectParser.withinMethod_return withinMethod() throws RecognitionException {
        AspectParser.withinMethod_return retval = new AspectParser.withinMethod_return();
        retval.start = input.LT(1);
        int withinMethod_StartIndex = input.index();
        Object root_0 = null;

        Token withinToken=null;
        Token char_literal11=null;
        Token char_literal12=null;
        AspectParser.methodReference_return metref = null;


        Object withinToken_tree=null;
        Object char_literal11_tree=null;
        Object char_literal12_tree=null;

        WithinMethodPointcutExpression within = new WithinMethodPointcutExpression();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 416) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:396:2: (withinToken= 'within' '(' metref= methodReference ')' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:396:4: withinToken= 'within' '(' metref= methodReference ')'
            {
            root_0 = (Object)adaptor.nil();

            withinToken=(Token)match(input,117,FOLLOW_117_in_withinMethod720); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            withinToken_tree = (Object)adaptor.create(withinToken);
            adaptor.addChild(root_0, withinToken_tree);
            }
            char_literal11=(Token)match(input,67,FOLLOW_67_in_withinMethod722); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal11_tree = (Object)adaptor.create(char_literal11);
            adaptor.addChild(root_0, char_literal11_tree);
            }
            pushFollow(FOLLOW_methodReference_in_withinMethod726);
            metref=methodReference();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, metref.getTree());
            char_literal12=(Token)match(input,68,FOLLOW_68_in_withinMethod728); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal12_tree = (Object)adaptor.create(char_literal12);
            adaptor.addChild(root_0, char_literal12_tree);
            }
            if ( state.backtracking==0 ) {
              retval.element = within; within.setMethodReference(metref.element); setKeyword(retval.element, withinToken); 
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
            if ( state.backtracking>0 ) { memoize(input, 416, withinMethod_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "withinMethod"

    public static class within_return extends ParserRuleReturnScope {
        public WithinPointcutExpression element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "within"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:398:1: within returns [WithinPointcutExpression element] : (withinTypeCL= withinType | withinMethodCL= withinMethod );
    public final AspectParser.within_return within() throws RecognitionException {
        AspectParser.within_return retval = new AspectParser.within_return();
        retval.start = input.LT(1);
        int within_StartIndex = input.index();
        Object root_0 = null;

        AspectParser.withinType_return withinTypeCL = null;

        AspectParser.withinMethod_return withinMethodCL = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 417) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:399:2: (withinTypeCL= withinType | withinMethodCL= withinMethod )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==117) ) {
                int LA13_1 = input.LA(2);

                if ( (synpred15_Aspect()) ) {
                    alt13=1;
                }
                else if ( (true) ) {
                    alt13=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:399:4: withinTypeCL= withinType
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_withinType_in_within745);
                    withinTypeCL=withinType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, withinTypeCL.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = withinTypeCL.element;
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:400:4: withinMethodCL= withinMethod
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_withinMethod_in_within754);
                    withinMethodCL=withinMethod();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, withinMethodCL.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = withinMethodCL.element;
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
            if ( state.backtracking>0 ) { memoize(input, 417, within_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "within"

    public static class pointcutAtom_return extends ParserRuleReturnScope {
        public PointcutExpression element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointcutAtom"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:404:1: pointcutAtom returns [PointcutExpression element] : (cl= 'call' '(' metref= methodReference ')' | clA= 'callAnnotated' '(' annot= Identifier ')' | emptyCatch= 'emptyHandler' | fieldRead= 'fieldRead' '(' fieldreadtype= type fieldref= fieldReference ')' | handler= 'typeHandler' '(' exceptionType= type ')' | cast= 'cast' '(' castType= type ')' | namedRef= namedPointcutReference | withinCl= within | getargs= 'arguments' t= typesOrParameters | thisType= 'thisType' '(' exp= expression ')' | targetType= 'targetType' '(' exp= expression ')' | ifCheck= 'if' '(' exp= expression ')' | '!' expr1= pointcutAtom | '(' expr2= pointcutExpression ')' );
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
        Token char_literal28=null;
        Token char_literal29=null;
        Token char_literal30=null;
        Token char_literal31=null;
        AspectParser.methodReference_return metref = null;

        Aspect_JavaP.type_return fieldreadtype = null;

        AspectParser.fieldReference_return fieldref = null;

        Aspect_JavaP.type_return exceptionType = null;

        Aspect_JavaP.type_return castType = null;

        AspectParser.namedPointcutReference_return namedRef = null;

        AspectParser.within_return withinCl = null;

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
        Object char_literal28_tree=null;
        Object char_literal29_tree=null;
        Object char_literal30_tree=null;
        Object char_literal31_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 418) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:406:2: (cl= 'call' '(' metref= methodReference ')' | clA= 'callAnnotated' '(' annot= Identifier ')' | emptyCatch= 'emptyHandler' | fieldRead= 'fieldRead' '(' fieldreadtype= type fieldref= fieldReference ')' | handler= 'typeHandler' '(' exceptionType= type ')' | cast= 'cast' '(' castType= type ')' | namedRef= namedPointcutReference | withinCl= within | getargs= 'arguments' t= typesOrParameters | thisType= 'thisType' '(' exp= expression ')' | targetType= 'targetType' '(' exp= expression ')' | ifCheck= 'if' '(' exp= expression ')' | '!' expr1= pointcutAtom | '(' expr2= pointcutExpression ')' )
            int alt14=14;
            switch ( input.LA(1) ) {
            case 118:
                {
                alt14=1;
                }
                break;
            case 119:
                {
                alt14=2;
                }
                break;
            case 120:
                {
                alt14=3;
                }
                break;
            case 121:
                {
                alt14=4;
                }
                break;
            case 122:
                {
                alt14=5;
                }
                break;
            case 123:
                {
                alt14=6;
                }
                break;
            case Identifier:
                {
                alt14=7;
                }
                break;
            case 117:
                {
                alt14=8;
                }
                break;
            case 124:
                {
                alt14=9;
                }
                break;
            case 125:
                {
                alt14=10;
                }
                break;
            case 126:
                {
                alt14=11;
                }
                break;
            case 77:
                {
                alt14=12;
                }
                break;
            case 113:
                {
                alt14=13;
                }
                break;
            case 67:
                {
                alt14=14;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:406:4: cl= 'call' '(' metref= methodReference ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    cl=(Token)match(input,118,FOLLOW_118_in_pointcutAtom779); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    cl_tree = (Object)adaptor.create(cl);
                    adaptor.addChild(root_0, cl_tree);
                    }
                    char_literal13=(Token)match(input,67,FOLLOW_67_in_pointcutAtom781); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal13_tree = (Object)adaptor.create(char_literal13);
                    adaptor.addChild(root_0, char_literal13_tree);
                    }
                    pushFollow(FOLLOW_methodReference_in_pointcutAtom785);
                    metref=methodReference();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, metref.getTree());
                    char_literal14=(Token)match(input,68,FOLLOW_68_in_pointcutAtom787); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal14_tree = (Object)adaptor.create(char_literal14);
                    adaptor.addChild(root_0, char_literal14_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.element = new SignatureMethodInvocationPointcutExpression(metref.element); setKeyword(retval.element, cl);
                    }

                    }
                    break;
                case 2 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:407:4: clA= 'callAnnotated' '(' annot= Identifier ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    clA=(Token)match(input,119,FOLLOW_119_in_pointcutAtom796); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    clA_tree = (Object)adaptor.create(clA);
                    adaptor.addChild(root_0, clA_tree);
                    }
                    char_literal15=(Token)match(input,67,FOLLOW_67_in_pointcutAtom798); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal15_tree = (Object)adaptor.create(char_literal15);
                    adaptor.addChild(root_0, char_literal15_tree);
                    }
                    annot=(Token)match(input,Identifier,FOLLOW_Identifier_in_pointcutAtom802); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    annot_tree = (Object)adaptor.create(annot);
                    adaptor.addChild(root_0, annot_tree);
                    }
                    char_literal16=(Token)match(input,68,FOLLOW_68_in_pointcutAtom804); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal16_tree = (Object)adaptor.create(char_literal16);
                    adaptor.addChild(root_0, char_literal16_tree);
                    }
                    if ( state.backtracking==0 ) {
                      AnnotatedMethodInvocationExpression result = new AnnotatedMethodInvocationExpression(); result.setReference(new AnnotationReference((annot!=null?annot.getText():null))); retval.element = result; setKeyword(retval.element, clA);
                    }

                    }
                    break;
                case 3 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:408:4: emptyCatch= 'emptyHandler'
                    {
                    root_0 = (Object)adaptor.nil();

                    emptyCatch=(Token)match(input,120,FOLLOW_120_in_pointcutAtom813); if (state.failed) return retval;
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:409:4: fieldRead= 'fieldRead' '(' fieldreadtype= type fieldref= fieldReference ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    fieldRead=(Token)match(input,121,FOLLOW_121_in_pointcutAtom822); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    fieldRead_tree = (Object)adaptor.create(fieldRead);
                    adaptor.addChild(root_0, fieldRead_tree);
                    }
                    char_literal17=(Token)match(input,67,FOLLOW_67_in_pointcutAtom824); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal17_tree = (Object)adaptor.create(char_literal17);
                    adaptor.addChild(root_0, char_literal17_tree);
                    }
                    pushFollow(FOLLOW_type_in_pointcutAtom828);
                    fieldreadtype=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fieldreadtype.getTree());
                    pushFollow(FOLLOW_fieldReference_in_pointcutAtom832);
                    fieldref=fieldReference();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fieldref.getTree());
                    char_literal18=(Token)match(input,68,FOLLOW_68_in_pointcutAtom834); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal18_tree = (Object)adaptor.create(char_literal18);
                    adaptor.addChild(root_0, char_literal18_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.element = new FieldReadPointcutExpression(fieldreadtype.element, fieldref.element); setKeyword(retval.element, fieldRead); 
                    }

                    }
                    break;
                case 5 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:410:4: handler= 'typeHandler' '(' exceptionType= type ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    handler=(Token)match(input,122,FOLLOW_122_in_pointcutAtom843); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    handler_tree = (Object)adaptor.create(handler);
                    adaptor.addChild(root_0, handler_tree);
                    }
                    char_literal19=(Token)match(input,67,FOLLOW_67_in_pointcutAtom845); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal19_tree = (Object)adaptor.create(char_literal19);
                    adaptor.addChild(root_0, char_literal19_tree);
                    }
                    pushFollow(FOLLOW_type_in_pointcutAtom849);
                    exceptionType=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exceptionType.getTree());
                    char_literal20=(Token)match(input,68,FOLLOW_68_in_pointcutAtom851); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal20_tree = (Object)adaptor.create(char_literal20);
                    adaptor.addChild(root_0, char_literal20_tree);
                    }
                    if ( state.backtracking==0 ) {
                      TypeCatchClausePointcutExpression catchHandler = new TypeCatchClausePointcutExpression(); catchHandler.setExceptionType(exceptionType.element); retval.element = catchHandler; setKeyword(retval.element, handler); 
                    }

                    }
                    break;
                case 6 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:411:4: cast= 'cast' '(' castType= type ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    cast=(Token)match(input,123,FOLLOW_123_in_pointcutAtom860); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    cast_tree = (Object)adaptor.create(cast);
                    adaptor.addChild(root_0, cast_tree);
                    }
                    char_literal21=(Token)match(input,67,FOLLOW_67_in_pointcutAtom862); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal21_tree = (Object)adaptor.create(char_literal21);
                    adaptor.addChild(root_0, char_literal21_tree);
                    }
                    pushFollow(FOLLOW_type_in_pointcutAtom866);
                    castType=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, castType.getTree());
                    char_literal22=(Token)match(input,68,FOLLOW_68_in_pointcutAtom868); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal22_tree = (Object)adaptor.create(char_literal22);
                    adaptor.addChild(root_0, char_literal22_tree);
                    }
                    if ( state.backtracking==0 ) {
                       retval.element = new CastPointcutExpression(castType.element); setKeyword(retval.element, cast); 
                    }

                    }
                    break;
                case 7 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:412:4: namedRef= namedPointcutReference
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_namedPointcutReference_in_pointcutAtom877);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:413:4: withinCl= within
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_within_in_pointcutAtom886);
                    withinCl=within();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, withinCl.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = withinCl.element;
                    }

                    }
                    break;
                case 9 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:415:4: getargs= 'arguments' t= typesOrParameters
                    {
                    root_0 = (Object)adaptor.nil();

                    getargs=(Token)match(input,124,FOLLOW_124_in_pointcutAtom897); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    getargs_tree = (Object)adaptor.create(getargs);
                    adaptor.addChild(root_0, getargs_tree);
                    }
                    pushFollow(FOLLOW_typesOrParameters_in_pointcutAtom901);
                    t=typesOrParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if ( state.backtracking==0 ) {
                      ArgsPointcutExpression expr = new ArgsPointcutExpression(); expr.addAll(t.element); retval.element = expr; setKeyword(retval.element, getargs); 
                    }

                    }
                    break;
                case 10 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:416:4: thisType= 'thisType' '(' exp= expression ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    thisType=(Token)match(input,125,FOLLOW_125_in_pointcutAtom910); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    thisType_tree = (Object)adaptor.create(thisType);
                    adaptor.addChild(root_0, thisType_tree);
                    }
                    char_literal23=(Token)match(input,67,FOLLOW_67_in_pointcutAtom912); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal23_tree = (Object)adaptor.create(char_literal23);
                    adaptor.addChild(root_0, char_literal23_tree);
                    }
                    pushFollow(FOLLOW_expression_in_pointcutAtom916);
                    exp=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
                    char_literal24=(Token)match(input,68,FOLLOW_68_in_pointcutAtom918); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal24_tree = (Object)adaptor.create(char_literal24);
                    adaptor.addChild(root_0, char_literal24_tree);
                    }
                    if ( state.backtracking==0 ) {
                      ThisTypePointcutExpression expr = new ThisTypePointcutExpression((NamedTargetExpression) exp.element); retval.element = expr; setKeyword(retval.element, thisType); 
                    }

                    }
                    break;
                case 11 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:417:4: targetType= 'targetType' '(' exp= expression ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    targetType=(Token)match(input,126,FOLLOW_126_in_pointcutAtom927); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    targetType_tree = (Object)adaptor.create(targetType);
                    adaptor.addChild(root_0, targetType_tree);
                    }
                    char_literal25=(Token)match(input,67,FOLLOW_67_in_pointcutAtom929); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal25_tree = (Object)adaptor.create(char_literal25);
                    adaptor.addChild(root_0, char_literal25_tree);
                    }
                    pushFollow(FOLLOW_expression_in_pointcutAtom933);
                    exp=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
                    char_literal26=(Token)match(input,68,FOLLOW_68_in_pointcutAtom935); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal26_tree = (Object)adaptor.create(char_literal26);
                    adaptor.addChild(root_0, char_literal26_tree);
                    }
                    if ( state.backtracking==0 ) {
                      TargetTypePointcutExpression expr = new TargetTypePointcutExpression((NamedTargetExpression) exp.element); retval.element = expr; setKeyword(retval.element, targetType); 
                    }

                    }
                    break;
                case 12 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:418:4: ifCheck= 'if' '(' exp= expression ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    ifCheck=(Token)match(input,77,FOLLOW_77_in_pointcutAtom944); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ifCheck_tree = (Object)adaptor.create(ifCheck);
                    adaptor.addChild(root_0, ifCheck_tree);
                    }
                    char_literal27=(Token)match(input,67,FOLLOW_67_in_pointcutAtom946); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal27_tree = (Object)adaptor.create(char_literal27);
                    adaptor.addChild(root_0, char_literal27_tree);
                    }
                    pushFollow(FOLLOW_expression_in_pointcutAtom950);
                    exp=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
                    char_literal28=(Token)match(input,68,FOLLOW_68_in_pointcutAtom952); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal28_tree = (Object)adaptor.create(char_literal28);
                    adaptor.addChild(root_0, char_literal28_tree);
                    }
                    if ( state.backtracking==0 ) {
                      IfPointcutExpression expr = new IfPointcutExpression(exp.element); retval.element = expr; setKeyword(retval.element, ifCheck);
                    }

                    }
                    break;
                case 13 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:419:4: '!' expr1= pointcutAtom
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal29=(Token)match(input,113,FOLLOW_113_in_pointcutAtom959); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal29_tree = (Object)adaptor.create(char_literal29);
                    adaptor.addChild(root_0, char_literal29_tree);
                    }
                    pushFollow(FOLLOW_pointcutAtom_in_pointcutAtom963);
                    expr1=pointcutAtom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr1.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element = new PointcutExpressionNot(expr1.element);
                    }

                    }
                    break;
                case 14 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:420:4: '(' expr2= pointcutExpression ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal30=(Token)match(input,67,FOLLOW_67_in_pointcutAtom970); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal30_tree = (Object)adaptor.create(char_literal30);
                    adaptor.addChild(root_0, char_literal30_tree);
                    }
                    pushFollow(FOLLOW_pointcutExpression_in_pointcutAtom974);
                    expr2=pointcutExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr2.getTree());
                    char_literal31=(Token)match(input,68,FOLLOW_68_in_pointcutAtom976); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal31_tree = (Object)adaptor.create(char_literal31);
                    adaptor.addChild(root_0, char_literal31_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 418, pointcutAtom_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:423:1: namedPointcutReference returns [PointcutReference element] : decl= pointcutDecl '(' (params= argParams )? end= ')' ;
    public final AspectParser.namedPointcutReference_return namedPointcutReference() throws RecognitionException {
        AspectParser.namedPointcutReference_return retval = new AspectParser.namedPointcutReference_return();
        retval.start = input.LT(1);
        int namedPointcutReference_StartIndex = input.index();
        Object root_0 = null;

        Token end=null;
        Token char_literal32=null;
        AspectParser.pointcutDecl_return decl = null;

        AspectParser.argParams_return params = null;


        Object end_tree=null;
        Object char_literal32_tree=null;

        List<NamedTargetExpression> arguments = new ArrayList<NamedTargetExpression>();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 419) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:425:2: (decl= pointcutDecl '(' (params= argParams )? end= ')' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:425:4: decl= pointcutDecl '(' (params= argParams )? end= ')'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_pointcutDecl_in_namedPointcutReference999);
            decl=pointcutDecl();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, decl.getTree());
            char_literal32=(Token)match(input,67,FOLLOW_67_in_namedPointcutReference1001); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal32_tree = (Object)adaptor.create(char_literal32);
            adaptor.addChild(root_0, char_literal32_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:425:26: (params= argParams )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==Identifier) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:425:27: params= argParams
                    {
                    pushFollow(FOLLOW_argParams_in_namedPointcutReference1006);
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

            end=(Token)match(input,68,FOLLOW_68_in_namedPointcutReference1014); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 419, namedPointcutReference_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "namedPointcutReference"

    public static class fieldReference_return extends ParserRuleReturnScope {
        public MemberReference element;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fieldReference"
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:428:1: fieldReference returns [FieldReference element] : (initName= Identifier ( '.' appendName= Identifier )* ) ;
    public final AspectParser.fieldReference_return fieldReference() throws RecognitionException {
        AspectParser.fieldReference_return retval = new AspectParser.fieldReference_return();
        retval.start = input.LT(1);
        int fieldReference_StartIndex = input.index();
        Object root_0 = null;

        Token initName=null;
        Token appendName=null;
        Token char_literal33=null;

        Object initName_tree=null;
        Object appendName_tree=null;
        Object char_literal33_tree=null;

        String fullName = "";
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 420) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:430:2: ( (initName= Identifier ( '.' appendName= Identifier )* ) )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:430:4: (initName= Identifier ( '.' appendName= Identifier )* )
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:430:4: (initName= Identifier ( '.' appendName= Identifier )* )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:430:5: initName= Identifier ( '.' appendName= Identifier )*
            {
            initName=(Token)match(input,Identifier,FOLLOW_Identifier_in_fieldReference1039); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            initName_tree = (Object)adaptor.create(initName);
            adaptor.addChild(root_0, initName_tree);
            }
            if ( state.backtracking==0 ) {
              fullName = (initName!=null?initName.getText():null);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:430:54: ( '.' appendName= Identifier )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==30) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:430:55: '.' appendName= Identifier
            	    {
            	    char_literal33=(Token)match(input,30,FOLLOW_30_in_fieldReference1044); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal33_tree = (Object)adaptor.create(char_literal33);
            	    adaptor.addChild(root_0, char_literal33_tree);
            	    }
            	    appendName=(Token)match(input,Identifier,FOLLOW_Identifier_in_fieldReference1048); if (state.failed) return retval;
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
            	    break loop16;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {
              retval.element = new MemberReference(fullName);
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
            if ( state.backtracking>0 ) { memoize(input, 420, fieldReference_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:434:1: argParams returns [List<NamedTargetExpression> element] : name= Identifier ( ',' otherparams= argParams )? ;
    public final AspectParser.argParams_return argParams() throws RecognitionException {
        AspectParser.argParams_return retval = new AspectParser.argParams_return();
        retval.start = input.LT(1);
        int argParams_StartIndex = input.index();
        Object root_0 = null;

        Token name=null;
        Token char_literal34=null;
        AspectParser.argParams_return otherparams = null;


        Object name_tree=null;
        Object char_literal34_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 421) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:435:2: (name= Identifier ( ',' otherparams= argParams )? )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:435:4: name= Identifier ( ',' otherparams= argParams )?
            {
            root_0 = (Object)adaptor.nil();

            name=(Token)match(input,Identifier,FOLLOW_Identifier_in_argParams1075); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            name_tree = (Object)adaptor.create(name);
            adaptor.addChild(root_0, name_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:435:20: ( ',' otherparams= argParams )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==42) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:435:21: ',' otherparams= argParams
                    {
                    char_literal34=(Token)match(input,42,FOLLOW_42_in_argParams1078); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal34_tree = (Object)adaptor.create(char_literal34);
                    adaptor.addChild(root_0, char_literal34_tree);
                    }
                    pushFollow(FOLLOW_argParams_in_argParams1082);
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
            if ( state.backtracking>0 ) { memoize(input, 421, argParams_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:443:1: advice returns [Advice element] : (t= type | 'void' )? advtype= adviceTypeModifier pars= formalParameters (advtypespec= adviceTypeModifierSpecifier )? ':' pointcutExpr= pointcutExpression bdy= adviceBody ;
    public final AspectParser.advice_return advice() throws RecognitionException {
        AspectParser.advice_return retval = new AspectParser.advice_return();
        retval.start = input.LT(1);
        int advice_StartIndex = input.index();
        Object root_0 = null;

        Token string_literal35=null;
        Token char_literal36=null;
        Aspect_JavaP.type_return t = null;

        AspectParser.adviceTypeModifier_return advtype = null;

        Aspect_JavaP.formalParameters_return pars = null;

        AspectParser.adviceTypeModifierSpecifier_return advtypespec = null;

        AspectParser.pointcutExpression_return pointcutExpr = null;

        AspectParser.adviceBody_return bdy = null;


        Object string_literal35_tree=null;
        Object char_literal36_tree=null;

        TypeReference tref = null; List<NamedTargetExpression> arguments = new ArrayList<NamedTargetExpression>(); Modifier adviceTypeSpecifier = null;
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 422) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:446:2: ( (t= type | 'void' )? advtype= adviceTypeModifier pars= formalParameters (advtypespec= adviceTypeModifierSpecifier )? ':' pointcutExpr= pointcutExpression bdy= adviceBody )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:446:4: (t= type | 'void' )? advtype= adviceTypeModifier pars= formalParameters (advtypespec= adviceTypeModifierSpecifier )? ':' pointcutExpr= pointcutExpression bdy= adviceBody
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:446:4: (t= type | 'void' )?
            int alt18=3;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==Identifier||(LA18_0>=57 && LA18_0<=64)) ) {
                alt18=1;
            }
            else if ( (LA18_0==48) ) {
                alt18=2;
            }
            switch (alt18) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:446:5: t= type
                    {
                    pushFollow(FOLLOW_type_in_advice1116);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:446:31: 'void'
                    {
                    string_literal35=(Token)match(input,48,FOLLOW_48_in_advice1121); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal35_tree = (Object)adaptor.create(string_literal35);
                    adaptor.addChild(root_0, string_literal35_tree);
                    }
                    if ( state.backtracking==0 ) {
                      tref = typeRef("void");
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_adviceTypeModifier_in_advice1129);
            advtype=adviceTypeModifier();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, advtype.getTree());
            pushFollow(FOLLOW_formalParameters_in_advice1133);
            pars=formalParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, pars.getTree());
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:446:115: (advtypespec= adviceTypeModifierSpecifier )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=130 && LA19_0<=131)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:446:116: advtypespec= adviceTypeModifierSpecifier
                    {
                    pushFollow(FOLLOW_adviceTypeModifierSpecifier_in_advice1138);
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

            char_literal36=(Token)match(input,76,FOLLOW_76_in_advice1144); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal36_tree = (Object)adaptor.create(char_literal36);
            adaptor.addChild(root_0, char_literal36_tree);
            }
            pushFollow(FOLLOW_pointcutExpression_in_advice1148);
            pointcutExpr=pointcutExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, pointcutExpr.getTree());
            if ( state.backtracking==0 ) {

              		retval.element=new JavaAdvice(tref);
              		retval.element.setPointcutExpression(pointcutExpr.element);
              		retval.element.addModifier(advtype.element);
              		retval.element.addModifier(adviceTypeSpecifier);
              		retval.element.addFormalParameters(pars.element);
              	
            }
            pushFollow(FOLLOW_adviceBody_in_advice1159);
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
            if ( state.backtracking>0 ) { memoize(input, 422, advice_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:461:1: adviceBody returns [Block element] : b= adviceBlock ;
    public final AspectParser.adviceBody_return adviceBody() throws RecognitionException {
        AspectParser.adviceBody_return retval = new AspectParser.adviceBody_return();
        retval.start = input.LT(1);
        int adviceBody_StartIndex = input.index();
        Object root_0 = null;

        AspectParser.adviceBlock_return b = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 423) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:462:5: (b= adviceBlock )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:462:9: b= adviceBlock
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_adviceBlock_in_adviceBody1185);
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
            if ( state.backtracking>0 ) { memoize(input, 423, adviceBody_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:465:1: adviceBlock returns [Block element] : '{' (stat= adviceBlockStatement )* '}' ;
    public final AspectParser.adviceBlock_return adviceBlock() throws RecognitionException {
        AspectParser.adviceBlock_return retval = new AspectParser.adviceBlock_return();
        retval.start = input.LT(1);
        int adviceBlock_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal37=null;
        Token char_literal38=null;
        AspectParser.adviceBlockStatement_return stat = null;


        Object char_literal37_tree=null;
        Object char_literal38_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 424) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:466:5: ( '{' (stat= adviceBlockStatement )* '}' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:466:9: '{' (stat= adviceBlockStatement )* '}'
            {
            root_0 = (Object)adaptor.nil();

            char_literal37=(Token)match(input,45,FOLLOW_45_in_adviceBlock1214); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal37_tree = (Object)adaptor.create(char_literal37);
            adaptor.addChild(root_0, char_literal37_tree);
            }
            if ( state.backtracking==0 ) {
              retval.element = new Block();
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:466:45: (stat= adviceBlockStatement )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>=Identifier && LA20_0<=ASSERT)||LA20_0==27||LA20_0==29||(LA20_0>=32 && LA20_0<=38)||LA20_0==45||(LA20_0>=47 && LA20_0<=48)||LA20_0==54||(LA20_0>=57 && LA20_0<=64)||(LA20_0>=66 && LA20_0<=67)||(LA20_0>=70 && LA20_0<=74)||LA20_0==77||(LA20_0>=79 && LA20_0<=82)||(LA20_0>=84 && LA20_0<=88)||(LA20_0>=106 && LA20_0<=107)||(LA20_0>=110 && LA20_0<=114)||LA20_0==132) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:466:46: stat= adviceBlockStatement
            	    {
            	    pushFollow(FOLLOW_adviceBlockStatement_in_adviceBlock1221);
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
            	    break loop20;
                }
            } while (true);

            char_literal38=(Token)match(input,46,FOLLOW_46_in_adviceBlock1227); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal38_tree = (Object)adaptor.create(char_literal38);
            adaptor.addChild(root_0, char_literal38_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 424, adviceBlock_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:469:1: adviceBlockStatement returns [Statement element] : (local= localVariableDeclarationStatement | cd= classOrInterfaceDeclaration | specialReturn= adviceReturnStatement | stat= statement );
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
            if ( state.backtracking>0 && alreadyParsedRule(input, 425) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:471:5: (local= localVariableDeclarationStatement | cd= classOrInterfaceDeclaration | specialReturn= adviceReturnStatement | stat= statement )
            int alt21=4;
            alt21 = dfa21.predict(input);
            switch (alt21) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:471:9: local= localVariableDeclarationStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_localVariableDeclarationStatement_in_adviceBlockStatement1260);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:472:9: cd= classOrInterfaceDeclaration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_classOrInterfaceDeclaration_in_adviceBlockStatement1274);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:473:7: specialReturn= adviceReturnStatement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_adviceReturnStatement_in_adviceBlockStatement1286);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:474:9: stat= statement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_statement_in_adviceBlockStatement1300);
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
            if ( state.backtracking>0 ) { memoize(input, 425, adviceBlockStatement_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:477:1: adviceReturnStatement returns [Statement element] : retkey= 'return' (retex= expression )? ';' ;
    public final AspectParser.adviceReturnStatement_return adviceReturnStatement() throws RecognitionException {
        AspectParser.adviceReturnStatement_return retval = new AspectParser.adviceReturnStatement_return();
        retval.start = input.LT(1);
        int adviceReturnStatement_StartIndex = input.index();
        Object root_0 = null;

        Token retkey=null;
        Token char_literal39=null;
        AspectParser.expression_return retex = null;


        Object retkey_tree=null;
        Object char_literal39_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 426) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:479:5: (retkey= 'return' (retex= expression )? ';' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:479:7: retkey= 'return' (retex= expression )? ';'
            {
            root_0 = (Object)adaptor.nil();

            retkey=(Token)match(input,85,FOLLOW_85_in_adviceReturnStatement1333); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            retkey_tree = (Object)adaptor.create(retkey);
            adaptor.addChild(root_0, retkey_tree);
            }
            if ( state.backtracking==0 ) {
              retval.element = new AdviceReturnStatement();
                     setKeyword(retval.element,retkey);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:482:8: (retex= expression )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==Identifier||(LA22_0>=FloatingPointLiteral && LA22_0<=DecimalLiteral)||LA22_0==48||(LA22_0>=57 && LA22_0<=64)||(LA22_0>=66 && LA22_0<=67)||(LA22_0>=70 && LA22_0<=73)||(LA22_0>=106 && LA22_0<=107)||(LA22_0>=110 && LA22_0<=114)||LA22_0==132) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:482:9: retex= expression
                    {
                    pushFollow(FOLLOW_expression_in_adviceReturnStatement1355);
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

            char_literal39=(Token)match(input,27,FOLLOW_27_in_adviceReturnStatement1361); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal39_tree = (Object)adaptor.create(char_literal39);
            adaptor.addChild(root_0, char_literal39_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 426, adviceReturnStatement_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:487:1: adviceTypeModifier returns [Modifier element] : (bf= 'before_' | af= 'after_' | ar= 'around_' );
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
            if ( state.backtracking>0 && alreadyParsedRule(input, 427) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:488:2: (bf= 'before_' | af= 'after_' | ar= 'around_' )
            int alt23=3;
            switch ( input.LA(1) ) {
            case 127:
                {
                alt23=1;
                }
                break;
            case 128:
                {
                alt23=2;
                }
                break;
            case 129:
                {
                alt23=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:488:4: bf= 'before_'
                    {
                    root_0 = (Object)adaptor.nil();

                    bf=(Token)match(input,127,FOLLOW_127_in_adviceTypeModifier1386); if (state.failed) return retval;
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:489:4: af= 'after_'
                    {
                    root_0 = (Object)adaptor.nil();

                    af=(Token)match(input,128,FOLLOW_128_in_adviceTypeModifier1395); if (state.failed) return retval;
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:490:4: ar= 'around_'
                    {
                    root_0 = (Object)adaptor.nil();

                    ar=(Token)match(input,129,FOLLOW_129_in_adviceTypeModifier1404); if (state.failed) return retval;
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
            if ( state.backtracking>0 ) { memoize(input, 427, adviceTypeModifier_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:493:1: adviceTypeModifierSpecifier returns [Modifier element] : (rt= 'returning' ( '(' (retparam= formalParameter )? ')' )? | tw= 'throwing' ( '(' (throwabletype= formalParameter )? ')' )? );
    public final AspectParser.adviceTypeModifierSpecifier_return adviceTypeModifierSpecifier() throws RecognitionException {
        AspectParser.adviceTypeModifierSpecifier_return retval = new AspectParser.adviceTypeModifierSpecifier_return();
        retval.start = input.LT(1);
        int adviceTypeModifierSpecifier_StartIndex = input.index();
        Object root_0 = null;

        Token rt=null;
        Token tw=null;
        Token char_literal40=null;
        Token char_literal41=null;
        Token char_literal42=null;
        Token char_literal43=null;
        Aspect_JavaP.formalParameter_return retparam = null;

        Aspect_JavaP.formalParameter_return throwabletype = null;


        Object rt_tree=null;
        Object tw_tree=null;
        Object char_literal40_tree=null;
        Object char_literal41_tree=null;
        Object char_literal42_tree=null;
        Object char_literal43_tree=null;

        FormalParameter fp = null; FormalParameter exceptionParam = null;
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 428) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:495:2: (rt= 'returning' ( '(' (retparam= formalParameter )? ')' )? | tw= 'throwing' ( '(' (throwabletype= formalParameter )? ')' )? )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==130) ) {
                alt28=1;
            }
            else if ( (LA28_0==131) ) {
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:495:4: rt= 'returning' ( '(' (retparam= formalParameter )? ')' )?
                    {
                    root_0 = (Object)adaptor.nil();

                    rt=(Token)match(input,130,FOLLOW_130_in_adviceTypeModifierSpecifier1428); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    rt_tree = (Object)adaptor.create(rt);
                    adaptor.addChild(root_0, rt_tree);
                    }
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:495:19: ( '(' (retparam= formalParameter )? ')' )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==67) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:495:20: '(' (retparam= formalParameter )? ')'
                            {
                            char_literal40=(Token)match(input,67,FOLLOW_67_in_adviceTypeModifierSpecifier1431); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal40_tree = (Object)adaptor.create(char_literal40);
                            adaptor.addChild(root_0, char_literal40_tree);
                            }
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:495:24: (retparam= formalParameter )?
                            int alt24=2;
                            int LA24_0 = input.LA(1);

                            if ( (LA24_0==Identifier||LA24_0==36||(LA24_0>=57 && LA24_0<=64)||LA24_0==74) ) {
                                alt24=1;
                            }
                            switch (alt24) {
                                case 1 :
                                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:495:25: retparam= formalParameter
                                    {
                                    pushFollow(FOLLOW_formalParameter_in_adviceTypeModifierSpecifier1436);
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

                            char_literal41=(Token)match(input,68,FOLLOW_68_in_adviceTypeModifierSpecifier1442); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal41_tree = (Object)adaptor.create(char_literal41);
                            adaptor.addChild(root_0, char_literal41_tree);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:496:4: tw= 'throwing' ( '(' (throwabletype= formalParameter )? ')' )?
                    {
                    root_0 = (Object)adaptor.nil();

                    tw=(Token)match(input,131,FOLLOW_131_in_adviceTypeModifierSpecifier1453); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    tw_tree = (Object)adaptor.create(tw);
                    adaptor.addChild(root_0, tw_tree);
                    }
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:496:18: ( '(' (throwabletype= formalParameter )? ')' )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==67) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:496:19: '(' (throwabletype= formalParameter )? ')'
                            {
                            char_literal42=(Token)match(input,67,FOLLOW_67_in_adviceTypeModifierSpecifier1456); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal42_tree = (Object)adaptor.create(char_literal42);
                            adaptor.addChild(root_0, char_literal42_tree);
                            }
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:496:23: (throwabletype= formalParameter )?
                            int alt26=2;
                            int LA26_0 = input.LA(1);

                            if ( (LA26_0==Identifier||LA26_0==36||(LA26_0>=57 && LA26_0<=64)||LA26_0==74) ) {
                                alt26=1;
                            }
                            switch (alt26) {
                                case 1 :
                                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:496:24: throwabletype= formalParameter
                                    {
                                    pushFollow(FOLLOW_formalParameter_in_adviceTypeModifierSpecifier1461);
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

                            char_literal43=(Token)match(input,68,FOLLOW_68_in_adviceTypeModifierSpecifier1467); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal43_tree = (Object)adaptor.create(char_literal43);
                            adaptor.addChild(root_0, char_literal43_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 428, adviceTypeModifierSpecifier_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:499:1: methodReference returns [MethodReference element] : (t= typeOrVoid | twc= IdentifierWithWC ) name= fqn ;
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
            if ( state.backtracking>0 && alreadyParsedRule(input, 429) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:502:2: ( (t= typeOrVoid | twc= IdentifierWithWC ) name= fqn )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:502:4: (t= typeOrVoid | twc= IdentifierWithWC ) name= fqn
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:502:4: (t= typeOrVoid | twc= IdentifierWithWC )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==Identifier||LA29_0==48||(LA29_0>=57 && LA29_0<=64)) ) {
                alt29=1;
            }
            else if ( (LA29_0==IdentifierWithWC) ) {
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:502:5: t= typeOrVoid
                    {
                    pushFollow(FOLLOW_typeOrVoid_in_methodReference1505);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:502:39: twc= IdentifierWithWC
                    {
                    twc=(Token)match(input,IdentifierWithWC,FOLLOW_IdentifierWithWC_in_methodReference1511); if (state.failed) return retval;
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

            pushFollow(FOLLOW_fqn_in_methodReference1518);
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
            if ( state.backtracking>0 ) { memoize(input, 429, methodReference_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:505:1: typeOrVoid returns [JavaTypeReference element] : (t= type | v= voidType );
    public final AspectParser.typeOrVoid_return typeOrVoid() throws RecognitionException {
        AspectParser.typeOrVoid_return retval = new AspectParser.typeOrVoid_return();
        retval.start = input.LT(1);
        int typeOrVoid_StartIndex = input.index();
        Object root_0 = null;

        Aspect_JavaP.type_return t = null;

        Aspect_JavaP.voidType_return v = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 430) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:506:2: (t= type | v= voidType )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==Identifier||(LA30_0>=57 && LA30_0<=64)) ) {
                alt30=1;
            }
            else if ( (LA30_0==48) ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:506:4: t= type
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_type_in_typeOrVoid1538);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:507:4: v= voidType
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_voidType_in_typeOrVoid1547);
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
            if ( state.backtracking>0 ) { memoize(input, 430, typeOrVoid_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:510:1: fqn returns [QualifiedMethodHeader element] : (id= ( IdentifierWithWC | Identifier ) '.' )* mth= simpleMethodHeader ;
    public final AspectParser.fqn_return fqn() throws RecognitionException {
        AspectParser.fqn_return retval = new AspectParser.fqn_return();
        retval.start = input.LT(1);
        int fqn_StartIndex = input.index();
        Object root_0 = null;

        Token id=null;
        Token char_literal44=null;
        AspectParser.simpleMethodHeader_return mth = null;


        Object id_tree=null;
        Object char_literal44_tree=null;

        CompositeQualifiedName prefixes = new CompositeQualifiedName();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 431) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:513:2: ( (id= ( IdentifierWithWC | Identifier ) '.' )* mth= simpleMethodHeader )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:513:4: (id= ( IdentifierWithWC | Identifier ) '.' )* mth= simpleMethodHeader
            {
            root_0 = (Object)adaptor.nil();

            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:513:4: (id= ( IdentifierWithWC | Identifier ) '.' )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==Identifier||LA31_0==IdentifierWithWC) ) {
                    int LA31_1 = input.LA(2);

                    if ( (LA31_1==30) ) {
                        alt31=1;
                    }


                }


                switch (alt31) {
            	case 1 :
            	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:513:5: id= ( IdentifierWithWC | Identifier ) '.'
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

            	    char_literal44=(Token)match(input,30,FOLLOW_30_in_fqn1589); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal44_tree = (Object)adaptor.create(char_literal44);
            	    adaptor.addChild(root_0, char_literal44_tree);
            	    }
            	    if ( state.backtracking==0 ) {
            	      prefixes.append(new SimpleNameSignature((id!=null?id.getText():null)));
            	    }

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            pushFollow(FOLLOW_simpleMethodHeader_in_fqn1597);
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
            if ( state.backtracking>0 ) { memoize(input, 431, fqn_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:517:1: simpleMethodHeader returns [SimpleNameDeclarationWithParameterTypesHeader element] : name= ( IdentifierWithWC | Identifier ) pars= formalParameterTypes ;
    public final AspectParser.simpleMethodHeader_return simpleMethodHeader() throws RecognitionException {
        AspectParser.simpleMethodHeader_return retval = new AspectParser.simpleMethodHeader_return();
        retval.start = input.LT(1);
        int simpleMethodHeader_StartIndex = input.index();
        Object root_0 = null;

        Token name=null;
        AspectParser.formalParameterTypes_return pars = null;


        Object name_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 432) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:519:9: (name= ( IdentifierWithWC | Identifier ) pars= formalParameterTypes )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:519:11: name= ( IdentifierWithWC | Identifier ) pars= formalParameterTypes
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

            pushFollow(FOLLOW_formalParameterTypes_in_simpleMethodHeader1644);
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
            if ( state.backtracking>0 ) { memoize(input, 432, simpleMethodHeader_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:522:1: typesOrParameters returns [List<NamedTargetExpression> element] : '(' (pars= typesOrParameterDecls )? ')' ;
    public final AspectParser.typesOrParameters_return typesOrParameters() throws RecognitionException {
        AspectParser.typesOrParameters_return retval = new AspectParser.typesOrParameters_return();
        retval.start = input.LT(1);
        int typesOrParameters_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal45=null;
        Token char_literal46=null;
        AspectParser.typesOrParameterDecls_return pars = null;


        Object char_literal45_tree=null;
        Object char_literal46_tree=null;

        retval.element = new ArrayList<NamedTargetExpression>();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 433) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:524:5: ( '(' (pars= typesOrParameterDecls )? ')' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:524:9: '(' (pars= typesOrParameterDecls )? ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal45=(Token)match(input,67,FOLLOW_67_in_typesOrParameters1686); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal45_tree = (Object)adaptor.create(char_literal45);
            adaptor.addChild(root_0, char_literal45_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:524:13: (pars= typesOrParameterDecls )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==Identifier||(LA32_0>=FloatingPointLiteral && LA32_0<=DecimalLiteral)||LA32_0==48||(LA32_0>=57 && LA32_0<=64)||(LA32_0>=66 && LA32_0<=67)||(LA32_0>=70 && LA32_0<=73)||(LA32_0>=106 && LA32_0<=107)||(LA32_0>=110 && LA32_0<=114)||LA32_0==132) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:524:14: pars= typesOrParameterDecls
                    {
                    pushFollow(FOLLOW_typesOrParameterDecls_in_typesOrParameters1691);
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

            char_literal46=(Token)match(input,68,FOLLOW_68_in_typesOrParameters1697); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal46_tree = (Object)adaptor.create(char_literal46);
            adaptor.addChild(root_0, char_literal46_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 433, typesOrParameters_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:527:1: typesOrParameterDecls returns [List<NamedTargetExpression> element] : exp= expression ( ',' decls= typesOrParameterDecls )? ;
    public final AspectParser.typesOrParameterDecls_return typesOrParameterDecls() throws RecognitionException {
        AspectParser.typesOrParameterDecls_return retval = new AspectParser.typesOrParameterDecls_return();
        retval.start = input.LT(1);
        int typesOrParameterDecls_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal47=null;
        AspectParser.expression_return exp = null;

        AspectParser.typesOrParameterDecls_return decls = null;


        Object char_literal47_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 434) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:528:5: (exp= expression ( ',' decls= typesOrParameterDecls )? )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:528:7: exp= expression ( ',' decls= typesOrParameterDecls )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_expression_in_typesOrParameterDecls1720);
            exp=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:528:22: ( ',' decls= typesOrParameterDecls )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==42) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:528:23: ',' decls= typesOrParameterDecls
                    {
                    char_literal47=(Token)match(input,42,FOLLOW_42_in_typesOrParameterDecls1723); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal47_tree = (Object)adaptor.create(char_literal47);
                    adaptor.addChild(root_0, char_literal47_tree);
                    }
                    pushFollow(FOLLOW_typesOrParameterDecls_in_typesOrParameterDecls1727);
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
            if ( state.backtracking>0 ) { memoize(input, 434, typesOrParameterDecls_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:536:1: formalParameterTypes returns [List<TypeReference> element] : '(' (pars= formalParameterTypeDecls )? ')' ;
    public final AspectParser.formalParameterTypes_return formalParameterTypes() throws RecognitionException {
        AspectParser.formalParameterTypes_return retval = new AspectParser.formalParameterTypes_return();
        retval.start = input.LT(1);
        int formalParameterTypes_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal48=null;
        Token char_literal49=null;
        AspectParser.formalParameterTypeDecls_return pars = null;


        Object char_literal48_tree=null;
        Object char_literal49_tree=null;

        retval.element = new ArrayList<TypeReference>();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 435) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:538:5: ( '(' (pars= formalParameterTypeDecls )? ')' )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:538:9: '(' (pars= formalParameterTypeDecls )? ')'
            {
            root_0 = (Object)adaptor.nil();

            char_literal48=(Token)match(input,67,FOLLOW_67_in_formalParameterTypes1774); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal48_tree = (Object)adaptor.create(char_literal48);
            adaptor.addChild(root_0, char_literal48_tree);
            }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:538:13: (pars= formalParameterTypeDecls )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==Identifier||(LA34_0>=57 && LA34_0<=64)) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:538:14: pars= formalParameterTypeDecls
                    {
                    pushFollow(FOLLOW_formalParameterTypeDecls_in_formalParameterTypes1779);
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

            char_literal49=(Token)match(input,68,FOLLOW_68_in_formalParameterTypes1785); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal49_tree = (Object)adaptor.create(char_literal49);
            adaptor.addChild(root_0, char_literal49_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 435, formalParameterTypes_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:541:1: formalParameterTypeDecls returns [List<TypeReference> element] : t= type ( ',' decls= formalParameterTypeDecls )? ;
    public final AspectParser.formalParameterTypeDecls_return formalParameterTypeDecls() throws RecognitionException {
        AspectParser.formalParameterTypeDecls_return retval = new AspectParser.formalParameterTypeDecls_return();
        retval.start = input.LT(1);
        int formalParameterTypeDecls_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal50=null;
        Aspect_JavaP.type_return t = null;

        AspectParser.formalParameterTypeDecls_return decls = null;


        Object char_literal50_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 436) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:542:5: (t= type ( ',' decls= formalParameterTypeDecls )? )
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:542:9: t= type ( ',' decls= formalParameterTypeDecls )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_type_in_formalParameterTypeDecls1810);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:542:16: ( ',' decls= formalParameterTypeDecls )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==42) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:542:17: ',' decls= formalParameterTypeDecls
                    {
                    char_literal50=(Token)match(input,42,FOLLOW_42_in_formalParameterTypeDecls1813); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal50_tree = (Object)adaptor.create(char_literal50);
                    adaptor.addChild(root_0, char_literal50_tree);
                    }
                    pushFollow(FOLLOW_formalParameterTypeDecls_in_formalParameterTypeDecls1817);
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
            if ( state.backtracking>0 ) { memoize(input, 436, formalParameterTypeDecls_StartIndex); }
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
    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:550:1: expression returns [Expression element] : (ex= conditionalExpression (op= assignmentOperator exx= expression )? | prcd= 'proceed' args= arguments );
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
            if ( state.backtracking>0 && alreadyParsedRule(input, 437) ) { return retval; }
            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:551:5: (ex= conditionalExpression (op= assignmentOperator exx= expression )? | prcd= 'proceed' args= arguments )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==Identifier||(LA37_0>=FloatingPointLiteral && LA37_0<=DecimalLiteral)||LA37_0==48||(LA37_0>=57 && LA37_0<=64)||(LA37_0>=66 && LA37_0<=67)||(LA37_0>=70 && LA37_0<=73)||(LA37_0>=106 && LA37_0<=107)||(LA37_0>=110 && LA37_0<=114)) ) {
                alt37=1;
            }
            else if ( (LA37_0==132) ) {
                alt37=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:551:9: ex= conditionalExpression (op= assignmentOperator exx= expression )?
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_conditionalExpression_in_expression1861);
                    ex=conditionalExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ex.getTree());
                    if ( state.backtracking==0 ) {
                      retval.element=ex.element;
                    }
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:551:63: (op= assignmentOperator exx= expression )?
                    int alt36=2;
                    alt36 = dfa36.predict(input);
                    switch (alt36) {
                        case 1 :
                            // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:551:64: op= assignmentOperator exx= expression
                            {
                            pushFollow(FOLLOW_assignmentOperator_in_expression1868);
                            op=assignmentOperator();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, op.getTree());
                            pushFollow(FOLLOW_expression_in_expression1872);
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
                    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:563:11: prcd= 'proceed' args= arguments
                    {
                    root_0 = (Object)adaptor.nil();

                    prcd=(Token)match(input,132,FOLLOW_132_in_expression1908); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    prcd_tree = (Object)adaptor.create(prcd);
                    adaptor.addChild(root_0, prcd_tree);
                    }
                    pushFollow(FOLLOW_arguments_in_expression1912);
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
            if ( state.backtracking>0 ) { memoize(input, 437, expression_StartIndex); }
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


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:297:10: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:297:10: annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
        {
        pushFollow(FOLLOW_annotations_in_synpred5_Aspect80);
        annotations();

        state._fsp--;
        if (state.failed) return ;
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:298:9: (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* )
        int alt43=2;
        int LA43_0 = input.LA(1);

        if ( (LA43_0==26) ) {
            alt43=1;
        }
        else if ( (LA43_0==ENUM||LA43_0==29||(LA43_0>=32 && LA43_0<=38)||LA43_0==47||LA43_0==74) ) {
            alt43=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 43, 0, input);

            throw nvae;
        }
        switch (alt43) {
            case 1 :
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:298:13: np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )*
                {
                pushFollow(FOLLOW_packageDeclaration_in_synpred5_Aspect96);
                np=packageDeclaration();

                state._fsp--;
                if (state.failed) return ;
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:303:13: (imp= importDeclaration )*
                loop40:
                do {
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==28) ) {
                        alt40=1;
                    }


                    switch (alt40) {
                	case 1 :
                	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:303:14: imp= importDeclaration
                	    {
                	    pushFollow(FOLLOW_importDeclaration_in_synpred5_Aspect132);
                	    imp=importDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop40;
                    }
                } while (true);

                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:304:13: (typech= typeDeclaration )*
                loop41:
                do {
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==ENUM||LA41_0==27||LA41_0==29||(LA41_0>=32 && LA41_0<=38)||LA41_0==47||LA41_0==74) ) {
                        alt41=1;
                    }


                    switch (alt41) {
                	case 1 :
                	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:304:14: typech= typeDeclaration
                	    {
                	    pushFollow(FOLLOW_typeDeclaration_in_synpred5_Aspect153);
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
            case 2 :
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:308:13: cd= classOrInterfaceDeclaration (typech= typeDeclaration )*
                {
                pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred5_Aspect202);
                cd=classOrInterfaceDeclaration();

                state._fsp--;
                if (state.failed) return ;
                // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:314:13: (typech= typeDeclaration )*
                loop42:
                do {
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==ENUM||LA42_0==27||LA42_0==29||(LA42_0>=32 && LA42_0<=38)||LA42_0==47||LA42_0==74) ) {
                        alt42=1;
                    }


                    switch (alt42) {
                	case 1 :
                	    // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:314:14: typech= typeDeclaration
                	    {
                	    pushFollow(FOLLOW_typeDeclaration_in_synpred5_Aspect237);
                	    typech=typeDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop42;
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


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:377:4: (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:377:4: expr1= pointcutExpressionOr '&&' expr2= pointcutExpression
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


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:383:4: (expr1= pointcutAtom '||' expr2= pointcutExpressionOr )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:383:4: expr1= pointcutAtom '||' expr2= pointcutExpressionOr
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

    // $ANTLR start synpred15_Aspect
    public final void synpred15_Aspect_fragment() throws RecognitionException {   
        AspectParser.withinType_return withinTypeCL = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:399:4: (withinTypeCL= withinType )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:399:4: withinTypeCL= withinType
        {
        pushFollow(FOLLOW_withinType_in_synpred15_Aspect745);
        withinTypeCL=withinType();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_Aspect

    // $ANTLR start synpred36_Aspect
    public final void synpred36_Aspect_fragment() throws RecognitionException {   
        Aspect_JavaP.localVariableDeclarationStatement_return local = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:471:9: (local= localVariableDeclarationStatement )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:471:9: local= localVariableDeclarationStatement
        {
        pushFollow(FOLLOW_localVariableDeclarationStatement_in_synpred36_Aspect1260);
        local=localVariableDeclarationStatement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred36_Aspect

    // $ANTLR start synpred37_Aspect
    public final void synpred37_Aspect_fragment() throws RecognitionException {   
        Aspect_JavaP.classOrInterfaceDeclaration_return cd = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:472:9: (cd= classOrInterfaceDeclaration )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:472:9: cd= classOrInterfaceDeclaration
        {
        pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred37_Aspect1274);
        cd=classOrInterfaceDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred37_Aspect

    // $ANTLR start synpred38_Aspect
    public final void synpred38_Aspect_fragment() throws RecognitionException {   
        AspectParser.adviceReturnStatement_return specialReturn = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:473:7: (specialReturn= adviceReturnStatement )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:473:7: specialReturn= adviceReturnStatement
        {
        pushFollow(FOLLOW_adviceReturnStatement_in_synpred38_Aspect1286);
        specialReturn=adviceReturnStatement();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred38_Aspect

    // $ANTLR start synpred56_Aspect
    public final void synpred56_Aspect_fragment() throws RecognitionException {   
        Aspect_JavaP.assignmentOperator_return op = null;

        AspectParser.expression_return exx = null;


        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:551:64: (op= assignmentOperator exx= expression )
        // C:\\GIT\\aspects-java\\src\\aspectsjava\\input\\Aspect.g:551:64: op= assignmentOperator exx= expression
        {
        pushFollow(FOLLOW_assignmentOperator_in_synpred56_Aspect1868);
        op=assignmentOperator();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_expression_in_synpred56_Aspect1872);
        exx=expression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred56_Aspect

    // Delegated rules
    public Aspect_JavaP.voidInterfaceMethodDeclaration_return voidInterfaceMethodDeclaration() throws RecognitionException { return gJavaP.voidInterfaceMethodDeclaration(); }
    public Aspect_JavaP.typeList_return typeList() throws RecognitionException { return gJavaP.typeList(); }
    public Aspect_JavaP.arguments_return arguments() throws RecognitionException { return gJavaP.arguments(); }
    public Aspect_JavaP.variableDeclarators_return variableDeclarators() throws RecognitionException { return gJavaP.variableDeclarators(); }
    public Aspect_JavaP.interfaceBody_return interfaceBody() throws RecognitionException { return gJavaP.interfaceBody(); }
    public Aspect_JavaP.selector_return selector() throws RecognitionException { return gJavaP.selector(); }
    public Aspect_JavaP.annotationTypeElementDeclaration_return annotationTypeElementDeclaration() throws RecognitionException { return gJavaP.annotationTypeElementDeclaration(); }
    public Aspect_JavaP.constantExpression_return constantExpression() throws RecognitionException { return gJavaP.constantExpression(); }
    public Aspect_JavaP.assignmentOperator_return assignmentOperator() throws RecognitionException { return gJavaP.assignmentOperator(); }
    public Aspect_JavaP.inclusiveOrExpression_return inclusiveOrExpression() throws RecognitionException { return gJavaP.inclusiveOrExpression(); }
    public Aspect_JavaP.enumBody_return enumBody() throws RecognitionException { return gJavaP.enumBody(); }
    public Aspect_JavaP.annotationMethodOrConstantRest_return annotationMethodOrConstantRest(TypeReference type) throws RecognitionException { return gJavaP.annotationMethodOrConstantRest(type); }
    public Aspect_JavaP.moreIdentifierSuffixRubbish_return moreIdentifierSuffixRubbish() throws RecognitionException { return gJavaP.moreIdentifierSuffixRubbish(); }
    public Aspect_JavaP.enumDeclaration_return enumDeclaration() throws RecognitionException { return gJavaP.enumDeclaration(); }
    public Aspect_JavaP.typeParameters_return typeParameters() throws RecognitionException { return gJavaP.typeParameters(); }
    public Aspect_JavaP.innerCreator_return innerCreator() throws RecognitionException { return gJavaP.innerCreator(); }
    public Aspect_JavaP.typeBound_return typeBound() throws RecognitionException { return gJavaP.typeBound(); }
    public Aspect_JavaP.constructorDeclaratorRest_return constructorDeclaratorRest() throws RecognitionException { return gJavaP.constructorDeclaratorRest(); }
    public Aspect_JavaP.elementValuePair_return elementValuePair() throws RecognitionException { return gJavaP.elementValuePair(); }
    public Aspect_JavaP.methodDeclaratorRest_return methodDeclaratorRest() throws RecognitionException { return gJavaP.methodDeclaratorRest(); }
    public Aspect_JavaP.interfaceMethod_return interfaceMethod() throws RecognitionException { return gJavaP.interfaceMethod(); }
    public Aspect_JavaP.modifier_return modifier() throws RecognitionException { return gJavaP.modifier(); }
    public Aspect_JavaP.annotationTypeDeclaration_return annotationTypeDeclaration() throws RecognitionException { return gJavaP.annotationTypeDeclaration(); }
    public Aspect_JavaP.annotationMethodRest_return annotationMethodRest(TypeReference type) throws RecognitionException { return gJavaP.annotationMethodRest(type); }
    public Aspect_JavaP.variableDeclarator_return variableDeclarator() throws RecognitionException { return gJavaP.variableDeclarator(); }
    public Aspect_JavaP.modifiers_return modifiers() throws RecognitionException { return gJavaP.modifiers(); }
    public Aspect_JavaP.variableModifier_return variableModifier() throws RecognitionException { return gJavaP.variableModifier(); }
    public Aspect_JavaP.memberDecl_return memberDecl() throws RecognitionException { return gJavaP.memberDecl(); }
    public Aspect_JavaP.exclusiveOrExpression_return exclusiveOrExpression() throws RecognitionException { return gJavaP.exclusiveOrExpression(); }
    public Aspect_JavaP.importDeclaration_return importDeclaration() throws RecognitionException { return gJavaP.importDeclaration(); }
    public Aspect_JavaP.catchClause_return catchClause() throws RecognitionException { return gJavaP.catchClause(); }
    public Aspect_JavaP.typeArgument_return typeArgument() throws RecognitionException { return gJavaP.typeArgument(); }
    public Aspect_JavaP.nonTargetPrimary_return nonTargetPrimary() throws RecognitionException { return gJavaP.nonTargetPrimary(); }
    public Aspect_JavaP.typeArguments_return typeArguments() throws RecognitionException { return gJavaP.typeArguments(); }
    public Aspect_JavaP.formalParameterDecls_return formalParameterDecls() throws RecognitionException { return gJavaP.formalParameterDecls(); }
    public Aspect_JavaP.forUpdate_return forUpdate() throws RecognitionException { return gJavaP.forUpdate(); }
    public Aspect_JavaP.typeDeclaration_return typeDeclaration() throws RecognitionException { return gJavaP.typeDeclaration(); }
    public Aspect_JavaP.defaultValue_return defaultValue() throws RecognitionException { return gJavaP.defaultValue(); }
    public Aspect_JavaP.classOrInterfaceDeclaration_return classOrInterfaceDeclaration() throws RecognitionException { return gJavaP.classOrInterfaceDeclaration(); }
    public Aspect_JavaP.voidMethodDeclaratorRest_return voidMethodDeclaratorRest() throws RecognitionException { return gJavaP.voidMethodDeclaratorRest(); }
    public Aspect_JavaP.explicitConstructorInvocation_return explicitConstructorInvocation() throws RecognitionException { return gJavaP.explicitConstructorInvocation(); }
    public Aspect_JavaP.nonWildcardTypeArguments_return nonWildcardTypeArguments() throws RecognitionException { return gJavaP.nonWildcardTypeArguments(); }
    public Aspect_JavaP.primitiveType_return primitiveType() throws RecognitionException { return gJavaP.primitiveType(); }
    public Aspect_JavaP.expressionList_return expressionList() throws RecognitionException { return gJavaP.expressionList(); }
    public Aspect_JavaP.constructorDeclaration_return constructorDeclaration() throws RecognitionException { return gJavaP.constructorDeclaration(); }
    public Aspect_JavaP.memberDeclaration_return memberDeclaration() throws RecognitionException { return gJavaP.memberDeclaration(); }
    public Aspect_JavaP.normalInterfaceDeclaration_return normalInterfaceDeclaration() throws RecognitionException { return gJavaP.normalInterfaceDeclaration(); }
    public Aspect_JavaP.interfaceGenericMethodDecl_return interfaceGenericMethodDecl() throws RecognitionException { return gJavaP.interfaceGenericMethodDecl(); }
    public Aspect_JavaP.conditionalOrExpression_return conditionalOrExpression() throws RecognitionException { return gJavaP.conditionalOrExpression(); }
    public Aspect_JavaP.variableInitializer_return variableInitializer() throws RecognitionException { return gJavaP.variableInitializer(); }
    public Aspect_JavaP.typeParameter_return typeParameter() throws RecognitionException { return gJavaP.typeParameter(); }
    public Aspect_JavaP.andExpression_return andExpression() throws RecognitionException { return gJavaP.andExpression(); }
    public Aspect_JavaP.statementExpression_return statementExpression() throws RecognitionException { return gJavaP.statementExpression(); }
    public Aspect_JavaP.creator_return creator() throws RecognitionException { return gJavaP.creator(); }
    public Aspect_JavaP.classBody_return classBody() throws RecognitionException { return gJavaP.classBody(); }
    public Aspect_JavaP.annotations_return annotations() throws RecognitionException { return gJavaP.annotations(); }
    public Aspect_JavaP.interfaceMemberDecl_return interfaceMemberDecl() throws RecognitionException { return gJavaP.interfaceMemberDecl(); }
    public Aspect_JavaP.enumBodyDeclarations_return enumBodyDeclarations() throws RecognitionException { return gJavaP.enumBodyDeclarations(); }
    public Aspect_JavaP.integerLiteral_return integerLiteral() throws RecognitionException { return gJavaP.integerLiteral(); }
    public Aspect_JavaP.catches_return catches() throws RecognitionException { return gJavaP.catches(); }
    public Aspect_JavaP.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus() throws RecognitionException { return gJavaP.unaryExpressionNotPlusMinus(); }
    public Aspect_JavaP.arrayInitializer_return arrayInitializer() throws RecognitionException { return gJavaP.arrayInitializer(); }
    public Aspect_JavaP.elementValue_return elementValue() throws RecognitionException { return gJavaP.elementValue(); }
    public Aspect_JavaP.annotation_return annotation() throws RecognitionException { return gJavaP.annotation(); }
    public Aspect_JavaP.variableDeclaratorId_return variableDeclaratorId() throws RecognitionException { return gJavaP.variableDeclaratorId(); }
    public Aspect_JavaP.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException { return gJavaP.multiplicativeExpression(); }
    public Aspect_JavaP.interfaceMethodDeclaratorRest_return interfaceMethodDeclaratorRest() throws RecognitionException { return gJavaP.interfaceMethodDeclaratorRest(); }
    public Aspect_JavaP.argumentsSuffixRubbish_return argumentsSuffixRubbish() throws RecognitionException { return gJavaP.argumentsSuffixRubbish(); }
    public Aspect_JavaP.annotationConstantRest_return annotationConstantRest(TypeReference type) throws RecognitionException { return gJavaP.annotationConstantRest(type); }
    public Aspect_JavaP.shiftOp_return shiftOp() throws RecognitionException { return gJavaP.shiftOp(); }
    public Aspect_JavaP.classOrInterfaceModifiers_return classOrInterfaceModifiers() throws RecognitionException { return gJavaP.classOrInterfaceModifiers(); }
    public Aspect_JavaP.typeName_return typeName() throws RecognitionException { return gJavaP.typeName(); }
    public Aspect_JavaP.qualifiedName_return qualifiedName() throws RecognitionException { return gJavaP.qualifiedName(); }
    public Aspect_JavaP.constructorBody_return constructorBody() throws RecognitionException { return gJavaP.constructorBody(); }
    public Aspect_JavaP.forControl_return forControl() throws RecognitionException { return gJavaP.forControl(); }
    public Aspect_JavaP.voidType_return voidType() throws RecognitionException { return gJavaP.voidType(); }
    public Aspect_JavaP.identifierSuffixRubbush_return identifierSuffixRubbush() throws RecognitionException { return gJavaP.identifierSuffixRubbush(); }
    public Aspect_JavaP.methodDeclaration_return methodDeclaration() throws RecognitionException { return gJavaP.methodDeclaration(); }
    public Aspect_JavaP.elementValuePairs_return elementValuePairs() throws RecognitionException { return gJavaP.elementValuePairs(); }
    public Aspect_JavaP.elementValueArrayInitializer_return elementValueArrayInitializer() throws RecognitionException { return gJavaP.elementValueArrayInitializer(); }
    public Aspect_JavaP.primary_return primary() throws RecognitionException { return gJavaP.primary(); }
    public Aspect_JavaP.normalClassDeclaration_return normalClassDeclaration() throws RecognitionException { return gJavaP.normalClassDeclaration(); }
    public Aspect_JavaP.blockStatement_return blockStatement() throws RecognitionException { return gJavaP.blockStatement(); }
    public Aspect_JavaP.annotationTypeElementRest_return annotationTypeElementRest() throws RecognitionException { return gJavaP.annotationTypeElementRest(); }
    public Aspect_JavaP.enumConstantName_return enumConstantName() throws RecognitionException { return gJavaP.enumConstantName(); }
    public Aspect_JavaP.constantDeclarator_return constantDeclarator() throws RecognitionException { return gJavaP.constantDeclarator(); }
    public Aspect_JavaP.classOrInterfaceModifier_return classOrInterfaceModifier() throws RecognitionException { return gJavaP.classOrInterfaceModifier(); }
    public Aspect_JavaP.additiveExpression_return additiveExpression() throws RecognitionException { return gJavaP.additiveExpression(); }
    public Aspect_JavaP.conditionalExpression_return conditionalExpression() throws RecognitionException { return gJavaP.conditionalExpression(); }
    public Aspect_JavaP.literal_return literal() throws RecognitionException { return gJavaP.literal(); }
    public Aspect_JavaP.castExpression_return castExpression() throws RecognitionException { return gJavaP.castExpression(); }
    public Aspect_JavaP.genericMethodOrConstructorDecl_return genericMethodOrConstructorDecl() throws RecognitionException { return gJavaP.genericMethodOrConstructorDecl(); }
    public Aspect_JavaP.fieldDeclaration_return fieldDeclaration() throws RecognitionException { return gJavaP.fieldDeclaration(); }
    public Aspect_JavaP.genericMethodOrConstructorRest_return genericMethodOrConstructorRest() throws RecognitionException { return gJavaP.genericMethodOrConstructorRest(); }
    public Aspect_JavaP.interfaceConstant_return interfaceConstant() throws RecognitionException { return gJavaP.interfaceConstant(); }
    public Aspect_JavaP.classBodyDeclaration_return classBodyDeclaration() throws RecognitionException { return gJavaP.classBodyDeclaration(); }
    public Aspect_JavaP.explicitGenericInvocation_return explicitGenericInvocation() throws RecognitionException { return gJavaP.explicitGenericInvocation(); }
    public Aspect_JavaP.arrayAccessSuffixRubbish_return arrayAccessSuffixRubbish() throws RecognitionException { return gJavaP.arrayAccessSuffixRubbish(); }
    public Aspect_JavaP.instanceOfExpression_return instanceOfExpression() throws RecognitionException { return gJavaP.instanceOfExpression(); }
    public Aspect_JavaP.switchLabel_return switchLabel() throws RecognitionException { return gJavaP.switchLabel(); }
    public Aspect_JavaP.parExpression_return parExpression() throws RecognitionException { return gJavaP.parExpression(); }
    public Aspect_JavaP.createdName_return createdName() throws RecognitionException { return gJavaP.createdName(); }
    public Aspect_JavaP.enumConstants_return enumConstants() throws RecognitionException { return gJavaP.enumConstants(); }
    public Aspect_JavaP.methodBody_return methodBody() throws RecognitionException { return gJavaP.methodBody(); }
    public Aspect_JavaP.relationalExpression_return relationalExpression() throws RecognitionException { return gJavaP.relationalExpression(); }
    public Aspect_JavaP.booleanLiteral_return booleanLiteral() throws RecognitionException { return gJavaP.booleanLiteral(); }
    public Aspect_JavaP.enumConstant_return enumConstant() throws RecognitionException { return gJavaP.enumConstant(); }
    public Aspect_JavaP.superSuffix_return superSuffix() throws RecognitionException { return gJavaP.superSuffix(); }
    public Aspect_JavaP.nameAndParams_return nameAndParams() throws RecognitionException { return gJavaP.nameAndParams(); }
    public Aspect_JavaP.statement_return statement() throws RecognitionException { return gJavaP.statement(); }
    public Aspect_JavaP.shiftExpression_return shiftExpression() throws RecognitionException { return gJavaP.shiftExpression(); }
    public Aspect_JavaP.forInit_return forInit() throws RecognitionException { return gJavaP.forInit(); }
    public Aspect_JavaP.switchBlockStatementGroups_return switchBlockStatementGroups() throws RecognitionException { return gJavaP.switchBlockStatementGroups(); }
    public Aspect_JavaP.type_return type() throws RecognitionException { return gJavaP.type(); }
    public Aspect_JavaP.packageDeclaration_return packageDeclaration() throws RecognitionException { return gJavaP.packageDeclaration(); }
    public Aspect_JavaP.annotationTypeBody_return annotationTypeBody() throws RecognitionException { return gJavaP.annotationTypeBody(); }
    public Aspect_JavaP.switchCase_return switchCase() throws RecognitionException { return gJavaP.switchCase(); }
    public Aspect_JavaP.formalParameters_return formalParameters() throws RecognitionException { return gJavaP.formalParameters(); }
    public Aspect_JavaP.classDeclaration_return classDeclaration() throws RecognitionException { return gJavaP.classDeclaration(); }
    public Aspect_JavaP.localVariableDeclaration_return localVariableDeclaration() throws RecognitionException { return gJavaP.localVariableDeclaration(); }
    public Aspect_JavaP.enhancedForControl_return enhancedForControl() throws RecognitionException { return gJavaP.enhancedForControl(); }
    public Aspect_JavaP.classCreatorRest_return classCreatorRest() throws RecognitionException { return gJavaP.classCreatorRest(); }
    public Aspect_JavaP.localVariableDeclarationStatement_return localVariableDeclarationStatement() throws RecognitionException { return gJavaP.localVariableDeclarationStatement(); }
    public Aspect_JavaP.formalParameter_return formalParameter() throws RecognitionException { return gJavaP.formalParameter(); }
    public Aspect_JavaP.qualifiedNameList_return qualifiedNameList() throws RecognitionException { return gJavaP.qualifiedNameList(); }
    public Aspect_JavaP.equalityExpression_return equalityExpression() throws RecognitionException { return gJavaP.equalityExpression(); }
    public Aspect_JavaP.block_return block() throws RecognitionException { return gJavaP.block(); }
    public Aspect_JavaP.interfaceMethodOrFieldDecl_return interfaceMethodOrFieldDecl() throws RecognitionException { return gJavaP.interfaceMethodOrFieldDecl(); }
    public Aspect_JavaP.classOrInterfaceType_return classOrInterfaceType() throws RecognitionException { return gJavaP.classOrInterfaceType(); }
    public Aspect_JavaP.voidInterfaceMethodDeclaratorRest_return voidInterfaceMethodDeclaratorRest() throws RecognitionException { return gJavaP.voidInterfaceMethodDeclaratorRest(); }
    public Aspect_JavaP.unaryExpression_return unaryExpression() throws RecognitionException { return gJavaP.unaryExpression(); }
    public Aspect_JavaP.voidMethodDeclaration_return voidMethodDeclaration() throws RecognitionException { return gJavaP.voidMethodDeclaration(); }
    public Aspect_JavaP.createClassHereBecauseANTLRisAnnoying_return createClassHereBecauseANTLRisAnnoying() throws RecognitionException { return gJavaP.createClassHereBecauseANTLRisAnnoying(); }
    public Aspect_JavaP.interfaceBodyDeclaration_return interfaceBodyDeclaration() throws RecognitionException { return gJavaP.interfaceBodyDeclaration(); }
    public Aspect_JavaP.variableModifiers_return variableModifiers() throws RecognitionException { return gJavaP.variableModifiers(); }
    public Aspect_JavaP.interfaceDeclaration_return interfaceDeclaration() throws RecognitionException { return gJavaP.interfaceDeclaration(); }
    public Aspect_JavaP.conditionalAndExpression_return conditionalAndExpression() throws RecognitionException { return gJavaP.conditionalAndExpression(); }
    public Aspect_JavaP.relationalOp_return relationalOp() throws RecognitionException { return gJavaP.relationalOp(); }
    public Aspect_JavaP.annotationName_return annotationName() throws RecognitionException { return gJavaP.annotationName(); }

    public final boolean synpred38_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred38_Aspect_fragment(); // can never throw exception
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
    public final boolean synpred15_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_Aspect_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred56_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred56_Aspect_fragment(); // can never throw exception
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
    public final boolean synpred37_Aspect() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred37_Aspect_fragment(); // can never throw exception
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
    protected DFA21 dfa21 = new DFA21(this);
    protected DFA36 dfa36 = new DFA36(this);
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
            return "297:8: ( annotations (np= packageDeclaration (imp= importDeclaration )* (typech= typeDeclaration )* | cd= classOrInterfaceDeclaration (typech= typeDeclaration )* ) | (np= packageDeclaration )? (imp= importDeclaration )* (typech= typeDeclaration | ad= aspect )* )";
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
        "\21\uffff";
    static final String DFA10_eofS =
        "\21\uffff";
    static final String DFA10_minS =
        "\1\4\16\0\2\uffff";
    static final String DFA10_maxS =
        "\1\176\16\0\2\uffff";
    static final String DFA10_acceptS =
        "\17\uffff\1\1\1\2";
    static final String DFA10_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\1\15\2\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\7\76\uffff\1\16\11\uffff\1\14\43\uffff\1\15\3\uffff\1\10"+
            "\1\1\1\2\1\3\1\4\1\5\1\6\1\11\1\12\1\13",
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
            return "375:1: pointcutExpression returns [PointcutExpression element] : (expr1= pointcutExpressionOr '&&' expr2= pointcutExpression | expr= pointcutExpressionOr );";
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
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA10_2 = input.LA(1);

                         
                        int index10_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA10_3 = input.LA(1);

                         
                        int index10_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA10_4 = input.LA(1);

                         
                        int index10_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA10_5 = input.LA(1);

                         
                        int index10_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA10_6 = input.LA(1);

                         
                        int index10_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA10_7 = input.LA(1);

                         
                        int index10_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA10_8 = input.LA(1);

                         
                        int index10_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA10_9 = input.LA(1);

                         
                        int index10_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA10_10 = input.LA(1);

                         
                        int index10_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA10_11 = input.LA(1);

                         
                        int index10_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA10_12 = input.LA(1);

                         
                        int index10_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA10_13 = input.LA(1);

                         
                        int index10_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA10_14 = input.LA(1);

                         
                        int index10_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index10_14);
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
        "\21\uffff";
    static final String DFA11_eofS =
        "\21\uffff";
    static final String DFA11_minS =
        "\1\4\16\0\2\uffff";
    static final String DFA11_maxS =
        "\1\176\16\0\2\uffff";
    static final String DFA11_acceptS =
        "\17\uffff\1\1\1\2";
    static final String DFA11_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\1\15\2\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\7\76\uffff\1\16\11\uffff\1\14\43\uffff\1\15\3\uffff\1\10"+
            "\1\1\1\2\1\3\1\4\1\5\1\6\1\11\1\12\1\13",
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
            return "381:1: pointcutExpressionOr returns [PointcutExpression element] : (expr1= pointcutAtom '||' expr2= pointcutExpressionOr | expr= pointcutAtom );";
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
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA11_2 = input.LA(1);

                         
                        int index11_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA11_3 = input.LA(1);

                         
                        int index11_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA11_4 = input.LA(1);

                         
                        int index11_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA11_5 = input.LA(1);

                         
                        int index11_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA11_6 = input.LA(1);

                         
                        int index11_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA11_7 = input.LA(1);

                         
                        int index11_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA11_8 = input.LA(1);

                         
                        int index11_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA11_9 = input.LA(1);

                         
                        int index11_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA11_10 = input.LA(1);

                         
                        int index11_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA11_11 = input.LA(1);

                         
                        int index11_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA11_12 = input.LA(1);

                         
                        int index11_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA11_13 = input.LA(1);

                         
                        int index11_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA11_14 = input.LA(1);

                         
                        int index11_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Aspect()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index11_14);
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
    static final String DFA21_eotS =
        "\72\uffff";
    static final String DFA21_eofS =
        "\72\uffff";
    static final String DFA21_minS =
        "\1\4\13\0\11\uffff\1\0\44\uffff";
    static final String DFA21_maxS =
        "\1\u0084\13\0\11\uffff\1\0\44\uffff";
    static final String DFA21_acceptS =
        "\14\uffff\1\2\11\uffff\1\4\41\uffff\1\1\1\3";
    static final String DFA21_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\11\uffff"+
        "\1\13\44\uffff}>";
    static final String[] DFA21_transitionS = {
            "\1\3\1\14\7\26\16\uffff\1\26\1\uffff\1\14\2\uffff\4\14\1\1"+
            "\2\14\6\uffff\1\26\1\uffff\1\14\1\26\5\uffff\1\26\2\uffff\1"+
            "\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\uffff\2\26\2\uffff\4\26"+
            "\1\2\2\uffff\1\26\1\uffff\4\26\1\uffff\1\26\1\25\3\26\21\uffff"+
            "\2\26\2\uffff\5\26\21\uffff\1\26",
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

    static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
    static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
    static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
    static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
    static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
    static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
    static final short[][] DFA21_transition;

    static {
        int numStates = DFA21_transitionS.length;
        DFA21_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
        }
    }

    class DFA21 extends DFA {

        public DFA21(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 21;
            this.eot = DFA21_eot;
            this.eof = DFA21_eof;
            this.min = DFA21_min;
            this.max = DFA21_max;
            this.accept = DFA21_accept;
            this.special = DFA21_special;
            this.transition = DFA21_transition;
        }
        public String getDescription() {
            return "469:1: adviceBlockStatement returns [Statement element] : (local= localVariableDeclarationStatement | cd= classOrInterfaceDeclaration | specialReturn= adviceReturnStatement | stat= statement );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA21_1 = input.LA(1);

                         
                        int index21_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (synpred37_Aspect()) ) {s = 12;}

                         
                        input.seek(index21_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA21_2 = input.LA(1);

                         
                        int index21_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (synpred37_Aspect()) ) {s = 12;}

                         
                        input.seek(index21_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA21_3 = input.LA(1);

                         
                        int index21_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA21_4 = input.LA(1);

                         
                        int index21_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA21_5 = input.LA(1);

                         
                        int index21_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA21_6 = input.LA(1);

                         
                        int index21_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA21_7 = input.LA(1);

                         
                        int index21_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA21_8 = input.LA(1);

                         
                        int index21_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA21_9 = input.LA(1);

                         
                        int index21_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA21_10 = input.LA(1);

                         
                        int index21_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA21_11 = input.LA(1);

                         
                        int index21_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_Aspect()) ) {s = 56;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA21_21 = input.LA(1);

                         
                        int index21_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred38_Aspect()) ) {s = 57;}

                        else if ( (true) ) {s = 22;}

                         
                        input.seek(index21_21);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 21, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA36_eotS =
        "\16\uffff";
    static final String DFA36_eofS =
        "\1\14\15\uffff";
    static final String DFA36_minS =
        "\1\33\13\0\2\uffff";
    static final String DFA36_maxS =
        "\1\142\13\0\2\uffff";
    static final String DFA36_acceptS =
        "\14\uffff\1\2\1\1";
    static final String DFA36_specialS =
        "\1\uffff\1\2\1\10\1\3\1\5\1\0\1\7\1\1\1\11\1\4\1\6\1\12\2\uffff}>";
    static final String[] DFA36_transitionS = {
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

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "551:63: (op= assignmentOperator exx= expression )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA36_5 = input.LA(1);

                         
                        int index36_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_5);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA36_7 = input.LA(1);

                         
                        int index36_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_7);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA36_1 = input.LA(1);

                         
                        int index36_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_1);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA36_3 = input.LA(1);

                         
                        int index36_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA36_9 = input.LA(1);

                         
                        int index36_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_9);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA36_4 = input.LA(1);

                         
                        int index36_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_4);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA36_10 = input.LA(1);

                         
                        int index36_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_10);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA36_6 = input.LA(1);

                         
                        int index36_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_6);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA36_2 = input.LA(1);

                         
                        int index36_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_2);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA36_8 = input.LA(1);

                         
                        int index36_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_8);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA36_11 = input.LA(1);

                         
                        int index36_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_Aspect()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index36_11);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 36, _s, input);
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
    public static final BitSet FOLLOW_45_in_aspect475 = new BitSet(new long[]{0xFE01400000000010L,0x8010000000000001L,0x0000000000000003L});
    public static final BitSet FOLLOW_advice_in_aspect484 = new BitSet(new long[]{0xFE01400000000010L,0x8010000000000001L,0x0000000000000003L});
    public static final BitSet FOLLOW_pointcut_in_aspect491 = new BitSet(new long[]{0xFE01400000000010L,0x8010000000000001L,0x0000000000000003L});
    public static final BitSet FOLLOW_46_in_aspect499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_pointcut526 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_pointcutDecl_in_pointcut530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_formalParameters_in_pointcut534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_pointcut536 = new BitSet(new long[]{0x0000000000000010L,0x7FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_pointcut540 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_pointcut542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_pointcutDecl564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_pointcutExpression589 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_pointcutExpression591 = new BitSet(new long[]{0x0000000000000010L,0x7FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_pointcutExpression595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_pointcutExpression604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutAtom_in_pointcutExpressionOr627 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_pointcutExpressionOr629 = new BitSet(new long[]{0x0000000000000010L,0x7FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_pointcutExpressionOr633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutAtom_in_pointcutExpressionOr642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_subtypeMarker659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_withinType681 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_withinType683 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_type_in_withinType687 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000010L});
    public static final BitSet FOLLOW_subtypeMarker_in_withinType692 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_withinType698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_withinMethod720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_withinMethod722 = new BitSet(new long[]{0xFE01000000400010L,0x0000000000000001L});
    public static final BitSet FOLLOW_methodReference_in_withinMethod726 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_withinMethod728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_withinType_in_within745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_withinMethod_in_within754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_118_in_pointcutAtom779 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom781 = new BitSet(new long[]{0xFE01000000400010L,0x0000000000000001L});
    public static final BitSet FOLLOW_methodReference_in_pointcutAtom785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_119_in_pointcutAtom796 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom798 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_Identifier_in_pointcutAtom802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_120_in_pointcutAtom813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_121_in_pointcutAtom822 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom824 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_type_in_pointcutAtom828 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_fieldReference_in_pointcutAtom832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_122_in_pointcutAtom843 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom845 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_type_in_pointcutAtom849 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_123_in_pointcutAtom860 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom862 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_type_in_pointcutAtom866 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_namedPointcutReference_in_pointcutAtom877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_within_in_pointcutAtom886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_pointcutAtom897 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_typesOrParameters_in_pointcutAtom901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_125_in_pointcutAtom910 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom912 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000010L});
    public static final BitSet FOLLOW_expression_in_pointcutAtom916 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_126_in_pointcutAtom927 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom929 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000010L});
    public static final BitSet FOLLOW_expression_in_pointcutAtom933 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_pointcutAtom944 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_pointcutAtom946 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000010L});
    public static final BitSet FOLLOW_expression_in_pointcutAtom950 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_pointcutAtom959 = new BitSet(new long[]{0x0000000000000010L,0x7FE2000000002008L});
    public static final BitSet FOLLOW_pointcutAtom_in_pointcutAtom963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_pointcutAtom970 = new BitSet(new long[]{0x0000000000000010L,0x7FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_pointcutAtom974 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_pointcutAtom976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutDecl_in_namedPointcutReference999 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_namedPointcutReference1001 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000010L});
    public static final BitSet FOLLOW_argParams_in_namedPointcutReference1006 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_namedPointcutReference1014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_fieldReference1039 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_fieldReference1044 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_Identifier_in_fieldReference1048 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_Identifier_in_argParams1075 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_argParams1078 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_argParams_in_argParams1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_advice1116 = new BitSet(new long[]{0xFE01000000000010L,0x8000000000000001L,0x0000000000000003L});
    public static final BitSet FOLLOW_48_in_advice1121 = new BitSet(new long[]{0xFE01000000000010L,0x8000000000000001L,0x0000000000000003L});
    public static final BitSet FOLLOW_adviceTypeModifier_in_advice1129 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_formalParameters_in_advice1133 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L,0x000000000000000CL});
    public static final BitSet FOLLOW_adviceTypeModifierSpecifier_in_advice1138 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_advice1144 = new BitSet(new long[]{0x0000000000000010L,0x7FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_advice1148 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_adviceBody_in_advice1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adviceBlock_in_adviceBody1185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_adviceBlock1214 = new BitSet(new long[]{0xFE41E07F2C001FF0L,0x0007CC0001F7A7CDL,0x0000000000000010L});
    public static final BitSet FOLLOW_adviceBlockStatement_in_adviceBlock1221 = new BitSet(new long[]{0xFE41E07F2C001FF0L,0x0007CC0001F7A7CDL,0x0000000000000010L});
    public static final BitSet FOLLOW_46_in_adviceBlock1227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclarationStatement_in_adviceBlockStatement1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_adviceBlockStatement1274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adviceReturnStatement_in_adviceBlockStatement1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_adviceBlockStatement1300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_adviceReturnStatement1333 = new BitSet(new long[]{0xFE01000008000FD0L,0x0007CC00000003CDL,0x0000000000000010L});
    public static final BitSet FOLLOW_expression_in_adviceReturnStatement1355 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_adviceReturnStatement1361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_127_in_adviceTypeModifier1386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_128_in_adviceTypeModifier1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_adviceTypeModifier1404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_130_in_adviceTypeModifierSpecifier1428 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_adviceTypeModifierSpecifier1431 = new BitSet(new long[]{0xFE00001000000010L,0x0000000000000411L});
    public static final BitSet FOLLOW_formalParameter_in_adviceTypeModifierSpecifier1436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_adviceTypeModifierSpecifier1442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_131_in_adviceTypeModifierSpecifier1453 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_adviceTypeModifierSpecifier1456 = new BitSet(new long[]{0xFE00001000000010L,0x0000000000000411L});
    public static final BitSet FOLLOW_formalParameter_in_adviceTypeModifierSpecifier1461 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_adviceTypeModifierSpecifier1467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeOrVoid_in_methodReference1505 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_IdentifierWithWC_in_methodReference1511 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_fqn_in_methodReference1518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeOrVoid1538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_voidType_in_typeOrVoid1547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_fqn1583 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_fqn1589 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_simpleMethodHeader_in_fqn1597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_simpleMethodHeader1636 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_formalParameterTypes_in_simpleMethodHeader1644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_typesOrParameters1686 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003DDL,0x0000000000000010L});
    public static final BitSet FOLLOW_typesOrParameterDecls_in_typesOrParameters1691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_typesOrParameters1697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_typesOrParameterDecls1720 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_typesOrParameterDecls1723 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000010L});
    public static final BitSet FOLLOW_typesOrParameterDecls_in_typesOrParameterDecls1727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_formalParameterTypes1774 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000011L});
    public static final BitSet FOLLOW_formalParameterTypeDecls_in_formalParameterTypes1779 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_formalParameterTypes1785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formalParameterTypeDecls1810 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_formalParameterTypeDecls1813 = new BitSet(new long[]{0xFE00000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_formalParameterTypeDecls_in_formalParameterTypeDecls1817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_expression1861 = new BitSet(new long[]{0x00100A0000000002L,0x00000007F8000000L});
    public static final BitSet FOLLOW_assignmentOperator_in_expression1868 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000010L});
    public static final BitSet FOLLOW_expression_in_expression1872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_132_in_expression1908 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_arguments_in_expression1912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotations_in_synpred5_Aspect80 = new BitSet(new long[]{0x0000807F24000020L,0x0000000000000400L});
    public static final BitSet FOLLOW_packageDeclaration_in_synpred5_Aspect96 = new BitSet(new long[]{0x0000807F3C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_importDeclaration_in_synpred5_Aspect132 = new BitSet(new long[]{0x0000807F3C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Aspect153 = new BitSet(new long[]{0x0000807F2C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred5_Aspect202 = new BitSet(new long[]{0x0000807F2C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Aspect237 = new BitSet(new long[]{0x0000807F2C000022L,0x0000000000000400L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_synpred12_Aspect589 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_synpred12_Aspect591 = new BitSet(new long[]{0x0000000000000010L,0x7FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpression_in_synpred12_Aspect595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointcutAtom_in_synpred13_Aspect627 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_synpred13_Aspect629 = new BitSet(new long[]{0x0000000000000010L,0x7FE2000000002008L});
    public static final BitSet FOLLOW_pointcutExpressionOr_in_synpred13_Aspect633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_withinType_in_synpred15_Aspect745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclarationStatement_in_synpred36_Aspect1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred37_Aspect1274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adviceReturnStatement_in_synpred38_Aspect1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentOperator_in_synpred56_Aspect1868 = new BitSet(new long[]{0xFE01000000000FD0L,0x0007CC00000003CDL,0x0000000000000010L});
    public static final BitSet FOLLOW_expression_in_synpred56_Aspect1872 = new BitSet(new long[]{0x0000000000000002L});

}