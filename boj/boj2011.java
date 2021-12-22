package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 2011 암호코드 (DP)
// 128 ms
public class boj2011 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 5000자리 이하의 암호가 주어짐.
    String N = br.readLine(); // 입력된 암호 문자열
    int c[] = new int[N.length()+1];  // 암호를 한 글자씩 저장할 배열(C랑 비슷한 그런 느낌으로)
    int dp[] = new int[N.length() + 1];
    
    dp[0] = 1;

    for (int i = 1; i <= N.length(); i++) {
      c[i] = N.charAt(i - 1) - '0';
    }

    if (c[1] == 0) {
      // 알파벳이 1~26이므로 0은 불가능
      System.out.println(0);
      return;
    }

    // dp
    dp[1] = 1;
    for (int i = 2; i <= N.length(); i++) {
      if (c[i] == 0) {
        // 앞의 숫자가 1 아니면 2여야만 알파벳으로 치환 가능
        if (c[i - 1] == 1 || c[i - 1] == 2)
          dp[i] = dp[i - 2];
        else {
          System.out.println(0);
          return;
        }
      } else {
        if (c[i - 1] == 1) {
          dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000;
        } else if (c[i - 1] == 2) {
          if (c[i] <= 6 && c[i] >= 1) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000;
          } else {
            dp[i] = dp[i - 1];
          }
        } else {
          dp[i] = dp[i - 1];
        }
      }
    }
    
    System.out.println(dp[N.length()]);
  }
}
