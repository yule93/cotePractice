package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 1233 사칙연산 유효성 검사(트리)
public class swea1233 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder("");
        int T = 10;

		for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());        // 정점의 개수
            int answer = 1;
            String[] tree = new String[N+1];            
            for(int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                tree[Integer.parseInt(st.nextToken())] = st.nextToken();
            }

            int index = N/2;
            for(int i = 1; i <= N; ++i) {
                if(tree[i].charAt(0) >= '0' && tree[i].charAt(0) <= '9') {      // i번째 노드에 저장된 값이 숫자일 때,
                    if(i <= index) {    // i번째 노드가 N/2이라면 숫자끼리는 연산 불가
                        answer = 0;
                        break;
                    }
                } else {            // i번째 노드에 저장된 값이 사칙연산자일 때,
                    if(i > index) { // i번째 노드가 N/2+1이라면 연산자끼리 연산 불가
                        answer = 0;
                    }
                }
            }
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}