package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 13349 광이삼의 암벽 등반 (BFS, 가지치기, 맨하튼)
public class swea13349 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    int[][] map;
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken()); // 행의 개수
      int N = Integer.parseInt(st.nextToken()); // 열의 개수
      int L = Integer.parseInt(st.nextToken()); // 최대 이동 가능 거리

      ArrayList<int[]> hook = new ArrayList<>();
      int[] start = new int[2];
      int[] end = new int[2];
      // 맵 초기화
      map = new int[M][N];
      for (int i = 0; i < M; i++) {
        String str = br.readLine().replaceAll(" ", "");
        for (int j = 0; j < N; j++) {
          map[i][j] = str.charAt(j) - '0';
          if (map[i][j] != 0) {
            if (map[i][j] == 2) {
              start = new int[] { i, j };
            } else if (map[i][j] == 3) {
              end = new int[] { i, j };
              hook.add(new int[] {i, j});
            } else {
              hook.add(new int[] { i, j });
            }
          }
        }
      }

      //bfs 시작
      int answer = hook.size()+1; // 최소값 저장할 변수
      Queue<int[]> q = new LinkedList<>();
      q.add(new int[] { start[0], start[1], 0 }); // 시작점, 고리개수 넣어줌
      while (q.size() > 0) {
        int[] nowPos = q.poll();
        //System.out.println("현재 위치: " + nowPos[0] + ", "+nowPos[1] + "사용 후크: "+nowPos[2]+"개");
        if (nowPos[0] == end[0] && nowPos[1] == end[1]) {
          answer = Math.min(answer, nowPos[2]-1);
          break;
        }
        if (nowPos[2] >= answer)
          continue;
        for (int i = 0; i < hook.size(); i++) {
          int[] hookPos = hook.get(i);

          if ((Math.abs(end[0] - hookPos[0]) + Math.abs(end[1] - hookPos[1])) > Math.abs(end[0] - nowPos[0])
              + Math.abs(end[1] - nowPos[1])) {
            continue;
          }

          if ((Math.abs(hookPos[0] - nowPos[0]) + Math.abs(hookPos[1] - nowPos[1]) <= L)
            && !(hookPos[0] == nowPos[0] && hookPos[1] == nowPos[1])) {
            // 이동 가능한 후크일 때만,
            q.add(new int[] { hookPos[0], hookPos[1], nowPos[2] + 1 });
          }
        }
      }

      sb.append("#").append(tc).append(" ").append(answer > hook.size() ? -1 : answer).append("\n");
    }
    
    System.out.print(sb.toString());
  }
}

/*

1
5 5 3
0 0 0 0 3
1 0 0 0 0
0 1 1 0 1
0 0 0 1 1
2 0 0 0 0

1
4 5 3
1 0 0 0 3
0 1 1 0 1
0 0 0 1 1
2 0 0 0 0

1
3 3 4
0 0 3
0 0 0
2 0 0

 */