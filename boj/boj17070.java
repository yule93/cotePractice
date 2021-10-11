package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ 17070 파이프 옮기기 1 (DP, 시뮬레이션)
// 프로그래머스 등교길이랑 비슷한 문제인 것 같음.
public class boj17070 {

    static int[][][] dp;
    static int[][] map;
    static int result;

    public static void main(String[] args) throws Exception {
        // 파이프의 종류는 1: ㅡ, 2: ㅣ, 3: ＼. / 모양은 없음! 조심하기!!
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N][N][3]; // 3차원은 파이프에 따른 이동 방향(0, 1, 2)
        // 0은 갈 수 있는 곳, 1은 벽
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine().replaceAll(" ", "");
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        if (map[N - 1][N - 1] == 1) {
            // 목표지점이 벽이라면,
            System.out.println(0);
            return;
        }

        dp[0][1][0] = 1; // 가로랑 세로로 이동한 상태!
        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                if (j - 1 >= 0 && map[i][j] == 0) { // 가로
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
                }
                if (i - 1 >= 0 && map[i][j] == 0) { // 세로
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
                }
                if (i - 1 >= 0 && j - 1 >= 0 && map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) { // 대각
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
            }
        }

        int answer = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
        System.out.println(answer);
    }
}
