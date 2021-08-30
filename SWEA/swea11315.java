package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class swea11315 {

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            char[][] map = new char[N][N];
            for(int j = 0; j < N; j++) {
                String str = br.readLine();
                for(int i = 0; i < N; i++) {
                    map[j][i] = str.charAt(i);
                }
            }

            boolean flag = false;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 'o') {
                        for(int k = 0; k < 8; k++) {
                            int count = 1;
                            while(count < 5) {
                                int nr = i + dr[k] * count;
                                int nc = i + dc[k] * count;
                                if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
                                else if(map[nr][nc] != 'o') break;
                                if(count == 4) {
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            if(flag) sb.append("#").append(tc).append(" ").append("YES").append("\n");
            else sb.append("#").append(tc).append(" ").append("NO").append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
