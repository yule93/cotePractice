package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 1535 안녕 (냅색 문제)
// 132ms
public class boj1535 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    // 최대 기쁨 느끼기.... 냅색
    // ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ 세준이 체력이 0이나 음수 되면 죽음ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
    int N = Integer.parseInt(br.readLine());  // 1~20
    
    int[] consume = new int[N+1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      consume[i] = Integer.parseInt(st.nextToken());
    }

    int[] happy = new int[N+1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      happy[i] = Integer.parseInt(st.nextToken());
    }

    int[] answer = new int[101]; // 체력이 100이므로, 냅색을 생각하면 무게가 100인 가방에 담는 것
    for (int i = 1; i <= N; i++) {
      for (int hp = 100; hp >= 0; hp--) {
        int sum = hp + consume[i];
        if (sum < 100 && sum > 0) {
          answer[sum] = Math.max(answer[sum], answer[hp] + happy[i]);
          // System.out.println(answer[sum]);
        } else if (sum <= 0)
          continue;
      }
    }

    System.out.println(answer[99]);
  }
}
