package patterns.document;

public interface ColorTrait extends Document {

	final String KEY = "color";

	default String getColor() {
		final String value = (String) get(KEY);
		return value == null
				? ""
				: value;
	}
}