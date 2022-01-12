package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 2629 양팔저울(냅색)
// 128mx
public class boj2629 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine()); // 30개 이하
    StringTokenizer st = new StringTokenizer(br.readLine());

    boolean[] dp = new boolean[40001]; // 구슬의 무게는 4만보다 작거나 같은 자연수이므로 냅색 문제라 생각하면 4만까지 배열을 만들어야 한다.

    int max = 0;
    int value;
    for (int i = 0; i < N; i++) {
      value = Integer.parseInt(st.nextToken());
      max = Math.max(value, max);
      // ! dp가 반복문 내에서 변경되지 않도록 temp 배열을 만들음
      boolean[] temp = new boolean[40001];
      int maxTemp = max;

      // 주어진 각 구슬의 무게에 대해 확인이 가능하다면 Y, 아니면 N을 출력
      // 냅색 점화식 ㄱㄱ
      for (int n = 0; n <= max; n++) { // 현재 확인할 수 있는 가장 큰 무게까지
        if (dp[n]) { // 만약 무게 n을 확인할 수 있다면
          // n 과 현재 받아온 추의 무게를 더한 무게와,
          temp[n + value] = true;
          // n과 현재 받아온 추의 무게를 뺀 값의 절대값 무게는 확인할 수 있다.
          temp[Math.abs(value - n)] = true;
          maxTemp = Math.max(maxTemp, n + value);
        }
      }
      max = maxTemp;

      for (int j = 0; j <= max; j++) { // 현재 확인할 수 있는 가장 큰 무게까지
        if (temp[j])
          dp[j] = true;
      }
      dp[value] = true;
    }

    int M = Integer.parseInt(br.readLine()); // 7개 이하
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      int weight = Integer.parseInt(st.nextToken());
      if (dp[weight])
        sb.append("Y ");
      else
        sb.append("N ");
    }

    System.out.print(sb.toString());
  }
}
