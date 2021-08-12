package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ 2961 도영이가 만든 맛있는 음식 (DFS, 비트마스크)
public class boj2961 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());        // 재료의 개수
        int[][] taste = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            taste[i][0] = Integer.parseInt(st.nextToken());     // 신맛
            taste[i][1] = Integer.parseInt(st.nextToken());     // 쓴맛
        }

        int answer = 1000000000;
        for(int i = 1; i < 1<<N; i++) {
            int sour = 1, bitter = 0;
            for(int j = 0; j <N; j++) {
                if((i & 1<<j) > 0) {
                    sour *= taste[j][0];
                    bitter += taste[j][1];
                }
            }
            //System.out.println(sour + ", " + bitter);
            answer = Math.min(answer, Math.abs(sour - bitter));
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
