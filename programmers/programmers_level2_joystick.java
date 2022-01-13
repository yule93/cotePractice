package programmers;

public class programmers_level2_joystick {

  public static void main(String[] args) {
    System.out.println(solution("JAMES"));
  }

  public static int solution(String name) {
    int answer = 0;

    char[] names = name.toCharArray();
    int minMove = name.length() - 1;

    for (int i = 0; i < name.length(); i++) {
      if (names[i] != 'A') {
        int next = i + 1;
        while (next < name.length() && names[next] == 'A') {
          next++;
        }
        int move = 2 * i + name.length() - next;
        minMove = Math.min(move, minMove);
      }
    }
    answer += minMove;

    for (int i = 0; i < name.length(); i++) {
      int front_a = Math.abs(names[i] - 'A');
      int end_z = Math.abs(names[i] - 'Z') + 1;
      answer += Math.min(front_a, end_z);
    }

    return answer;
  }
}
