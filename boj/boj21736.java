package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 21736 헌내기는 친구가 필요해(dfs, bfs)
// bfs로 풀면 O(NlogN)이므로 600x600xlog360000 = 대략 80x36만 = 3천만이므로 1초 내 해결 가능
// 368ms
public class boj21736 {
  static int[] dy = { 1, 0, -1, 0 };
  static int[] dx = { 0, 1, 0, -1 };
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());   // 세로 1~600
    int M = Integer.parseInt(st.nextToken()); // 가로 1~ 600
    
    Queue<int[]> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][M];
    char[][] map = new char[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = str.charAt(j);
        if (map[i][j] == 'I') {
          q.add(new int[] { i, j }); // 도연이의 y, x 좌표, 사람 만난 수
        }
      }
    }
    
    int answer = 0;
    
    // * bfs 시작
    while (q.size() > 0) {
      int[] now = q.poll(); // 현재 도연이의 좌표

      for (int i = 0; i < 4; i++) {
        int ny = now[0] + dy[i];
        int nx = now[1] + dx[i];

        if (ny < 0 || ny >= N || nx < 0 || nx >= M)
          continue;
        if (visited[ny][nx] || map[ny][nx] == 'X')
          continue;

        if (map[ny][nx] == 'P')
          answer++;

        q.add(new int[] { ny, nx});
        visited[ny][nx] = true;
      }
    }
    
    System.out.println(answer == 0 ? "TT" : answer);
  }
}
