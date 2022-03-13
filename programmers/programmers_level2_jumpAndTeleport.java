package programmers;

// 프로그래머스 점프와 순간이동
// 백준 리모콘 문제랑 또 다르네
// 처음에 n이 10억이라길래 2로 top-down하면 시간 초과 날 것 같아서 걱정했는데
// 생각해보니 2를 계속 나누면 되는 거라 O(log2_N)이 돼서 O(log10억)=해봤자 30...밖에 안 됨 ㅋㅋ
public class programmers_level2_jumpAndTeleport {
  public static void main(String[] args) {
    System.out.println(solution(5000));
  }
  public static int solution(int n) {
    int answer = 0;
    // n 은 10억 이하 자연수, k는 1이상 자연수
    // top-down
    while (n != 0) {
      if (n % 2 == 0) {
        n /= 2;
      } else {
        n--;
        answer++;
      }
    }

    return answer;
  }
}
