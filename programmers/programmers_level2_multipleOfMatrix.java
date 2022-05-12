package programmers;

public class programmers_level2_multipleOfMatrix {
  public static void main(String[] args) {
    for (int[] row : solution(new int[][] { {1, 4}, {3, 2}, {4, 1} }, new int[][] { {3, 3}, {3, 3} })) {
      for (int elem : row) {
        System.out.print(elem+" ");
      }
      System.out.println();
    }
  }
  public static int[][] solution(int[][] arr1, int[][] arr2) {
    int[][] answer = new int[arr1.length][arr2[0].length];

    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr2[0].length; j++) {
        int res = 0;
        for (int r = 0; r < arr1[0].length; r++) {
          res += arr1[i][r] * arr2[r][j];
        }
        answer[i][j] = res;
      }
    }

    return answer;
  }
}
