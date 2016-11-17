package patterns.visitor;

import org.junit.Assert;
import org.junit.Test;

import patterns.visitor.components.Change;
import patterns.visitor.components.Code;
import patterns.visitor.components.Growth;
import patterns.visitor.components.Improvement;
import patterns.visitor.components.Refactoring;
import patterns.visitor.components.Unknown;
import patterns.visitor.components.UnsuportedChangeException;

public class SmellyCodeTest {

	@Test
	public void testTriggerProcessingChangeOfRefactoring() throws Exception {
		Code code = new Code();
		Change change = new Refactoring();

		String actual = new SmellyCode().triggerProcessingChangeOf(code, change);

		Assert.assertEquals(SmellyCode.REFACTORING, actual);
	}

	@Test
	public void testTriggerProcessingChangeOfImprovement() throws Exception {
		Code code = new Code();
		Change change = new Improvement();

		String actual = new SmellyCode().triggerProcessingChangeOf(code, change);

		Assert.assertEquals(SmellyCode.IMPROVEMENT, actual);
	}

	@Test
	public void testTriggerProcessingChangeOfGrowth() throws Exception {
		Code code = new Code();
		Change change = new Growth();

		String actual = new SmellyCode().triggerProcessingChangeOf(code, change);

		Assert.assertEquals(SmellyCode.GROWTH, actual);
	}

	@Test(expected = UnsuportedChangeException.class)
	public void testTriggerProcessingChangeOfUnknown() throws Exception {
		Code code = new Code();
		Change change = new Unknown();

		new SmellyCode().triggerProcessingChangeOf(code, change);
	}
}
