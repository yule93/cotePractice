package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 정올 1169 주사위 굴리기 (dfs)
public class jungol1169 {

    static int[] temp;
    static boolean[] visited;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 주사위를 던진 횟수(2~5)
        M = Integer.parseInt(st.nextToken()); // 출력모양 (1~3)

        visited = new boolean[7];
        temp = new int[7];

        switch (M) {
            case 1:
                dice1(1, 0);
                break;
            case 2:
                dice2(1, 0);
                break;
            case 3:
                permu(1, 0);
                break;
            default:
                break;
        }

    }

    public static void dice1(int start, int depth) {
        if (depth == N) {
            for (int i = 1; i <= depth; i++) {
                System.out.print(temp[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <= 6; i++) {
            temp[start] = i;
            dice1(start + 1, depth + 1);
        }
    }

    public static void dice2(int start, int depth) {
        if (depth == N) {
            for (int i = 1; i <= depth; i++) {
                System.out.print(temp[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = temp[start - 1]; i <= 6; i++) {
            if (i > 0) {
                temp[start] = i;
                dice2(start + 1, depth + 1);
            }
        }
    }

    public static void permu(int start, int depth) {
        if (depth == N) {
            for (int i = 1; i <= depth; i++) {
                System.out.print(temp[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <= 6; i++) {
            if (visited[i])
                continue;
            temp[start] = i;
            visited[i] = true;
            permu(start + 1, depth + 1);
            visited[i] = false;
        }
    }
}
