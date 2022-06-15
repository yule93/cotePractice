package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class programmers_level3_presentationByN {
  public int solution(int N, int number) {
    List<Set<Integer>> countList = new ArrayList<>();
    for (int i = 0; i < 9; i++)
      countList.add(new HashSet<>());
    countList.get(1).add(N); // N을 1개 쓴 값은 N만임

    for (int i = 2; i < 9; i++) {
      // 8 이하일 때의 값 구하기
      Set<Integer> countSet = countList.get(i);
      for (int j = 1; j <= i; j++) {
        Set<Integer> preSet = countList.get(j);
        Set<Integer> postSet = countList.get(i - j);

        for (int preNum : preSet) {
          for (int postNum : postSet) {
            countSet.add(preNum + postNum);
            countSet.add(preNum - postNum);
            countSet.add(preNum * postNum);

            // Numeric 에러 방지(0/0같은 거랑 0/num = 0일 때 중복 추가 방지)
            if (preNum != 0 && postNum != 0)
              countSet.add(preNum / postNum);
          }
        }
      }

      // NNNN 같은 거 더하기
      int repeat = 0;
      for (int j = 0; j < i; j++) {
        repeat += Math.pow(10, j) * N;
      }
      countSet.add(repeat);
    }

    for (Set<Integer> sub : countList) {
      if (sub.contains(number))
        return countList.indexOf(sub);
    }

    return -1;
  }
}
