package patterns.document;

import java.util.OptionalDouble;

public interface PriceTrait extends Document {

	final String KEY = "price";

	default OptionalDouble getPrice() {
		final Number value = (Number) get(KEY);
		return value == null
				? OptionalDouble.empty()
				: OptionalDouble.of(value.doubleValue());
	}
}