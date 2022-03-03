package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 11404 플로이드
// O(N^3) = 최악의 경우 1000000인 약 100만회 연산. 0.01초밖에 안 걸리므로 무시하고 진행해도 된다.
// 440ms
public class boj11404 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    int n = Integer.parseInt(br.readLine());    // 도시의 개수 2~100
    int m = Integer.parseInt(br.readLine());    // 버스의 개수 1~100000
    
    // 인접 헹렬
    int[][] bus = new int[n+1][n+1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        bus[i][j] = 987_654_321;
      }
    }
    
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken()); // 시작점
      int e = Integer.parseInt(st.nextToken()); // 도착점
      int c = Integer.parseInt(st.nextToken()); // 코스트
      bus[s][e] = Math.min(bus[s][e], c);   // ! 단... s와 e가 같은 버스 경로가 여러개일 수 있음....
    }
    
    // 플로이드 와샬. 경유>출발>도착 순
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        for (int k = 1; k <= n; k++) {
          if (i == k || i == j || j == k) {
            // 무한루프 안 타도록 경유 설정. 그리고 버스 노선이 없는 곳은 갈 필요 없음.
            continue;
          }

          bus[j][k] = Math.min(bus[j][k], bus[j][i] + bus[i][k]);
        }
      }
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (bus[i][j] == 987_654_321) {
          sb.append(0).append(" ");
        } else {
          sb.append(bus[i][j]).append(" ");
        }
      }
      sb.append("\n");
    }

    System.out.print(sb.toString());
  }
}
