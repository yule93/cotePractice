package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea2383_1 {
  
  static int answer, N, personNum;
  static int[][] map;
  static int[] perm;
  static ArrayList<int[]> person;
  static ArrayList<int[]> stair;
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      answer = 123_456_789;
      N = Integer.parseInt(br.readLine());
      person = new ArrayList<>();
      stair = new ArrayList<>(); // 계단 정보 저장하는 배열
      map = new int[N][N];

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
          if (map[i][j] != 0) {
            if (map[i][j] == 1)
              person.add(new int[] { i, j });
            else
              stair.add(new int[] { i, j });
          }
        }
      }

      personNum = person.size();
      perm = new int[personNum];
      dfs(0);
      sb.append("#").append(tc).append(" ").append(answer).append("\n");
    }

    System.out.print(sb.toString());
  }
  
  // 중복 순열을 돌릴 dfs
  public static void dfs(int depth) {
    if (depth == personNum) {
      int totalTime = 0;
      for (int i = 0; i < 2; i++) {
        int[] nowPos = stair.get(i);
        int[] pq = new int[2 * N + 2]; // 시간이 t일 때 1번 계단을 이용하는 사람들
        int[] pq2 = new int[2 * N + N * N]; // 시간이 t일 때 2번 계단을 이용하는 사람들
        int pe = 123_456_789;
        for (int j = 0; j < personNum; j++) {
          if (perm[j] == i) {
            int dx = Math.abs(stair.get(i)[1] - person.get(j)[1]);
            int dy = Math.abs(stair.get(i)[0] - person.get(j)[0]);
            pe = Math.min(pe, dx + dy + 1); // 둘 중 내려간 시간이 더 작은 걸 pe에 저장
            pq[dx + dy + 1]++;
          }
        }

        if (pe == 123_456_789)
          continue; // 아무도 점심 먹으러 못 가....

        int nowTime = pe;
        int stairTime = map[nowPos[0]][nowPos[1]];
        for (int j = 1; j < 2 * N + 2; j++) {
          while (pq[i] > 0) {
            pq[j]--;
            int remainTime = stairTime;
            for (int k = j; k < pq2.length; k++) {
              if (pq2[k] < 3) {
                pq2[k]++;
                remainTime--;
              }
              if (remainTime == 0) {
                nowTime = Math.max(nowTime, k + 1);
                break;
              }
            }
          }
        }
        totalTime = Math.max(totalTime, nowTime);
      }
      answer = Math.min(answer, totalTime);
      return;
    }
    for (int i = 0; i < 2; i++) {
      perm[depth] = i;
      dfs(depth + 1);
    }
  }
}
