package boj;
import java.util.*;
import java.io.*;

public class boj2667 {
    static int[] dx = {0, 0, -1, 1};    // 상하좌우 순 
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<pos> q = new LinkedList<>();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int cnt, mark = 1;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; i++) {
                if(map[i][j] == 1) {
                    cnt = 1;
                    mark++;
                    q.add(new pos(i, j));
                    map[i][j] = mark;

                    while(!q.isEmpty()) {
                        pos now = q.poll();

                        for(int k = 0; k < 4; k++) {        // 상하좌우 움직이면서 주거지(1)인 부분 찾기
                            int xPos = now.x + dx[k];
                            int yPos = now.y + dy[k];

                            if(xPos < 0 || yPos < 0 || xPos >= N ||yPos >= N) continue;
                            if(map[xPos][yPos] != 1) continue;

                            map[xPos][yPos] = mark;
                            cnt++;
                            q.add(new pos(xPos, yPos));
                        }
                    }

                    arr.add(cnt);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(mark);

        for(int i = 0; i < mark; i++) {
            sb.append("\n"+arr.get(i));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static public class pos {
        int x, y;

        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
