package programmers;

// 소모할 수 있는 피로도 K는 1~5000
// ! 던전의 길이는 1~8이므로 dfs 순열을 만든다 해도 시간복잡도인 O(NPx)가 최악의 경우 8! = 약 4만 회 밖에 나오지 않는다.
// * dfs 순열
public class programmers_level2_fatique {
  static int answer;
  public static void main(String[] args) {
    System.out.println(solution(80, new int[][] {{80, 20}, {50, 40}, {30, 10}}));
  }

  public static int solution(int k, int[][] dungeons) {
    answer = -1;
    int[] arr = new int[dungeons.length];
    boolean[] visited = new boolean[dungeons.length];

    dfs(0, dungeons, arr, visited, dungeons.length, k);

    return answer;
  }

  // * 순열 만드는 dfs 함수
  public static void dfs(int depth, int[][] dungeons, int[] perm, boolean[] visited, int len, int k) {
    if (depth == len) {
      int fati = k;
      int count = 0;
      for (int i = 0; i < len; i++) {
        // 던전의 최소 필요 피로도가 현 피로도보다 작거나 같을 때,
        if (fati >= dungeons[perm[i]][0]) {
          fati -= dungeons[perm[i]][1];
          count++;
        } else
          break;
      }

      answer = Math.max(answer, count);
      return;
    }

    for (int i = 0; i < len; i++) {
      if (!visited[i]) {
        perm[depth] = i;
        visited[i] = true;
        dfs(depth + 1, dungeons, perm, visited, len, k);
        visited[i] = false;
      }
    }
  }
}
