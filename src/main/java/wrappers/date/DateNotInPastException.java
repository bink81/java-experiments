package wrappers.date;

import java.util.Date;

public class DateNotInPastException extends RuntimeException {
	private static final long serialVersionUID = -5579331550788104681L;

	private final Date value;

	private final Date now;

	public DateNotInPastException(Date value, Date now) {
		this.value = value;
		this.now = now;
	}

	public Date getValue() {
		return value;
	}

	public Date getNow() {
		return now;
	}
}
