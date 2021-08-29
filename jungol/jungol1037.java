package jungol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class jungol1037 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];
        // 가로 세로 1의 개수가 홀수인지 세어서 저장하는 변수
        // 만약 odd가 1개면 수정 가능
        // row는 바꿀 0의 행 저장하는 변수
        int odd = 0;
        int row = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sum = 0;
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum += arr[i][j];
            }
            if(sum % 2 != 0) {
                row = i;
            }
        }
		int col = 0;
		for(int i = 0 ; i < n ; i++) {
			int sum = 0;
			for(int j = 0 ; j < n ; j++) {
				sum += arr[j][i];
			}
			if (sum % 2 != 0) {
				col = i;
				odd++;
			}
		}
		if(odd > 1) {
		    bw.write("Corrupt");
            bw.flush();
            return;
		}
		else if(odd == 1) {
            sb.append("Change bit (").append(row + 1).append(",").append(1 + col).append(")");
			bw.write(sb.toString());
		}
		else bw.write("OK");
        bw.flush();
        bw.close();
        br.close();
    }
}
