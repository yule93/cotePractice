package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 1978 소수 찾기 (에라토스테네스 체)
// 132ms
public class boj1978 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    int answer = 0;
    out:
    for (int n = 0; n < N; n++) {
      if (arr[n] == 1)
        continue;
      for (int j = 2; j*j <= arr[n]; j++) {
        if (arr[n] % j == 0)
          continue out;
      }
      answer++;
    }
    System.out.println(answer);
  }

}
