package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 17142 연구소 3(bfs, dfs, 종합선물세트)
// 296ms
public class boj17142 {
  
  static int N, M, answer, zeroCount;
  static int[][] map;
  static int[] combi;
  static ArrayList<int[]> virusList;
  static Queue<int[]> q;
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    virusList = new ArrayList<>();
    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      String str = br.readLine().replaceAll(" ", "");
      for (int j = 0; j < N; j++) {
        map[i][j] = str.charAt(j) - '0';
        if (map[i][j] == 0)
          zeroCount++;
        if (map[i][j] == 2)
          virusList.add(new int[] { i, j });
      }
    }

    if (zeroCount == 0) {
      System.out.println(0);
      return;
    }

    q = new LinkedList<>();
    combi = new int[M];
    answer = 987_654_321;
    dfs(0, 0, zeroCount);

    System.out.println(answer == 987_654_321 ? -1 : answer);
  }
  
  // * 조합으로 바이러스 위치 선택
  public static void dfs(int depth, int start, int zeroCount) {
    if (depth == M) {
      boolean[][] visited = new boolean[N][N];
      for (int num : combi) {
        int[] selectedVirus = virusList.get(num);
        q.add(new int[] { selectedVirus[0], selectedVirus[1], 0 });
        visited[selectedVirus[0]][selectedVirus[1]] = true;
      }
      
      int count = 0;
      int leave = zeroCount;
      while (q.size() > 0) {
        int[] now = q.poll();
        if (map[now[0]][now[1]] == 0) {
          leave--;
          count = Math.max(count, now[2]);
        }
        for (int d = 0; d < 4; d++) {
          int ny = now[0] + dy[d];
          int nx = now[1] + dx[d];

          if (ny < 0 || ny >= N || nx < 0 || nx >= N)
            continue;
          // * 벽이거나 이미 왔던 곳이면 통과
          if (visited[ny][nx] || map[ny][nx] == 1)
            continue;

          visited[ny][nx] = true;
          // * 비활성화된 바이러스가 있던 곳이면 그냥 통과...라고 보면 될듯?
          q.add(new int[] { ny, nx, now[2] + 1 });
        }
      }

      if(leave == 0)
        answer = Math.min(answer, count);
      return;
    }
    
    for (int i = start; i < virusList.size(); i++) {
      combi[depth] = i;
      dfs(depth + 1, i + 1, zeroCount);
    }
  }
}