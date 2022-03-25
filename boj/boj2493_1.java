package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// boj 2493 탑(투포인터)
// 764ms
public class boj2493_1 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine()); // 탑의 개수 1~50만 이하
    Stack<int[]> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      // 방금 들어온 탑의 높이
      int height = Integer.parseInt(st.nextToken());
      if (stack.size() == 0) {
        sb.append("0 ");
        stack.add(new int[] { i, height });
      }
      while (true) {
        if (stack.size() == 0) {
          sb.append("0 ");
          stack.add(new int[] { i, height });
          break;
        }

        int[] pop = stack.peek();
        if (pop[1] > height) {
          sb.append(pop[0]).append(" ");
          stack.push(new int[] { i, height });
          break;
        } else {
          stack.pop();
        }
      }
    }

    System.out.println(sb.toString().substring(2));
  }
}
