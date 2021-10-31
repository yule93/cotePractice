package programmers;

import java.util.Arrays;

public class programmers_level3_ranking {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] fw = new int[n + 1][n + 1];

        // 플로이드 와샬 표 채우기
        int MAX = 10000000;

        for (int[] arr : fw) {
            Arrays.fill(arr, MAX);
        }

        // 인접 행렬 만들기
        for (int[] arr : results) {
            fw[arr[0]][arr[1]] = 1;
        }

        // 플로이드 와샬: 경>출>도 순으로 짠다
        for (int k = 1; k < fw.length; k++) {
            // 경유지
            for (int i = 1; i < fw.length; i++) {
                // 출발점
                for (int j = 1; j < fw.length; j++) {
                    // 도착점
                    if (fw[i][j] > fw[i][k] + fw[k][j]) {
                        fw[i][j] = fw[i][k] + fw[k][j];
                    }
                }
            }
        }

        for (int i = 1; i < fw.length; i++) {
            boolean flag = true;
            for (int j = 1; j < fw[i].length; j++) {
                if (i == j)
                    continue;
                // 싸운 경우가 없을 때, 즉 순위를 판단할 수 없을 때,
                if (fw[i][j] >= MAX && fw[j][i] >= MAX) {
                    flag = false;
                    break;
                }
            }

            if (flag)
                answer++; // 모든 정점에서 갈 수 있는 정점을 찾은 경우
        }
        return answer;
    }
}
