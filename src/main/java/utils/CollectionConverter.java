package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Preconditions;

public class CollectionConverter<T> {

	private final Collection<T> collection;

	public CollectionConverter(Collection<T> collection) {
		Preconditions.checkNotNull(collection, "collection must not be null");
		this.collection = collection;
	}

	public List<T> toList() {
		List<T> list;
		if (collection instanceof List) {
			list = (List<T>) collection;
		} else {
			list = new ArrayList<T>(collection);
		}
		return list;
	}
}
