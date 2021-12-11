package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 15988 1,2,3 더하기 3 (dp, long타입 조심하기)
// 152ms
public class boj15988 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    long[] dp = new long[1000001];
    
    dp[1] = 1; // 1
    dp[2] = 2; // 1+1, 2
    dp[3] = 4; // 2+1, 1+2, 1+1+1, 3
    for (int i = 4; i <= 1000000; i++) {
      dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1_000_000_009;
    }

    for (int tc = 1; tc <= T; tc++) {
    int N = Integer.parseInt(br.readLine());
      sb.append(dp[N]).append("\n");
    }

    System.out.print(sb.toString());
  }
  
  public static int dfs(int n) {
    if (n == 1)
      return 1;
    else if (n == 2)
      return 2;
    else if (n == 3)
      return 4;
    
    return dfs(n - 1) + dfs(n - 2) + dfs(n - 3);
  }
}
