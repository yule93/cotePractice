package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 2212 센서(정렬, 그리디)
// * 집중국이 2개 이상의 센서를 담당해야할 때, 서로 붙어있는 센서를 하나의 기지국이 처리하면 좋음!
// * diff는 옆에 있는 센서간 거리를 저장한 배열로, 작은 거리를 기지국이 커버할 수록 최단 거리 합을 구하기 좋음
// ! 예로 6개의 센서가 있는데 4개의 기지국이 있다면 2개의 센서는 각 2개의 기지국이 한 개씩 더 커버하던지, 1개의 기지국이 세 개를 커버하면 됨.
// ! 즉, 6-4개의 센서 중에서 간격이 짧은 센서들을 커버하면 굉장히 좋음!
// 200ms
public class boj2212 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine()); // 센서의 개수 1~1만
    int K = Integer.parseInt(br.readLine()); // 집중국의 개수 1~1000
    
    if (K >= N) {
      System.out.println(0);
      return;
    }

    int[] censor = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      censor[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(censor);

    int[] diff = new int[N - 1];
    for (int i = 0; i < N - 1; i++) {
      diff[i] = censor[i + 1] - censor[i];
    }
    Arrays.sort(diff);

    int answer = 0;
    for (int i = 0; i < N -K; i++) {
      answer += diff[i];
    }

    System.out.println(answer);
  }
}
