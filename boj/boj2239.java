package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// boj 2239 스도쿠(백트래킹, 시뮬레이션)
// 모두 탐색해보고 가지치기 하는거라 dfs
public class boj2239 {

    static int[][] board;
    static boolean flag;
    static ArrayList<int[]> zero;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        zero = new ArrayList<>();
        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = str.charAt(j) - '0';
                if (board[i][j] == 0)
                    zero.add(new int[] { i, j });
            }
        }

        flag = false;
        dfs(0);
    }

    public static void dfs(int index) {
        if (index == zero.size() && !flag) {
            flag = true;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());
            return;
        }

        // 사전식으로 앞서는 것만 출력하기 위해서, 이미 출력했으면 아무리 풀 수 있는 스도쿠더라도 출력 X
        if (flag)
            return;

        int[] zeroPos = zero.get(index);
        for (int j = 1; j <= 9; j++) {
            if (board[zeroPos[0]][zeroPos[1]] == 0 && check(zeroPos[0], zeroPos[1], j)) {
                board[zeroPos[0]][zeroPos[1]] = j;
                dfs(index + 1);
                board[zeroPos[0]][zeroPos[1]] = 0;
            }
        }
    }

    public static boolean check(int y, int x, int num) {
        // 스도쿠 조건을 만족하는지 확인하는 함수
        for (int i = 0; i < 9; i++) {
            // 순서대로 행, 열, 사각형 체크
            if (board[y][i] == num)
                return false;
            if (board[i][x] == num)
                return false;
            if (board[(y / 3) * 3 + i / 3][(x / 3) * 3 + i % 3] == num)
                return false;
        }

        return true;
    }
}
