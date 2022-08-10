package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// boj 14621 나만 안되는 연애(MST)
// 240ms
// * 동일한 성별끼리 경로가 이뤄진 거라면 잇지 않아도 되는 경로이므로 패스
public class boj14621 {
  static int N, M;
  static char[] arr;
  static int[] parents;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    parents = new int[N + 1];

    arr = new char[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      arr[i] = st.nextToken().charAt(0);
    }

    List<Edge> edgeList = new ArrayList<>();

    int s, e, d;    // 시작점, 끝점, 거리
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      s = Integer.parseInt(st.nextToken());
      e = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());

      if (arr[s] == arr[e])
        continue;

      edgeList.add(new Edge(s, e, d));
    }

    // ! 최소 신장트리이므로 가장 짧은 경로를 타야해서 거리순으로 정렬
    Collections.sort(edgeList, (o1, o2) -> Integer.compare(o1.d, o2.d));

    // 부모 배치
    init();

    int nodeCnt = 0;
    int answer = 0;

    for (Edge temp : edgeList) {
      if (union(temp.s, temp.e)) {
        nodeCnt++;
        answer += temp.d;
        if (nodeCnt == N - 1) {
          System.out.println(answer);
          return;
        }
      }
    }

    System.out.println(-1);
  }

  static boolean union(int x, int y) {
    x = find(x);
    y = find(y);

    if (x == y) {
      return false;
    } else {
      parents[y] = x;
      return true;
    }
  }

  static void init() {
    for (int i = 1; i <= N; i++) {
      parents[i] = i;
    }
  }

  static int find(int x) {

    if (x != parents[x]) {
      return parents[x] = find(parents[x]);
    }
    return parents[x];
  }

  static class Edge {
    int s, e, d;

    public Edge(int s, int e, int d) {
      this.s = s;
      this.e = e;
      this.d = d;
    }
  }
}
