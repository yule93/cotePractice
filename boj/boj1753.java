package boj;
import java.io.*;
import java.util.*;
 
public class boj1753 {
    static class Edge implements Comparable<Edge> {
        int v, weight;
        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());    // 정점의 개수
        int E = Integer.parseInt(st.nextToken());    // 간선의 개수
        int K = Integer.parseInt(br.readLine()) - 1;    // 시작 정점(번호를 1부터 input)
        final int INF = Integer.MAX_VALUE;      // int 최대값(최소값 구하는거라 최대값 일단 넣어놓고 비교)
        
        // 인접 리스트 준비
        ArrayList<Edge>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) 
            adj[i] = new ArrayList<>();
        
        // 간선 정보 입력
        for (int i = 0; i < E; i++)  {
            st = new StringTokenizer(br.readLine());
            adj[Integer.parseInt(st.nextToken()) - 1].add(new Edge(Integer.parseInt(st.nextToken()) - 1,        // 배열이니까 0부터 시작해서 1 빼주는거
                    Integer.parseInt(st.nextToken())));
        }
    
        // dist 배열
        int[] dist = new int[V];
        boolean[] checked = new boolean[V];
        
        // dist 배열을 INF로 초기화
        Arrays.fill(dist, INF);
        // 시작점은 0으로 변경
        dist[K] = 0;
        
        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.add(new Edge(K, 0));
        Edge cur = null;
        
        while(!pQ.isEmpty()) {
            // check되지 않았으면서,
            // 현재(i)정점으로부터 dist 값이 제일 작은 정점(j)의 번호 찾기
            cur = pQ.poll();
            if(checked[cur.v]) continue;
            
            // 찾은 정점(j)으로부터 갈 수 있는 경로가 이미 알고 있는 dist보다 작다면 갱신
            // index가 가지고 있는 모든 간선을 검사
            for (Edge next : adj[cur.v]) {
                // check되지 않았으면서 다음 노드 까지의 거리가
                // 나까지 거리 + 나로부터 다음 노드까지 거리보다 작다면 갱신 
                if(!checked[next.v] && dist[next.v] > dist[cur.v] + next.weight) {
                    dist[next.v] = dist[cur.v] + next.weight;
                    pQ.add(new Edge(next.v, dist[next.v]));
                }
            }
            
            // 체크 완료
            checked[cur.v] = true;
        }
        
        for (int i = 0; i < V; i++) {
            if(dist[i] == INF) bw.write("INF\n");//System.out.println("INF");
            else bw.write(String.valueOf(dist[i])+"\n");//System.out.println(dist[i]);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}