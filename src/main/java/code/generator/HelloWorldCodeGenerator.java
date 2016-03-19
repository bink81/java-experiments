package code.generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

public class HelloWorldCodeGenerator {

	public OutputStream generateCode() throws IOException, URISyntaxException {
		MethodSpec main = MethodSpec.methodBuilder("main")
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.returns(void.class)
				.addParameter(String[].class, "args")
				.addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
				.build();

		TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addMethod(main)
				.build();

		JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
				.build();

		OutputStream out = new ByteArrayOutputStream();
		Appendable stream = new PrintStream(out);
		javaFile.writeTo(stream);
		return out;
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		OutputStream result = new HelloWorldCodeGenerator().generateCode();
		System.out.println(result);
	}
}
