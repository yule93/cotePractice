package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 14503 로봇 청소기(dfs, 백트래킹)
// 136ms
class boj14503 {

  static int[][] map;

  // ! d가 0이면 북, 1이면 동, 2이면 남, 3이면 서
  static int[] dy = { -1, 0, 1, 0 };
  static int[] dx = { 0, 1, 0, -1 };

  static int N, M, answer;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int startY = Integer.parseInt(st.nextToken()); // 시작 행
    int startX = Integer.parseInt(st.nextToken()); // 시작 열
    int startD = Integer.parseInt(st.nextToken()); // 시작 방향

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // 한 길로 쭉 파고드는 거니까 dfs
    answer = 1;   // ! 로봇청소기가 제일 먼저 시작하는 곳은 하나 먼저 카운팅 한다.
    dfs(startY, startX, startD);

    System.out.println(answer);
  }

  public static void dfs(int sy, int sx, int sd) {
    map[sy][sx] = 2;
    int nx, ny;
    for (int i = 0; i < 4; i++) {
      // 반시계 방향으로 돌아간다
      sd = (sd + 3) % 4;
      ny = sy + dy[sd];
      nx = sx + dx[sd];

      if (ny >= 0 && ny < N && nx >= 0 && nx < M && map[ny][nx] == 0) {
        answer++;
        dfs(ny, nx, sd);
        return;
      }
    }

    int back = (sd + 2) % 4;
    ny = sy + dy[back];
    nx = sx + dx[back];
    if (ny >= 0 && ny < N && nx >= 0 && nx < M && map[ny][nx] != 1) {
      dfs(ny, nx, sd);
    }
  }
}