package SWEA;

import java.util.Scanner;

// SWEA 1954: 달팽이
public class swea1954 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int[][] array = new int[N][N];
			int turn = 1;
			int x = 0, y = -1;
			int count = 1;
			
			while(true) {
				for(int i = 0; i < N; i++) {
					y = y + turn;
					array[x][y] = count++;
				}
				N--;
				for(int i = 0; i < N; i++) {
					x = x + turn;
					array[x][y] = count++;
				}
				turn *= -1;
				if(N == 0) break;
			}
			System.out.println("#" + t);
			int len = array.length;

			for(int i = 0; i < len; i++) {
				for(int j = 0; j < len; j++) {
					System.out.print(array[i][j] + " ");
				}
				System.out.println();
			}
		}
        sc.close();
    }               // end of main
}                   // end of class
