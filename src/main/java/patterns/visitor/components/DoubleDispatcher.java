package patterns.visitor.components;

public interface DoubleDispatcher {
	String visit(Refactoring refactoring);

	String visit(Improvement improvement);

	String visit(Growth growth);

	String visit(Unknown unknown) throws UnsuportedChangeException;
}
