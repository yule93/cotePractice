package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
// SWEA 3234 준환이의 양팔저울 (순열, 백트래킹?)
public class swea3234 {
    static int[] weight;
    static boolean[] visited;
    static int N;
    static int answer;
    static int sum;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T=Integer.parseInt(br.readLine());
         
        for(int tc = 1; tc <= T;tc++) {
            N = Integer.parseInt(br.readLine());
            weight = new int[N];
            visited = new boolean[N];
            sum=0;
            answer = 0;
             
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
                sum += weight[i];
            }
            permu(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
     
    static void permu(int depth, int left, int right) {
        if(depth==N) {
            answer += 1;
            return;
        }
        if(sum - left <= left) {        // 이러면 뭐가 됐든 left가 항상 커지므로
            // N개의 추를 선택(depth)했고 그 이후에 발생할 수 있는 총 가지수인
            // 2^(N-depth) * (N-depth)!개가 모두 left가 크단 말이므로 싹 다 더해준다.
            answer += fact(N - depth) * Math.pow(2, N - depth);
            return;
        }
         
        for(int i=0 ;i<N; i++) {
            if(!visited[i]) {
                visited[i]=true;
                permu(depth+1, left + weight[i], right);
                visited[i]=false;
                 
                if(left >= right + weight[i]) {
                    visited[i] = true;
                    permu(depth+1, left, right + weight[i]);
                    visited[i] = false;
                }
                     
            }
        }
    }
     
    static int fact(int num) {
        // 팩토리얼 계산 메소드
        int count = 1;
        for(int i=num;i>=1;i--) {
            count *= i;
        }
        return count;
    }
}