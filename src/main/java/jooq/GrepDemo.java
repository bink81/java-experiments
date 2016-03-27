package jooq;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jooq.lambda.Seq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrepDemo {
	private static final Logger LOGGER = LoggerFactory.getLogger(GrepDemo.class);

	public static void main(String[] args) throws IOException {
		displayFoundWithTwoSurroundingLines("pom.xml", "<configuration>");
	}

	private static void displayFoundWithTwoSurroundingLines(String url, CharSequence searchString) throws IOException {
		Seq.seq(Files.readAllLines(Paths.get(new File(url).toURI()))).window()
				.filter(w -> w.value().contains(searchString)).forEach(w -> {
					LOGGER.info("\n");
					LOGGER.info("-1:{}", w.lag().orElse(""));
					LOGGER.info(" 0:{}", w.value());
					LOGGER.info("+1:{}", w.lead().orElse(""));
				});
	}
}
