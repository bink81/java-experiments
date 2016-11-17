package patterns.visitor.components;

public class Refactoring implements Change {
	public static final String LABEL = "Refactoring";

	@Override
	public String accept(DoubleDispatcher visitor) {
		return visitor.visit(this);
	}
}
