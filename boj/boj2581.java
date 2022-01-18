package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 2581 소수
// 144ms
public class boj2581 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int M = Integer.parseInt(br.readLine());
    int N = Integer.parseInt(br.readLine());
    boolean[] prime = new boolean[N+1];

    prime[0] = prime[1] = true;
    for (int i = 2; i <= N; i++) {
      for (int j = i*i; j <= N; j += i) {
        prime[j] = true;
      }
    }

    int min = N;
    int sum = 0;
    for (int i = M; i <= N; i++) {
      if (!prime[i]) {
        min = Math.min(min, i);
        sum += i;
      }
    }

    if (sum == 0) {
      System.out.println(-1);
      return;
    }

    System.out.println(sum +"\n"+min);
  }
}
