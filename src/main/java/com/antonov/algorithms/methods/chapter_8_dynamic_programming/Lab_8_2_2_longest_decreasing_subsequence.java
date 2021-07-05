package com.antonov.algorithms.methods.chapter_8_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lab_8_2_2_longest_decreasing_subsequence {
    public static void main(String[] args) throws IOException {
        new Lab_8_2_2_longest_decreasing_subsequence().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_8/input_8_2_2.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());
        String[] inputMas = br.readLine().split(" ");

        int[] data = new int[n];
        for (int i = 0; i < inputMas.length; i++) {
            data[i] = Integer.parseInt(inputMas[i]);
        }

//        int[] positions = longestDecreasingSubsequenceBottomUp(data);
        int[] positions = longestDecreasingSubsequenceBottomUpWithoutPrevElementPositionsArray(data);
        System.out.println(positions.length);
        Arrays.stream(positions).forEach(p -> System.out.print(p + 1 + " "));
    }

    public int[] longestDecreasingSubsequenceBottomUp(int[] a) {
        int[] d = new int[a.length];
        int[] prevElemPos = new int[a.length];

        for (int i = 0; i < d.length; i++) {
            d[i] = 1;
            prevElemPos[i] = -1;

            for (int j = 0; j < i; j++) {
                if (a[j] >= a[i] && d[j] + 1 > d[i]) {
                    d[i] = d[j] + 1;
                    prevElemPos[i] = j;
                }
            }
        }

        int maxLen = 0;
        int maxSubSeqLastIndex = 0;
        for (int i = 0; i < d.length; i++) {
            if (d[i] > maxLen) {
                maxSubSeqLastIndex = i;
                maxLen = d[i];
            }
        }

        int[] positions = new int[maxLen];
        positions[positions.length - 1] = maxSubSeqLastIndex;

        for (int i = maxLen - 2; i >= 0; i--) {
            positions[i] = prevElemPos[positions[i + 1]];
        }

        return positions;
    }

    public int[] longestDecreasingSubsequenceBottomUpWithoutPrevElementPositionsArray(int[] a) {
        int[] d = new int[a.length];

        for (int i = 0; i < d.length; i++) {
            d[i] = 1;

            for (int j = 0; j < i; j++) {
                if (a[j] >= a[i] && d[j] + 1 > d[i]) {
                    d[i] = d[j] + 1;
                }
            }
        }

        int maxLen = 0;
        int maxSubSeqLastIndex = 0;
        for (int i = 0; i < d.length; i++) {
            if (d[i] > maxLen) {
                maxLen = d[i];
                maxSubSeqLastIndex = i;
            }
        }

        int[] positions = new int[maxLen];
        int[] subSequence = new int[maxLen];

        int currentIndex = maxSubSeqLastIndex;
        int currentValue = a[currentIndex];

        positions[maxLen - 1] = currentIndex;
        subSequence[maxLen - 1] = currentValue;

        for (int i = maxLen - 2; i >= 0; i--) {
            for (int j = currentIndex - 1; j >= 0; j--) {
                if (a[j] >= currentValue) {
                    currentIndex = j;
                    currentValue = a[j];
                    break;
                }
            }

            positions[i] = currentIndex;
            subSequence[i] = currentValue;
        }

        return positions;
    }
}
