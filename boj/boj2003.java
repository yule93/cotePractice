package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 2003 수들의 합2 (투 포인터)
// 160ms
public class boj2003 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken()); // * N개의 수열 (1~10_000)
    int M = Integer.parseInt(st.nextToken()); // * 합으로 나와야 하는 수 (1~300_000_000)

    st = new StringTokenizer(br.readLine());
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int s = 0; // * 투포인터 시작점
    int e = 0; // * 투포인터 끝점
    int answer = 0, sum = 0;
    while (true) {
      if (sum >= M)
        sum -= arr[s++];
      else if (e == N)
        break;
      else
        sum += arr[e++];
      if (sum == M)
        answer++;
    }

    System.out.println(answer);
  }
}