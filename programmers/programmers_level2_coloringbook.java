package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class programmers_level2_coloringbook {
  public static void main(String[] args) {
    int[][] example1 = { { 1, 1, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 },
        { 0, 0, 0, 1 } };
    int[] answer = solution(6, 4, example1);

    for (int e : answer) {
      System.out.println(e);
    }
  }

  public static int[] solution(int m, int n, int[][] picture) {
    long[][] p = new long[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        p[i][j] = picture[i][j];
      }
    }
    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;

    boolean[][] checked = new boolean[m][n];

    int[] dy = { -1, 1, 0, 0 };
    int[] dx = { 0, 0, -1, 1 };

    ArrayList<long[]> list = new ArrayList<>();
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (!checked[i][j] && p[i][j] > 0) {
          // 좌표랑 영역 개수를 넣음
          numberOfArea++;
          int areaSize = 0;
          long num = p[i][j];
          q.add(new int[] { i, j });
          while (q.size() > 0) {
            int[] now = q.poll();
            if(!checked[now[0]][now[1]]) areaSize++;
            // System.out.println(now[0]+", "+now[1]);
            checked[now[0]][now[1]] = true;

            for (int d = 0; d < 4; d++) {
              int ny = now[0] + dy[d];
              int nx = now[1] + dx[d];
              if (ny >= m || nx >= n || ny < 0 || nx < 0)
                continue;
              if (!checked[ny][nx] && p[now[0]][now[1]] == p[ny][nx]) {
                q.add(new int[] { ny, nx });
              }
            }
          }

          list.add(new long[] { num, areaSize });
          maxSizeOfOneArea = areaSize > maxSizeOfOneArea
              ? areaSize
              : maxSizeOfOneArea;
        }
      }
    }

    int[] answer = new int[2];
    answer[0] = numberOfArea;
    answer[1] = maxSizeOfOneArea;
    return answer;
  }
}
