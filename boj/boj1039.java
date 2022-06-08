package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 1039 교환(dfs)
// * K가 10보다 작거나 같은 자연수래서 dfs 느낌이 왔음
// 204ms
public class boj1039 {
  static int K, len, answer;
  static String N;
  static int[][] visited;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = st.nextToken();
    K = Integer.parseInt(st.nextToken());
    len = N.length();
    visited = new int[K + 1][1000001]; // [변경횟수] [최대숫자] 2차원 visit 배열
    answer = -1;
    answer = dfs(N, 0);

    System.out.println(answer);
  }

  static int dfs(String copyN, int depth) {
    int num = Integer.parseInt(copyN);
    if (depth == K)
      return num;

    int result = visited[depth][num];
    if (result != 0)
      return result;

    result = -1;
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        // 바뀐 수가 0으로 시작하면 안 되니까 패스
        if (i == 0 && copyN.charAt(j) == '0')
          continue;
        String temp = swapLoc(copyN, i, j);
        int change = dfs(temp, depth + 1);
        result = change > result ? change : result;
      }
    }
    visited[depth][num] = result;
    return result;
  }

  // String의 i, j 위치가 들어오면 바꿔주는 함수
  static String swapLoc(String str, int i, int j) {
    char[] chars = str.toCharArray();
    char tmp = chars[i];
    chars[i] = chars[j];
    chars[j] = tmp;
    return String.valueOf(chars);
  }
}
