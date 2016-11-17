package patterns.visitor.components;

public class Growth implements Change {
	public static final String LABEL = "Growth";

	@Override
	public String accept(DoubleDispatcher visitor) {
		return visitor.visit(this);
	}
}
