package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 7576번: 토마토
public class boj7576 {
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
 
    public static void main(String args[]) throws Exception {
        //BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int M = Integer.parseInt(str[0]);		// 열의 갯수, x좌표
        int N = Integer.parseInt(str[1]);		// 행의 갯수, y좌표
 
        int[][] arr = new int[N][M];
 
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
 
            }
        }
        //----------------- 입력부 ------------------
        BFS(arr, N, M);
    }
 
    public static void BFS(int[][] arr, int N, int M) {
        Queue<DOT> q = new LinkedList<DOT>();
 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1)
                    //익은 토마토가 있는 모든 위치를 큐에 담는다.
                    q.add(new DOT(i, j));
            }
        }
 
        while (!q.isEmpty()) {
            //익은 토마토의 상하좌우는 다음에 익기 때문에 큐에 담아야한다.
            DOT dot = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = dot.x + dx[i];
                int nextY = dot.y + dy[i];

                //범위 밖 패스
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }
                //다음 위치가 익지 않은 토마토가 아니면 패스
                if (arr[nextX][nextY] != 0) {
                    continue;
                }
                //최대 일수를 구하기 때문에 1로 바꾸는 것이 아니라 현재 일수 +1 을 해줘야한다.
                arr[nextX][nextY] = arr[dot.x][dot.y] + 1;
                q.add(new DOT(nextX, nextY));
            }
            print(arr, N, M); // 농장 전체 출력
            System.out.println();
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    // 익지 못한 토마토가 하나라도 있다면 -1 을 출력한다.
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, arr[i][j]);
            }
        }
        //그렇지 않다면 최대값을 출력한다.
        System.out.println(max - 1);
 
    }
    //농장을 전체 보여주는 함수
    public static void print(int[][] arr, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
 
class DOT {
    int x;
    int y;
 
    DOT(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/*class Pair2 {
    int x;
    int y;
 
    Pair2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class boj7576 {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        String[] str1 = br.readLine().split(" ");
 
        int m = Integer.parseInt(str1[0]);
        int n = Integer.parseInt(str1[1]);
 
        int[][] arr = new int[n][m];
        int[][] dist = new int[n][m];
 
        Queue<Pair2> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] str2 = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = str2[j].charAt(0) - '0';
                if (arr[i][j] == 1) {
                    queue.add(new Pair2(i, j));
                }
            }
        }
 
        while (!queue.isEmpty()) {
            Pair2 p = queue.remove();
            int x = p.x;
            int y = p.y;
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
 
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (arr[nx][ny] == 0 && dist[nx][ny] == 0) {
                        queue.add(new Pair2(nx, ny));
                        dist[nx][ny] = dist[x][y] + 1;
                    }
                }
            }
        }
 
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dist[i][j]);
            }
        }
 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0 && dist[i][j] == 0) {
                    ans = -1;
                }
            }
        }
 
        System.out.println(ans);
	}
}*/