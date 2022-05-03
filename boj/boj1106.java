package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 1106 호텔(냅색DP or DFS+memoization)
// * 정수배로 가능하다는 사실을 조심해야 하는 문제
// 124 ms
public class boj1106 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int INF = 987654321;

    int C = Integer.parseInt(st.nextToken());   // 유치하려는 고객
    int N = Integer.parseInt(st.nextToken());   // 홍보할 수 있는 도시 개수

    int[] knapsack = new int[C + 101];
    Arrays.fill(knapsack, INF);
    knapsack[0] = 0;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int cost = Integer.parseInt(st.nextToken()); // 비용
      int custom = Integer.parseInt(st.nextToken()); // 유치 가능 고객
      for (int j = custom; j < C + 101; j++) {
        int prev = knapsack[j - custom];
        if (prev != INF) {
          knapsack[j] = Math.min(prev + cost, knapsack[j]);
        }
      }
    }


    int answer = INF;
    for (int i = C; i < C + 101; i++) {
      answer = Math.min(answer, knapsack[i]);
    }

    System.out.println(answer);
  }
}
