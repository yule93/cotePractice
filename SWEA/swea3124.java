package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 3124 최소 스패닝 트리 (BFS, Disjoint set)
public class swea3124 {

    static int V, E;
    static int[] parents;
    static boolean[] visited; // 각 정점 방문 여부

    private static void makeSet() {
        // 모든 원소의 대표자를 자신으로 만든다.
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int a) {
        // a가 속한 집합의 대표자 찾기
        if (a == parents[a])
            return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        // 두 원소를 하나의 집합으로 합치기(대표자 이용)
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot)
            return false; // 이미 같은 집합

        parents[bRoot] = aRoot;
        return true;
    }

    private static class Edge {
        private int start;
        private int end;
        private int weight;

        private Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long answer = 0;
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 간선 수
            E = Integer.parseInt(st.nextToken()); // 정점 수
            parents = new int[V + 1];

            PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new Comparator<Edge>() {
                public int compare(Edge o1, Edge o2) {
                    return o1.weight > o2.weight ? 1 : -1;
                }
            });

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()); // 시작점
                int end = Integer.parseInt(st.nextToken()); // 끝점
                int weight = Integer.parseInt(st.nextToken()); // 가중치
                pq.add(new Edge(start, end, weight));
            }

            makeSet();

            ArrayList<Edge> list = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                Edge ed = pq.poll();
                if (findSet(ed.start) == findSet(ed.end))
                    continue;
                union(ed.start, ed.end);
                list.add(ed);
            }

            for (int i = 0; i < list.size(); i++) {
                answer += list.get(i).weight;
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb.toString());
    }
}
