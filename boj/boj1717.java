package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1717 {
  static int N, M;
  static int[] parents;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(st.nextToken());
    parents = new int[N + 1];
    for (int n = 1; n <= N; n++) {
      makeSet(n);
    }

    M = Integer.parseInt(st.nextToken());
    for (int m = 0; m < M; m++) {
      st = new StringTokenizer(br.readLine());

      int command = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      if (command == 0) {
        // 합집합
        union(a, b);
      } else if (command == 1) {
        // 비교 연산
        if (findSet(a) != findSet(b)) {
          sb.append("NO\n");
        } else if(findSet(a) == findSet(b)) {
          sb.append("YES\n");
        }
      }
    }

    System.out.print(sb.toString());
  }

  public static void makeSet(int a) {
    parents[a] = a;
  }

  public static void union(int a, int b) {
    int aRoot = findSet(a);
    int bRoot = findSet(b);
    if (aRoot != bRoot)
      parents[bRoot] = aRoot;
  }

  public static int findSet(int a) {
    if (a == parents[a])
      return a;
    return parents[a] = findSet(parents[a]);
  }
}
