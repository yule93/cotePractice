package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// BOJ 2448 별 찍기 - 11 (분할정복?)
public class boj2448 {
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] printStar = new String[N];

        printStar[0] = "  *  ";
        printStar[1] = " * * ";
        printStar[2] = "*****";

        for(int k = 1; 3 * (int)Math.pow(2, k) <= N; ++k) {
            int bottom = 3 * (int)Math.pow(2, k);       // 맨 밑(별이 완전 꽉 찬 거)
            int middle = bottom / 2;
            for (int i = middle; i < bottom; ++i) {
                printStar[i] = printStar[i - middle] + " " + printStar[i -middle];
            }
            String space = "";
            while (space.length() < middle) {
                space += " ";
            }
            for (int i = 0; i < middle; ++i) {
                printStar[i] = space + printStar[i] + space;
            }
        }
        
        for(int i = 0; i < N; i++) sb.append(printStar[i]).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
