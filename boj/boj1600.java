package boj;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ 1600 말이 되고픈 원숭이 (BFS)
public class boj1600 {
    static int[] hdx = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int[] hdy = { 2, 1, -1, -2, -2, -1, 1, 2 };
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = -1;

        int K = Integer.parseInt(br.readLine()); // 원숭이가 말처럼 이동할 수 있는 횟수
        StringTokenizer st = new StringTokenizer(br.readLine()); // 0~30
        int W = Integer.parseInt(st.nextToken()); // 1~200
        int H = Integer.parseInt(st.nextToken()); // 1~200

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];
        // 맵이 1인 곳은 장애물. 맨 위에서 맨 아래로 이동해야 함!
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[] { 0, 0, 0, 0 });
        while (!q.isEmpty()) {
            int[] monkeyPos = q.poll();
            int x = monkeyPos[0];
            int y = monkeyPos[1];
            int k = monkeyPos[2];
            int move = monkeyPos[3];

            // 원숭이가 도착점에 도달했을 때,
            if (x == W - 1 && y == H - 1) {
                answer = move;
                break;
            }
            // 아직 말처럼 뛸 수 있을 때,
            if (k < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + hdx[i];
                    int ny = y + hdy[i];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H)
                        continue;
                    if (map[ny][nx] == 1)
                        continue;
                    if (visited[ny][nx][k + 1])
                        continue;
                    visited[ny][nx][k + 1] = true;
                    q.add(new int[] { nx, ny, k + 1, move + 1 });
                }
            }
            // 말처럼 못 뛸 때
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= W || ny >= H)
                    continue;
                if (map[ny][nx] == 1)
                    continue;
                if (visited[ny][nx][k])
                    continue;
                visited[ny][nx][k] = true;
                q.add(new int[] { nx, ny, k, move + 1 });
            }
        }
        System.out.println(answer);
    }
}

/*
 * 1 / 4 4 / 0 0 0 0 / 1 0 0 0 / 0 0 1 0 / 0 1 0 0
 * 
 * 답: 4
 */
