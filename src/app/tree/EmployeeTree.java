package app.tree;

import app.model.Employee;
import java.util.Queue;
import java.util.LinkedList;

public class EmployeeTree {
    private TreeNode root;

    public EmployeeTree() {
        this.root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void insert(Employee employee) {
        if (root == null) {
            root = new TreeNode(employee);
        } else {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while(!queue.isEmpty()) {
                TreeNode current = queue.poll();

                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode(employee));
                    break;
                } else {
                    queue.add(current.getLeft());
                }

                if (current.getRight() == null) {
                    current.setRight(new TreeNode(employee));
                    break;
                } else {
                    queue.add(current.getRight());
                }

            }
        }
    }

    // level-order traversal: prints the tree level by level (top to bottom, left to right) using a queue
    public void printLevelOrder() {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int nodesInLevel = queue.size(); // everyone in the queue right now is on this level
            System.out.println("Level " + level + ":");

            for (int k = 0; k < nodesInLevel; k++) {
                TreeNode current = queue.poll();
                Employee employee = current.getEmployee();
                System.out.println("   " + employee.getName() + " | "
                        + employee.getManagerType().getManagerTypeName() + " | "
                        + employee.getDepartment().getDepartmentName());

                // the children belong to the next level, so they wait at the end of the queue
                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }
            level++;
        }
    }

    // height of the whole tree (number of levels)
    public int height() {
        return height(root);
    }

    // recursive: an empty tree has height 0, otherwise 1 + the taller side
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // total number of nodes in the tree
    public int countNodes() {
        return countNodes(root);
    }

    // recursive: an empty tree has 0 nodes, otherwise this node + the left side + the right side
    private int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }
}
