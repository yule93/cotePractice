package programmers;

public class programmers_level2_quadCompactAndCounting {
  static int[] answer;

  public static void main(String[] args) {
    
    for (int num : solution(new int[][] { { 1, 1, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 1 }, { 1, 1, 1, 1 } })) {
      System.out.println(num);
    }
  }

  public static int[] solution(int[][] arr) {
    answer = new int[2];
    quad(arr.length, 0, 0, arr);
    return answer;
  }

  public static void quad(int n, int y, int x, int[][] arr) {
    if (n == 1) {
      answer[arr[y][x]]++;
      return;
    }

    boolean flag = true;
    for (int i = y; i < y + n; i++) {
      for (int j = x; j < x + n; j++) {
        if (arr[y][x] != arr[i][j])
          flag = false;
      }
    }

    if (flag) {
      answer[arr[y][x]]++;
      return;
    }

    quad(n / 2, y, x, arr);
    quad(n / 2, y + n / 2, x, arr);
    quad(n / 2, y, x + n / 2, arr);
    quad(n / 2, y + n / 2, x + n / 2, arr);

  }
}
