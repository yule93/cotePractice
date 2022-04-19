package programmers;

// 프로그래머스 양궁대회(딱 봐도 dfs)
// * 화살 개수가 10개 제한이기 때문...!
public class programmers_level2_yanggungCompetition {
  public static void main(String[] args) {
    // for (int num : solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 })) {
    //   System.out.print(num + ", ");
    // }
    // System.out.println();

    // * 답: 1,1,1,1,1,1,1,1,0,0,2
    for (int num : solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 })) {
      System.out.print(num + ", ");
    }
  }

  static int[] answer, lionScores, peachScores;
  static int lionScore, result;

  public static int[] solution(int n, int[] info) {
    answer = new int[] { -1 };
    // 기본으로 10개짜리 배열을 리턴하지만
    // 어떤 방법으로도 어피치를 이기지 못한다면 -1만 든 배열 반환
    // 중복 순열...?
    lionScore = 123_456_789;
    result = 0;
    lionScores = new int[11];
    peachScores = info;
    dfs(0, n, 0);

    return answer;
  }

  public static void dfs(int depth, int n, int count) {
    if (depth == 11) {
      int lionSum = 0;
      int peachSum = 0;
      for (int i = 0; i < 10; i++) {
        if (lionScores[i] != 0 || peachScores[i] != 0) {
          if (lionScores[i] > peachScores[i])
            lionSum += (10 - i);
          else
            peachSum += (10 - i);
        }
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 11; i++) {
        sb.append(lionScores[i]).append(" ");
      }
      if (lionSum > peachSum && (lionSum - peachSum) > result) {
        // * 어피치와 점수차가 최대로 나는 경우를 저장해야 함.
        // * 만약 점수차가 크게 나는 경우가 여러가지 있을 경우, 점수가 낮은 쪽을 저장해야 함.
        result = lionSum - peachSum;
        lionScore = lionSum;
        lionScores[10] = n - count;
        answer = lionScores.clone();
        System.out.println(sb.toString()+": "+lionSum+", "+peachSum);
      }
      return;
    }

    for (int i = 0; i < n; i++) {
      // ! n개 이내로 쏴야 하므로 화살 합계가 n개를 넘어서면 안 된다. 백트래킹을 해줘야 함.
      if (count + i > n)
        break;
      lionScores[depth] = i;
      dfs(depth + 1, n, count + i);
    }
  }
}