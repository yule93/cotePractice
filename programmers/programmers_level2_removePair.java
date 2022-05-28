package programmers;

import java.util.Stack;

public class programmers_level2_removePair {
  public static void main(String[] args) {
    System.out.println(solution("bbaa"));
  }
  public static int solution(String s) {
    if (s.length() % 2 == 1)
      return 0;
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray())
      if (stack.size() > 0 && stack.peek() == c)
        stack.pop();
      else
        stack.push(c);

    return stack.size() == 0 ? 1 : 0;
  }
}
