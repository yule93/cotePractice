package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj16236 {

    static int N;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 맵의 크기
        int[] babyShark = new int[3];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine().replaceAll(" ", "");
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 9) {
                    babyShark = new int[] { i, j };
                    map[i][j] = 0;
                }
            }
        }

        int babySharkSize = 2;
        int eaten = 0;
        int move = 0;   // 총 움직인 거리

        while (true) {
            // 더 이상 먹을 게 없을 때까지 탐색
            // 이 람다식 잘 살펴보고 하... 담번엔 꼭 생각해내서 쓰기.... 아무리 봐도 아기상어랑 비슷한 문제였는데 걍 내 머리가 빡추여서 못 풀었네 ㅆㅃ
            // 물론 오리가 더 어렵긴 했다 시바 그렇게 어렵게 나올줄은
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
                o1[2] != o2[2] ? Integer.compare(o1[2], o2[2])
                :(o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
            );
            
            boolean[][] visited = new boolean[N][N];

            pq.add(new int[] { babyShark[0], babyShark[1], 0 });
            visited[babyShark[0]][babyShark[1]] = true;
            
            boolean check = false;  // 아기 상어가 밥 먹었는지 아닌지 체크
            while (pq.size() > 0) {
                babyShark = pq.poll();

                if (map[babyShark[0]][babyShark[1]] != 0 && map[babyShark[0]][babyShark[1]] < babySharkSize) {
                    // 작아야 잡아먹을 수 있는 물고기임!
                    map[babyShark[0]][babyShark[1]] = 0;    // 잡아먹음
                    eaten++;
                    move += babyShark[2];
                    check = true;
                    break;
                }
                
                for (int i = 0; i < 4; i++) {
                    // 상어 이동 시작
                    int ny = babyShark[0] + dy[i];
                    int nx = babyShark[1] + dx[i];

                    if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx] || map[ny][nx] > babySharkSize)
                        continue;
                    
                    pq.add(new int[] { ny, nx, babyShark[2] + 1 });
                    visited[ny][nx] = true;
                }
            }
            
            if (!check)
                break;
            
            if (babySharkSize == eaten) {
                // 크기만큼 먹었다면 성장
                babySharkSize++;
                eaten = 0;
            }
        }

        System.out.println(move);
    }
    
}
