package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 1743 음식물 피하기
// 딱봐도 단순한 bfs 문제인데 이상하게 메모리 초과가 계속 남...
// 180ms.... visited 중복 선언 조심하자....
public class boj1743 {
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };
  static int N, M, K, answer;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 통로 세로 길이 1 ~ 100
    M = Integer.parseInt(st.nextToken()); // 가로 길이 1 ~ 100
    K = Integer.parseInt(st.nextToken()); // 음식물 개수 1 ~ 100*100

    boolean[][] map = new boolean[N][M];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()) - 1;
      int m = Integer.parseInt(st.nextToken()) - 1;
      map[n][m] = true;
    }

    answer = 0;
    // bfs or dfs 아무튼 완전탐색
    // for (int i = 0; i < N; i++) {
    // for (int j = 0; j < M; j++) {
    // if (map[i][j]) {
    // boolean[][] visited = new boolean[N][M];
    // dfs(i, j, 1, map, visited);
    // }
    // }
    // }

    Queue<int[]> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j]) {
          q.clear();
          int cnt = 1;
          q.add(new int[] { i, j });
          visited[i][j] = true;
          while (q.size() > 0) {
            int[] now = q.poll();
            for (int d = 0; d < 4; d++) {
              int ny = now[0] + dy[d];
              int nx = now[1] + dx[d];
              if (ny < 0 || ny >= N || nx < 0 || nx >= M)
                continue;
              if (visited[ny][nx])
                continue;
              if (!map[ny][nx])
                continue;

              visited[ny][nx] = true;
              cnt++;
              q.add(new int[] { ny, nx });
            }
          }
          
          answer = Math.max(answer, cnt);
        }
      }
    }

    System.out.println(answer);
  }

  public static void dfs(int y, int x, int size, boolean[][] map, boolean[][] visited) {
    for (int d = 0; d < 4; d++) {
      int ny = y + dy[d];
      int nx = x + dx[d];

      if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
        answer = Math.max(answer, size);
        continue;
      }
      if (visited[ny][nx]) {
        answer = Math.max(answer, size);
        continue;
      }
      if (!map[ny][nx]) {
        answer = Math.max(answer, size);
        continue;
      }

      dfs(ny, nx, size + 1, map, visited);
    }
  }

  public static void bfs() {
    /*
     * Queue<int[]> q = new LinkedList<>();
     * boolean[][] visited;
     * for (int i = 0; i < N; i++) {
     * for (int j = 0; j < M; j++) {
     * if (map[i][j]) {
     * q.add(new int[] { i, j, 1 });
     * visited = new boolean[N][M];
     * while (q.size() > 0) {
     * int[] now = q.poll();
     * answer = Math.max(answer, now[2]);
     * for (int d = 0; d < 4; d++) {
     * int ny = now[0] + dy[d];
     * int nx = now[1] + dx[d];
     * 
     * if (ny < 0 || ny >= N || nx < 0 || nx >= M)
     * continue;
     * if (visited[ny][nx])
     * continue;
     * if (!map[ny][nx])
     * continue;
     * 
     * visited[ny][nx] = true;
     * q.add(new int[] { ny, nx, now[2] + 1 });
     * }
     * }
     * }
     * }
     * }
     */
  }
}
