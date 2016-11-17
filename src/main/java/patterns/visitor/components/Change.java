package patterns.visitor.components;

public interface Change {
	String accept(DoubleDispatcher visitor) throws UnsuportedChangeException;
}
