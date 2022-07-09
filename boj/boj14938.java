package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 14938 서강그라운드(플로이드 와샬)
// * 늘 그렇지만 플로이드 와샬은 다익스트라와 비슷한 경향이 잇다
// 152ms
public class boj14938 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int r = Integer.parseInt(st.nextToken());

    int[] map = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      map[i] = Integer.parseInt(st.nextToken());
    }

    // 갈 수 없는 길은 최대화해서 Math.min에 해당되지 않도록 배치
    int[][] root = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(root[i], 987_654_321);
    }
    for (int i = 0; i < r; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken()) - 1;
      int e = Integer.parseInt(st.nextToken()) - 1;
      int w = Integer.parseInt(st.nextToken());
      root[s][e] = root[e][s] = w;
    }
    
    int[] res = new int[n];
    // 경출도
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          if (i == j || j == k || i == k)
            continue;
          root[j][k] = Math.min(root[j][i] + root[i][k], root[j][k]);
        }
      }
    }

    int answer = 0;
    for (int i = 0; i < n; i++) {
      int temp = map[i];
      for (int j = 0; j < n; j++) {
        if (root[i][j] <= m) {
          temp += map[j];
        }
      }
      answer = Math.max(answer, temp);
    }

    System.out.println(answer);
  }
}
