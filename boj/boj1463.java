package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Queue;

public class boj1463 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 10^6 보다 작거나 같음.
        int[] count = new int[N + 1];

        count[0] = 0;
        for (int i = 2; i <= N; i++) {
            count[i] = count[i - 1] + 1;
            if (N % 3 == 0)
                count[i] = Math.min(count[i], count[i / 3] + 1);
            if (N % 2 == 0)
                count[i] = Math.min(count[i], count[i / 2] + 1);
        }

        bw.write(String.valueOf(count[N]));
        bw.flush();

        // System.out.println(dp(N, 0));
    }

    public static int bfs(Queue<Integer> q, int N) {
        int pCount = 0;
        ex: while (q.size() > 0) {
            for (int i = q.size(); i > 0; i--) {
                N = q.poll();
                if (N == 1)
                    break ex;
                if (N % 3 == 0)
                    q.offer(N / 3);
                if (N % 2 == 0)
                    q.offer(N / 2);
            }
            pCount++;
        }

        return pCount;
    }

    public static int dp(int N, int val) {
        int res = val;
        int calN = N;
        if (calN % 3 == 0) {
            calN /= 3;
        } else if (calN % 2 == 0) {
            calN /= 2;
        } else {
            calN--;
        }

        if (calN < 3)
            return res;

        res++;
        return dp(calN, res);
    }
}
