package patterns.visitor;

import patterns.visitor.components.Change;
import patterns.visitor.components.ChangeProcessor;
import patterns.visitor.components.Code;
import patterns.visitor.components.UnsuportedChangeException;

public class Start {

	public String triggerProcessingChangeOf(Code code, Change change)
			throws UnsuportedChangeException {
		verifyChangeOf(code, change);
		return change.accept(new ChangeProcessor(code));
	}

	private void verifyChangeOf(Code code, Change change) {
		// TODO Auto-generated method stub
	}
}
