package com.company;

public class BinaryHeap {
    public int[] heapArray;
    public int capacity;
    public int current_heap_size;

    public BinaryHeap(int n)
    {
        capacity = n;
        heapArray = new int[capacity];
        current_heap_size = 0;
    }
    public int Parent(int key)
    {
        return (key - 1) / 2;
    }
    public int Left(int key)
    {
        return 2 * key + 1;
    }
    public int Right(int key)
    {
        return 2 * key + 2;
    }

    public boolean insertKey(int key)
    {
        if (current_heap_size == capacity)
        {
            return false;
        }

        int i = current_heap_size;
        heapArray[i] = key;
        current_heap_size++;
        while (i != 0 && heapArray[i] <
                heapArray[Parent(i)])
        {
            int temp = heapArray[i];
            heapArray[i] = heapArray[Parent(i)];
            heapArray[Parent(i)] = temp;
            i = Parent(i);
        }
        return true;
    }

    public int extractMin()
    {
        if (current_heap_size == 1)
        {
            current_heap_size--;
            return heapArray[0];
        }
        int root = heapArray[0];
        heapArray[0] = heapArray[current_heap_size - 1];
        current_heap_size--;
        MinHeapify(0);
        return root;
    }

    public void MinHeapify(int key)
    {
        int l = Left(key);
        int r = Right(key);
        int smallest = key;
        if (l < current_heap_size &&
                heapArray[l] < heapArray[smallest])
        {
            smallest = l;
        }
        if (r < current_heap_size &&
                heapArray[r] < heapArray[smallest])
        {
            smallest = r;
        }
        if (smallest != key)
        {
            int temp = heapArray[key];
            heapArray[key] = heapArray[smallest];
            heapArray[smallest] = temp;
            MinHeapify(smallest);
        }
    }
    public void print_heap() {
        System.out.println("BINARY HEAP AS TREE");
        int k=0;
        for(int i=0;i<current_heap_size;i++){
            for(int j=0;j<Math.pow(2,i)&&j+Math.pow(2,i)<=current_heap_size;j++){
                System.out.print(heapArray[j+(int)Math.pow(2,i)-1]+" ");
                 k++;
            }

            if (k<current_heap_size)
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    public void printeHeapInArray ()
    {
        System.out.println("BINARY HEAP IN ARRAY");
        for (int i = 0; i < current_heap_size; ++i)
            System.out.print(heapArray[i] + " ");
        System.out.println();
        System.out.println();
    }
}
