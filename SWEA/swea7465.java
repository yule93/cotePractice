package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// SWEA 7465 창용 마을 무리의 개수 (Graph, 서로소집합)
public class swea7465 {

    static int N, M;        // N은 정점의 수, M은 간선의 수
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int answer = 0;     // 마을 내 몇 개의 무리가 존재하는
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parents = new int[N];
            makeSet();
            
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a - 1, b - 1);
            }

            for(int i = 0; i < N; i++) {
                if(parents[i] == i) answer++;
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void makeSet() {
        // 모든 원소의 대표자를 자신으로 만든다.
        for(int i = 0; i < N; i++) {
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
}
