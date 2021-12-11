package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 12761 돌다리 (BFS, 메모이제이션)
// 172ms
public class boj12761 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 돌의 위치는 0~100000
    int A = Integer.parseInt(st.nextToken()); // 스카이콩콩 1의 힘
    int B = Integer.parseInt(st.nextToken()); // 스카이콩콩 2의 힘
    int N = Integer.parseInt(st.nextToken()); // 동규의 현재 위치
    int M = Integer.parseInt(st.nextToken()); // 주미의 현재 위치
    int[] memo = new int[100_001];
    Arrays.fill(memo, 123_456_789);
    // BFS 시작
    Queue<Integer> q = new LinkedList<>();

    memo[N] = 0;
    q.add(N); // 동규의 현재 위치와 이동한 횟수
    while (q.size() > 0) {
      int nowDong = q.poll();
      if (nowDong == M) {
        break;
      }

      int[] way = { nowDong + 1, nowDong - 1, nowDong + A, nowDong - A, nowDong + B, nowDong - B,
          nowDong * A, nowDong * B }; // 동규가 이동할 수 있는 돌구간
      for (int i = 0; i < 8; i++) {
        if (way[i] > 100_000 || way[i] < 0 || memo[way[i]] <= memo[nowDong]+1)
          continue;
        memo[way[i]] = memo[nowDong]+1;
        q.add(way[i]);
      }
    }

    System.out.println(memo[M]);
  }
}
