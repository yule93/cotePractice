package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj2477 {

    static class Info{
		int dir, length;
		Info(int dir, int length){
			this.dir = dir; this.length = length;
		}
	}
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        List<Info> list = new ArrayList<>();
        int K = Integer.parseInt(br.readLine());

        int N = 0, M = 0;		// 세로, 가로
        // 동서남북 : 1234
		for(int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			if(list.get(i).dir == 1 || list.get(i).dir == 2) 
				N = Math.max(list.get(i).length, N);        // 좌우변의 길이 중에서 긴 것 체크
			if(list.get(i).dir == 3 || list.get(i).dir == 4) 
				M = Math.max(list.get(i).length, M);        // 상하변의 길이 중에서 긴 것 체크
		}
		list.add(list.get(0));
		int answer = M * N;
		
		for(int i = 0; i < list.size() - 1; i++) {
			Info f = list.get(i);
			Info s = list.get(i+1);
            // 뚫린 부분의 패턴. ㄱ자 모양은 아래 오른쪽 아래 오른쪽
            // ㄴ자 모양은 위 왼쪽 위 왼쪽
            // 」자 모양은 왼쪽 아래 왼쪽 아래
            //「자 모양은 오른쪽 위 오른쪽 위
			if((f.dir == 1 && s.dir == 3) || (f.dir == 2 && s.dir == 4) || (f.dir == 3 && s.dir == 2) || (f.dir == 4 && s.dir == 1)) {
				answer -= f.length * s.length;
				break;
			}
		}

        bw.write(String.valueOf(answer * K));
        bw.flush();
        bw.close();
        br.close();
    }
}
