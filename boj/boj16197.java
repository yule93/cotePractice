package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 16197 두 동전(bfs)
// ! 동전의 개수는 항상 두 개
public class boj16197 {

  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());   // 맵의 세로
    int M = Integer.parseInt(st.nextToken()); // 맵의 가로
    
    Queue<int[]> q = new LinkedList<>();
    char[][] map = new char[N][M];
    boolean[][][] visited = new boolean[2][N][M];   // 두 코인 방문 지점을 동시에 체크해줘야 함....
    ArrayList<int[]> coins = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = str.charAt(j);
        if (map[i][j] == 'o') {
          coins.add(new int[] { i, j });
          map[i][j] = '.';
        }
      }
    }
    
    int answer = 10;
    visited[0][coins.get(0)[0]][coins.get(0)[1]] = true;
    visited[1][coins.get(1)[0]][coins.get(1)[1]] = true;
    q.add(new int[] { coins.get(0)[0], coins.get(0)[1], coins.get(1)[0], coins.get(1)[1], 0 }); // 두 동전의 위치와 움직인 횟수 넣어줌
    while (q.size() > 0) {
      int[] now = q.poll();
      for (int d = 0; d < 4; d++) {
        int ny1 = now[0] + dy[d];
        int nx1 = now[1] + dx[d];
        int ny2 = now[2] + dy[d];
        int nx2 = now[3] + dx[d];
        int count = now[4] + 1;

        if (ny1 < 0 || ny1 >= N || nx1 < 0 || nx1 >= M || ny2 < 0 || ny2 >= N || nx2 < 0 || nx2 >= M) {
          answer = Math.min(answer, count);
          continue;
        }
        if (visited[0][ny1][nx2] && visited[1][ny2][nx2])
          continue;
        if (map[ny1][nx1] == '#') {
          ny1 -= dy[d];
          nx1 -= dx[d];
        }
        if (map[ny2][nx2] == '#') {
          // 두 번째 코인이 벽을 만났을 때,
          ny2 -= dy[d];
          nx2 -= dx[d];
        }

        visited[0][ny1][nx1] = true;
        visited[1][ny2][nx2] = true;
        q.add(new int[] { ny1, nx1, ny2, nx2, count });
      }
    }

    System.out.println(answer >= 10 ? -1 : answer);
  }
}
