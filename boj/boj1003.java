package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 1003 피보나치 함수 (dp)
// 제한시간이 0.25초로 알고리즘 문제 중에선 매우 짧은 편... 따라서 시간을 줄일 수 있는 메모이제이션 혹은 dp를 사용해야 한다.
// 120ms
public class boj1003 {
  
  static int[][] dp = new int[41][2]; // 점화식 값을 메모이제이션 해둘 배열
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    
    int T = Integer.parseInt(br.readLine());
    for (int tc = 0; tc < T; tc++) {
      // 각 테스트케이스별 진행 시작
      int N = Integer.parseInt(br.readLine());

      dp[0][0] = 1;
      dp[1][1] = 1;
      for (int i = 2; i <= N; i++) {
        dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
        dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
      }
      sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
    }
    
    System.out.print(sb.toString());
  }

  public static int fibonacci(int N) {
    if (N == 1) {
      return 1;
    } else if (N == 0) {
      return 0;
    }

    return fibonacci(N - 1) + fibonacci(N - 2);
  }
}
