package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 1937 욕심쟁이 판다(완전탐색, 메모이제이션)
// 메모이제이션이 중요함
// 1224ms
public class boj1937 {
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };
  static int[][] map, memo;
  static int n;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine()); // 맵 크기
    map = new int[n][n];
    memo = new int[n][n];

    StringTokenizer st;
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        answer = Math.max(answer, dfs(i, j));
      }
    }

    System.out.println(answer);
  }

  public static int dfs(int y, int x) {
    if (memo[y][x] != 0)
      return memo[y][x];

    memo[y][x] = 1;
    for (int d = 0; d < 4; d++) {
      int ny = y + dy[d];
      int nx = x + dx[d];

      if (ny < 0 || ny >= n || nx < 0 || nx >= n)
        continue;

      if (map[y][x] >= map[ny][nx])
        continue;

      // 다음 대나무 숲에 대나무가 더 많다면 진행
      memo[ny][nx] = Math.max(memo[ny][nx], dfs(ny, nx) + 1);
    }

    return memo[y][x];
  }
}
