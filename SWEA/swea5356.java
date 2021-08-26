package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// SWEA 5356 의석이의 세로로 말해요 (단순 배열): 조심해야 하는 건 배열이 비어있는 문자일 경우 답 문자열에 추가하지 않는 것
// 105ms
public class swea5356 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
		int T = Integer.parseInt(br.readLine());

        int max = 0;
		for(int test_case = 1; test_case <= T; test_case++) {
			char[][] arr = new char[5][15];
            for(int i = 0; i < 5; i++) {
                String str = br.readLine();
                max = Math.max(max, str.length());
                for(int j = 0; j < str.length(); j++) {
                	arr[i][j] = str.charAt(j);
                }
            }
            sb.append("#").append(test_case).append(" ");
            for(int i = 0; i < max; i++) {
            	for(int j = 0; j < 5; j++) {
                	if(arr[j][i] != 0) sb.append(arr[j][i]);
                }
            }
            sb.append("\n");
		}
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}