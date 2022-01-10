package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class programmers_level2_theBiggestNumber {
  public static void main(String[] args) throws Exception {
    System.out.println(solution(new int[] {0, 0, 0}));
  }

  public static String solution(int[] numbers) {
    String answer = "";
    // 원소의 가장 큰 자리의 수가 큰 순서대로 정렬 후 나열하면 그게 최대값이 되는데
    String[] num = new String[numbers.length];
    int compare = 0;
    for (int i = 0; i < numbers.length; i++) {
      num[i] = String.valueOf(numbers[i]);
      compare += numbers[i];
    }

    if (compare == 0)
      return "0";

    Arrays.sort(num, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return (o1 + o2).compareTo(o2 + o1) * -1;
      }
    });

    for (int i = 0; i < num.length; i++) {
      answer += num[i];
    }

    return answer;
  }
}
