package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// boj 15681 트리와 쿼리(트리, 그래프, dfs)
public class boj15681 {

  static int N, R, Q;
  static int[] dp;
  static boolean visited[];
  static ArrayList<Integer>[] list;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    Q = Integer.parseInt(st.nextToken());

    list = new ArrayList[N + 1];
    dp = new int[N + 1];
    visited = new boolean[N + 1];
    for (int i = 0; i <= N; i++) {
      list[i] = new ArrayList<Integer>();
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      list[u].add(v);
      list[v].add(u);
    }

    dfs(R);
    while (Q-- > 0) {
      int root = Integer.parseInt(br.readLine());
      sb.append(dp[root]).append("\n");
    }

    System.out.print(sb.toString());
  }

  public static int dfs(int now) {
    // 이미 방문한 노드면 패스
    // 
    if (dp[now] != 0)
      return dp[now];
    
    visited[now] = true;

    int count = 1;
    for (int node : list[now]) {
      // 아직 방문하지 않은 노드일 때만 값을 더해줌
      if (visited[node])
        continue;
      count += dfs(node);
    }

    // now번째 정점이 루트일 때, 서브트리에 속한 정점의 수 저장
    dp[now] = count;
    return dp[now];
  }
}