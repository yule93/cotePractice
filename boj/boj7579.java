package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 7579 앱(dp, knapsack)
// 156ms
public class boj7579 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken()); // 1~ 100. 사용 가능한 앱
    int M = Integer.parseInt(st.nextToken()); // 1~1천만. 필요한 메모리로, 앱을 비활성화해 확보해야 하는 메모리의 양이다.
    
    int[] usingMemory = new int[N+1];     // 현재 앱이 사용하고 있는 비용
    int[] unuseMemory = new int[N+1];     // 비활성화 비용
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      usingMemory[i] = Integer.parseInt(st.nextToken());
    }

    int sumCost = 0;
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      unuseMemory[i] = Integer.parseInt(st.nextToken());
      sumCost += unuseMemory[i];
    }

    int answer = 123_456;
    int[][] ks = new int[N+1][sumCost + 1]; // 배낭 문제이므로 선택할 수 있는 할당 메모리의 양은 sumCost만큼이다.
    for (int i = 1; i <= N; i++) {
      int memory = usingMemory[i];
      int cost = unuseMemory[i];

      for (int j = 0; j <= sumCost; j++) {
        if (j - cost >= 0) {
          ks[i][j] = Math.max(ks[i - 1][j], ks[i - 1][j - cost] + memory);
        } else {
          ks[i][j] = ks[i - 1][j];
        }

        if (ks[i][j] >= M) {
          // 비워내야 하는 메모리만큼 만족이 됐다면 답을 비교해 최소한으로 드는 비활성화 코스트를 찾아주면 된다.
          answer = Math.min(answer, j);
        }
      }
    }
    
    System.out.println(answer);
  }
}
