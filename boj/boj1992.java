package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// BOJ 1992 쿼드 트리 (딱 봐도 분할정복)
public class boj1992 {
    static int[][] video;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        video = new int[N][N];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                video[i][j] = str.charAt(j) - '0';
            }
        }       // 영상이 어떻게 생겼는지 초기화

        divide(0, 0, N);
        sb.append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void divide(int y, int x, int N) {
        // 시작 좌표의 행과 열, 그리고 크기 주어짐. N은 >>=1 될 예정
        if(isEqualNumber(y, x, N)) sb.append(video[y][x]);
        else {
            sb.append("(");
            int divideN = N >> 1;
            // int moveX = x + divideN;
            // int moveY = y + divideN;
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    // 깔끔하게 4사분면 한 바퀴 도는 걸로 최적화
                    divide(y + divideN * i, x + divideN * j, divideN);
                }
            }
            // divide(y, x, divideN);          // 2사분면
            // divide(y, moveX, divideN);      // 1사분면
            // divide(moveY, x, divideN);      // 3사분면
            // divide(moveY, moveX, divideN);  // 4사분면
            sb.append(")");
        }
    }

    public static boolean isEqualNumber(int y, int x, int N) {
        int value = video[y][x];
        for (int i = y; i < y + N; i++) {
			for (int j = x; j < x + N; j++) {
				if (video[i][j] != value) return false;
			}
		}
        return true;
    }
}
