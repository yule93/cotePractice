package programmers;

import java.util.*;

// 프로그래머스 후보키 (DFS+해시셋)
// 해시셋의 O(1) 검색 알고리즘과 DFS로 컬럼의 부분집합을 만들어 될 수 있는지 없는지 확인하며 후보키로 만드는 알고리즘을 구현하는 게 이 문제의 핵심!!
// 시간 복잡도는 O((부분집합의 원소의 개수)x(컬럼의 길이 조합)). 최악의 경우 O(4x8C4) 정도 되지 않을지.... 어차피 후보키를 만드는 데 순서는 중요하지 않으니까!
public class programmers_level2_candidateKey {
  public static void main(String[] args) throws Exception {
    System.out.println(solution(new String[][] { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
        { "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
        { "600", "apeach", "music", "2" } }));
  }

  static ArrayList<HashSet<Integer>> candidateKey;

  public static int solution(String[][] relation) {
    candidateKey = new ArrayList<>();
    int colSize = relation[0].length;

    for (int i = 1; i <= colSize; ++i) {
      dfs(-1, colSize - 1, 0, i, new HashSet<>(), relation);
    }

    return candidateKey.size();
  }

  // * 재귀로 후보키가 될 수 있는 칼럼을 한 개씩 선택하면서 만들어보는 부분집합 함수
  public static void dfs(int attr, int maxAttr, int depth, int size, HashSet<Integer> keySet, String[][] relation) {
    if (depth == size) {
      for (HashSet<Integer> key : candidateKey) {
        // 보통 candidateKey가 더 짧을테니 O(1)의 장점을 더 잘 이용하기 위해
        // 만약 이미 등록된 키라면 재귀 빠져나옴
        if (keySet.containsAll(key)) {
          return;
        }
      }

      if (isUnique(keySet, relation)) {
        // 후보키로써 유일한 게 입증이 됐다면 해쉬셋에 넣어준다.
        candidateKey.add(keySet);
      }
      return;
    }

    for (int i = attr + 1; i <= maxAttr; ++i) {
      HashSet<Integer> newKeySet = new HashSet<>(keySet);
      newKeySet.add(i);
      dfs(i, maxAttr, depth + 1, size, newKeySet, relation);
    }
  }

  // * 유일한 후보키인지 판단하는 함수
  public static boolean isUnique(HashSet<Integer> keySet, String[][] relation) {
    HashSet<String> hs = new HashSet<>();
    for (int r = 0; r < relation.length; ++r) {
      String key = "";
      for (int c : keySet) {
        key += relation[r][c];
      }
      if (hs.contains(key))
        return false;
      else
        hs.add(key);
    }
    return true;
  }
}
