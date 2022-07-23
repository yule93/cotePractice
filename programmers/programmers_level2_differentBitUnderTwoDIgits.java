package programmers;

// 프로그래머스 두 개 이하로 다른 비트(수학)
public class programmers_level2_differentBitUnderTwoDIgits {
  public static void main(String[] args) {

    for (long num : solution(new long[] { 2, 7 })) {
      System.out.println(num);
    }
  }

  public static long[] solution(long[] numbers) {
    long[] answer = new long[numbers.length];

    for (int i = 0; i < numbers.length; i++) {
      long num = numbers[i];
      if ((num & 1) == 0) {
        answer[i] = num + 1;
      } else {
        loop: 
        for (long j = 1; j < (num << 2); j = j << 1) {
          if ((j & num) == 0) {
            num = num | j;
            for (long k = j >> 1; k > 0; k = k >> 1) {
              if ((num & k) == k) {
                answer[i] = num - k;
                break loop;
              }
            }
          }
        }
      }
    }

    // * 왜... 안 되는거지...? ㅜㅜ
    // for (int i = 0; i < numbers.length; i++) {
    // for (long j = numbers[i] + 1; j <= Math.pow(10, 15); j++) {
    // // numbers보다 크고 다른 비트가 2개 이하여야 함
    // if (2 > Long.toBinaryString(j).length() - (numbers[i] & j)) {
    // // System.out.println((numbers[i] & j));
    // answer[i] = j;
    // break;
    // }
    // }
    // }
    return answer;
  }
}