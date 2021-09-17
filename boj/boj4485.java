package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj4485 {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[][] map;
        int[][] distance;
        for (int tc = 1;; tc++) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;

            map = new int[N][N];
            distance = new int[N][N];
            for (int i = 0; i < N; i++) {
                String str = br.readLine().replace(" ", "");
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                    distance[i][j] = 10000000;
                }
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    return o1[2] - o2[2];
                }
            });

            pq.add(new int[] { 0, 0, map[0][0] });
            distance[0][0] = map[0][0];

            while (pq.size() > 0) {
                int[] nowPos = pq.poll();
                // System.out.println("y: " + nowPos[0] + ", x:" + nowPos[1]);
                if (distance[nowPos[0]][nowPos[1]] < nowPos[2])
                    continue;
                for (int i = 0; i < 4; i++) {
                    int ny = nowPos[0] + dy[i];
                    int nx = nowPos[1] + dx[i];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N)
                        continue;
                    if (distance[ny][nx] > distance[nowPos[0]][nowPos[1]] + map[ny][nx]) {
                        distance[ny][nx] = distance[nowPos[0]][nowPos[1]] + map[ny][nx];
                        pq.add(new int[] { ny, nx, distance[ny][nx] });
                    }
                }
            }

            // 도착지점에서 도둑맞은 루피의 개수 중 최소를 구해야 하므로,
            sb.append("Problem ").append(tc).append(": ").append(distance[N - 1][N - 1]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
