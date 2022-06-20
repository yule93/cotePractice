package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 9019 DSLR(bfs)
// * D: n을 두 배로, S: n에서 1을 뺀 결과, L: n의 각 자릿수를 왼편으로 회전, R: 각 자릿수를 오른편으로 회전
// ! String.valueOf, Integer.parseInt()와 같은 변환 함수가 생각보다 시간을 많이 잡아먹나봄....ㅠㅠ 앞으로는 노드로 처리하기
// 3260ms
public class boj9019 {
  public static class Register {
    int num;
    String command;

    Register(int num, String command) {
      this.num = num;
      this.command = command;
    }

    int D() {
      return (num * 2) % 10000;
    }

    int S() {
      return num == 0 ? 9999 : num - 1;
    }

    int L() {
      return num % 1000 * 10 + num / 1000;
    }

    int R() {
      return num % 10 * 1000 + num / 10;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    for (int tc = 0; tc < T; tc++) {
      st = new StringTokenizer(br.readLine());
      int first = Integer.parseInt(st.nextToken()); // 초기 숫자
      int end = Integer.parseInt(st.nextToken()); // 최종 값

      boolean[] visited = new boolean[10000];
      Queue<Register> q = new LinkedList<>();
      q.add(new Register(first, ""));
      while (q.size() > 0) {
        Register cur = q.poll();
        if (cur.num == end) {
          sb.append(cur.command).append("\n");
          break;
        }

        if (!visited[cur.D()]) {
          q.add(new Register(cur.D(), cur.command + "D"));
          visited[cur.D()] = true;
        }
        if (!visited[cur.S()]) {
          q.add(new Register(cur.S(), cur.command + "S"));
          visited[cur.S()] = true;
        }
        if (!visited[cur.L()]) {
          q.add(new Register(cur.L(), cur.command + "L"));
          visited[cur.L()] = true;
        }
        if (!visited[cur.R()]) {
          q.add(new Register(cur.R(), cur.command + "R"));
          visited[cur.R()] = true;
        }

      }
    }

    System.out.print(sb.toString());
  }
}