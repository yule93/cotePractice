package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea5644 {
    static int[] dx = {0, 0, 1, 0, -1};      // X 상 우 하 좌
    static int[] dy = {0, -1, 0, 1, 0};      // X 상 우 하 좌
    static int answer, A, M, px1, py1, px2, py2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());        // 테케 수
        int[] person1, person2;

        // 무선 충전기로부터의 거리 D = (x2-x1)+(y2-y1)이면 충전이 가능!
        // 해당 식을 기억해두면 마름모꼴 영역 처리할 때 좋음!
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());       // 총 이동시간 M
            A = Integer.parseInt(st.nextToken());       // BC(충전기)의 개수
            int answer = 0;

            person1 = new int[M];
            person2 = new int[M];
            px1 = 1; py1 = 1;       // 1번 사람의 시작 좌표
            px2 = 10; py2 = 10;     // 2번 사람의 시작 좌표

            st = new StringTokenizer(br.readLine());        // 사용자 A의 이동 정보. 0,0,에서 시작
            for(int i = 0; i < M; i++) person1[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());        // 사용자 B의 이동 정보 M-1, M-1에서 시작
            for(int i = 0; i < M; i++) person2[i] = Integer.parseInt(st.nextToken());

            int[][] AP = new int[A][4];         // 1. y좌표 2. x좌표 3. 사거리 4. 성능(충전량)
            
            for(int ap = 0; ap < A; ap++) {
                st = new StringTokenizer(br.readLine());
                for(int i = 0; i < 4; i++) {
                    AP[ap][i] = Integer.parseInt(st.nextToken());
                }
            }

            charge(AP, 0);     // 처음부터 충전이 가능해서 첫 시작도 재준다!
            for(int i = 0; i < M; i++) {
                px1 += dx[person1[i]];
                py1 += dy[person1[i]];
                px2 += dx[person2[i]];
                py2 += dy[person2[i]];
                charge(AP, i+1);
            }       // 시뮬레이션 종료!
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
            answer = 0;
        }       // end of tc

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void charge(int[][] apInfo, int depth) {
        ArrayList<Integer> chargePerson1 = new ArrayList<>();
        ArrayList<Integer> chargePerson2 = new ArrayList<>();
        for(int i = 0; i < A; i++) {
            // 바로 충전량을 저장하지 않는 이유: 두 사람이 한 충전기에 겹치면 충전량을 절반씩 나눠서 쓰게 됨.
            // 따라서 최대값을 찾기 위해서는 무선 통신기를 각자 쓰는게 좋으니까 일단 범위 내에 들어갈 때가 어느 좌표인지 저장만 해둠! 따로 쓸 수 있는지 확인하기 위해서!
            if(Math.abs(py1 - apInfo[i][1]) + Math.abs(px1 - apInfo[i][0]) <= apInfo[i][2]) chargePerson1.add(i);
            if(Math.abs(py2 - apInfo[i][1]) + Math.abs(px2 - apInfo[i][0]) <= apInfo[i][2]) chargePerson2.add(i);
        }

        //System.out.println();
        //System.out.println(depth + ". 사람 1의 x좌표, y좌표 " + px1 + " " + py1 + ", 사람 2의 x좌표, y좌표 " + px2 + " " + py2);
        //System.out.println(depth + ". 사람 1이 충전한 횟수: " + chargePerson1.size() + ", 사람 2가 충전한 횟수: " + chargePerson2.size());

        int sizeA = chargePerson1.size();
		int sizeB = chargePerson2.size();
		int p1 = 0, p2 = 0;
		int max = 0;
		
		if(sizeA == 0 && sizeB == 0) {
            // 둘 다 충전 못 했을 경우
			return;
		} else if(sizeA == 0) {
            // 첫 번째 사람만 충전했을 경우
			for(int a : chargePerson1) {
				p1 = apInfo[a][3] > p1 ? apInfo[a][3] : p1;
			}
		} else if(sizeB == 0) {
            // 두 번째 사람만 충전했을 경우
			for(int b : chargePerson2) {
				p2 = apInfo[b][3] > p2 ? apInfo[b][3] : p2;
			}
		} else {
			for(int a : chargePerson1) {
                for(int b : chargePerson2) {
                    int sum = 0;
                    if(a == b) {
                        // 두 사람이 같은 충전기를 사용할 때,
                        sum = apInfo[b][3];     // 정확히는 (사람1이 가진 충전량) + (사람2가 가진 충전량)인데 두 사람이 정확하게 반으로 나눠가져서 별도의 처리 없이 넣어줌
                        if(sum > max) {
                            p1 = p2 = sum/2;
                            max = sum;
                        }
                    } else {
                        // 두 사람이 다른 충전기를 사용할 때,
                        sum = apInfo[a][3] + apInfo[b][3];
                        if(sum > max) {
                            p1 = apInfo[a][3];
                            p2 = apInfo[b][3];
                            max = sum;
                        }
                    }
                }
            }
		}
		answer += p1 + p2;
        // System.out.println("p1: "+ p1 + ", p2: " + p2 + ", 답: " + answer);
    }
}
