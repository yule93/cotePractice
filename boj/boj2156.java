package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 2156 포도주 시식(dp)
// 160ms
public class boj2156 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] wine = new int[N];
    int dp[] = new int[N];
    for (int i = 0; i < N; i++) {
      dp[i] = 0;
      wine[i] = Integer.parseInt(br.readLine());
    }

    if (N == 1) {
      System.out.println(wine[0]);
      return;
    } else if (N == 2) {
      System.out.println(wine[0] + wine[1]);
      return;
    } else if (N == 3) {
      System.out.println(Math.max(wine[2] + wine[1], Math.max(wine[2] + wine[0], wine[0] + wine[1])));
      return;
    }

    dp[0] = wine[0];
    dp[1] = wine[0] + wine[1];
    dp[2] = Math.max(wine[2] + wine[1], Math.max(wine[2] + wine[0], wine[0] + wine[1]));

    for (int i = 3; i < N; i++) {
      dp[i] = Math.max(dp[i - 1], Math.max(wine[i] + dp[i - 2], wine[i] + wine[i - 1] + dp[i - 3]));
    }
    
    System.out.println(dp[N-1]);
  }
}
