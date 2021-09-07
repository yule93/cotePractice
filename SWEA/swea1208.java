package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class swea1208 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] array;
        int answer = 0;
        boolean flag = false;        // 모든 박스의 높이가 같은지 확인하는

        int TC = 10;

        for(int tc = 1; tc <= TC; tc++) {
            int dump = Integer.parseInt(br.readLine());
            String[] box = br.readLine().split(" ");
            int len = box.length;
            array = new int[len];

            for(int i = 0; i < len; i++) {
                array[i] = Integer.parseInt(box[i]);
                
            }
            Arrays.sort(array);
            for(int i = 0; i < dump; i++) {
                array[0] += 1;
                array[len - 1] -= 1;
                Arrays.sort(array);
            }
            answer = array[len-1] - array[0];
            bw.write(String.valueOf("#"+tc+" " + answer + "\n"));
        }       // end of for
        bw.flush();
        bw.close();
        br.close();
    }       // end of main
}           // end of class
