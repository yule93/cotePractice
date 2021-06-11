package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

// BOJ 13334, 자료구조
public class boj13334 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        long[][] arr = new long[N][2];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(start, end);
            arr[i][1] = Math.max(start, end);
        }

        long L = Long.parseLong(br.readLine());        // 철로 L의 길이

        Arrays.sort(arr, (A, B) -> {    // 람다식 비교. 가장 앞에 있는 경로 순으로 재정렬
            if(A[1] == B[1]) return Long.compare(A[0], B[0]);
            return Long.compare(A[1], B[1]);
        });

        PriorityQueue<Long> pq = new PriorityQueue<>();
        int max = 0;

        for(int i = 0; i < N; i++) {
            long start = arr[i][0];
            long end = arr[i][1];
            if(end - start > L) continue;
            pq.add(start);
            while(!pq.isEmpty() && pq.peek() < end - L) {       // L이라는 철로 안에 그뭐냐 집과 직장간 경로가 들어갈 수 있을 때: 철로보다 경로가 짧을 때
                pq.poll();
            }
            max = Math.max(max, pq.size());
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }

    static class Pair {
        int y;
        int x;
 
        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
