package boj;
import java.util.*;
import java.io.*;

// boj 1018번: 체스판 다시 칠하기
public class boj1018 {
    static String[][] originBoard;

    static void makeBoard(int col, int row) {
        originBoard = new String[2][row];
        for(int i = 0; i < row; i++) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for(int j = 1; j <= col/2; j++) {
                sb1.append("BW");
                sb2.append("WB");
            }
            originBoard[0][i] = sb1.toString();
            originBoard[1][i] = sb2.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        String[] chessBoard = new String[row];

        makeBoard(col, row);

        for(int i = 0; i < col; i++) {
            chessBoard[i] = br.readLine();
        }

        int cnt1 = 0;
        int cnt2 = 0;

        // System.out.println("\n"+originBoard[0][1]+"\n");
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(originBoard[0][i].charAt(j) != chessBoard[i].charAt(j)) cnt1++; //System.out.println("1번째 보드: " + i + ", "+ j);
                if(originBoard[1][i].charAt(j) != chessBoard[i].charAt(j)) cnt2++; //System.out.println("2번째 보드: " + i + ", "+ j);
            }
        }

        bw.write(String.valueOf(Math.min(cnt1, cnt2)));
        bw.flush();
        bw.close();
        br.close();
    }
}
