package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 17143 낚시왕(시뮬레이션)
// * 최대 N(R*C*s) = 1000만회여서 ㄱㅊ을거 같음
public class boj17143_2 {

  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, 1, -1 };

  static class Shark {
    int r, c, s, d, z;
    int i;

    public Shark(int r, int c, int s, int d, int z, int i) {
      this.r = r;
      this.c = c;
      this.s = s;
      this.d = d;
      this.z = z;
      this.i = i;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder(
          "info: " + this.r + ", " + this.c + ", " + this.s + ", " + this.d + ", " + this.z);
      return sb.toString();
    }
  }

  static int R, C, M;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken()); // ? 격자의 세로
    C = Integer.parseInt(st.nextToken()); // ? 격자의 가로
    M = Integer.parseInt(st.nextToken()); // ? 상어의 수

    if (M == 0) {
      System.out.println(0);
      return;
    }

    // * 1. 낚시왕 오른쪽 이동
    // * 2. 낚시왕이 있는 열의 상어 중 땅과 제일 가까운 상어를 잡음. 그럼 상어는 격자에서 사라짐
    // * 3. 상어 이동(이 때, 크기가 가장 큰 상어가 나머지를 모두 잡아먹음. 즉, 큰 상어가 오면 나머지 그냥 덮어 씌우면 됨)

    Shark[][] map = new Shark[R][C];
    Shark[] list = new Shark[M];
    for (int i = 1; i <= M; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()); // ? 상어 위치
      int c = Integer.parseInt(st.nextToken()); // ? 상어 위치
      int s = Integer.parseInt(st.nextToken()); // ? 상어 스피드
      int d = Integer.parseInt(st.nextToken()); // ? 상어 이동 방향
      int z = Integer.parseInt(st.nextToken()); // ? 상어 크기

      map[r - 1][c - 1] = new Shark(r - 1, c - 1, s, d - 1, z, i-1); // ? 상어 저장
      list[i - 1] = map[r - 1][c - 1];
    }

    int count = 0;
    int index = 0;
    while (index < C) {
      // * 1. 낚시~

      for (int r = 0; r < R; r++) {
        if (map[r][index] != null) {
          count += map[r][index].z;
          list[map[r][index].i] = null;
          map[r][index] = null;
          break;
        }
        continue;
      }

      // * 2. 이동
      for (int i = 0; i < M; i++) {
        if (list[i] == null)
          continue;

        Shark sh = list[i];
        map[sh.r][sh.c] = null;

        int ny = sh.r;
        int nx = sh.c;
        int nd = sh.d;

        for (int move = 0; move < sh.s; move++) {
          ny += dy[nd];
          nx += dx[nd];
          if (ny < 0) {
            ny = 1;
            nd = 1;
          } else if (ny >= R) {
            ny = R - 2;
            nd = 0;
          } else if (nx < 0) {
            nx = 1;
            nd = 2;
          } else if (nx >= C) {
            nx = C - 2;
            nd = 3;
          }
        }

        list[i] = new Shark(ny, nx, sh.s, nd, sh.z, sh.i);
      }

      // * 2-1. 이동 후 잡아먹기
      for (int i = 0; i < M; i++) {
        if (list[i] == null)
          continue;

        Shark sh = list[i];
        if (map[sh.r][sh.c] == null || map[sh.r][sh.c].z < sh.z) {
          map[sh.r][sh.c] = sh;
        }
      }
      
      // for (int i = 0; i < R; i++) {
      //   for (int j = 0; j < C; j++) {
      //     System.out.print(map[i][j] == null ? "0 " : map[i][j].z + " ");
      //   }
      //   System.out.println();
      // }
      // System.out.println();

      index++;
    }

    System.out.println(count);
  }
}