package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 2571 색종이3(분할정복이라는데??? 그냥 다중 반복문으로 처리가 가능...?? 한 거 같음. 그리디)
// 248ms
public class boj2571 {
  static int answer;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    boolean[][] papers = new boolean[100][100];
    StringTokenizer st;
    // 가로세로 크기가 각각 100
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      for (int j = x; j < x + 10; j++) {
        for (int k = y; k < y + 10; k++) {
          papers[j][k] = true;
        }
      }
    }

    answer = 0;

    for (int sx = 0; sx < 99; sx++) {
      for (int sy = 0; sy < 99; sy++) {
        if (!papers[sx][sy])
          continue;

        for (int ex = sx + 1; ex < 100; ex++) {
          for (int ey = sy + 1; ey < 100; ey++) {
            if (!papers[ex][ey])
              break;

            int now = (ex - sx + 1) * (ey - sy + 1);

            if (now <= answer)
              continue;

            boolean flag = true;
            loop: for (int i = sx; i <= ex; i++) {
              for (int j = sy; j <= ey; j++) {
                if (!papers[i][j]) {
                  flag = false;
                  break loop;
                }
              }
            }
            if (flag)
              answer = Math.max(answer, now);
          }
        }
      }
    }

    System.out.println(answer);
  }
}