package com.antonov.algorithms.methods.chapter_6_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lab_6_1_binary_search {
    public static void main(String[] args) throws IOException {
        new Lab_6_1_binary_search().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_6/input_6_1.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int[] data = parseArray(br.readLine());
        int[] search = parseArray(br.readLine());

        for (int i = 0; i < search.length; i++) {
            int position = binarySearch(data, search[i]);
            if (position != -1)
                position++;

            System.out.print(position);

            if (i != search.length - 1)
                System.out.print(" ");
        }
    }

    private int binarySearch(int[] data, int element) {
        int l = 0;
        int r = data.length - 1;

        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (data[m] == element) {
                return m;
            } else {
                if (data[m] > element) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }

        return -1;
    }

    private int linearSearch(int[] data, int element) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == element) {
                return i;
            }
        }
        return -1;
    }

    private int[] parseArray(String str) {
        String[] inputMas = str.split(" ");
        int n = Integer.parseInt(inputMas[0]);

        int[] intMas = new int[n];
        for (int i = 1; i < inputMas.length; i++) {
            intMas[i - 1] = Integer.parseInt(inputMas[i]);
        }

        return intMas;
    }

    private int[] scanArrayFromFile(String str) {
        String[] inputMas = str.split(" ");
        int n = Integer.parseInt(inputMas[0]);

        int[] intMas = new int[n];
        for (int i = 1; i < inputMas.length; i++) {
            intMas[i - 1] = Integer.parseInt(inputMas[i]);
        }

        return intMas;
    }
}
