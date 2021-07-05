package com.antonov.algorithms.methods.chapter_8_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lab_8_3_editing_distance {
    public static void main(String[] args) throws IOException {
        new Lab_8_3_editing_distance().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_8/input_8_3.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String l1 = br.readLine();
        String l2 = br.readLine();

        int distance = editDistBottomUp(l1, l2);
        System.out.println(distance);
    }

    private int editDistBottomUp(String l1, String l2) {
        int n = l1.length() + 1;
        int m = l2.length() + 1;

        int[][] D = new int[n][m];

        for (int i = 0; i < n; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m; j++) {
            D[0][j] = j;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int ins = D[i][j - 1] + 1;
                int del = D[i - 1][j] + 1;

                int diff = l1.charAt(i - 1) == l2.charAt(j - 1) ? 0 : 1;
                int sub = D[i - 1][j - 1] + diff;

                int min = Math.min(sub, Math.min(ins, del));
                D[i][j] = min;
            }
        }

        int distance = D[n - 1][m - 1];
        return distance;
    }
}
