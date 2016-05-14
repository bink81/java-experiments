package algorithms.trees;

public class TreeHeightCalculator {
	private final int parents[];

	/**
	 * @param parents
	 *          defines index of parent for each node. Value -1 determines a root node
	 */
	public TreeHeightCalculator(int parents[]) {
		this.parents = parents;
	}

	/**
	 * assumptions: there is exactly one root and the input represents a tree
	 */
	public int computeHeight() {
		TreeNode root = createTree();
		return recursiveHeightCalculation(root);
	}

	private TreeNode createTree() {
		TreeNode[] nodes = new TreeNode[parents.length];
		for (int i = 0; i < parents.length; i++) {
			nodes[i] = new TreeNode();
		}
		TreeNode root = null;
		for (int i = 0; i < parents.length; i++) {
			int parentId = parents[i];
			if (parentId >= 0) {
				nodes[i].setParent(nodes[parentId]);
				nodes[parentId].getChildren().add(nodes[i]);
			} else {
				root = nodes[i];
			}
		}
		return root;
	}

	private int recursiveHeightCalculation(TreeNode treeNode) {
		int max = 0;
		for (TreeNode child : treeNode.getChildren()) {
			int newMax = recursiveHeightCalculation(child);
			if (newMax > max) {
				max = newMax;
			}
		}
		return ++max;
	}
}
