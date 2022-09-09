package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 16234 인구 이동(bfs)
// ! 불필요한 순회를 줄여야하는 게 포인트
// 580ms
public class boj16234 {
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken()); // ? L명 이상
    int R = Integer.parseInt(st.nextToken()); // ? R명 이하

    int[][] map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Queue<int[]> q = new LinkedList<>();
    int day = -1;
    int[][] groupSum = new int[N * N][2];
    while (true) {
      int group = 0;
      boolean flag = false;
      // * 여러 개의 연합이 구성될 수 있으며, 만약 한 개라도 구성이 안 되면 그 때는 인구 이동이 불가하므로 탈출
      day++;
      int[][] visited = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (visited[i][j] == 0) {
            group++;
            q.add(new int[] { i, j });
            while (q.size() > 0) {
              int[] now = q.poll();

              for (int d = 0; d < 4; d++) {
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] != 0)
                  continue;

                if (Math.abs(map[ny][nx] - map[now[0]][now[1]]) >= L
                    && Math.abs(map[ny][nx] - map[now[0]][now[1]]) <= R) {
                  flag = true;
                  visited[ny][nx] = group;
                  groupSum[group - 1][0] += map[ny][nx];
                  groupSum[group - 1][1]++;
                  q.add(new int[] { ny, nx });
                }
              }
            }
          }
        }
      }

      if (!flag)
        break;

      // * 각 연합별로 인구 이동 시작
      else {
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            if (visited[i][j] != 0) {
              map[i][j] = groupSum[visited[i][j] - 1][0] / groupSum[visited[i][j] - 1][1];
            }
          }
        }
      }
    }

    System.out.println(day);
  }
}