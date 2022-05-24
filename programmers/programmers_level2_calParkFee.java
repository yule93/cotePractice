package programmers;

import java.util.Arrays;
import java.util.HashMap;

public class programmers_level2_calParkFee {

  public static void main(String[] args) {
    // for (int num : solution(new int[] { 180, 5000, 10, 600 },
    // new String[] { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59
    // 5961 OUT", "07:59 0148 IN",
    // "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" })) {
    // System.out.println(num);
    // }
    for (int num : solution(new int[] { 180, 5000, 10, 600 },
        new String[] { "05:34 5961 IN", "06:34 5961 OUT", "07:34 5961 IN", "08:34 5961 OUT", "09:34 5961 IN",
            "10:34 5961 OUT", "11:34 5961 IN", "12:34 5961 OUT" })) {
      System.out.println(num);
    }
  }

  public static int[] solution(int[] fees, String[] records) {
    int[] answer = {};
    HashMap<String, String> hs = new HashMap<>();
    HashMap<String, Integer> ansMap = new HashMap<>();

    for (String record : records) {
      String[] info = record.split(" ");
      if (info[2].equals("IN")) {
        // 입차했을 때,
        hs.put(info[1], info[0]);
      } else if (info[2].equals("OUT")) {
        // 출차했을 때,
        String[] inTime = hs.remove(info[1]).split(":");
        String[] outTime = info[0].split(":");
        int hour = (Integer.parseInt(outTime[0]) - Integer.parseInt(inTime[0])) * 60;
        int min = (Integer.parseInt(outTime[1]) - Integer.parseInt(inTime[1]));
        if (min < 0) {
          min += 60;
          hour -= 60;
        }

        int total = min + hour;
        if (ansMap.get(info[1]) != null)
          total += ansMap.get(info[1]);

        ansMap.put(info[1], total);
      }
    }

    for (String carNum : hs.keySet()) {
      String[] inTime = hs.get(carNum).split(":");
      int hour = (23 - Integer.parseInt(inTime[0])) * 60;
      int min = (59 - Integer.parseInt(inTime[1]));
      if (min < 0) {
        min += 60;
        hour -= 60;
      }

      int total = min + hour;
      if (ansMap.get(carNum) != null)
        total += ansMap.get(carNum);

      ansMap.put(carNum, total);
    }

    Object[] carNums = ansMap.keySet().toArray();
    Arrays.sort(carNums);

    answer = new int[carNums.length];
    for (int i = 0; i < carNums.length; i++) {
      // answer[i] = (int) carNums[0];
      int fee = ansMap.get(carNums[i]);
      if (fee <= fees[0]) {
        fee = fees[1];
      } else {
        fee = fees[1] + (fee - fees[0]) / fees[2] * fees[3] + (fee % fees[0] % fees[2] == 0 ? 0 : 1) * fees[3];
      }
      answer[i] = fee;
    }

    return answer;
  }
}
