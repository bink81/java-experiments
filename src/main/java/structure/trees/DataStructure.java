package structure.trees;

public interface DataStructure<T> {

	void put(String key, T value);

	boolean contains(String key);

	T get(String key);

	void delete(String key);

}