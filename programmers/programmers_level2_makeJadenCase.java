package programmers;

public class programmers_level2_makeJadenCase {

  public static void main(String[] args) {
    System.out.println(solution("3people unFollowed me"));
  }

  public static String solution(String s) {
    String answer = "";

    boolean flag = true;
    for (int i = 0; i < s.length(); i++) {
      if (flag)
        answer += String.valueOf(s.charAt(i)).toUpperCase();
      else
        answer += String.valueOf(s.charAt(i)).toLowerCase();
      
      if (s.charAt(i) == ' ')
        flag = true;
      else
        flag = false;
    }

    return answer;
  }
}
