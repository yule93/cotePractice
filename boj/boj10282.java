package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// boj 10282 해킹 (완전탐색, 다익스트라)
// 다익스트라 알고리즘을 아주 세심하게 짜야하는 문제였음.... 거리 기록을 방문체크 밖에서 했더니 시간메모리 초과 와장창
// 1112ms
public class boj10282 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine());

    for (int tc = 0; tc < T; tc++) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수 1~10000
      int d = Integer.parseInt(st.nextToken()); // 의존성 개수 1 ~ 100000
      int c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호 1 ~ n

      ArrayList<int[]>[] dep = new ArrayList[n];
      for (int i = 0; i < n; i++) {
        dep[i] = new ArrayList<int[]>();
      }

      for (int i = 0; i < d; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        int s = Integer.parseInt(st.nextToken());
        dep[b].add(new int[] { a, s });
      }

      int[] visited = new int[n];
      Arrays.fill(visited, 987_654_321);
      
      PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
          return Integer.compare(o1[1], o2[1]);
        }
      });
      q.add(new int[] { c - 1, 0 }); // 다음으로 갈 컴퓨터 번호, 방문 개수, 방문 시간
      visited[c - 1] = 0;
      while (q.size() > 0) {
        int[] now = q.poll();

        for (int[] next : dep[now[0]]) {
          if (visited[next[0]] > now[1]+next[1]) {
            visited[next[0]] = now[1] + next[1];
            q.add(new int[] { next[0], now[1] + next[1] });
          }
        }
      }

      int fTime = 0;
      int count = 0;
      for (int i = 0; i < n; i++) {
        if (visited[i] != 987_654_321) {
          fTime = Math.max(fTime, visited[i]);
          count++;
        }
      } 
      sb.append(count).append(" ").append(fTime).append("\n");
    }
    
    System.out.print(sb.toString());
  }
}
