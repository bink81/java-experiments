package patterns.visitor.components;

public class Unknown implements Change {

	@Override
	public String accept(DoubleDispatcher visitor) throws UnsuportedChangeException {
		return visitor.visit(this);
	}
}
