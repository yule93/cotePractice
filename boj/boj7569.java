package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 7569 토마토 (BFS)
// 668ms
public class boj7569 {
  static int[][][] arr;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken());   // 열의 개수
    int N = Integer.parseInt(st.nextToken());   // 행으 ㅣ개수
    int H = Integer.parseInt(st.nextToken());

    // 1은 익은 토마토, 0은 익지 않은 토마토, -1은 토마토가 들어있지 않음.
    arr = new int[H][N][M]; // 높이, 세로, 가로
    int[] dx = { 0, 0, 0, 0, -1, 1 };
    int[] dy = { 0, 0, -1, 1, 0, 0 };
    int[] dh = { -1, 1, 0, 0, 0, 0 };

    Queue<int[]> q = new LinkedList<>();
    int ripe = 0;
    for (int k = 0; k < H; k++) {
      for (int i = 0; i < N; i++) {
        String str = br.readLine().replace(" ", "");
        for (int j = 0; j < M; j++) {
          int value = str.charAt(j) - '0';
          arr[k][i][j] = value;
          if (value == 0)
            ripe++;
          if (value == 1) {
            q.add(new int[] { k, i, j });
          }
        }
      }
    }

    if (ripe == 0) {
      System.out.println(0);
      return;
    }

    int result = 0;

    // bfs 시작
    while (q.size() > 0) {
      result++; // 하루 지남
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int[] nowPos = q.poll();
        for (int j = 2; j < 6; j++) {
          int ny = nowPos[1] + dy[j];
          int nx = nowPos[2] + dx[j];
          if (ny < 0 || nx < 0 || nx >= M || ny >= N)
            continue;
          if (arr[nowPos[0]][ny][nx] == 0) {
            arr[nowPos[0]][ny][nx] = 1; // 익음
            ripe--;
            q.add(new int[] { nowPos[0], ny, nx });
          }
        }

        for (int j = 0; j < 2; j++) {
          int nh = nowPos[0] + dh[j];
          if (nh < 0 || nh >= H)
            continue;
          if (arr[nh][nowPos[1]][nowPos[2]] == 0) {
            arr[nh][nowPos[1]][nowPos[2]] = 1;
            ripe--;
            q.add(new int[] { nh, nowPos[1], nowPos[2] });
          }
        }
      }
    }

    System.out.println(ripe != 0 ? -1 : result-1);
  }

}
