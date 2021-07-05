package com.antonov.algorithms.methods.chapter_4_greedy_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Lab_4_2_1_huffman_encoding {
    private Map<Character, String> dictionary = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new Lab_4_2_1_huffman_encoding().start();
    }

    public void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_4/input_4_2_1.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        char[] chars = br.readLine().toCharArray();

        Map<Character, Integer> map = new LinkedHashMap<>();
        List<Vertex> vertexList = new ArrayList<>();

        for (Character c : chars) {
            if (!map.containsKey(c)) {
                map.put(c, calculateCount(c, chars));
            } else {
                map.put(c, map.get(c) + 1);
            }
        }

        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            vertexList.add(new Vertex(e.getValue(), e.getKey()));
        }

        Collections.sort(vertexList);
        process(vertexList);

        if (vertexList.size() == 1 && vertexList.get(0).getLetter() != null) {
            vertexList.get(0).setCode("0");
            dictionary.put(vertexList.get(0).getLetter(), vertexList.get(0).getCode());
        } else {
            setCodes(vertexList.get(0));
        }

        String encodedResult = encodeChars(chars);

        System.out.println(dictionary.size() + " " + encodedResult.length());
        for (Map.Entry<Character, String> e : dictionary.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }

        System.out.println(encodedResult);
    }

    private String encodeChars(char[] chars) {
        String encodedResult = "";
        for (Character c : chars) {
            String foundedCode = dictionary.get(c);
            if (foundedCode != null)
                encodedResult += foundedCode;
        }

        return encodedResult;
    }

    private void setCodes(Vertex vertex) {
        if (vertex.getLetter() != null) {
            dictionary.put(vertex.getLetter(), vertex.getCode());
        }

        if (vertex.getVertices().size() == 0)
            return;

        Vertex a = vertex.getVertices().get(0);
        Vertex b = vertex.getVertices().get(1);

        a.setCode(vertex.getCode() + "0");
        b.setCode(vertex.getCode() + "1");


        setCodes(a);
        setCodes(b);
    }

    private void process(List<Vertex> vertexList) {
        if (vertexList.size() == 1)
            return;

        Vertex a = extractMin(vertexList);
        Vertex b = extractMin(vertexList);

        int sum = a.getCount() + b.getCount();
        Vertex newVertex = new Vertex(sum, null);
        newVertex.getVertices().add(a);
        newVertex.getVertices().add(b);

        vertexList.add(newVertex);

        process(vertexList);
    }


    private int calculateCount(Character character, char[] chars) {
        int count = 0;
        for (Character c : chars) {
            if (c.equals(character))
                count++;
        }

        return count;
    }

    private Vertex extractMin(List<Vertex> vertexList) {
        int min = Integer.MAX_VALUE;
        Vertex minVertex = null;

        for (Vertex v : vertexList) {
            if (v.getCount() < min) {
                min = v.getCount();
                minVertex = v;
            }
        }

        if (minVertex != null) {
            vertexList.remove(minVertex);
        }

        return minVertex;
    }

    class Vertex implements Comparable<Vertex> {
        private Integer count;
        private Character letter;
        private String code = "";

        private List<Vertex> vertices = new ArrayList<>();

        public Vertex(Integer count, Character letter) {
            this.count = count;
            this.letter = letter;
        }

        public Integer getCount() {
            return count;
        }

        public Character getLetter() {
            return letter;
        }

        public List<Vertex> getVertices() {
            return vertices;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return letter + ": " + count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return count.equals(vertex.count) &&
                    letter.equals(vertex.letter) &&
                    vertices.equals(vertex.vertices);
        }

        @Override
        public int hashCode() {
            return Objects.hash(count, letter, vertices);
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(count, o.getCount());
        }
    }
}
