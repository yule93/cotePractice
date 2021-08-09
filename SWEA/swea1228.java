package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class swea1228 {
    public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		//Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder("");
        
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case);
            LinkedList<Integer> list = new LinkedList<>();
            int N = Integer.parseInt(br.readLine());		// 암호문의 길이

			String password = br.readLine();				// 원본 암호문
            for(String ex : password.split(" ")) {
                list.add(Integer.parseInt(ex));
            }

            int commandNum = Integer.parseInt(br.readLine());       // 커맨드 숫자
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < commandNum; i++) {
            	String command = st.nextToken();
                int pos = Integer.parseInt(st.nextToken());
                int numberNum = Integer.parseInt(st.nextToken());
                for(int j = 0; j < numberNum; j++) {
                    list.add(pos, Integer.parseInt(st.nextToken()));
                    pos++;
                }
            }
            for(int i = 0; i < 10; i++) {
                sb.append(" ").append(list.get(i));
            }
            sb.append("\n");
		}
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}
}
