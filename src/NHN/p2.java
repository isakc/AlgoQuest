package NHN;

import java.util.HashMap;
import java.util.Map;

public class p2 {
    public static int solution(String s, int n) {
        int low = 1;
        int high = s.length();

        while (low < high) {
            int mid = (low + high) / 2;
            if (canSplit(s, n, mid)) {
                high = mid;  // 가능한 최솟값을 찾기 위해 upper bound를 낮춤
            } else {
                low = mid + 1;  // mid 값이 너무 작으므로 lower bound를 올림
            }
        }

        return low;
    }

    private static boolean canSplit(String s, int n, int maxCount) {
        int splits = 0;
        int[] freq = new int[26];  // 알파벳 빈도를 저장하는 배열
        int maxFreq = 0;
        int currentLength = 0;

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(i) - 'a']);
            currentLength++;

            // 현재 부분 문자열에서 maxCount를 초과하는지 확인
            if (maxFreq > maxCount) {
                splits++;
                freq = new int[26];  // 빈도 배열을 초기화
                freq[s.charAt(i) - 'a']++;
                maxFreq = 1;
                currentLength = 1;
            }
        }

        return splits <= n;
    }

    private static int getMaxFreq(Map<Character, Integer> freqMap) {
        int maxFreq = 0;
        for (int count : freqMap.values()) {
            maxFreq = Math.max(maxFreq, count);
        }
        return maxFreq;
    }

    public static void main(String[] args) {
        String s1 = "aabbbabba";
        int n1 = 2;
        System.out.println(solution(s1, n1));

        String s2 = "xyyyyxxxxxx";
        int n2 = 2;
        System.out.println(solution(s2, n2));

        String s3 = "abcd";
        int n3 = 1;
        System.out.println(solution(s3, n3));
    }
}
