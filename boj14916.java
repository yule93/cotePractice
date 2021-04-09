import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj14916 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int val = Integer.parseInt(br.readLine());
        int remain = 0;
        
        while(val > 0) {
            if(val % 5 == 0) {
                remain += val/5;
                break;
            } else if(val >= 2) {
                val -= 2;
                remain += 1;
            } else {
                remain = -1;
                break;
            }
        }
        System.out.println(remain);
	}
}