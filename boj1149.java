import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {	
    public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N][3];
        int[][] cost = new int[N][3];
        
        for(int i = 0; i < N; i++) {
            StringTokenizer tok = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(tok.nextToken());
            cost[i][1] = Integer.parseInt(tok.nextToken());
            cost[i][2] = Integer.parseInt(tok.nextToken());
        }
        
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];
        
        for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
		}
        
		bw.write(String.valueOf(Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2])));
		bw.flush();
		bw.close();
		br.close();
	}
}