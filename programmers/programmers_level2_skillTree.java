package programmers;

public class programmers_level2_skillTree {
  public static void main(String[] args) {
    System.out.println(solution("CBD", new String[] { "BACDE", "CBADF", "AECB", "BDA" }));
  }

  public static int solution(String skill, String[] skill_trees) {
    int answer = 0;
    for (String skillT : skill_trees) {
      String now = skillT;
      for (int i = 0; i < skillT.length(); i++) {
        String s = skillT.substring(i, i + 1);
        if (!skill.contains(s)) {
          // 스킬트리에 없는 거면 걍 빼고
          now = now.replace(s, "");
        }
      }

      if (skill.indexOf(now) == 0) {
        // 순차적으로 스킬(뒤에건 빼먹었더라도)을 찍었다면 답임
        answer++;
      }
    }

    return answer;
  }
}
