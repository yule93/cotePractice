package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 *  아이디어
 * 
 * 1. 일단 주어진 맵의 시작좌표에서 이동할 수 있는 부분으로 사면 이동하는 DFS or BFS. 이동할 때마다 카운트를 +1 해준다.
 * 2. 이 때, 한 번 지나간 알파벳을 저장하기 위해서 0~25의 char boolean 배열을 만들어준다.
 * 알파벳은 대문자만 주어지기 때문에 -'A'를 사용하면 Character의 숫자를 구할 수 있다. ex) A는 0, C는 2 ....
 * 3. 따라서 해당 맵에서 읽어온 알파벳의 순번의 boolean 배열이 만약 방문(true) 처리가 되어있다면 이미 방문한 곳이므로 다른 곳으로 가거나 멈춰야 함.
 * 멈추는 기준은 사방으로 이동하는(4번 반복하는) 반복문을 다 돌았을 때 만약 움직일 곳이 없다면 거기서 정답 구하기.
 * 혹은 알파벳의 끝자리인 26번째(Z)까지 다 갔을 때.
*/

// BOJ 1987: 알파벳, DFS, 그래프, 백트래킹
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
        dfs(1, 0, 0);

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
                dfs(count+1, nx, ny);
                visited[map[nx][ny] - 'A'] = false;
            } 
        }
    }
}
