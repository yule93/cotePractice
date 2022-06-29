package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 14501 퇴사(냅색 or dfs)
// 124 ms
public class boj14501 {
  static int[][] arr;
  static int N;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine()); // 1 ~ 15
    arr = new int[N + 1][2];
    StringTokenizer st;
    for (int i = N; i > 0; i--) {
      st = new StringTokenizer(br.readLine());
      arr[i][0] = Integer.parseInt(st.nextToken()); // T: 걸리는 일수. 1 ~ 1000
      arr[i][1] = Integer.parseInt(st.nextToken()); // P:벌 수 있는 금액. 1 ~ 1000
    }

    int[] dp = new int[N + 1];
    for (int i = 1; i < dp.length; i++) {
      if (arr[i][0] > i) {
        dp[i] = dp[i - 1];
      } else {
        dp[i] = Math.max(dp[i - 1], arr[i][1] + dp[i - arr[i][0]]);
      }
    }
    // 출력
    System.out.println(dp[N]);

    // dp[0] = 0;
    // for (int i = 0; i <= N; i++) {
    // if (arr[i][0] + i <= N) {
    // // System.out.println((i) +": "+dp[arr[i][0]+i]+", " + (arr[i][1]+dp[i])+",
    // "+arr[i][0]);
    // dp[arr[i][0] + i] = Math.max(dp[arr[i][0] + i], arr[i][1] + dp[i]);
    // }
    // }

    // int answer = 0;
    // for (int i = 0; i <= N; i++) {
    // answer = Math.max(answer, dp[i]);
    // }

    // System.out.println(answer);
  }
}
