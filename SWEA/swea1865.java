package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA 1865 동철이의 일 분배 (dfs, 백트래킹)
// 백트래킹이 진짜 중요함....
public class swea1865 {
  static int N;
  static double[][] arr;
  static double answer;
  static boolean[] visited;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());

      arr = new double[N][N];
      for (int n = 0; n < N; n++) {
        st = new StringTokenizer(br.readLine());
        for (int l = 0; l < N; l++) {
          arr[n][l] = Double.parseDouble(st.nextToken())/100.0;
        }
      }

      visited = new boolean[N];
      answer = 0;
      dfs(0, 1.0);

      sb.append("#").append(tc).append(" ").append(String.format("%.6f", answer*100.0)).append("\n");
    }
    
    System.out.print(sb.toString());
  }

  public static void dfs(int depth, double result) {
    if (depth == N) {
      answer = Math.max(answer, result);
      return;
    }

    if (answer >= result)
      return;

    for (int i = 0; i < N; i++) {
      if (visited[i])
        continue;
      visited[i] = true;
      dfs(depth + 1, result * arr[depth][i]);
      visited[i] = false;
    }
  }
}
