package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj11399 ATM(그리디)
// 각 사람이 돈을 인출하는 데 최소한으로 걸리는 시간을 출력하기
// 136ms
public class boj11399 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    // 그리디 시작
    Arrays.sort(arr);
    for (int i = 1; i < N; i++) {
      arr[i] += arr[i - 1];
    }

    int answer = 0;
    for (int time : arr) {
      answer += time;
    }

    System.out.println(answer);
  }
}
