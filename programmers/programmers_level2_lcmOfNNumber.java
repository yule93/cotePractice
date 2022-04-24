package programmers;

public class programmers_level2_lcmOfNNumber {
  public static void main(String[] args) {
    System.out.println(solution(new int[] {2,6,8,14}));
  }

  public static int solution(int[] arr) {
    int answer = arr[0];
    for (int i = 1; i < arr.length; i++) {
      answer = lcm(answer, arr[i]);
    }
    return answer;
  }

  // 유클리드 호제법. 최대공약수 구하는 함수
  static int gcd(int a, int b) {
    while (b != 0) {
      int r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  // 최소공배수 구하는 함수
  static int lcm(int a, int b) {
    return a * b / gcd(a, b);
  }
}