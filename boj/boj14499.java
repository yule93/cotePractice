package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 14499 주사위 굴리기(시뮬레이션, 그... 주사위 이거 어케야하는거지 ㅎㅎ)
// * 원래는 주사위의 위와 아래를 정하고 좌우로 움직이면 큐에서 넣어주고 빼고 하려고 했는데... 상하로 움직이면 큐의 값이 완전히 초기화돼야 해서 굳이 그럴 필요가 없다고 생각해 규칙을 찾음
// 132ms
public class boj14499 {
  static int[] dx = { 1, -1, 0, 0 };
  static int[] dy = { 0, 0, -1, 1 };
  static int[] dice;
  static int[][] map;
  static int[] cur;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기 1~20
    int M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기 1~20
    int x = Integer.parseInt(st.nextToken()); // 주사위 좌표 x
    int y = Integer.parseInt(st.nextToken()); // 주사위 좌표 y
    int K = Integer.parseInt(st.nextToken()); // 명령어 개수 1~1000

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dice = new int[7];
    cur = new int[] {x, y};
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      int d = Integer.parseInt(st.nextToken()) - 1;
      int ny = cur[0] + dy[d];
      int nx = cur[1] + dx[d];

      if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
        continue;
      }

      switch (d) {
        case 0:
          rollE(new int[] { ny, nx });
          break;
        case 1:
          rollW(new int[] { ny, nx });
          break;
        case 2:
          rollN(new int[] { ny, nx });
          break;
        case 3:
          rollS(new int[] { ny, nx });
          break;
        default:
          break;
      }

      sb.append(dice[1]).append("\n");
    }

    System.out.print(sb.toString());
  }

  public static void rollS(int[] next) {
    int temp = dice[1];
    dice[1] = dice[2];
    dice[2] = dice[6];
    dice[6] = dice[5];
    dice[5] = temp;
    copyVal(next);
  }

  public static void rollN(int[] next) {
    int temp = dice[1];
    dice[1] = dice[5];
    dice[5] = dice[6];
    dice[6] = dice[2];
    dice[2] = temp;
    copyVal(next);
  }

  public static void rollW(int[] next) {
    int temp = dice[1];
    dice[1] = dice[3];
    dice[3] = dice[6];
    dice[6] = dice[4];
    dice[4] = temp;
    copyVal(next);
  }

  public static void rollE(int[] next) {
    int temp = dice[1];
    dice[1] = dice[4];
    dice[4] = dice[6];
    dice[6] = dice[3];
    dice[3] = temp;
    copyVal(next);
  }

  public static void copyVal(int[] next) {
    if (map[next[0]][next[1]] == 0) {
      // * 맵의 숫자가 0이라면 주사위에 있는 숫자를 map에 복사
      map[next[0]][next[1]] = dice[6];
    } else {
      // * 맵의 숫자가 0이 아니라면 주사위에 map의 숫자를 복사
      dice[6] = map[next[0]][next[1]];
      map[next[0]][next[1]] = 0;
    }
    cur = next;
  }
}
