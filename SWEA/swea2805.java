package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class swea2805 {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String st;
        int T = Integer.parseInt(br.readLine());            // TC 갯수

        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());        // 농장의 크기
            int answer = 0;
            
            // 수학공식 있으면 좋을 것 같음. 가운데는 무조건 N/2가 들어가게 됨.
            for(int i = 0; i <= N; i++) {
                while(i <= N/2) {       // 각 행에서 수확할 수 있는 작물의 갯수는 i*2 + 1
                    st = br.readLine().substring(N/2 - i, N/2 + i + 1);
                    //System.out.println(i+"번째 문자열: " + st);
                    for(int j = 0; j < st.length(); j++) {
                        answer += st.charAt(j) - '0';
                    }
                    break;
                }
                while(i < N && i > N/2) {          // 각 행에서 수확할 수 있는 작물의 갯수는 (N-i-1)*2 + 1 
                    st = br.readLine().substring((i - N/2), (N-1-i)*2 + 1 + (i - N/2));
                    //System.out.println(i+"번째 문자열: "+ st + ", " + (i - N/2) + ", "+ (i - N/2) + (N-i));
                    for(int j = 0; j < st.length(); j++) {
                        answer += st.charAt(j) - '0';
                    }
                    break;
                }

            }
            sb.append("#").append(tc).append(" " + answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }       // end of main
}           // end of class
