package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 2281 데스노트(dp, dfs)
// * 1학년이랑 비슷한듯 하면서 다른 문제... 다시 복습해봐야 할 거 같음 ㅜㅜ
// * 1학년이랑 비슷하게 2가지 갈래로 나뉘는 건 맞음. 1) 같은 줄에 이어서 쓸 때, 2) 다음 줄에 쓸 때
// 172ms
public class boj2281 {

  static int[][] dp;
  static int[] name;
  static int n, m;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken()); // 사람 명수 1 ~ 1000
    m = Integer.parseInt(st.nextToken()); // 노트의 가로 칸 개수. 1 ~ 1000

    name = new int[n];
    for (int i = 0; i < n; i++) {
      name[i] = Integer.parseInt(br.readLine());
    }

    dp = new int[1001][1002];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], -1);
    }

    int index = 1;
    int count = name[0] + 1;  // 이름칸수+띄어쓰기 한 칸
    System.out.println(check(index, count));
  }

  public static int check(int index, int count) {
    if (index == n)
      return 0;
    int ans = dp[index][count];
    if (ans != -1)
      return ans;

    int left = m - count + 1; // 남은 칸 수
    ans = check(index + 1, name[index] + 1) + (left * left);

    if (count + name[index] <= m) {
      ans = Math.min(check(index + 1, count + name[index] + 1), ans);
    }

    return dp[index][count] = ans;
  }

  public static int dfs3(int col, int index) {
    if (index == n)
      return 0;
    if (col >= m)
      return dfs3(name[index] + 1, index + 1) + (col >= m ? 1 : 0);

    int result = dp[col][index];
    if (result != -1)
      return result;

    result = dfs3(name[index] + 1, index + 1) + (m - col + 1) * (m - col + 1);
    if (col + name[index] <= m) {
      // 줄 안 바꾸고도 이어쓸 수 있다면
      result = Math.min(result, dfs3(col + name[index] + 1, index + 1));
    }

    return result;
  }

  public static int dfs2(int index, int rem) {
    if (index == n)
      return 0;
    int ret = dp[index][rem];
    if (ret != -1)
      return ret;
    ret = rem * rem + dfs2(index + 1, m - name[index] % m);
    if (rem >= 2) {
      if (rem == m)
        ret = Math.min(ret, dfs2(index + 1, m - name[index] % m));
      else if (rem >= name[index] + 1)
        ret = Math.min(ret, dfs2(index + 1, rem - (name[index] + 1)));
    }
    return ret;
  }

  public static int dfs(int index, int count, int answer) {
    if (index == n)
      return answer;
    int remain = m - count + 1;
    // 한 줄 엔터 쳤을 때,
    int num = dfs(index + 1, name[index], answer + (int) Math.pow(remain, 2));

    if (count + name[index] <= m) {
      // 엔터 안 쳐도 될 때,
      num = Math.min(dfs(index + 1, count + name[index] + 1, answer), num);
    }

    return num;
  }
}
