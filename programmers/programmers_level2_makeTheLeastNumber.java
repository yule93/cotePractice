package programmers;

import java.util.Arrays;

// 프로그래머스 최솟값 만들기 (걍 그리디)
public class programmers_level2_makeTheLeastNumber {

  public static void main(String[] args) {
    System.out.println(solution(new int[] {1, 4, 2}, new int[] {5, 4, 4}));
  }

  public static int solution(int[] A, int[] B) {
    int answer = 0;
    Arrays.sort(A);
    Arrays.sort(B);

    for (int i = 0; i < A.length; i++) {
      answer += A[i] * B[A.length - 1 - i];
    }

    return answer;
  }
}