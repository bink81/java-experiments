package algorithms.trees;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
	private TreeNode parent;
	private List<TreeNode> children = new ArrayList<>();

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
}