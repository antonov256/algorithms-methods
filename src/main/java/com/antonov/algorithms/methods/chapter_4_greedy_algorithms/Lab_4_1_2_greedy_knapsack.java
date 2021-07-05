package com.antonov.algorithms.methods.chapter_4_greedy_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab_4_1_2_greedy_knapsack {
    private Double value;
    private Double totalCost = 0d;

    public static void main(String[] args) throws IOException {
        new Lab_4_1_2_greedy_knapsack().start();
    }

    public void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_4/input_4_1_2.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String[] data = br.readLine().split(" ");

        int n = Integer.parseInt(data[0]);
        value = Double.parseDouble(data[1]);

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            data = br.readLine().split(" ");

            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);

            items.add(new Item(a, b));
        }

        Collections.sort(items);
        Collections.reverse(items);
        process(items);

        System.out.println(totalCost);
    }

    void process(List<Item> items) {
        if (items.size() == 0)
            return;

        if (value.equals(0d))
            return;

        Item item = items.get(0);

        if (value - item.getValue() >= 0) {
            totalCost += item.getCost();
            value -= item.getValue();

        } else {
            double part = value / item.getValue();
            double partialCost = part * item.getCost();

            value -= part * item.getValue();
            totalCost += partialCost;
        }

        items.remove(item);
        process(items);
    }

    class Item implements Comparable<Item> {
        private int cost;
        private int value;

        public Item(int cost, int value) {
            this.cost = cost;
            this.value = value;
        }

        public int getCost() {
            return cost;
        }

        public int getValue() {
            return value;
        }

        public double getCostPerValue() {
            return 1.0 * cost / value;
        }

        @Override
        public int compareTo(Item o) {
            return Double.compare(getCostPerValue(), o.getCostPerValue());
        }

        @Override
        public String toString() {
            return value + "v, " + cost + "$ (" + getCostPerValue() + " $/v)";
        }
    }
}
