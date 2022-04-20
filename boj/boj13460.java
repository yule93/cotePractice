package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 13460 구슬 탈출 2 (시뮬레이션, bfs, 백트래킹)
// boj 16197 두 동전과 비슷한 풀이로 진행됨
// 132ms....
// * 98번째 continue를 어디로 할지 헤매지 않았으면 해결 금방 했을 문제.... inloop로 해야 사방탐색이 가능한데 loop로 넘겨줘서 사방 탐색을 못했음....
public class boj13460 {
  static int[] dy = { 0, 0, 1, -1 }; // 좌우상하
  static int[] dx = { 1, -1, 0, 0 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] marblePos = new int[4];
    int[] hole = new int[2];
    char[][] map = new char[N][M];
    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = line.charAt(j);
        if (map[i][j] == 'R') {
          marblePos[0] = i;
          marblePos[1] = j;
          map[i][j] = '.';
        } else if (map[i][j] == 'B') {
          marblePos[2] = i;
          marblePos[3] = j;
          map[i][j] = '.';
        } else if (map[i][j] == 'O') {
          hole[0] = i;
          hole[1] = j;
        }
      }
    }

    int answer = 11;
    boolean[][][][] visited = new boolean[N][M][N][M];
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { marblePos[0], marblePos[1], marblePos[2], marblePos[3], 0 }); // * 맨 마지막 요소는 구슬이 이동한 횟수
    loop: while (q.size() > 0) {
      int[] nowPos = q.poll();
      // System.out.println(nowPos[0] + ", " + nowPos[1] + ", " + nowPos[2] + ", " + nowPos[3]+ ", " + nowPos[4]);
      if (nowPos[4] > 10 || nowPos[4] >= answer)
        continue;
      if (nowPos[4] + 1 > answer)
        continue;

      inloop:
      for (int i = 0; i < 4; i++) {
        // 구슬 이동
        int rny = nowPos[0];
        int rnx = nowPos[1];
        int bny = nowPos[2];
        int bnx = nowPos[3];
        // System.out.println(rny + ", "+ rnx+" / "+bny+", "+bnx+": "+nowPos[4]);

        int redC = 0; // 빨간 구슬이 움직인 거리
        int blueC = 0; // 파란 구슬이 움직인 거리
        while (true) {
          rny += dy[i];
          rnx += dx[i];
          if (map[rny][rnx] == '#') {
            rny -= dy[i];
            rnx -= dx[i];
            break;
          }
          if (map[rny][rnx] == 'O') {
            int res = 11;
            if (answer < nowPos[4] + 1)
              break loop;
            else {
              res = nowPos[4] + 1;
            }
            while (true) {
              bny += dy[i];
              bnx += dx[i];
              if (map[bny][bnx] == '#') {
                bny -= dy[i];
                bnx -= dx[i];
                break;
              }
              if (map[bny][bnx] == 'O') {
                res = 11;
                break;
              }
            }
            answer = Math.min(answer, res);
            // System.out.println(rny + ", "+ rnx+" / "+bny+", "+bnx+": "+answer);
            continue inloop;
          }
          redC++;
        }

        while (true) {
          bny += dy[i];
          bnx += dx[i];
          if (map[bny][bnx] == '#') {
            bny -= dy[i];
            bnx -= dx[i];
            break;
          }
          if (map[bny][bnx] == 'O') {
            // answer = 11;
            continue inloop;
          }
          blueC++;
        }

        if (rny == bny && rnx == bnx) {
          // * 둘이 같은 위치에 도착했다면, 더 많이 움직인 쪽이 뒤쪽에 위치한 것이므로 한 바퀴 덜 감아야 함
          if (redC > blueC) {
            rny -= dy[i];
            rnx -= dx[i];
          } else if (blueC > redC) {
            bny -= dy[i];
            bnx -= dx[i];
          }
        }

        if (nowPos[2] == hole[0] && nowPos[3] == hole[1]) {
          continue;
        }
        if (rnx < 0 || rnx >= M || rny < 0 || rny >= N
            || bnx < 0 || bnx >= M || bny < 0 || bny >= N)
          continue;

        if (visited[rny][rnx][bny][bnx])
          continue;


        visited[rny][rnx][bny][bnx] = true;
        q.add(new int[] { rny, rnx, bny, bnx, nowPos[4] + 1 });
      }
    }

    System.out.println(answer == 11 ? -1 : answer);
  }
}

/*
* 틀린 tc
9 6
######
##.#.#
#.#.##
#..#.#
#B..##
#R#.##
#.##.#
#O.###
######
답: 2

10 9
#########
#R...B#.#
#.....#.#
#.......#
##.#....#
#....#..#
#.#....O#
##.....##
#...##..#
#########
답: 4

6 9
#########
#..#...R#
#..##.#.#
#.###OB.#
####.#..#
#########
답: 3

 */