package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 7236 저수지 (탐색, 2차원 배열)
public class swea7236 {

    // 시계방향으로 8방
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N;
    static String[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            // 저수지의 가로세로 길이
            int answer = 0;
            N = Integer.parseInt(br.readLine());
            map = new String[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < N; j++) {
                    map[i][j] = st.nextToken();
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j].equals("W")) {

                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int count(int row, int col) {
        int count = 0;
        for(int i = 0; i < 8; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];
            if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc].equals("W")) count++;
        }
        if(count == 0) return 1;
        return count;
    }
}
