package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 4012: 요리사 (순열조합, DFS)
// 248ms
public class swea4012 {
    static int[][] tasteCombi;
    static boolean[] visited;
    static int N, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            // N은 무조건 짝수개. N/2씩 나눠서 두 개의 요리를 하려고 함.
            // 따라서 음식1에 재료 N/2개, 음식2에 재료 N/2개씩 무조건 들어간다!
            N = Integer.parseInt(br.readLine());
            answer = Integer.MAX_VALUE;     // 최소값 저장해야해서 최대값을 일단 넣어둠
            tasteCombi = new int[N][N];
            visited = new boolean[N];       // 몇 번 재료를 사용하고 있는지 저장하기 위해서
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    tasteCombi[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            permutation(0, 0);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }       // end of test case

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void permutation(int depth, int start) {
        if(depth == N/2) {
            int food1 = 0, food2 = 0, result = 0;
            for(int i = 0; i < N - 1; i++) {
                for(int j = i; j < N; j++) {
                    // 만약 n번째 재료와 m번째 재료를 사용한다면
                    // taste[n][m]과 taste[m][n]을 더해야 요리의 맛이 나온당
                    if(visited[i] && visited[j]) food1 += tasteCombi[i][j] + tasteCombi[j][i];  // N/2 재료
                    else if(!visited[i] && ! visited[j]) food2 += tasteCombi[i][j] + tasteCombi[j][i];  // N/2 재료
                }
            }
            result = Math.abs(food1 - food2);
            answer = Math.min(answer, result);  // 두 요리 맛 차이를 최소로 만들어야 함
            return;
        }

        for(int i = start; i < N; i++) {
            visited[i] = true;      // i번째 재료 선택
            permutation(depth + 1, i + 1);
            visited[i] = false;
        }
    }
}
