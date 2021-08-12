package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// BOJ 3040번 백설공주와 일곱 난쟁이 (dfs, 비트마스크)
public class boj3040 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder("");

        int N = 9;      // 항상 아홉명의 난쟁이가 광산에서 돌아오기 때문
        int[] people = new int[N];      // 난쟁이의 모자에 쓰인 숫자를 저장하는 배열
        for(int small = 0; small < N; small++) {
            people[small] = Integer.parseInt(br.readLine());
        }

        // 어차피 9명 중 7명이라는 숫자는 무조건 뽑아야 하니까 2명만 걸러내면 됨.
        int answer = 0;
        for(int i = 1; i < 1 << N; i++) {
            if(bitCount(i) == 7) {
                int sum = 0;
                for(int j = 0; j < N; j++) {
                    if((i & 1<<j) > 0) {
                        //System.out.println(j+"번째 난쟁이");
                        sum += people[j];
                        if(sum == 100) {
                            answer = i;
                            break;
                        }
                    }
                }
                if(sum == 100) break;
            }
        }

        for(int i = 0; i < N; i++) {
            if((1 << i & answer) > 0) sb.append(people[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int bitCount(int input) {
        int count = 0;
        int mask = 1 << 31;
        while (true){
            if (mask == 0) break;
            if((mask & input) != 0) count++;
            mask = mask >>> 1;
        }
        return count;
    }
}
