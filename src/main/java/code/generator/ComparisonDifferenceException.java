package code.generator;

public class ComparisonDifferenceException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String reference;
	private final String actual;
	private final Integer index;

	public ComparisonDifferenceException(String reference, String actual, Integer index) {
		this.reference = reference;
		this.actual = actual;
		this.index = index;
	}

	public final String getReference() {
		return reference;
	}

	public final String getActual() {
		return actual;
	}

	public final Integer getIndex() {
		return index;
	}
}
