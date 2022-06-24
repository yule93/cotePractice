package boj.structureForCheckWeek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 1197 최소 스패닝 트리(MST, disjoint-set, 크루스칼, prim)
// * start, end가 정해진 간선이므로 주의하고...
// 624ms
public class boj1197 {

  static class Edge implements Comparable<Edge> {
    int start, end, weight;

    public Edge(int start, int end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      // 음수를 비교하게 될까봥 ㅣ렇게 비교함
      return Integer.compare(this.weight, o.weight);
    }
  }

  static int[] parents;

  private static void makeSet() {
    parents = new int[V];
    // 모든 원소의 대표자를 자신으로 만든다.
    for (int i = 0; i < V; i++) {
      parents[i] = i;
    }
  }

  private static int findSet(int a) {
    // a가 속한 집합의 대표자 찾기
    if (a == parents[a])
      return a;
    return parents[a] = findSet(parents[a]);
  }

  private static boolean union(int a, int b) {
    // 두 원소를 하나의 집합으로 합치기(대표자 이용)
    int aRoot = findSet(a);
    int bRoot = findSet(b);
    if (aRoot == bRoot)
      return false; // 이미 같은 집합

    parents[bRoot] = aRoot;
    return true;
  }

  static int V, E;
  static Edge[] edgeList;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    edgeList = new Edge[E];
    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken())-1; // 시작 점
      int b = Integer.parseInt(st.nextToken())-1; // 도착 점
      int c = Integer.parseInt(st.nextToken()); // 간선 가중치
      edgeList[i] = new Edge(a, b, c);
    }
    
    Arrays.sort(edgeList);
    makeSet();

    int count = 0, result = 0;
    for (Edge e : edgeList) {
      if (union(e.start, e.end)) {
        result += e.weight;
        if (count++ == V - 1)
          break;
      }
    }

    System.out.println(result);
  }
}
