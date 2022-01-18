package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 2639 N번째 큰 수(그리디, 정렬)
// 156ms
public class boj2639 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] arr = new int[10];
      for (int i = 0; i < 10; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);
      sb.append(arr[7]).append("\n");
    }
    
    System.out.print(sb.toString());
  }
}
