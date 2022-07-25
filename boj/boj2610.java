package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// boj 2610 회의준비(플로이드 와샬)
public class boj2610 {

  static int N, M, dist[][];

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine()); // 1~100. ghldml dlsdnjstn
    M = Integer.parseInt(br.readLine());

    // N이 작아서 인접 행렬 가능한 것 같음
    dist = new int[N][N];
    for (int i = 0; i < N; i++) {
      Arrays.fill(dist[i], 987_654_321);
    }

    for (int m = 0; m < M; m++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken()) - 1;
      int e = Integer.parseInt(st.nextToken()) - 1;
      dist[s][e] = dist[e][s] = 1;
    }

    // 플로이드 와샬 시작
    floyd();

    // for (int i = 0; i < N; i++) {
    // for (int j = 0; j < N; j++) {
    // System.out.print(dist[i][j] + "\t");
    // }
    // System.out.println();
    // }

    // bfs
    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        // 벨만포드 아니어서 compare로 비교 안 해도 되지만 하면 좋음
        return o1[1] - o2[1];
      }
    });
    boolean[] visited = new boolean[N];
    ArrayList<Integer> connect = new ArrayList<>();
    ArrayList<Integer> leader = new ArrayList<>();

    int count = 0;
    for (int i = 0; i < N; i++) {
      if (!visited[i]) {
        count++;
        visited[i] = true;
        connect.add(i);
        for (int j = 0; j < N; j++) {
          if (dist[i][j] != 987_654_321 && i != j) {
            connect.add(j);
            visited[j] = true;
          }
        }

        // 대표 뽑기
        for (int j = 0; j < connect.size(); j++) {
          int max = Integer.MIN_VALUE;
          int v = connect.get(j);
          for (int k = 0; k < N; k++) {
            if (dist[v][k] != 987_654_321 && v != k) {
              max = Math.max(max, dist[v][k]);
            }
          }

          System.out.println(v + ", " + max);
          pq.add(new int[] { v, max });
        }

        System.out.println();
        leader.add(pq.peek()[0]);
      }
    }

    // for (int i = 0; i < N; i++) {
    // for (int j = 0; j < N; j++) {
    // System.out.print(dist[i][j] + "\t");
    // }
    // System.out.println();
    // }

    StringBuilder sb = new StringBuilder();
    sb.append(count).append("\n");

    for (int num : leader)
      sb.append(num + 1).append("\n");

    System.out.print(sb.toString());
  }

  public static void floyd() {
    // 경출도
    for (int k = 0; k < N; k++) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          // 경로 중 최소값
          if (i != j) {
            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
          }
        }
      }
    }
  }
}