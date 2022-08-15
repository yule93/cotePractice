package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 2638 치즈(bfs)
// ! 치즈 외곽을 구별하기 위해서는 치즈가 아니라 외부 공기를 탐색해야 한다는 걸 깨달아야 하는 문제
// 344ms
public class boj2638 {
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, 1, -1 };
  static int N, M;
  static boolean[][] visited, air, cheese;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    air = new boolean[N][M];
    cheese = new boolean[N][M];
    int[][] map = new int[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine().replaceAll(" ", "");
      for (int j = 0; j < M; j++) {
        map[i][j] = str.charAt(j) - '0';
        if (map[i][j] == 1) {
          cheese[i][j] = true;
        }
      }
    }

    int hour = 0;
    Queue<int[]> q = new LinkedList<>();
    Queue<int[]> meltC = new LinkedList<>();
    // * 두 변 이상이 실내 온도의 공기와 접촉하면 녹아 없어짐
    while (true) {
      int count = 0; // 한 시간에 녹은 치즈의 개수

      visited = new boolean[N][M];
      q.add(new int[] { 0, 0 });
      while (q.size() > 0) {
        int[] now = q.poll();
        for (int d = 0; d < 4; d++) {
          int ny = now[0] + dy[d];
          int nx = now[1] + dx[d];
          if (ny < 0 || ny >= N || nx < 0 || nx >= M || cheese[ny][nx] || visited[ny][nx])
            continue;
          air[ny][nx] = true;
          visited[ny][nx] = true;
          q.add(new int[] { ny, nx });
        }
      }

      // System.out.println();
      // for (int i = 0; i < N; i++) {
      //   for (int j = 0; j < M; j++) {
      //     if (air[i][j])
      //       System.out.print(0 + " ");
      //     else
      //       System.out.print(1 + " ");
      //   }
      //   System.out.println();
      // }
      
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (!cheese[i][j])
            continue;
          int edgeCount = 0;
          for (int d = 0; d < 4; d++) {
            int ny = i + dy[d];
            int nx = j + dx[d];
            if (air[ny][nx])
              edgeCount++;
            if (edgeCount >= 2) {
              meltC.add(new int[] { i, j });
              break;
            }
          }

          // System.out.println(i+", "+j+": "+edgeCount);
        }
      }

      while (meltC.size() > 0) {
        int[] now = meltC.poll();
        cheese[now[0]][now[1]] = false;
        count++;
      }

      if (count == 0)
        break;
      else
        hour++;
    }

    System.out.println(hour);
  }

}