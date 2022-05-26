package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// boj 1339 단어 수학(그리디, 해시맵)
// ! ABB, BB, BB, BB, ..., BB와 같은 특수한 경우를 생각해야 했음....
// 140ms
public class boj1339 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < N; i++) {
      char[] ch = br.readLine().toCharArray();
      for (int j = ch.length - 1; j >= 0; j--) {
        int pow = (int) Math.pow(10, ch.length - 1 - j);
        // 키: 알파벳 순서, 값: (알파벳 순서 or 0) + 현재 알파벳의 문자열 내 위치
        // map의 값으로 저장된 수가 클 수록 높은 숫자(9, 8같은)를 부여받는 게 큰 값을 얻을 수 잇음...!
        // 예시로 ABB가 1개, BB가 9개 있다 했을 때, ABB와 BB에서 B가 10의 자리, 1의 자리에 총 10번이 나오므로 100+10의 값을 가짐
        // A는 ABB에서 100의 자리 딱 한 개만 나오므로 100이라는 값을 가짐
        // * 이를 토대로 A보다 B가 크므로 B가 9라는 값을 부여받으면 됨...!
        map.put(ch[j] - 'A', map.getOrDefault(ch[j] - 'A', 0) + pow);
      }
    }

    List<Integer> keyList = new ArrayList<>(map.keySet());
    Collections.sort(keyList, (o1, o2) -> (map.get(o1) - map.get(o2)));

    int answer = 0;
    int num = 10 - map.size();
    for (int key : keyList) {
      answer += map.get(key) * num;
      num++;
    }

    System.out.println(answer);
  }

  // 특수한 경우가 성립이 안 됨.... ㅠㅜ
  public static void solution1() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    String[] arr = new String[N];
    for (int i = 0; i < N; i++) {
      arr[i] = br.readLine();
    }

    // 제일 긴 문자열의 가장 큰 자리수부터 9, 8, 7... 이렇게 차지해야 함
    // 예시로 주어진 GCF, ACDEB가 있다면 A가 9, C가 8이 돼야 제일 큰 숫자가 될 수 있음
    Arrays.sort(arr, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o2.length() - o1.length();
      }
    });

    int num = 9;
    int[] alpha = new int[26];
    int[] alphaCount = new int[26];
    int[] alphaPos = new int[26];
    Arrays.fill(alpha, -1);

    for (int i = 0; i < N; i++) {
      String str = arr[i];
      for (int j = 0; j < str.length() - arr[N - 1].length(); j++) {
        int ch = str.charAt(j) - 'A';
        if (alpha[ch] < 0) {
          alpha[ch] = num--;
        }
      }
      for (int j = 0; j < arr[N - 1].length(); j++) {
        int ch = str.charAt(j + str.length() - arr[N - 1].length()) - 'A';
        alphaPos[ch] = Math.max(alphaPos[ch], arr[N - 1].length() - j);
        alphaCount[ch]++;
      }
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        if (alphaPos[o1] == alphaPos[o2]) {
          // 둘이 나타난 위치가 같을 때,
          return o1.compareTo(o2);
        }
        return alphaPos[o2] - alphaPos[o1];
      }
    });
    for (int i = 0; i < 26; i++) {
      if (alpha[i] < 0 && alphaCount[i] > 0) {
        pq.add(i);
      }
    }

    while (pq.size() > 0) {
      int pos = pq.poll();
      alpha[pos] = num--;
    }

    int answer = 0;
    for (String str : arr) {
      int sum = 0;
      for (int i = 0; i < str.length(); i++) {
        sum *= 10;
        int pos = alpha[str.charAt(i) - 'A'];
        sum += pos;
      }
      answer += sum;
    }

    // System.out.println(alphaPos['D'-'A']+", "+ alphaCount['D'-'A']);
    // System.out.println(alpha['G'-'A']);
    System.out.println(answer);
  }
}
