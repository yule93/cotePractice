package boj.structureForCheckWeek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

// boj 1700 멀티탭 스케줄링
// 시간제한 2초, 메모리 제한 128MB
// integer 배열로 저장해도 괜찮을 것 같긴 한데.... search 메소드는 27%에서 틀렸다고 나옴
// 132ms
public class boj1700 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int answer = 0;
    int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍의 개수 1~100
    int K = Integer.parseInt(st.nextToken()); // 전기용품의 총 사용횟수 1~100

    int[] electCounts = new int[K];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      electCounts[i] = Integer.parseInt(st.nextToken());
    }

    Set<Integer> set = new HashSet<>();   // 코드 꽂는 걸 set으로 구현
    for (int i = 0; i < K; i++) {
      if (!set.contains(electCounts[i])) {
        // ! 1) 코드를 바꿔 낄 수 있을 때,(이미 사용하고 있는 전기용품이 아닐 경우)
        if (N <= set.size()) {
          // 코드를 모두 사용 중일 경우
          ArrayList<Integer> list = new ArrayList<>();  // set에서 사용중인 전기용품들
          Set<Integer> remain = new HashSet<>(set);   // 아직 코드 안 낀 순서 기다리는 전기용품들

          for (int j = i; j < K; j++) {
            if (set.contains(electCounts[j]) && !list.contains(electCounts[j])) {
              list.add(electCounts[j]);
              remain.remove(electCounts[j]);
            }
          }

          if (list.size() == N) {
            // ! 2-1) 전부 사용중인 전기용품들일 경우, 이것들 중에서 그래도 가중 나중에 쓰이는 애는 다시 바꿔끼는 게 효율적이므로 먼저 빼준다
            set.remove(list.get(list.size() - 1));
          } else {
            // ! 2-2) 뒤에 사용하지 않지만 지금 코드에 꽂혀있는 전기용품이 있을 경우 안 쓰는 걸 제거
            ArrayList<Integer> temp = new ArrayList<>(remain);
            set.remove(temp.get(0));
          }

          answer++;
        }

        set.add(electCounts[i]);
      }
    }

    System.out.println(answer);
  }

}

/*
   * public static void counting() {
   * LinkedList<Integer> list = new LinkedList<>();
   * ArrayList<Integer> code = new ArrayList<>();
   * for (int i = 0; i < K; i++) {
   * // ! 순서대로 코드에 꽂고 시작해야 하므로 코드 구멍 개수인 N개 이하일 때는 코드에 바로 꽂기위해 code에 넣어주고
   * // ! 이후 들어온 전기용품 사용 계획은 LIFO 형태를 구현해주기 위해 list에 넣어줘 관리.
   * int elect = Integer.parseInt(st.nextToken()) - 1;
   * if (i < N)
   * code.add(elect);
   * else {
   * list.add(elect);
   * electCounts[elect] = Math.max(electCounts[elect], i);
   * }
   * }
   * 
   * // for (int i = 0; i < K; i++) {
   * // if (electCounts[i] == K + 1)
   * // electCounts[i] = 0;
   * // }
   * 
   * // * 그리디 탐색 시작. 앞에 N이 들어가는 이유는 이미 N번째까지 전기용품은 코드에 넣어서 사용중이기 때문
   * for (int i = N; i < K; i++) {
   * // Collections.sort(code, new Comparator<Integer>() {
   * // @Override
   * // public int compare(Integer o1, Integer o2) {
   * // // 맨 처음에 바뀌는 것부터... 해도 괜찮나?
   * // return electCounts[o2] - electCounts[o1];
   * // }
   * // });
   * 
   * // 잠시 확인용
   * for (int n = 0; n < N; n++) {
   * System.out.print(code.get(n)+1 + " ");
   * }
   * System.out.println();
   * 
   * if (!code.contains(list.peek())) {
   * // list에 저장된 것이 code에 꽂힌 게 아닐 때,
   * int rem = 0;
   * 
   * for (int n = 0; n < N; n++) {
   * if (electCounts[code.get(rem)] > electCounts[code.get(n)]) {
   * rem = n;
   * }
   * }
   * 
   * code.remove(rem);
   * electCounts[list.peek()]--;
   * code.add(list.poll());
   * answer++;
   * } else {
   * electCounts[list.poll()]--;
   * continue;
   * }
   * }
   * }
   */