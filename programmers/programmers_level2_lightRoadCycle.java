package programmers;

import java.util.ArrayList;
import java.util.Arrays;

// 프로그래머스 빛의 경로 싸이클(완전탐색, 백트래킹)
// O(4YX) = O(NM)
public class programmers_level2_lightRoadCycle {
  public static void main(String[] args) {
    for (int num : solution(new String[] {"SL","LR"})) {
      System.out.print(num+", ");
    }
  }

  static int Y, X;
  static int[] dy = { -1, 0, 1, 0 }, dx = { 0, -1, 0, 1 }; // 상좌하우 무조건 90도씩 돌아가게 맞춰야 회전 시 편함
  static boolean[][][] isVisited;

  public static int[] solution(String[] grid) {
    ArrayList<Integer> answer = new ArrayList<Integer>();

    Y = grid.length;
    X = grid[0].length();

    isVisited = new boolean[Y][X][4];   // 4방향을 모두 탐색했을 때가 돼서야 사이클 완성이 가능하다.
    for (int i = 0; i < Y; i++) {
      for (int j = 0; j < X; j++) {
        for (int d = 0; d < 4; d++) {
          if (!isVisited[i][j][d])
            answer.add(light(grid, i, j, d));
        }
      }
    }

    int[] arr = new int[answer.size()];
    for (int i = 0; i < answer.size(); i++) {
      arr[i] = answer.get(i);
    }
    Arrays.sort(arr);   // 오름차순으로 정렬

    return arr;
  }

  private static int light(String[] grid, int r, int nx, int d) {
    int cnt = 0; // 이동거리

    while (true) {
      if (isVisited[r][nx][d])
        break;
      cnt++; // 거리증가
      isVisited[r][nx][d] = true; // 방문처리

      if (grid[r].charAt(nx) == 'L')
        d = (d + 3) % 4; // 좌회전
      else if (grid[r].charAt(nx) == 'R')
        d = (d + 1) % 4; // 우회전

      r = (r + dy[d] + Y) % Y;
      nx = (nx + dx[d] + X) % X;
    }

    return cnt;
  }
}
