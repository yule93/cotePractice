package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// boj 1065 한수(재귀, 백트래킹)
// N은 1000 이하(4자리 이하라는 말)
// 128ms
public class boj1065 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    if (N < 100) {
			System.out.println(N);
		} else {
			int answer = 99;
			
			for (int i = 100; i <= N; ++i) {
        int diff1 = i / 100 % 10;
        int diff2 = i / 10 % 10;
        int diff3 = i % 10;
        if (diff3 - diff2 == diff2 - diff1)
          answer++;
			}
			
			if (N == 1000) answer--;
			
			System.out.println(answer);
		}
  }
}
