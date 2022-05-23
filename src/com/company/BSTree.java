package com.company;

public class BSTree {
    protected BSTNode root;

    public void insertNode(int key) {
        root = insertNode(key, root);
    }

    BSTNode insertNode(int key, BSTNode node) {
        // No node at current position --> store new node at current position
        if (node == null) {
            node = new BSTNode(key);
        }

        // Otherwise, traverse the tree to the left or right depending on the key
        else if (key < node.data) {
            node.left = insertNode(key, node.left);
        } else if (key >= node.data) {
            node.right = insertNode(key, node.right);
        }

        return node;
    }

    public void traversePreOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        traversePreOrder(node.left);
        traversePreOrder(node.right);
    }
    public void traversePostOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        traversePostOrder(node.left);
        System.out.print(node.data + " ");
        traversePostOrder(node.right);
    }
    public void traverseInOrder(BSTNode node) {
        if (node == null) {
            return;
        }

        traverseInOrder(node.left);
        traverseInOrder(node.right);
        System.out.print(node.data + " ");
    }
    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }

        showTrunks(p.prev);
        System.out.print(p.str);
    }
    public static void printBSTree(BSTNode root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }

        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);

        printBSTree(root.right, trunk, true);

        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }

        showTrunks(trunk);
        System.out.println(" " + root.data);

        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";

        printBSTree(root.left, trunk, false);
    }
}
