package wrappers.core;

import java.io.Serializable;

import com.google.common.base.Objects;

public abstract class GenericWrapper<T extends Serializable> implements ValueType {
	private static final long serialVersionUID = -8179388800930925443L;

	private final T value;

	protected GenericWrapper(T value) {
		if (value == null) {
			throw new IllegalArgumentException("Value must not be null!");
		}
		this.value = value;
	}

	public final T get() {
		return value;
	}

	@Override
	public final int hashCode() {
		return Objects.hashCode(value);
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		GenericWrapper<T> other = (GenericWrapper<T>) obj;

		return Objects.equal(value, other.value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
