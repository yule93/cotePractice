package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj10833 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine().trim());
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int student = Integer.parseInt(st.nextToken().trim());
            int apple = Integer.parseInt(st.nextToken().trim());

            answer += apple % student;
        }

        System.out.println(answer);
    }
}
