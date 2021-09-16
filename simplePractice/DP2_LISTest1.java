package simplePractice;

import java.util.Arrays;
import java.util.Scanner;

public class DP2_LISTest1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] LIS = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int size = 0;
        for (int i = 0; i < N; i++) {
            // 중복값이 없으므로 탐색 실패: 음수값 --> 삽입위치로 환산
            int temp = Math.abs(Arrays.binarySearch(LIS, 0, size, arr[i])) - 1;
            LIS[temp] = arr[i];

            // 추가된 위치가 맨 뒤라면 사이즈 증가
            if (temp == size)
                ++size;
        }

        System.out.println(size);

        // int max = 0;
        // for (int i = 0; i < N; i++) {
        // LIS[i] = 1;
        // // i인 자신보다 작은 숫자들과 크기를 비교하기 위해서(앞에 있는 애가 나보다 작은지 확인)
        // for (int j = 0; j < i; j++) {
        // if (arr[j] < arr[i] && LIS[j] + 1 > LIS[i]) {
        // LIS[i] = LIS[j] + 1;
        // }
        // } // i를 끝으로 하는 최장길이 값 계산 완료
        // if (max < LIS[i])
        // max = LIS[i];
        // }

        // System.out.println(max);
    }
}