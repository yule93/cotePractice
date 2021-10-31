package programmers;

public class programmers_level2_targetNumber {
    public static void main(String[] args) {
        numbers = new int[] { 1, 1, 1, 1, 1 };
        int target = 3;
        oper = new boolean[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            oper[i] = false;
        }

        System.out.println(solution(numbers, target));
    }

    public static int solution(int[] numbers, int target) {
        answer = 0;
        dfs(0, numbers.length, target, 0);

        return answer;
    }

    static boolean[] oper;
    static int[] numbers;
    static int answer;
    static String str;

    public static void dfs(int depth, int len, int target, int start) {
        if (depth == len) {
            int res = 0;
            str = "";
            for (int i = 0; i < depth; i++) {
                str = str + (oper[i] == true ? "+" : "-") + numbers[i];
                if (oper[i] == true)
                    res += numbers[i];
                else if (oper[i] == false)
                    res -= numbers[i];
            }
            System.out.println(str);
            if (res == target) {
                answer++;
            }
            return;
        }

        for (int i = start; i < len; i++) {
            oper[depth] = true;
            dfs(depth + 1, len, target, i + 1);
            oper[depth] = false;
            dfs(depth + 1, len, target, i + 1);
        }
    }
}

/**
 * 완전 좋은 풀이(참고용) class Solution { public int solution(int[] numbers, int target)
 * { int answer = 0; answer = dfs(numbers, 0, 0, target); return answer; } int
 * dfs(int[] numbers, int n, int sum, int target) { if(n == numbers.length) {
 * if(sum == target) { return 1; } return 0; } return dfs(numbers, n + 1, sum +
 * numbers[n], target) + dfs(numbers, n + 1, sum - numbers[n], target); } }
 * 
 */