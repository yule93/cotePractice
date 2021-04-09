import java.util.*;
import java.io.*;

public class boj2336 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		String [] grades = new String [N];
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		int len = 0;
		int bestStudent = 0;
		
		for (int i = 0; i < N; i++) {
			grades[i] = br.readLine();	// 학생수 N에 따라 학생의 등수 입력받음
			if(i == 0) len = grades[i].length();	// 학생수만큼 
		}
		
		base:
			for(int i = 0; i <= len; i++) {
				for(int j = 0; j < N; j++) {
					String student = grades[j].substring(len-1);
					if(map.containsKey(student)) {
						map.clear();
						continue base;
					} else {
						map.put(student, true);
					}
				}
				bestStudent = i;
				break;
			}
			bw.write(bestStudent+"\n");
			
			bw.flush();
			br.close();
			bw.close();
	}
}