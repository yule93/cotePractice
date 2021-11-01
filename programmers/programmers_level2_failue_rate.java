package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class programmers_level2_failue_rate {

    public static void main(String[] args) {
        int[] ans = solution(5, new int[] {2, 1, 2, 6, 2, 4, 3, 3});
        //int[] ans = solution(4, new int[] {4, 4, 4, 4, 4});

        for (int test : ans) {
            System.out.println(test);
        }
    }

    // 실패율이 높은 스테이지부터 내림차순으로 스테이지 번호가 담긴 배열을 return
    public static int[] solution(int N, int[] stages) {
        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                if (o1[1] != o2[1]) {
                    return o2[1] > o1[1] ? 1 : -1;
                }
                return (int) (o1[0] - o2[0]);
            }
        });
        // 스테이지의 개수만큼 반복문
        for (int i = 1; i <= N; i++) {
            int success = 0;
            int fail = 0;
            // 각 사람마다 성공, 실패 나눠서 실패율 각 배열에 저장할 예정
            for (int j = 0; j < stages.length; j++) {
                if (stages[j] > i) {
                    // 성공한 거임!
                    success++;
                } else if (stages[j] == i) {
                    // 실패...
                    fail++;
                    success++;
                }
            }

            double rate = (double) fail / (double) success;

            if (success == 0) {
                pq.add(new double[] { i, 0 });
                continue;
            }
            pq.add(new double[] { i, rate });
        }
        
        int[] answer = new int[pq.size()];
        for (int i = 0; i < answer.length; i++) {
            double[] rate = pq.poll();
            answer[i] = (int) rate[0];
        }

        return answer;
    }
}
