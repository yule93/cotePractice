package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 7453 합이 0인 네 정수
// * 정수의 절댓값은 최대 2^28이어서... long으로 해야할듯
// * 완전탐색은 N^4로 최대 4000^4 ㅋㅋㅋㅋ가 되기 때문에 이진탐색 or 그리디 중 시간 절약이 가능한 걸 해야함
// * 이 때, A+B+C+D를 구하는 것이므로 A+B와 C+D를 먼저 구해서 -(A+B) = C+D 인 요소들을 찾아도 괜찮음
// 6384ms ㄴㅇㄱ
public class boj7453 {
  static long[] A, B, C, D;
  static long[] AB, CD;
  static int N;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;

    N = Integer.parseInt(br.readLine());

    A = new long[N];
    B = new long[N];
    C = new long[N];
    D = new long[N];
    AB = new long[N * N];
    CD = new long[N * N];

    for (int i = 0; i < N; ++i) {
      st = new StringTokenizer(br.readLine());
      A[i] = Long.parseLong(st.nextToken());
      B[i] = Long.parseLong(st.nextToken());
      C[i] = Long.parseLong(st.nextToken());
      D[i] = Long.parseLong(st.nextToken());
    }

    // AB, CD 만들기
    int idx = 0;
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < N; ++j) {
        AB[idx++] = A[i] + B[j];
      }
    }

    idx = 0;
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < N; ++j) {
        CD[idx++] = C[i] + D[j];
      }
    }

    Arrays.sort(AB);
    Arrays.sort(CD);

    long answer = 0;

    for (int i = 0; i < N * N; ++i) {
      answer += upper_bound(0, CD.length, -AB[i]) - lower_bound(0, CD.length, -AB[i]);
    }

    System.out.println(answer);
  }

  private static int upper_bound(int left, int right, long target) {
    while (left < right) {
      int mid = (left + right) / 2;
      if (CD[mid] <= target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return right;
  }

  private static int lower_bound(int left, int right, long target) {
    while (left < right) {
      int mid = (left + right) / 2;
      if (CD[mid] < target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return right;
  }

}