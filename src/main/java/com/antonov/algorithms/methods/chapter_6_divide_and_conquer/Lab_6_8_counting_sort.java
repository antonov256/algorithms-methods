package com.antonov.algorithms.methods.chapter_6_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lab_6_8_counting_sort {
    public static void main(String[] args) throws IOException {
        new Lab_6_8_counting_sort().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_6/input_6_8.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());
        String[] inputMas = br.readLine().split(" ");
        int count = 10;

        int[] data = new int[n];
        for (int i = 0; i < inputMas.length; i++) {
            data[i] = Integer.parseInt(inputMas[i]);
        }

        int[] sorted = countingSort(data, count);

        String stringResult = Arrays.stream(sorted).mapToObj(Objects::toString).collect(Collectors.joining(" "));
        System.out.println(stringResult);
    }

    public int[] countingSort(int[] data, int count) {
        int[] stats = new int[count];
        int[] sorted = new int[data.length];

        for (int i = 0; i < data.length; i++) {
            stats[data[i] - 1] += 1;
        }

        for (int i = 1; i < stats.length; i++) {
            stats[i] += stats[i - 1];
        }

        for (int i = data.length - 1; i >= 0; i--) {
            sorted[stats[data[i] - 1] - 1] = data[i];
            stats[data[i] - 1]--;
        }

        return sorted;
    }
}
