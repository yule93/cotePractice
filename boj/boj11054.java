package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 11054 가장 긴 바이토닉 수열 (LIS, dp)

// * 1 2 3 4 5
// * 5 4 3 2 1
// 164ms
public class boj11054 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N + 1];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    // 왼쪽에서 오른쪽으로 LIS 구하기
    int[] dpLR = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      dpLR[i] = 1;
      for (int j = 1; j < i; j++) {
        if (arr[i] > arr[j]) {
          dpLR[i] = Math.max(dpLR[j] + 1, dpLR[i]);
        }
      }
    }
    
    // 오른쪽에서 왼쪽으로 LIS 구하기
    int[] dpRL = new int[N + 1];
    for (int i = N; i > 0; i--) {
      // * 산꼭대기
      // * 1 2 3 4 7 5 6 5 4 3 2 1
      dpRL[i] = 1;
      for (int j = N; j > i; j--) {
        if (arr[i] > arr[j]) {
          dpRL[i] = Math.max(dpRL[j] + 1, dpRL[i]);
        }
      }
    }
      
    // 두 dp 배열의 합의 최대값 찾기
    int max = 0;
    for (int i = 1; i <= N; i++) {
      max = Math.max(max, dpLR[i] + dpRL[i]);
    }
    
    System.out.println(max - 1); // 해당 원소 중복되므로 -1

  }
}
