package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
    아이디어:
        * 달팽이랑 유사한 문제가 아닐지...?
    1) 입력 받아서 숫자를 저장하는 배열, 방문 체크하는 배열 생성
    2) 처음에는 0x0에서 시작하고, 1x1, 2x2... (세로랑 가로 중 짧은 길이/2)x(세로랑 가로 중 짧은 길이/2)까지 시작배열 위치를 옮겨가며 회전을 시킴
    3) ixi 위치 배열에서 시작해서 아래로 쭉 가다 오른쪽으로 1번 회전... 오른쪽으로 쭉 가다 위쪽으로 2번 회전...
    위쪽으로 쭉 가다 왼쪽으로 3번 회전... 왼쪽으로 쭉 가다 아래쪽 방향으로 4번째 회전이 일어나는 곳에서 break;를 걸어서 빠져나옴
    즉 count로 회전 횟수를 세다가 4가 됐을 때 반복문 빠져나옴
    4) (세로랑 가로 중 짧은 길이/2)번만큼 (3)을 반복
    5) 답 나올듯...?
*/
public class boj16926 {
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder("");

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 세로 길이
        int M = Integer.parseInt(st.nextToken());       // 가로 길이
        int R = Integer.parseInt(st.nextToken());       // 회전 횟수
        int[][] map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());       // 입력받은 값들로 2차원 배열 초기화
            }
        }

        int min = Math.min(N, M)/2;           // 둘 중 적은 숫자의 1/2번만 큰 회전을 하기 위해서
        for(int rot = 0; rot < R; rot++) {
            for(int i = 0; i < min; i++) {
                int count = 0;
                int nx = i, ny = i;
                int temp = map[nx][ny];
                while(count < 4) {          // 3번만 회전하기 위해서
                    int cx = nx + dx[count], cy = ny + dy[count];
					if(cx < i || cy < i || cx >= N-i || cy >= M-i) {
                        // dx[count]와 dy[count]를 더했을 때, 시작 위치인 i랑 끝 위치인 N-i or M-i를 넘어서면 count++해줘서 다른 방향으로 전환시켜줌
                        count++;
					} else {
                        map[nx][ny] = map[cx][cy];
					    nx = cx;
					    ny = cy;
                    }
                }
                map[nx + 1][ny] = temp;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j <M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

                    // if(count % 2 == 0) {    // 상하로 움직일 떄,
                    //     for(int j = 0; j < N - i; j++) {
                    //         int val = map[ny][nx];
                    //         ny += dy[count];
                    //         if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                    //         map[ny][nx] = temp;
                    //         temp = val;
                    //     }
                    // } else {
                    //     for(int j = 0; j < M - i; j++) {        // 좌우로 움직일 때,
                    //         int val = map[ny][nx];
                    //         nx += dx[count];
                    //         if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                    //         map[ny][nx] = temp;
                    //         temp = val;
                    //     }
                    // }
                    // count++;
                    // for(int k = 0; k < N; k++) {
                    //     for(int j = 0; j <M; j++) {
                    //         sb.append(map[k][j]).append(" ");
                    //     }
                    //     sb.append("\n");
                    // }
                    // sb.append("------------\n");