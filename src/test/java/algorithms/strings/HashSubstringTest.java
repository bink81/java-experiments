package algorithms.strings;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import utils.RandomIntegerGenerator;
import utils.RandomStringGenerator;

public class HashSubstringTest {

	@Test
	public void testGetOccurrences1() throws Exception {
		List<Integer> actual = HashSubstring.getOccurrences("aba", "abacaba");

		Assert.assertEquals(2, actual.size());
		Assert.assertEquals((Integer) 0, actual.get(0));
		Assert.assertEquals((Integer) 4, actual.get(1));
	}

	@Test
	public void testGetOccurrences2() throws Exception {
		List<Integer> actual = HashSubstring.getOccurrences("Test", "testTesttesT");

		Assert.assertEquals(1, actual.size());
		Assert.assertEquals((Integer) 4, actual.get(0));
	}

	@Test
	public void testGetOccurrencesAllFound() throws Exception {
		List<Integer> actual = HashSubstring.getOccurrences("a", "aaa");

		Assert.assertEquals(3, actual.size());
		Assert.assertEquals((Integer) 0, actual.get(0));
		Assert.assertEquals((Integer) 1, actual.get(1));
		Assert.assertEquals((Integer) 2, actual.get(2));
	}

	@Test
	public void testGetOccurrencesNoneFound() throws Exception {
		List<Integer> actual = HashSubstring.getOccurrences("b", "aaa");

		Assert.assertEquals(0, actual.size());
	}

	@Test
	public void testGetOccurrences11() throws Exception {
		String text = "qwertyuiopasdfghjklzxcvbnm1234567890";
		List<Integer> beginIndices = new RandomIntegerGenerator(10, text.length() - 1).produceList();
		for (Integer beginIndex : beginIndices) {
			String pattern = text.substring(beginIndex);
			List<Integer> actual = HashSubstring.getOccurrences(pattern, text);

			Assert.assertEquals(1, actual.size());
			Assert.assertEquals(beginIndex, actual.get(0));
		}
	}

	@Test
	public void testGetOccurrencesRandom() throws Exception {
		String text = new RandomStringGenerator(100).generateString();
		List<Integer> beginIndices = new RandomIntegerGenerator(10, text.length() - 1).produceList();
		for (Integer beginIndex : beginIndices) {
			String pattern = text.substring(beginIndex);

			List<Integer> actual = HashSubstring.getOccurrences(pattern, text);
			List<Integer> actualNaively = HashSubstring.getOccurrencesNaively(pattern, text);

			Assert.assertEquals(actualNaively, actual);
		}
	}
}
