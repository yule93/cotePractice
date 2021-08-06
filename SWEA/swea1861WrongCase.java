package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


// 왜 틀린건지 감이 안 잡혀서 깜깜... 다시 살펴볼 예정
public class swea1861WrongCase {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] DP;
    
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());        // 방의 크기
            DP = new int[N*N];                        // 0~N까지 숫자가 붙은 방에서 출발했을 때 갈 수 있는 방의 갯수를 저장하는 배열. 이거 Knapsack도 가능할듯...?
            int[][] map = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }       // end of initialize of map
            
            int deadLine = N*N;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    int ny = i;
                    int nx = j;
                    while(deadLine-- > 0) {
                        ex:
                        for(int k = 0; k < 4; k++) {
                            if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                            int count = 1;
                            int beforeMap = map[ny][nx];
                            ny += dy[k];
                            nx += dx[k];
                            if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                            if(map[ny][nx] - 1 == beforeMap ) {
                                count++;
                                break ex;
                            } else {
                                DP[map[i][j]] = count;
                                continue;
                            }
                        }
                        
                    }
                }
            }

            int answer = 0, num = 1000001;
            for(int i = N*N - 1; i >= 0; i--) {
                if(DP[i] >= answer) {
                    answer = DP[i];
                    num = num > i ? i : num;
                }
            }
            sb.append("#").append(tc).append(" ").append(num).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
