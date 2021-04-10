import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj9663 {
    static int N, ans;

    static boolean[] col, slash, backslash;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        col = new boolean[N];
        slash = new boolean[2 * N - 1];
        backslash = new boolean[2 * N - 1];

        ans = 0;
        solve(0);
        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int r) {
        if (r == N) {
            ans++;
            return;
        }
        for (int c = 0; c < N; c++) {
            if (check(r, c)) {
                col[c] = slash[r + c] = backslash[r - c + N - 1] = true;
                solve(r + 1);
                col[c] = slash[r + c] = backslash[r - c + N - 1] = false;
            }
        }
    }
    public static boolean check(int r, int c) {
        if (col[c] || slash[r + c] || backslash[r - c + N - 1])
            return false;
        return true;
    }
}
