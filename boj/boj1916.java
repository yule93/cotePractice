package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// boj 1916 최소 비용 구하기(다익스트라)
// 452ms
public class boj1916 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());

    ArrayList<Edge>[] adj = new ArrayList[N + 1]; // 각 노드별 거리 저장
    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      adj[start].add(new Edge(end, weight));
    }

    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    PriorityQueue<Edge> pq = new PriorityQueue<>();
    boolean[] visited = new boolean[N + 1]; // 노드 방문 여부
    Edge[] D = new Edge[N + 1];

    for (int i = 1; i <= N; i++) {
      if (i == start) {
        D[i] = new Edge(i, 0);
      } else {
        D[i] = new Edge(i, Integer.MAX_VALUE);
      }
      pq.add(D[i]);
    }

    while (pq.size() > 0) {
      Edge e = pq.poll();

      for (Edge next : adj[e.v]) {
        if (!visited[next.v] && D[next.v].weight > D[e.v].weight + next.weight) {
          D[next.v].weight = D[e.v].weight + next.weight;
          pq.remove(D[next.v]);
          pq.add(D[next.v]);
        }
      }

      visited[e.v] = true;
    }

    System.out.println(D[end].weight);
  }

  static class Edge implements Comparable<Edge> {
    int v, weight;

    public Edge(int v, int weight) {
      this.v = v;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      return Integer.compare(this.weight, o.weight);
    }
  }
}
