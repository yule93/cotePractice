package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// SWEA 2382 미생물 격리(BFS, 시뮬레이션)
// 2071ms
public class swea2382 {
  public static int swapdir(int i) {
    switch (i) {
      case 1:
        return 2;
      case 2:
        return 1;
      case 3:
        return 4;
      case 4:
        return 3;
    }
    return -1;
  }

  public static int[][] dir = {
    { 0, 0 },
    { -1, 0 }, // 상
    { 1, 0 }, // 하
    { 0, -1 }, // 좌
    { 0, 1 } // 우
  };
  public static int[][][] n_map;

  public static void main(String args[]) throws Exception {
    // System.setIn(new FileInputStream("res/swea/2382.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T;
    T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      int ans = 0;
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      n_map = new int[N][N][5];
      int M = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      int[][] oper = new int[K][5]; // {r, c, 미생물 수, 이동방향, 합쳐진max 미생물수}
      LinkedList<int[]> rc = new LinkedList<>();

      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 4; j++) {
          oper[i][j] = Integer.parseInt(st.nextToken());
        }
        oper[i][4] = oper[i][2];
        // {r, c, 미생물 수, 이동방향, 합쳐진max 미생물수}
        rc.add(new int[] { oper[i][0], oper[i][1], oper[i][2], oper[i][3], oper[i][4] });
      }

      int lsize = 0;
      int[] now;
      int nr = 0;
      int nc = 0;
      for (int time = 0; time < M; time++) {
        // n_map에 이동한 것 표시 + 세포위치 list에 넣음
        lsize = rc.size();
        for (int i = 0; i < lsize; i++) {
          now = rc.removeFirst(); // {r, c, 미생물 수, 이동방향, 합쳐진max 미생물수}
          nr = now[0] + dir[now[3]][0];
          nc = now[1] + dir[now[3]][1];
          // 경계로 갔음
          if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
            // 방향 바꾸고, 미생물 수 반으로 준다
            n_map[nr][nc] = new int[] { nr, nc, now[2] / 2, swapdir(now[3]), now[2] / 2 };
            rc.add(new int[] { nr, nc });
          }
          // 이동칸에 아무도없음
          else if (n_map[nr][nc][4] == 0) {
            n_map[nr][nc] = new int[] { nr, nc, now[2], now[3], now[2] };
            rc.add(new int[] { nr, nc });
          }
          // 이동칸에 미생물 있음
          else {
            // 미생물 수가 큰게 들어왔으면
            if (n_map[nr][nc][4] < now[4]) {
              // 미생물수 합치고
              n_map[nr][nc][2] += now[2];
              // 방향바꿈
              n_map[nr][nc][3] = now[3];
              n_map[nr][nc][4] = now[4];
            }
            // 미생물 수가 작은게 들어왔다
            else {
              // 미생물수 합치기
              n_map[nr][nc][2] += now[2];
            }
          }
        }
        // n_map 기준으로 세포 위치 및 정보들 list에 넣음
        lsize = rc.size();
        for (int i = 0; i < lsize; i++) {
          now = rc.removeFirst();
          // {r, c, 미생물 수, 이동방향, 합쳐진max 미생물수}
          rc.add(new int[] { now[0],
              now[1],
              n_map[now[0]][now[1]][2],
              n_map[now[0]][now[1]][3],
              n_map[now[0]][now[1]][2] });
        }

        // n_map 초기화
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            for (int k = 0; k < 5; k++) {
              n_map[i][j][k] = 0;
            }
          }
        }
      }

      lsize = rc.size();
      for (int i = 0; i < lsize; i++) {
        ans += rc.get(i)[2];
      }

      System.out.println("#" + test_case + " " + ans);
    }
  }
}
