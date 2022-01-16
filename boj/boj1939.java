package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// boj 1939 중량제한(bfs, kruskal)
// 436ms
public class boj1939 {

  static int[] parents, rank;
  static int N, M;
  static PriorityQueue<Edge> pq;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 2~10000
    M = Integer.parseInt(st.nextToken()); // 1~100000

    pq = new PriorityQueue<>();
    parents = new int[N+1]; // 부모 저장
    rank = new int[N + 1];
    make();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      pq.add(new Edge(u, v, cost));
    }

    st = new StringTokenizer(br.readLine());

    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    
    int answer = -1;
    while (pq.size() > 0) {
      Edge e = pq.poll();
      union(e.u, e.v);
      if (findSet(start) == findSet(end)) {
        answer = e.cost;
        break;
      }
    }

    System.out.println(answer);
  }

  // * 간선 정보 만들기
  static class Edge implements Comparable<Edge> {
    int u, v, cost;
    public Edge(int u, int v, int cost) {
      this.u = u;
      this.v = v;
      this.cost = cost;
    }
    @Override
    public int compareTo(Edge o) {
      return o.cost - cost;
    }
  }

  // * 각자 자기 자신을 부모 노드로 초기화
  public static void make() {
    for (int i = 1; i <= N; i++) {
      parents[i] = i;
    }
  }

  public static boolean union(int a, int b) {
    int aRoot = findSet(a);
    int bRoot = findSet(b);
    if (aRoot == bRoot)
      return false; // 이미 같은 집합

    parents[bRoot] = aRoot;
    return true;
  }

  public static int findSet(int a) {
    if (a == parents[a])
      return a;
    return parents[a] = findSet(parents[a]);
  }
}
