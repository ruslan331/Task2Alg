package com.company;

public class BinomialHeap {
    private BHNode Nodes;
    private int size;
    public BinomialHeap() {
        Nodes = null;
        size = 0;
    }
    public void insert(int value) {

        if (value > 0) {
            BHNode temp
                    = new BHNode(value);
            if (Nodes == null) {
                Nodes = temp;
                size = 1;
            } else {
                unionNodes(temp);
                size++;
            }
        }
    }
    private void merge(BHNode binHeap) {
        BHNode temp1 = Nodes, temp2 = binHeap;
        while ((temp1 != null) && (temp2 != null)) {
            if (temp1.degree == temp2.degree) {
                BHNode tmp = temp2;
                temp2 = temp2.sibling;
                tmp.sibling = temp1.sibling;
                temp1.sibling = tmp;
                temp1 = tmp.sibling;
            } else {
                if (temp1.degree < temp2.degree) {

                    if ((temp1.sibling == null)
                            || (temp1.sibling.degree
                            > temp2.degree)) {
                        BHNode tmp = temp2;
                        temp2 = temp2.sibling;
                        tmp.sibling = temp1.sibling;
                        temp1.sibling = tmp;
                        temp1 = tmp.sibling;
                    } else {
                        temp1 = temp1.sibling;
                    }
                } else {
                    BHNode tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.sibling;
                    temp1.sibling = tmp;

                    if (tmp == Nodes) {
                        Nodes = temp1;
                    } else {
                    }
                }
            }
        }
        if (temp1 == null) {
            temp1 = Nodes;
            while (temp1.sibling != null) {
                temp1 = temp1.sibling;
            }
            temp1.sibling = temp2;
        } else {
        }
    }
    private void unionNodes(BHNode binHeap) {
        merge(binHeap);
        BHNode prevTemp = null, temp = Nodes,
                nextTemp = Nodes.sibling;
        while (nextTemp != null) {
            if ((temp.degree != nextTemp.degree)
                    || ((nextTemp.sibling != null)
                    && (nextTemp.sibling.degree
                    == temp.degree))) {
                prevTemp = temp;
                temp = nextTemp;
            } else {
                if (temp.key <= nextTemp.key) {
                    temp.sibling = nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                } else {
                    if (prevTemp == null) {
                        Nodes = nextTemp;
                    } else {
                        prevTemp.sibling = nextTemp;
                    }
                    temp.parent = nextTemp;
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = nextTemp;
                }
            }
            nextTemp = temp.sibling;
        }
    }
    public int extractMin() {
        if (Nodes == null)
            return -1;
        BHNode temp = Nodes, prevTemp = null;
        BHNode minNode = Nodes.findMinNode();
        System.out.println("DELETING MIN VALUE ("+minNode.key+"):");
        while (temp.key != minNode.key) {
            prevTemp = temp;
            temp = temp.sibling;
        }
        if (prevTemp == null) {
            Nodes = temp.sibling;
        } else {
            prevTemp.sibling = temp.sibling;
        }
        temp = temp.child;
        BHNode fakeNode = temp;
        while (temp != null) {
            temp.parent = null;
            temp = temp.sibling;
        }
        if ((Nodes == null) && (fakeNode == null)) {
            size = 0;
        } else {
            if ((Nodes == null) && (fakeNode != null)) {
                Nodes = fakeNode.reverse(null);
                size = Nodes.getSize();
            } else {
                if ((Nodes != null) && (fakeNode == null)) {
                    size = Nodes.getSize();
                } else {
                    unionNodes(fakeNode.reverse(null));
                    size = Nodes.getSize();
                }
            }
        }
        return minNode.key;
    }

    public void displayHeap() {
        System.out.print("\nHeap : ");
        displayHeap(Nodes);
        System.out.println("\n");
    }
    private void displayHeap(BHNode r) {
        if (r != null) {
            displayHeap(r.child);
            System.out.print(r.key + " ");
            displayHeap(r.sibling);
        }
    }
}
