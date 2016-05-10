package utils;

public class IncorrectFeatureSwitch<I> extends FeatureSwitch<I> {
	public IncorrectFeatureSwitch(String id) {
		super(id);
	}

	public static final IncorrectFeatureSwitch<Integer> A =
			new IncorrectFeatureSwitch<Integer>("a");
}
