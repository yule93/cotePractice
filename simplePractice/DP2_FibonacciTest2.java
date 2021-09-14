package simplePractice;

public class DP2_FibonacciTest2 {
    private static long fibo1(int n) {
        long[] DP = new long[n + 1];
        DP[0] = 0;
        DP[1] = 1;
        for (int i = 2; i <= n; ++i) {
            DP[i] = DP[i - 1] + DP[i - 2];
        }
        return DP[n];
    }

    public static void main(String[] args) {

    }
}
