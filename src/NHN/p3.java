package NHN;

import java.util.*;

public class p3 {
    public static void main(String[] args) {
        int[] sid = {201820793, 201969011, 202020202, 202299999, 202200000, 202232134, 202310039};
        int[] sfirst = {201820793, 202020202, 202200000, 202232134, 202310039};

        System.out.println( solution(sid, sfirst) );
    }

    public static int solution(int[] sid, int[] sfirst) {
        // Sort student IDs and IDs of students who want the lower bunk
        Arrays.sort(sid);
        Arrays.sort(sfirst);

        int n = sid.length;
        int k = (n + 3) / 4; // Number of rooms

        // Create a set of students who want the lower bunk
        Set<Integer> wantLowerBunk = new HashSet<>();
        for (int id : sfirst) {
            wantLowerBunk.add(id);
        }

        // Variable to keep track of the maximum number of students who can get the lower bunk
        int maxLowerBunkWanted = 0;

        // Process each room
        for (int i = 0; i < k; i++) {
            int startIndex = i * 4;
            int endIndex = Math.min(startIndex + 4, n);
            int[] roomStudents = Arrays.copyOfRange(sid, startIndex, endIndex);

            // Count the number of students in this room who want the lower bunk
            int lowerBunkWanted = 0;
            for (int student : roomStudents) {
                if (wantLowerBunk.contains(student)) {
                    lowerBunkWanted++;
                }
            }

            // Calculate the number of lower bunks that can be assigned
            if (roomStudents.length >= 3) {
                // Room has 3 or 4 students, we can assign 2 lower bunks
                maxLowerBunkWanted += Math.min(lowerBunkWanted, 2);
            } else {
                // Room has 1 or 2 students, all can get lower bunks
                maxLowerBunkWanted += lowerBunkWanted;
            }
        }

        return maxLowerBunkWanted;
    }
}