package programmers;

import java.util.*;

public class programmers_level2_passingBridgeTruck {
  class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
      Queue<Integer> queue = new LinkedList<>();
      int sum = 0;
      int time = 0;

      for (int truck : truck_weights) {
        while (true) {
          if (queue.isEmpty()) {
            queue.add(truck);
            sum += truck;
            time++; // 다리에 오를 때만 시간 추가
            break;
          } else if (queue.size() == bridge_length) {
            // 큐에 다리 길이만큼 트럭이 다 찬 경우
            sum -= queue.poll();
          } else {
            // 다리 길이만큼 큐가 차지않았을 때, 초과 용량보다 truck과 이전에 담겨있던 트럭의 무게 합이 작을 경우
            if (sum + truck <= weight) {
              queue.add(truck);
              sum += truck;
              time++;
              break;
            } else {
              // 넘는다면 0을 넣어 이미 큐에 있는 트럭이 다리를 건너게 만듬
              queue.add(0);
              time++;
            }
          }
        }
      }

      return time + bridge_length;
    }
  }
}
