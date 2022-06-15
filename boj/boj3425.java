package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// boj 3425 고스택(빡구현, 스택)
// 진짜 구현 문제라 뭐라 할 말이 없음....
// 356ms
public class boj3425 {
  public static ArrayList<String[]> list = new ArrayList<>();
  public static Stack<long[]> stack = new Stack<>();
  public static long n;
  static long MAX = 1000000000;   //10^9보다 크면 안 됨.....

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();

    while (true) {
      String cmd;
      while (true) {
        st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) {
          list.clear();
          sb.append("\n");
          continue;
        }
        cmd = st.nextToken();
        if (cmd.equals("NUM")) {
          String num = st.nextToken();
          list.add(new String[] { cmd, num });
        } else if (cmd.equals("END") || cmd.equals("QUIT")) {
          break;
        } else {
          list.add(new String[] { cmd });
        }
      }
      if ("QUIT".equals(cmd))
        break;

      boolean isNumber = false;
      st = new StringTokenizer(br.readLine());

      int tc = Integer.parseInt(st.nextToken());
      while (tc-- > 0) {
        st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        isNumber = gostack();
        if (!isNumber || stack.size() != 1) {
          sb.append("ERROR").append("\n");
        } else {
          if (Math.abs(stack.peek()[0]) > MAX)
            sb.append("ERROR").append("\n");
          else
            sb.append(stack.peek()[0]).append("\n");
        }

        while (!stack.isEmpty())
          stack.pop();
      }

      while (!list.isEmpty())
        list.remove(0);
    }

    System.out.print(sb.toString());
  }

  // action for each command
  public static boolean gostack() {
    stack.push(new long[] { n });
    for (String[] command : list) {
      if (command[0].equals("NUM")) {
        stack.push(new long[] { Long.parseLong(command[1]) });
      } else if (command[0].equals("POP")) {
        if (stack.isEmpty())
          return false;
        stack.pop();
      } else if (command[0].equals("INV")) {
        if (stack.isEmpty())
          return false;
        long a = stack.peek()[0];
        stack.pop();
        stack.push(new long[] { -a });
      } else if (command[0].equals("DUP")) {
        if (stack.isEmpty())
          return false;
        stack.push(stack.peek());
      } else if (command[0].equals("SWP")) {
        if (stack.size() < 2)
          return false;
        long num1 = stack.peek()[0];
        stack.pop();
        long num2 = stack.peek()[0];
        stack.pop();
        stack.push(new long[] { num1 });
        stack.push(new long[] { num2 });
      } else if (command[0].equals("ADD")) {
        if (stack.size() < 2)
          return false;
        long num1 = stack.peek()[0];
        stack.pop();
        long num2 = stack.peek()[0];
        stack.pop();
        stack.push(new long[] { num1 + num2 });
      } else if (command[0].equals("SUB")) {
        if (stack.size() < 2)
          return false;
        long num1 = stack.peek()[0];
        stack.pop();
        long num2 = stack.peek()[0];
        stack.pop();
        stack.push(new long[] { num2 - num1 });
      } else if (command[0].equals("MUL")) {
        if (stack.size() < 2)
          return false;
        long num1 = stack.peek()[0];
        stack.pop();
        long num2 = stack.peek()[0];
        stack.pop();
        stack.push(new long[] { num1 * num2 });
      } else if (command[0].equals("DIV")) {
        if (stack.size() < 2)
          return false;
        long num1 = stack.peek()[0];
        stack.pop();
        if (num1 == 0)
          return false;
        long num2 = stack.peek()[0];
        stack.pop();
        stack.push(new long[] { num2 / num1 });
      } else if (command[0].equals("MOD")) {
        if (stack.size() < 2)
          return false;
        long num1 = stack.peek()[0];
        stack.pop();
        if (num1 == 0)
          return false;
        long num2 = stack.peek()[0];
        stack.pop();
        stack.push(new long[] { num2 % num1 });
      }
    }
    return true;
  }
}
