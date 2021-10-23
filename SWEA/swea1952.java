package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// SWEA 1952 수영장 (DP)
class swea1952 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            // 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권이 차례대로 주어진다.
            int[] ticketPrice = new int[4];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                ticketPrice[i] = Integer.parseInt(st.nextToken());
            }

            int[] month = new int[12];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                month[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[12];
            Arrays.fill(dp, 1_000_000);
            dp[0] = Math.min(ticketPrice[0] * month[0], ticketPrice[1]);
            dp[1] = dp[0] + Math.min(ticketPrice[1], ticketPrice[0] * month[1]);
            dp[2] = Math.min(ticketPrice[2], Math.min(ticketPrice[1], ticketPrice[0] * month[2]) + dp[1]);
            for (int i = 3; i < 12; i++) {
                // 4월 이상!
                dp[i] = Math.min(ticketPrice[2] + dp[i - 3],
                        dp[i - 1] + Math.min(ticketPrice[1], ticketPrice[0] * month[i]));
                if (i == 11) {
                    dp[i] = Math.min(dp[i], ticketPrice[3]);
                }
                // System.out.println(dp[i]);
            }

            sb.append("#").append(tc).append(" ").append(dp[11]);
        }

        System.out.println(sb.toString());
    }
}