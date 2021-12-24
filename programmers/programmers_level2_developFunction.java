package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class programmers_level2_developFunction {
  public static void main(String[] args) {
    int[] progresses = { 93, 30, 55 };
    int[] speeds = { 1, 30, 5 };

    int[] answer = solution(progresses, speeds);

    for (int ex : answer) {
      System.out.print(ex + ", ");
    }
    System.out.println();
  }

  public static int[] solution(int[] progresses, int[] speeds) {
    Stack<Integer> stack = new Stack<Integer>();
    for (int i = progresses.length - 1; i >= 0; i--) {
      stack.add((100 - progresses[i]) / speeds[i] + ((100 - progresses[i]) % speeds[i] > 0 ? 1 : 0));
      // 완성까지 며칠 걸리는지 계산하는 걸 스택에 넣어줌.
    }
      

    List<Integer> s = new ArrayList<Integer>();
    while (!stack.isEmpty()) {
      int cnt = 1;
      int top = stack.pop();
      while (!stack.isEmpty() && stack.peek() <= top) {
        cnt++;
        stack.pop();
      }
      s.add(cnt);
    }
    int[] answer = new int[s.size()];
    for (int i = 0; i < answer.length; i++) {
      answer[i] = s.get(i);
    }
    return answer;
  }
}
