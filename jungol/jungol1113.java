package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 정올 1113 구급대 (다익스트라, 시뮬레이션)
// 최소 코너를 도는 횟수를 출력하기
public class jungol1113 {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[N][M];
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine().replaceAll(" ", "");
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                // 코너 돈 횟수를 비교해서 넣음
                return o1[2] - o2[2];
            };
        });
        // 처음에는 오른쪽이랑 아래로만 이동 가능
        // y좌표, x좌표, 코너 돌은 횟수, 이동 방향 순
        if (map[0][1] == 1) {
            pq.add(new int[] { 0, 1, 0, 0 });
        }
        if (map[1][0] == 1) {
            pq.add(new int[] { 1, 0, 0, 2 });
        }

        while (pq.size() > 0) {
            int[] nowPos = pq.poll();
            visited[nowPos[0]][nowPos[1]] = true;

            if (nowPos[0] == m && nowPos[1] == n) {
                System.out.println(nowPos[2]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int ny = nowPos[0] + dy[i];
                int nx = nowPos[1] + dx[i];

                // 이미 방문한 곳, 혹은 벽으로 막혀있을 때는 이동 x
                if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || map[ny][nx] == 0)
                    continue;

                int corner = 0;
                if (nowPos[3] < 2 && i >= 2) {
                    // 좌우로 이동하다가 상하로 꺾일 때
                    corner = nowPos[2] + 1;
                    pq.add(new int[] { ny, nx, corner, i });
                    continue;
                } else if (nowPos[3] >= 2 && i < 2) {
                    // 상하로 이동하다가 좌우로 꺾일 때
                    corner = nowPos[2] + 1;
                    pq.add(new int[] { ny, nx, corner, i });
                    continue;
                }

                pq.add(new int[] { ny, nx, nowPos[2], i });
            }
        }

        System.out.println(-1);
    }
}
