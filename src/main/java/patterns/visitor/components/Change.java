package patterns.visitor.components;

public interface Change {
	String accept(VisitorDemo visitor) throws UnsuportedChangeException;
}
