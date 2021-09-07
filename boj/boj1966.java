package boj;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 1966번: 프린터 큐
public class boj1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            ArrayList<Ele> list = new ArrayList<Ele>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                list.add(new Ele(j, Integer.parseInt(st.nextToken())));     // 문서의 인덱스, 중요도를 순서대로 Ele에 저장하고 그 Ele을 list에 순서대로 차곡차곡 다시 저장
            }
            int cnt = 1;
            base:
            while (!list.isEmpty()) {       // list가 빌 때까지
                Ele e = list.get(0);        // 첫 순서의 문서. 중요도는 각각 랜덤이며 아래의 반복문을 통해 문서가 계속해서 바뀐다.
                for(int j = 1; j < list.size(); j++){  
                    if(e.val < list.get(j).val){        // ele의 중요도가 비교할 list 요소보다 중요도가 낮을 경우,
                        list.remove(0);                 // ele을 지우고,
                        list.add(e);                    // 뒤에 다시 더한다. 중요도가 낮기 때문에 후순위로 미뤄진 것.
                        continue base;                  // base 반복문 반복
                    }
                }
                if(e.p == M){       // ele의 맨 처음 문서순서가 M과 같으면,
                    break;          // 반복문에서 벗어나서
                }
                list.remove(0);
                cnt++;
            }
            bw.write(cnt + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static class Ele {
        int p, val;

        public Ele(int p, int val) {      // pos는 문서의 순서, val은 중요도를 나타낸다.
            this.p = p;
            this.val = val;
        }
    }
}
