package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 2933 미네랄 (BFS, 시뮬레이션)
// * bfs로 연결된 클러스터가 어디까지인지 확인하고, 천장에서 떨어지는 걸 구현하는 게 포인트. 마치 테트리스 문제!
// 시간복잡도는 O(N*MlogM) = O(100 * (RC)log(RC)) = 약 1천만번~3천만번의 연산으로 0.3초.... 1초 내로 가능.... 메모리는 int의 2차원 배열 중 최대 100x100이니까, 4*100*100 = 4만 바이트 = 40kb여서 걱정 안 해도 된다.
// 536ms
public class boj2933 {

  static String[][] map;
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dx = { 0, 0, -1, 1 };
  static ArrayList<int[]> cluster;

  static int R, C;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken()); // 1~100
    C = Integer.parseInt(st.nextToken()); // 1~100
    map = new String[R][C];
    for (int i = 0; i < R; i++) {
      String str = br.readLine();
      for (int j = 0; j < C; j++) {
        map[i][j] = String.valueOf(str.charAt(j));
      }
    }

    int N = Integer.parseInt(br.readLine()); // 막대를 던진 횟수. 1~100

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int throwStick = Integer.parseInt(st.nextToken());
      bfs(throwStick, i);
    }

    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        System.out.print(map[i][j]);
      }
      System.out.println();
    }
  }
  
  // height는 막대 던진 높이, order은 왼쪽과 오른쪽 중 누가 던지는지
  public static void bfs(int height, int order) {
    int r = R - height;
    if (order % 2 == 0) {
      // * 왼쪽에서 던질 때,
      for (int c = 0; c < C; c++) {
        if (map[r][c].equals("x")) {
          // 막대 맞은 부분 떨어트리기
          map[r][c] = ".";
          break;
        }
      }
    } else {
      // * 오른쪽에서 던질 때,
      for (int c = C - 1; c >= 0; c--) {
        if (map[r][c].equals("x")) {
          // 막대 맞은 부분 떨어트리기
          map[r][c] = ".";
          break;
        }
      }
    }

    // ! 클러스터 찾기
    findCluster();
    if (cluster.size() > 0)
      dropMineral();
    cluster.clear();
  }

  public static void findCluster() {
    cluster = new ArrayList<>();
    Queue<int[]> q = new LinkedList<>();
    boolean[][] visited = new boolean[R][C];

    // 바닥에 붙은 미네랄을 모두 큐에 넣고 같은 클러스터인지 boolean으로 체크
    for (int c = 0; c < C; c++) {
      if (map[R - 1][c].equals("x")) {
        q.add(new int[] { R - 1, c });
        visited[R - 1][c] = true;
      }
    }

    while (q.size() > 0) {
      int[] now = q.poll();
      for (int d = 0; d < 4; d++) {
        int ny = now[0] + dy[d];
        int nx = now[1] + dx[d];
        if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx].equals("."))
          continue;
        if (visited[ny][nx])
          continue;

        visited[ny][nx] = true;
        q.add(new int[] { ny, nx });
      }
    }

    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (map[i][j].equals("x") && !visited[i][j]) {
          // 공중에 떠있고 체크가 안된 미네랄을 리스트에 담음
          cluster.add(new int[] { i, j });
        }
      }
    }
  }
  
  public static void dropMineral() {
		int count = 0;
		// 현재 떨어질 클러스터를 모두 지운다. 
		for(int[] n : cluster) {
          map[n[0]][n[1]] = ".";
		}
		
		// 현재 떨어질 클러스터가 몇칸이나 내려올 수 있는지 체크한다. 
		OUTER: for(int i = 1 ; i < R ; ++i) {
			for(int[] n : cluster) {
				if(n[0] + i >= R || map[n[0] + i][n[1]].equals("x")) {
					break OUTER;
				}
			}
			count = i;
		}
		
		// 계산된 칸 만큼 이동시킨 클러스터를 새로 그린다. 
		for(int[] n : cluster) {
			map[n[0] + count][n[1]] = "x";
		}
	}
}
