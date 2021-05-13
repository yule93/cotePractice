package boj;
import java.util.*;
import java.io.*;

// boj 2468: 안전 영역
// 연구소랑 비슷한 문제인 것 같음
// 불 문제랑도 비슷한듯
public class boj2468 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int N;
    static boolean[][] visited;

    static public class pos {
        int x, y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static public void dfs(int x, int y) {     // 비 내리고 나서 
        visited[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int curX = x + dx[i];
            int curY = y + dy[i];

            if (curX < 0 || curY < 0 || curX >= N || curY >= N || visited[curX][curY]) continue;

            dfs(curX, curY);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int max = 0;        // 가장 높은 지역. 이만큼 비 오면 다 잠겨서 0이 되므로 가장 많이 올 수 있는 비의 양으로 제한선을 정해두는 것
        int result = 0;      // 안전 영역 갯수(가장 큰 수로 저장할거임)

        StringTokenizer st;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        for(int i = 0; i <= max; i++) {
            visited = new boolean[N][N];
            int count = 0;
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    if(map[j][k] <= i) visited[j][k] = true;
                }
            }

            for(int j = 0; j < N; j++) {
                for(int k = 0; k <N; k++) {
                    if(!visited[j][k] && map[j][k] > i) {
                        count++;
                        dfs(j, k);
                    }
                }
            }
            bw.write(String.valueOf(i + ": " + count + "\n"));
            result = Math.max(result, count);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

}
