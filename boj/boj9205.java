package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj9205 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int beerMax = 20;
        int[][] pos;
        int[][] distance;
        boolean[][] visited;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine()); // 맥주 파는 편의점 개수
            pos = new int[n + 2][2];
            distance = new int[n + 2][n + 2];
            visited = new boolean[n + 2][n + 2];
            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                pos[i][0] = Integer.parseInt(st.nextToken());
                pos[i][1] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    int[] pos1 = pos[i], pos2 = pos[j];
                    distance[i][j] = Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
                    // 50미터당 맥주 한 병씩 마셔야 하는데 만약 거리가 남아있는 맥주보다 작으면 갈 수 있는 곳임!
                    if (distance[i][j] <= 50 * beerMax)
                        visited[i][j] = true;
                }
            }

            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    for (int k = 0; k < n + 2; k++) {
                        if (visited[i][k] && visited[j][i])
                            visited[j][k] = true;
                    }
                }
            }

            if (visited[0][n + 1])
                sb.append("happy").append("\n");
            else
                sb.append("sad").append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
