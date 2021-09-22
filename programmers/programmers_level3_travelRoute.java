package programmers;

import java.util.ArrayList;
import java.util.Collections;

// 프로그래머스 여행경로(DFS)
// 진행 방법
// dfs로 시작할 수 있는 티켓(ICN)부터 시작해서 경유지를 거쳐 티켓을 다 소모할 수 있는 방법을 구함
public class programmers_level3_travelRoute {
    public static void main(String[] args) {
        String[][] tickets = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };

        for (String ex : solution(tickets)) {
            System.out.println(ex);
        }
    }

    public static String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        answers = new ArrayList<String>();

        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(answers); // 답들 중 가장 알파벳순이 빠른 배열들로 정렬

        String[] answer = answers.get(0).split(" "); // 가장 빠른 배열을 뽑아서 String형 배열로 만듬
        return answer;
    }

    static boolean[] visited;
    static ArrayList<String> answers;

    public static void dfs(int depth, String start, String answer, String[][] ticktes) {
        // 티켓을 전부 사용했을 때
        if (depth == ticktes.length) {
            answers.add(answer); // answers에 추가
            return;
        }
        for (int i = 0; i < ticktes.length; i++) {
            // present와 같고 들리지 않은 공항을 찾음
            if (!visited[i] && ticktes[i][0].equals(start)) {
                visited[i] = true; // 공항 방문함!
                dfs(depth + 1, ticktes[i][1], answer + " " + ticktes[i][1], ticktes);
                visited[i] = false;
            }
        }
        return;
    }
}
