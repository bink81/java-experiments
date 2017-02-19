package patterns.decorator.retry;

public class SomeException extends Exception {
	private static final long serialVersionUID = 1L;

	public SomeException(String string) {
		super(string);
	}
}
