package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj1158 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder("");
        sb.append("<");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // N명의 사람
        int K = Integer.parseInt(st.nextToken());       // K번째 사람 제거  
        
        /*
        // 첫 번째 가장 무난한 queue 쓰는 방법. 그러나 느리다.      
        Queue<Integer> que = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            que.offer(i);
        }
        while(que.size() != 1) {
            for(int i = 0; i < K - 1; i++) {
                que.offer(que.poll());
            }
            sb.append(que.poll()).append(", ");
        }

        //sb.delete(sb.length() - 2, sb.length());
        sb.append(que.poll()).append(">");
        */

        // 두 번째 방법. linkedlist 사용하기. 개빠르다. 그리고 더 간결.... 비순차적 배열보다 순차적 배열이 더 나아보임.
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 1; i <= N; i++) list.add(i);
        int i = 0, count = 0;
        while(list.size() != 1) {
            i = (i+K-1) % list.size();
            sb.append(list.remove(i)).append(", ");
        }
        sb.append(list.removeFirst()).append(">");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
