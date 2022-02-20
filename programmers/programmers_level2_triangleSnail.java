package programmers;

/*
1
2 9
3 10 8
4 5 6 7
*/
// 백준에 있는 회전 문제랑 비슷하게 풀면 될 것 같은 이 느낌...?
public class programmers_level2_triangleSnail {
  // 0: 아래, 1: 오른쪽, 2: 대각선 위
  public static int[] dy = { 1, 0, -1 };
  public static int[] dx = { 0, 1, -1 };

  public static void main(String[] args) {
    int[] sol1 = solution(4);
    for (int num : sol1) {
      System.out.print(num+" ");
    }
  }

  public static int[] solution(int n) {
    int[] answer = new int[(n * (n + 1)) / 2]; // 숫자의 총 개수는 1~n까지 다 더한 개수이므로 합 공식인 n*(n+1)/2 을 이용한다.
    int[][] matrix = new int[n][n];

    int num = 1; // 매트릭스에 넣을 숫자
    int x = 0, y = -1;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n-i; j++) {
        x += dx[i % 3];
        y += dy[i % 3];
        // System.out.println(y+", "+x+": "+num);
        matrix[y][x] = num++;
      }
    }

    int k = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0)
          break;
        answer[k++] = matrix[i][j];
      }
    }

    return answer;
  }
}
