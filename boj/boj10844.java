package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 10844 쉬운 계단수(dp)
// * 10억 이상 나올 수 있으므로 나머지값 계산과 long 타입 조심하기
// 128ms
public class boj10844 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());    //  1~100의 자연수

    int[][] dp = new int[N + 1][10]; // 끝이 0~9로 끝나는 N자리 계단수
    for (int i = 1; i < 10; i++) {
      dp[0][i] = 0;
      dp[1][i] = 1;
    }

    int DIV = 1_000_000_000;
    for (int i = 2; i <= N; i++) {
      for (int j = 0; j < 10; j++) {
        if (j == 0) {
          dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % DIV;
        } else if (j == 9) {
          dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % DIV;
        } else {
          dp[i][j] = (dp[i][j] + (dp[i - 1][j + 1] + dp[i - 1][j - 1])) % DIV;
        }
      }
    }

    long answer = 0;
    for (int i = 0; i < 10; i++) {
      answer = (answer+ dp[N][i])%DIV;
    }

    System.out.println(answer % DIV);
  }
}
