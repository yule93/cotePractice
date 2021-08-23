package boj;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 삭제, 삽입을 많이 하면 LinkedList, Queue. 인덱스로 원하는 데이터 찾을 때는 배열을 사용
// 카드1이 선행 문제
public class boj2164 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> cards = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            cards.add(i);
        }
        while (cards.size() != 1) {
            cards.remove(0);            // 맨 위의 카드 제거
            cards.add(cards.get(0));    // 제거 후에 맨 위의 카드가 된 두 번째 카드를 맨 뒤로 옮김
            cards.remove(0);            // 다시 맨 위의 카드 제거
        }
        
        bw.write(String.valueOf(cards.get(0)));
		bw.flush();
		bw.close();
		br.close();
	}
}
