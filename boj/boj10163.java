package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ 10163 색종이 (구현): 근데 이거 서브태스크는 대체 머임... 100점 어케 맞는거임...??
public class boj10163 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int maxW = 101;
        int maxH = 101;
        int[][] map = new int[maxH][maxW];      // 색종이가 놓일 수 있는 평면의 최대 크기
        int N = Integer.parseInt(br.readLine());

        for(int num = 1; num <= N; num++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            for(int i = row; i < row + height; i++) {
                for(int j = col; j < col + width; j++) {
                    map[i][j] = num;
                }
            }
        }
        
        // 이제 각 색종이의 면적이 얼마나 되는지 확인하기
        for(int number = 1; number <= N; number++) {
            int count = 0;      // 색종이 면적
            for(int i = 0; i < maxW; i++) {
                for(int j = 0; j < maxH; j++) {
                    if(map[i][j] == number) count++;
                }
            }
            sb.append(count).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
