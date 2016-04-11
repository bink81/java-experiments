package wrappers.booleans;

import wrappers.core.BooleanWrapper;
import wrappers.core.ValueSafe;

@ValueSafe
public final class IsOptional extends BooleanWrapper {
	private static final long serialVersionUID = -5210975541585203124L;

	private IsOptional(Boolean value) {
		super(value);
	}

	public static IsOptional of(Boolean value) {
		return new IsOptional(value);
	}
}
