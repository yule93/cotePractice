package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// boj 11724 연결 요소의 개수 (재귀, 백트래킹, 전형적 그래프 탐색)
// 3초 제한이므로 연산이 3억번 이하로 돼야 함. 메모리 제한은 512mb. 따라서 인접리스트를 쓸 예정.
// 712ms
public class boj11724 {

  static ArrayList<Integer>[] list;
  static boolean[] visited;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 정점(노드)의 개수, 1~1000
    int M = Integer.parseInt(st.nextToken()); // 간선의 개수, 0~N*(N-1)/2: 1~N-1까지의 합. 이진 트리 같은 그래프가 아니므로 간선의 개수는 1개의 노드에 N-1개가 존재 가능.

    visited = new boolean[N + 1];
    list = new ArrayList[N + 1];
    for (int i = 0; i <= N; i++) {
      list[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      list[a].add(b);
      list[b].add(a);
    }

    int count = 0;
    for (int i = 1; i <= N; i++) {
      if (!visited[i]) {
        dfs(i);
        count++;
      }
    }

    System.out.println(count);
  }

  static void dfs(int node) {
    if (visited[node]) {
      return;
    }
    visited[node] = true;
    for (int i : list[node]) {
      if (!visited[i]) {
        dfs(i);
      }
    }
  }
}
