package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// boj 15683 감시(구현, 순열)
// 336ms
public class boj15683 {
  public static class Pair {
    int y, x, cctv, d;

    Pair(int y, int x, int cctv, int d) {
      this.y = y;
      this.x = x;
      this.cctv = cctv;
      this.d = d;
    }
  }

  static int N, M, k, ans = Integer.MAX_VALUE;
  static ArrayList<Pair> cList;
  static int[][] map;
  static int[][] temp;
  static int[] dy = { -1, 0, 1, 0 };
  static int[] dx = { 0, -1, 0, 1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    temp = new int[N][M];
    cList = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (1 <= map[i][j] && map[i][j] <= 5) {
          // ? cctv일 때,
          cList.add(new Pair(i, j, map[i][j], 0));
        }
      }
    }
    k = cList.size();

    dfs(0);
    System.out.println(ans);
  }

  public static void calculate() {
    int cnt = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (temp[i][j] == 0)
          cnt += 1;
      }
    }
    ans = Math.min(ans, cnt);
    // for (int i = 0; i < N; i++) {
    //   for (int j = 0; j < M; j++) {
    //     System.out.print(temp[i][j] + " ");
    //   }
    //   System.out.println();
    // }
    // System.out.println();
  }

  public static void fillArr(int nx, int ny, int d) {
    // cctv 감시하는 부분을 7로 표현
    while (-1 < nx && nx < N && -1 < ny && ny < M && map[nx][ny] != 6) {
      temp[nx][ny] = 7;
      nx += dy[d];
      ny += dx[d];
    }
  }

  public static void monitor() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        temp[i][j] = map[i][j];
      }
    }
    for (Pair p : cList) {
      // cctv 종류별로 회전을 다르게 해줌
      int nx = p.y, ny = p.x, d = p.d;
      switch (p.cctv) {
        case 1:
          fillArr(nx, ny, d);
          break;
        case 2:
          fillArr(nx, ny, d);
          nx = p.y;
          ny = p.x;
          d = (d + 2) % 4;
          fillArr(nx, ny, d);
          break;
        case 3:
          for (int i = 0; i < 2; i++) {
            nx = p.y;
            ny = p.x;
            d = (d + i) % 4;
            fillArr(nx, ny, d);
          }
          break;
        case 4:
          for (int i = 0; i < 3; i++) {
            nx = p.y;
            ny = p.x;
            d = (d + i) % 4;
            fillArr(nx, ny, d);
          }
          break;
        case 5:
          for (int i = 0; i < 4; i++) {
            nx = p.y;
            ny = p.x;
            d = (d + i) % 4;
            fillArr(nx, ny, d);
          }
          break;
      }
    }
  }

  public static void dfs(int depth) {
    if (depth == k) {
      monitor();
      calculate();
      return;
    }

    Pair p = cList.get(depth);
    switch (p.cctv) {
      case 2:
        p.d = 0;
        dfs(depth + 1);
        p.d = 1;
        dfs(depth + 1);
        break;
      case 5:
        p.d = 0;
        dfs(depth + 1);
        break;
      default:
        for (int d = 0; d < 4; d++) {
          p.d = d;
          dfs(depth + 1);
        }
        break;
    }
  }

}