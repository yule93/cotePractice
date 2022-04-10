package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 16197 두 동전(bfs)
// ! 동전의 개수는 항상 두 개
// 228ms
public class boj16197_1 {

  static class Node {
    int r, c, t;

    Node(int r, int c, int t) {
      this.r = r;
      this.c = c;
      this.t = t;
    }
  }

  static char[][] map;
  static Queue<Node> q;
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };
  static int N, M;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new char[N][M];
    q = new LinkedList<Node>();

    for (int i = 0; i < N; ++i) {
      String str = br.readLine();
      for (int j = 0; j < M; ++j) {
        map[i][j] = str.charAt(j);
        if (map[i][j] == 'o') {
          q.offer(new Node(i, j, 0));
        }
      }
    }

    System.out.println(bfs());
  }

  private static int bfs() {
    while (q.size() > 0) {
      Node coin1 = q.poll();
      Node coin2 = q.poll();

      if (coin1.t >= 10)
        return -1;

      for (int d = 0; d < 4; ++d) {
        boolean drop1 = false;
        boolean drop2 = false;

        int ny1 = coin1.r + dy[d];
        int nx1 = coin1.c + dx[d];
        int ny2 = coin2.r + dy[d];
        int nx2 = coin2.c + dx[d];

        // 두 동전의 떨어짐을 체크
        if (ny1 >= N || ny1 < 0 || nx1 >= M || nx1 < 0) {
          drop1 = true;
        }
        if (ny2 >= N || ny2 < 0 || nx2 >= M || nx2 < 0) {
          drop2 = true;
        }

        // 두 동전 모두 떨어진 경우
        if (drop1 && drop2)
          continue;
        // 한 동전만 떨어진 경우
        if (drop1 || drop2) {
          return coin1.t + 1;
        }

        // 두 동전 모두 안떨어진 경우
        // 동전이 벽을 만난 경우
        if (map[ny1][nx1] == '#' && map[ny2][nx2] == '#')
          continue;

        if (map[ny1][nx1] == '#') {
          ny1 = coin1.r;
          nx1 = coin1.c;
        }
        if (map[ny2][nx2] == '#') {
          ny2 = coin2.r;
          nx2 = coin2.c;
        }

        q.offer(new Node(ny1, nx1, coin1.t + 1));
        q.offer(new Node(ny2, nx2, coin1.t + 1));
      }
    }
    return -1;
  }
}