package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// boj 1495 기타리스트(dp)
// * 1학년, 데스노트와 비슷한 유형의 문제. 냅색과 비슷하게 푸는 게 특징인듯....
// 144ms
public class boj1495 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());   // 곡의 개수 1 ~ 50
    int S = Integer.parseInt(st.nextToken());   // 시작 볼륨 0 ~ M
    int M = Integer.parseInt(st.nextToken());   // M보다 큰 값으로 볼륨을 바꿀 수 없음 1 ~ 1000

    int[] dp = new int[M + 1];    // arr[m] = n: n번째 연주에서 볼륨 m으로 연주 가능
    for (int i = 0; i <= M; i++)
      dp[i] = -1;
    
    dp[S] = 0;

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      int v = Integer.parseInt(st.nextToken());
      List<Integer> list = new ArrayList<>();

      for (int j = 0; j <= M; j++) {
        if (dp[j] == i - 1) {
          int plus = j + v;
          int minus = j - v;
          if (plus <= M)
            list.add(plus);
          if (minus >= 0 && minus <= M)
            list.add(minus);
        }
      }

      for (int num : list) {
        dp[num] = i; // i번재 연주에서 num만큼 볼륨을 쓸 수 있다
      }
    }
    
    int answer = -1;
    for (int i = 0; i <= M; i++) {
      if (dp[i] == N) {
        answer = Math.max(answer, i);
      }
    }

    System.out.println(answer);
  }
}
