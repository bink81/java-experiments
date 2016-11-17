package patterns.visitor.components;

public class Refactoring implements Change {
	public static final String LABEL = "Refactoring";

	@Override
	public String accept(VisitorDemo visitor) {
		return visitor.visit(this);
	}
}
