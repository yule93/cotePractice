package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 17406 배열돌리기 4(시뮬레이션, DFS)
public class boj17406 {
  static int N, M, K, min;
  static int[][] arr, rotationInfo;
  static boolean[] visited;
  static int[] per;
  static int[] dr = { 1, 0, -1, 0 };
  static int[] dc = { 0, 1, 0, -1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 세로
    M = Integer.parseInt(st.nextToken()); // 가로
    K = Integer.parseInt(st.nextToken()); // 각기 다른 회전의 횟수

    visited = new boolean[K];
    per = new int[K];
    arr = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // ! 회전 연산은 세 정수 (r, c, s)로 이루어져 있고, 가장 왼쪽 윗 칸이 (r-s, c-s), 가장 오른쪽 아랫 칸이 (r+s, c+s)인 정사각형을
    // ! 시계 방향으로 한 칸씩 돌린다는 의미이다.
    // ! 배열의 칸 (r, c)는 r행 c열을 의미한다.
    rotationInfo = new int[K][3];
    for (int k = 0; k < K; k++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        rotationInfo[k][j] = Integer.parseInt(st.nextToken());
      }
    } // end of rotation info init

    min = 123_456_789;
    // 배열 돌리는 순서를 순열로 정하기 위해 dfs 사용
    dfs(0);

    System.out.println(min);
  }
  
  // ! dfs 함수
  public static void dfs(int depth) {
    if (depth == K) {
      // * 회전 해주고
      int[][] copy = new int[N][M];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          copy[i][j] = arr[i][j];
        }
      }

      for (int roInfo = 0; roInfo < K; roInfo++) {
        // r은 순열 번째수
        int nr = rotationInfo[roInfo][0];
        int nc = rotationInfo[roInfo][1];
        int ns = rotationInfo[roInfo][2];

        for (int count = 0; count < ns; count++) {
          int r = nr - ns - 1 + count;
          int c = nc - ns - 1 + count;
          int start = copy[r][c];

          int d = 0; // 회전 총 4번 할 거임
          while (d < 4) {
            int nextR = r + dr[d];
            int nextC = c + dc[d];
            if (nr - ns - 1 + count > nextR || nc - ns - 1 + count > nextC || nr + ns - count <= nextR
                || nc + ns - count <= nextC) {
              d++;
              continue;
            }
            copy[r][c] = copy[nextR][nextC];
            r = nextR;
            c = nextC;
          }
          copy[nr - ns - 1 + count][nc - ns + count] = start;
        }

        // System.out.println();
        // for (int i = 0; i < N; i++) {
        //   for (int j = 0; j < M; j++) {
        //     System.out.print(copy[i][j] + " ");
        //   }
        //   System.out.println();
        // }

      }
      // * 각 행의 최소값을 다 더한 값을 구하면 탈출
      int res = 1_000_000;
      for (int i = 0; i < N; i++) {
        int val = 0;
        for (int j = 0; j < M; j++) {
          val += copy[i][j];
        }
        res = Math.min(res, val);
      }

      // System.out.println();
      // for (int i = 0; i < N; i++) {
      //   for (int j = 0; j < M; j++) {
      //     System.out.print(copy[i][j] + " ");
      //   }
      //   System.out.println();
      // }

      min = Math.min(min, res);
      return;
    }

    for (int i = 0; i < K; i++) {
      if (visited[i])
        continue;
      per[depth] = i;
      visited[i] = true;
      dfs(depth + 1);
      visited[i] = false;
    }
  }
}
/*
5 6 2
1 2 3 2 5 6
3 8 7 2 1 3
8 2 3 1 4 5
3 4 5 1 1 1
9 3 2 1 4 3
3 4 2
4 2 1
 */