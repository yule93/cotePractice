package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ :: 17144 미세먼지안녕!
public class boj17144 {
    private static int R, C, T;
    private static int[][] map, nmap;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } //// input
 
        // 1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
        for (int t = 0; t < T; t++) {
            spreadDust();
        }
        
        int ans=0;
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(map[i][j]>0) {
                    ans+=map[i][j];
                }
            }
        }
        
        System.out.println(ans);
 
    }
 
    public static void spreadDust() {
        ArrayList<pair> airCleanr = new ArrayList<>();
        nmap = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 먼지인 경우
                if (map[i][j] > 0) {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        // 맵 범위를 벗어나는 exception 처리
                        if (ny < 0 || ny > R - 1 || nx < 0 || nx > C - 1 || map[ny][nx] == -1)
                            continue;
 
                        nmap[ny][nx] += map[i][j] / 5;
                        cnt++;
                    }
                    nmap[i][j] += map[i][j] - (map[i][j] / 5) * cnt;
                }
                // 공기청정기 위치.
                else if (map[i][j] == -1) {
                    nmap[i][j] = -1;
                    airCleanr.add(new pair(i, j));
                }
            }
        }
 
        for (int i = 0; i < R; i++) {
            map[i] = Arrays.copyOf(nmap[i], C);
        }
 
        cleanAir(airCleanr.get(0), airCleanr.get(1));
 
    }
 
    public static void cleanAir(pair up, pair down) {
        // up : 반시계방향
        // down : 시계방향
 
        for (int i = 0; i < C - 1; i++) {
            if (nmap[up.y][i] == -1 || nmap[down.y][i] == -1) {
                map[up.y][i + 1] = 0;
                map[down.y][i + 1] = 0;
 
            } else {
                map[up.y][i + 1] = nmap[up.y][i];
                map[down.y][i + 1] = nmap[down.y][i];
            }
 
            map[0][i] = nmap[0][i + 1];
            map[R - 1][i] = nmap[R - 1][i + 1];
        }
 
        for (int j = 0; j < R - 1; j++) {
 
            if (j < up.y) {
                map[j + 1][0] = nmap[j][0]; // 위로
                map[j][C - 1] = nmap[j + 1][C - 1]; // 아래로
 
            } else if (j >= down.y) {
                map[j + 1][C - 1] = nmap[j][C - 1]; // 아래
                map[j][0] = nmap[j + 1][0]; // 위로
            }
        }
        map[up.y][up.x] = -1;
        map[down.y][down.x] = -1;
 
    }
 
    static class pair {
        int y;
        int x;
 
        public pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
 
    }
 
}