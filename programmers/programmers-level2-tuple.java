package programmers;
import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
		String[] strs = s.replaceAll ("[{}]", " ").trim().split(" ,");	// trim으로 공백 제거, split으로 문자열을 나눠 문자 배열 생성
		answer = new int [strs.length];
		HashSet<Integer> hs = new HashSet<Integer>(); // Set을 쓰는 이유는 보통 중복 제거
		Arrays.sort(strs, (a,b)->(		// 람다식
			a.length()-b.length()
			));

		int i = 0;

		for(String str: strs) {
			for(String strrr: str.split(",")) {
				int a = Integer.parseInt(strrr.trim());
				if(hs.contains(a)) continue;
				hs.add(a);
				answer[i++] = a;
			}
		}
        return answer;
    }
}