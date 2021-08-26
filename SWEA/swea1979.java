package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 1979 어디에 어떤 단어가 들어갈 수 있을까 (단순 탐색)
public class swea1979 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int answer = 0;
            
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());       // 가로세로길이
            int K = Integer.parseInt(st.nextToken());       // 단어의 길이
            int[][] map = new int[N][N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 가로
            for(int i = 0; i < N; i++) {
                int count = 0;
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 1) {
                        count++;
                        if(j == N-1 && count == K) {
                            answer++;
                            //System.out.println("행: " + i + ", 열: " + j);
                        }
                    }
                    else {      // 검게 칠해진 부분을 만났을 때,
                        if(count == K) {
                            answer++;
                            count = 0;
                            //System.out.println("행: " + i + ", 열: " + j);
                        }
                    }
                }
            }

            // 세로
            for(int j = 0; j < N; j++) {
                int count = 0;
                for(int i = 0; i < N; i++) {
                    if(map[i][j] == 1) {
                        count++;
                        if(i == N-1 && count == K) {
                            answer++;
                            // System.out.println("행: " + i + ", 열: " + j);
                        }
                    }
                    else {      // 검게 칠해진 부분을 만났을 때,
                        if(count == K) {
                            answer++;
                            // System.out.println("행: " + i + ", 열: " + j);
                        }
                        count = 0;
                    }
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
