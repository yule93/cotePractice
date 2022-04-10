package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class programmers_level2_compression {
  public static void main(String[] args) {
    for (int res : solution("KAKAO")) {
      System.out.print(res + " ");
    }
  }

  public static int[] solution(String msg) {
    int[] answer = {};
    HashMap<String, Integer> hs = new HashMap<>();
    for (int count = 0; count < 26; count++) {
      hs.put(String.valueOf((char) (65 + count)), count);
    }

    ArrayList<Integer> list = new ArrayList<>();
    loop: for (int i = 0; i < msg.length(); i++) {
      // 투 포인터
      String str = String.valueOf(msg.charAt(i));
      for (int j = i + 1; j < msg.length(); j++) {
        String sum = str + String.valueOf(msg.charAt(j));
        if (!hs.containsKey(sum)) {
          hs.put(sum, hs.size());
          list.add(hs.get(str));
          continue loop;
        }
        str = sum;
        i++;
      }
      list.add(hs.get(str));
    }

    // System.out.println(list.size());
    answer = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      answer[i] = list.get(i) + 1;
    }
    return answer;
  }
}
