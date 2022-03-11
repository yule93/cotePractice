package programmers;

import java.util.Arrays;
import java.util.Comparator;

// 프로그래머스 파일명 정렬(문자열)
// 파일은 1000개 이하, 각 파일명은 100글자 이하로 Arrays.sort가 O(N^2)의 복잡도를 가진 만큼
// 여기에 최대 길이 100의 문자열을 비교해야 하므로, 시간 복잡도는 최악의 경우 100x1000x1000 = 100_000_000 = 약 1초이다. 
public class programmers_level2_sortingFileName {
  public static void main(String[] args) {
    for (String str : solution(
        new String[] { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" })) {
      System.out.print(str + " ");
    }
  }

  public static String[] solution(String[] files) {
    Arrays.sort(files, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        String head1 = o1.split("[0-9]")[0];
        String head2 = o2.split("[0-9]")[0];

        int result = head1.toLowerCase().compareTo(head2.toLowerCase());
        // *Head가 같으면 Number 비교 후 Tail은 상관 없으니까 원래 순서대로 놔둠
        if (result == 0) {
          String file1 = o1.substring(head1.length());
          String file2 = o2.substring(head2.length());

          String res1 = "";
          String res2 = "";

          for (char c : file1.toCharArray()) {
            if (Character.isDigit(c) && res1.length() < 5) {
              // ! number는 최대 5글자
              res1 += c;
            } else
              break;
          }

          for (char c : file2.toCharArray()) {
            if (Character.isDigit(c) && res2.length() < 5) {
              res2 += c;
            } else
              break;
          }

          result = Integer.valueOf(res1) - Integer.valueOf(res2);
        }

        return result;
      }
    });

    return files;
  }
}
