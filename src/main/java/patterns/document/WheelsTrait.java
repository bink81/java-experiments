package patterns.document;

import java.util.OptionalInt;

public interface WheelsTrait extends Document {

	final String KEY = "wheels";

	default OptionalInt getWheels() {
		final Number value = (Number) get(KEY);
		return value == null
				? OptionalInt.empty()
				: OptionalInt.of(value.intValue());
	}
}