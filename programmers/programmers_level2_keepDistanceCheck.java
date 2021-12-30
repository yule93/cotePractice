package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class programmers_level2_keepDistanceCheck {
  public static void main(String[] args) {
    String[][] places1 = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
        { "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
        { "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"} };
    for (int ex : solution(places1)) {
      System.out.println(ex);
    }
  }

  static int[] dx = { 0, 1, 0, -1 };
  static int[] dy = { -1, 0, 1, 0 };

  public static int[] solution(String[][] places) {
    int[] result = new int[places.length];
    loop:
    for (int tc = 0; tc < places.length; tc++) {
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if (places[tc][i].charAt(j) == 'P') {
            if (!bfs(places[tc], i, j)) {
              result[tc] = 0;
              continue loop;
            }
          }
        }
      }
      result[tc] = 1;
    }
    return result;
  }

  public static boolean bfs(String[] board, int x, int y) {
    Queue<int[]> q = new LinkedList<>();
    boolean[][] visited = new boolean[board.length][board.length];
    q.offer(new int[] { x, y });
    visited[x][y] = true;

    while (!q.isEmpty()) {
      int[] current = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = current[0] + dx[i];
        int ny = current[1] + dy[i];
        int manhattan = Math.abs(x - nx) + Math.abs(y - ny);

        if (nx < 0 || ny < 0 || nx >= board.length || ny >= board.length)
          continue;
        if (visited[nx][ny] || manhattan > 2)
          continue;

        visited[nx][ny] = true;
        if (board[nx].charAt(ny) == 'X')
          continue;
        else if (board[nx].charAt(ny) == 'P')
          return false;
        else
          q.offer(new int[] { nx, ny });
      }
    }
    return true;
  }
}
