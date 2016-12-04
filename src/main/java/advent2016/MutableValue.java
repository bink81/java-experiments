package advent2016;

public class MutableValue<E> {
	private E value;

	public E get() {
		return value;
	}

	public void set(E value) {
		this.value = value;
	}
}
