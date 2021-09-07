package simplePractice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 * [입력 예시]
    2
    3 3
    2 4
 */
public class testIM1 {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // double minimumH = Math.min(x, y) / 2 - 1;
            double answer = 0;

            // 근의 공식
            double[] h = new double[2];
            h[0] = (x + y + Math.sqrt(Math.pow(x, 2) - x * y + Math.pow(y, 2))) / 6.0;
            h[1] = (x + y - Math.sqrt(Math.pow(x, 2) - x * y + Math.pow(y, 2))) / 6.0;

            for(int i = 0; i < 2; i++) {
                double A = x - 2 * h[i];
                double B = y - 2 * h[i];
                if(A <= 0 || B <= 0) continue;
                else {
                    answer = Math.max(answer, A * B * h[i]);
                    answer = Math.round(answer * 1000000) / 1000000.0;
                }
            }
            
            DecimalFormat df = new DecimalFormat("0.000000");
            sb.append("#").append(test_case).append(" ").append(df.format(answer)).append("\n");
		}

        bw.write(sb.toString());
        bw.flush();
		bw.close();
        br.close();
    }
}
