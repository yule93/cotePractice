package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 정올 2577 회전 초밥(백트래킹)
public class jungol2577 {

    public static int[] sushis;
    public static int N, d, k, c; // 벨트에 놓인 접시 수 N, 초밥 가짓수 d, 연속해서 먹는 접시수 k, 쿠폰번호 c
    public static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushis = new int[N];

        // 중복 초밥을 안 넣기 위해서 해시맵 사용! 키가 초밥의 종류, 값이 동종의 초밥 개수를 나타낸다.
        Map<Integer, Integer> eat = new HashMap<Integer, Integer>();
        eat.put(c, 1);

        for (int i = 0; i < N; i++) {
            int sushi = Integer.parseInt(br.readLine());
            sushis[i] = sushi;
            if (i < k) {
                if (eat.containsKey(sushi)) {
                    eat.put(sushi, eat.get(sushi) + 1);
                } else {
                    eat.put(sushi, 1);
                }
            }
        }

        max = -1;

        for (int i = k; i < sushis.length + k; i++) {
            max = Math.max(max, eat.size());
            int e = sushis[Math.abs(k - i)];
            if (eat.containsKey(e) && eat.get(e) > 1) {
                // 초밥 먹었으면 갖고 있던 초밥에서 개수 하나 뺀걸 넣음!
                eat.put(e, eat.get(e) - 1);
            } else {
                // 먹은 초밥은 버림
                eat.remove(e);
            }

            int sushi = sushis[(i >= sushis.length) ? Math.abs(sushis.length - i) : i];
            if (!eat.containsKey(sushi)) {
                eat.put(sushi, 1);
            } else {
                eat.put(sushi, eat.get(sushi) + 1);
            }
        }

        System.out.println(max);
    }
}
