package com.antonov.algorithms.methods.chapter_2_intro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab_2_2_2_fibonacci {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);

        for (int i = 2; i <= n; i++) {
            Integer nextFib = (list.get(i - 1) % 10 + list.get(i - 2) % 10) % 10;
            list.add(nextFib);
        }

        System.out.println(list.get(n));
    }
}
