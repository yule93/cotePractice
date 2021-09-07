package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1074 {
    static int answer = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N);
		answer = solve(size, r, c);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
	}
	
	public static int solve(int size, int r, int c) {
        if (size <= 1) {        // 이제 더 divide가 되지 않을 때,
            return (r * 2) + c;
        }

        else {
            // 
            return ((r / size) * 2 * size * size)
            + ((c / size) * size * size)
            + solve(size / 2, r % size, c % size);
        }
    }

    /** 비트연산으로도 계산이 가능한데 개쩐다 진짜 */
    /* 
        for(int i = 0; i < size; i++) {
            answer += (((r & 1) << 1) + (c & 1) << 1);      // 와 쩐다
            r >>= 1;
            c >>= 1;
            if(r == 0 && c == 0) break;
        }
    */
}
