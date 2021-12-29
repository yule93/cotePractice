package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 1922 네트워크 연결(MST, 최소 신장 트리)
// 568ms
public class boj1922 {

  static int[] parents;
  static Edge[] list;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());

    parents = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      parents[i] = i;
    }

    list = new Edge[M];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      Edge ed = new Edge(a, b, cost);
      list[i] = ed;
    }

    Arrays.sort(list);

    int cnt = 0, result = 0;
    for (Edge ex : list) {
      if (union(ex.start, ex.end)) {
        result += ex.weight;
        if (++cnt == N - 1)
          break;
      }
    }
    
    System.out.println(result);
  }

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

  // 재귀
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
}
