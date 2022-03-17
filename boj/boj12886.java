package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 12886 돌 그룹(BFS)
// 단순히 반복 돌면서 하면 될 줄 알았는데... 생각해보니 한 번 나왔던 중복 돌그룹을 제거할 방법이 없음
// * 그래서 visited로 해결
// 252ms
public class boj12886 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int sum = 0;
    int[] arr = new int[3];   // 1~500
    for (int i = 0; i < 3; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      sum += arr[i];
    }
    
    if (sum % 3 != 0) {
      System.out.println(0);
      return;
    }

    // * ABC 그룹의 돌 개수가 모두 맞춰질 때까지 무한 반복해야함.
    // * 나머지 두 개의 돌이 같은 상황이 오면 자동적으로 판가름이 나므로 2차원 방문 배열
    boolean[][] visited = new boolean[1501][1501];
    Queue<int[]> q = new LinkedList<>();
    
    q.add(new int[] { arr[0], arr[1], arr[2] });
    visited[arr[0]][arr[1]] = true;
    while (q.size() > 0) {
      int[] group = q.poll();

      int A = group[0];
      int B = group[1];
      int C = group[2];

      if (A == B && B == C) {
        System.out.println(1);
        return;
      }

      if (A != B) {
        int X = A > B ? A - B : A * 2;
        int Y = A < B ? B - A : B * 2;

        if (!visited[X][Y]) {
          q.add(new int[] { X, Y, C });
          visited[X][Y] = true;
        }
      }

      if (A != C) {
        int X = A > C ? A - C : A * 2;
        int Y = A < C ? C - A : C * 2;

        if (!visited[X][Y]) {
          q.add(new int[] { X, B, Y });
          visited[X][Y] = true;
        }
      }

      if (B != C) {
        int X = B > C ? B - C : B * 2;
        int Y = B < C ? C - B : C * 2;

        if (!visited[X][Y]) {
          q.add(new int[] { A, X, Y });
          visited[X][Y] = true;
        }
      }
    }
    
    System.out.println(0);
  }
}
