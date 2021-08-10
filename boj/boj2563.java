package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ 2563 색종이 (구현)
public class boj2563 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Square[] sqArr = new Square[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sqArr[i] = new Square(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int answer = 0;     // 색종이가 덮은 전체 넓이
        /** arr[n]과 arr[n+1]이 있을 때, 겹치는 부분이 있으면 빼고 더한다. (빼야할 넓이) = (arr[n].x-arr[n+1].x)*(arr[n].y-arr[n+1].y) */
        for(int i = 0; i < N; i++) {
            int area = 100;
            for(int j = 0; j < i; j++) {
                int maxX = Math.min(sqArr[i].x, sqArr[j].x) + 10;
                int minX = Math.max(sqArr[i].x, sqArr[j].x);
                int maxY = Math.min(sqArr[i].y, sqArr[j].y) + 10;
                int minY = Math.max(sqArr[i].y, sqArr[j].y);
                if(i != j && (Math.min(sqArr[i].x, sqArr[j].x) + 10 > minX || Math.min(sqArr[i].y, sqArr[j].y) + 10 > minY)) {
                    area -= Math.abs((maxX-minX) * (maxY-minY));
                    System.out.println(i+1 + "번째, " + (j+1) + "번째: " +area);
                }
            }
            answer += area;
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}

class Square {
    int x;
    int y;
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/*

public class Main {
    public static void main (String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        boolean map[][] = new boolean[101][101];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    map[j][k] = true;
                }
            }
        }
        int area = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (black[i][j]) area++;
            }
        }
        bw.write(String.valueOf(area));
        bw.flush();
        bw.close();
        br.close();
    }
}

*/