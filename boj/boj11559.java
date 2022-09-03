package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// boj 11559 Puyo Puyo (bfs, 시뮬레이션)
// ! '연쇄'가 무엇인지 조건을 제대로 보지 않아서 틀리고 있었음....
// 120ms
public class boj11559 {

  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = {0, 0, -1, 1};
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // ? 총 12개의 줄에 맵 정보가 주어진다. N = 12, M = 6
    int N = 12, M = 6;
    char[][] map = new char[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = str.charAt(j);
      }
    }

    int answer = 0;
    Queue<int[]> q = new LinkedList<>();
    while (true) {
      int count = 0; // * 1그룹 이상 터지면 계속 진행

      // * 1. bfs로 터질 그룹이 몇 개인지 세고 터트려주기
      boolean[][] checked = new boolean[N][M]; // * 방문한 맵
      boolean[][] pop = new boolean[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (map[i][j] != '.' && !checked[i][j]) {
            pop = new boolean[N][M];
            int pc = 1;
            checked[i][j] = true;
            pop[i][j] = true;
            q.add(new int[] { i, j });
            while (q.size() > 0) {
              int[] now = q.poll();
              for (int d = 0; d < 4; d++) {
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M || checked[ny][nx])
                  continue;

                if (map[now[0]][now[1]] == map[ny][nx]) {
                  pc++;
                  checked[ny][nx] = true;
                  pop[ny][nx] = true;
                  q.add(new int[] { ny, nx });
                }
              }
            }

          //   for (int r = 0; r < N; r++) {
          //     for (int c = 0; c < M; c++) {
          //       System.out.print(pop[r][c] == true ? 1 + " " : 0 + " ");
          //     }
          //     System.out.println();
          //   }
          // System.out.println();

            if (pc >= 4) {
              for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                  if (pop[r][c])
                    map[r][c] = '.';
                }
              }
              count++;
            }
          }
        }
      }

      if (count == 0)
        break;

      answer++;

      // * 2. 내릴 뿌요들 내려주기
      for (int i = 0; i < M; i++) {
        for (int j = N-1; j > 0; j--) {
          if (map[j][i] == '.') {
            for (int k = j; k >= 0; k--) {
              if (map[k][i] != '.') {
                map[j][i] = map[k][i];
                map[k][i] = '.';
                break;
              }
            }
          }
        }
      }
      // for (int i = 0; i < N; i++) {
      //   for (int j = 0; j < M; j++) {
      //     System.out.print(map[i][j] + " ");
      //   }
      //   System.out.println();
      // }
      
    }
    
    System.out.println(answer);
  }
}