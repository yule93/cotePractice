package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 2224 명제 증명(플로이드 와샬)
// 168ms
public class boj2224 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    boolean[][] dp = new boolean[58][58]; // 대소문자 합쳐서 개수
    int count = 0; // 총 몇 개 나왔는지

    int N = Integer.parseInt(br.readLine());

    for (int n = 0; n < N; n++) {
      // 입력 받기
      char[] info = br.readLine().toCharArray();
      if (info[0] == info[info.length - 1])
        continue;
      if (dp[info[0] - 'A'][info[info.length - 1] - 'A'])
        continue;

      count++;
      dp[info[0] - 'A'][info[info.length - 1] - 'A'] = true;
    }

    for (int i = 0; i < 58; i++) {
        for (int j = 0; j < 58; j++) {
          for (int k = 0; k < 58; k++) {
            if (dp[j][k] || j == k)
              continue;

            dp[j][k] = dp[j][i] && dp[i][k];
            if (dp[j][k])
              count++;
          }
        }
      }

      sb.append(count).append("\n");
      for (int i = 0; i < 58; i++) {
        for (int j = 0; j < 58; j++) {
          if (dp[i][j]) {
            sb.append((char) (i + 'A')).append(" => ").append((char) (j + 'A')).append("\n");
          }
        }
      }

    System.out.print(sb.toString());
  }
}
