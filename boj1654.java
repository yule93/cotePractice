import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class boj1654 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int K = Integer.parseInt(st.nextToken());    // 주어지는 줄의 갯수
        int N = Integer.parseInt(st.nextToken());    // 필요한 줄의 갯수
        int[] lineArr = new int[K];
        long answer = 0;
        long sum = 0;        // 주어진 줄 길이 모두 더한것
        
        for(int i = 0; i < K; i++) {
            lineArr[i] = Integer.parseInt(br.readLine());
            sum += (long)lineArr[i];
        }
        answer = 1;
        long left = 1;
        long right = sum/N;
        while(left <= right) {
            long mid = (left + right) / 2;
            int count = checkPossibleLineNo(lineArr, mid, K);
            if(count >= N) {
                left = mid + 1;       
                if(mid > answer) {
                    answer = mid;
                }
            } else {
                right = mid - 1;
            }
        }
        
        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
    private static int checkPossibleLineNo(int[] wires, long mid, int K) {
        int count = 0;
        for(int i = 0; i < K; i++) {
            count += wires[i] / mid;
        }
        return count;
    }
}