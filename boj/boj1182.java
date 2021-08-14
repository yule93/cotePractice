package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1182 {
    static int[] arr;
    static boolean[] visited;
    static int N, S, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        answer = 0;
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        /** 1. 비트마스킹 */
        for(int i = 1; i < 1 << N; i++) {
            int sum = 0;
            for(int j = 0; j < N; j++) {
                if((i & 1 << j) > 0) {
                    sum += arr[j];
                } 
            }
            if(sum == S) answer++;
        }

        /** 2. 재귀(BFS) */
        
        sumCount(0, 0);
        bw.write(String.valueOf(answer/2));
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void sumCount(int sum, int depth) {
        if(sum == S) {
            answer++;
            return;
        } if(depth == N) return;
        sumCount(sum + arr[depth], depth + 1);
    }
}
