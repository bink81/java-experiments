package code.generator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Assert;

public class HelloWorldCodeGeneratorTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldCodeGeneratorTest.class);

	@Test
	public void testName() throws IOException, URISyntaxException {
		Path path = Paths.get("src", "test", "resources", "com", "example", "helloworld", "HelloWorld.java");
		String reference = DebugUtils.readFileContentAsString(path);

		OutputStream generatedCode = new HelloWorldCodeGenerator().generateCode();

		boolean stringsAreEqual = reference.equals(generatedCode.toString());
		// Compare details to debug problems faster
		if (!stringsAreEqual) {
			try {
				DebugUtils.compareStringsByCharacters(reference, generatedCode.toString());
			} catch (ComparisonDifferenceException e) {
				LOGGER.info("Different characters ('{}'!='{}') at index {}",
						new Object[] { e.getReference(), e.getActual(), e.getIndex() });
				LOGGER.debug("Stack trace", e);
			}
			try {
				DebugUtils.compareStringsByLines(reference, generatedCode.toString());
			} catch (ComparisonDifferenceException e) {
				LOGGER.warn("Lines differ:\n{}\nVS\n{}\nat line number {}",
						new Object[] { e.getReference(), e.getActual(), e.getIndex() });
				LOGGER.debug("Stack trace", e);
			}
		}
		Assert.assertTrue(stringsAreEqual);
	}
}
