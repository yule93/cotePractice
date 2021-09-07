import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * N이라는 숫자가 주어졌을 때, N번째 소수를 답으로 내기
 */
public class PrimeNumber_송민주 {
    static boolean[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int num = 1000;
            int answer = 0;
            arr = new boolean[num];
        
            arr[0] = true;
            for(int i = 2; i < num; i++) {
                // 소수는 2부터 시작한다.
                for(int j = i * i; j < num; j += i) {
                    arr[j] = true;
                }
            }

            int count = 0;
            for(int i = 2; i < num; i++) {
                if(!arr[i]) count++;
                if(count == N) {
                    answer = i;
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
