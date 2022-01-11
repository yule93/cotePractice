package programmers;

import java.util.*;

public class programmers_level2_findPrimeNumber {
  static HashSet<Integer> numbersSet = new HashSet<>();

  public static void main(String[] args) {
    System.out.println(solution("17"));
  }

  public static int solution(String numbers) {
    dfs("", numbers); // 순열로 원소 하나씩 세워서 붙여보기(부분집합 개념)
    int count = 0;
    Iterator<Integer> it = numbersSet.iterator();
    while (it.hasNext()) {
      int number = it.next();
      if (isPrime(number))
        count++;
    }

    return count;
  }

  public static boolean isPrime(int num) { // 1. 0과 1은 소수가 아니다
    if (num == 0 || num == 1)
      return false;
    int lim = (int) Math.sqrt(num);

    for (int i = 2; i <= lim; i++)
      if (num % i == 0)
        return false;

    return true;
  }

  // 순열 dfs
  public static void dfs(String comb, String others) {
    if (!comb.equals(""))
      numbersSet.add(Integer.valueOf(comb));
    for (int i = 0; i < others.length(); i++)
      dfs(comb + others.charAt(i),
          others.substring(0, i) + others.substring(i + 1));
  }
}
