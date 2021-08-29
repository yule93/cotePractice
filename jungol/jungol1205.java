package jungol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 정올 1205 조커 (단순 구현? 스택?)
public class jungol1205 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine().trim());
        int[] list = new int[N];
        int zokerCount = 0, answer = 1;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
            if(list[i] == 0) zokerCount++;
        }
        Arrays.sort(list);

        int temp = list[zokerCount];
        for(int i = zokerCount + 1; i < N; i++) {
            int count = list[i] - temp;
            // System.out.println(i + ", count: " + count);
            if(count == 1) {
                //System.out.println(i + ", 1번");
                answer++;
            } else if(count - 1 <= zokerCount && count > 1) {
                //System.out.println(i + ", 2번");
                answer += count - 1;
                zokerCount -= count - 1;
            }
            temp = list[i];
        }

        answer += zokerCount;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
