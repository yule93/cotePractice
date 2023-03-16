package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// boj 1874 스택 수열 (단순 구현)
public class boj1874_1 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int n = Integer.parseInt(br.readLine());
    
    Stack<Integer> st = new Stack<>();
    int i = 1; // ? 1부터 집어넣기 시작
    int a = Integer.parseInt(br.readLine());
    int count = 0;

    while (true) {
      if (!st.isEmpty()) {
        if (st.peek() == a) {
          sb.append("-").append("\n");
          st.pop();
          count++;

          if (count == n)
            break;

          a = Integer.parseInt(br.readLine());
          continue;
        }
      }

      st.add(i++);
      sb.append("+").append("\n");

      if (i > n + 1)
        break;
    }

    if (st.size() == 0)
      System.out.println(sb.toString());
    else
    System.out.println("NO");

  }
}
