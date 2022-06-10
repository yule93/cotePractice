package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 15591 MooTube(Silver) (그래프 탐색)
// 1532ms
// * 노드 번호 꼭 확인해....
public class boj15591 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int Q = Integer.parseInt(st.nextToken());

    ArrayList<int[]>[] list = new ArrayList[N+1];    
    for (int i = 0; i <= N; i++) {
      list[i] = new ArrayList<>();
    }

    for (int i = 0; i < N-1; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int u = Integer.parseInt(st.nextToken());
      list[s].add(new int[] { e, u });
      list[e].add(new int[] {s, u});
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < Q; i++) {
      // 쿼리 처리
      // * 유사도가 k일 때, v를 보고 있는 소들에게 몇 개의 동영상이 추천될지 묻는 것
      st = new StringTokenizer(br.readLine());
      int k = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      int answer = 0;
      boolean[] visited = new boolean[N + 1];
      Queue<Integer> q = new LinkedList<>();
      q.add(v);
      while (q.size() > 0) {
        int nowV = q.poll();
        for (int j = 0; j < list[nowV].size(); j++) {
          if (v == list[nowV].get(j)[0])
            continue;
          if (visited[list[nowV].get(j)[0]])
            continue;
          if (list[nowV].get(j)[1] >= k) {
            q.add(list[nowV].get(j)[0]);
            visited[list[nowV].get(j)[0]] = true;
            answer++;
          }
        }
      }

      sb.append(answer).append("\n");
    }

    System.out.print(sb.toString());
  }
}
