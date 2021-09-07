package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ 14725: 개미굴
public class boj14725 {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        String[] root = new String[N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            root[i] = br.readLine();
        }



        bw.write("answer");
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int node, int k) {}
}
