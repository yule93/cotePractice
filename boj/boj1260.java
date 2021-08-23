package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1260 {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       // 정점의 개수
        int M = Integer.parseInt(st.nextToken());       // 간선의 개수
        int V = Integer.parseInt(st.nextToken());       // 시작점의 위치

        boolean[] visited = new boolean[N + 1];
        int[][] arr = new int[N + 1][N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = arr[to][from] = 1;
        }

        dfs(V, visited, arr);
        visited = new boolean[N + 1];
        System.out.println();

        arr = new int[N + 1][N + 1];
        bw.write(bfs(V, visited, arr));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int start, boolean[] visited, int[][] arr) {
        if(visited[start]) return;
        visited[start] = true;
        System.out.print(start + " ");
        for(int i = 1; i <= N; i++) {
            if(arr[start][i] == 1) dfs(i, visited, arr);
        }
    }

    public static String bfs(int start, boolean[] visited, int[][] arr) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            int temp = q.poll();
            visited[temp] = true;
            sb.append(temp).append(" ");

            for(int i = 1; i <= N; i++) {
                if(arr[temp][i] == 1 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
