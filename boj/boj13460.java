package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 13460 구슬 탈출 2 (시뮬레이션, pq)
public class boj13460 {
  static int[] dy = { 0, 0, 1, -1 }; // 좌우상하
  static int[] dx = { 1, -1, 0, 0 };
  static boolean[][][][] visited;
  static int min = 123_456_789;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken()); // 3~10
    int M = Integer.parseInt(st.nextToken()); // 3~10

    // * '.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 'O'는 구멍의 위치를 의미한다. 'R'은 빨간
    // 구슬의 위치, 'B'는 파란 구슬의 위치
    int ry = 0, rx = 0, by = 0, bx = 0;
    visited = new boolean[N][M][N][M];
    String[][] map = new String[N][M];
    for (int i = 0; i < N; i++) {
      String[] strs = br.readLine().split("");
      for (int j = 0; j < M; j++) {
        map[i][j] = strs[j];
        if (map[i][j] == "R") {
          ry = i;
          rx = j;
        } else if (map[i][j] == "B") {
          by = i;
          bx = j;
        }
      }
    }

    // ! bfs 시작
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { ry, rx, by, bx, 0 });
    visited[ry][rx][by][bx] = true; // 빨간 구슬과 파란구슬 방문 여부

    bfs:
    while (q.size() > 0) {
      int[] nowPos = q.poll();
      int pCount = nowPos[4];

      if (pCount >= 10) {
        break;
      }

      for (int d = 0; d < 4; d++) {
        int nRy = nowPos[0];
        int nRx = nowPos[1];
        int nBy = nowPos[2];
        int nBx = nowPos[3];

        // System.out.println(nRy+", "+nRx+", "+nBy+", "+nBx);
        // 빨간 구슬 이동
        while (map[nRy + dy[d]][nRx + dx[d]] != "#") {
          nRy += dy[d];
          nRx += dx[d];
          if (map[nRy][nRx] == "O")
            break;
        }

        while (map[nBy + dy[d]][nBx + dx[d]] != "#") {
          nBy += dy[d];
          nBx += dx[d];
          if (map[nBy][nBx] == "O")
            break;
        }

        if (map[nBy][nBx] == "O")
          continue;
        if (map[nRy][nRx] == "O") {
          min = Math.min(min, pCount + 1);
          break bfs;
        }

        if (nRy >= N || nRx < 0 || nRx >= M || nRy < 0 || nBy >= N || nBy < 0 || nBx >= M || nBx < 0)
          continue;

        if (nRy == nBy && nRx == nBx && map[nRy][nRx] != "O") {
          int redMove = Math.abs(nRy - nowPos[0]) + Math.abs(nRx - nowPos[1]);
          int blueMove = Math.abs(nBy - nowPos[2]) + Math.abs(nBx - nowPos[3]);

          // * 파란 공이 먼저 도착
          if (redMove > blueMove) {
            nRy -= dy[d];
            nRx -= dx[d];
          } else {
            // * 빨간 공이 먼저 도착
            nBy -= dy[d];
            nBx -= dx[d];
          }
        }
        
        if (!visited[nRy][nRx][nBy][nBx]) {
          visited[nRy][nRx][nBy][nBx] = true;
          q.add(new int[] { nRy, nRx, nBy, nBx });
        }
      }
    }

    System.out.println(min == 123_456_789 ? -1 : min);
  }
}
