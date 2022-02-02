package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 10830 행렬 제곱(분할 정복)
// 1629번 문제 '곱셈'에서 한 단계 심화된 알고리즘. 분할정복을 이용한 거듭제곱 문제이다.
// 시간 복잡도는 O(N^3 * logB) = 최악의 경우 약 40*75 = 3000번. 공간 복잡도는 2*N*N*4 = 200byte여서 괜찮다.
// 120ms
public class boj10830 {

  static int N;
  static long B;
  static long[][] arr, unitArr;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(st.nextToken()); // 행렬의 가로세로 크기. 2~5
    B = Long.parseLong(st.nextToken()); // A의 B 제곱할 때, B. 1~1천억

    arr = new long[N][N];
    unitArr = new long[N][N];    // ! 단위 행렬
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        arr[i][j] = Long.parseLong(st.nextToken()) % 1000;
        if (i == j)
          unitArr[i][j] = 1;
      }
    }
    
    arr = pow(B, arr);

    for (long[] example : arr) {
      for (long num : example) {
        sb.append(num).append(" ");
      }
      sb.append("\n");
    }

    System.out.print(sb.toString());
  }

  // * 행렬 제곱!! 함수
  static long[][] pow(long n, long[][] array) {
    if (n == 0)
      return unitArr;
    if (n == 1)
      return array;

    long[][] nMatrix = pow(n / 2, array);
    nMatrix = multi(nMatrix, nMatrix);

    return n % 2 == 0 ? nMatrix : multi(nMatrix, array);
  }

  // * 행렬 곱셈!! 함수
  static long[][] multi(long[][] m1, long[][] m2) {
    long[][] matrix = new long[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < N; k++)
          matrix[i][j] += (m1[i][k] * m2[k][j]) % 1000;
        matrix[i][j] %= 1000;
      }
    }

    return matrix;
  }

}
