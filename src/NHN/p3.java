package NHN;

import java.util.*;

public class p3 {
    public static int solution(int[] sid, int[] sfirst) {
        // sid와 sfirst를 정렬
        Arrays.sort(sid);
        Arrays.sort(sfirst);

        int n = sid.length;
        int rooms = (n + 3) / 4;  // 방의 수는 n/4의 올림 값
        int firstIdx = 0; // sfirst에서의 인덱스
        int sidIdx = 0;   // sid에서의 인덱스
        int count = 0;    // 1층에 배치된 1층 희망 학생 수

        // 방별로 배정
        for (int r = 0; r < rooms; r++) {
            int bedCount = Math.min(4, n - r * 4); // 현재 방의 학생 수 (최대 4명)
            int firstFloorCapacity = Math.min(2, bedCount); // 현재 방에 1층에 배치할 수 있는 최대 인원

            int placedOnFirst = 0; // 이번 방에 1층 침대에 배치된 학생 수

            // 1층을 희망하는 학생들을 1층 침대에 배정
            while (firstIdx < sfirst.length && placedOnFirst < firstFloorCapacity) {
                placedOnFirst++;
                firstIdx++;
            }

            count += placedOnFirst;

            // 남은 자리 학번 순으로 배치
            sidIdx += bedCount;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] sid1 = {201820793, 201969011, 202020202, 202299999, 202200000, 202232134, 202310039};
        int[] sfirst1 = {201820793, 202020202, 202200000, 202232134, 202310039};
        System.out.println(solution(sid1, sfirst1));

        int[] sid2= {900000000, 900000001, 900000002, 900000003, 999999999};
        int[] sfirst2 = {999999999};
        System.out.println(solution(sid2, sfirst2));
    }
}
