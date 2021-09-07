package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 1238 Contact (BFS, Graph)
public class swea1238 {
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = 10;

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int graphLen = Integer.parseInt(st.nextToken());        // 입력받는 데이터의 길이
            int startP = Integer.parseInt(st.nextToken());          // 시작점
            
            int[][] graph = new int[graphLen+1][graphLen+1];
            boolean[] visited = new boolean[graphLen+1];
            Queue<Node> q = new LinkedList<>();
            ArrayList<Node> list = new ArrayList<>();

            int max = 1;

            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from][to] = 1;
            }

            q.add(new Node(1, startP));
            list.add(new Node(1, startP));
            visited[startP] = true;

            while(!q.isEmpty()) {
                Node node = q.poll();
                int v = node.v;
                int width = node.width;

                for(int i = 0; i < graphLen; i++) {
                    if(graph[v][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        list.add(new Node(width + 1, i));
                        q.add(new Node(width + 1, i));
                    }
                }
                max = Math.max(max, width);
            }

            int answer = 1;
            for(int i = 0; i < list.size(); i++) {
                if(list.get(i).width == max) answer = Math.max(answer, list.get(i).v);
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Node {
        int width;  // 너비
        int v;      // 정점
        Node(int width, int v) {
            this.width = width;
            this.v = v;
        }
    }
}
