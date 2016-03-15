package patterns.document;

public interface ModelTrait extends Document {

	final String KEY = "model";

	default String getModel() {
		final String value = (String) get(KEY);
		return value == null
				? ""
				: value;
	}
}
