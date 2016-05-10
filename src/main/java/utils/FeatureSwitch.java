package utils;

public class FeatureSwitch<T> implements NamedType<T> {
	private final String id;

	public FeatureSwitch(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
