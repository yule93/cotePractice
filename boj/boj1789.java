package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 1789 수들의 합(그리디)
// 136ms
public class boj1789 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long S = Long.parseLong(br.readLine());    // 1~429496795. int의 크기를 넘어선다.

    long sum = 0;
    int count = 0;   // 서로 다른 수가 더해져야 하므로, 더해질 때마다 수가 1씩 증가하면 각기 다른 수가 된다.
    while (sum <= S) {
      // ! 예를 들어, 55라는 숫자는 1~10을 모두 더한 숫자인데 50이라는 숫자는 여기서 5를 빼고 나머지를 다 더한 숫자가 된다.
      // ! 따라서, S보다 sum의 크기가 작을 때 while문이 종료되고 count를 출력하게 된다면 여태 더한 수에서 1개를 빼면 성립된다.
      // ! 역시 그리디는 아이디어 문제...!!
      sum += (++count);
    }

    System.out.println(sum == S ? count : count-1);
  }
}
