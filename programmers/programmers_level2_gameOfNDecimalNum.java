package programmers;

// 프로그래머스 n진수 게임 (재귀)
public class programmers_level2_gameOfNDecimalNum {
  public static void main(String[] args) {
    System.out.println(solution(2, 4, 2, 1));
  }

  public static String solution(int n, int t, int m, int p) {
    String answer = "";
    StringBuilder sb = new StringBuilder();

    int num = 0;
    while (sb.length() < m * t) {
      sb.append(notation(num++, n));
    }

    for (int i = p - 1; i < m * t; i += m) {
      answer += sb.charAt(i);
    }

    // System.out.println(wholeS);
    return answer;
  }

  public static String notation(int num, int radix) {
    StringBuilder sb = new StringBuilder();
    // 만약 진법 변환할 숫자가 진법보다 큰 경우
    while (num >= radix) {
      sb.append(isHexa(num % radix));
      num = num / radix;
    }
    sb.append(isHexa(num));
    // 뒤집어서 리턴 - > 8을 2진법으로 위 로직을 돌리면 0001 됨
    return sb.reverse().toString();
  }

  public static char isHexa(int num) {
    if (num >= 10) {
      return (char) (num - 10 + 'A');
    }
    return (char) (num + '0');
  }
}