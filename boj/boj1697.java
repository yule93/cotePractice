package boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1697번 숨바꼭질: 그래프 탐색, BFS
public class boj1697 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());       // 수빈이 위치
        int K = Integer.parseInt(st.nextToken());       // 동생 위치
        int count = 0;                                  // 가장 빠른 시간
        int[] visited = new int[100005];
        Arrays.fill(visited, -1);
        
        bw.write(String.valueOf(bfs(N, K, visited)));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int bfs(int N, int K, int[] visited) {
        int nextP = N;
        int[] status = new int[3];
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(nextP);
        visited[nextP] = 0;

        while(!q.isEmpty() && nextP != K) {     // 다음 좌표가 동생의 좌표랑 같으면 관둠(따라잡은거니까!)
            nextP = q.poll();
            status[0] = nextP - 1;      // 뒤로 1 이동
            status[1] = nextP + 1;      // 앞으로 1 이동
            status[2] = nextP * 2;      // 순간이동

            for(int i = 0; i < 3; i++) {
                if(status[i] >= 0 && status[i] <= 100000) {     // 최대 최소 좌표 범위를 넘었는지 안 넘었는지 체크
                    if(visited[status[i]] == -1) {       // 방문하지 않은 좌표면
                        q.add(status[i]);
                        visited[status[i]] = visited[nextP] + 1;
                    }
                }
            }
        }
        return visited[K];
    }
}
