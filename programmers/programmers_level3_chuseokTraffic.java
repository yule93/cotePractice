package programmers;

import java.text.SimpleDateFormat;
import java.util.Date;

// 프로그래머스 추석 트래픽
public class programmers_level3_chuseokTraffic {
  public static void main(String[] args) throws Exception {
    String[] arrs1 = new String[] {
        "2016-09-15 20:59:57.421 0.351s",
        "2016-09-15 20:59:58.233 1.181s",
        "2016-09-15 20:59:58.299 0.8s",
        "2016-09-15 20:59:58.688 1.041s",
        "2016-09-15 20:59:59.591 1.412s",
        "2016-09-15 21:00:00.464 1.466s",
        "2016-09-15 21:00:00.741 1.581s",
        "2016-09-15 21:00:00.748 2.31s",
        "2016-09-15 21:00:00.966 0.381s",
        "2016-09-15 21:00:02.066 2.62s" };

    System.out.println(solution(arrs1));
  }

  public static int solution(String[] lines) throws Exception {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    int[] counts = new int[lines.length];
    int max = 0;

    for (int i = 0; i < lines.length; i++) {
      // * 이전 로그의 완료 시점
      String[] pre = lines[i].split(" ");
      Date preEndTime = format.parse(pre[1]);
      long preEnd = preEndTime.getTime(); // ! 이전 로그 완료 시점의 시분초밀리초를 밀리초로 변경

      // * 해당 로그 보다 늦게 종료되는 로그 체크
      for (int j = i; j < lines.length; j++) {
        String[] next = lines[j].split(" ");
        Date nextEndTime = format.parse(next[1]);
        // * 처리 시간
        double sec = Double.parseDouble(next[2].substring(0, next[2].length() - 1));

        // ! 다음로그의 종료시점 - 처리 초 + 1;
        long nextStart = (long) (nextEndTime.getTime() - sec * 1000 + 1);
        if (preEnd + 1000 > nextStart) {
          counts[i] += 1;
          max = Math.max(max, counts[i]);
        }
      }
    }
    return max;
  }
}
