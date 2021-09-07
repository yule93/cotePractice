package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 9229번: 한빈이와 Spot Mart (DP 혹은 완탐)
public class swea9229 {
    public static void main(String args[]) throws Exception {
		//Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder("");
		int T = Integer.parseInt(br.readLine());
        int[] snack;

        // 한빈이는 정확히 두 봉지를 사야하는게 관건
		for(int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case);

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());       // 스팟마트의 과자봉지 총 갯수
            int M = Integer.parseInt(st.nextToken());       // 한빈이가 최대한으로 들 수 있는 과자의 무게
            st = new StringTokenizer(br.readLine());
            snack = new int[N];
            for(int i = 0; i < N; i++) {
                snack[i] = Integer.parseInt(st.nextToken());
            }

            int answer = -1;
            for(int i = 0; i < N; i++) {
                int max = -1;
                for(int j = 0; j < N; j++) {
                    if(j != i && snack[i] + snack[j] <= M) {
                        max = Math.max(max, snack[i] + snack[j]);
                    }
                }
                answer = Math.max(answer, max);
            }
            sb.append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}
