package simplePractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MSTKruskalTest {

    static class Edge implements Comparable<Edge> {
        int start, end, weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            // 음수를 비교하게 될까봥 ㅣ렇게 비교함
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int[] parents;
    
    private static void makeSet() {
        parents = new int[V];
        // 모든 원소의 대표자를 자신으로 만든다.
        for(int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int a) {
        // a가 속한 집합의 대표자 찾기
        if(a == parents[a]) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        // 두 원소를 하나의 집합으로 합치기(대표자 이용)
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot == bRoot) return false;    // 이미 같은 집합

        parents[bRoot] = aRoot;
        return true;
    }

    static int V, E;
    static Edge[] edgeList;
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];     // 간선 리스트 작성

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(start, end, weight);
        }

        Arrays.sort(edgeList);      // 오름차순 정렬
        makeSet();     // 모든 정잠을 각각으로 집합으로 만들고 출발

        // 간선 하나씩 시도하며 트리 만들어 감.
        int cnt = 0, result = 0;
        for(Edge edge : edgeList) {
            if(union(edge.start, edge.end)) {
                result += edge.weight;      // 간선을 사용했으니 가중치 누적.
                if(++cnt == V - 1) break;   // 신장트리 완성
            }
        }

        System.out.println(result);
    }
}
