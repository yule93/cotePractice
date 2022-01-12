package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 10026 적녹색약 (dfs)
// 196ms
public class boj10026 {
  static int N;
  static int[][] map;
  static boolean[][] checked;
  static int[] dx = { -1, 0, 1, 0 };
  static int[] dy = { 0, -1, 0, 1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    checked = new boolean[N][N];
    int count = 0;
    int rgCount = 0;

    for (int i = 0; i < N; i++) {
      String[] color = br.readLine().split("");
      for (int j = 0; j < N; j++) {
        if (color[j].equals("R")) {
          map[i][j] = 1;
        } else if (color[j].equals("G")) {
          map[i][j] = 2;
        } else {
          map[i][j] = 3;
        }

      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 1; k < 4; k++) {
          if (!checked[i][j] && map[i][j] == k) {
            dfs(i, j, k);
            count++;
          }
        }
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (map[i][j] == 1) {
          // R이랑 G랑 똑같은 걸로 바꿔주기
          map[i][j] = 2;
        }
      }
    }

    checked = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 2; k < 4; k++) {
          if (!checked[i][j] && map[i][j] == k) {
            dfs(i, j, k);
            rgCount++;
          }
        }
      }
    }

    System.out.println(count + " " + rgCount);

  }

  static void dfs(int x, int y, int color) {
    checked[x][y] = true;
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1)
        continue;
      if (checked[nx][ny])
        continue;

      if (map[nx][ny] == color) {
        dfs(nx, ny, color);
      }
    }
  }
}
