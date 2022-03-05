package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// boj 2151 거울 설치(BFS)
// 말이 되고픈 원숭이(1600)와 네트워크랑 유사한 문제. 때문에 3차원 배열로 어떤 거울을 사용했는지 저장해줘야 한다.
// 144ms
public class boj2151 {
  static char[][] map;
  static int startX, startY, endX, endY;
  static int visited[][][];
  static int dy[] = { -1, 0, 1, 0 };
  static int dx[] = { 0, 1, 0, -1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    map = new char[N][N];
    visited = new int[N][N][4];
    startX = -1;
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < N; j++) {
        map[i][j] = str.charAt(j);
        if (map[i][j] == '#') {
          if (startX == -1) {
            startX = i;
            startY = j;
          } else {
            endX = i;
            endY = j;
          }
        }
        for (int k = 0; k < 4; k++) {
          visited[i][j][k] = 987_654_321;
        }
      }
    }

    Queue<Point> q = new LinkedList<>();
    // * : 벽 # : 문  ! : 거울을 놓을수 있는 자리 . : 움직을수있는자리
    for (int k = 0; k < 4; k++) {
      visited[startX][startY][k] = 0;
      q.add(new Point(startX, startY, k));
    }

    while (!q.isEmpty()) {
      Point p = q.poll();
      int nx = p.x + dy[p.dir];
      int ny = p.y + dx[p.dir];
      if (nx < 0 || nx >= N || ny < 0 || ny >= N)
        continue;
      if (map[nx][ny] == '*')
        continue;

      if (map[nx][ny] == '#') {
        if (visited[nx][ny][p.dir] > visited[p.x][p.y][p.dir]) {
          visited[nx][ny][p.dir] = visited[p.x][p.y][p.dir];
          continue;
        }
      }

      if (map[nx][ny] == '.') {
        if (visited[nx][ny][p.dir] > visited[p.x][p.y][p.dir]) {
          visited[nx][ny][p.dir] = visited[p.x][p.y][p.dir];
          q.add(new Point(nx, ny, p.dir));
          continue;
        }
      }
      if (map[nx][ny] == '!') {
        // 거울을 설치하는 경우와 하지않는 경우.

        // 거울을 설치하지 않음.
        if (visited[nx][ny][p.dir] > visited[p.x][p.y][p.dir]) {
          visited[nx][ny][p.dir] = visited[p.x][p.y][p.dir];
          q.add(new Point(nx, ny, p.dir));
        }
        // 거울을 설치
        if (visited[nx][ny][(p.dir + 1) % 4] > visited[p.x][p.y][p.dir] + 1) {
          visited[nx][ny][(p.dir + 1) % 4] = visited[p.x][p.y][p.dir] + 1;
          q.add(new Point(nx, ny, (p.dir + 1) % 4));
        }
        if (visited[nx][ny][(p.dir + 3) % 4] > visited[p.x][p.y][p.dir] + 1) {
          visited[nx][ny][(p.dir + 3) % 4] = visited[p.x][p.y][p.dir] + 1;
          q.add(new Point(nx, ny, (p.dir + 3) % 4));
        }
      }
    }

    int min = 987_654_321;
    for (int i = 0; i < 4; i++) {
      if (min > visited[endX][endY][i]) {
        min = visited[endX][endY][i];
      }
    }
    System.out.println(min);

  }

  static class Point {
    int x, y;
    int count, dir;

    Point(int x, int y, int dir) {
      this.x = x;
      this.y = y;
      this.dir = dir;
    }
  }
}
