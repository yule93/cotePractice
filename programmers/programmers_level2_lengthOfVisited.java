package programmers;

public class programmers_level2_lengthOfVisited {
  public static void main(String[] args) {
    System.out.println(solution("ULURRDLLU"));
  }

  public static int solution(String dirs) {
    int answer = 0;

    boolean[][][][] map = new boolean[11][11][11][11]; // -5~5까지 방문 체크
    int[] now = { 5, 5 }; // 첫 시작 좌표
    for (String str : dirs.split("")) {
      int ny = now[0];
      int nx = now[1];
      switch (str) {
        case "U":
          ny = now[0] - 1;
          break;
        case "D":
          ny = now[0] + 1;
          break;
        case "R":
          nx = now[1] + 1;
          break;
        case "L":
          nx = now[1] - 1;
          break;
        default:
          break;
      }
      if (ny > 10 || ny < 0 || nx > 10 || nx < 0)
        continue;
      if (!map[now[0]][now[1]][ny][nx] && !map[ny][nx][now[0]][now[1]])
        answer++;
      map[now[0]][now[1]][ny][nx] = map[ny][nx][now[0]][now[1]] = true;
      
      now[0] = ny;
      now[1] = nx;
    }

    return answer;
  }
}
