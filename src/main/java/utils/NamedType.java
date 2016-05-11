package utils;

import translations.Translatable;

public abstract class NamedType<T> implements Translatable {
	private final String id;

	public NamedType(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
