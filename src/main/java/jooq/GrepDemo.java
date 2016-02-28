package jooq;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jooq.lambda.Seq;

public class GrepDemo {
	public static void main(String[] args) throws IOException {
		displayFoundWithTwoSurroundingLines("pom.xml", "<configuration>");
	}

	private static void displayFoundWithTwoSurroundingLines(String url, CharSequence searchString) throws IOException {
		Seq.seq(Files.readAllLines(Paths.get(new File(url).toURI()))).window()
				.filter(w -> w.value().contains(searchString)).forEach(w -> {
					System.out.println();
					System.out.println("-1:" + w.lag().orElse(""));
					System.out.println(" 0:" + w.value());
					System.out.println("+1:" + w.lead().orElse(""));
				});
	}
}
