package boj;
import java.awt.Point;
import java.io.*;
import java.util.*;
 
public class boj14502 {
    static int N, M, res = 0, map[][], infectedMap[][];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static Queue<Point> q;
    static ArrayList<Point> virus;

    public static void setupWall(int cnt) {
        // 벽을 세 개 모두 설치했다면
        if(cnt == 3) {
            // 바이러스를 퍼트림.
            spreadVirus();
            return;
        }
        
        for (int i = 0; i < N*M; i++) {
            if(map[i/M][i%M] == 0) {
                map[i/M][i%M] = 1;
                setupWall(cnt+1);
                map[i/M][i%M] = 0;
                
            }
        }
    }
    
    public static void spreadVirus() {
        // 원본 보존을 위해 현재 map을 복사
        infectedMap = new int[N][M];
        for (int i = 0; i < N; i++) 
            System.arraycopy(map[i], 0, infectedMap[i], 0, M);

        q = new LinkedList<>();
        // 바이러스의 좌표들을 하나씩 확인하면서
        for (int i = 0; i < virus.size(); i++) {
            Point v = virus.get(i);
            // Queue에 넣어주고
            q.add(new Point(v.x, v.y));
            // 바이러스를 퍼뜨려보자.
            while(!q.isEmpty()) {
                Point now = q.poll();
                
                for (int d = 0; d < 4; d++) {
                    int xx = now.x + dx[d];
                    int yy = now.y + dy[d];
                    // 범위를 벗어나면 pass
                    if(xx < 0 || yy < 0 || xx >= N || yy >= M) continue;
                    // 벽이거나 이미 감염된 곳이면 pass
                    if(infectedMap[xx][yy] == 1 ||infectedMap[xx][yy] == 3) continue;
                    // 빈 칸이면 바이러스로 감염시키고 Queue에 add
                    // visited대신 감염된 곳은 3으로
                    infectedMap[xx][yy] = 3;
                    q.add(new Point(xx,yy));
                }
            }
        }
        // 바이러스가 모두 퍼졌을 때, 안전 영역의 크기를 count
        int sum = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if(infectedMap[x][y] == 0) sum += 1;
            }
        }
        
        res = Math.max(res, sum);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());    // 세로
        M = Integer.parseInt(st.nextToken());    // 가로
        map = new int[N][M];
        virus = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 바이러스의 위치 저장
                if(map[i][j] == 2) virus.add(new Point(i, j));
            }
        }

        // 재귀로 3개의 벽을 설치해보자.
        setupWall(0);
        System.out.println(res);
    }
}
