package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// boj 2374 같은 수로 만들기(그리디, 스택)
// 204ms
public class boj2374 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Stack<Long> st = new Stack<>();
    
    int n = Integer.parseInt(br.readLine());
    long answer = 0;
    long max = 0;
    for (int i = 0; i < n; i++) {
      long a = Long.parseLong(br.readLine());
      max = Math.max(max, a);

      // ! 모두 같은 값이 되게 하는 횟수는  가장 작은 값이 가장 큰 값이 되게 하면 구할 수 있음
      if (st.size() == 0) {
        // 처음 시작 or 이전 숫자들을 모두 같게 만들었다면
        st.add(a);
      } else {
        if (st.peek() < a) {
          answer += a - st.pop();
          st.add(a);
        } else if (st.peek() > a) {
          st.pop();
          st.add(a);
        }
      }
    }
    
    while (st.size() > 0) {
      long num = st.pop();
      answer += max - num;
    }

    System.out.println(answer);
  }
}