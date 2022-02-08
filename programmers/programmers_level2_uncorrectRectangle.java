package programmers;

// 프로그래머스 멀쩡한 사각형
// * 그리디 문제로, w와 h의 최대공약수로 w와 h를 나눠준만큼의 영역이 최대공약수만큼 반복된다.
// * 예를 들어 10과 6이 있을 때, 둘의 최대공약수는 2로 5x3의 영역이 2번 반복된다는 말!
// * 따라서 5x3 영역에서 쓸 수 없는 종이의 개수를 구해 2를 곱해서 전체 영역에서 빼주면 답이 나온다!
// * 일차함수로 생각해 기울기*(x값)만큼 나온 수를, 대칭이므로 두 배 해줘서 구해도 무방....
class programmers_level2_uncorrectRectanble {
  public static void main(String[] args) {
    System.out.println(solution(8, 4));
  }

  public static long solution(int w, int h) {
    long answer = 1;

    long min = Math.min((long) w, (long) h);
    long max = Math.min((long) w, (long) h);

    long value = 1;
    while (value > 0) {
      value = max % min;
      max = min;
      min = value;
    }

    answer = (long) w * (long) h - ((long) w + (long) h - max);
    return answer;
  }
}