package programmers;

// 프로그래머스 큰 수 만들기(그리디)
// * 자리수마다 큰 수를 비교해서 max에 저장해두고 가장 끝에 남은 max 값을 String Builder에 더해주는 식으로 진행한다.
public class programmers_level2_makeBigNumber {
  public static void main(String[] args) {
    // System.out.println(solution());
  }

  public String solution(String number, int k) {
    StringBuilder sb = new StringBuilder();
    int index = 0;
    int max = 0;

    for (int i = 0; i < number.length() - k; i++) {
      max = 0;
      for (int j = index; j <= k + i; j++) {
        if (max < number.charAt(j) - '0') {
          max = number.charAt(j) - '0';
          index = j + 1;
        }
      }
      sb.append(max);
    }
    return sb.toString();
  }
}
