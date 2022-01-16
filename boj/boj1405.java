package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 1405 미친 로봇(dfs, 백트래킹)
// * 전형적인 dfs 문제이나 문제에서 가리키는 단순한 이동이 뭔지 파악하느라 오래걸림....
// 176ms
public class boj1405 {
  static boolean[][] visited = new boolean[30][30];
  static int N;
  static double[] prob = new double[4];
  static double check;
  static int[] dy = { 0, 0, 1, -1 }; // 동서남북 순으로 저장
  static int[] dx = { 1, -1, 0, 0 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    // 사방향으로 갈 확률을 저장하는 배열
    for (int i = 0; i < 4; i++)
      prob[i] = Double.parseDouble(st.nextToken()) / 100.0;

    visited[15][15] = true; // 시작지점
    dfs(15, 15, 1.0, 0);

    // !조심해야 하는 건 답이 실수의 범위에 있기 때문에 float double과 같은 타입을 사용해줘야 함
    System.out.println(check);
  }

  public static void dfs(int nr, int nc, double mul, int move) {
    if (move == N) {
      // 이동 횟수가 N이 되면 탐색 끝
      check += mul;
      return;
    }

    for (int i = 0; i < 4; i++) {
      int mr = nr + dy[i];
      int mc = nc + dx[i];

      if (!visited[mr][mc] && prob[i] > 0) {
        visited[mr][mc] = true;
        dfs(mr, mc, mul * prob[i], move + 1);
        visited[mr][mc] = false;
      }
    }
  }
}
