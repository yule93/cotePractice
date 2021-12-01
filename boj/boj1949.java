package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// boj 1949 우수 마을 (dp, tree)
// 인접 리스트
public class boj1949 {
  
  static int[] town;
  static List<Integer>[] list;
  static boolean[] check;
  static int[][] memo;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    int n = Integer.parseInt(br.readLine());

    town = new int[n + 1];
    memo = new int[n + 1][2];
    check = new boolean[n + 1];
    list = new ArrayList[n + 1];

    for (int i = 0; i < n + 1; i++) {
      list[i] = new ArrayList<>();
    }

    for (int i = 1; i < n + 1; i++) {
      Arrays.fill(memo[i], -1);
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < n + 1; i++) {
      town[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < n - 1; i++) {
      st = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      // B에서 A로, A에서 B로 갈 수 있는 경로 생성
      list[b].add(a);
      list[a].add(b);
    }

    System.out.println(Math.max(dfs(1, 0), dfs(1, 1) + town[1]));
  }

  static int dfs(int pos, int flag) {
    if (list[pos].size() == 0)
      return 0;
    if (memo[pos][flag] != -1)
      return memo[pos][flag];

    check[pos] = true;
    memo[pos][flag] = 0;
    for (int child : list[pos]) {
      if (check[child])
        continue;
      if (flag == 1) {
        // 이전 노드가 우수 마을일 경우
        memo[pos][flag] += dfs(child, 0);
      } else {
        // 이전 노드가 우수 마을이 아닐 경우
        // dfs탐색으로 최대값 구하기
        memo[pos][flag] += Math.max(dfs(child, 1) + town[child], dfs(child, 0));
      }
    }

    check[pos] = false;
    return memo[pos][flag];
  }
}
