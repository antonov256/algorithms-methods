package com.antonov.algorithms.methods.chapter_4_greedy_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lab_4_1_3 {

    public static void main(String[] args) throws IOException {
        new Lab_4_1_3().start();
    }

    public void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_4/input_4_1_3.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        Integer n = Integer.parseInt(br.readLine());

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; true; i++) {
            if (n - i < i + 1) {
                numbers.add(n);
                break;
            }

            if (!numbers.contains(i)) {
                n -= i;
                numbers.add(i);
            }
        }

        System.out.println(numbers.size());
        System.out.println(numbers.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
