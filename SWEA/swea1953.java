package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 1953 탈주범 검거(진짜 걍 bfs로 탐색하기 문제)
public class swea1953 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        int[] dy = { -1, 1, 0, 0, 1, 1 };
        int[] dx = { 0, 0, -1, 1, 1, -1 };
        // 0번부터 8번까지 파이프를 통해 이동할 수 있는 탈주범의 이동범위
        // 0은 벽이니까 0에서 0
        // 들어오는 위치에 따라 -1을 곱해주거나 아니게 해줌!
        // int[][] pipeNum = { { 0, 0 }, { 0, 4 }, { 0, 2 }, { 2, 4 }, { 4, 5 }, { 5, 6
        // }, { 4, 5 }, { 5, 6 } };
        int[][] map;
        boolean[][] visited;

        for (int tc = 1; tc <= T; tc++) {
            int count = 1; // 탈주범이 위치할 수 있는 공간 카운팅한 수. 맨홀 위치에는 무조건 들어갈 수 있으므로 1부터 세어준다.
            st = new StringTokenizer(br.readLine().trim());

            int N = Integer.parseInt(st.nextToken()); // 지하 터널 지도 세로 크기
            int M = Integer.parseInt(st.nextToken()); // 지하 터널 지도 가로 크기
            int R = Integer.parseInt(st.nextToken()); // 맨홀의 세로 위치
            int C = Integer.parseInt(st.nextToken()); // 맨홀의 가로 위치
            int L = Integer.parseInt(st.nextToken()); // 탈출 후 소요시간. 즉 BFS 탐색을 할 수 있는 횟수

            visited = new boolean[N][M];
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                String str = br.readLine().trim().replaceAll(" ", "");
                for (int j = 0; j < M; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] { R, C });

            int time = 0;
            while (q.size() > 0) {
                int size = q.size();
                if (++time >= L)
                    break;
                for (int s = 0; s < size; s++) {
                    int[] nowPos = q.poll();
                    visited[nowPos[0]][nowPos[1]] = true;
                    int pipe = map[nowPos[0]][nowPos[1]];

                    for (int i = 0; i < 4; i++) {
                        int ny = nowPos[0] + dy[i];
                        int nx = nowPos[1] + dx[i];

                        if (ny < 0 || ny >= N || nx < 0 || nx >= M)
                            continue;
                        if (visited[ny][nx] || map[ny][nx] == 0)
                            continue;

                        int next = map[ny][nx];
                        switch (i) {
                            case 0:
                                if (pipe == 1 || pipe == 2 || pipe == 4 || pipe == 7) {
                                    if (next == 1 || next == 2 || next == 5 || next == 6) {
                                        visited[ny][nx] = true;
                                        q.offer(new int[] { ny, nx });
                                        count++;
                                    }
                                }
                                break;
                            case 1:
                                if (pipe == 1 || pipe == 2 || pipe == 5 || pipe == 6) {
                                    if (next == 1 || next == 2 || next == 4 || next == 7) {
                                        visited[ny][nx] = true;
                                        q.offer(new int[] { ny, nx });
                                        count++;
                                    }
                                }
                                break;
                            case 2:
                                if (pipe == 1 || pipe == 3 || pipe == 6 || pipe == 7) {
                                    if (next == 1 || next == 3 || next == 4 || next == 5) {
                                        visited[ny][nx] = true;
                                        q.offer(new int[] { ny, nx });
                                        count++;
                                    }
                                }
                                break;
                            case 3:
                                if (pipe == 1 || pipe == 3 || pipe == 4 || pipe == 5) {
                                    if (next == 1 || next == 3 || next == 6 || next == 7) {
                                        visited[ny][nx] = true;
                                        q.offer(new int[] { ny, nx });
                                        count++;
                                    }
                                }
                                break;
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.println(sb.toString());
    }
}