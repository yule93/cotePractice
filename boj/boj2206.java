package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// boj 2206 벽 부수고 이동하기 (bfs, 백트래킹)
public class boj2206 {

  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };

  static boolean[][] visited;
  static int[][] map;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());   // 1000
    int M = Integer.parseInt(st.nextToken());   // 1000
    // 0은 이동 가능, 1은 이동 불가능
    visited = new boolean[N][M];
    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = str.charAt(j) - '0';
      }
    }

    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[2] - o2[2];
      }
    });

    pq.add(new int[] { 0, 0, 0, 1 }); // y, x, 이동한 칸, 벽 부술 수 있는 기회
    while (pq.size() > 0) {
      int[] now = pq.poll();

      if (now[0] == N - 1 && now[1] == M - 1) {
        System.out.println(now[2] + 1);
        return;
      }

      for (int i = 0; i < 4; i++) {
        int ny = now[0] + dy[i];
        int nx = now[1] + dx[i];
        if (ny >= N || ny < 0 || nx >= M || nx < 0)
          continue;
        if (visited[ny][nx])
          continue;
        if (now[3] == 1 && map[ny][nx] == 1) {
          pq.add(new int[] { ny, nx, now[2] + 1, 0 });
          visited[now[0]][now[1]] = true;
        }
        if (map[ny][nx] == 0) {
          pq.add(new int[] { ny, nx, now[2] + 1, now[3] });
          visited[now[0]][now[1]] = true;
        }
      }
    }

    System.out.println(-1);
  }
}
