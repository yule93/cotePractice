package boj.structureForCheckWeek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 15686 치킨 배달(dfs)
// M이 1~13이어서 dfs로 순열 정하고 시작
// 2864ms
public class boj15686 {

  static int N, M, answer, chickenCount;
  static int[] perm;
  static int[][] map, dist;
  static boolean[] visited;
  static ArrayList<int[]> chickenList, homeList;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][N];
    chickenCount = 0;
    chickenList = new ArrayList<>();
    homeList = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      String str = br.readLine().replaceAll(" ", "");
      for (int j = 0; j < N; j++) {
        map[i][j] = str.charAt(j) - '0';
        if (map[i][j] == 2) {
          // 치킨집 저장
          chickenCount++;
          chickenList.add(new int[] { i, j });
        } else if (map[i][j] == 1)
          homeList.add(new int[] { i, j });
      }
    }

    perm = new int[M];
    visited = new boolean[chickenCount];
    answer = 987_654_321;

    dfs(0, 0);

    // for (int i = 0; i < N; i++) {
    // for (int j = 0; j < N; j++) {
    // System.out.print(map[i][j] + " ");
    // }
    // System.out.println();
    // }

    System.out.println(answer);
  }

  public static void dfs(int depth, int start) {
    if (depth == M) {
      // 단순 거리를 재서 dist에 최소값으로 저장
      dist = new int[N][N];
      for (int i = 0; i < N; i++) {
        Arrays.fill(dist[i], 987_654_321);
      }
      for (int m = 0; m < M; m++) {
        int[] startP = chickenList.get(perm[m]);
        for (int h = 0; h < homeList.size(); h++) {
          int[] endP = homeList.get(h);
          dist[endP[0]][endP[1]] = Math.min(dist[endP[0]][endP[1]],
              Math.abs(startP[0] - endP[0]) + Math.abs(startP[1] - endP[1]));
        }
      }

      int sum = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (dist[i][j] != 987_654_321)
            sum += dist[i][j];
        }
      }

      answer = Math.min(answer, sum);
      return;
    }

    for (int i = start; i < chickenCount; i++) {
      if (visited[i])
        continue;

      perm[depth] = i;
      visited[i] = true;
      dfs(depth + 1, i + 1);
      visited[i] = false;
      dfs(depth + 1, i + 1);
    }
  }
}
