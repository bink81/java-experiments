package patterns.visitor.components;

public class ChangeProcessor implements VisitorDemo {
	private final Code code;

	public ChangeProcessor(Code code) {
		this.code = code;
	}

	@Override
	public String visit(Refactoring refactoring) {
		return Refactoring.LABEL;
	}

	@Override
	public String visit(Improvement improvement) {
		return Improvement.LABEL;
	}

	@Override
	public String visit(Growth growth) {
		return Growth.LABEL;
	}

	@Override
	public String visit(Unknown unknown) throws UnsuportedChangeException {
		throw new UnsuportedChangeException();
	}
}