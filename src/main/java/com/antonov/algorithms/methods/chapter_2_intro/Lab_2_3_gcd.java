package com.antonov.algorithms.methods.chapter_2_intro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lab_2_3_gcd {
    public static void main(String[] args) throws IOException {
        new Lab_2_3_gcd().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_2/input_2_3.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String input = br.readLine();
        String[] data = input.split(" ");

        int a = Integer.parseInt(data[0]);
        int b = Integer.parseInt(data[1]);

        do {
            if (a > b)
                a = a % b;
            else
                b = b % a;
        } while (a != 0 && b != 0);


        System.out.println(a + b);
    }
}
