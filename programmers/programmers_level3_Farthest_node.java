package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class programmers_level3_Farthest_node {
  public static void main(String[] args) {
    System.out.println(solution(6, new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
  }
  public static int solution(int n, int[][] edge) {
    int answer = 0;
    boolean[] visited = new boolean[n + 1];
    int[] count = new int[n + 1]; // 각 노드간 1과의 거리 저장 배열

    // 인접행렬리스트
    ArrayList[] list = new ArrayList[n+1];
    for (int i = 0; i <= n; i++) {
      list[i] = new ArrayList<Integer>();
    }
    for (int[] node : edge) {
      list[node[0]].add(node[1]);
      list[node[1]].add(node[0]);
    }

    Queue<Integer> q = new LinkedList<>();
    q.add(1);
    visited[0] = visited[1] = true;
    int now;
    while (q.size() > 0) {
      now = q.poll();
      for (Object v : list[now]) {
        // 방문하지 않은 곳이라면
        int num = (int) v;
        if (!visited[num]) {
          count[num] = count[now] + 1;
          visited[num] = true;
          q.add(num);
        }
      }
    }

    int max = 0;
    for (int cnt : count) {
      if (max < cnt) {
        max = cnt;
        answer = 1;
      } else if (max == cnt)
        answer++;
    }

    return answer;
  }
}
