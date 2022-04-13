package programmers;

// 프로그래머스 가장 큰 사각형 찾기(그리디, dp)
// 완전 수학 문제네.... 백준 토마토 문제들이랑 탐색이 비슷한 것 같음.
/*
1 0 1
1 1 1
1 1 1
이 있다고 치면, (1,1)에서 바라보는 정사각형 만족 조건은 바로 위, 바로 왼쪽, 대각선 바로 위가 1이어야지 자기 위치 기준 정사각형임. 즉, 0 초과인 수가 있다면 +1씩 해주면 변의 길이가 되는 것. 바텀업 방식 dp라 생각해도 무방...
*/
public class programmers_level2_mostBiggestSquare {
  public static void main(String[] args) {
    System.out.println(solution(new int[][] {}));
  }

  public static int solution(int[][] board) {
    int[][] map = new int[board.length + 1][board[0].length + 1];
    int maxLen = 0;
    for (int i = 1; i <= board.length; i++) {
      for (int j = 1; j <= board[0].length; j++) {
        if (board[i - 1][j - 1] != 0) {
          int min = Math.min(Math.min(map[i - 1][j], map[i][j - 1]), map[i - 1][j - 1]);
          map[i][j] = min + 1;
          maxLen = Math.max(maxLen, min + 1);
        }
      }
    }
    return (int) Math.pow(maxLen, 2);
  }
}
