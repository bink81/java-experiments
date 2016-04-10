package wrappers.core;

import java.math.BigDecimal;

public abstract class BigDecimalWrapper extends GenericWrapper<BigDecimal> {

	private static final long serialVersionUID = 3661950284867406541L;

	protected BigDecimalWrapper(BigDecimal value) {
		super(value);
	}
}
