package utils;

public class CorrectFeatureSwitch<I> extends FeatureSwitch<I> {
	public CorrectFeatureSwitch(String id) {
		super(id);
	}

	public static final CorrectFeatureSwitch<Integer> S_1 = new CorrectFeatureSwitch<Integer>("s1");

	public static final CorrectFeatureSwitch<Integer> S_2 = new CorrectFeatureSwitch<Integer>("s2");
}
