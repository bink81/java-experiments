package translations.terms;

import translations.Translatable;
import utils.NamedType;

public class CorrectFeatureSwitches<I> extends NamedType<I> implements Translatable {

	@Override
	public String getBundleName() {
		return "CorrectFeatureSwitches";
	}

	public CorrectFeatureSwitches(String id) {
		super(id);
	}

	public static final CorrectFeatureSwitches<Integer> S_1 =
			new CorrectFeatureSwitches<Integer>("s1");

	public static final CorrectFeatureSwitches<Integer> S_2 =
			new CorrectFeatureSwitches<Integer>("s2");
}
