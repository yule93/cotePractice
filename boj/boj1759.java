package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1759 {
    static char[] map;
    static StringBuilder sb;
    static int L, C;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        String str = br.readLine().replace(" ", "");
        map = new char[C];
        for(int i = 0; i < C; i++) {
            map[i] = str.charAt(i);
        }
        sb = new StringBuilder();
        Arrays.sort(map);
        
        String vowels = "aeiou";        // 모음
        
        for(int i = 1; i < 1 << C; i++) {
            String pass = "";
            int vowelsCount = 0;
            int consonantCount = 0;
            if(bitCount(i) == 4) {
                for(int j = 0; j < C; j++) {
                    if((i & 1 << j) > 0) {
                        pass = pass.concat(Character.toString(map[j]));
                        if(vowels.contains(Character.toString(map[j]))) vowelsCount++;
                        else consonantCount++;
                    }
                }
                if(vowelsCount >= 1 && consonantCount >= 2) sb.append(pass).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int bitCount(int input) {
        int count = 0;
        int mask = 1 << 31;
        while (true){
            if (mask == 0) break;
            if((mask & input) != 0) count++;
            mask = mask >>> 1;
        }
        return count;
    }

    public static void combination(int cnt, int idx, String s) {
		if (cnt == L) {
			int vowelCnt = 0;
			int consonantsCnt = 0;
			for (int i = 0; i < L; ++i) {
				if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u') {
					vowelCnt++;
				}
				else {
					consonantsCnt++;
				}
			}
			if (vowelCnt >= 1 && consonantsCnt >= 2) {
				sb.append(s).append("\n");
			}
			return;
		}
		for (int i = idx; i < C; ++i) {
			combination(cnt + 1, i + 1, s + Character.toString(map[i]));
		}
	}
}
