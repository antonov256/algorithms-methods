package com.antonov.algorithms.methods.chapter_8_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lab_8_4_knapsack {
    public static void main(String[] args) throws IOException {
        new Lab_8_4_knapsack().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_8/input_8_4.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String[] data = br.readLine().split(" ");
        int W = Integer.parseInt(data[0]);
        int n = Integer.parseInt(data[1]);

        data = br.readLine().split(" ");
        List<Integer> weights = Arrays.stream(data).map(Integer::parseInt).collect(Collectors.toList());

        int maxPackedWeight = packKnapsack(W, weights);
        System.out.println(maxPackedWeight);
    }

    private int packKnapsack(int W, List<Integer> weights) {
        int n = weights.size();

        int[][] D = new int[n + 1][W + 1];
        for (int w = 0; w < W; w++) {
            D[0][w] = 0;
        }
        for (int i = 0; i < n; i++) {
            D[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            Integer weight = weights.get(i - 1);

            for (int w = 1; w <= W; w++) {
                D[i][w] = D[i - 1][w];

                if (weight <= w) {
                    int wIfNotAdd = D[i - 1][w];
                    int wIfAdd = D[i - 1][w - weight] + weight;
                    int newWeight = Math.max(wIfNotAdd, wIfAdd);
                    D[i][w] = newWeight;
                }
            }
        }

        int maxWeight = D[n][W];
        return maxWeight;
    }
}
