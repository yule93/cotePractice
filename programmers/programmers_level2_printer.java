package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class programmers_level2_printer {
  public static void main(String[] args) {
    System.out.println(solution(new int[] {2, 1, 3, 2}, 2));
  }

  public static int solution(int[] priorities, int location) {
    int answer = 0;

    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < priorities.length; i++) {
      q.add(new int[] { i, priorities[i] });
    }

    while (q.size() > 0) {
      int[] now = q.poll();

      boolean flag = false;
      for (int[] ex : q) {
        if (ex[1] > now[1]) {
          flag = true;
          break;
        }
      }

      if (flag) {
        q.offer(now);
      } else {
        if (now[0] == location) {
          answer = priorities.length - q.size();
        }
      }
    }

    return answer;
  }
}
