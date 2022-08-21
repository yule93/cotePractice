package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 2573 빙산(dfs, bfs)
// * 빙산이 한 개도 남지 않았을 경우를 생각해야 하는 문제.... 마지막 줄을 잘 안 봤음....
// 624ms
public class boj2573 {
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };
  static int N, M;
  static int[][] map;
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

    int hour = 0;

    Queue<int[]> q = new LinkedList<>();
    while (true) {
      int count = 0;
      // 1. dfs로 빙산이 두 조각 이상인지 체크
      boolean[][] visited = new boolean[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (map[i][j] != 0 && !visited[i][j]) {
            dfs(i, j, visited);
            count++;
          }
        }
      }
      if (count >= 2)
        break;
      else if (count == 0) {
        hour = 0;
        break;
      }

      hour++;
      // 2. 녹이기 시작
      visited = new boolean[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (map[i][j] != 0) {
            q.offer(new int[] { i, j });
            visited[i][j] = true;
          }
        }
      }

      while (q.size() > 0) {
        int[] nowP = q.poll();
        int sea = 0;
        for (int d = 0; d < 4; d++) {
          int ny = nowP[0] + dy[d];
          int nx = nowP[1] + dx[d];

          if (ny < 0 || ny >= N || nx < 0 || nx >= M)
            continue;

          if (!visited[ny][nx] && map[ny][nx] == 0)
            sea++;
        }
        
        map[nowP[0]][nowP[1]] = map[nowP[0]][nowP[1]] - sea < 0 ? 0 : map[nowP[0]][nowP[1]] - sea;
      }
    }

    System.out.println(hour);
  }
  
  public static void dfs(int y, int x, boolean[][] visited) {
    visited[y][x] = true;
    for (int d = 0; d < 4; d++) {
      int ny = y + dy[d];
      int nx = x + dx[d];
      if (ny < 0 || ny >= N || nx < 0 || nx >= M)
        continue;

      if (map[ny][nx] != 0 && !visited[ny][nx]) {
        dfs(ny, nx, visited);
      }
    }
  }
}