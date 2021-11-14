package kickStart;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class H20211114_1 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
      
    int T = Integer.parseInt(br.readLine());
    
    for(int tc = 1; tc <= T; tc++) {
      sb.append("Case").append(" #").append(tc).append(": ");
      int answer = 0;
      String str = br.readLine();
      String preferUnits = br.readLine();
      
      loop:
      for (int i = 0; i < str.length(); i++) {
        int cnt = 1_000_000;
        for (int j = 0; j < preferUnits.length(); j++) {
          char unit = preferUnits.charAt(j);
          // 바꿀 단어
          // 아스키코드 a~z: 97~122
          char spell = str.charAt(i);
          if (unit == spell)
            continue loop;
          int innerLen = Math.abs(unit - spell);
          int outerLen = 0;
          if (spell > unit) {
            // 커서가 있는 부분의 알파벳이 뒷순서일 경우,
            outerLen = Math.abs(Math.abs(122 - spell) + Math.abs(unit - 97));
          } else if (spell < unit) {
            // 커서가 잇는 부분의 알파벳이 앞순서일 경우,
            outerLen = Math.abs(Math.abs(122 - unit) + Math.abs(spell - 97));
          }
          int unitCnt = innerLen >= outerLen ? outerLen : innerLen;
          cnt = Math.min(cnt, unitCnt);
          //System.out.println(j+1 + ": " + cnt);
        }
        answer += cnt;
      }
      sb.append(answer).append("\n");
    }
    
    System.out.println(sb.toString());
  }
}
