package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 2749 피보나치 수 3 (dp, 분할정복을 이용한 탐색, 피사노 주기)
// 164ms
public class boj2749 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long N = Long.parseLong(br.readLine()); // 1~이거 무슨 숫자임... 무서워.... 10경까지 1,000,000,000,000,000,000
    
    // ! 피사노 주기...? 라는 걸 써야 한다고 하네요
    // * 모듈러가 10^k, 주기는 15*10^(k-1)이다. 이 때, k가 지금 6....
    int cycle = (int) (N % 1_500_000);
    int mod = 1_000_000;
    
    long[] fibo = new long[cycle + 1];

    fibo[0] = 0;
    fibo[1] = 1;

    for (int i = 2; i <= cycle; i++) {
      fibo[i] = (fibo[i - 1] + fibo[i - 2]) % mod;
    }

    System.out.println(fibo[cycle]);
  }
}
