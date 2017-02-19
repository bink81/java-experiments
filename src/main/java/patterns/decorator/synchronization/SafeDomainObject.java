package patterns.decorator.synchronization;

public class SafeDomainObject implements DomainObject {
	private final UnsafeDomainObject origin;

	SafeDomainObject(UnsafeDomainObject origin) {
		this.origin = origin;
	}

	@Override
	public synchronized void increaseCounter() {
		this.origin.increaseCounter();
	}

	public int getCounter() {
		return origin.getCounter();
	}
}
