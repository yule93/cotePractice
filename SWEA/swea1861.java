package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 1861 정사각형 방 (완탐 or DFS)
public class swea1861 {
    static int[][] map;
	static int N;
	static boolean[][] visit;
	static int idx, max, cnt;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	// public static void dfs(int start, int depth, int x, int y) {
	// 	visit[x][y] = true;
		
	// 	for(int i = 0; i < 4; i++) {
	// 		int nx = x + dx[i];
	// 		int ny = y + dy[i];
			
	// 		if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
	// 			if(map[nx][ny] == (map[x][y] + 1) && !visit[nx][ny]) dfs(start, depth + 1, nx, ny);
	// 		}
	// 	}
	// 	if(depth > max) {
	// 		max = depth;
	// 		idx = start;
	// 	}
	// 	if(depth == max) {
	// 		idx = Math.min(idx, start);
	// 	}
	// 	visit[x][y] = false;
	// }
	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
            map = new int[N+2][N+2];
             
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            // 상하좌우에서 현재 값보다 +1 있으면 이동 없으면 break
            int max = Integer.MIN_VALUE;
            int res_x = 0;
            int res_y = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    cnt = 1;
                    DFS(j, i);
                    if (cnt > max) {
                        res_x = j;
                        res_y = i;
                        max = cnt;
                    }
                    if (cnt == max && map[i][j] < map[res_y][res_x]) {
                        res_x = j;
                        res_y = i;
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(map[res_y][res_x]).append(" ").append(max).append("\n");
        } // end of for testCase
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
        private static void DFS(int x, int y) {
 
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (map[ny][nx] == map[y][x] + 1) {
                cnt++;
                DFS(nx, ny);
            }
        }
        return;
    }
}