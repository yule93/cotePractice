package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// boj 1043 거짓말(union-find. disjoint Set)
// 128ms
public class boj1043 {

  static int[] parent;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 사람의 수. 50 이하 자연수
    int M = Integer.parseInt(st.nextToken()); // 파티의 수. 50 이하 자연수

    // * 즉, 진실을 아는 사람의 번호가 i라 했을 때, i라는 사람이 없는 파티에서 구라를 깔 수 있다는 말
    // * 따라서 하... 이거 조합 아님?? m명 중 truep만큼만 진실을 아는 것 근데 수가 너무 커....
    // * 아... 아는 사람 주어지는구나
    // * 이게 왜 유니온파인드임...?
    parent = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }

    boolean[] knownP = new boolean[N + 1];
    st = new StringTokenizer(br.readLine());
    int trueP = Integer.parseInt(st.nextToken()); // 진실을 아는 사람. 0~50
    for (int i = 0; i < trueP; i++) {
      // * 진실을 아는 사람을 저장
      knownP[Integer.parseInt(st.nextToken())] = true;
    }

    ArrayList<Integer>[] list = new ArrayList[M];
    for (int i = 0; i < M; i++) {
      list[i] = new ArrayList<>();
    }

    // * 아!!! 왜 유니온파인드인지 이해함. 파티 자리에 진실을 아는 사람 한 명이라도 있고 거기서 구라 치면 거기 있던 모든 사람들이 구라인 걸 알게 되는 거니까 ㅇㅋㅇㅋ
    int value, pre = 0;
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      trueP = Integer.parseInt(st.nextToken());
      if (trueP > 0) {
        pre = Integer.parseInt(st.nextToken());
        list[i].add(pre);
      }

      for (int j = 1; j < trueP; j++) {
        value = Integer.parseInt(st.nextToken());
        list[i].add(value);
        union(pre, value);
        pre = value;
      }
    }

    for (int i = 1; i < N + 1; i++) {
      if (knownP[i]) {
        knownP[find(i)] = true;
      }
    }

    int answer = 0;
    for (int i = 0; i < M; i++) {
      if (list[i].size() > 0) {
        // 파티한 사람이 한 명이라도 있을 경우만 체크
        int k = find(list[i].get(0));
        if (!knownP[k])
          answer++;
      }
    }

    System.out.println(answer);
  }
  
  private static int find(int a) {
		if(parent[a] == a) 
			return parent[a] = a;
		else  return find(parent[a]);
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a!= b) {
			if(a>b) {
				parent[a] = b;
			} else {
				parent[b] = a;
			}
			return true;
		}
		return false;
	}
}
