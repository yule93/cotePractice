package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// SWEA 6026 성수의 비밀번호 공격(수학, 중복순열)
public class swea6026_2 {

    static int MAX_MOD = 1_000_000_007;
    static int M, N;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        makeDP();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            // M은 비밀번호 후보 개수, N은 비밀번호 자리수. 즉, NPM의 중복 순열을 뽑아야 함...!
            // 근데 중요한건 M에 해당하는 비밀번호 후보 키가 한 번 이상은 들어간다는 점!!!
            // 결국 그러면 N-M개를 M 내에서 뽑아야 한다는 말이네용...? 이미 1개씩 뽑힌
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            long answer = solve();
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static long solve() {
        long answer = 0;
        for (int i = 0; i <= M; i++) {
            long combiFirst = i % 2 == 0 ? 1 : -1;
            long combiRes = combi(i);
            long res3 = pow(M - i, N);
            long result = ((combiFirst * combiRes) % MAX_MOD * res3) % MAX_MOD;
            answer = (answer + result + MAX_MOD) % MAX_MOD;
        }
        return answer;
    }

    public static long combi(int r) {
        if (r == 0)
            return 1;

        long result = dp[M];
        long beforeRes = pow(dp[M - r], MAX_MOD - 2);
        long afterRes = pow(dp[M - r], MAX_MOD - 2);

        return ((result * beforeRes) % MAX_MOD * afterRes) % MAX_MOD;
    }

    public static void makeDP() {
        dp = new long[101];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 101; i++) {
            dp[i] = (dp[i - 1] * i) % MAX_MOD;
        }
    }

    public static long pow(long a, int b) {
        // a를 b번 제곱
        if (b == 1)
            return a;
        long half = pow(a, b / 2);
        long mod = (half * half) % MAX_MOD;
        if (b % 2 == 0) {
            return mod;
        } else {
            return (mod * (a % MAX_MOD)) % MAX_MOD;
        }

    }
}
