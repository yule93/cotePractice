package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ 2493: 탑 (재귀, ) 엥 나 이거 풀었던거네
// 해당 탑에서 쏜 신호가 '몇 번째' 탑에서 송신 받는지 저장해서 출력. 이 때 탑의 높이는 서로 다르다.
public class boj2493 {
    static int[] topMap;
    static Stack<Integer> height = new Stack<>();
    static Stack<Integer> index = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());            // 탑의 갯수. 1~50만개까지
        StringTokenizer st = new StringTokenizer(br.readLine());
        topMap = new int[N + 1];

        for(int i = 1; i <= N; ++i) {
            topMap[i] = Integer.parseInt(st.nextToken());
        }

        height.push(topMap[1]);
        index.push(1);
        sb.append(0);

        for(int i = 2; i <= N; ++i) {
            while(true) {
                if(!height.isEmpty()) {
                    int top = height.peek();
                    if(top > topMap[i]) {
                        sb.append(" ").append(index.peek());
                        height.push(topMap[i]);
                        index.push(i);
                        break;
                    } else {
                        height.pop();
                        index.pop();
                    }
                } else {
                    sb.append(" ").append(0);
                    height.push(topMap[i]);
                    index.push(i);
                    break;
                }
            }
        }

        bw.write(sb.toString());
        // topMap = new int[N];
        // 
        // for(int i = 0; i < N; i++) {
        //     topMap[i] = Integer.parseInt(st.nextToken());
        // }
        
        // for(int i = N - 1; i >= 0; i--) {
        //     sb.append(whichTop(i)).append(" ");
        // }
        // bw.write(sb.reverse().toString().substring(1, sb.length()));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int whichTop(int num) {
        int which = 0;      // num번째 탑의 신호를 몇 번째 탑이 송신받는지 저장할 숫자. 즉 return값
        for(int i = num - 1; i >= 0; i--) {
            if(topMap[num] < topMap[i]) {
                which = i+1;
                break;
            }
        }
        return which;
    }
}
