grammar Aspect;

options {
  backtrack=true; 
  memoize=true;
  output=AST;
  superClass = ChameleonParser;
}
import JavaP,JavaL;
@header {
package aspectsjava.input;

import chameleon.aspects.*;
import chameleon.aspects.advice.*;
import chameleon.aspects.pointcut.*;
import chameleon.aspects.pointcut.expression.*;

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
}
@members{

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
  
  public JavaTypeReference typeRef(String qn) {
    return ((Java)language()).createTypeReference(qn);
  }

}
// starting point for parsing a java file
/* The annotations are separated out to make parsing faster, but must be associated with
   a packageDeclaration or a typeDeclaration (and not an empty one). */
compilationUnit returns [CompilationUnit element] 
@init{ 
NamespacePart npp = null;
retval.element = getCompilationUnit();
}
    :  ( annotations
        (   np=packageDeclaration
                {npp=np.element;
                 retval.element.add(npp);
                 npp.addImport(new DemandImport(new NamespaceReference("java.lang")));
                } 
            (imp=importDeclaration{npp.addImport(imp.element);})* 
            (typech=typeDeclaration
                {processType(npp,typech.element);
                }
            )*
        |   cd=classOrInterfaceDeclaration
               {npp = new NamespacePart(language().defaultNamespace());
                retval.element.add(npp);
                npp.addImport(new DemandImport(new NamespaceReference("java.lang")));
                processType(npp,cd.element);
               } 
            (typech=typeDeclaration
               {processType(npp,typech.element);
               }
            )*
        )
    |   (np=packageDeclaration
            {
              npp=np.element;
            }
         )?
        {
         if(npp == null) {
           npp = new NamespacePart(language().defaultNamespace());
         }
         npp.addImport(new DemandImport(new NamespaceReference("java.lang")));
         retval.element.add(npp);
        }
        (imp=importDeclaration
          {npp.addImport(imp.element);}
        )* 
        (typech=typeDeclaration
          {
           processType(npp,typech.element);
          }
          |
          ad=aspect
      	   {
       		 npp.add(ad.element);       	
       	  }
        )* )
    ;

aspect returns [Aspect element]
@after{setLocation(retval.element, retval.start, retval.stop);}
	: asp='aspect' name=Identifier {retval.element = new Aspect($name.text);} 
	'{' 	// This is: (advice|pointcut)*
	((adv=advice{retval.element.addAdvice(adv.element);})|(ptc=pointcut{retval.element.addPointcut(ptc.element);}))* 
	'}' 
	
	{
		setKeyword(retval.element, asp);
	}
	;

pointcut returns [CrossReferencePointcut element]
@after{setLocation(retval.element, decl.start, decl.stop);}
	: pct='pointcut' decl=pointcutDecl pars=formalParameters ':' expr=pointcutExpression ';'
	
	{
		SimpleNameDeclarationWithParametersHeader header = new SimpleNameDeclarationWithParametersHeader(decl.element);
		header.addFormalParameters(pars.element);
		setLocation(header, decl.start, pars.stop);
		retval.element = new CrossReferencePointcut(header, expr.element);
		setKeyword(retval.element, pct);
	}
	;

pointcutDecl returns [String element]
	: name=Identifier {retval.element = $name.text;}
	;
	
pointcutExpression returns [PointcutExpression element]
@after{setLocation(retval.element, retval.start, retval.stop);}
	: expr1=pointcutExpressionOr '&&' expr2=pointcutExpression {retval.element = new PointcutExpressionAnd(expr1.element, expr2.element);}
	| expr=pointcutExpressionOr {retval.element = expr.element;}
	;

pointcutExpressionOr returns [PointcutExpression element]
@after{setLocation(retval.element, retval.start, retval.stop);}
	: expr1=pointcutAtom '||' expr2=pointcutExpressionOr {retval.element = new PointcutExpressionOr(expr1.element, expr2.element);}
	| expr=pointcutAtom {retval.element = expr.element;}
	;

	
pointcutAtom returns [PointcutExpression element]
@after{setLocation(retval.element, retval.start, retval.stop);}
	: cl='call' '(' metref=methodReference ')' {retval.element = new CrossReferencePointcutExpression(metref.element); setKeyword(retval.element, cl);}
	| '!' expr1=pointcutAtom {retval.element = new PointcutExpressionNot(expr1.element);}
	| '(' expr2=pointcutExpression ')' {retval.element = expr2.element;}
	;

advice returns [Advice element]
@init{TypeReference tref = null;}
@after{setLocation(retval.element, retval.start, retval.stop);}
	: (t=type {tref=t.element;}| 'void' {tref = typeRef("void");}) advtype=adviceType pars=formalParameters ':' decl=pointcutDecl args=arguments
	
	{
		retval.element=new Advice(advtype.element, tref);
		PointcutReference ref = new PointcutReference();
		ref.addAllArguments(args.element);
		ref.setName(decl.element);
		retval.element.setPointcutReference(ref);
		retval.element.addFormalParameters(pars.element);
		setLocation(ref, decl.start, args.stop);
	} 
	bdy=methodBody
	{
		retval.element.setBody(bdy.element);
		setKeyword(retval.element, advtype.start);
	}
	;

adviceType returns [AdviceType element]
	: 'before_' { retval.element = AdviceType.BEFORE; }
	| 'after_' {retval.element = AdviceType.AFTER; }
	| 'around_' {retval.element = AdviceType.AROUND; }
	;
        
methodReference returns [MethodReference element]
@after{setLocation(retval.element, retval.start, retval.stop);}
	: t=(IdentifierWithWC|Identifier|'void') name=fqn {retval.element = new MethodReference($t.text, name.element);}
	;
	
        
fqn returns [QualifiedMethodHeader element]
@init{CompositeQualifiedName prefixes = new CompositeQualifiedName();}
@after{setLocation(retval.element, retval.start, retval.stop);}
	: (id=(IdentifierWithWC|Identifier) '.' {prefixes.append(new SimpleNameSignature($id.text));})* mth=simpleMethodHeader {retval.element = new QualifiedMethodHeader(mth.element); retval.element.setPrefixes(prefixes);}
	;
        

simpleMethodHeader returns [PointcutMethodHeader element]
@after{setLocation(retval.element, retval.start, retval.stop);}
        :	name=(IdentifierWithWC|Identifier) pars=formalParameterTypes {retval.element = new PointcutMethodHeader($name.text, pars.element); } 
        ;
        
formalParameterTypes returns [List<TypeReference> element]
@init{retval.element = new ArrayList<TypeReference>();}
    :   '(' (pars=formalParameterTypeDecls {retval.element=pars.element;})? ')'
    ;

formalParameterTypeDecls returns [List<TypeReference> element]
    :   t=type (',' decls=formalParameterTypeDecls {retval.element=decls.element; })?
        {if(retval.element == null) {
         retval.element=new ArrayList<TypeReference>();}
         retval.element.add(0, t.element);
         }
    ;