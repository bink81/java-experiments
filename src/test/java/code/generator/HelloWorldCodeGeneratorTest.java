package code.generator;

import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class HelloWorldCodeGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testName() throws Exception {
		Path path = Paths.get("src", "test", "resources", "com", "example", "helloworld", "HelloWorld.java");
		String expected = DebugUtils.readFileContentAsString(path);

		OutputStream generatedCode = new HelloWorldCodeGenerator().generateCode();

		boolean stringsAreEqual = expected.equals(generatedCode.toString());
		// Compare details to debug problems faster
		if (!stringsAreEqual) {
			DebugUtils.compareStringsByCharacters(expected, generatedCode.toString());
			DebugUtils.compareStringsByLines(expected, generatedCode.toString());
		}
		Assert.assertTrue(stringsAreEqual);
	}
}
