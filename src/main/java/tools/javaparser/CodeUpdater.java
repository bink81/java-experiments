package tools.javaparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;

public class CodeUpdater {
	private static final Logger logger = LoggerFactory.getLogger(CodeUpdater.class);

	public static void main(String[] args) throws Exception {
		CodeUpdater codeParser = new CodeUpdater();
		Path path = Paths.get("src", "main", "resources", "tools", "javaparser", "TestJavaClass.java");
		CompilationUnit compilationUnit = codeParser.parseFile(path.toFile());
		codeParser.showMethods(compilationUnit);
		codeParser.updateMethods(compilationUnit);
	}

	private void updateMethods(CompilationUnit compilationUnit) {
		logger.info("Updating a method declaration...");
		new MethodUpdater().visit(compilationUnit, "2");
		logger.info("New code:\n{}", compilationUnit);
	}

	private CompilationUnit parseFile(File file) throws FileNotFoundException, ParseException, IOException {
		FileInputStream inputStream = new FileInputStream(file);
		logger.info("Parsing file {}", file);
		CompilationUnit compilationUnit;
		try {
			compilationUnit = JavaParser.parse(inputStream);
		} finally {
			inputStream.close();
		}
		logger.info("Original code:\n{}", compilationUnit);
		return compilationUnit;
	}

	private void showMethods(CompilationUnit compilationUnit) {
		List<TypeDeclaration> typeDeclarations = compilationUnit.getTypes();
		for (TypeDeclaration typeDeclaration : typeDeclarations) {
			List<BodyDeclaration> members = typeDeclaration.getMembers();
			for (BodyDeclaration member : members) {
				if (member instanceof MethodDeclaration) {
					MethodDeclaration method = (MethodDeclaration) member;
					String methodName = method.getName();
					List<Parameter> parameters = method.getParameters();
					logger.info("Method name={}, parameters={}", methodName, parameters);
				}
			}
		}
	}
}