package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 17143 낚시 왕 (시뮬레이션)
// * 완전탐색은 아니고 나온 문제 그대로 구현하는 문제
// * 방향 전환 탐색을 잘 하면 되는 문제인데... 왜 내가 작성한 거 물고기가 중간에 사라지는지 모르겠음
// * 방향 전환이 잘못돼서 그런가,..,..,
public class boj17143_1 {
  static int R, C, M, sharkMap[][], king;
  static Shark[] sharks;
  static int[] dr = { -1, 0, 0, 1 }, dc = { 0, -1, 1, 0 }; // 상, 좌, 우, 하

  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken()); // 세로
    C = Integer.parseInt(st.nextToken()); // 가로
    M = Integer.parseInt(st.nextToken()); // 상어의 수
    sharkMap = new int[R + 1][C + 1];
    sharks = new Shark[M + 1];

    for (int i = 1; i <= M; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken()); // 속력
      int d = Integer.parseInt(st.nextToken()); // 이동방향
      int z = Integer.parseInt(st.nextToken()); // 크기
      // 초기 방향 설정
      if (d == 1)
        d = 0;
      else if (d == 2)
        d = 3;
      else if (d == 3)
        d = 2;
      else
        d = 1;

      sharkMap[r][c] = i;
      sharks[i] = new Shark(r, c, s, d, z);
    }

    System.out.println(process());
  }

  private static int process() {
    int res = 0;
    // 1. 낚시왕이 오른쪽으로 한 칸 이동
    while (king++ < C) {
      // 2. 낚시왕이 상어를 잡는다.
      res += fishing();
      // 3. 상어가 이동
      move();
    }
    return res;
  }

  private static void move() {
    sharkMap = new int[R + 1][C + 1];
    for (int i = 1; i <= M; i++) {
      // 이미 잡힌 상어는 pass
      if (sharks[i] == null)
        continue;

      Shark now = sharks[i];
      int turn = 1, r = now.r, c = now.c;
      for (int m = 0; m < now.s; m++) {
        r += turn * dr[now.d];
        c += turn * dc[now.d];
        // 범위를 벗어날 경우
        if (r < 1 || r > R || c < 1 || c > C) {
          // 방향을 반대로 하고
          turn *= -1;
          // 이전 칸으로 이동
          r += (turn * dr[now.d]) * 2;
          c += (turn * dc[now.d]) * 2;
        }
      }

      // 상어의 방향이 바뀐 상태라면
      if (turn == -1) {
        sharks[i].d = 3 - sharks[i].d;
      }

      // 이동한 칸에 다른 상어가 있을 경우
      if (sharkMap[r][c] > 0) {
        // 현재 상어보다 더 큰 상어일 경우 잡아먹힌다..
        if (sharks[sharkMap[r][c]].z > now.z)
          sharks[i] = null;
        // 현재 상어가 더 클 경우
        else {
          sharks[i].r = r;
          sharks[i].c = c;
          // 해당 칸에 있던 상어를 잡아먹자.
          sharks[sharkMap[r][c]] = null;
          sharkMap[r][c] = i;
        }
      }
      // 이동한 칸이 비었을 경우
      else {
        sharks[i].r = r;
        sharks[i].c = c;
        sharkMap[r][c] = i;
      }
    }
  }

  private static int fishing() {
    // 낚시왕이 있는 열에 있는 상어 중에서
    for (int i = 1; i <= R; i++) {
      // 땅과 제일 가까운 상어를 잡자.
      if (sharkMap[i][king] > 0) {
        int size = sharks[sharkMap[i][king]].z;
        // 잡힌 상어의 정보는 삭제
        sharks[sharkMap[i][king]] = null;
        sharkMap[i][king] = 0;
        return size;
      }
    }
    return 0;
  }

  static class Shark {
    // 세로, 가로, 속력, 이동방향, 크기
    int r, c, s, d, z;

    public Shark(int r, int c, int s, int d, int z) {
      this.r = r;
      this.c = c;
      this.s = s;
      this.d = d;
      this.z = z;
    }
  }
}
