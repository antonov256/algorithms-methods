package com.antonov.algorithms.methods.chapter_6_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lab_6_4_merge_sort_and_inversion_count {
    private long inversions = 0;

    public static void main(String[] args) throws IOException {
        new Lab_6_4_merge_sort_and_inversion_count().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_6/input_6_4.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());
        int[] data = scanArray(n, br.readLine());
        int[] sorted = mergeSort(data, 0, data.length - 1);

        System.out.println(inversions);
    }

    private int[] mergeSort(int[] data, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            return merge(mergeSort(data, l, m), mergeSort(data, m + 1, r));
        } else {
            int[] singleElement = new int[1];
            singleElement[0] = data[l];

            return singleElement;
        }
    }

    private int[] merge(int[] d1, int[] d2) {
        int[] result = new int[d1.length + d2.length];

        int p1 = 0;
        int p2 = 0;

        for (int i = 0; i < d1.length + d2.length; i++) {
            int element1;
            if (p1 < d1.length)
                element1 = d1[p1];
            else
                element1 = Integer.MAX_VALUE;

            int element2;
            if (p2 < d2.length)
                element2 = d2[p2];
            else
                element2 = Integer.MAX_VALUE;

            if (element1 <= element2) {
                result[i] = element1;
                p1++;
            } else {
                result[i] = element2;
                inversions += d1.length - p1;
                p2++;
            }
        }

        return result;
    }

    private int[] scanArray(int n, String str) {
        String[] inputMas = str.split(" ");

        int[] intMas = new int[n];
        for (int i = 0; i < inputMas.length; i++) {
            intMas[i] = Integer.parseInt(inputMas[i]);
        }

        return intMas;
    }
}
