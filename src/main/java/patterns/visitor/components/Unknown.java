package patterns.visitor.components;

public class Unknown implements Change {

	@Override
	public String accept(VisitorDemo visitor) throws UnsuportedChangeException {
		return visitor.visit(this);
	}
}
