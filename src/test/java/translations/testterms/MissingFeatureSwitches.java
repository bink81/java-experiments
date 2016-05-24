package translations.testterms;

import translations.Localizable;
import utils.NamedType;

@Localizable
public class MissingFeatureSwitches<I> extends NamedType<I> {

	@Override
	public String getBundleName() {
		return "IncorrectFeatureSwitches";
	}

	public MissingFeatureSwitches(String id) {
		super(id);
	}

	public static final MissingFeatureSwitches<Integer> A =
			new MissingFeatureSwitches<Integer>("a");
}
