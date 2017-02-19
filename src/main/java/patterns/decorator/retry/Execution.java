package patterns.decorator.retry;

public interface Execution<T> {
	// return true if executed successfully
	public T execute(String parameter) throws SomeException;
}
