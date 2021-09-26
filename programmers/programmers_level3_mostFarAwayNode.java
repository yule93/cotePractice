package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 프로그래머스 가장 먼 노드 BFS
public class programmers_level3_mostFarAwayNode {
    public static void main(String[] args) {
        System.out.println(
                solution(6, new int[][] { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } }));
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        int edgeLen = edge.length;
        boolean[] visited = new boolean[n + 1];
        int[] count = new int[n + 1]; // 각 노드간 1과의 거리 저장 배열

        // 인접행렬리스트
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < edgeLen; i++) {
            list.add(new ArrayList<Integer>());
        }

        int a, b;
        for (int[] node : edge) {
            a = node[0];
            b = node[1];
            list.get(a).add(b);
            list.get(b).add(a);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[0] = visited[1] = true;
        int now;
        while (q.size() > 0) {
            now = q.poll();
            for (int v : list.get(now)) {
                // 방문하지 않은 곳이라면
                if (!visited[v]) {
                    count[v] = count[now] + 1;
                    visited[v] = true;
                    q.add(v);
                }
            }
        }

        int max = 0;
        for (int cnt : count) {
            if (max < cnt) {
                max = cnt;
                answer = 1;
            } else if (max == cnt)
                answer++;
        }

        return answer;
    }

}
