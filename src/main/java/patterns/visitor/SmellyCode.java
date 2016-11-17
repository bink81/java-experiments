package patterns.visitor;

import patterns.visitor.components.Change;
import patterns.visitor.components.Code;
import patterns.visitor.components.Growth;
import patterns.visitor.components.Improvement;
import patterns.visitor.components.Refactoring;
import patterns.visitor.components.UnsuportedChangeException;

public class SmellyCode {
	public static final String REFACTORING = "Refactoring";

	public static final String IMPROVEMENT = "Improvement";

	public static final String GROWTH = "Growth";

	public String triggerProcessingChangeOf(Code code, Change change)
			throws UnsuportedChangeException {
		verifyChangeOf(code, change);

		if (change instanceof Refactoring) {
			return processRefactoring(code, (Refactoring) change);
		}
		else if (change instanceof Improvement) {
			return processImprovement(code, (Improvement) change);
		}
		else if (change instanceof Growth) {
			return processGrowth(code, (Growth) change);
		}
		else {
			throw new UnsuportedChangeException();
		}
	}

	private void verifyChangeOf(Code code, Change change) {
		// TODO Auto-generated method stub
	}

	private String processRefactoring(Code code, Refactoring change) {
		return REFACTORING;
	}

	private String processImprovement(Code code, Improvement change) {
		return IMPROVEMENT;
	}

	private String processGrowth(Code code, Growth change) {
		return GROWTH;
	}
}
