package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

// BOJ 2098: 외판원 순회, TSP(Traveling Salesman problem), 다익스트라, 비트마스크
public class boj2098 {
    static int N;
    static int[][] cost, dp;
    static final int MAX = 16000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		dp = new int[N][(1 << N) - 1];
        cost = new int[N][N];

        for(int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(input[j]);
			}
        }

        for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], MAX);
		}

		bw.write(dfs(1, 0) + "\n");
		bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int k, int node) {
        if (k == (1 << N) - 1) { // base case
			if (cost[node][0] == 0)
				return MAX;
			return cost[node][0];
		}

		if (dp[node][k] != MAX) {
			return dp[node][k];
		}

		for (int i = 0; i < N; i++) {
			if ((k & (1 << i)) == 0 && cost[node][i] != 0) {
				dp[node][k] = Math.min(dp[node][k], dfs(k | (1 << i), i) + cost[node][i]);
			}
		}

		return dp[node][k];
    }
}
