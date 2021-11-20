package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 15990 1, 2, 3 더하기 5 (DP)
// 176ms
public class boj15990 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder("");

    long[][] dp = new long[100_001][4];
    dp[1][1] = 1; // 1
    dp[2][2] = 1; // 2
    dp[3][1] = 1; // 2+1
    dp[3][2] = 1; // 1+2
    dp[3][3] = 1; // 3

    for (int i = 4; i < 100_001; i++) {
      dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1_000_000_009;
      dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1_000_000_009;
      dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1_000_000_009;
    }

    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      int N = Integer.parseInt(br.readLine());
      sb.append((dp[N][1] + dp[N][2] + dp[N][3]) % 1_000_000_009).append("\n");
    }
      System.out.print(sb.toString());
    }
  }