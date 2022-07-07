package programmers;

public class programmers_level2_correctRectangle_1 {
  public static void main(String[] args) {
    System.out.println(solution(8, 12));
  }
  public static long solution(int w, int h) {
    long answer = 1;
    long min = (long) Math.min(w, h);
    long max = (long) Math.max(w, h);

    long gcd = 1; // 최대 공약수
    // 유클리드 호제법
    while (gcd > 0) {
      gcd = max % min;
      max = min;
      min = gcd;
    }
    answer = (long) w * (long) h - ((long) w + (long) h - max);

    return answer;
  }
}
