package translations.terms;

import translations.Localizable;
import translations.Translatable;

@Localizable
public class ButtonTerms implements Translatable {

	private static final String NAME = "ButtonTerms";

	@Override
	public String getBundleName() {
		return NAME;
	}

	public static final String OK = "OK";

	public static final String CANCEL = "CANCEL";
}
