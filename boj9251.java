import java.util.*;
import java.io.*;

// BOJ 9251: LCS(Longest Common Subsequence)
public class boj9251 {
    static int[][] dp = new int[1001][1001];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String a = br.readLine();
        String b = br.readLine();

        for(int i=0; i<a.length(); i++) {
            for(int j=0; j<b.length(); j++) {
                if(a.charAt(i) == b.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j]+1;
                }
                else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        
        System.out.println(dp[a.length()][b.length()]);
        
        int i = a.length();
        int j = b.length();
        StringBuffer sb = new StringBuffer();

        while(!(i == 0 || j == 0)) {
            if(a.charAt(i-1) == b.charAt(j-1)) {
                sb.append(String.valueOf(a.charAt(i-1)));
                i--;
                j--;
            } else if(dp[i][j] == dp[i-1][j]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.println(sb.reverse().toString());
    }
}
