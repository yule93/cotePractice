package programmers;

import java.util.HashMap;

// key로 웨어의 종류만을 저장할 수 있던 이유는 같은 이름을 가진 의상은 존재하지 않기 때문임.
public class programmersLevel2Camouflage {
    public int solution(String[][] clothes) {
        int answer = 1;		// 곱셈을 위해 1로 지정
        HashMap<String, Integer> hash = new HashMap<>();
        
        for(int i = 0; i < clothes.length; i++) {
    		if(hash.get(clothes[i][1]) == null)
    			hash.put(clothes[i][1], 1);
    		else
    			hash.put(clothes[i][1], hash.getOrDefault(clothes[i][1], 0) + 1);
    	}
    	
    	for(String keys: hash.keySet()) {
    		answer *= (hash.get(keys) + 1);
    	}
        
        return answer - 1;
    }
}
