package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// boj 1941 칠 공주 (dfs, bfs)
// * 1. 먼저 칠공주로 삼을 인원을 dfs(재귀)로 지정한다.
// * 2-1. 7명이 인접해있는지 bfs로 카운팅을 해서 만약 7명이 맞으면 true,
// * 2-2. 그러나 이다솜파가 4명 이상인지 확인하기 위해 다시 한 번 칠 공주 값 중 "S"가 4개 이상인지 카운팅
// * 3. 상기 조건을 모두 만족하면 answer++
// 356ms
public class boj1941 {
  static int N = 5, res = 0, selected[];
  static char map[][];
  static boolean visited[], adjVisited[];
  static int dx[] = { -1, 0, 1, 0 };
  static int dy[] = { 0, 1, 0, -1 };
  static Queue<Integer> q;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    map = new char[N][N];
    for (int i = 0; i < N; i++)
      map[i] = br.readLine().toCharArray();

    visited = new boolean[N * N];   // 맵 방문을 일렬 행렬로 만든 이유: 조합을 만들어야 해서 2차원 반복문으론 어려울 것 같음....
    selected = new int[7]; // 선택된 칠공주

    find(0, 0, 0);
    System.out.println(res);
  }

  // * dfs
  public static void find(int idx, int count, int countS) {
    if (count == 7) {
      // 7명 완성
      if (countS >= 4) {
        // 인접 확인
        if (checkAdj())
          res++;
        return;
      }
      // '솜'파가 4명 이상이 아니면 그냥 return..
      return;
    }

    for (int i = idx; i < N * N; i++) {
      visited[i] = true;
      selected[count] = i;
      // '솜'파일 경우,
      if (map[i / N][i % N] == 'S')
        find(i + 1, count + 1, countS + 1);
      else
        find(i + 1, count + 1, countS);
      visited[i] = false;
    }
  }

  public static boolean checkAdj() {
    int count = 1;
    adjVisited = new boolean[N * N];
    q = new LinkedList<>();
    // 임의 한 명 위치를 Queue에 넣고
    q.add(selected[0]);
    // 7명이 모두 인접해있는지 확인해보자.
    while (!q.isEmpty()) {
      int now = q.poll();
      adjVisited[now] = true;

      for (int d = 0; d < 4; d++) {
        int xx = (now / N) + dx[d];
        int yy = (now % N) + dy[d];
        if (xx < 0 || yy < 0 || xx >= N || yy >= N)
          continue;
        // 7공주 중 이미 확인한 인원일 때,
        if (adjVisited[xx * N + yy])
          continue;
        // 7공주가 아닐 때,
        if (!visited[xx * N + yy])
          continue;
        count++;
        adjVisited[xx * N + yy] = true;
        q.add(xx * N + yy);
      }
    }
    // 임의 한 명 이랑 인접한 공주가 모두 7명이 되면 true
    if (count == 7)
      return true;
    else
      return false;
  }

}
