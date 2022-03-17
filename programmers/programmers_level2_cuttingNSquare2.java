package programmers;

// * n이 1천만 이하. 2차원 배열 미리 잡고 시작하면 큰일남
// ! 그리디 문제인듯...?
// 8초도 나오는 어마무시함 ㄷㄷ
public class programmers_level2_cuttingNSquare2 {
  class Solution {
    public int[] solution(int n, long left, long right) {
      int[] answer = new int[(int) (right - left) + 1];

      for (int i = 0; i < answer.length; i++) {
        int max = Math.max((int) ((left + i) / n), (int) ((left + i) % n));
        answer[i] = max + 1;
      }

      return answer;
    }
  }
}
