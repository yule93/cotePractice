package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj10974 {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N];

        /** 2. 비트마스킹! */
        /* */

        /** 1. FM식 전형적인 재귀문으로 모든 조합 구하기 */
        combi(0);
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void combi(int depth) {
        if(depth == N) {
            for(int num : arr) sb.append(num).append(" ");
            sb.append("\n");
            return;
        }
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                arr[depth] = i + 1;
                visited[i] = true;
                combi(depth + 1);
                visited[i] = false;
            }
        }
    }
    
}
