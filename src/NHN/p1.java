package NHN;

import java.util.Arrays;

public class p1 {

    public static int solution(int n, int m, int[][] preferences) {
        int maxSatisfied = 0;

        // 2^m개의 조합을 탐색
        for (int i = 0; i < Math.pow(2, m); i++) {
            for (int j = 0; j < Math.pow(2, m); j++) {
                // 현재 조합으로 몇 명이 만족하는지 카운트
                int satisfied = 0;

                boolean[] firstHalf = getCombination(i, m);
                boolean[] secondHalf = getCombination(j, m);

                for (int[] preference : preferences) {
                    if (isSatisfied(firstHalf, preference, m) || isSatisfied(secondHalf, preference, m)) {
                        satisfied++;
                    }
                }

                maxSatisfied = Math.max(maxSatisfied, satisfied);
            }
        }

        return maxSatisfied;
    }

    // 특정 조합 번호를 m개의 토핑에 대해 boolean 배열로 변환
    private static boolean[] getCombination(int combo, int m) {
        boolean[] combination = new boolean[m];
        for (int k = 0; k < m; k++) {
            combination[k] = (combo % 2 == 1);
            combo /= 2;
        }
        return combination;
    }

    // 피자 부분에 대해 현재 조합으로 만족하는지 체크
    private static boolean isSatisfied(boolean[] combo, int[] preference, int m) {
        for (int k = 0; k < m; k++) {
            if (preference[k] == 1 && !combo[k]) {
                return false;  // 선호하는 토핑이 없으면 false
            }
            if (preference[k] == -1 && combo[k]) {
                return false;  // 불호하는 토핑이 있으면 false
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n1 = 4, m1 = 4;
        int[][] preferences1 = {
                {1, 1, 0, -1},
                {0, 1, 1, -1},
                {1, 0, -1, -1},
                {1, 0, 0, 1}
        };
        System.out.println(solution(n1, m1, preferences1));  // 3

        int n2 = 5, m2 = 5;
        int[][] preferences2 = {
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1},
                {-1, -1, -1, -1, 0},
                {0, -1, -1, -1, -1}
        };
        System.out.println(solution(n2, m2, preferences2));  // 5

        int n3 = 4, m3 = 2;
        int[][] preferences3 = {
                {-1, -1},
                {-1, 1},
                {1, -1},
                {1, 1}
        };
        System.out.println(solution(n3, m3, preferences3));  // 2

        int n4 = 5, m4 = 4;
        int[][] preferences4 = {
                {0, 1, 1, 0},
                {1, 1, -1, -1},
                {-1, 0, 1, 1},
                {-1, -1, -1, 1},
                {1, 1, 0, -1}
        };
        System.out.println(solution(n4, m4, preferences4));  // 4
    }
}