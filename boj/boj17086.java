package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 17086 아기 상어 2 (완전 탐색)
// 532ms
public class boj17086 {
  
  // 8방 이동 가능
  static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
  static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());   // 세로
    int M = Integer.parseInt(st.nextToken()); // 가로
    
    int[][] map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // 아기 상어와 가장 가까운 거리. 아기 상어를 기준으로 진행하면 좋을 것 같음.
    int[][] dist = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 1)
          continue;
        dist[i][j] = N * M;
      }
    }
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 1) {
          // bfs 시작
          q.add(new int[] { i, j, 1});
          boolean[][] visited = new boolean[N][M];
          while (q.size() > 0) {
            int[] now = q.poll();

            for (int d = 0; d < 8; d++) {
              int ny = now[0] + dy[d];
              int nx = now[1] + dx[d];

              if (ny < 0 || ny >= N || nx < 0 || nx >= M)
                continue;
              if (visited[ny][nx])
                continue;
              if (map[ny][nx] == 1)
                continue;

              dist[ny][nx] = Math.min(now[2], dist[ny][nx]);
              visited[ny][nx] = true;
              q.add(new int[] { ny, nx, now[2]+1 });
            }
          }
          
        }
      }
    }

    int answer = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        // System.out.print(dist[i][j] + " ");
        answer = Math.max(answer, dist[i][j]);
      }
      // System.out.println();
    }
    System.out.println(answer);
  }
}