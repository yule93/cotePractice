package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// boj 17225 세훈이의 선물가게(우선순위 큐)
// ! 조심해야 하는 점: 같은 시간에 포장을 시작하면 상민이가 더 일찍 들어가게 되어있음....
// 416 ms
public class boj17225 {

  public static class Order implements Comparable<Order> {
    int startTime; // 시작 시간
    char color; // 포장지 색

    public Order(int startTime, char color) {
      this.startTime = startTime;
      this.color = color;
    }

    @Override
    public int compareTo(Order o) {
      // 시작 시간이 같으면 상민이 먼저
      if (this.startTime == o.startTime) {
        return o.color - this.color; // 양수 => 오름차순
      }
      return this.startTime - o.startTime; // 시작 시간 오름차순 정렬
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int A = Integer.parseInt(st.nextToken()); // 상민이가 포장하는 데 걸리는 시간
    int B = Integer.parseInt(st.nextToken()); // 지수가
    int N = Integer.parseInt(st.nextToken()); // 손님 수. 1~1000

    ArrayList<Integer> sangmin = new ArrayList<>();
    ArrayList<Integer> jisu = new ArrayList<>();

    int sangminT = 0;   // 상민이 포장 끝 시간
    int jisuT = 0;    // 지수 포장 끝 시간
    PriorityQueue<Order> pq = new PriorityQueue<>();    // 상민이랑 지수 우선순위 비교
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken()); // 주문 시간
      String color = st.nextToken(); // 상민인지 지수인지
      int num = Integer.parseInt(st.nextToken()); // 맡긴 선물 개수

      for (int j = 0; j < num; j++) {
        if (color.equals("B")) {
          // * 상민이한테
          if (sangminT >= t) {
            pq.add(new Order(sangminT, 'B'));
            sangminT += A;
          } else {
            pq.add(new Order(t, 'B'));
            sangminT = t + A;
          }
        } else if (color.equals("R")) {
          // * 지수한테
          if (jisuT >= t) {
            pq.add(new Order(jisuT, 'R'));
            jisuT += B;
          } else {
            pq.add(new Order(t, 'R'));
            jisuT = t + B;
          }
        }
      }
    }
    
    int index = 1;
    while (pq.size() > 0) {
      Order order = pq.poll();
      if (order.color == 'B') {
        sangmin.add(index++);
      } else {
        jisu.add(index++);
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(sangmin.size()).append("\n");
    for (int i = 0; i < sangmin.size(); i++) {
      sb.append(sangmin.get(i)).append(" ");
    }
    sb.append("\n").append(jisu.size()).append("\n");
    for (int i = 0; i < jisu.size(); i++) {
      sb.append(jisu.get(i)).append(" ");
    }

    System.out.print(sb.toString());
  }
}
