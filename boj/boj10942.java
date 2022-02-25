package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 10942 펠린드롬? (dp+dfs)
// 사실 dp 사용 안 해도 통과할 것 같은... 인덱스를 왼쪽과 오른쪽으로 나눠 왼쪽이 하나 증가하면 오른쪽은 하나 감소하는 식으로...!
// 단순 반복문은 해봤자 O(N*M) = 어.... 안 되겠네 ㅎㅎ 죄송
// 760ms
public class boj10942 {
  static int[] num;
  static int[][] dp;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine()); // 2000 이하
    num = new int[N + 1];
    dp = new int[N + 1][N + 1];   // 메모이제이션
    for (int i = 0; i < N + 1; i++) {
      Arrays.fill(dp[i], -1);
    }
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      num[i] = Integer.parseInt(st.nextToken());
    }

    int M = Integer.parseInt(br.readLine());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      boolean flag = checkPalin(start, end) == 1 ? true : false;
      if (flag) {
        sb.append("1\n");
      } else {
        sb.append("0\n");
      }
    }

    System.out.print(sb.toString());
  }

  // * 팰린드롬인지 아닌지 체크하는 함수
  static int checkPalin(int s, int e) {
    // * 모든 수를 체크했을 때, 팰린드롬 조건을 모두 만족한다면 1을 반환
    if (s >= e)
      return 1;

    // * 이미 계산된 결과가 있을 때,
    if (dp[s][e] != -1)
      return dp[s][e];

    // * s랑 e번째 수가 같음. 즉 팰린드롬 조건 만족
    if (num[s] == num[e]) {
      return dp[s][e] = checkPalin(s + 1, e - 1);
    }
    return 0;
  }
}
