package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 5215번 햄버거 다이어트(DP, knapsack?, 부분집합)
public class swea5215 {
    static int taste[], cal[], tasteDP[][];
    static int N, L, answer;
    public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T;
		T = Integer.parseInt(br.readLine().trim());

		for(int TC = 1; TC <= T; TC++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            answer = 0;
            taste = new int[N];
            cal = new int[N];
            tasteDP = new int[N][L+1];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                taste[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
                // System.out.println(taste[i] + ", " + cal[i]);
                for(int j = 0; j < L; j++) {
                    tasteDP[i][j] = -1;
                }
            }
            
            knapsack(0, L);

            StringBuilder sb = new StringBuilder("");
            sb.append("#" + TC + " " + answer);
            bw.write(sb.toString());
		}       // 입력받은 TC만큼

        bw.flush();
        bw.close();
        br.close();
	}   // end of main

    // DP(knapsack) 처리 해주는 메서드
    static int knapsack(int idx, int remain) {
		if(idx == N) return 0;
		int temp = tasteDP[idx][remain];
		if(temp != -1) return temp;
		if(cal[idx] <= remain) temp = knapsack(idx + 1, remain - cal[idx]) + taste[idx];
		temp = Math.max(temp, knapsack(idx + 1, remain));
		answer = Math.max(answer, temp);
		return tasteDP[idx][remain] = temp;
	}

}       // end of class
