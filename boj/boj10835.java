package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj10835 {

    static int N;
    static int[][] dp;
    static int[] leftDeck, rightDeck;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 한 더미의 카드 개수(즉, 오른쪽과 왼쪽에 똑같이 나눠질 카드 개수)
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = 0;
            }
        }

        rightDeck = new int[N];
        leftDeck = new int[N];

        // 왼쪽, 오른쪽 순서대로 한 줄씩 주어짐
        st = new StringTokenizer(br.readLine().trim());
        for (int j = 0; j < N; j++) {
            leftDeck[j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine().trim());
        for (int j = 0; j < N; j++) {
            rightDeck[j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Topdown(0, 0));
    }

    static int Topdown(int l, int r) {
        if (l == N || r == N)
            return 0;

        if (dp[l][r] != -1)
            return dp[l][r];

        // l+1, r+1 : 양쪽 카드를 동시에 제거하는 경우
        // l+1, r : 왼쪽 카드만 제거하는 경우
        dp[l][r] = Math.max(Topdown(l + 1, r + 1), Topdown(l + 1, r));

        if (leftDeck[l] > rightDeck[r]) {
            dp[l][r] = Math.max(dp[l][r], Topdown(l, r + 1) + rightDeck[r]);
        }
        return dp[l][r];
    }
}
