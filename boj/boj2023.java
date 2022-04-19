package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 2023 신기한 소수(dfs, 소수)
// * 에라토스테네스의 체는 N이 최대값이 8로, 10^8이 되면 자바가 만들 수 있는 배열의 최대수를 초과하기 때문에 메모리 초과가 남....
// 168ms
public class boj2023 {
  static StringBuilder sb;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    // * 에라토스테네스의 체로 소수 거르기
    dfs(N, "");

    System.out.print(sb.toString());
  }

  public static void dfs(int N, String str) {
    if (str.length() == N) {
      sb.append(str).append("\n");
      return;
    }

    for (int i = 1; i <= 9; i++) {
      int num = Integer.parseInt(str + i);
      if (checkPrime(num)) {
        dfs(N, str+i);
      }
    }
  }

  public static boolean checkPrime(int num) {
    if (num < 2)
      return false;
    for (int i = 2; i * i <= num; i++) {
      if (num % i == 0)
        return false;
    }
    return true;
  }
  
  // * 시간 초과 남 ㅎㅎ... ㅜㅜ
  public static void eratos(int N) {
    StringBuilder sb = new StringBuilder();
    // * 에라토스테네스의 체로 소수 거르기
    int end = (int) Math.pow(10, N);
    boolean[] check = new boolean[end];
    check[0] = check[1] = true;
    for (int i = 2; i < end; i++) {
      for (int j = i * i; j < end; j += i) {
        // * 소수 판별
        check[j] = true;
      }
    }
    
    for (int i = (int) Math.pow(10, N-1); i < end; i++) {
      if (!check[i]) {
        String str = String.valueOf(i);
        boolean flag = true;
        for (int j = 1; j <= str.length(); j++) {
          int num = Integer.parseInt(str.substring(0, j));
          if (check[num]) {
            flag = false;
            break;
          }
        }
        if (flag)
          sb.append(str).append("\n");
      }
    }
  }
}
