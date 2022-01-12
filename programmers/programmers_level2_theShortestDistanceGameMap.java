package programmers;

import java.util.*;

public class programmers_level2_theShortestDistanceGameMap {
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };

  public static void main(String[] args) {
    System.out.println(solution(new int[][] {
        { 1, 0, 1, 1, 1 },
        { 1, 0, 1, 0, 1 },
        { 1, 0, 1, 1, 1 },
        { 1, 1, 1, 0, 1 },
        { 0, 0, 0, 0, 1 }
    }));
  }

  public static int solution(int[][] maps) {
    // 0은 벽, 1은 빈칸
    int answer = 0;
    boolean[][] visited = new boolean[maps.length][maps[0].length];

    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[2] - o2[2];
      }
    });

    pq.add(new int[] { 0, 0, 0 });
    while (pq.size() > 0) {
      int[] now = pq.poll();

      if (now[0] == maps.length - 1 && now[1] == maps[0].length - 1) {
        return now[2] + 1;
      }
      for (int i = 0; i < 4; i++) {
        int ny = now[0] + dy[i];
        int nx = now[1] + dx[i];
        if (ny >= maps.length || ny < 0 || nx >= maps[0].length || nx < 0)
          continue;
        if (visited[ny][nx] || maps[ny][nx] == 0)
          continue;

        pq.add(new int[] { ny, nx, now[2] + 1 });
        visited[ny][nx] = true; // 다시 bfs를 탈 때, 체크하는 게 효율성에 좋다.
      }
    }

    return -1;
  }
}
