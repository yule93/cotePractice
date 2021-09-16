package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// SWEA 1263 사람 네트워크2 (플로이드 와샬, 다익스트라)
public class swea1263 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            // for (int[] i : map) {
            // Arrays.fill(i, 100000);
            // }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    map[i][j] = val;
                    if (map[i][j] == 0 && i != j)
                        map[i][j] = 1000000;
                }
            }

            int answer = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                int temp = 0;
                for (int j = 0; j < N; j++) {
                    temp += map[i][j];
                }
                // System.out.println("answer: " + answer + ", temp: " + temp);
                answer = Math.min(answer, temp);
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}

/*
 * #1 2 / #2 3 / #3 25 / #4 37 / #5 16 / #6 11 / #7 21 / #8 20 / #9 715 / #10
 * 1449
 */
