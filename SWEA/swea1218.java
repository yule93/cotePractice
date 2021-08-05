package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

// 괄호 짝짓기: 아니 이거 스택이네
public class swea1218 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = 10;     // 테스트 케이스는 늘 10개가 주어진다.

        Stack<Character> st;
        for(int tc = 1; tc <= T; tc++) {
            st = new Stack<>();
            int gwalNum = Integer.parseInt(br.readLine());
            String pairSentence = br.readLine();
            int answer = 1;

            for(int i = 0; i < gwalNum; i++) {
                if(pairSentence.charAt(i) == '(' || pairSentence.charAt(i) == '{' || pairSentence.charAt(i) == '[' || pairSentence.charAt(i) == '<') {
                    st.push(pairSentence.charAt(i));
                } else {
                    if(st.peek() == '(' && pairSentence.charAt(i) == ')') {
                        st.pop();
                    } else if(st.peek() == '{' && pairSentence.charAt(i) == '}') {
                        st.pop();
                    } else if(st.peek() == '[' && pairSentence.charAt(i) == ']') {
                        st.pop();
                    } else if(st.peek() == '<' && pairSentence.charAt(i) == '>') {
                        st.pop();
                    } else {
                        answer = 0;
                        break;
                    }
                }
                //System.out.println(pairSentence.charAt(i));
            }
            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
