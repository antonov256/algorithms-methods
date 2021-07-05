package com.antonov.algorithms.methods.chapter_4_greedy_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lab_4_1_1 {
    private List<Integer> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Lab_4_1_1().start();
    }

    public void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_4/input_4_1_1.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());

        List<Segment> segments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] data = br.readLine().split(" ");

            int a = Integer.parseInt(data[0]);
            int b = Integer.parseInt(data[1]);

            segments.add(new Segment(a, b));
        }

        Collections.sort(segments);
        process(segments);

        System.out.println(points.size());
        System.out.println(points.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }

    void process(List<Segment> segments) {
        if (segments.size() == 0)
            return;

        Segment segment = segments.get(0);
        int endCoordinate = segment.getEnd();
        List<Segment> coveredSegments = segments.stream()
                .filter(seg -> seg.getStart() <= endCoordinate && seg.getEnd() >= endCoordinate)
                .collect(Collectors.toList());

        segments.removeAll(coveredSegments);
        points.add(endCoordinate);

        process(segments);
    }

    class Segment implements Comparable<Segment> {
        private int start;
        private int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(end, o.getEnd());
        }

        @Override
        public String toString() {
            return "[" + start + "; " + end + "]";
        }
    }
}
