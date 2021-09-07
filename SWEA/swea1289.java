package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 1289번: 원재의 메모리 복구하기(문자열, 단순구현)
public class swea1289 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
	
		for(int tc = 1; tc <= T; tc++) {
            int answer = 0;
            String line = br.readLine();

            for(int i = 0; i < line.length(); i++) {
                if(i == 0) {
                    if(line.charAt(i) == '1') answer++;
                } else {
                    if(line.charAt(i) != line.charAt(i - 1)) answer++;
                }
            }
            /**
             * 또 다른 방법
             * char temp = '0';
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) != temp){
	            	    temp =line.charAt(i);
    	                answer++;
                    }
                }
             */

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
