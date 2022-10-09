package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// boj 17837 새로운 게임2
public class boj17837 {

  static ArrayList<Integer>[][] order = new ArrayList[12][12];
  static int[] dy = { 0, 0, -1, 1 };
  static int[] dx = { 1, -1, 0, 0 };

  public static class Piece {
    int y, x, d;

    public Piece(int y, int x, int d) {
      this.y = y;
      this.x = x;
      this.d = d;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    // ? 0은 흰색, 1은 빨간색(도착한 말탑 뒤집기), 2는 파란색(방향 반대로 바꾸고 한 칸 이동)
    int[][] map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < 12; ++i)
      for (int j = 0; j < 12; ++j)
        order[i][j] = new ArrayList<>();

    ArrayList<Piece> chessList = new ArrayList<>();
    for (int k = 0; k < K; k++) {
      st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken()) - 1;
      int x = Integer.parseInt(st.nextToken()) - 1;
      int d = Integer.parseInt(st.nextToken()) - 1;
      chessList.add(new Piece(y, x, d));
      order[y][x].add(k); // * 체스 말이 쌓인 순서 저장용
    }

    int turn = 0;
    while (true) {
      // * 턴이 1000번보다 많아지거나 절대로 게임이 종료되지 않으면 -1을 출력함.
      turn++;
      if (turn > 1000)
        break;

      for (int i = 0; i < K; ++i) {
        int y = chessList.get(i).y;
        int x = chessList.get(i).x;

        int ny = y + dy[chessList.get(i).d];
        int nx = x + dx[chessList.get(i).d];

        if (!(0 <= ny && ny < N && 0 <= nx && nx < N) || map[ny][nx] == 2) {
          if (chessList.get(i).d == 0)
            chessList.get(i).d = 1;
          else if (chessList.get(i).d == 1)
            chessList.get(i).d = 0;
          else if (chessList.get(i).d == 2)
            chessList.get(i).d = 3;
          else if (chessList.get(i).d == 3)
            chessList.get(i).d = 2;
          ny = y + dy[chessList.get(i).d];
          nx = x + dx[chessList.get(i).d];
          continue;
        }

        else if (map[ny][nx] == 0) {
          int idx = -1;
          for (int j = 0; j < order[y][x].size(); ++j) {
            int chessNum = order[y][x].get(j);

            if (chessNum == i)
              idx = j;
            if (idx == -1)
              continue;

            chessList.get(chessNum).y = ny;
            chessList.get(chessNum).x = nx;
            order[ny][nx].add(chessNum);
            if (order[ny][nx].size() >= 4) {
              System.out.println(turn);
              System.exit(0);
            }
          }
          int cnt = order[y][x].size();
          for (int j = idx; j < cnt; ++j)
            order[y][x].remove(order[y][x].size() - 1);
        } else {
          int idx = -1;
          for (int j = order[y][x].size() - 1; j >= 0; --j) {
            int chessNum = order[y][x].get(j);

            if (chessNum == i) {
              idx = j;
              break;
            }
          }
          for (int j = order[y][x].size() - 1; j >= idx; --j) {
            int chessNum = order[y][x].get(j);

            chessList.get(chessNum).y = ny;
            chessList.get(chessNum).x = nx;
            order[ny][nx].add(chessNum);
            if (order[ny][nx].size() >= 4) {
              System.out.println(turn);
              return;
            }

          }
          int cnt = order[y][x].size();
          for (int j = idx; j < cnt; ++j)
            order[y][x].remove(order[y][x].size() - 1);

        }

      }
    }
    System.out.println(-1);
  }
}
