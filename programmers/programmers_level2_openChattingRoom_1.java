package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class programmers_level2_openChattingRoom_1 {
  public String[] solution(String[] record) {
    String[] answer = {};
    HashMap<String, String> hm = new HashMap<>();
    ArrayList<String[]> list = new ArrayList<>();
    for (String r : record) {
      String[] info = r.split(" ");
      if (info[0].equals("Enter")) {
        // 첨으로 들왔음
        hm.put(info[1], info[2]);
        list.add(new String[] { info[1], "님이 들어왔습니다." });
      } else if (info[0].equals("Leave")) {
        // 채팅방 떠남
        // hm.remove(info[1]);
        list.add(new String[] { info[1], "님이 나갔습니다." });
      } else {
        // 닉네임 바꿈
        hm.put(info[1], info[2]);
      }
    }

    answer = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      answer[i] = hm.get(list.get(i)[0]) + list.get(i)[1];
    }

    return answer;
  }
}
