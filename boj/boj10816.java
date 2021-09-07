package boj;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


// 숫자 카드 2:
// 숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다. 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하는 프로그램을 작성하시오.
public class boj10816 {
    static int num = 20000001;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int arr[] = new int[num];
        
        for(int i = 0; i < N; i++) {
            arr[Integer.parseInt(st.nextToken())+10000000]++;
        }
        
        int M = Integer.parseInt(br.readLine());
        s = br.readLine();
        st = new StringTokenizer(s);
        int count = 0;

        while(count++ < M) {
            bw.write(String.valueOf(arr[Integer.parseInt(st.nextToken())+10000000]+" "));
        }
        
        /*HashMap<int, int> hsmap = new HashMap<>();
        for(int i = 0; i < N; i++) {
            hsmap.put(Integer.valueOf(st.nextToken()), );
        }*/
        
		bw.flush();
		bw.close();
		br.close();
	}
}
