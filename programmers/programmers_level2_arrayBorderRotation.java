package programmers;

public class programmers_level2_arrayBorderRotation {
  public static void main(String[] args) {
    int[][] queries1 = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
    int[][] queries2 = { { 1, 1, 2, 2 }, { 1, 2, 2, 3 }, { 2, 1, 3, 2 }, { 2, 2, 3, 3 } };

    for (int num : solution(6, 6, queries1)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }

  public static int[] solution(int rows, int columns, int[][] queries) {
    // 쿼리들은 (x1, y1, x2, y2)로 이뤄져있다.
    // 테두리만 회전한다...!! 즉, 테두리 부분인 한 칸씩만 회전이 되는 것!
    int[][] map = new int[rows][columns];

    // 초기화 작업
    int count = 1;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        map[i][j] = count++;
      }
    }

    int[] answer = new int[queries.length];
    // 쿼리 실행
    for (int q = 0; q < queries.length; q++) {
      int y1 = queries[q][0] - 1;
      int x1 = queries[q][1] - 1;
      int y2 = queries[q][2] - 1;
      int x2 = queries[q][3] - 1;

      int first = map[y1][x1];
      int min = first;

      for (int r = y1 + 1; r <= y2; r++) {
        min = Math.min(min, map[r][x1]);
        map[r - 1][x1] = map[r][x1];
      }
      for (int c = x1 + 1; c <= x2; c++) {
        min = Math.min(min, map[y2][c]);
        map[y2][c - 1] = map[y2][c];
      }
      for (int r = y2 - 1; r >= y1; r--) {
        min = Math.min(min, map[r][x2]);
        map[r + 1][x2] = map[r][x2];
      }
      for (int c = x2 - 1; c > x1; c--) {
        min = Math.min(min, map[y1][c]);
        map[y1][c + 1] = map[y1][c];
      }
      map[y1][x1 + 1] = first;
      answer[q] = min;
    }

    return answer;
  }
}
