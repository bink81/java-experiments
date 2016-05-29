package algorithms.strings;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import utils.RandomIntegerGenerator;

public class HashSubstringTest {

	@Test
	public void testGetOccurrences1() throws Exception {
		HashSubstring.Data input = new HashSubstring.Data("aba", "abacaba");

		List<Integer> actual = HashSubstring.getOccurrences(input);

		Assert.assertEquals(2, actual.size());
		Assert.assertEquals((Integer) 0, actual.get(0));
		Assert.assertEquals((Integer) 4, actual.get(1));
	}

	@Test
	public void testGetOccurrences2() throws Exception {
		HashSubstring.Data input = new HashSubstring.Data("Test", "testTesttesT");

		List<Integer> actual = HashSubstring.getOccurrences(input);

		Assert.assertEquals(1, actual.size());
		Assert.assertEquals((Integer) 4, actual.get(0));
	}

	@Test
	public void testGetOccurrencesAllFound() throws Exception {
		HashSubstring.Data input = new HashSubstring.Data("a", "aaa");

		List<Integer> actual = HashSubstring.getOccurrences(input);

		Assert.assertEquals(3, actual.size());
		Assert.assertEquals((Integer) 0, actual.get(0));
		Assert.assertEquals((Integer) 1, actual.get(1));
		Assert.assertEquals((Integer) 2, actual.get(2));
	}

	@Test
	public void testGetOccurrencesNoneFound() throws Exception {
		HashSubstring.Data input = new HashSubstring.Data("b", "aaa");

		List<Integer> actual = HashSubstring.getOccurrences(input);
		Assert.assertEquals(0, actual.size());
	}

	@Test
	public void testGetOccurrences11() throws Exception {
		String text = "qwertyuiopasdfghjklzxcvbnm1234567890";
		List<Integer> beginIndices = new RandomIntegerGenerator(10, text.length() - 1).toList();

		for (Integer beginIndex : beginIndices) {
			String pattern = text.substring(beginIndex);
			HashSubstring.Data input = new HashSubstring.Data(pattern, text);
			List<Integer> actual = HashSubstring.getOccurrences(input);
			Assert.assertEquals(1, actual.size());
			Assert.assertEquals(beginIndex, actual.get(0));
		}
	}
}
