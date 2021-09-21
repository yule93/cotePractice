package programmers;

public class programmers_level3_network {
    public static void main(String[] args) {
        int n = 3;
        int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };

        System.out.println(solution(n, computers));
    }

    public static int solution(int n, int[][] computers) {
        answer = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(computers, i);
                answer++;
            }
        }

        return answer;
    }

    static boolean[] visited;
    static int answer;

    public static void dfs(int[][] computers, int n) {
        visited[n] = true;

        for (int i = 0; i < computers.length; i++) {
            if (n != i && computers[n][i] == 1 && !visited[i]) {
                dfs(computers, i);
            }
        }
    }
}
