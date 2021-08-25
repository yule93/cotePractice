package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// SWEA 2005 파스칼의 삼각형 (단순 구현)
public class swea2005 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j <= i; j++) {
                    if(j == 0 || j == i) map[i][j] = 1;
                    else map[i][j] = map[i-1][j-1] + map[i-1][j];
                }
            }

            sb.append("#").append(tc).append("\n");
            for(int i = 0; i < N; i++) {
                for(int j = 0; j <= i; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
