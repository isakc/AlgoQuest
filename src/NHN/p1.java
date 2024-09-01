package NHN;

public class p1 {
    public static void main(String[] args) {
        int[][] preferences = {{1, 1, 1, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 1, 1, 1}, {-1, -1, -1, -1, 0}, {0, -1, -1, -1, -1}};
        System.out.println(solution(5, 5, preferences));
    }

    public static int solution(int n, int m, int[][] preferences) {
        // 최대 만족하는 사람 수를 저장할 변수
        int maxSatisfied = 0;
        // 가능한 모든 토핑 조합의 수 (2^m)
        int totalCombinations = 1 << m;

        // 모든 가능한 토핑 조합에 대해 반복
        for (int i = 0; i < totalCombinations; i++) {
            for (int j = i; j < totalCombinations; j++) {
                // 현재 조합(i, j)에 대해 만족하는 사람 수 계산
                int satisfied = countSatisfiedPeople(n, m, preferences, i, j);
                // 최대값 갱신
                maxSatisfied = Math.max(maxSatisfied, satisfied);
            }
        }

        return maxSatisfied;
    }

    private static int countSatisfiedPeople(int n, int m, int[][] preferences, int leftHalf, int rightHalf) {
        int satisfied = 0;

        for (int person = 0; person < n; person++) {
            // 각 사람에 대해 왼쪽 또는 오른쪽 반이 선호도를 만족하는지 확인
            if (isSatisfied(preferences[person], leftHalf, m) || isSatisfied(preferences[person], rightHalf, m)) {
                satisfied++;
            }
        }

        return satisfied;
    }

    private static boolean isSatisfied(int[] preference, int toppings, int m) {
        for (int i = 0; i < m; i++) {
            if (preference[i] == 1 && (toppings & (1 << i)) == 0) {
                return false; // 선호하는 토핑이 없음
            }
            if (preference[i] == -1 && (toppings & (1 << i)) != 0) {
                return false; // 불호하는 토핑이 있음
            }
        }
        return true; // 모든 조건 만족
    }
}
