package com.antonov.algorithms.methods.chapter_8_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lab_8_7_1_ladder {
    public static void main(String[] args) throws IOException {
        new Lab_8_7_1_ladder().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_8/input_8_7_1.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());
        String[] a = br.readLine().split(" ");
        List<Integer> steps = Arrays.stream(a).map(Integer::parseInt).collect(Collectors.toList());

//        int maxPackedWeight1 = goUpTheLadderRecursiveTopDown(steps, steps.size());
//        System.out.println(maxPackedWeight1);
//        int maxPackedWeight2 = goUpTheLadderIterativeBottomUp(steps);
//        System.out.println(maxPackedWeight2);
        int maxPackedWeight3 = goUpTheLadderIterativeBottomUpWithEffectiveMemoization(steps);
        System.out.println(maxPackedWeight3);
    }

    private int goUpTheLadderRecursiveTopDown(List<Integer> steps, int position) {
        if(position == 0)
            return 0;

        if(position == 1)
            return steps.get(0);

        int stepValue = steps.get(position - 1);
        int stepFromPrevValue = goUpTheLadderRecursiveTopDown(steps, position - 1) + stepValue;
        int stepFromPrevPrevValue = goUpTheLadderRecursiveTopDown(steps, position - 2) + stepValue;

        int result = Math.max(stepFromPrevValue, stepFromPrevPrevValue);
        return result;
    }

    private int goUpTheLadderIterativeBottomUp(List<Integer> steps) {
        int n = steps.size();
        int [] D = new int[n + 1];

        D[0] = 0;
        for(int i = 1; i < D.length; i++) {
            if(i == 1) {
                D[i] = steps.get(0);
            } else {
                int stepValue = steps.get(i - 1);
                int stepFromPrevValue = D[i - 1] + stepValue;
                int stepFromPrevPrevValue = D[i - 2] + stepValue;

                D[i] = Math.max(stepFromPrevValue, stepFromPrevPrevValue);
            }
        }

        int result = D[steps.size()];
        return result;
    }

    private int goUpTheLadderIterativeBottomUpWithEffectiveMemoization(List<Integer> steps) {
        int n = steps.size();

        int prev = 0;
        int prevPrev = 0;
        for(int i = 1; i < n + 1; i++) {
            if(i == 1) {
                prev = steps.get(0);
            } else {
                int stepValue = steps.get(i - 1);
                int stepFromPrevValue = prev + stepValue;
                int stepFromPrevPrevValue = prevPrev + stepValue;

                prevPrev = prev;
                prev = Math.max(stepFromPrevValue, stepFromPrevPrevValue);
            }
        }

        int result = prev;
        return result;
    }
}
