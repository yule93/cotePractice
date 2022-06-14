package programmers;

import java.util.ArrayList;

// 프로그래머스 하노이의 탑
// n이 15라서 재귀로 가능. 전에 dp로 알려주신 방법이 있던 것 같은데 기억이? 안 남? 큰일남?
public class programmers_level2_topOfHanoi {
  public static void main(String[] args) {
    for (int[] arr : solution(2)) {
      System.out.println(arr[0] + ", " + arr[1] + " ");
    }
  }

  public static int[][] solution(int n) {
    int[][] answer = {};
    ArrayList<int[]> seq = new ArrayList<>();

    hanoi(n, 1, 3, 2, seq);
    answer = new int[seq.size()][2];
    for (int i = 0; i < seq.size(); i++) {
      answer[i] = seq.get(i);
    }
    return answer;
  }

  public static void hanoi(int n, int from, int to, int via, ArrayList<int[]> seq) {
    int[] move = { from, to };

    if (n == 1) {
      seq.add(move);
    } else {
      hanoi(n - 1, from, via, to, seq);
      seq.add(move);
      hanoi(n - 1, via, to, from, seq);
    }
  }
}
