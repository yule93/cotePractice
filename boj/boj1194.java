package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ 1194 달이 차오른다, 가자. (시뮬레이션)
// 오히려 민식이가 아니라 달이 있는 '1'의 위치에서 시작해서 최단 거리 찾아나가도 되지 않을까...?? 아님 말고
public class boj1194 {

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static boolean[][][] visited;
    static int MAX = 10000000, N, M;
    static Queue<int[]> q;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        q = new LinkedList<int[]>();
        map = new char[N][M];
        visited = new boolean[N][M][64]; // 가로, 세로, 키를 갖고 있는 여부
        for (int i = 0; i < N; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    // 시작위치를 아예 queue에 처음부터 넣는다. 그리고 지나갈 수 있는 자리여야 하므로 .으로 바꿔준다!
                    q.offer(new int[] { i, j, 0 });
                    visited[i][j][0] = true;
                    map[i][j] = '.';
                }
            }
        }

        int count = 0; // 최단거리
        while (q.size() > 0) {
            count++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] nowPos = q.poll();
                for (int j = 0; j < 4; j++) {
                    int ny = nowPos[0] + dy[j];
                    int nx = nowPos[1] + dx[j];
                    int nkey = nowPos[2];

                    if (ny >= N || ny < 0 || nx >= M || nx < 0 || map[ny][nx] == '#' || visited[ny][nx][nkey])
                        continue;
                    if (map[ny][nx] == '1') {
                        System.out.println(count);
                        return;
                    } else if (map[ny][nx] >= 'a' && map[ny][nx] <= 'z') {
                        // key가 위치하는 곳일 때, key를 획득함
                        int ckey = 1 << (map[ny][nx] - 'a');
                        if ((nowPos[2] & ckey) != ckey) {
                            // 획득하지 않은 키이면 비트마스킹으로 더해준다.
                            nkey |= ckey;
                        }
                    } else if (map[ny][nx] >= 'A' && map[ny][nx] <= 'Z') {
                        // 문이 위치하는 곳일 때, 지나가려면 key가 필요!
                        int cdoor = 1 << (map[ny][nx] - 'A');
                        if ((nowPos[2] & cdoor) != cdoor) {
                            // 갖고 있는 키가 아니라면 못 지나감....
                            continue;
                        }
                    }

                    // System.out.println(ny + "행, " + nx + "열: " + map[ny][nx]);
                    q.offer(new int[] { ny, nx, nkey });
                    visited[ny][nx][nowPos[2]] = true;
                }
            }
        }
        // BFS로 찾았는데도 답이 안 나올 경우에는 -1 출력
        System.out.println(-1);
        // System.out.println(bfs());
    }

    public static int bfs() {
        int time = 0;

        while (q.size() > 0) {
            int size = q.size();
            time++;

            for (int i = 0; i < size; ++i) {
                int[] np = q.poll();
                int y = np[0];
                int x = np[1];
                int k = np[2];

                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    int nk = k;

                    if (ny < 0 || ny >= N || nx < 0 || nx >= M)
                        continue;
                    if (visited[ny][nx][k] || map[ny][nx] == '#')
                        continue;

                    if (map[ny][nx] == '1')
                        return time;

                    else if (map[ny][nx] >= 'a' && map[ny][nx] <= 'z') {
                        int ck = 1 << (map[ny][nx] - 'a');
                        if ((k & ck) != ck)
                            nk |= ck;
                    } else if (map[ny][nx] >= 'A' && map[ny][nx] <= 'Z') {
                        int cd = 1 << (map[ny][nx] - 'A');
                        if ((k & cd) != cd)
                            continue;
                    }

                    q.offer(new int[] { ny, nx, nk });
                    visited[ny][nx][k] = true;
                }
            }
        }
        return -1;
    }
}
/*
 * 빈 곳 : 언제나 이동할 수 있다. ('.‘로 표시됨) ---------------------------------------------
 * 벽 : 절대 이동할 수 없다. (‘#’)-------------------------------------------------------
 * 열쇠 : 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. (a - f) ---------------------------
 * 문 : 대응하는 열쇠가 있을 때만 이동할 수 있다.(A - F) ----------------------------------------
 * 민식이의 현재 위치 : 빈 곳이고, 민식이가 현재 서 있는 곳이다.(숫자 0) ---------------------------------
 * 출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. (숫자 1)
 */
