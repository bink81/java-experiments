package advent2016;

public class MutableIntegerValue extends MutableValue<Integer> {

	public MutableIntegerValue() {
		set(0);
	}

	public MutableIntegerValue(int initial) {
		set(initial);
	}
}
