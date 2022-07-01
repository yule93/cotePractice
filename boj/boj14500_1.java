package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 14500 테트로미노(dfs)
// 892ms
public class boj14500_1 {

  public static class Dot {
    int x, y;

    public Dot(int y, int x) {
      this.y = y;
      this.x = x;
    }
  }

  static int N, M, answer;
  static int[][] map;
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    answer = 0;
    boolean[][] visited = new boolean[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        visited[i][j] = true;
        dfs(i, j, 0, 0, visited);
        visited[i][j] = false;
        check(i, j);
      }
    }

    System.out.println(answer);
  }
  
  public static void dfs(int y, int x, int depth, int sum, boolean[][] visited) {
    if (depth == 4) {
      answer = Math.max(sum, answer);
      return;
    }
    
    for (int d = 0; d < 4; d++) {
      int ny = y + dy[d];
      int nx = x + dx[d];
      
      if (ny < 0 || ny >= N || nx < 0 || nx >= M)
        continue;
      if (visited[ny][nx])
        continue;
      
      visited[ny][nx] = true;
      dfs(ny, nx, depth + 1, sum + map[ny][nx], visited);
      visited[ny][nx] = false;
    }
  }

  public static void check(int y, int x) {
    int sum = 0;
    // * 1. ㅗ
    if (y > 0 && x > 0 && y < N && x < M-1) {
      sum += (map[y][x] + map[y - 1][x] + map[y][x - 1] + map[y][x + 1]);
      answer = Math.max(answer, sum);
    }

    // * 2. ㅏ
    if (y > 0 && x >= 0 && y < N - 1 && x < M - 1) {
      sum = 0;
      sum += (map[y][x] + map[y - 1][x] + map[y+1][x] + map[y][x + 1]);
      answer = Math.max(answer, sum);
    }

    // * 3. ㅜ
    if (y >=0 && x > 0 && y < N - 1 && x < M - 1) {
      sum = 0;
      sum += (map[y][x] + map[y][x-1] + map[y+1][x] + map[y][x + 1]);
      answer = Math.max(answer, sum);
    }

    // * 4. ㅓ
    if (y > 0 && x > 0 && y < N - 1 && x < M) {
      sum = 0;
      sum += (map[y][x] + map[y - 1][x] + map[y + 1][x] + map[y][x -1]);
      answer = Math.max(answer, sum);
    }
  }
}
