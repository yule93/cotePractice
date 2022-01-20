package boj.structureForCheckWeek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 14888 연산자 끼워넣기(dfs, 재귀?)
// N이 2~11인거 봐선 dfs를 진행해도 될 것 같은 느낌...! 최악의 경우 O(N!) = 11! = 39_916_800 = 약 0.4초
// 132ms
public class boj14888 {

  static int[] oper = new int[4];
  static int[] arr;
  static int max = -123_456_789, min = 123_456_789, N;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine()); // 2~11
    StringBuilder sb = new StringBuilder();

    arr = new int[N]; // 숫자 저장할 배열
    StringTokenizer st = new StringTokenizer(br.readLine()); // 1 ~ 100의 숫자
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 4; i++) {
      // 연산자 개수 저장
      oper[i] = Integer.parseInt(st.nextToken());
    }

    // ! 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다.
    dfs(arr[0], 0, 0, 0, 0, 0);

    sb.append(max).append("\n").append(min);
    System.out.print(sb.toString());
  }

  public static void dfs(int sum, int depth, int plus, int minus, int multi, int divide) {
    if (depth == N-1) {
      min = Math.min(min, sum);
      max = Math.max(max, sum);
      return;
    }

    if (oper[0] > plus) {
      dfs(sum + arr[depth+1], depth + 1, plus + 1, minus, multi, divide);
    }
    if (oper[1] > minus) {
      dfs(sum - arr[depth+1], depth + 1, plus, minus + 1, multi, divide);
    }
    if (oper[2] > multi) {
      dfs(sum * arr[depth+1], depth + 1, plus, minus, multi + 1, divide);
    }
    if (oper[3] > divide) {
      dfs(sum / arr[depth+1], depth + 1, plus, minus, multi, divide + 1);
    }
  }
}
