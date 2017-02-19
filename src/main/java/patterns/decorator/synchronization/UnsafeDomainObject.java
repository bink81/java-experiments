package patterns.decorator.synchronization;

public class UnsafeDomainObject implements DomainObject {
	private int counter = 0;

	@Override
	public void increaseCounter() {
		int before = this.getCounter();
		int after = before + 1;
		counter = after;
	}

	public int getCounter() {
		return counter;
	}
}
