package NHN;

import java.util.Arrays;

public class p2 {
    public static void main(String[] args) {
        String s = "aabbbabba";
        int n = 2;

        System.out.println(solution(s, n));
    }

    public static int solution(String s, int n) {
        // 이분 탐색을 위한 범위 설정
        int left = 1;
        int right = s.length();

        while (left < right) {
            // 중간값 계산
            int mid = left + (right - left) / 2;
            // 현재 중간값으로 분할 가능한지 확인
            if (canSplit(s, n, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean canSplit(String s, int n, int maxCount) {
        int[] count = new int[26]; // 알파벳 카운트 배열
        int splits = 0; // 분할 횟수
        int start = 0; // 현재 부분 문자열의 시작 인덱스

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;

            // 현재 알파벳의 등장 횟수가 maxCount를 초과하면 분할
            if (count[s.charAt(i) - 'a'] > maxCount) {
                if (i == start) {
                    return false; // 한 문자도 포함하지 않고 분할해야 하는 경우
                }
                splits++;
                if (splits > n) {
                    return false; // 분할 횟수 초과
                }
                Arrays.fill(count, 0); // 카운트 초기화
                i--; // 현재 문자를 다시 검사하기 위해 인덱스 감소
                start = i; // 새로운 부분 문자열 시작
            }
        }

        return true; // 분할 가능
    }
}
