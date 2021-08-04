package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 2001번: 파리 퇴치
public class swea2001 {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[][] map;                // 파리수 저장할 맵 배열
        int[] dx = {0, 1, 1, 0};       // 제자리, 우, 하우, 하
        int[] dy = {0, 0, 1, 1};       // 제자리, 우, 하우, 하
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());        // N과 M
            int N = Integer.parseInt(st.nextToken());       // 배열의 가로세로 길이
            int M = Integer.parseInt(st.nextToken());       // 파리채의 가로세로 길이
            map = new int[N][N];
            int answer = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i <= N-M; i++) {
                for(int j = 0; j <= N-M; j++) {
                    int sum = 0;
                    for(int k = i; k < i+M; k++) {          // i는 map의 기준이 될 y좌표
                        for(int l = j; l < j+M; l++) {      // j는 map의 기준이 될 x좌표
                            sum += map[k][l];
                        }
                    }
                    answer = Math.max(sum, answer);
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }           // end of main
}               // end of class
