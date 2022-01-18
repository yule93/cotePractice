package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

// boj 7662 이중 우선순위 큐(트리맵 자료구조)
// 3056ms
// 6초 제한으로, 트리맵 탐색이 O(1), Q가 백만 이하, T가 
public class boj7662 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    for (int tc = 1; tc <= T; tc++) {
      int Q = Integer.parseInt(br.readLine());
      TreeMap<Integer, Integer> tm = new TreeMap<>();
      for (int i = 0; i < Q; i++) {
        String[] temp = br.readLine().split(" ");
        int input = Integer.parseInt(temp[1]);
        switch (temp[0]) {
          case "I":
            if (tm.containsKey(input)) {
              tm.put(input, tm.get(input) + 1);
            } else {
              tm.put(input, 1);
            }
            break;
          case "D":
            if (tm.size() > 0) {
              if (input == -1) {
                int min = tm.firstKey();
                if (tm.get(min) == 1)
                  tm.remove(min);
                else
                  tm.put(min, tm.get(min) - 1);
              } else {
                int max = tm.lastKey();
                if (tm.get(max) == 1)
                  tm.remove(max);
                else
                  tm.put(max, tm.get(max) - 1);
              }
            }
            break;
          default:
            break;
        }

      }
      if (tm.isEmpty())
        sb.append("EMPTY").append("\n");
      // else if(tm.size() == 1)
      //   sb.append(tm.firstKey()).append(" ").append(tm.firstKey()).append("\n");
      else
        sb.append(tm.lastKey()).append(" ").append(tm.firstKey()).append("\n");
    }
    System.out.print(sb.toString());
  }
}
