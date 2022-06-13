package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 2212 센서(정렬, 그리디)
// 192ms
public class boj2212_1 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    int[] sensor = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      sensor[i] = Integer.parseInt(st.nextToken());
    }

    if (N == K) {
      System.out.println(0);
      return;
    }

    Arrays.sort(sensor);
    // 1 3 6 6 7 9
    int[] diff = new int[N - 1];
    for (int i = 0; i < N - 1; i++) {
      diff[i] = sensor[i + 1] - sensor[i];
    }

    Arrays.sort(diff);
    int answer = 0;
    for (int i = 0; i < N - K; i++) {
      answer += diff[i];
    }

    System.out.println(answer);
  }
}
