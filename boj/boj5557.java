package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj5557 1학년(dp, 냅색)
// 128ms
public class boj5557 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine()); // 숫자의 개수 N. 3 ~ 100

    // * 상근이는 음수와 20을 넘는 수를 모르는 올바른 등식을 만드려고 함.
    int[] arr = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    long[][] dp = new long[N][21];

    dp[0][arr[0]] = 1;
    int plus;
    int minus;
    // * N이 최대 300이므로 재귀로는 시간 초과.
    // * arr[n]과 arr[m]의 조합은 0~18 사이의 숫자로 정해짐
    for (int i = 1; i < N - 1; i++) {
      for (int j = 0; j <= 20; j++) {
        if (dp[i - 1][j] != 0) {
          plus = j + arr[i];
          minus = j - arr[i];
          if (plus >= 0 && plus <= 20) {
            dp[i][plus] += dp[i - 1][j];
          }
          if (minus >= 0 && minus <= 20) {
            dp[i][minus] += dp[i - 1][j];
          }
        }
      }
    }

    System.out.println(dp[N-2][arr[N-1]]);
  }
}
