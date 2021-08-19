package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1244 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());        // 100 이하의 양의 정수
        int[] switchMap = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            switchMap[i] = Integer.parseInt(st.nextToken());
        }

        int student = Integer.parseInt(br.readLine());      // 학생 수

        for(int i = 0; i < student; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int FM = Integer.parseInt(st.nextToken());      // 1. 남자 2. 여자
            int num = Integer.parseInt(st.nextToken());     // 몇 번째 스위치를 기준으로 잡을건지

            if(FM == 1) {
                for(int j = num-1; j < N; j += num) {
                    switchMap[j] = switchMap[j] == 0 ? 1 : 0;
                }
            } else {
                switchMap[num - 1] = switchMap[num - 1] == 0 ? 1 : 0;
                for(int j = 1; j < N/2; j++) {
                    if(num - 1 + j >= N || num - 1 - j < 0) break;
                    if(switchMap[num + j - 1] == switchMap[num - j - 1]) {
                        switchMap[num + j - 1] = switchMap[num + j - 1] == 0 ? 1 : 0;
                        switchMap[num - j - 1] = switchMap[num - j - 1] == 0 ? 1 : 0;
                    } else break;
                }
            }
        }

        for(int i = 0; i < N; i++) {
            sb.append(switchMap[i]).append(" ");
            if((i+1) % 20 == 0) sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
