package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// boj 8983 사냥꾼(이분탐색!)
// * M*N이 100억이 돼서 시간 초과가 무조건 난다.... 따라서 이분탐색과 같이 시간 복잡성을 줄일 수 있는 방법을 모색해야 함.
// 736 ms
public class boj8983 {
  static int M, N, L;
  static int[] range;
  static int[][] animals;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken()); // 사대의 수
    N = Integer.parseInt(st.nextToken()); // 동물의 수
    L = Integer.parseInt(st.nextToken()); // 사정거리

    range = new int[M];
    animals = new int[N][2]; // 사대 정보

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      //
      range[i] = Integer.parseInt(st.nextToken());
    }
    // 동물의 위치 정보
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      animals[i] = new int[] { a, b };
    }

    System.out.println(process());
  }

  private static int process() {
    int res = 0;
    Arrays.sort(range); // 사대의 위치를 정렬
    
    for (int i = 0; i < N; i++) {
      // 동물 위치가 사격 가능 거리 L보다 멀면 못 잡으니까 패스
      if (animals[i][1] > L)
        continue;
      res += search(i);
    }
    return res;
  }

  // 이분 탐색
  private static int search(int idx) {
    int start = 0, end = M - 1, mid = 0;
    int min = animals[idx][0] + animals[idx][1] - L;
    int max = animals[idx][0] - animals[idx][1] + L;
    while (start <= end) {
      mid = (start + end) / 2;
      if (min <= range[mid] && range[mid] <= max)
        return 1;
      else if (range[mid] < max)
        start = mid + 1;
      else
        end = mid - 1;
    }
    return 0;
  }

}
