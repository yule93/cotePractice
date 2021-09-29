package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// SWEA 6026 성수의 비밀번호 공격(수학, 중복순열)
public class swea6026 {

    static int MAX_MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            // M은 비밀번호 후보 개수, N은 비밀번호 자리수. 즉, NPM의 중복 순열을 뽑아야 함...!
            // 근데 중요한건 M에 해당하는 비밀번호 후보 키가 한 번 이상은 들어간다는 점!!!
            // 결국 그러면 N-M개를 M 내에서 뽑아야 한다는 말이네용...? 이미 1개씩 뽑힌
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            long[] dp = new long[N + 1];
            dp[0] = 1;
            dp[1] = 1;

            // 아 왤케 크냐고 6번째가
            for (int i = 2; i <= M; i++) {
                dp[i] = (long) makePow(i, N) % MAX_MOD;
                if (i == M)
                    System.out.println(i + ": " + dp[i]);
                for (int j = 1; j < i; j++) {
                    // dp[i] += jC1*dp[j]*(-1)^(i-j)
                    dp[i] += ((dp[j] % MAX_MOD) * (combi(i, j) % MAX_MOD)) * makePow(-1, i - j) % MAX_MOD;
                    if (i == M)
                        System.out.println(dp[j]);
                }
            }
            sb.append("#").append(tc).append(" ").append(dp[M]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int combi(int n, int r) {
        if (r == 0 || r == n)
            return 1;
        else
            return combi(n - 1, r - 1) + combi(n - 1, r);
    }

    public static int makePow(int n, int m) {
        if (m == 1)
            return n % MAX_MOD;
        else if (m == 0)
            return 1;
        return makePow(n, m - 1) * n % MAX_MOD;
    }
}
