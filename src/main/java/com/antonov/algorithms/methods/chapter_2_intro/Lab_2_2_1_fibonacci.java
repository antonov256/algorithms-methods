package com.antonov.algorithms.methods.chapter_2_intro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab_2_2_1_fibonacci {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        List<Long> list = new ArrayList<>();
        list.add(0L);
        list.add(1L);

        for (int i = 2; i <= n; i++) {
            Long nextFib = list.get(i - 1) + list.get(i - 2);
            list.add(nextFib);
        }

        System.out.println(list.get(n));
    }
}
