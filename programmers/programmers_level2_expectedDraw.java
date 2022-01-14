package programmers;

// 프로그래머스 예상 대진표(이분탐색)
public class programmers_level2_expectedDraw {
  public static void main(String[] args) {
    System.out.println(solution(8, 4, 7));
  }

  public static int solution(int n, int a, int b) {
    int answer = 1, left = 0, right = 0;

    // left와 right 정해주기
    if (a > b) {
      left = b;
      right = a;
    } else {
      left = a;
      right = b;
    }

    while (left <= right) {
      // left가 무조건 홀수 or right가 짝수가 나와야 둘이 대결이 가능하다.
      if (left % 2 != 0 && right - left == 1) {
        break;
      }

      left = (left + 1) / 2;
      right = (right + 1) / 2;
      answer++;
    }

    return answer;
  }
}