package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 1309 동물원(dp)
// 136ms
public class boj1309 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    long[][] dp = new long[N + 1][3];
    dp[1][0] = dp[1][1] = dp[1][2] = 1; // * 각각 한 개씩 가능하다. 0개 놓는 거 1개, 1개를 각각 오른쪽이랑 왼쪽 줄에 놓는 거 1개씩
    
    // ! 첫 번째 줄을 이용할 때!!! 9901로 나눈 나머지 값을 더해주면 된다.
    for (int i = 2; i <= N; i++) {
      dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
      dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]);
      dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
    }
    
    System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % 9901);
  }
}
