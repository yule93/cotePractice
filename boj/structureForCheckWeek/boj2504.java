package boj.structureForCheckWeek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// boj 2504 괄호의 값(스택!!!)
// 140ms
public class boj2504 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // * () 괄호열의 값은 2, [] 괄호열읙 값은 3, (x)는 2x(x값), [x]는 3x(x값)이다.

    int answer = 0;
    Stack<Character> st = new Stack<>();
    String str = br.readLine();
    int sum = 1;
    for (int i = 0; i < str.length(); i++) {
      char bracket = str.charAt(i);
      // ! 열리는 괄호일 때는 스택에 넣어준다. 또한, 미리 *2 처리를 해줘서 닫히는 괄호에서 /2로 나눠서 원상복구해준다.
      // ! 닫히는 괄호일 때, 만약 짝이 맞지 않는 괄호, 모양이 다르거나 열리는 괄호가 더 이상 남지 않았을 때, 면 성립할 수 없는 괄호 문자열이므로 answer = 0으로 리턴해준다.
      if (bracket == '(') {
        st.add(bracket);
        sum *= 2;
      } else if (bracket == '[') {
        st.add(bracket);
        sum *= 3;
      } else {
        if (bracket == ')') {
          if (st.size() == 0 || st.peek() != '(') {
            answer = 0;
            break;
          }
          if (str.charAt(i - 1) == '(') {
            answer += sum;
          }
          st.pop();
          sum /= 2;
        } else if (bracket == ']') {
          if (st.size() == 0 || st.peek() != '[') {
            answer = 0;
            break;
          }
          if (str.charAt(i - 1) == '[')
            answer += sum;
          st.pop();
          sum /= 3;
        }
      }
    }

    System.out.println(st.size() > 0 ? 0 : answer);
  }
}
