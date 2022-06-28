package boj.structureForCheckWeek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 12851 숨바꼭질 2(bfs, dp)
// * bfs를 구현할 때 중요한 점은 이미 방문했다고 다시 못가는 방법이 아닌, 현재 이동한 횟수가 해당 위치에서 이동할 수 있는 최소값보다 크다면 이동할 수 없다.
// 204ms
public class boj12851 {
  static int[] dx = { -1, 1 };
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    if (N >= K) {
      System.out.println((N - K) + "\n" + 1);
      return;
    }

    Queue<Integer> q = new LinkedList<>();
    q.add(N);
    int[] visited = new int[100001];
    int answer = 987_654_321;
    int how = 0;
    visited[N] = 1;
    while (q.size() > 0) {
      int now = q.poll();
      if (answer < visited[now])
        continue;
      
      int[] move = { now - 1, now + 1, now * 2 };
      for (int i = 0; i < 3; i++) {
        int next = move[i];
        if (next < 0 || next > 100000)
          continue;
        if (next == K) {
          answer = visited[now];
          how++;
        }

        // * 한 번도 방문한 적이 없는 위치 or 한 번도 방문해본 적 없을 때 저장해둔 최소 이동횟수와 똑같은 이동횟수를 가졌다면
        if (visited[next] == 0 || visited[next] >= visited[now] + 1) {
          q.add(next);
          visited[next] = visited[now] + 1;
        }
      }
    }

    System.out.println(answer + "\n" + how);
  }
  
  public static void bfs(int N, int K) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { N, 0 });

    int[] dist = new int[100001];
    int[] count = new int[100001];
    Arrays.fill(dist, 987_654_321);
    dist[N] = 0;
    count[N] = 1;
    while (q.size() > 0) {
      int[] now = q.poll();
      if (now[0] > 100000 || now[0] < 0)
        continue;
      for (int i = 0; i < 3; i++) {
        int npos = now[0];
        if (i == 2)
          npos *= 2;
        else
          npos += dx[i];
        if (npos < 0 || npos > 100000 || now[1] + 1 > dist[npos])
          continue;
        
        // 동생 찾음
        if (dist[npos] == now[1] + 1)
          count[npos] += count[now[0]];
        else {
          dist[npos] = dist[now[0]] + 1;
          count[npos] += count[now[0]];
          q.add(new int[] { npos, now[1] + 1 });
        }
      }
    }

    System.out.println(dist[K]+"\n"+count[K]);
  }
}
