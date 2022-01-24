package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// boj 2688 숫자가 줄어들지 않아 (dp)
// ! 어떤 숫자가 줄어들지 않는다는 것은 그 숫자의 각 자리 수보다 그 왼쪽 자리 수가 작거나 같을 때이다. 즉 문제에서 오른쪽수부터 진행하라는 말
// ! N은 1~64. int로 환산미리 계산해두고 진행하는 것도 좋을 것 같음
// 120ms
public class boj2688 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    long[][] dp = new long[65][10]; // 행은 자리수, 열은 0~9까지 왼쪽에 붙일 수 있는 숫자들
    Arrays.fill(dp[0], 0); // 아예 공백
    Arrays.fill(dp[1], 1); // 1의 자리 모두 다 줄어들지 않는 숫자이므로
    
    for (int i = 2; i < 65; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = j; k < 10; k++) {
          dp[i][j] += dp[i - 1][k];
        }
      }
    }

    for (int tc = 1; tc <= T; tc++) {
      int n = Integer.parseInt(br.readLine());
      long sum = 0;
      for (int i = 0; i < 10; i++) {
        sum += dp[n][i];
      }
      sb.append(sum).append("\n");
    }
    
    System.out.print(sb.toString());
  }
}
