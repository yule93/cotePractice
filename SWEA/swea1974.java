package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 아이디어:
 *      * 스도쿠는 한 줄, 혹은 3x3 칸에 1~9까지 숫자가 모두 들어가야 한다.
 *  1) 가로, 세로 한 줄, 3x3칸으로 들어가는 dfs 혹은 완탐을 만들어 탐색한다.
 *  2) 해당 탐색의 결과가 1~9까지의 합, 즉 45가 나오지 않는다면 틀린 것이므로 break하고 탈출해서 false 반환
 *  3) 끝까지 간다면 true
 *  4) 정 아니면 이거 걍 3x3가 9개 있으니까 전체 값 더해서 45*9가 나오지 않는다면 false 처음부터 반환하고 그 뒤에 탐색해도 될듯 싶은디.... 근데 아닐 경우도 있으니 함 탐색 고
 */
public class swea1974 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder("");
        StringTokenizer st;

        int[][] map = new int[9][9];
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            int answer = 1;
            for(int i = 0; i < 9; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 9; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < 9; i++) {
				int rsum = 0;
				int csum = 0;
				for (int j = 0; j < 9; j++) {
					rsum += map[i][j];
					csum += map[j][i];
				}
				if (rsum != 45 || csum != 45) {
					answer = 0;
					break;
				}
			}
			for (int i = 0; i < 9; i+=3) {
				for (int j = 0; j < 9; j+=3) {
					int sum = 0;
					for (int x = 0; x < 3; x++)
						for (int y = 0; y < 3; y++)
							sum += map[i+x][j+y];
					if (sum != 45) {
						answer = 0;
						break;
					}
				}
				if (answer == 0) break;					
			}
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }       // end of main

}


