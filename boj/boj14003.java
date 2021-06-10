package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj14003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb;

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(Integer.MIN_VALUE);

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[N];
        int val = 0;
        int[] preInd = new int[N];
        int index = arr.size() - 1;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < N; i++) {
            val = Integer.parseInt(st.nextToken());
            num[i] = val;

            if(arr.get(arr.size()-1) < val) {
                arr.add(val);
                preInd[i] = arr.size() - 1;
            } else {        // 이분(이진) 탐색 부분
                int left = 1;
                int right = arr.size() - 1;

                while(left < right) {
                    int mid = (left+right)/2;
                    if(arr.get(mid) < val) {
                        left = mid+1;
                    } else right = mid;
                }

                arr.set(right, val);        // 리스트에서의 자신의 위치 기록
                preInd[i] = right;
            }
        }

        sb = new StringBuilder();
        sb.append(arr.size() - 1 + "\n");

        for(int i = N-1; i >= 0; i--) {
            if(preInd[i] == index) {    // 찾는 인덱스에 도달했을 떄,
                stack.push(num[i]);
                index--;
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
            // System.out.println("Test");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
