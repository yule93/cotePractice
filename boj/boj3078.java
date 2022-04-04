package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 3078 좋은 친구 (슬라이딩 윈도우, 큐)
// * 생각해보니 친구가 30만명이라면 2중 반복문으로 최고 9초가 나오는 처참한 상황 발생.... 다르게 생각해야 한다.
// 344ms
public class boj3078 {
  static BufferedReader br;
  static StringTokenizer st;

  public static void main(String[] args) throws Exception {
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 상근이네 반 학생의 수 3~30만
    int K = Integer.parseInt(st.nextToken()); // K등 차이 이내이고 글자수가 같은 친구가 좋은 친구(ㅋㅋㅋㅋㅋ) 2~20글자

    long answer = 0;    // 최대 21억쌍이라 괜찮을 줄 알았는데 아니네요... ㅎㅎ
    // * 학생 이름의 길이 별로 큐에 넣어준다. 즉, 4글자면 q[4]에 넣어주고 이런식. 따라서 20글자까지 존재하므로 큐를 20개 만들어줌.
    Queue<Integer>[] q = new LinkedList[21];
    for (int i = 2; i < 21; ++i)
      q[i] = new LinkedList<>();

    for (int i = 1; i <= N; ++i) {
      int len = br.readLine().length();
      if (q[len].size() == 0) {
        q[len].add(i);
      } else {
        while (q[len].size() > 0) {
          // 아니라면 가장 앞의 등수를 확인하고, 현재 학생의 등수의 차가 K이하라면 q사이즈만큼 쌍이 만들어지므로 answer에 더한다.
          // 그리고 q에 넣고 빠져나온다
          if (i - q[len].peek() <= K) {
            answer += q[len].size();
            q[len].add(i);
            break;
          }
          // 만약 q의 가장 앞에 있는 등수와의 차가 K보다 크다면 빼낸다.
          q[len].poll();
        }
        // q가 비었다면 q에 있었던 모든 학생의 차가 K보다 컸기 때문에 그냥 현재 학생의 등수만 넣어준다.
        if (q[len].size() == 0)
          q[len].add(i);
      }
    }
    System.out.println(answer);

  }

  // 슬라이딩 윈도우를 썼지만 시간초과....
  public static void function1(int N, int K) throws Exception {
    String[] student = new String[N];
    for (int i = 0; i < N; i++) {
      student[i] = br.readLine();
    }

    ArrayList<String> list = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      String name = student[i];
      for (int j = i - 1; j >= 0 && j >= i - K; j--) {
        // 자기보다 앞선 등수에 있는 애들 K만큼 체크
        if (student[j].length() == name.length()) {
          String combi1 = name + student[j];
          String combi2 = student[j] + name;
          if (list.contains(combi1) || list.contains(combi2))
            continue;
          list.add(combi1);
        }
      }
      for (int j = i + 1; j < N && j <= i + K; j++) {
        // 자기보다 뒷 등수에 있는 애들 K만큼 체크
        if (student[j].length() == name.length()) {
          String combi1 = name + student[j];
          String combi2 = student[j] + name;
          if (list.contains(combi1) || list.contains(combi2))
            continue;
          list.add(combi1);
        }
      }
    }

    System.out.println(list.size());
  }
}
