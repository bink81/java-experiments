package structure.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a R-way Try Search algorithm
 *
 */
// Efficiency (typical case with words as keys):
// - search hit: L + ln N
// - search miss: ln N
// - insert: L + ln N
// Space: 4 * N
public class RwayTrieSearch<T> implements DataStructure<T> {
	private static final int R = 256;
	Node root = new Node();

	/*
	 * (non-Javadoc)
	 *
	 * @see structure.trees.DataStructure#put(java.lang.String, T)
	 */
	@Override
	public void put(String key, T value) {
		root = put(root, key, value, 0);
	}

	private Node put(Node x, String key, T value, int d) {
		if (x == null) {
			x = new Node();
		}
		if (d == key.length()) {
			x.value = value;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, value, d + 1);
		return x;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see structure.trees.DataStructure#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String key) {
		return get(key) != null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see structure.trees.DataStructure#get(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(String key) {
		Node x = get(root, key, 0);
		if (x == null) {
			return null;
		}
		return (T) x.value;
	}

	private Node get(Node x, String key, int d) {
		if (x == null) {
			x = new Node();
		}
		if (d == key.length()) {
			return x;
		}
		char c = key.charAt(d);
		return get(x.next[c], key, d + 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see structure.trees.DataStructure#delete(java.lang.String)
	 */
	@Override
	public void delete(String key) {
		put(key, null);
		// TODO: remove parent nodes if their value is null
	}

	public Iterable<String> keys() {
		List<String> queue = new ArrayList<String>();
		collect(root, "", queue);
		return queue;
	}

	private void collect(Node node, String prefix, List<String> queue) {
		if (node == null) {
			return;
		}
		if (node.value != null) {
			queue.add(prefix);
		}
		for (char c = 0; c < R; c++) {
			collect(node.next[c], prefix + c, queue);
		}
	}

	public Iterable<String> keysWithPrefix(final String prefix) {
		List<String> queue = new ArrayList<String>();
		Node node = get(root, prefix, 0);
		collect(node, prefix, queue);
		return queue;
	}

	private static class Node {
		Object value;
		Node[] next = new Node[R];
	}
}
