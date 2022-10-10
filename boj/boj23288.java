package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 23288 주사위 굴리기2(bfs, 구현)
// 236ms
// 주사위가 구를 때 어떻게 변하는지 바꿔주는 게 힘들었던 문제
public class boj23288 {

  static int[] dy = { 0, 1, 0, -1 }; // ? 시계방향으로 맨 첨 시작 방향인 동부터 남 서 북 순
  static int[] dx = { 1, 0, -1, 0 };
  static int N, M, K;
  static int[][] map;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    // ? 세로가로 크기 N, M, 이동 횟수 K
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine().replaceAll(" ", "");
      for (int j = 0; j < M; j++) {
        map[i][j] = str.charAt(j) - '0';
      }
    }

    int[] dice = { 1, 2, 3, 4, 5, 6 }; // ? 주사위 전개도 처음 상태
    int score = 0, rotate_count = 1;

    // * 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x, y)에 있는 정수 B를 비교해 이동 방향을 결정한다.
    // * A > B인 경우 이동 방향을 90도 시계 방향으로 회전시킨다.
    // * A < B인 경우 이동 방향을 90도 반시계 방향으로 회전시킨다.
    // * A = B인 경우 이동 방향에 변화는 없다.
    int direct = 0;
    int ny = 0, nx = 0;
    while (rotate_count++ <= K) {
      ny += dy[direct];
      nx += dx[direct];

      // * 이동할 수 없는 곳으로 갔다면
      if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
        ny -= dy[direct];
        nx -= dx[direct];

        direct = (direct + 2) % 4;
        ny += dy[direct];
        nx += dx[direct];
      }

      if (direct == 0) {
        // * 동쪽 이동
        int num = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[5];
        dice[5] = dice[2];
        dice[2] = num;
      } else if (direct == 1) {
        // * 남쪽 이동
        int num = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[4];
        dice[4] = num;
      } else if (direct == 2) {
        // * 서쪽 이동
        int num = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[5];
        dice[5] = dice[3];
        dice[3] = num;
      } else {
        // * 북쪽 이동
        int num = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[5];
        dice[5] = dice[1];
        dice[1] = num;
      }
      int count = bfs(ny, nx);
      score += (count * map[ny][nx]);

      if (map[ny][nx] < dice[5]) {
        // * 1. 주사위 바닥 값이 더 클 때,
        direct = (direct + 1) % 4;
      } else if (map[ny][nx] > dice[5]) {
        // * 2. 주사위 바닥 값이 작을 때,
        direct = (direct - 1) < 0 ? 3 : direct - 1;
      }
    }

    System.out.println(score);
  }

  public static int bfs(int ny, int nx) {
    boolean[][] visited = new boolean[N][M];
    int cnt = 1;
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[] { ny, nx });
    visited[ny][nx] = true;
    while (q.size() > 0) {
      int[] now = q.poll();
      for (int d = 0; d < 4; d++) {
        int cy = now[0] + dy[d];
        int cx = now[1] + dx[d];

        if (cy < 0 || cy >= N || cx < 0 || cx >= M)
          continue;
        if (!visited[cy][cx] && map[cy][cx] == map[ny][nx]) {
          visited[cy][cx] = true;
          cnt++;
          q.offer(new int[] { cy, cx });
        }
      }
    }
    return cnt;
  }
}
