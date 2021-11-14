package kickStart;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 아 열받네,,,
public class H20211114_2 {

  static char[] colors = { 'R', 'Y', 'B', 'O', 'P', 'G', 'A'};

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      sb.append("Case").append(" #").append(tc).append(": ");
      int answer = 0; // 획의 개수를 저장할 답 변수

      int len = Integer.parseInt(br.readLine());
      String line = br.readLine();

      boolean[][] haveColor = new boolean[3][line.length()]; // R, Y, B
      for (int i = 0; i < len; i++) {
        if (line.charAt(i) == colors[0]) {
          haveColor[0][i] = true;
        } else if (line.charAt(i) == colors[1]) {
          haveColor[1][i] = true;
        } else if (line.charAt(i) == colors[2]) {
          haveColor[2][i] = true;
        } else if (line.charAt(i) == colors[3]) {
          haveColor[0][i] = true;
          haveColor[1][i] = true;
        } else if (line.charAt(i) == colors[4]) {
          haveColor[0][i] = true;
          haveColor[2][i] = true;
        } else if (line.charAt(i) == colors[5]) {
          haveColor[1][i] = true;
          haveColor[2][i] = true;
        } else if (line.charAt(i) == colors[6]) {
          haveColor[0][i] = true;
          haveColor[1][i] = true;
          haveColor[2][i] = true;
        }
      }

      
      for (int i = 0; i < 3; i++) {
        int oneStrokeLen = 0; // 한 번에 같은 컬러로 이어진 선의
        int strokeNum = 0; // 고것의 갯수
        boolean flag = false;
        for (int j = 0; j < len; j++) {
          if (haveColor[i][j]) {
            // 스트로크가 그려졌을 때,
            if (!flag) {
              flag = true;
              strokeNum++;
            }
            oneStrokeLen++;
          } else if (!haveColor[i][j] && oneStrokeLen > 0) {
            // 안 그려졌을 때,
            flag = false;
            oneStrokeLen = 0;
          }
        }
        System.out.println(i+": " + strokeNum);
        answer += strokeNum;
      }

      sb.append(answer).append("\n");
    }
    
    System.out.println(sb.toString());
  }
}
