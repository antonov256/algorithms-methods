package com.antonov.algorithms.methods.chapter_6_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Lab_6_5_quick_sort {
    Random random = new Random();

    public static void main(String[] args) throws IOException {
        new Lab_6_5_quick_sort().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_6/input_6_5.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String[] strMas = br.readLine().split(" ");

        int n = Integer.parseInt(strMas[0]);
        int m = Integer.parseInt(strMas[1]);

        int[][] segments = new int[n][2];
        int[] segmentsStarts = new int[n];
        int[] segmentsEnds = new int[n];
        for (int i = 0; i < n; i++) {
            strMas = br.readLine().split(" ");

            segments[i][0] = Integer.parseInt(strMas[0]);
            segments[i][1] = Integer.parseInt(strMas[1]);

            segmentsStarts[i] = segments[i][0];
            segmentsEnds[i] = segments[i][1];
        }


        int[] dots = new int[m];
        strMas = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            dots[i] = Integer.parseInt(strMas[i]);
        }


        segmentsStarts = quickSort(segmentsStarts, 0, segmentsStarts.length - 1);
        segmentsEnds = quickSort(segmentsEnds, 0, segmentsEnds.length - 1);


        int[] dotsInSegmentsStatistic = new int[m];
        for (int i = 0; i < dots.length; i++) {
            int endIsLower = countEndIsLower(segmentsEnds, dots[i]);
            int startIsGreater = countStartIsGreater(segmentsStarts, dots[i]);

            dotsInSegmentsStatistic[i] = segments.length - (startIsGreater + endIsLower);
        }

        System.out.println(
                Arrays.stream(dotsInSegmentsStatistic)
                        .mapToObj(Objects::toString)
                        .collect(Collectors.joining(" "))
        );
    }

    int countEndIsLower(int[] ends, int value) {
        int count = 0;

        for (int e = 0; e < ends.length; e++) {
            if (ends[e] < value)
                count++;
            else
                break;
        }

        return count;
    }

    int countStartIsGreater(int[] starts, int value) {
        int count = 0;

        for (int s = starts.length - 1; s >= 0; s--) {
            if (starts[s] > value)
                count++;
            else
                break;
        }

        return count;
    }

    int[] quickSort(int[] data, int l, int r) {
        if (l >= r) {
            return data;
        }

//        int randomIndex = l + random.nextInt(r - l);
//        switchElements(data, l, randomIndex);

//        int medianIndex = findMedianIndex(data, l, l + (r-1 - l) / 2, r-1);
//        switchElements(data, l, medianIndex);

        int m = partition(data, l, r);
        quickSort(data, l, m - 1);
        quickSort(data, m + 1, r);

        return data;
    }

    int partition(int[] data, int l, int r) {
        int x = data[l];
        int j = l;

        for (int i = l + 1; i <= r; i++) {
            if (data[i] <= x) {
                j++;
                switchElements(data, j, i);
            }
        }

        switchElements(data, l, j);

        return j;
    }

    int partition3(int[] data, int l, int r) {
        int x = data[l];
        int j = l;
        int e = l;

        for (int i = l + 1; i <= r; i++) {
            if (data[i] < x) {
                switchElements(data, j + 1, i);
                j++;
            }

            if (data[i] > x) {
                continue;
            }

            if (data[i] == x) {
                switchElements(data, e + 1, i);
                e++;
            }
        }

        switchElements(data, l, j);

        return j;
    }

    void switchElements(int[] data, int i1, int i2) {
        int t = data[i1];
        data[i1] = data[i2];
        data[i2] = t;
    }

    private int findMedianIndex(int[] data, int l, int i, int r) {
        if (data[l] < data[i] && data[i] < data[r])
            return i;
        if (data[l] < data[r] && data[r] < data[i])
            return r;

        if (data[i] < data[r] && data[r] < data[l])
            return r;
        if (data[i] < data[l] && data[l] < data[r])
            return l;

        if (data[r] < data[i] && data[i] < data[l])
            return i;
        if (data[r] < data[l] && data[l] < data[i])
            return l;

        if (data[l] == data[i])
            return i;

        if (data[i] == data[r])
            return i;

        if (data[l] == data[r])
            return l;

        return i;
    }
}
