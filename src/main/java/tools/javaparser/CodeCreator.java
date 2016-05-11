package tools.javaparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javaparser.ASTHelper;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;

public class CodeCreator {
	private static final Logger logger = LoggerFactory.getLogger(CodeCreator.class);

	public CompilationUnit createCU() {
		CompilationUnit compilationUnit = new CompilationUnit();
		addPackage(compilationUnit);
		ClassOrInterfaceDeclaration type = addClassDeclaration(compilationUnit);
		MethodDeclaration method = addMethodDeclaration(type);
		addMethodParameter(method);
		BlockStmt codeBlock = addBlock(method);
		addSystemOutPrintln(codeBlock);
		logger.info("{}", compilationUnit);
		return compilationUnit;
	}

	private void addPackage(CompilationUnit compilationUnit) {
		compilationUnit.setPackage(new PackageDeclaration(ASTHelper.createNameExpr("tools.javaparser")));
	}

	private ClassOrInterfaceDeclaration addClassDeclaration(CompilationUnit cu) {
		ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(ModifierSet.PUBLIC, false, "GeneratedClass");
		type.setJavaDoc(new JavadocComment(" Generated code "));
		ASTHelper.addTypeDeclaration(cu, type);
		return type;
	}

	private MethodDeclaration addMethodDeclaration(ClassOrInterfaceDeclaration type) {
		MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "main");
		method.setModifiers(ModifierSet.addModifier(method.getModifiers(), ModifierSet.STATIC));
		ASTHelper.addMember(type, method);
		JavadocComment javadocComment = new JavadocComment(" Comment... ");
		method.setJavaDoc(javadocComment);
		return method;
	}

	private void addMethodParameter(MethodDeclaration method) {
		Parameter param = ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "args");
		param.setVarArgs(true);
		ASTHelper.addParameter(method, param);
	}

	private BlockStmt addBlock(MethodDeclaration method) {
		BlockStmt block = new BlockStmt();
		method.setBody(block);
		return block;
	}

	private void addSystemOutPrintln(BlockStmt block) {
		NameExpr clazz = new NameExpr("System");
		FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
		MethodCallExpr call = new MethodCallExpr(field, "println");
		ASTHelper.addArgument(call, new StringLiteralExpr("Hello World!"));
		ASTHelper.addStmt(block, call);
	}

	public static void main(String[] args) {
		new CodeCreator().createCU();
	}
}
