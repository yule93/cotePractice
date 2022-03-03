package programmers;

import java.util.HashSet;

public class programmers_level2_englishEndTalk {
  public static void main(String[] args) {
    for (int num : solution(3,
        new String[] { "tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank" })) {
      System.out.print(num);
    }
    System.out.println();
  }

  public static int[] solution(int n, String[] words) {
    int[] answer = { 0, 0 };

    // 중복 단어 제거용
    HashSet<String> hm = new HashSet<>();
    for (int i = 0; i < words.length; i++) {
      if (hm.contains(words[i])) {
        // 중복되는 단어일 경우,
        answer[0] = (i % n) + 1;
        answer[1] = (i / n) + 1;
        return answer;
      } else if (i > 0 && words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
        // 중복은 안 되지만 앞의 단어와 끝말잇기가 만족되지 않을 경우,
        answer[0] = (i % n) + 1;
        answer[1] = (i / n) + 1;
        return answer;
      }

      // 앞에 넣어주면 처음부터 중복으로 걸림ㅋㅋ
      hm.add(words[i]);
    }

    // 중간에 걸린 거 없으면 0, 0 리턴 ㄱㄱ
    return answer;
  }
}
