package programmers;

import java.util.Arrays;
import java.util.HashSet;

public class programmers_level2_tuple {
  public static void main(String[] args) {
    System.out.print("[");
    for (int num : solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")) {
      System.out.print(num + ", ");
    }
    System.out.println("]");
  }

  public static int[] solution(String s) {
    int[] answer = {};
    // trim으로 공백 제거, split으로 문자열을 나눠 문자 배열 생성
    String[] strs = s.replaceAll("[{}]", " ").trim().split(" ,");

    answer = new int[strs.length];
    HashSet<Integer> hs = new HashSet<Integer>(); // Set을 쓰는 이유는 보통 중복 제거
    Arrays.sort(strs, (a, b) -> (a.length() - b.length()));
    int i = 0;
    for (String str : strs) {
      for (String strSplit : str.split(",")) {
        int a = Integer.parseInt(strSplit.trim());
        if (hs.contains(a))
          continue;
        hs.add(a);
        answer[i++] = a;
      }
    }
    return answer;
  }
}
