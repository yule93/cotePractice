package programmers;

import java.util.Stack;

// 프로그래머스 괄호 회전하기 (딱 봐도 스택과 구현 문제... 괄호 변환이랑 비슷)
// 해봤자 s의 길이를 L이라 했을 때, 시간복잡도는 O(L^2)밖에 안 되므로, L의 최대값인 1천이 들어와도 100만번 연산밖에 안 한다. 따라서 0.01초 내로 연산 가능.
public class programmers_level2_rotatingBracket {
  public static void main(String[] args) {
    System.out.println(solution("[](){}"));
  }

  public static int solution(String s) {
    int answer = 0;

    String new_s = s;
    for (int i = 0; i < s.length(); i++) {
      // ! 괄호가 맞는지 체크하는 함수
      if (correctBracket(new_s))
        answer++;
      // * 한 글자씩 뒤로 빼주는 연산
      new_s = new_s.substring(1, s.length()) + new_s.charAt(0);
    }

    return answer;
  }

  public static boolean correctBracket(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case '[':
          stack.add('[');
          break;
        case ']':
          if (stack.size() == 0)
            return false;
          if (stack.peek() != '[')
            return false;
          stack.pop();
          break;
        case '{':
          stack.add('{');
          break;
        case '}':
          if (stack.size() == 0)
            return false;
          if (stack.peek() != '{')
            return false;
          stack.pop();
          break;
        case '(':
          stack.add('(');
          break;
        case ')':
          if (stack.size() == 0)
            return false;
          if (stack.peek() != '(')
            return false;
          stack.pop();
          break;
      }
    }
    return stack.size() == 0 ? true : false;
  }
}
