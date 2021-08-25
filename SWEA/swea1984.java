package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea1984 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int[] arr = new int[10];
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < 10; i++) arr[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);

            double sum = 0;
            for(int i = 1; i < 9; i++) {
                sum += arr[i];
            }
            sum /= (double)8;
            int result = (int)Math.round(sum);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
