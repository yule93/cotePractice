package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// boj 16954 움직이는 미로 탈출(BFS)
// 테트리스 순한맛 버전 bfs
// 124ms
public class boj16954 {

  // 대각선 방향 이동 가능
  static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1, 0 };
  static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1, 0 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[][] map = new char[8][8];
    boolean[][] visited = new boolean[8][8];

    // 욱제의 캐릭터는 가장 왼쪽 아랫 칸. 가장 오른쪽 윗칸으로 이동해야 한다.
    for (int i = 0; i < 8; i++) {
      String str = br.readLine().replaceAll(" ", "");
      for (int j = 0; j < 8; j++) {
        map[i][j] = str.charAt(j);
      }
    }

    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { 7, 0 }); // 욱제 위치 저장
    while (q.size() > 0) {
      int size = q.size();

      for (int i = 0; i < size; i++) {
        int[] now = q.poll();
        if (map[now[0]][now[1]] == '#')
          continue;
        if (now[0] == 0) {
          // 맵 끝에 도달했을 때,
          System.out.println(1);
          return;
        }
        for (int d = 0; d < 9; d++) {
          int ny = now[0] + dy[d];
          int nx = now[1] + dx[d];

          if (ny < 0 || ny >= 8 || nx < 0 || nx >= 8)
            continue;
          if (visited[ny][nx] || map[ny][nx] == '#')
            continue;

          q.add(new int[] { ny, nx });
          visited[ny][nx] = true;
        }
      }

      for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 8; j++) {
          // 맨 윗줄은 계속 쭉 비니까 비는 걸로 초기화
          if (i == 0)
            map[i][j] = '.';
          else
            map[i][j] = map[i + 1][j];
        }
      }
    }
    
    System.out.println(0);
  }
}
