package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 1859 백만장자 프로젝트(D2, 구현, DP)
public class swea1859 {
    public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
		for(int test_case = 1; test_case <= T; test_case++) {
			int day = Integer.parseInt(br.readLine());
            long[] value = new long[day];
            long answer = 0;
            
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < day; i++) {
                value[i] = Integer.parseInt(st.nextToken());
            }
            long max = value[day-1];
            for(int sellDay = day-2; sellDay >= 0; sellDay--) {
            	if(value[sellDay] < max) {
                	answer += (max-value[sellDay]);
                } else {
                	max = value[sellDay];
                }
            }
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}
