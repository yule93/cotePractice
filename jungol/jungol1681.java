package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 정올 1681 해밀턴 순환회로 (다익스트라)
// 다익스트라 생각했는데 dfs로 풀어도 되는 것 같음.... N이 작으면 dfs/bfs가 더 나음!
public class jungol1681 {

    static int map[][], cost;
    static int answer = 10000000;
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        visited[0] = true;
        dfs(0, 0, N, 0);
        System.out.println(answer);
    }

    public static void dfs(int now, int depth, int N, int cost) {
        if (depth == N - 1) {
            if (map[now][0] == 0)
                return;
            answer = Math.min(answer, cost + map[now][0]);
            return;
        }

        if (cost > answer)
            return;

        for (int i = 0; i < N; i++) {
            if (visited[i] || map[now][i] == 0)
                continue;

            visited[i] = true;
            dfs(i, depth + 1, N, cost + map[now][i]);
            visited[i] = false;
        }
    }
}
