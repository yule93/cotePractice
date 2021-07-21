package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// 1289번: 원재의 메모리 복구하기(문자열, 단순구현)
public class swea1289 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T;
		T = Integer.parseInt(br.readLine());
	
		for(int tc = 1; tc <= T; tc++) {
            int answer = 0;
            String line = br.readLine();
            char change = '0';

            for(int i = 0; i < line.length(); i++) {
                if(change != line.charAt(i)) {
                    answer++;
                    change = line.charAt(i);
                }
            }

            bw.write("#" + tc + " " + String.valueOf(answer) + "\n");
		}

        bw.flush();
        bw.close();
        br.close();
    }
}
