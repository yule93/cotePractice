package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 17822 원판 돌리기(시뮬레이션, bfs)
// 248ms
public class boj17822 {
  static int N, M, T;
  static int[][] arr;
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());
    arr = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int t = 0; t < T; t++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      /*
       * 1. 번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
       * 2. 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다.
       * 1) 그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
       * 2) 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
       */

      rotate(x, d, k);
      if (!remove())
        replace();

    }

    int answer = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (arr[i][j] != -1)
          answer += arr[i][j];
      }
    }

    System.out.println(answer);
  }

  // ? 2-2 과정: 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
  private static void replace() {
    int sum = 0, cnt = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (arr[i][j] != -1) {
          sum += arr[i][j];
          cnt++;
        }
      }
    }

    double avg = sum / (double) cnt;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (arr[i][j] != -1) {
          if ((double) arr[i][j] > avg)
            arr[i][j]--;
          else if ((double) arr[i][j] < avg)
            arr[i][j]++;
        }
      }
    }
  }

  // ? 2-1 과정: 원판에서 인접하면서 같은 수를 모두 지운다.
  private static boolean remove() {
    boolean flag = false;
    boolean[][] visited = new boolean[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (arr[i][j] == -1 || visited[i][j])
          continue;

        // ! bfs로 같은 숫자 탐색
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { i, j, arr[i][j] });
        visited[i][j] = true;
        while (q.size() > 0) {
          int[] now = q.poll();
          for (int k = 0; k < dy.length; k++) {
            int ny = now[0] + dy[k];
            int nx = now[1] + dx[k];

            if (ny < 0 || ny >= N)
              continue;

            if (nx == -1)
              nx = M - 1;
            else if (nx == M)
              nx = 0;
            if (visited[ny][nx])
              continue;
            if (arr[ny][nx] == now[2]) {
              visited[ny][nx] = true;
              q.add(new int[] { ny, nx, now[2] });
              arr[now[0]][now[1]] = -1;
              arr[ny][nx] = -1;
              flag = true;
            }
          }
        }
      }
    }
    return flag;
  }

  // ? 원판 회애전회오리
  private static void rotate(int x, int d, int k) {
    for (int r = 0; r < N; r++) {
      if ((r + 1) % x != 0)
        continue;
      if (d == 0) {
        // * 시계방향 회전
        for (int times = 0; times < k; times++) {
          int[] tmp = arr[r].clone();
          arr[r][0] = tmp[M - 1];
          for (int i = 1; i < M; i++) {
            arr[r][i] = tmp[i - 1];
          }
        }
      } else {
        // * 반시계 방향 회전
        for (int times = 0; times < k; times++) {
          int[] tmp = arr[r].clone();
          arr[r][M - 1] = tmp[0];
          for (int i = 0; i < M - 1; i++) {
            arr[r][i] = tmp[i + 1];
          }
        }
      }
    }
  }
}
