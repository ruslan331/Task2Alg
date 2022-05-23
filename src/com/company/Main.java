package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("TASK 1 (BINARY SEARCH TREE): ");
	    BSTree bstree = new BSTree();
	    int N=20;
	    double d;
	    int[] arr=new int[N];
	    for (int i=0;i<N;i++)
        {
            d= Math.random()*101;
            arr[i]=(int)d;
        }
        for (int i=0;i<N;i++)
        {
            bstree.insertNode(arr[i]);
        }
        System.out.println(Arrays.toString(arr));
        bstree.printBSTree(bstree.root, null, false);
        System.out.println();
        System.out.print("Prefix notation: ");
        bstree.traversePreOrder(bstree.root);
        System.out.println();
        System.out.print("Infix notation: ");
        bstree.traversePostOrder(bstree.root);
        System.out.println();
        System.out.print("Postfix notation: ");
        bstree.traverseInOrder(bstree.root);
        System.out.println();
        System.out.println();

        System.out.println("TASK 2 (RED BLACK TREE): ");
        RBTree rbtree = new RBTree();
        N=20;
        int[] arr1=new int[N];
        for (int i=0;i<N;i++)
        {
            d= Math.random()*101;
            arr1[i]=(int)d;
        }
        for (int i=0;i<N;i++)
        {
            rbtree.insert((int)arr1[i]);
        }
        System.out.println(Arrays.toString(arr1));
        rbtree.printRBTree(rbtree.getRoot(), null, false);
        System.out.println("\nAfter deleting " + arr1[6] + ":\n");
        rbtree.deleteNode(arr1[6]);
        rbtree.printRBTree(rbtree.getRoot(), null, false);
        System.out.println();

        System.out.println("TASK 3 (BINARY HEAP): ");
        System.out.println();
        N=20;
        BinaryHeap bheap = new BinaryHeap(N);
        int[] arr2=new int[N];
        for (int i=0;i<N;i++)
        {
            d= Math.random()*101;
            arr2[i]=(int)d;
        }
        for (int i=0;i<N;i++)
        {
            bheap.insertKey(arr2[i]);
        }
        System.out.println(Arrays.toString(arr2));
        System.out.println();
        bheap.printeHeapInArray();
        bheap.print_heap();
        bheap.extractMin();
        bheap.extractMin();
        bheap.printeHeapInArray();
        bheap.print_heap();
        System.out.println();

        System.out.println("TASK 4 (BINOMIAL HEAP): ");
        System.out.println();
        BinomialHeap binomialHeap = new BinomialHeap();
        N=20;
        int[] arr3=new int[N];
        for (int i=0;i<N;i++)
        {
            d= Math.random()*101;
            arr3[i]=(int)d;
        }
        for (int i=0;i<N;i++)
        {
            System.out.print(arr3[i] + ", ");
            binomialHeap.insert(arr3[i]);
        }
        binomialHeap.displayHeap();
        binomialHeap.extractMin();
        binomialHeap.displayHeap();

        System.out.println("TASK 5: ");
        N=10;
        int[] arr4=new int[N];
        for (int i=0;i<N;i++)
        {
            d= Math.random()*101;
            arr4[i]=(int)d;
        }
        System.out.println("ARRAY:");
        System.out.println(Arrays.toString(arr4));
        Arrays.sort(arr4);
        System.out.println("SORTED ARRAY:");
        System.out.println(Arrays.toString(arr4));
        int k=4;
        int [] arr5 = Arrays.copyOf(arr4, k);
        System.out.println("K SMALLEST VALUES IN ARRAY:");
        System.out.println(Arrays.toString(arr5));
    }
}

