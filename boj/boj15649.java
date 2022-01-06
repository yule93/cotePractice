package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 15649 N과 M(1) (dfs)
// 중복이 아닌 순열을 만드는 문제
// 276ms
public class boj15649 {
  
  static int[] perm;
  static boolean[] visited;
  static int N, M;
  static StringBuilder sb;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    sb = new StringBuilder();

    N = Integer.parseInt(st.nextToken()); // 1 <= M <= N <= 8
    M = Integer.parseInt(st.nextToken());

    // 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
    perm = new int[M];
    visited = new boolean[N];
    dfs(0);

    System.out.print(sb.toString());
  }
  
  // 순열
  public static void dfs(int depth) {
    if (depth == M) {
      for (int i = 0; i < M; i++) {
        sb.append(perm[i]).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 0; i < N; i++) {
      if (visited[i])
        continue;
      visited[i] = true;
      perm[depth] = i+1;
      dfs(depth + 1);
      visited[i] = false;
    }
  }
}
