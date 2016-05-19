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

	private Node put(Node node, String key, T value, int index) {
		if (node == null) {
			node = new Node();
		}
		if (index == key.length()) {
			node.value = value;
			return node;
		}
		char character = key.charAt(index);
		node.next[character] = put(node.next[character], key, value, index + 1);
		return node;
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

	private Node get(Node node, String key, int index) {
		if (node == null) {
			node = new Node();
		}
		if (index == key.length()) {
			return node;
		}
		char character = key.charAt(index);
		return get(node.next[character], key, index + 1);
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

	public String longestPrefixOf(String query) {
		int length = search(root, query, 0, 0);
		return query.substring(0, length);
	}

	private int search(final Node node, final String query, final int index, int length) {
		if (node == null) {
			return length;
		}
		if (node.value != null) {
			length = index;
		}
		if (index == query.length()) {
			return length;
		}
		char character = query.charAt(index);
		return search(node.next[character], query, index + 1, length);
	}

	private static class Node {
		Object value;
		Node[] next = new Node[R];
	}
}
