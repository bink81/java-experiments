package tools.javaparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

public class CodeUpdater {

	public static void main(String[] args) throws Exception {
		CodeUpdater codeParser = new CodeUpdater();
		Path path = Paths.get("src", "main", "resources", "tools", "javaparser", "TestJavaClass.java");
		CompilationUnit cu = codeParser.parseFile(path.toFile());
		codeParser.showMethods(cu);
		codeParser.updateMethods(cu);
	}

	private void updateMethods(CompilationUnit cu) {
		System.out.println("Updating a method declaration...");
		new MethodUpdater().visit(cu, "2");
		System.out.println(cu.toString());
	}

	private CompilationUnit parseFile(File file) throws FileNotFoundException, ParseException, IOException {
		FileInputStream in = new FileInputStream(file);
		System.out.println("Parsing file " + file);
		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		System.out.println("Original code:");
		System.out.println(cu.toString());
		return cu;
	}

	private void showMethods(CompilationUnit cu) {
		List<TypeDeclaration> types = cu.getTypes();
		for (TypeDeclaration type : types) {
			List<BodyDeclaration> members = type.getMembers();
			for (BodyDeclaration member : members) {
				if (member instanceof MethodDeclaration) {
					MethodDeclaration method = (MethodDeclaration) member;
					System.out.println("Method found: " + method.getName());
					System.out.println("Parameters: " + method.getParameters());
				}
			}
		}
	}
}