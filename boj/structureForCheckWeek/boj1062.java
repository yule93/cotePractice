package boj.structureForCheckWeek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 1062 가르침 (dfs, 백트래킹)
// K개의 글자를 가르칠 시간 밖에 없고 학생들은 K개의 글자로만 이뤄진 단어를 읽을 수 있다. 어떤 K개의 글자를 가르쳐야 읽을 수 있는 단어의 개수가 최대가 되는지 고민에 빠졌다.
// 남극언어의 모든 단어는 anta로 시작되고 tica로 끝난다. N개 밖에 없을 때 최댓값을 구하는 프로그램을 작성하기
// 시간복잡도는 O(26C(K-5)*N*(단어최장길이))로, 최악의 시간복잡도는 O(26C13*50*15) = 약 78억이나 백트래킹으로 시간 단축이 가능한 것으로 보임
public class boj1062 {

  static boolean[] alpha;
  static int choose, N, K, answer;
  static String[] word;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 최대 50개. 단어의 길이는 8~15라서 단순 탐색해도 시간 초과 안 날듯
    K = Integer.parseInt(st.nextToken()); // 가르칠 수 있는 알파벳의 개수

    word = new String[N];
    for (int n = 0; n < N; n++) {
      String input = br.readLine();
      word[n] = input.substring(4, input.length()-4);
    }

    if (K < 5) {
      // * a, c, n, t, i 를 선택하지 못할 때 아무 단어도 말 못한다.
      System.out.println(0);
      return;
    } else if (K == 26) {
      // * 모든 알파벳을 쓸 수 있다면 모든 단어를 말할 수 있다.
      System.out.println(N);
      return;
    } else {
      // * 애매하게 6~25개 알파벳을 알 때,
      alpha = new boolean[26]; // 배우는 K개의 알파벳인데... 중요한건 이걸 비트마스킹으로 처리해야 하나봄....
      alpha['a' - 'a'] = alpha['c' - 'a'] = alpha['i' - 'a'] = alpha['t' - 'a'] = alpha['n' - 'a'] = true;

      choose = K - 5;
      answer = 0;
      dfs(0, 0);

      System.out.println(answer);
    }
  }

  // ! 조합을 만드는 메소드. 조합인 이유는 알파벳의 집합 내 순서가 어떻든 상관 없이 쓸 수 있기 때문에 start로 시작 지점 정함
  public static void dfs(int depth, int start) {
    if (depth == choose) {
      // String str = "";
      // for (int i = 0; i < 26; i++) {
      //   if (alpha[i])
      //     str += (char)('a' + i);
      // }
      // System.out.println(str);

      int max = 0;
      loop:
      for (int n = 0; n < N; n++) {
        for (int j = 0; j < word[n].length(); j++) {
          if (!alpha[word[n].charAt(j) - 'a']) {
            // 만약 n번째 단어를 만들 수 없다면 다음 단어 확인 ㄱㄱ
            continue loop;
          }
        }
        max++;
      }
      answer = Math.max(answer, max);
      return;
    }

    for (int i = start; i < 26; i++) {
      if (!alpha[i]) {
        alpha[i] = true;
        dfs(depth + 1, i);
        alpha[i] = false;
      }
    }
  }
}
