package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 13019 A를 B로
// 136ms
public class boj13019 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String strA = br.readLine();
    String strB = br.readLine();
    int[] arrA = new int[26];   // 알파벳 개수 비교용 배열
    int[] arrB = new int[26];   // 알파벳 개수 비교용 배열
    int answer = 0, length = strA.length();

    for (int i = 0; i < length; i++) {
      arrA[strA.charAt(i) - 'A']++;
      arrB[strB.charAt(i) - 'A']++;
    }

    boolean flag = true;
    for (int i = 0; i < 26; i++) {
      if (arrA[i] != arrB[i]) {
        flag = false;
        break;
      }
    }

    for (int i = length - 1; i >= 0; i--) {
      if (strA.charAt(i) == strB.charAt(length - 1 - answer))
        answer++;
    }

    System.out.println(flag ? length-answer : -1);
  }
}
