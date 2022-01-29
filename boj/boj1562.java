package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 1562 계단 수 (dp, 비트마스킹)
// i번째 수를 기준으로 i-1이 i와 +-1 차이나고, i+1과도 +-1 차이나야 한다. dp일 것 같은 예감 ^^
// ! N이 주어질 때, 길이가 N이면서 0부터 9까지 모두 등장하느ㅡㄴ 계단수 구하기... ㅋ
// 공간 복잡도는 (N+1)*11*1024, 최악의 경우 101*11*1024*4byte = 450만 byte = 4.5MB
// 시간 복잡도는 O(N*10*(2)^10), 최악의 경우 100*10*1024 = 대략 100만번으로 0.01초.
public class boj1562 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine()); // 1~100
    long[][][] dp = new long[N + 1][11][1 << 10];
    for (int i = 0; i < 10; i++) {
      dp[1][i][1 << i] = 1;
    }

    // ! N자리 계단수의 개수는 N-1자리 계단수에 i-1 혹은 i+1 숫자를 뒤에 붙인 것
    for (int i = 2; i <= N; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 1024; k++) {
          int bit = k | (1 << j);
          if (j == 0) {
            dp[i][0][k| 1<< 0] = (dp[i][0][k| 1<< 0] + dp[i - 1][1][k]) % 1_000_000_000;
          } else if (j == 9) {
            dp[i][9][k | 1 << 9] = (dp[i][9][k | 1 << 9] + dp[i - 1][8][k]) % 1_000_000_000;
          } else {
            dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k] + dp[i - 1][j - 1][k]) % 1_000_000_000;
          }
        }
      }
    }

    long sum = 0;
    for (int i = 0; i < 10; i++) {
      sum = (sum + dp[N][i][(1 << 10) - 1]) % 1_000_000_000;
    }
    System.out.println(sum);
  }
}
