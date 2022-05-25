package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// boj 1715 카드 정렬하기(우선순위 큐, 그리디)
// * Collections.sort로 하면 N^2이라 시간초과가 난다. 대신에 PQ를 쓰면 NlogN 가 돼서 시간 초과가 되지 않는다.
// ! 이 때, 중요한게 N이 1이라면 비교할 필요가 없으므로 답이 N이 아니라 10이 돼야 함....
public class boj1715 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    if (N == 1) {
      System.out.println(0);
      return;
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < N; i++) {
      pq.add(Integer.parseInt(br.readLine()));
    }

    int answer = 0;
    while (pq.size() > 1) {
      int sum = pq.poll() + pq.poll();
      answer += sum;
      pq.add(sum);
    }

    System.out.println(answer);
  }
}
