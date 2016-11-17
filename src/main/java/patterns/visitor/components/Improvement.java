package patterns.visitor.components;

public class Improvement implements Change {
	public static final String LABEL = "Improvement";

	@Override
	public String accept(DoubleDispatcher visitor) {
		return visitor.visit(this);
	}
}
