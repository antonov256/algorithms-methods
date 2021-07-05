package com.antonov.algorithms.methods.chapter_8_dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Lab_8_2_2_nLogn_longest_decreasing_subsequence {
    public static void main(String[] args) throws IOException {
        new Lab_8_2_2_nLogn_longest_decreasing_subsequence().start();
    }

    private void start() throws IOException {
        InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("chapter_8/input_8_2_2_nlogn.txt");
        InputStreamReader isr = new InputStreamReader(resourceInputStream);
//        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.parseInt(br.readLine());
        String[] inputMas = br.readLine().split(" ");

        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < inputMas.length; i++) {
            elements.add(new Element(i, Integer.parseInt(inputMas[i])));
        }

        elements = elements.stream().map(e -> new Element(e.getPosition(), (-1) * e.getValue())).collect(Collectors.toList());
        List<Element> sequence = longestIncreasingSubsequenceBottomUpNLogN(elements);

        System.out.println(sequence.size());
//        sequence.forEach(e -> System.out.print((-1) * e.getValue() + " "));
//        System.out.println();
        sequence.forEach(e -> System.out.print(e.getPosition() + 1 + " "));
        System.out.print("");
    }

    private int bisectionRight(int[] F, int x) {
        int l = 0;
        int r = F.length;

        while (l + 1 < r) {
            int middle = (l + r) / 2;
            if (F[middle] > x) {
                r = middle;
            } else {
                l = middle;
            }
        }

        return r;
    }

    public List<Element> longestIncreasingSubsequenceBottomUpNLogN(List<Element> A) {
        int[] F = new int[A.size() + 1];
        F = Arrays.stream(F).map(operand -> Integer.MAX_VALUE).toArray();
        F[0] = Integer.MIN_VALUE;

        List<Column> columns = new ArrayList<>();
        columns.add(new Column());

        for (int i = 0; i < A.size(); i++) {
            Element e = A.get(i);

            int currentColumnNumber = bisectionRight(F, e.getValue());
            F[currentColumnNumber] = A.get(i).getValue();

            if (columns.size() < currentColumnNumber) {
                Column previousColumn = columns.get(columns.size() - 1);
                Column newColumn = new Column(previousColumn);
                columns.add(newColumn);
            }

            Column currentColumn = columns.get(currentColumnNumber - 1);
            Optional<Column> optionalPreviousColumn = currentColumn.getPreviousColumn();

            Card newCard;
            if (optionalPreviousColumn.isPresent()) {
                Column previousColumn = optionalPreviousColumn.get();
                if (previousColumn.notEmpty()) {
                    newCard = new Card(e, previousColumn.lastCard());
                } else {
                    throw new RuntimeException("Previous column can't be empty");
                }
            } else {
                newCard = new Card(e);
            }

            currentColumn.add(newCard);
        }

        List<Element> sequence = new ArrayList<>();
        Optional<Card> previous = Optional.empty();

        for (int colNum = columns.size() - 1; colNum >= 0; colNum--) {
            Column column = columns.get(colNum);
            Card card = previous.orElseGet(column::firstCard);

            Element e = card.getElement();
            sequence.add(e);

            previous = card.getPreviousCard();
        }

        Collections.reverse(sequence);
        return sequence;
    }

    class Element {
        private int position;
        private int value;

        public Element(int position, int value) {
            this.position = position;
            this.value = value;
        }

        @Override
        public String toString() {
            return position + ". " + value;
        }

        public int getPosition() {
            return position;
        }

        public int getValue() {
            return value;
        }
    }

    class Card {
        private Element element;
        private Optional<Card> previousCard;

        public Card(Element element) {
            this.element = element;
            this.previousCard = Optional.empty();
        }

        public Card(Element element, Card previousCard) {
            this.element = element;
            this.previousCard = Optional.of(previousCard);
        }

        public Element getElement() {
            return element;
        }

        public Optional<Card> getPreviousCard() {
            return previousCard;
        }

        @Override
        public String toString() {
            return element + " <- " + previousCard;
        }
    }

    class Column {
        private List<Card> cards;
        private Optional<Column> previousColumn;

        public Column() {
            cards = new ArrayList<>();
            previousColumn = Optional.empty();
        }

        public Column(Column previousColumn) {
            cards = new ArrayList<>();
            this.previousColumn = Optional.of(previousColumn);
        }

        public int size() {
            return cards.size();
        }

        public boolean isEmpty() {
            return cards.isEmpty();
        }

        public boolean notEmpty() {
            return !isEmpty();
        }

        public void add(Card card) {
            cards.add(card);
        }

        public Card firstCard() {
            return cards.get(0);
        }

        public Card lastCard() {
            return cards.get(cards.size() - 1);
        }

        public Optional<Column> getPreviousColumn() {
            return previousColumn;
        }
    }
}
