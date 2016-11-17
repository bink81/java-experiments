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

public class StartTest {

	@Test
	public void testTriggerProcessingChangeOfRefactoring() throws Exception {
		Code code = new Code();
		Change change = new Refactoring();

		String actual = new Start().triggerProcessingChangeOf(code, change);

		Assert.assertEquals(Refactoring.LABEL, actual);
	}

	@Test
	public void testTriggerProcessingChangeOfImprovement() throws Exception {
		Code code = new Code();
		Change change = new Improvement();

		String actual = new Start().triggerProcessingChangeOf(code, change);

		Assert.assertEquals(Improvement.LABEL, actual);
	}

	@Test
	public void testTriggerProcessingChangeOfGrowth() throws Exception {
		Code code = new Code();
		Change change = new Growth();

		String actual = new Start().triggerProcessingChangeOf(code, change);

		Assert.assertEquals(Growth.LABEL, actual);
	}

	// Not really needed anymore since a new type will cause a compilation error if implementation
	// of "accept" method is missing
	@Test(expected = UnsuportedChangeException.class)
	public void testTriggerProcessingChangeOfUnknown() throws Exception {
		Code code = new Code();
		Change change = new Unknown();

		new Start().triggerProcessingChangeOf(code, change);
	}
}
