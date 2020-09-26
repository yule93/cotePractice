import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> scov = new PriorityQueue<>();	// 우선순위를 알아서 정렬해주는 큐. 전형적인 힙 문제라고 함.
        for(int i = 0; i < scoville.length; i++){
            scov.offer(scoville[i]);	// 순서 상관 없어서 일단 때려 박음
        }
        while(scov.peek() <= K && scov.size()>= 2){
            int new_food = scov.poll() + scov.poll()*2;
			scov.offer(new_food);
            answer++;
        }
        if(scov.peek() < K){
            answer = -1;
        }
		return answer;
    }
}