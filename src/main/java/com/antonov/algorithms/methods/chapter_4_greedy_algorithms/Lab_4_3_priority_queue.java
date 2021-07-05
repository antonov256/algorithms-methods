package com.antonov.algorithms.methods.chapter_4_greedy_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lab_4_3_priority_queue {
    public static void main(String[] args) throws IOException {
        new Lab_4_3_priority_queue().start();
    }

    public void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_4/input_4_3.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());
        MaxHeap heap = new MaxHeap(n);

        String[] data;
        for (int i = 0; i < n; i++) {
            data = br.readLine().split(" ");
            if (data[0].equals("ExtractMax")) {
                System.out.println(heap.extractMax());
            } else {
                if (data[0].equals("Insert")) {
                    int value = Integer.parseInt(data[1]);
                    heap.insert(value);
                } else {
                    System.out.println("Wrong input!");
                }
            }
        }
    }

    class MaxHeap {
        private Integer[] data;
        private int elementsCount = 0;

        public MaxHeap(int size) {
            data = new Integer[size];
        }

        public void insert(int value) {
            data[elementsCount] = value;
            elementsCount++;
            siftUp(elementsCount - 1);
        }

        public int extractMax() {
            Integer max = data[0];
            data[0] = null;
            switchItems(0, elementsCount - 1);
            elementsCount--;
            siftDown(0);

            return max;
        }

        private void switchItems(int i1, int i2) {
            Integer t = data[i1];
            data[i1] = data[i2];
            data[i2] = t;
        }

        private void siftUp(int index) {
            int parentIndex = getParentIndexOf(index);

            while (index != 0 && data[parentIndex] < data[index]) {
                switchItems(parentIndex, index);
                index = parentIndex;
                parentIndex = getParentIndexOf(index);
            }
        }

        private void siftDown(int index) {
            int leftChildIndex = leftChildIndexOf(index);
            int rightChildIndex = rightChildIndexOf(index);

            while (leftChildIndex < elementsCount) {
                if (rightChildIndex >= elementsCount) {
                    rightChildIndex = leftChildIndex;
                }

                int maxOfChildrenIndex = data[leftChildIndex] > data[rightChildIndex] ? leftChildIndex : rightChildIndex;

                if (data[maxOfChildrenIndex] > data[index]) {
                    switchItems(maxOfChildrenIndex, index);
                    index = maxOfChildrenIndex;

                    leftChildIndex = leftChildIndexOf(index);
                    rightChildIndex = rightChildIndexOf(index);
                } else {
                    return;
                }
            }
        }

        private int leftChildIndexOf(int index) {
            return 2 * index + 1;
        }

        private int rightChildIndexOf(int index) {
            return 2 * index + 2;
        }

        private int getParentIndexOf(int index) {
            return (index - 1) / 2;
        }

        public int size() {
            return elementsCount;
        }

        @Override
        public String toString() {
            return "MaxHeap{" +
                    "data=" + Arrays.toString(data) +
                    ", elementsCount=" + elementsCount +
                    '}';
        }
    }
}
