package app.tree;

import app.model.Employee;

public class TreeNode {
    private final Employee employee;
    private TreeNode left;
    private TreeNode right;

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