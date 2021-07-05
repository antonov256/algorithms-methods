package com.antonov.algorithms.methods;

import java.util.Arrays;

public class Utils {
    public int[] generateRandomIntArray(int size, int scale) {
        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            int x = (int) (scale * Math.random());
            data[i] = x;
        }

        return data;
    }

    public void printArray(int[] array) {
        Arrays.stream(array).forEach(i -> System.out.print(i + " "));
    }

    public void printArray(String[] array) {
        Arrays.stream(array).forEach(s -> System.out.print(s + " "));
    }
}
