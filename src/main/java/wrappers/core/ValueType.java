package wrappers.core;

import java.io.Serializable;

@ValueSafe
public interface ValueType extends Serializable {
	@Override
	int hashCode();

	@Override
	boolean equals(Object c);

	@Override
	String toString();
}