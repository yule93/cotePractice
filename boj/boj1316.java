package boj;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


// BOJ 1316: 그룹 단어 체커
public class boj1316 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for(int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int count = 0;
        StringBuilder sb;

        for(int i = 0; i < N; i++) {
            sb = new StringBuilder("");
            boolean flag = true;
            count++;
            for(int j = 0; j < words[i].length(); j++) {
                if(!sb.toString().contains(words[i].substring(j, j+1))) {
                    sb.append(words[i].substring(j, j+1));
                } else if(!sb.toString().substring(sb.length()-1, sb.length()).equals(words[i].substring(j, j+1))) {
                    flag = false;
                }
            }

            if(!flag) count--;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
