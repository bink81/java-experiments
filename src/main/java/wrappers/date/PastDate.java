package wrappers.date;

import java.util.Date;

import wrappers.core.DateWrapper;
import wrappers.core.ValueSafe;

@ValueSafe
public final class PastDate extends DateWrapper {

	private static final long serialVersionUID = -8236902430466692652L;

	private PastDate(Date value) {
		super(value);
		Date now = new Date(System.currentTimeMillis());
		if (value.after(now)) {
			throw new DateNotInPastException(value, now);
		}
	}

	public static PastDate of(Date value) {
		return new PastDate(value);
	}
}
