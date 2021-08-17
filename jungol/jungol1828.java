package jungol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class jungol1828 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());        // 1 이상 100 이하, 보관온도는 -270 ~ 10000도 사이
        int[][] arr = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int answer = 1;         // 냉장고 개수는 무조건 한 대 이상

        // 최고 온도를 오름차순으로
        Arrays.sort(arr, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        for(int i = 0; i < N; i++) {
            System.out.println("최저 온도: " + arr[i][0] + ", 최고 온도: " + arr[i][1]);
        }

        int max = arr[0][1];
        for(int i = 0; i < N; i++) {
            // 높은 온도 중 가장 낮은 온도를 가진 max가 i번째의 화학 물질의 낮은 온도보다 높다면,
            // 두 개의 화학 물질은 같은 냉장고에 존재 가능.
            if(arr[i][0] <= max) {
                // 이 때, arr[i]의 높은 온도가 arr[0]의 높은 온도보다 낮다면 max값을 더 낮춰서 교차점이 없도록 줄여나간다!
                if(arr[i][1] < max) max = arr[i][1];
                continue;
                // 높은 온도 중 가장 낮은 온도를 가진 max가 i번째 화학 물질의 낮은 온도보다 낮다면,
                // 두 물질은 절대 같은 냉장고에 들어갈 수 없으므로, answer++;
                // 그리고 더 높은 낮은 온도를 가진 물질과 교차점이 있는지 비교하기 위해 max를 i번째 높은 온도로 바꿔준다.
            } else {
                max = arr[i][1];
                answer++;
            }
        }       // end of counting

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }       // end of main
}       // end of class
