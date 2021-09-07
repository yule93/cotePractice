package boj;
import java.io.*;
import java.util.Arrays;

// 시간 초과되는 DP PS.... logN 방법인 이진탐색을 사용해야 한다.
/* public class boj1920 {
    public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
        String[] originS = br.readLine().split(" ");
        int[] originArr = new int[N];
        for(int i =0; i < N; i++) {
            originArr[i] = Integer.parseInt(originS[i]);
        }
        int K = Integer.parseInt(br.readLine());
        String[] compairS = br.readLine().split(" ");
        int[] compairArr = new int[K];
        for(int i = 0; i < K; i++) {
            compairArr[i] = Integer.parseInt(compairS[i]);
        }
        
        for(int i = 0; i < K; i++) {
            int answer = 0;
            for(int j = 0; j < N; j++) {
                if(originArr[j] == compairArr[i]) {
                    answer = 1;
                }
            }
            bw.write(String.valueOf(answer));
        }
        
		bw.flush();
		bw.close();
		br.close();
	}
}*/

// 수 찾기
// N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.
public class boj1920{
    static int N, M;
    static int[] originArr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
        String[] originS = br.readLine().split(" ");
        originArr = new int[N];
        for(int i =0; i < N; i++) {
            originArr[i] = Integer.parseInt(originS[i]);
        }
        Arrays.sort(originArr);
        
        M = Integer.parseInt(br.readLine());
        String[] compareS = br.readLine().split(" ");
        for(int i = 0; i < M; i++) {
            int temp = Integer.parseInt(compareS[i]);
            bw.write(String.valueOf(binarySearch(temp))+"\n");      // \n 같은 개행 잘 보자....
        }
        
		bw.flush();
		bw.close();
		br.close();
	}
    static int binarySearch(int compareNum) {
        int start, end, mid;
        start = 0;
        end = N - 1;
        while(start <= end) {
            mid = (start + end) / 2;
            int val = originArr[mid];
            if(compareNum == val) return 1;
            else if(compareNum > val) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return 0;
    }
}