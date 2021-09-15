package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ 2636 치즈 (BFS, 시뮬레이션)
public class boj2636 {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int hour = 0;
        int cheeseCount = 0;
        int beforeCheese = 0;

        int H = Integer.parseInt(st.nextToken()); // 세로
        int W = Integer.parseInt(st.nextToken()); // 가로

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 1)
                    cheeseCount++;
            }
        }

        while (cheeseCount > 0) {
            beforeCheese = cheeseCount;
            visited = new boolean[H][W];
            Queue<int[]> q = new LinkedList<int[]>();
            q.offer(new int[] { 0, 0 }); // 0, 0 부터 탐색 시작
            visited[0][0] = true;
            while (q.size() > 0) {
                int[] curPos = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = curPos[1] + dx[i];
                    int ny = curPos[0] + dy[i];

                    // System.out.println("nx: " + nx + ", ny: " + ny);

                    if (nx < 0 || nx >= W || ny < 0 || ny >= H || visited[ny][nx]) {
                        continue;
                    }

                    if (map[ny][nx] == 1) {
                        cheeseCount--;
                        map[ny][nx] = 0;
                    } else if (map[ny][nx] == 0) {
                        q.offer(new int[] { ny, nx });
                    }

                    visited[ny][nx] = true;
                }
                // System.out.println(hour + "시간 째: " + cheeseCount);
            }
            hour++;
        }

        System.out.println(hour);
        System.out.println(beforeCheese);
    }
}
