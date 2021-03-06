package translations.terms;

import translations.Localizable;
import translations.Translatable;
import utils.NamedType;

@Localizable
public class FeatureSwitches<I> extends NamedType<I> implements Translatable {

	public static final String NAME = "FeatureSwitches";

	@Override
	public String getBundleName() {
		return NAME;
	}

	public FeatureSwitches(String id) {
		super(id);
	}

	public static final FeatureSwitches<Integer> ENABLE_OPTION_1 =
			new FeatureSwitches<Integer>("ENABLE_OPTION_1");

	public static final FeatureSwitches<Integer> ENABLE_OPTION_2 =
			new FeatureSwitches<Integer>("ENABLE_OPTION_2");
}
