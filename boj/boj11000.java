package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// boj 11000 강의실 배정(우선순위 큐)
// * 만약 강의 시간이 겹친다면 다른 강의실을 써야 하고, 겹치지 않는다면 같은 강의실을 써도 된다는 거니까 앞에 들어간 강의의 끝나는 시간이 뒤에 들어올 강의의 시작 시간보다 이전이라면 빼내고 뒤에 이을 강의를 pq에 넣어주면 된다!
// 644ms
public class boj11000 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[][] arr = new int[N][2]; // 각 강의의 시작과 끝 시간 저장
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      arr[i][0] = Integer.parseInt(st.nextToken());
      arr[i][1] = Integer.parseInt(st.nextToken());
    }

    // * 시작 시간 순 정렬
    Arrays.sort(arr, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
      }
    });

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    pq.add(arr[0][1]);

    for (int i = 1; i < N; i++) {
      if (pq.peek() <= arr[i][0])
        pq.poll();
      pq.add(arr[i][1]);
    }

    System.out.println(pq.size());
  }
}