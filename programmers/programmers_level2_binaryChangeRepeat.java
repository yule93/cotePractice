package programmers;

public class programmers_level2_binaryChangeRepeat {
  public static void main(String[] args) {
    for (int num : solution("0111010")) {
    System.out.print(num + " ");
    }
  }

  public static int[] solution(String s) {
    int[] answer = new int[2]; // 이진 변환 횟수, 제거된 0의 개수
    String change = s;

    while (!change.equals("1")) {
      int zero = 0;
      for (int i = 0; i < change.length(); i++) {
        if (change.charAt(i) == '0')
          zero++;
      }

      change = change.replaceAll("0", "");
      answer[1] += zero;
      answer[0]++;

      change = Integer.toBinaryString(change.length());
    }

    return answer;
  }
}
