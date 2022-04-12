package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 1011 Fly me to the Alpha Centauri (수학, ㄹㅇ dp...)
// * dfs로 푸는 줄 알았는데 수학문제였다.... 어쩐지 안 풀리더라
// * 규칙성은 n^2과 n^2+n마다 나타난다. 즉, 1, 2, 4, 6, 9, 12, 16, 20....
// * 규칙성을 경계로, 이동횟수가 1씩 늘어난다!
// 120ms
public class boj1011 {
  static int answer;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken()); // 시작 지점
      int y = Integer.parseInt(st.nextToken()); // 도착 지점

      answer = 987_654_321;
      int dist = y - x;
      
      int root = (int) Math.sqrt(dist);
      if (dist - Math.pow(root, 2) == 0) {
        answer = 2 * root - 1;
      } else if (dist - Math.pow(root, 2) <= root)
        answer = 2 * root;
      else
        answer = 2 * root + 1;

      sb.append(answer).append("\n");
    }

    System.out.print(sb.toString());
  }

  // public static void dfs(int dest, int k, int res, int pos) {
  //   // k는 이동 가능한 거리의 중간값
  //   if (res > answer || pos > dest)
  //     return;
  //   if (pos == dest) {
  //     // 현재 위치랑 목적지랑 좌표가 같다면
  //     answer = Math.min(answer, res);
  //     return;
  //   }

  //   for (int i = k - 1; i <= k + 1; i++) {
  //     if (i <= 0)
  //       continue;
  //     if (pos + i <= 0)
  //       continue;
  //     dfs(dest, i, res + 1, pos+i);
  //   }
  // }
}
