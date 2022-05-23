package com.company;

import static com.company.BSTree.showTrunks;

public class RBTree {
        private RBTNode root;
        private RBTNode TNULL;
        public RBTree() {
                TNULL = new RBTNode();
                TNULL.color = 0;
                TNULL.left = null;
                TNULL.right = null;
                root = TNULL;
        }
        public void insert(int key) {
                RBTNode node = new RBTNode();
                node.parent = null;
                node.data = key;
                node.left = TNULL;
                node.right = TNULL;
                node.color = 1;

                RBTNode y = null;
                RBTNode x = this.root;

                while (x != TNULL) {
                        y = x;
                        if (node.data < x.data) {
                                x = x.left;
                        } else {
                                x = x.right;
                        }
                }

                node.parent = y;
                if (y == null) {
                        root = node;
                } else if (node.data < y.data) {
                        y.left = node;
                } else {
                        y.right = node;
                }

                if (node.parent == null) {
                        node.color = 0;
                        return;
                }

                if (node.parent.parent == null) {
                        return;
                }

                fixInsert(node);
        }

        private void fixInsert(RBTNode k) {
                RBTNode u;
                while (k.parent.color == 1) {
                        if (k.parent == k.parent.parent.right) {
                                u = k.parent.parent.left;
                                if (u.color == 1) {
                                        u.color = 0;
                                        k.parent.color = 0;
                                        k.parent.parent.color = 1;
                                        k = k.parent.parent;
                                } else {
                                        if (k == k.parent.left) {
                                                k = k.parent;
                                                rightRotate(k);
                                        }
                                        k.parent.color = 0;
                                        k.parent.parent.color = 1;
                                        leftRotate(k.parent.parent);
                                }
                        } else {
                                u = k.parent.parent.right;

                                if (u.color == 1) {
                                        u.color = 0;
                                        k.parent.color = 0;
                                        k.parent.parent.color = 1;
                                        k = k.parent.parent;
                                } else {
                                        if (k == k.parent.right) {
                                                k = k.parent;
                                                leftRotate(k);
                                        }
                                        k.parent.color = 0;
                                        k.parent.parent.color = 1;
                                        rightRotate(k.parent.parent);
                                }
                        }
                        if (k == root) {
                                break;
                        }
                }
                root.color = 0;
        }

        public void deleteNode(int data) {
                deleteNodeHelper(this.root, data);
        }
        private void deleteNodeHelper(RBTNode node, int key) {
                RBTNode z = TNULL;
                RBTNode x, y;
                while (node != TNULL) {
                        if (node.data == key) {
                                z = node;
                        }

                        if (node.data <= key) {
                                node = node.right;
                        } else {
                                node = node.left;
                        }
                }

                if (z == TNULL) {
                        System.out.println("Couldn't find key in the tree");
                        return;
                }

                y = z;
                int yOriginalColor = y.color;
                if (z.left == TNULL) {
                        x = z.right;
                        rbTransplant(z, z.right);
                } else if (z.right == TNULL) {
                        x = z.left;
                        rbTransplant(z, z.left);
                } else {
                        y = minimum(z.right);
                        yOriginalColor = y.color;
                        x = y.right;
                        if (y.parent == z) {
                                x.parent = y;
                        } else {
                                rbTransplant(y, y.right);
                                y.right = z.right;
                                y.right.parent = y;
                        }

                        rbTransplant(z, y);
                        y.left = z.left;
                        y.left.parent = y;
                        y.color = z.color;
                }
                if (yOriginalColor == 0) {
                        fixDelete(x);
                }
        }
        private void fixDelete(RBTNode x) {
                RBTNode s;
                while (x != root && x.color == 0) {
                        if (x == x.parent.left) {
                                s = x.parent.right;
                                if (s.color == 1) {
                                        s.color = 0;
                                        x.parent.color = 1;
                                        leftRotate(x.parent);
                                        s = x.parent.right;
                                }

                                if (s.left.color == 0 && s.right.color == 0) {
                                        s.color = 1;
                                        x = x.parent;
                                } else {
                                        if (s.right.color == 0) {
                                                s.left.color = 0;
                                                s.color = 1;
                                                rightRotate(s);
                                                s = x.parent.right;
                                        }

                                        s.color = x.parent.color;
                                        x.parent.color = 0;
                                        s.right.color = 0;
                                        leftRotate(x.parent);
                                        x = root;
                                }
                        } else {
                                s = x.parent.left;
                                if (s.color == 1) {
                                        s.color = 0;
                                        x.parent.color = 1;
                                        rightRotate(x.parent);
                                        s = x.parent.left;
                                }

                                if (s.right.color == 0 && s.right.color == 0) {
                                        s.color = 1;
                                        x = x.parent;
                                } else {
                                        if (s.left.color == 0) {
                                                s.right.color = 0;
                                                s.color = 1;
                                                leftRotate(s);
                                                s = x.parent.left;
                                        }

                                        s.color = x.parent.color;
                                        x.parent.color = 0;
                                        s.left.color = 0;
                                        rightRotate(x.parent);
                                        x = root;
                                }
                        }
                }
                x.color = 0;
        }

        private void rbTransplant(RBTNode u, RBTNode v) {
                if (u.parent == null) {
                        root = v;
                } else if (u == u.parent.left) {
                        u.parent.left = v;
                } else {
                        u.parent.right = v;
                }
                v.parent = u.parent;
        }


        public RBTNode minimum(RBTNode node) {
                while (node.left != TNULL) {
                        node = node.left;
                }
                return node;
        }


        public void leftRotate(RBTNode x) {
                RBTNode y = x.right;
                x.right = y.left;
                if (y.left != TNULL) {
                        y.left.parent = x;
                }
                y.parent = x.parent;
                if (x.parent == null) {
                        this.root = y;
                } else if (x == x.parent.left) {
                        x.parent.left = y;
                } else {
                        x.parent.right = y;
                }
                y.left = x;
                x.parent = y;
        }

        public void rightRotate(RBTNode x) {
                RBTNode y = x.left;
                x.left = y.right;
                if (y.right != TNULL) {
                        y.right.parent = x;
                }
                y.parent = x.parent;
                if (x.parent == null) {
                        this.root = y;
                } else if (x == x.parent.right) {
                        x.parent.right = y;
                } else {
                        x.parent.left = y;
                }
                y.right = x;
                x.parent = y;
        }



        public RBTNode getRoot() {
                return this.root;
        }

        public static void printRBTree(RBTNode root, Trunk prev, boolean isLeft) {
                if (root == null) {
                        return;
                }

                String prev_str = "    ";
                Trunk trunk = new Trunk(prev, prev_str);

                printRBTree(root.right, trunk, true);

                if (prev == null) {
                        trunk.str = "———";
                } else if (isLeft) {
                        trunk.str = ".———";
                        prev_str = "   |";
                } else {
                        trunk.str = "`———";
                        prev.str = prev_str;
                }

                showTrunks(trunk);
                System.out.print(" " + root.data);
                if (root.color==1)
                        System.out.print("(RED)");
                else System.out.print("(BLACK)");
                System.out.println();

                if (prev != null) {
                        prev.str = prev_str;
                }
                trunk.str = "   |";

                printRBTree(root.left, trunk, false);
        }


}
