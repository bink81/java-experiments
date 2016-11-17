package patterns.visitor.components;

public class Growth implements Change {
	public static final String LABEL = "Growth";

	@Override
	public String accept(VisitorDemo visitor) {
		return visitor.visit(this);
	}
}
