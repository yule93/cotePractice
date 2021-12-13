package programmers;

public class programmers_level2_takeGroupPhoto {
  public static void main(String[] args) {
    System.out.println(solution(2, new String[] { "N~F=0", "R~T>2" }));
  }

  private static int answer;
  private static String[] friends = { "A", "C", "F", "J", "M", "N", "R", "T" };

  public static int solution(int n, String[] data) {
    boolean[] isVisited = new boolean[8];
    answer = 0;
    dfs("", isVisited, data);
    System.out.println(answer);
    return answer;
  }

  private static void dfs(String names, boolean[] isVisited, String[] datas) {
    if (names.length() == 7) {
      for (String data : datas) {
        int f1 = names.indexOf(data.charAt(0));
        int f2 = names.indexOf(data.charAt(2));
        char op = data.charAt(3);
        int gap = data.charAt(4) - '0'; // 두 친구간 간격

        if (op == '=') {
          if (!(Math.abs(f1 - f2) == gap + 1))
            return;
        } else if (op == '>') {
          if (!(Math.abs(f1 - f2) > gap + 1))
            return;
        } else if (op == '<') {
          if (!(Math.abs(f1 - f2) < gap + 1))
            return;
        }
      }
      answer++;
    }
    for (int i = 0; i < 8; i++) { // 조합
      if (!isVisited[i]) {
        isVisited[i] = true;
        String name = names + friends[i];
        dfs(name, isVisited, datas);
        isVisited[i] = false;
      }
    }
  }

  private static boolean check(String names, String[] datas) {
    for (String data : datas) {
      int position1 = names.indexOf(data.charAt(0)); // 프렌즈 포지션1
      int position2 = names.indexOf(data.charAt(2)); // 프렌즈 포지션2
      char op = data.charAt(3); // 수식
      int index = data.charAt(4) - '0'; // 간격
      if (op == '=') {
        if (!(Math.abs(position1 - position2) == index + 1))
          return false; // 둘 포지션 차이를 구하기 위해선 index+1 을 해야함에 주의
      } else if (op == '>') {
        if (!(Math.abs(position1 - position2) > index + 1))
          return false;
      } else if (op == '<') {
        if (!(Math.abs(position1 - position2) < index + 1))
          return false;
      }
    }
    return true;
  }
}
