package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 5212 지구 온난화(DFS)
// 124ms
public class boj5212 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    //boolean[][] visited = new boolean[R][C];
    char[][] map = new char[R][C];
    char[][] copyMap = new char[R][C];
    for (int i = 0; i < R; i++) {
      String str = br.readLine().replace(" ", "");
      for (int j = 0; j < C; j++) {
        map[i][j] = str.charAt(j);
        copyMap[i][j] = map[i][j];
      }
    }

    
    // dfs 시작
    int[] dy = { -1, 1, 0, 0 };
    int[] dx = { 0, 0, -1, 1 };
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (map[i][j] == 'X') {
          int count = 0; // 사면 바다 체크
          for (int c = 0; c < 4; c++) {
            int ny = i + dy[c];
            int nx = j + dx[c];

            if (ny >= R || ny < 0 || nx >= C || nx < 0) {
              count++;
              continue;
            }
            if (map[ny][nx] == '.')
              count++;
          }
          if (count >= 3) {
            copyMap[i][j] = '.';
          }
        }
      }
    }
    
    // 섬 남아있는 곳 체크
    int minR = R;
    int maxR = 0;
    int minC = C;
    int maxC = 0;
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (copyMap[i][j] == 'X') {
          minR = Math.min(i, minR);
          maxR = Math.max(maxR, i);
          minC = Math.min(j, minC);
          maxC = Math.max(maxC, j);
        }
      }
    }

    // System.out.println(minR + ", "+maxR + ", "+ minC + ", "+ maxC);
    // for (int i = 0; i < R; i++) {
    //   for (int j = 0; j < C; j++) {
    //     System.out.print(copyMap[i][j]);
    //   }
    //   System.out.println();
    // }
    StringBuilder sb = new StringBuilder();
    for (int i = minR; i <= maxR; i++) {
      for (int j = minC; j <= maxC; j++) {
        sb.append(copyMap[i][j]);
      }
      sb.append("\n");
    }

    System.out.print(sb.toString());
  }
}
