package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 정올 1515 구슬찾기 (플로이드 와샬)
public class jungol1515 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 구슬의 개수 (1~99)
        int M = Integer.parseInt(st.nextToken()); // 저울에 올려 본 쌍의 개수

        int answer = 0; // 중간이 될 수 없는 구슬의 개수
        int MAX = 1_000_000;

        int[][] compare = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                compare[i][j] = MAX;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            compare[front - 1][end - 1] = 1; // 더 무거움
        }

        // 플로이드 와샬 시작!
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j || j == k)
                        continue;
                    if (compare[i][j] > compare[i][k] + compare[k][j])
                        compare[i][j] = compare[i][k] + compare[k][j];
                }
            }
        }

        int mid = N / 2 + (N % 2);

        for (int i = 0; i < N; i++) {
            int heavy = 0;
            int light = 0;
            for (int j = 0; j < N; j++) {
                if (compare[i][j] == 1 && i != j)
                    // 자기보다 가벼운 게 있을 때,
                    light++;
            }
            for (int j = 0; j < N; j++) {
                if (compare[j][i] == 1 && i != j)
                    // 자기보다 무거운 게 있을 때,
                    heavy++;
            }
            if (heavy >= mid || light >= mid)
                answer++;
        }

        // for (int i = 0; i < N; i++) {
        // for (int j = 0; j < N; j++) {
        // System.out.print(compare[i][j] + " ");
        // }
        // System.out.println();
        // }
        System.out.println(answer);
    }
}
