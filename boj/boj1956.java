package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 1956 운동 (플로이드 와샬)
// 636 ms
public class boj1956 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int V = Integer.parseInt(st.nextToken());   // 2 ~ 400
    int E = Integer.parseInt(st.nextToken());   // (V-1)xV

    int INF = 987_654_321;

    int[][] map = new int[V+1][V+1];
    for (int i = 1; i <= V; i++) {
      Arrays.fill(map[i], INF);
    }

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      map[a][b] = c;
    }

    int answer = INF;

    for (int i = 1; i <= V; i++) {
      for (int j = 1; j <= V; j++) {
        for (int k = 1; k <= V; k++) {
          if (j == k)
            continue;
          map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
        }
      }
    }
    
    // for (int i = 1; i <= V; i++) {
    //   for (int j = 1; j <= V; j++) {
    //     System.out.print(map[i][j] + " ");
    //   }
    //   System.out.println();
    // }

    for (int i = 1; i <= V; i++) {
      for (int j = 1; j <= V; j++) {
        if (map[i][j] != INF && i != j && map[j][i] != INF) {
          answer = Math.min(answer, map[i][j]+map[j][i]);
        }
      }
    }

    System.out.println(answer == INF ? -1 : answer);
  }
}
