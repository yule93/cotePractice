package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 3307번: 최장 증가 부분 수열(LIS, DP)
public class swea3307 {
    static int[] DP;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T;
		T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int length = Integer.parseInt(br.readLine());       // 전체 수열의 길이
            int answer = 0;                                     // 답 저장할 곳
            
            DP = new int[length];
            DP[0] = 1;
            
            int[] arr = new int[length];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < length; i++) {
                arr[i] = Integer.parseInt(st.nextToken());      // 입력받은 수열들 값 저장
            }

            for(int i = 0; i < length; i++) {
                DP[i] = 1;
                for(int j = 0; j < i; j++) {
                    if(arr[i] > arr[j]) {
                        DP[i] = Math.max(DP[i], DP[j] + 1);
                    }
                }
            }

            for(int i = 0; i < length; i++) {
                answer = Math.max(answer, DP[i]);
            }

            // 연속하는 가장 긴 증가 부분 수열(ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ)
            /* for(int i = 1; i < length; i++) {
                if(arr[i] > arr[i-1]) {
                    count++;
                    DP[i] = Math.max(DP[i-1], count);
                } else continue;
            } 
            for(int i = 0; i < length; i++) answer = Math.max(answer, DP[i]);*/

            bw.write("#" + tc + " " + String.valueOf(answer) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }       // end of main
}           // end of class
