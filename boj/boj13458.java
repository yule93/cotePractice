package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 13458 시험 감독(수학)
// * 답 리턴 타입이 생각해보니... 100만*100만이 될 수 있어서 int로 정의가 불가능... 조심하세요 ㅋㅋ
// 504ms
public class boj13458 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] A = new int[N];
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    int B = Integer.parseInt(st.nextToken());   // 총 감독관이 감시할 수 있는 응시자 수
    int C = Integer.parseInt(st.nextToken()); // 부감독관이 감시할 수 있는 응시자 수

    long answer = N;
    for (int i = 0; i < N; i++) {
      long res = A[i] - B < 0 ? 0 : A[i] - B;
      answer += ((res) % C != 0 ? (res) / C + 1 : (res) / C);
    }
    
    System.out.println(answer);
  }
}
