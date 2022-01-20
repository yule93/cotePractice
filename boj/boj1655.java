package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Collections;

// boj 1655 가운데를 말해요
// 정수를 하나씩 외칠 때마다 지금까지 말한 수 중에서 중간 값을 말해야 한다. 그 동안 외친 수가 짝수개라면 중간에 있는 두 수 중에서 작은 수를 말해야 한다.
// 0.2초 시간 제한으로 힙 검색+sysout 출력의 문제점을 파악해야 하는 문제
// 476ms
public class boj1655 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine()); // 1 ~ 10만

    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(br.readLine());
      if ((i + 1) % 2 == 0) {
        min.add(num);
      } else {
        max.add(num);
      }
      if (min.size() > 0 && min.peek() < max.peek()) {
        int tmp = min.poll();
        min.add(max.poll());
        max.add(tmp);
      }

      sb.append(max.peek()).append("\n");
    }

    System.out.print(sb.toString());
  }
}
