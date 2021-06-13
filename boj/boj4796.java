package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// BOJ 4796: 캠핑, 그리디 알고리즘
public class boj4796 {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        int count = 0;
        
        while(true) {
            int L, P, V;
            L = sc.nextInt();
            P = sc.nextInt();
            V = sc.nextInt();
            if(L == 0) break;
            int answer = (V / P * L) + (V % P < L ? V % P : L);
            bw.write(String.valueOf("Case " + ++count + ": " + answer + "\n"));
        }
        
        bw.flush();
        bw.close();
        sc.close();
    }
}
