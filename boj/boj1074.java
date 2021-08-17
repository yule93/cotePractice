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
            // r과 c를 size로 나눴다가 다시 곱하는 이유: int형이기 때문에 소수점이 떨어짐.
            return ((r / size) * 2 * size * size)
            + ((c / size) * size * size)
            + solve(size / 2, r % size, c % size);
        }
    }
}
