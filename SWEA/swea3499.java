package SWEA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea3499 {
    static String[] firstDeck;
    static String[] lastDeck;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc);
            int N = Integer.parseInt(br.readLine());        // 카드의 갯수
            st = new StringTokenizer(br.readLine());
            if(N % 2 != 0) {
                firstDeck = new String[(N/2)+1];
                lastDeck = new String[N/2];
                for(int i = 0; i <= N/2; i++) firstDeck[i] = st.nextToken();
                for(int i = 0; i < N/2; i++) lastDeck[i] = st.nextToken();
            } else {
                firstDeck = new String[N/2];
                lastDeck = new String[N/2];
                for(int i = 0; i < N/2; i++) firstDeck[i] = st.nextToken();
                for(int i = 0; i < N/2; i++) lastDeck[i] = st.nextToken();
            }
            
            for(int i = 0; i < N/2; i++) {
                sb.append(" ").append(firstDeck[i]).append(" ").append(lastDeck[i]);
            }
            if(N % 2 != 0) sb.append(" ").append(firstDeck[firstDeck.length - 1]);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
