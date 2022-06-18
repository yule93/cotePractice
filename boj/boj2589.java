package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 2589 보물섬(bfs)
// 536ms
public class boj2589 {
  static boolean[][] visited;
  static int[][] memo;
  static char[][] map;
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };
  static int R, C, answer;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    map = new char[R][C];
    for (int i = 0; i < R; i++) {
      map[i] = br.readLine().toCharArray();
    }

    answer = 0;
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (map[i][j] == 'L') {
          memo = new int[R][C];
          visited = new boolean[R][C];
          memo[i][j] = 1;
          visited[i][j] = true;   // ! 늘 초기화하고 방문점 체크해주는 거 잊지 않기....
          bfs(i, j);
          for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
              answer = Math.max(answer, memo[r][c]);
            }
          }
        }
      }
    }

    System.out.println(answer);
  }

  public static void bfs(int y, int x) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { y, x, 1 });
    while (q.size() > 0) {
      int[] now = q.poll();

      for (int d = 0; d < 4; d++) {
        int ny = now[0] + dy[d];
        int nx = now[1] + dx[d];

        if (ny < 0 || ny >= R || nx < 0 || nx >= C)
          continue;
        if (visited[ny][nx])
          continue;
        if (map[ny][nx] == 'W')
          continue;

        visited[ny][nx] = true;
        memo[ny][nx] = now[2];
        q.add(new int[] { ny, nx, now[2] + 1 });
      }
    }
  }

  public static void dfs(int y, int x, int move) {
    for (int d = 0; d < 4; d++) {
      int ny = y + dy[d];
      int nx = x + dx[d];

      if (ny < 0 || ny >= R || nx < 0 || nx >= C)
        continue;
      if (memo[ny][nx] <= move + 1)
        return;
      if (visited[ny][nx])
        continue;
      if (map[ny][nx] == 'W')
        continue;

      visited[ny][nx] = true;
      memo[ny][nx] = Math.min(move + 1, memo[ny][nx]);
      answer = Math.max(answer, memo[ny][nx]);
      dfs(ny, nx, move + 1);
    }
  }
}
