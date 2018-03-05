package ru.ladence.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MathProblems {

    /**
     * Problem :
     * To find all solutions of a^3 + b^3 = c^3 + d^3 in range of [0, N]
     * @param N right limit of range
     */
    public static void findCubicSum(int N) {
        HashMap<Long, List<Pair>> storage = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Long sum = (long)Math.pow(i, 3) + (long)Math.pow(j, 3);
                if (storage.containsKey(sum)) {
                    List<Pair> list = storage.get(sum);
                    for (Pair pair : list) {
                        System.out.println(i + " " + j + " " + pair.a + " " + pair.b);
                    }
                } else {
                    List<Pair> list = new ArrayList<>();
                    storage.put(sum, list);
                }

                storage.get(sum).add(new Pair(i, j));
            }
        }
    }

    private static class Pair {
        int a;
        int b;

        private Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
