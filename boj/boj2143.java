package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// boj 2143 두 배열의 합
public class boj2143 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine()); // 목표 값
    
    int n = Integer.parseInt(br.readLine()); // A배열의 길이
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int m = Integer.parseInt(br.readLine());
    int[] brr = new int[m];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < m; i++) {
      brr[i] = Integer.parseInt(st.nextToken());
    }

    // ! 무조건 A와 B 원소 중 하나 이상은 꼭 들어가야 함
    // 1. A로 만들 수 있는 부분합 만들기
    ArrayList<Integer> sumA = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int temp = arr[i];
      sumA.add(temp);
      for (int j = i + 1; j < n; j++) {
        temp += arr[j];
        sumA.add(temp);
      }
    }

    ArrayList<Integer> sumB = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      int temp = brr[i];
      sumB.add(temp);
      for (int j = i + 1; j < m; j++) {
        temp += brr[j];
        sumB.add(temp);
      }
    }

    Collections.sort(sumA);
    Collections.sort(sumB);

    // 이분 탐색
    int sa = 0;
    int sb = sumB.size() - 1;
    long count = 0;

    while (sa < sumA.size() && sb >= 0) {
      long sum = sumA.get(sa) + sumB.get(sb);
      if (sum == T) {
        int a = sumA.get(sa);
        int b = sumB.get(sb);

        long aCount = 0;
        long bCount = 0;

        while (sa < sumA.size() && sumA.get(sa) == a) {
          aCount++;
          sa++;
        }
        while (sb >= 0 && sumB.get(sb) == b) {
          bCount++;
          sb--;
        }

        count += aCount * bCount;
      } else if (sum < T)
        sa++;
      else if (sum > T)
        sb--;
    }

    System.out.println(count);
  }
}
