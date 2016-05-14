package code.validation;

import org.junit.Assert;
import org.junit.Test;

public class BracketValidatorTest {

	@Test
	public void test1() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("[]");
		Assert.assertEquals(BracketValidator.SUCCESS, actual);

	}

	@Test
	public void test2() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("{}[]");
		Assert.assertEquals(BracketValidator.SUCCESS, actual);
	}

	@Test
	public void test3() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("[()]");
		Assert.assertEquals(BracketValidator.SUCCESS, actual);
	}

	@Test
	public void test4() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("(())");
		Assert.assertEquals(BracketValidator.SUCCESS, actual);
	}

	@Test
	public void test5() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("{[]}()");
		Assert.assertEquals(BracketValidator.SUCCESS, actual);
	}

	@Test
	public void test6() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("{");
		Assert.assertEquals(0, actual);
	}

	@Test
	public void test7() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("{[}");
		Assert.assertEquals(2, actual);
	}

	@Test
	public void test8() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("foo(bar);");
		Assert.assertEquals(BracketValidator.SUCCESS, actual);
	}

	@Test
	public void test9() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("foo(bar[i);");
		Assert.assertEquals(9, actual);
	}

	@Test
	public void test77() throws Exception {
		int actual = BracketValidator.findUnbalanancedBracketPosition("[](()");
		Assert.assertEquals(2, actual);
	}
}
