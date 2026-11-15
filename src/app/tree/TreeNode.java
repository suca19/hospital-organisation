package app.tree;

import app.model.Employee;

// One position in the hierarchy tree: an employee plus at most two children (left and right)
public class TreeNode {
    private final Employee employee;
    private TreeNode left;   // first child (null = empty slot)
    private TreeNode right;  // second child (null = empty slot)

    // a new node starts with no children
    public TreeNode(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }

    public Employee getEmployee() {
        return employee;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}