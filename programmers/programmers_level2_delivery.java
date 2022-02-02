package programmers;

// 프로그래머스 배달 (플로이드 와샬)
// N이 50이하이므로 공간 복잡도가 해봤자 50*50*4 = 1만 byte 밖에 안 됨.
// 시간 복잡도는 플로이드 와샬이므로, O(N^3) = 50*50*50 = 75000번이라 편하게 가능
public class programmers_level2_delivery {
  public static void main(String[] args) {
    //
  }

  public static int solution(int N, int[][] road, int K) {
    int[][] map = new int[N][N];

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map.length; j++) {
        if (i == j) {
          map[i][j] = 0;
          continue;
        }
        // ! 플로이드 와샬을 이용하기 위해서 미리 다른 마을로 가는 비용을 최대값으로 초기화해둔다.
        // * K는 음식 배달이 가능한 시간을 나타내며, 1 이상 500,000 이하이므로 500001로 초기화!
        map[i][j] = 500001;
      }
    }

    for (int i = 0; i < road.length; i++) {
      if (map[road[i][0] - 1][road[i][1] - 1] < road[i][2])
        continue;
      map[road[i][0] - 1][road[i][1] - 1] = road[i][2];
      map[road[i][1] - 1][road[i][0] - 1] = road[i][2];
    }

    // ! 경 출 도 순으로
    for (int k = 0; k < N; k++) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
        }
      }
    }

    int answer = 0;

    for (int i = 0; i < N; i++) {
      if (map[0][i] <= K)
        answer++;
    }

    return answer;
  }
}
