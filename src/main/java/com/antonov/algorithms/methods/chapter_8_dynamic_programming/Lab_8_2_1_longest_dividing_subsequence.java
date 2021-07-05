package com.antonov.algorithms.methods.chapter_8_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lab_8_2_1_longest_dividing_subsequence {
    public static void main(String[] args) throws IOException {
        new Lab_8_2_1_longest_dividing_subsequence().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_8/input_8_2_1.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());
        String[] inputMas = br.readLine().split(" ");

        int[] data = new int[n];
        for (int i = 0; i < inputMas.length; i++) {
            data[i] = Integer.parseInt(inputMas[i]);
        }

        Arrays.stream(data).forEach(i -> System.out.print(i + " "));
        System.out.println();
        longestIncreasingDividingSubsequenceBottomUp(data);
    }

    public void longestIncreasingDividingSubsequenceBottomUp(int[] a) {
        int[] d = new int[a.length];

        for (int i = 0; i < d.length; i++) {
            d[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[j] <= a[i] && a[i] % a[j] == 0 && d[j] + 1 > d[i]) {
                    d[i] = d[j] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < d.length; i++) {
            ans = Math.max(ans, d[i]);
        }

        System.out.println(ans);
    }

    public void longestIncreasingSubsequenceBottomUp(int[] a) {
        int[] d = new int[a.length];

        for (int i = 0; i < d.length; i++) {
            d[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i] && d[j] + 1 > d[i]) {
                    d[i] = d[j] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < d.length; i++) {
            ans = Math.max(ans, d[i]);
        }

        System.out.println(ans);
    }
}
