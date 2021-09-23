package programmers;

public class programmers_level3_esscenseTriangle {
    public static int solution(int[][] triangle) {
        int len = triangle.length;
        int[][] dp = new int[len][len];
        dp[0][0] = triangle[0][0];
        // 맨 앞이랑 맨 끝은 언제나 자신과 같은 열, 행-1번째에 있는 수를 더해야 하므로 해당 처리를 미리 해준다.
        for (int i = 1; i < triangle.length; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }

        for (int i = 2; i < triangle.length; i++)
            for (int j = 1; j < i; j++)
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];

        int answer = 0;
        for (int i = 0; i < dp.length; i++)
            answer = Math.max(answer, dp[dp.length - 1][i]);

        return answer;
    }

    // 굳이 함수 안 만들어도 될 거 같아서 solution 안으로 넣음
    public static void DP(int[][] triangle, int[][] dp, int depth, int col, int len) {
        if (depth == len) {
            return;
        }

        // for (int i = col; i <= col; i++) {
        // dp[depth+1][col] = Math.max(dp[depth][i] + triangle[depth][i], dp[depth][i]);
        // DP(triangle, dp, depth + 1, i, len);
        // DP(triangle, dp, depth + 1, i + 1, len);
        // }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][] { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } }));
    }
}
