package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 1249 보급로 (다익스트라, BFS)
public class swea1249 {

    // static int[][] dijkstra, map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[][] map, copyMap;
        boolean[][] visited;

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int MAX = 1_000_000_000;
            visited = new boolean[N][N];
            map = new int[N][N];
            copyMap = new int[N][N];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                    copyMap[i][j] = MAX;
                }
            }

            copyMap[0][0] = 0;
            // BFS
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    // 오름차순
                    return o1[2] - o2[2];
                }
            });

            // Queue<int[]> pq = new LinkedList<>();
            pq.add(new int[] { 0, 0, map[0][0] });
            while (pq.size() > 0) {
                int[] nowPos = pq.poll();
                visited[nowPos[0]][nowPos[1]] = true;

                for (int m = 0; m < 4; m++) {
                    int ny = nowPos[0] + dy[m];
                    int nx = nowPos[1] + dx[m];

                    if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx])
                        continue;

                    int val = nowPos[2] + map[ny][nx];
                    if (val > copyMap[ny][nx])
                        continue;

                    copyMap[ny][nx] = val;
                    // System.out.println("ny: " + ny + ", nx: " + nx + ", val: " + val);
                    pq.add(new int[] { ny, nx, copyMap[ny][nx] });
                }
            }

            sb.append("#").append(tc).append(" ").append(copyMap[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
