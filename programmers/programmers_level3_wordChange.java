package programmers;

// 프로그래머스 단어변환(DFS)
public class programmers_level3_wordChange {

    public static int solution(String begin, String target, String[] words) {
        answer = 0;
        boolean check = false;
        for (String str : words) {
            if (str.equals(target))
                check = true;
        }
        if (!check)
            return 0;

        visited = new boolean[words.length];
        dfs(begin, target, words, 0);
        return answer;
    }

    static int answer;
    static boolean[] visited;

    public static void dfs(String begin, String target, String[] words, int cnt) {
        if (begin.equals(target)) {
            answer = cnt;
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (visited[i]) {
                continue;
            }

            int k = 0; // 같은 스펠링 몇개인지 세기
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) == words[i].charAt(j)) {
                    k++;
                }
            }

            if (k == begin.length() - 1) { // 한글자 빼고 모두 같은 경우
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }
    }
}
