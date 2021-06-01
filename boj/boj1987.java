package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ 1987: 알파벳, DFS, 그래프
public class boj1987 {
    static int[] dx = {0, 0, -1, 1};       // 상하좌우 순으로 움직이는 좌표
    static int[] dy = {1, -1, 0, 0};
    static boolean[] visited = new boolean[26];       // 알파벳은 A ~ Z로 총 26개이므로 맥스 26번만 말을 움직일 수 있어서 26으로 지정해둠
    static char[][] map;
    static int answer = 0;
    static int R, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());


        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        visited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int count, int i, int j) {
        if(answer < count) answer = count;
        else if (answer == 26) return;

        for(int pos = 0; pos < dx.length; pos++) {
            int nx = i + dx[pos];
            int ny = j + dy[pos];

            if((nx >= 0 && nx < R && ny >= 0 && ny < C) && !visited[map[nx][ny] - 'A']) {
                visited[map[nx][ny] - 'A'] = true;
                dfs(nx, ny, count+1);
                visited[map[nx][ny] - 'A'] = false;
            } 
        }
    }
}
