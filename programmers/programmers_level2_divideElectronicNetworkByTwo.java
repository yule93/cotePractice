package programmers;

import java.util.*;

// 프로그래머스 전력망 둘로 나누기 (완전탐색)
// 인접 행렬도 괜찮을 것 같았는데 bfs 탐색으로 O(N^2) 할 거 생각하니까 시간 복잡도가 기하급수적으로 늘 것 같아서 포기....
// 시간 복잡도는 O(N^2xlogN) = 10000*7 = 약 70000회 정도로 걱정할 건 아닌 것 같지만 bfs보다 dfs가 더 나을지도....
// depth가 i개와 N-i개로 나눠서 접근하도록 유도
public class programmers_level2_divideElectronicNetworkByTwo {
  public static List<Integer>[] list;

  public static void main(String[] args) {
    System.out.println(
        solution(9, new int[][] { { 1, 3 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 8 }, { 7, 9 } }));
  }

  public static int solution(int n, int[][] wires) {
    int answer = 100; // 송전탑 최대 개수!

    list = new List[n + 1];   // 인접 리스트 구현
    for (int i = 0; i <= n; i++) {
      list[i] = new ArrayList<>();
    }

    for (int[] wire : wires) {
      list[wire[0]].add(wire[1]);
      list[wire[1]].add(wire[0]);
    }

    for (int[] wire : wires) {
      int group1 = bfs(wire[0], wire[1], n);
      int group2 = bfs(wire[1], wire[0], n);

      // 
      answer = Math.min(answer, Math.abs(group1 - group2));
    }

    return answer;
  }

  public static int bfs(int v1, int v2, int n) {
    Queue<Integer> q = new LinkedList<>();
    boolean[] visit = new boolean[n + 1];

    int cnt = 0;

    q.add(v1);
    visit[v1] = true;

    while (q.size() > 0) {
      int nowPos = q.poll();
      cnt++;

      for (int next : list[nowPos]) {
        if (next != v2 && !visit[next]) {
          q.add(next);
          visit[next] = true;
        }
      }
    }
    return cnt;
  }
}
