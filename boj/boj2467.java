package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 2467 용액(이분탐색, 투 포인터로도 되나봐요)
// N이 10만이라 괜찮은듯...? 대신에 Long 타입인 거 주의하기....
// 416ms
public class boj2467 {
  static int N;
  static long[] arr;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    arr = new long[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Long.parseLong(st.nextToken());
    }

    long min = Long.MAX_VALUE;
    int ml = 0, mr = 0;
    for (int i = 0; i < N - 1; i++) {
      int left = i + 1;
      int right = N - 1;
      while (left <= right) {
        int mid = (left + right) / 2;
        long sum = Math.abs(arr[i] + arr[mid]);

        if (min > sum) {
          min = sum;
          ml = i;
          mr = mid;
        }
        if (arr[mid] >= -arr[i]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }
    }
    System.out.println(arr[ml] + " " + arr[mr]);
  }
}
