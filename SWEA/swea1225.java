package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 1225번: 암호생성기 (큐, 구현)
public class swea1225 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= 10; tc++) {
            int T = Integer.parseInt(br.readLine());        // 테케 번호
            sb.append("#").append(T);
            Queue<Integer> queue = new LinkedList<>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 8; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }

            int num = 1;            // 각 숫자의 연산 후 결과값
            while(num != 0) {
                for(int i = 1; i < 6; i++) {
                    num = queue.poll();
                    num -= i;
                    if(num <= 0) {
                        num = 0;
                        queue.offer(num);
                        break;
                    }
                    queue.offer(num);
                }
            }
            for(int i = 0; i < 8; i++) {
                int code = queue.poll();
                sb.append(" ").append(code);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }           // end of main
}               // end of class
