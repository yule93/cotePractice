package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// boj 1744 수묶기(그리디, 정렬, 분기점)
// * 음수x음수, 음수x양수, 0x음수, 0x양수일 때를 고려해서 분기점을 만들어줌.
// * 이외에는 순서에 상관 없으므로 정렬을 통해 양수일 때는 큰 수*다음 큰 수가 가장 큰 값이 저장되고 음수일 때는 가장 작은 수*다음 작은 수가 가장 큰 값이 되므로 구현해주면 된다.
// 120ms
public class boj1744 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    if (N == 1) {
      System.out.println(br.readLine());
      return;
    }

    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    // 어떤 수를 묶으려 할 때, 위치에 상관 없이 묶을 수 있다.
    Arrays.sort(arr);
    int answer = 0;
    int num = 0;
    for (int i = 0; i < N; i++) {
      if (arr[i] < 0 && arr[i + 1] <= 0) {
        answer += arr[i] * arr[i + 1];
        i++;
      } else if (arr[i] < 0 && arr[i + 1] > 0) {
        answer += arr[i];
        num = i + 1;
        break;
      } else if (arr[i] == 0) {
        answer += arr[i];
        num = i + 1;
        break;
      } else if (arr[i] > 0) {
        num = i;
        break;
      }
    }
    
    // System.out.println(num);

    for (int i = N - 1; i >= num; i--) {
      if (i > num) {
        answer += Math.max(arr[i] * arr[i - 1], arr[i] + arr[i-1]);
        i--;
      } else if (i == num) {
        answer += arr[i];
      }
    }

    System.out.println(answer);
  }
}
