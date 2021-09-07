package boj;
import java.util.*;

// boj 1018번: 체스판 다시 칠하기
public class boj1018 {
    static char trans[][]; // 8x8의 체스판을 뜯어 내 저장하는 체스판 변수이다.
	static char arr[][];  // nxm의 보드를 저장 할 변수이다.
	static final char[][] white = { // (0,0)이 W일 때 가질 수 있는 규칙이다.
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'}
	};
	
	static final char[][] black = { // (0,0)이 B일 때 가질 수 있는 규칙이다.
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'}
	};

    // 뜯어낸 체스판을 각 규칙과 비교하는 메소드
	public static int trans(int a, int b,int min) {
		int cnt1 = 0; // BLACK 비교
		int cnt2 = 0; // WHITE 비교
		int k=0;
		StringBuilder sb = new StringBuilder("");
		for(int i =a;i<a+8;i++) {   // 시작점이 전달되면 그로부터 8칸을 보드에서 가져온다
			sb.setLength(0);    //StringBuilder 초기화 , 공백으로 만든다.
			for(int j=b;j<b+8;j++) { // 시작점이 전달되면 그로부터 8칸을 보드에서 가져온다
				sb.append(arr[i][j]); // 보드에서 가져온 해당 점을 stringbuilder에 저장
			}		
			for(int j=0;j<sb.length();j++) {
				trans[k][j] = sb.charAt(j); //행 단위로 체스판 배열에 저장
			}
			k++; // 체스판 행 증가, 
			
		}
				
		for(int i=0; i<trans.length; i++) { // 체스판을 탐색
			for(int j = 0; j<trans[i].length; j++) {									
					if(trans[i][j] != black[i][j]) { // black의 규칙으로 변환되는 횟수
						cnt1++;
					}		
					if(trans[i][j] != white[i][j]) { // white의 규칙으로 변환되는 횟수
						cnt2++;
					}			
				}
			}
		return Math.min(Math.min(cnt2, cnt1), min); // 최소값 반환하기
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Input
		int n = sc.nextInt();
		int m = sc.nextInt();
		arr = new char [n][m];
		
		trans = new char [8][8]; 
		for(int i=0;i<n;i++) {
			String row = sc.next();
			for(int j=0;j<m;j++) {
				arr[i][j] = row.charAt(j);
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i=0; i <= n-8; i++) {       // (0,0) ~ (n-8, m-8)의 범위만큼 시작점을 전달
			for(int j=0; j <= m-8; j++) {
				min = trans(i, j, min);         // 위의 메소드로 시작점과 현재까지 만든 체스판 중 최솟값 전달
				
			}
			
		}
		System.out.println(min);
        sc.close();
	}
}
