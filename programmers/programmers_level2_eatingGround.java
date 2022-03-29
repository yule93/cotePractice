package programmers;

public class programmers_level2_eatingGround {

  public static void main(String[] args) {
    System.out.println(solution(new int[][] {{1,2,3,5}, {5,6,7,8}, {4,3,2,1}}));
  }

  public static int solution(int[][] land) {
    for (int i = 1; i < land.length; i++) {
      land[i][0] += Math.max(Math.max(land[i - 1][1], land[i - 1][2]), land[i - 1][3]);
      land[i][1] += Math.max(Math.max(land[i - 1][0], land[i - 1][2]), land[i - 1][3]);
      land[i][2] += Math.max(Math.max(land[i - 1][1], land[i - 1][0]), land[i - 1][3]);
      land[i][3] += Math.max(Math.max(land[i - 1][1], land[i - 1][2]), land[i - 1][0]);
    }

    int answer = 0;
    for (int i = 0; i < land[0].length; i++) {
      answer = Math.max(answer, land[land.length - 1][i]);
    }

    return answer;
  }
}
