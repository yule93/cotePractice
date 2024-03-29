package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj14502 {
    static int N, M, res = 0;
    static int[][] map, infectedMap;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, -1, 0, 1 };
    static Queue<int[]> q;
    static ArrayList<int[]> virus;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        map = new int[N][M];
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2)
                    virus.add(new int[] { i, j });
            }
        }

        setupWall(0);
        System.out.println(res);
    }

    public static void setupWall(int cnt) {
        if (cnt == 3) {
            spreadVirus();
            return;
        }

        for (int i = 0; i < N * M; i++) {
            if (map[i / M][i % M] == 0) {
                map[i / M][i % M] = 1;
                setupWall(cnt + 1);
                map[i / M][i % M] = 0;

            }
        }
    }

    public static void spreadVirus() {
        infectedMap = new int[N][M];
        for (int i = 0; i < N; i++)
            System.arraycopy(map[i], 0, infectedMap[i], 0, M);

        q = new LinkedList<int[]>();
        for (int i = 0; i < virus.size(); i++) {
            int[] v = virus.get(i);
            q.add(new int[] { v[0], v[1] });
            while (!q.isEmpty()) {
                int[] now = q.poll();

                for (int d = 0; d < 4; d++) {
                    int ny = now[0] + dy[d];
                    int nx = now[1] + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= M)
                        continue;
                    if (infectedMap[ny][nx] == 1 || infectedMap[ny][nx] == 3)
                        continue;
                    infectedMap[ny][nx] = 3;
                    q.add(new int[] { ny, nx });
                }
            }
        }

        int sum = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (infectedMap[x][y] == 0)
                    sum += 1;
            }
        }

        res = Math.max(res, sum);
    } // end of main
} // end of class
