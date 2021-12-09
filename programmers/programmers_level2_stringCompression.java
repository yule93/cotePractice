package programmers;

public class programmers_level2_stringCompression {
  public static void main(String[] args) throws Exception {
    System.out.println(solution("xababcdcdababcdcd"));
  }

  public static int solution(String s) {
    int answer = s.length();

    // i는 몇 개씩 자를지 결정하는 단위. (s.length()/2)+1이 리밋인 이유는 자르는 기준이 문자열의 반이 넘어가면 굳이 자를 필요가 없음...
    for (int i = 1; i < s.length() / 2 + 1; i++) {
      String prev = s.substring(0, i);
      int count = 1;
      String enc = "";
      String last = "";
      for (int j = i; j < s.length(); j += i) {
        if (j + i > s.length()) {
          last = s.substring(j);
          continue;
        }
        if (prev.equals(s.substring(j, j + i))) {
          count++;
        } else {
          enc += prev;
          if (count != 1) {
            enc = count + enc;
          }
          prev = s.substring(j, j + i);
          count = 1;
        }
      }
      enc += prev + last;
      if (count != 1) {
        enc = count + enc;
      }

      answer = Math.min(answer, enc.length());
    }

    return answer;
  }
}
