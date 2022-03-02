package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 11657 타임머신(벨만포드! 1865 웜홀과 비슷한 문제라고 함.)
// ! 모든 간선이 있는 경우 - 다익스트라 (한 정점 → 모든 정점), 플로이드-와샬(모든 정점 → 모든 정점)
// ! 음수 간선이 있는 경우 - 벨만-포드
// 단순 반복만으로는 O(NM)밖에 안 되므로 최악의 경우 500x6000 = 약 300만회로 0.03초 정도 걸린다.
// 256ms
public class boj11657 {
  static class Bus {
    int u;
    int v;
    int val;

    public Bus(int u, int v, int val) {
      this.u = u;
      this.v = v;
      this.val = val;
    }
  }

  static int N, M;
  static Bus[] e;
  static long[] dist;
  static int INF = Integer.MAX_VALUE;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());   // 도시의 개수. 1~500
    M = Integer.parseInt(st.nextToken());   // 버스의 개수. 1~6000

    e = new Bus[M];
    dist = new long[N + 1];
    // 최단 거리 테이블 초기화
    for (int i = 1; i < N + 1; i++) {
      dist[i] = INF;
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int val = Integer.parseInt(st.nextToken());
      e[i] = new Bus(u, v, val);
    }

    StringBuilder sb = new StringBuilder();

    if (bellmanford(1)) {
      // 음수 순환 존재하면 -1 출력
      sb.append(-1).append("\n");
    } else {
      // 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단거리 출력
      for (int i = 2; i < N + 1; i++) {
        if (dist[i] == INF) {// 도달할 수 없으면 -1
          sb.append(-1).append("\n");
        } else { // 최단 거리 출력
          sb.append(dist[i]).append("\n");
        }
      }
    }

    System.out.print(sb.toString());
  }

  // * 벨만포드 함수
  static boolean bellmanford(int start) {
    dist[start] = 0;
    // n번 반복
    for (int i = 1; i < N + 1; i++) {
      // 매 반복마다 모든 간선을 확인
      for (int j = 0; j < M; j++) {
        int cur = e[j].u;
        int next = e[j].v;
        int cost = e[j].val;

        if (dist[cur] == INF)
          continue;
        // 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 짧은 경우
        if (dist[next] > (dist[cur] + cost)) {
          dist[next] = dist[cur] + cost;

          // N번째 라운드에서 값이 갱신된다면 음수 순환 존재
          if (i == N) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
