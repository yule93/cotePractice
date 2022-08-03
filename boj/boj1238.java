package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 1238 파티 (다익스트라)
// 와 그래도 2076ms
public class boj1238 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());   // 마을 수와 학생 수 1~1000
    int m = Integer.parseInt(st.nextToken()); // 도로 개수 1 ~ 10000
    int x = Integer.parseInt(st.nextToken()); // 목적지

    // 1000이니까 10만까지는 괜찮은듯싶음~~
    int[][] memo = new int[n + 1][n + 1];

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (i == j) {
          memo[i][j] = 0;
        } else {
          memo[i][j] = 987_654_321;
        }
      }
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      memo[from][to] = cost;
    }

    for (int k = 1; k < n + 1; k++) {
      for (int i = 1; i < n + 1; i++) {
        for (int j = 1; j < n + 1; j++) {
          if (memo[i][k] + memo[k][j] < memo[i][j]) {
            memo[i][j] = memo[i][k] + memo[k][j];
          }
        }
      }
    }

    int res = Integer.MIN_VALUE;
    for (int i = 1; i < n + 1; i++) {
      int dis = memo[i][x] + memo[x][i];
      if (dis > res) {
        res = dis;
      }
    }

    System.out.println(res);
  }
}
