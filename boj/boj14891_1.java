package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 14891 톱니바퀴(dfs)
public class boj14891_1 {

  static int[][] gears;
  static boolean[] visited;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    visited = new boolean[4];
    gears = new int[4][8];
    for (int i = 0; i < 4; i++) {
      String str = br.readLine();
      for (int j = 0; j < 8; j++) {
        gears[i][j] = str.charAt(j) - '0'; // ? S극은 1, N극은 0
      }
    }

    int K = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for (int k = 0; k < K; k++) {
      st = new StringTokenizer(br.readLine());
      int num = Integer.parseInt(st.nextToken()); // ? 회전시킨 톱니바퀴의 번째수
      int direct = Integer.parseInt(st.nextToken()); // ? 회전 방향. 1: 시계, -1: 반시계

      // * 1. 회에에전 하는데 톱니끼리는 3번째 톱니가 맞닿아 있음
      // * 같은 극이면 회전 X, 다른 극이면 반대로 회전

      rotation(num - 1, direct);
    }

    int answer = 0;
    for (int i = 0; i < 4; i++) {
      answer += gears[i][0] == 0 ? 0 : Math.pow(2, i);
    }

    System.out.println(answer);
  }
  
  public static void rotation(int num, int dir) {
    visited[num] = true;
    if (num >= 1 && gears[num-1][2] != gears[num][6] && !visited[num-1])
      rotation(num - 1, -dir);
    if(num < 3 && gears[num][2] != gears[num+1][6] && !visited[num+1])
      rotation(num + 1, -dir);

    if (dir == -1) {
      oppositeClockRotation(num);
      return;
    }
    else if (dir == 1) {
      clockRotation(num);
      return;
    }
  }
  
  public static void clockRotation(int num) {
    // * 시계방향 회전
    int end = gears[num][7];
    for (int i = 7; i > 0; i--) {
      gears[num][i] = gears[num][i - 1];
    }
    gears[num][0] = end;
  }

  public static void oppositeClockRotation(int num) {
    // * 반시계방향 회전
    int first = gears[num][0];
    for (int i = 0; i < 7; i++) {
      gears[num][i] = gears[num][i + 1];
    }
    gears[num][7] = first;
  }
}
