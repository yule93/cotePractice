package programmers;

import java.util.HashMap;

// 프로그래머스 전화번호 목록(그리디)
// 유명한 문제인데 왜 유명하냐면 이제 테케가 바뀌어서 startWith으로 풀면 유효성에서 걸려서 통과 안 됨
public class programmers_level2_phoneNumberList {
  public static void main(String[] args) throws Exception {
    System.out.println(solution(new String[] {"119", "97674223", "1195524421"}));
  }

  public static boolean solution(String[] phone_book) {
    boolean answer = true;
    // 만약 phone_book 원소 중, 접두어가 되는 경우가 있으면 false, 아니면 true 반환
    HashMap<String, String> hs = new HashMap<>();
    for (String num : phone_book) {
      hs.put(num, num);
    }

    loop:
    for (String num : phone_book) {
      for (int i = 1; i < num.length(); i++) {
        if(hs.containsKey(num.substring(0, i))) {
          answer = false;
          break loop;
        }
      }
    }

    return answer;
  }
}
