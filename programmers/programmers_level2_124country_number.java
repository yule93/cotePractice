package programmers;

public class programmers_level2_124country_number {
  public static void main(String[] args) {
    System.out.println(solution(4));
  }

  public static String solution(int n) {
    StringBuilder sb = new StringBuilder();

    int num = n;
    while (num != 0) {
      int front = num / 3;
      int back = num % 3;
      if (back == 0) {
        // 나머지가 0일 때,
        front = front - 1;
        back = 4;
      }

      num = front;
      sb.insert(0, back);
    }

    return sb.toString();
  }
}
