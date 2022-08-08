package programmers;

public class programmers_level2_nextBiggerNumber {
  public static void main(String[] args) {
    System.out.println(solution(78));
  }

  public static int solution(int n) {
    int answer = 0;

    int count = 0;
    String binN = Integer.toBinaryString(n);
    for (int i = 0; i < binN.length(); i++) {
      if (binN.charAt(i) == '1')
        count++;
    }

    String nextN = "";
    for (int i = n + 1; i < 1_000_000; i++) {
      nextN = Integer.toBinaryString(i);
      int nextCount = 0;
      for (int j = 0; j < nextN.length(); j++) {
        if (nextN.charAt(j) == '1')
          nextCount++;
      }

      if (nextCount == count)
        return i;
    }

    return answer;
  }
}