package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// boj 6209 제자리 멀리뛰기(이분탐색)
// 452ms
public class boj6209 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int d = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    List<Integer> rockList = new ArrayList<>();
    rockList.add(0);
    for (int i = 0; i < n; i++) {
      rockList.add(Integer.parseInt(br.readLine()));
    }
    rockList.add(d);

    Collections.sort(rockList);
    int answer = 0;
    int left = 0, right = d;
    while (left <= right) {
      int mid = (left + right) / 2;
      int pos = 0, count = 0;
      for (int i = 1; i <= n+1; i++) {
        if (rockList.get(i) - rockList.get(pos) < mid)
          count++;
        else
          pos = i;
      }
      if (count > m)
        right = mid - 1;
      else {
        left = mid + 1;
        answer = mid;
      }
    }
    
    System.out.println(answer);
  }

}
