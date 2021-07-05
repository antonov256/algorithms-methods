package com.antonov.algorithms.methods.chapter_8_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Lab_8_7_2_calculator {
    public static void main(String[] args) throws IOException {
        new Lab_8_7_2_calculator().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_8/input_8_7_2.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());
        List<Integer> numbers = numberMorphFromOneIterativeBottomUp(n);
        System.out.println(numbers.size() - 1);
        System.out.println(numbers.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }

    private List<Integer> numberMorphFromOneIterativeBottomUp(int n) {
        List<List<Integer>> numbersData = new ArrayList<>();

        List<Integer> listZero = new ArrayList<>();
        numbersData.add(listZero);

        List<Integer> listOne = new ArrayList<>();
        listOne.add(1);
        numbersData.add(listOne);

        List<Integer> listTwo = new ArrayList<>();
        listTwo.add(1);
        listTwo.add(2);
        numbersData.add(listTwo);

        List<Integer> listThree = new ArrayList<>();
        listThree.add(1);
        listThree.add(3);
        numbersData.add(listThree);

        if(n <= 3) {
            return numbersData.get(n);
        }

        for(int i = 4; i <= n; i++) {
            List<Integer> forPlusOne = numbersData.get(i - 1);

            Optional<List<Integer>> forX2;
            if(i % 2 == 0) {
                forX2 = Optional.of(numbersData.get(i / 2));
            } else {
                forX2 = Optional.empty();
            }

            Optional<List<Integer>> forX3;
            if(i % 3 == 0) {
                forX3 = Optional.of(numbersData.get(i / 3));
            } else {
                forX3 = Optional.empty();
            }

            List<List<Integer>> variants = new ArrayList<>();
            variants.add(forPlusOne);
            forX2.ifPresent(variants::add);
            forX3.ifPresent(variants::add);

            Optional<List<Integer>> min = variants.stream().min(Comparator.comparingInt(List::size));
            if(min.isPresent()) {
                List<Integer> values = new ArrayList<>(min.get());
                values.add(i);
                numbersData.add(values);
            } else {
                throw new RuntimeException("Min list should exists!");
            }
        }

        List<Integer> numbers = numbersData.get(numbersData.size() - 1);
        return numbers;
    }

    private List<Integer> numberMorphFromOneRecursiveTopDown(int n) {
        if(n == 1) {
            return new ArrayList<Integer>(){{
                add(1);
            }};
        }

        if(n == 2) {
            return new ArrayList<Integer>(){{
                add(1);
                add(2);
            }};
        }

        if(n == 3) {
            return new ArrayList<Integer>(){{
                add(1);
                add(3);
            }};
        }

        List<Integer> forPlusOne = numberMorphFromOneRecursiveTopDown(n - 1);

        Optional<List<Integer>> forX2;
        if(n % 2 == 0) {
            forX2 = Optional.of(numberMorphFromOneRecursiveTopDown(n / 2));
        } else {
            forX2 = Optional.empty();
        }

        Optional<List<Integer>> forX3;
        if(n % 3 == 0) {
            forX3 = Optional.of(numberMorphFromOneRecursiveTopDown(n / 3));
        } else {
            forX3 = Optional.empty();
        }

        List<List<Integer>> variants = new ArrayList<>();
        variants.add(forPlusOne);
        forX2.ifPresent(variants::add);
        forX3.ifPresent(variants::add);

        Optional<List<Integer>> min = variants.stream().min(Comparator.comparingInt(List::size));
        if(min.isPresent()) {
            List<Integer> values = min.get();
            values.add(n);
            return values;
        }

        return new ArrayList<>();
    }
}
