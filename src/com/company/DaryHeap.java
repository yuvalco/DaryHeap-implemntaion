package com.company;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

class DaryHeap {

    private int size = 1000;

    private int heapSize;
    private int d;
    private int [] heap = new int [size];

    DaryHeap(int d) {
        this.d = d;
        getDataFromFile();

        buildHeap();
    }

    /**
     * fixes the heap from the index that given to the method till the end of the heap.
     * @param i the index to fix the heap from.
     * */
    private void maxHeapify(int i) {
        int largestIndex = i;
        int k = 1;
        for ( ; k <= d ; k++)
            if ( heap[getTheKthSon(i,k)] > heap[largestIndex] && getTheKthSon(i,k) < heapSize)
                largestIndex = getTheKthSon(i,k);

        if (largestIndex != i)
        {
            int temp =  heap[largestIndex];
            heap[largestIndex] = heap [i];
            heap[i] = temp;

            maxHeapify(largestIndex);
        }
    }

    /**
     * @param i the index of the node we would like to get his son.
     * @param k the k son of the node, starting from 1 till d.
     * @return the index of the k son of the node i
    * */
    private int getTheKthSon(int i, int k)
    {
        if (i == 0)
            return k;

        return ((i * d +2) - d + k) ;
    }

    /**
     * @param i the index of the node we would like to get his parent node.
     * @returns the index of the parent node
     * */
    private int getParent(int i)
    {
        return (i-1)/d + 1;
    }

    /**
     * builds the heap, each leaf is fulfilling the heap properties so there is no need.
    * */
    private void buildHeap()
    {
//       calculates leafs and than builds heap with values that are nodes only, and not leafs.
        double val = (heapSize * ((double)(d-1)/d));
        int i = heapSize-(int)val;

        for (; i >= 0; i--)
            maxHeapify(i);
//        calls Heapify on root one last time.
        maxHeapify(0);
    }


    /**
     * extract from the heap the maximum value and than arranges the heap so it will fulfil the heap properties.
     * @returns the extracted max value.
     * */
    public int extractMax()
    {
        if (heapSize < 1)
        {
            System.out.println("heap has no elements to extract");
            return 0;
        }

        int max = heap[0];
        heap[0] = heap[heapSize-1];
        heapSize--;
        maxHeapify(0);
        return max;
    }

    /**
     * inserts a new value to the heap and arranges the heap so that it will fulfil the heap proprieties.
     * @param value the value we would like to insert to the heap
    * */
    public void insert(int value)
    {
        heapSize++;
        heap[heapSize-1] = value;
        int index = heapSize-1;

        while ( index > 0 && heap[getParent(index)] < heap[index])
        {
            int temp = heap[index];
            heap[index] = heap[getParent(index)];
            heap[getParent(index)] = temp;

            index = getParent(index);
        }
    }

    /**
     * @param i the index of the key we would like to increase
     * @param value the value we would like to put instead of the current value.
     * */
    public void increaseKey(int i, int value)
    {
        if(i > heapSize)
        {
            System.out.println("Entered index is bigger than heap");
            return;
        }
        if ( heap[i] > value)
        {
            System.out.println("Entered key is smaller than current one");
            return;
        }

        heap[i] = value;
        while ( i > 0 && heap[i] > heap[getParent(i)])
        {
            int temp = heap[getParent(i)];
            heap[getParent(i)] = heap[i];
            heap[i] = temp;

            i = getParent(i);
        }
    }


    /**
     * prints the heap to screen.
     * each new line is the height level beginning with 0.
     * */
    public void print() {
        int power = 0;
        int counter = 0;

        for (int i = 0; i < heapSize; i++) {

            System.out.print("Node("+heap[i] +") sons [");
            for (int j = 1 ; j <=d ; j++)
            {
                if (getTheKthSon(i,j) >= heapSize)
                    System.out.print("N/A");
                else
                    System.out.print( heap[getTheKthSon(i,j)]);

                if (j != d)
                    System.out.print(" / ");

//                only for debug
                /*if (heap[i] < heap[getTheKthSon(i,j)])
                    System.out.println("~~~~~~~~~~~~~~~~~~~~error~~~~~~~~~~~~~~~~~~~~~~~~");*/
            }
            System.out.print("].");

            if (Math.pow(d, power) == counter || i == 0 ) {
                System.out.println();
                power++;
                counter = 0;
            }
            counter++;
        }

        System.out.println();
    }

    /**
     * gets the data from file, the file is in a certain format , where each number is separated by ','.
     * and after the last number there must be a ','
     * */
    private void getDataFromFile() {

        try {
//            reads file from current working directory.
            FileReader fileReader = new FileReader(System.getProperty("user.dir") + "\\drray.txt");

            int character ,i = 0 ;
            StringBuilder stringBuilder = new StringBuilder();
            while ((character = fileReader.read()) != -1 && i < size) {
                if ((char) character != ',')
                    stringBuilder.append((char) character);
                else
                {
                    heap[i] = Integer.parseInt(stringBuilder.toString());
                    i++;
                    stringBuilder = new StringBuilder();
                }
            }
            heapSize = i;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
