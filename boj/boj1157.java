package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 1157 단어 공부(문자열, 구현)
// 284ms
public class boj1157 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine().toLowerCase();
    int[] alpha = new int[26];    // 각 알파벳의 빈도수를 저장하는 배열

    for (int i = 0; i < str.length(); i++) {
      alpha[str.charAt(i) - 'a']++;
    }

    int max = 0, count = 0;
    for (int i = 0; i < 26; i++) {
      if (alpha[i] > 0 && alpha[i] > alpha[max]) {
        count = 1;
        max = i;
      } else if (alpha[i] > 0 && alpha[i] == alpha[max]) {
        count++;
      }
    }

    System.out.println(count > 1 ? "?" : (char)(max+'A'));
  }
}
