package com.antonov.algorithms.methods.chapter_4_greedy_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Lab_4_2_2_huffman_decoding {
    private Map<String, Character> dictionary = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new Lab_4_2_2_huffman_decoding().start();
    }

    public void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_4/input_4_2_2.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int l = Integer.parseInt(data[1]);

        for (int i = 0; i < n; i++) {
            data = br.readLine().split(": ");
            dictionary.put(data[1], data[0].charAt(0));
        }

        String encodedString = br.readLine();

        String tempStr = "";
        String decodedString = "";
        for (Character c : encodedString.toCharArray()) {
            tempStr += c;
            Character charFromDict = dictionary.get(tempStr);

            if (charFromDict != null) {
                decodedString += charFromDict;
                tempStr = "";
            }

        }

        System.out.println(decodedString);
    }
}
