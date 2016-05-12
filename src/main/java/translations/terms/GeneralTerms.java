package translations.terms;

import translations.Localizable;
import translations.Translatable;

@Localizable
public class GeneralTerms implements Translatable {

	public static final String NAME = "GeneralTerms";

	@Override
	public String getBundleName() {
		return NAME;
	}

	public static final String USER = "USER";

	public static final String ID = "ID";

	public static final String EMPLOYEE_NUMBER = "EMPLOYEE_NUMBER";

	public static final String BIRTH_DATE = "DATE_OF_BIRTH";

	/**
	 * @deprecated
	 * 			The BIRTHDAY indicates only a day and a month so you probably want
	 *             {@link #BIRTH_DATE}
	 */
	@Deprecated
	public static final String BIRTHDAY = "BIRTHDAY"; // Example of a common language error

	public static final String EMAIL = "EMAIL";

	public static final String ACCOUNTS_RECEIVABLE = "ACCOUNTS_RECEIVABLE";

	public static final String AR = "ACCOUNTS_RECEIVABLE_ABBREVIATION";
}
