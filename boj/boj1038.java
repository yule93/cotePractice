package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ 1038: 감소하는 수 (dfs, 백트래킹)
// 140ms
public class boj1038 {
    static ArrayList<Long> arr;
    public static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 몇 번째로 감소하는 수인지 받는 변수
        arr = new ArrayList<Long>();
        if (N <= 10) {
            System.out.println(N);
            return;
        } else if (N > 1022) {
            System.out.println(-1); // int 내 숫자로 표현 불가
            return;
        } else {
            for (int i = 0; i < 10; i++) {
                dfs(i, 1);
            }
        }
        Collections.sort(arr);
        System.out.println(arr.get(N));
    }

    static void dfs(long num, int depth) {
        if (depth > 10) {
            return;
        }

        arr.add(num);
        for (int i = 0; i < num % 10; i++) {
            dfs((num * 10) + i, depth + 1);
        }
    }
}
