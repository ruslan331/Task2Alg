package com.company;

public class BHNode {
    int key, degree;
    BHNode parent;
    BHNode sibling;
    BHNode child;
    public BHNode(int k)
    {
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }
    public BHNode reverse(BHNode sibl)
    {
        BHNode ret;
        if (sibling != null)
            ret = sibling.reverse(this);
        else
            ret = this;
        sibling = sibl;
        return ret;
    }
    public BHNode findMinNode()
    {
        BHNode x = this, y = this;
        int min = x.key;
        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }
        return y;
    }
    public int getSize()
    {
        return (
                1 + ((child == null) ? 0 : child.getSize())
                        + ((sibling == null) ? 0 : sibling.getSize()));
    }
}
