package SWEA;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class swea1210 {
    static int[] dx = {-1, 1, 0};
    static int[] dy = {0, 0, -1};
    static int size = 100;
    
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
        Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] dy = {0, 0, -1};
		int[] dx = {1, -1, 0};
		int T = 1;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int [][] ladder = new int[size][size];
			boolean [][] checked = new boolean[size][size];
			
			for(int i = 0; i< size;i++) 
				for(int j = 0; j<size;j++)
					ladder[i][j] = sc.nextInt();
			for(int i = 0; i<size;i++) {
				if(ladder[size-1][i] == 2) {
					int cy = size-1;
					int cx = i;
					int ny = size;
					int nx = size;
					
					while(ny != 0) {
						for(int j = 0; j< 3; j++) {
							ny = cy + dy[j];
							nx = cx + dx[j];
							if(nx > 99 || ny > 99 || nx < 0 || ny < 0) continue;
							if(ladder[ny][nx] == 1 && !checked[ny][nx]) {
								cy = ny;
								cx = nx;
								checked[cy][cx] = true;
								break;
							}
						}
					}
					bw.write(String.valueOf("#" + test_case + " " + nx + "\n"));
					break;
				}
			}
		}
        bw.flush();
        bw.close();
        sc.close();
    }       // end of main
}