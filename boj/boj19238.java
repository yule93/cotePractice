package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 19238 스타트택시(최단경로->bfs?)
// * 최단거리에 있는 손님을 먼저 찾는 bfs -> 택시 이동 bfs -> 다시 최단거리 손님 재탐색 순서를 매우 중요하게 따져야 함
// 260ms
public class boj19238 {
  static int[] dy = { 0, 0, -1, 1 };
  static int[] dx = { -1, 1, 0, 0 };

  static int N, M, oil, cnt = 0;
  static int[][] map, start, dest;
  static int[] taxi;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 행, 열 크기
    M = Integer.parseInt(st.nextToken()); // 승객 수
    oil = Integer.parseInt(st.nextToken()); // 연료
    map = new int[N][N];

    start = new int[M + 1][2]; // 승객들의 출발지 좌표 리스트
    dest = new int[M + 1][2]; // 승객들의 도착지 좌표 리스트
    taxi = new int[2]; // 택시의 현재 위치

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 1) { // 벽은 -1로 바꿔준다. (0 이상이면 승객으로 처리할 것이기 때문)
          map[i][j] = -1;
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    taxi[0] = Integer.parseInt(st.nextToken()) - 1; // 0~ N-1 좌표를 가져야하기 때문에 -1 해준다.
    taxi[1] = Integer.parseInt(st.nextToken()) - 1;

    for (int i = 1; i <= M; i++) {
      st = new StringTokenizer(br.readLine());
      start[i][0] = Integer.parseInt(st.nextToken()) - 1;
      start[i][1] = Integer.parseInt(st.nextToken()) - 1;
      map[start[i][0]][start[i][1]] = i; // 승객의 출발지 위치에 승객 번호를 지정 (겹치지 X)
      dest[i][0] = Integer.parseInt(st.nextToken()) - 1; // 다른 승객의 출발지 위치와 겹칠 수 있기 때문에 map에 지정하지 않는다.
      dest[i][1] = Integer.parseInt(st.nextToken()) - 1;
    }

    int answer = -1;
    while (true) {
      int[] passengerInfo = bfs(taxi[0], taxi[1]); // [0]: 승객번호, [1]: 거리
      // 승객 운반 및 오일 충전
      move(passengerInfo);

      if (passengerInfo[0] == 0 || oil < 0)
        break; // 승객이 없거나 || 연료가 다 닳았으면 끝.

      cnt++;
      if (cnt == M) { // 모든 승객을 다 운반했으면
        answer = oil;
        break;
      }
    }
    System.out.println(answer);
  }

  private static void move(int[] near) {
    // 거리 구하기
    int p = near[0];
    int dir = getDistance(p);
    // oil 양 줄인 후 충전
    oil -= dir + near[1];
    if (oil < 0)
      return;
    oil += (dir * 2);
    // 승객 초기화
    map[start[p][0]][start[p][1]] = 0;
    // 택시 위치 옮기기
    taxi[0] = dest[p][0];
    taxi[1] = dest[p][1];
  }

  private static int getDistance(int p) { // p의 출발지부터 도착지까지의 최소 거리 구하기
    int minDir = Integer.MAX_VALUE; // 최소 거리
    boolean[][] visited = new boolean[N][N];
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { start[p][0], start[p][1], 0 }); // x 좌표, y 좌표, 출발지부터의 거리
    visited[start[p][0]][start[p][1]] = true;

    while (q.size() > 0) {
      int[] now = q.poll();
      for (int d = 0; d < 4; d++) {
        int nx = now[0] + dx[d];
        int ny = now[1] + dy[d];

        if (nx >= N || nx < 0 || ny >= N || ny < 0)
          continue;
        if (map[nx][ny] == -1 || visited[nx][ny])
          continue;
        if (nx == dest[p][0] && ny == dest[p][1]) { // 도착지이면
          if (now[2] + 1 < minDir) // 최소거리 갱신
            minDir = now[2] + 1;
        } else if (!visited[nx][ny]) {
          q.add(new int[] { nx, ny, now[2] + 1 });
          visited[nx][ny] = true;
        }
      }
    }
    return minDir;
  }

  // 택시와 가장 가까운 승객 정보 반환
  private static int[] bfs(int x, int y) {
    if (map[x][y] > 0) {
      // 승객과 택시가 같은 곳에 있는 경우
      return new int[] { map[x][y], 0 };
    }

    int near = 0; // 가장 가까운 승객
    int minDir = Integer.MAX_VALUE; // 가까운 승객과의 거리
    boolean[][] visited = new boolean[N][N];
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { x, y, 0 });
    visited[x][y] = true;

    while (q.size() > 0) {
      int[] now = q.poll();
      for (int d = 0; d < 4; d++) {
        int nx = now[0] + dx[d];
        int ny = now[1] + dy[d];

        if (nx >= N || nx < 0 || ny >= N || ny < 0)
          continue;
        if (map[nx][ny] == -1 || visited[nx][ny])
          continue;
        if (map[nx][ny] > 0) {
          if (now[2] + 1 < minDir) {
            minDir = now[2] + 1;
            near = map[nx][ny];
          } else if (now[2] + 1 == minDir) {
            // 택시로부터 최소 거리 승객과 같은 거리면
            if (nx < start[near][0]) {
              near = map[nx][ny];
            } else if (nx == start[near][0] && ny < start[near][1]) {
              // 행은 같은데 열이 더 작으면
              near = map[nx][ny];
            }
          }
        }

        if (!visited[nx][ny]) {
          q.add(new int[] { nx, ny, now[2] + 1 });
          visited[nx][ny] = true;
        }
      }
    }
    return new int[] { near, minDir };
  }

  public static void bfs_second() {
    /*
     * boolean[][] visited = new boolean[N][N];
     * visited[sr][sc] = true;
     * while (q.size() > 0) {
     * int[] now = q.poll();
     * // 연료 0이니까 움직일 수 x
     * if (now[2] == 0)
     * continue;
     * if (now[3] == 0) {
     * // 모든 사람을 운행 햇다면
     * System.out.println(now[4]);
     * break;
     * }
     * 
     * System.out.println(now[0]+", "+now[1]);
     * if (now[4] >= 2 && now[0] == destList.get(now[4]-2)[0] && now[1] ==
     * destList.get(now[4]-2)[1]) {
     * // 목적지 도착시
     * visited = new boolean[N][N];
     * visited[now[0]][now[1]] = true;
     * now[2] += now[5] * 2;
     * now[5] = 0;
     * now[4] = -1;
     * // q.add(new int[] { now[0], now[1], now[2] + now[5] * 2, now[3], -1, now[5]
     * + 1 });
     * // System.out.println(now[4]);
     * }
     * 
     * for (int d = 0; d < 4; d++) {
     * int ny = now[0] + dy[d];
     * int nx = now[1] + dx[d];
     * 
     * if (ny < 0 || ny >= N || nx < 0 || nx >= N)
     * continue;
     * if (visited[ny][nx])
     * continue;
     * if (map[ny][nx] == 1)
     * continue;
     * 
     * if (map[ny][nx] >= 2 && now[4] == -1) {
     * // 사람을 만낫어요
     * int[] destPos = destList.get(map[ny][nx] - 2);
     * int dest = Math.abs(destPos[0] - ny) + Math.abs(destPos[1] - nx);
     * if (dest > now[2])
     * continue;
     * visited = new boolean[N][N];
     * visited[ny][nx] = true;
     * map[ny][nx] = 0;
     * q.add(new int[] { ny, nx, now[2] - 1, now[3] - 1, map[ny][nx], now[5] + 1 });
     * continue;
     * }
     * 
     * visited[ny][nx] = true;
     * q.add(new int[] { ny, nx, now[2] - 1, now[3], now[4], now[5] + 1 });
     * }
     * }
     * 
     * // for (int i = 0; i < N; i++) {
     * // for (int j = 0; j < N; j++) {
     * // System.out.print(map[i][j] + " ");
     * // }
     * // System.out.println();
     * // }
     * System.out.println(-1);
     */
  }
}
