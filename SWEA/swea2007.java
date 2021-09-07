package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// SWEA 2007 마디 패턴 (문자열)
// 100ms
public class swea2007 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int answer = 0;
            String words = br.readLine();

            for(int i = 1; i <= 30; i++) {
                String a = words.substring(0, i);
                String b = words.substring(i, i+i);
                if(a.equals(b)) {
                    answer = a.length();
                    break;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
