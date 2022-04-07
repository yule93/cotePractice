package programmers;

// 프로그래머스 방금 그 곡(그리디)
public class programmers_level2_justNowMusic {
  public static void main(String[] args) {
    //
  }
  public static String solution(String m, String[] musicinfos) {
    String answer = "(None)";
    int maxtime = 0;
    m = m.replaceAll("A#", "a");
    m = m.replaceAll("C#", "c");
    m = m.replaceAll("D#", "d");
    m = m.replaceAll("F#", "f");
    m = m.replaceAll("G#", "g");
    m = m.replaceAll("E#", "e");

    for (int i = 0; i < musicinfos.length; i++) {
      String[] tmp = musicinfos[i].split(",");
      tmp[3] = tmp[3].replaceAll("A#", "a");
      tmp[3] = tmp[3].replaceAll("C#", "c");
      tmp[3] = tmp[3].replaceAll("D#", "d");
      tmp[3] = tmp[3].replaceAll("F#", "f");
      tmp[3] = tmp[3].replaceAll("G#", "g");
      tmp[3] = tmp[3].replaceAll("E#", "e");

      String[] starttime = tmp[0].split(":");
      String[] endtime = tmp[1].split(":");

      int hour = Integer.parseInt(endtime[0]) - Integer.parseInt(starttime[0]);
      int min = Integer.parseInt(endtime[1]) - Integer.parseInt(starttime[1]) + (hour * 60);

      String melody = "";
      for (int j = 0; j < min; j++) {
        melody += tmp[3].charAt(j % tmp[3].length());
      }

      if (melody.indexOf(m) >= 0) {
        if (maxtime < melody.length()) {
          maxtime = melody.length();
          answer = tmp[2];
        }
      }
    }
    return answer;
  }
}
