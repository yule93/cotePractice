package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 1747 소수&팰린드롬
// 824ms
public class boj1747 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine()); // 1~1_000_000

    if (N == 1) {
      System.out.println(2);
      return;
    }

    String answer = "";
    for (int i = N; i <= 1_003_001; i++) {
      boolean flag = true;
      for (int j = 2; j * j <= i; j++) {
        if (i % j == 0) {
          flag = false;
          break;
        }
      }

      if (flag) {
        String str = "";
        for (int n = 0; n < String.valueOf(i).length(); n++) {
          str += String.valueOf(i).charAt(String.valueOf(i).length() - n - 1);
        }
        if (Integer.parseInt(str) == i) {
          answer = str;
          break;
        }
      }
    }

    System.out.println(answer);
  }
}
