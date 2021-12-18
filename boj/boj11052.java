package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 11052 카드 구매하기 (재귀, dp)
// 168ms
public class boj11052 {
  static int[] pack, dp;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());

    // !카드 팩의 종류 가지수.
    // ! N+1로 하는 이유는 점화식에서 i개가 들은 카드팩을 골랐다는 의미로 재귀를 탈 때, i가 0이면... 1장 팩 무한으로 구매를 해버려서 식이 안 끝남... 이런 경우를 막기 위해 
    pack = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      pack[i] = Integer.parseInt(st.nextToken());
    }

    // 민규는 돈을 최대한 많이 지불해서 카드 N개를 구매하려고 함. 256초 이내므로 메모이제이션+재귀로 진행.... 돌다리 건너기랑 비슷
    dp = new int[N + 1];

    System.out.println(recru(N));
  }
  
  static int recru(int n) {
    if (n == 1)
      return pack[n];
    
    if (dp[n] == 0) {
      for (int i = 1; i <= n; i++) {
        dp[n] = Math.max(recru(n - i) + pack[i], dp[n]);
      }
    }

    return dp[n];
  }
}
