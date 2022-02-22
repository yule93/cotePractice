package programmers;

import java.util.Arrays;

// 프로그래머스 구명보트(그리디)
public class programmers_level2_emergencyBoat {
  public int solution(int[] people, int limit) {
    Arrays.sort(people);
    int i = 0, j = people.length - 1;

    // * 만약 두 명의 사람이 구명보트에 탈 수 있다면, 2명의 사람이 1개의 그룹으로 묶이는 것이므로,
    // * 전체 사람의 수에서 -1을 해주면 일단 i번째 사람까지 짝을 이룰 수 있는지 따졌을 때의
    // * 구명보트 개수가 나옴
    for (; i < j; j--) {
      if (people[i] + people[j] <= limit)
        i++;
    }
    return people.length - i;
  }
}
