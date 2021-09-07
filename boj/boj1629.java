package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1629 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // A를 B번 곱한 수 알아내기... 를 하려고 했으나 너무 커지므로 C로 나눈 나머지를 구하는 프로그램을 작성하기
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        // A가 C보다 클 수 있으므로 나머지 값을 처음부터 넣어준다. 그래서 B가 1이 됐을 때 A%C가 리턴되도록 함.
        bw.write(String.valueOf(divide(A % C, B, C)));
        bw.flush();
        bw.close();
        br.close();
    }

    public static long divide(long A, long B, long C) {
        if(B == 1) return A;        // B가 1이면 A의 1승이랑 똑같으므로 걍 A리턴
        long temp = divide(A, B >> 1, C) % C;
        if(B % 2 == 0) return (temp * temp) % C;
        else return (((temp * temp) % C) * A ) % C;
    }
}
