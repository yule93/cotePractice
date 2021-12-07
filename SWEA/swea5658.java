package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 5658 보물상자 비밀번호 (슬라이딩 윈도우?, 자료구조 큐)
public class swea5658 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      ArrayList<Integer> list = new ArrayList<>();

      String str = br.readLine();   // N개의 16진수가 적힌 코드를 받아서 쪼갬
      Queue<String> q = new LinkedList<>();
      for (int i = 0; i < N; i++) {
        q.add(String.valueOf(str.charAt(i)));
      }

      // N/4번 회전하면 경우의 수 끝...!
      for (int r = 0; r < N/4; r++) {
        String front = q.poll();
        q.add(front);
        for (int i = 0; i < 4; i++) {
          front = "";
          for (int j = 0; j < N / 4; j++) {
            String token = q.poll();
            front += token;
            q.add(token);
          }
          // System.out.println(i+"번째: "+front);
          if (!list.contains(Integer.parseInt(front, 16))) {
            list.add(Integer.parseInt(front, 16));
          }
        }
      }

      Collections.sort(list, Comparator.reverseOrder());
      // for (int ex : list) {
      //   System.out.println(ex);
      // }
      sb.append("#").append(tc).append(" ").append(list.get(K-1)).append("\n");
    }
    
    System.out.print(sb.toString());
  }
}
