package structure.trees;

/**
 * Implementation of a Ternary Search Tries algorithm
 *
 */
// Efficiency (typical case with words as keys):
// - search hit: L + ln N
// - search miss: ln N
// - insert: L + ln N
// Space: 4N
public class TernarySearchTriesSearch<T> {
	Node root = new Node();

	public void put(String key, T value) {
		root = put(root, key, value, 0);
	}

	private Node put(Node node, String key, T value, int index) {
		char character = key.charAt(index);
		if (node == null) {
			node = new Node();
			node.character = character;
		}
		if (character < node.character) {
			node.left = put(node.left, key, value, index);
		} else if (character > node.character) {
			node.right = put(node.right, key, value, index);
		} else if (index < key.length() - 1) {
			node.middle = put(node.middle, key, value, index + 1);
		} else {
			node.value = value;
		}
		return node;
	}

	public boolean contains(String key) {
		return get(key) != null;
	}

	@SuppressWarnings("unchecked")
	public T get(String key) {
		Node node = get(root, key, 0);
		if (node == null) {
			return null;
		}
		return (T) node.value;
	}

	private Node get(Node node, String key, int index) {
		char c = key.charAt(index);
		if (node == null) {
			return null;
		}
		if (c < node.character) {
			return get(node.left, key, index);
		} else if (c > node.character) {
			return get(node.right, key, index);
		} else if (index < key.length() - 1) {
			return get(node.middle, key, index + 1);
		} else {
			return node;
		}
	}

	public void delete(String key) {
		put(key, null);
		// TODO: remove parent nodes if their values are null
	}

	private static class Node {
		Object value;
		char character;
		Node left, middle, right;
	}
}
