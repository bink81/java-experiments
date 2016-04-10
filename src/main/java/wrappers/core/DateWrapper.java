package wrappers.core;

import java.util.Date;

public abstract class DateWrapper extends GenericWrapper<Date> {
	private static final long serialVersionUID = 1118431922685094231L;

	protected DateWrapper(Date value) {
		super(value);
	}
}
