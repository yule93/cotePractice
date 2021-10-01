package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 정올 1121 행운의 로또 번호
// 단순 dfs라 생각했는데 이분탐색 아니면 메모이제이션... 이용해야 할듯 1초 제한이네....
public class jungol1121 {

    static int N, M, answer;
    static boolean[] visited;
    static long[][] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 1~10 뽑아야 하는 개수
            M = Integer.parseInt(st.nextToken()); // 1~2000 뽑아야 하는 범위
            if (N == 0 && M == 0)
                break;

            // M개의 숫자 중에 N개를 뽑는 경우의 수를 저장하는 메모이제이션 배열
            memo = new long[M + 1][N + 1];
            for (int i = 0; i <= M; i++) {
                memo[i][0] = 0;
                memo[i][1] = i;
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 2; j <= M; j++) {
                    for (int k = j; k >= 1 << (i - 1); k--) {
                        memo[j][i] += memo[k >> 1][i - 1];
                        // System.out.println(j + ", " + i + ": " + memo[j][i]);
                    }
                }
            }

            sb.append(memo[M][N]).append("\n");

            // answer = 0;
            // visited = new boolean[M + 1];
            // dfs(0, 1);
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int depth, int min) {
        // min은 전의 수의 2배가 되어야 함
        if (depth == N) {
            answer++;
            return;
        }

        for (int i = min; i <= M; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            dfs(depth + 1, i << 1);
            visited[i] = false;
        }
    }
}
