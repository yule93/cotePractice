package programmers;

public class programmers_level2_vowelDictionary {
  public static void main(String[] args) {
    System.out.println(solution("I"));
    System.out.println(solution("AAAEE"));
  }

  public static int solution(String word) {
    int answer = word.length();

    String str = "AEIOU";
    int[] x = { 781, 156, 31, 6, 1 };

    for (int i = 0; i < word.length(); i++) {
      int idx = str.indexOf(word.charAt(i));
      // System.out.println(idx);
      answer += x[i] * idx;
    }

    return answer;
  }
}
