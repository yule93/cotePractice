package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 16120 PPAP(그리디, 많은 분기점)
// ! 해당 문제는 P를 PPAP로 치환한 게 PPAP 규칙을 따르는지 확인해야 함. 즉, PPAP는 다시 P로 치환될 수 있으니,
// ! 모두 치환하고 맨 마지막에 남은 문자열이 P이면 PPAP가 마따
// 244ms
public class boj16120 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();

    int nowI = 0;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (c == 'P') {
        nowI++;
      } else {
        if (c == 'A') {
          if (nowI >= 2 && i + 1 < str.length() && str.charAt(i + 1) == 'P') {
            nowI--;
            i++;
          } else {
            // 틀린 문장일 때, ex) PPA, PPAPA
            System.out.println("NP");
            return;
          }
        } else {
          // 틀린 문장일 때, ex) 갑작스런 PPAD와 같은 문자열
          System.out.println("NP");
          return;
        }
      }
    }
    if (nowI > 1) {
      System.out.println("NP");
      return;
    }
    System.out.println("PPAP");
  }
}
