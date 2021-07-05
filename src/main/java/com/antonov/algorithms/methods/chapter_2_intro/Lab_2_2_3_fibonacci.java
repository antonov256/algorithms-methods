package com.antonov.algorithms.methods.chapter_2_intro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab_2_2_3_fibonacci {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        long m = s.nextLong();

        long first = 0;
        long second = 1;

        List<Long> modulos = new ArrayList<>();
        modulos.add(0L);
        modulos.add(1L);

        long pisanoPeriod = 0;
        long nextFib;
        for (long i = 2L; i <= n; i++) {
            nextFib = first % m + second % m;
            modulos.add(nextFib % m);

            if (modulos.get(modulos.size() - 2) == 0L && modulos.get(modulos.size() - 1) == 1L) {
                pisanoPeriod = i - 2;
//                System.out.println("pisanoPeriod = " + pisanoPeriod);

                modulos.remove(modulos.size() - 2);
                modulos.remove(modulos.size() - 1);
                break;
            }

            first = second;
            second = nextFib;
        }

        System.out.println(modulos.get((int) (n % modulos.size())));
        System.out.println();
    }
}
