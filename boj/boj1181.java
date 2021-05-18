package boj;
import java.util.*;
import java.io.*;

// 1181번: 단어정렬
public class boj1181 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] arrs = new String[N];

        for(int i = 0; i < N; i++) {
            arrs[i] = br.readLine();
        }

        Arrays.sort(arrs, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if(str1.length() == str2.length()) {
                    return str1.compareTo(str2);
                }
                return str1.length() - str2.length();       // 길이순으로 반환하기
            }
        });

        for(int i = 0; i < N; i++) {
            if(i > 0) {
                if(arrs[i-1].equals(arrs[i])) continue;
                bw.write(arrs[i] + "\n");
            } else bw.write(arrs[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
