package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

// BOJ 1038: 감소하는 수 (브루트포스)
public class boj1038 {
    static ArrayList<Integer> arr;
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());        // 몇 번째로 감소하는 수인지 받는 변수
        arr = new ArrayList<>();

        if(N <= 10) {
            bw.write(String.valueOf(N));
        } else if(N > 1022) {
            bw.write(String.valueOf(-1));
        } else {
            for(int i = 0; i < 10; i++) {       // 측정할 수 있는 숫자의 최대 크기는 0~9중 하나씩 뽑아 만들기 때문에 10이다.
                dfs(i, 1);
            }

            Collections.sort(arr);
            bw.write(String.valueOf(arr.get(N)));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int num, int depth) {
        if(depth > 10) return;
        arr.add(num);

        for(int i = 0; i < 10; i++) {
            if(num % 10 > i) dfs( (num * 10)+i, depth + 1 );
        }
        return;
    }
}
