package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 2437 저울(그리디, 정렬!)
// 만들 수 없는 양의 정수 최소값을 구하기
// 132ms
public class boj2437 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] weight = new int[N];
    for (int i = 0; i < N; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(weight);

    // 생각해보니... 정렬하고 작은 값부터 더해나간 추들의 무게보다 1 큰 값이 weight에 없다면 잴 수 없는 최소값인 거 같아서....
    // * ex) 1 3 4 6 이 있다 치면 1 ok, 3과 1에다 2의 무게를 더해 2 ok, 3, 4, 6은 그냥 ok, 5는 1+4하거나 6-1 하면 돼서 ok
    int sum = 0;
    for (int i = 0; i < N; i++) {
      if (sum + 1 < weight[i]) {
        break;
      }
      sum += weight[i];
    }

    System.out.println(sum + 1);
  }
}
