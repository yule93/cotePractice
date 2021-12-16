package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 17281 야구 (DFS, 시뮬레이션)
public class boj17281 {

  static int[] perm;
  static boolean[] visited;
  static int answer, N;
  static int[][] round;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    round = new int[N][9];
    for (int i = 0; i < N; i++) {
      String str = br.readLine().replaceAll(" ", "");
      for (int j = 0; j < 9; j++) {
        round[i][j] = str.charAt(j) - '0';
      }
    }

    answer = 0;
    perm = new int[9];
    visited = new boolean[9];

    visited[0] = true;
    perm[3] = 0;
    dfs(0);

    System.out.println(answer);
  }

  public static void dfs(int depth) {
    if (depth == 3) {
      dfs(depth + 1);
      return;
    }
    if (depth == 9) {
      // for (int i = 0; i < 9; i++) {
      //   System.out.print(perm[i] + " ");
      // }
      // System.out.println();
      simulation();
      return;
    }

    for (int i = 1; i < 9; i++) {
      if (visited[i])
        continue;
      perm[depth] = i;
      visited[i] = true;
      dfs(depth + 1);
      visited[i] = false;
    }
  }

  public static void simulation() {
    // 번호별 선수 고정해두고 시뮬레이션 시작
    int score = 0;
    int last = 0;
    boolean[] base; // 홈, 1루, 2루, 3루

    for (int i = 0; i < N; i++) {
      // N 이닝 실행
      int out = 0;
      base = new boolean[4];
      while (true) {
        int now = round[i][perm[last]];
        if (last == 8)
          last = 0;
        else
          last++;
        if (now == 1) {
          // 안타침(1루씩)
          if (base[3]) {
            score++;
            base[3] = false;
          }
          for (int r = 2; r >= 1; r--) {
            if (base[r]) {
              base[r] = false;
              base[r + 1] = true;
            }
          }
          base[1] = true;
        } else if (now == 2) {
          // 2루타
          if (base[3]) {
            score++;
            base[3] = false;
          }
          if (base[2]) {
            score++;
            base[2] = false;
          }
          if (base[1]) {
            base[1] = false;
            base[3] = true;
          }
          base[2] = true;
        } else if (now == 3) {
          // 3루타
          for (int r = 1; r < 4; r++) {
            if (base[r]) {
              score++;
              base[r] = false;
            }
          }
          base[3] = true;
        } else if (now == 4) {
          // 홈~~~ㅓㄹㄴ~~
          for (int r = 1; r < 4; r++) {
            if (base[r]) {
              score++;
              base[r] = false;
            }
          }
          score++;
        } else {
          // 야 야구 그따구로 할래
          out++;
          if (out == 3) {
            break;
          }
        }
      }
    }
    answer = Math.max(answer, score);
  }
}
