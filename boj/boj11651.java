package boj;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 11651번: 좌표 정렬하기 2
public class boj11651 {
    public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        int N = Integer.parseInt(br.readLine());
        int[][] pos = new int[N][2];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(pos, (a, b) -> {
            if(a[1] == b[1]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
            //Integer.compare(a[1], b[1])
        });
        
        for(int i = 0; i < N; i++) {
            bw.write(String.valueOf(pos[i][0])+" "+ String.valueOf(pos[i][1])+"\n");
        }

        bw.flush();
		bw.close();
		br.close();
	}
}
