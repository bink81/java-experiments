package concurrency.mapreducer;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class WordCounterTest {

	private static final String DUMMY_KEY_1 = "asd";
	private static final String DUMMY_KEY_2 = "aws";
	private static final String DUMMY_KEY_3 = "a";

	@Test
	public void testName() throws Exception {
		String[] records = new String[] { DUMMY_KEY_2 + " " + DUMMY_KEY_3 + " " + DUMMY_KEY_2,
				DUMMY_KEY_1, DUMMY_KEY_1 };
		Map<String, Object> results = new WordCounter().execute(records);

		Assert.assertEquals(3, results.size());
		Assert.assertEquals(2, results.get(DUMMY_KEY_1));
		Assert.assertEquals(2, results.get(DUMMY_KEY_2));
		Assert.assertEquals(1, results.get(DUMMY_KEY_3));
	}
}
