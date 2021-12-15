package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj4936 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    int[][] map;
    while (true) {
      st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());

      if (w == 0 && h == 0)
        break;
      
      map = new int[h][w];
      boolean check = false;
      for (int i = 0; i < h; i++) {
        String str = br.readLine().replace(" ", "");
        for (int j = 0; j < w; j++) {
          map[i][j] = str.charAt(j) - '0';
          if (map[i][j] == 1)
            check = true;
        }
      }

      if (!check) {
        sb.append(0).append("\n");
        continue;
      }

      int[] dy = { -1, 1, 0, 0, 1, -1, 1, -1 };
      int[] dx = { 0, 0, -1, 1, 1, -1, -1, 1 };
      int num = 0;
      boolean[][] visited = new boolean[h][w];
      Queue<int[]> q = new LinkedList<>();
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          if (map[i][j] == 1 && !visited[i][j]) {
            num++;
            q.add(new int[] { i, j });
            while (q.size() > 0) {
              int[] now = q.poll();
              for (int d = 0; d < 8; d++) {
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];
                if (ny >= h || nx >= w || ny < 0 || nx < 0)
                  continue;
                if (map[ny][nx] == 1 && !visited[ny][nx]) {
                  q.add(new int[] { ny, nx });
                  visited[ny][nx] = true;
                }
              }
            }
          }
        }
      }

      sb.append(num).append("\n");

    }

    System.out.println(sb.toString());
  }
}
