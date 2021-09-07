package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ 17135 캐슬디펜스 (dfs, 재귀, 조합)
/*
    아이디어 1: 적이 위에서부터 천천히 내려옴. 만약 N줄의 적이 모두 내려오면 게임 끝(성안으로 적이 들어왔던 안 들어왔던)
    아이디어 2: N+1번째줄부터 궁수가 훑고 올라가면서 적을 차례차례 죽여서 궁수의 행이 -1이 되었을 때, 반복문 종료하고 정답 리턴
*/
public class boj17135 {
    static int N, M, D, answer;
    static int[][] map, copyMap;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       // 행의 수, 3보다 무조건 크다
        M = Integer.parseInt(st.nextToken());       // 열의 수, 15보다 무조건 작다
        D = Integer.parseInt(st.nextToken());       // 궁수의 공격 거리 제한 D
        ArrayList<Integer> archer = new ArrayList<>();      // 궁수의 행 포지션

        map = new int[N+1][M+1];            // 예외처리 넣어주다 힘들고 괴로워서 하나씩 늘림
        copyMap = new int[N+1][M+1];        // map을 카피할 곳.
        for(int col = 0; col < N; col++) {
            st = new StringTokenizer(br.readLine());
            for(int row = 0; row < M; row++) {
                map[col][row] = Integer.parseInt(st.nextToken());
                copyMap[col][row] = map[col][row];
            }
        }
        combination(1, M, 3, archer);
        bw.write(String.valueOf(answer));
        bw.flush();
    }       // end of main

    public static void kill(ArrayList<Integer> list) {
        // 궁수가 공격하는 함수
        int count = 0;
        for(int i = 1; i <= N; i++) {
            boolean[][] visited = new boolean[N+1][M+1];
            for(int j = 0; j < list.size(); j++) {
                int archerCol = list.get(j);
                int curDis = Integer.MAX_VALUE;
                int curRow = Integer.MAX_VALUE;
                int curCol = Integer.MAX_VALUE;
                for(int k = 1; k <= N; k++) {
                    for(int m = 1; m <= M; m++) {
                        if(map[k][m] == 1) {
                            int distance = Math.abs(k - (N+1)) + Math.abs(m - archerCol);     // 거리는 사선이 아니라 칸에서 칸으로 이동하는 횟수
                            if(curDis > distance) {       // 최단 거리 구했을 때,
                                curDis = distance;        // 최단 거리 갱신 및
                                curRow = k;               // 해당 최단 거리의 row 좌표
                                curCol = m;               // 해당 최단 거리의 col 좌표
                            } else {
                                // 일직선상 거리가 같은 최단거리 좌표가 여러 개면 그중에서 가장 왼쪽 좌표를 골라야함!
                                if(curCol > m) {
                                    curRow = k;
                                    curCol = m;
                                }
                            }
                        }
                    }
                }
                if (curDis <= D) {        // 적이 위치한 곳과 궁수와의 거리가 D보다 작을 때,
                    visited[curRow][curCol] = true;     // 화살쏨
                }
            }

            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M; k++) {
                    if (visited[j][k]) {        // 궁수가 쏘아 죽인 곳이 맞다면
                        map[j][k] = 0;      // 0 처리(죽음) 해주고
                        count++;            // (궁수가 죽인 적 숫자)+1
                    }
                }
            }
            for (int j = 1; j <= M; j++) {
                map[N][j] = 0;      // 맨아랫줄 적이 성으로 들어간거라 맨 아랫줄을 0으로 모두 초기화
            }
            for (int j = N; j >= 1; j--) {
                for (int k = 1; k <= M; k++) {
                    map[j][k] = map[j - 1][k];      // 적이 아래로 한칸씩 이동함....
                }
            }
            answer = Math.max(answer, count);
        }
    }       // end of kill

    public static void combination(int start, int col, int r, ArrayList<Integer> list) {
        // 1번행부터(start) 시작해서 M번까지(col) 고를 수 있는 행 중에서 r번(궁수의 수, 3명으로 고정) 골라서 조합 만들어서 list에 궁수의 행 위치를 세 개 저장
        if (r == 0) {
            // 맵 초기화(최다로 킬한 수를 구해야 하니까)
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    map[i][j] = copyMap[i][j];
                }
            }
            kill(list);
            return;
        }
        for (int i = start; i <= col; i++) {
            list.add(i);
            combination(i + 1, col, r - 1, list);
            list.remove(list.size() - 1);       // 사용한 궁수의 위치는 제거....
        }
    }       // end of combination

/* 
        public static int bitCount(int value) {
        int count = 0;
        while (value != 0) {
               value = value & (value - 1);
               count++;

        }
        return count;
    }

    public static void kill(int floor) {
        // N-1행부터 0행까지 올라가면서 적을 처치할 예정
        // 근데 -1행이 되면 적들이 성으로 다 내려가버리고 map이 모두 0이된 것이랑 똑같기 때문에 return;으로 종료해준다.
        if(floor == -1) return;
        
        kill(floor - 1);
    }

    public static void kill() {
        int kill = 0;
        int[][] temp = {{-1, -1}, {-1, -1}, {-1, -1}};
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 3; j++) {
                ex:
                for(int distance = 1; distance <= D; distance++) {
                    int position = archerPos[j];
                    for(int k = 1; k < distance; k++) {
                        if(N - k < 0 || position - distance + k >= M || position - distance + k < 0) continue;      // 예외처리
                        if(copyMap[N - k][position - distance+k] == 1) {
                            temp[i][0] = N - k;
                            temp[i][1] = position - distance + k;
                            continue ex;
                        }
                    }

                    if(N - distance < 0) continue;
                    if(copyMap[N - distance][position] == 1) {
                        temp[i][0] = N - distance;
                        temp[i][1] = position;
                        continue ex;
                    }

                    for(int k = 1; k < distance; k++) {
                        if(N - k < 0 || position - distance + k >= M || position - distance + k < 0) continue;      // 예외처리
                        if(map[N - distance + k][position+k] == 1) {
                            temp[i][0] = N - distance;
                            temp[i][1] = position + k;
                            continue ex;
                        }
                    }
                }       // D까지 공격할 수 있으므로 아쳐의 포지션 잡음
            }       // 아쳐 한 명 한 명 배치

            for(int[] t : temp) {
                if(t[0] != -1) {
                    if(copyMap[t[0]][t[1]] != 0) {
                        copyMap[t[0]][t[1]] = 0;
                        kill++;
                    }
                }
            }
            for (int j = N-1; j >0;  j--) {
				System.arraycopy(copyMap[j-1], 0, copyMap[j], 0, M);
			}
			Arrays.fill(copyMap[0], 0);
        }
        answer = Math.max(kill, answer);
    }

    private static void combination(int cnt, int start) {
		if (cnt ==3) {
			for(int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, copyMap[i].length);
			}
			kill();
			return;
		}
		for (int i = start; i<M; i++) {
			archerPos[cnt]= i; 
			combination(cnt + 1, i + 1);
		}
	}
*/

/*
    static int n,m,d;
	static int dx[] = {0,-1,0};
	static int dy[] = {-1,0,1}; // 좌 상 우 밖에 없음
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = (Integer.parseInt(st.nextToken()));
		m = (Integer.parseInt(st.nextToken()));
		d = (Integer.parseInt(st.nextToken()));
		// n,m은 크기 ,d는 사정거리
		
		int ori[][] =new int[n+1][m];
		// 기본적인 적 위치 setting
		
		for(int i = 0;i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<m; j++) {
				ori[i][j] = (Integer.parseInt(st.nextToken()));
			}
		} // 전달받은 적 위치 기록
		int ans = 0; // 죽인 적 전체 기록하기 위함
		
		for(int i = 0; i<m-2;i++) {
			for(int j = i+1; j<m-1;j++) {
				for(int k = j+1; k<m; k++){ // 궁수 3마리이기 때문에 3중 for문이 훨씬 빠름
					int imsi[][] = new int[n+1][m]; // 시뮬레이션 돌릴때 original 전달 안하고 복사본 전달
					for(int a = 0; a<n; a++) {
						for(int b = 0; b<m; b++) {
							imsi[a][b] = ori[a][b]; // 복사본 값 복사
						}
					}
					ans = Math.max(ans, simul(0,0,i,j,k,imsi)); // 시뮬레이션에 복사본이랑, 궁수 위치 넣음-> 결과 반환(최대값 저장)
				}
			}
		}
		System.out.println(ans); // -> 출력
	}
	public static int simul(int cur, int cnt, int p1, int p2, int p3, int arr[][]) {
		if(cur == n) {
			return cnt;
			// 끝까지 도달했으니 값 return
		}
		// Key idea, 적이 내려오는게 아니라 궁수가 올라간다는 idea 궁수가 있는 줄이랑 그 전줄들은 다 0으로 세팅 해줄 것
		// d는 distance, 궁수들의 좌표: (n - cur, p1)
		Point A[] = new Point[3]; // 궁수 위치 저장
		A[0] = new Point(n-cur,p1);
		A[1] = new Point(n-cur,p2);
		A[2] = new Point(n-cur,p3);
		int x[] = new int[3]; // 궁수가 지정한 적이 될 x,y 좌표
		int y[] = new int[3];
		for(int i = 0; i<3; i++) { // 일단 어디다가 조준할지 모르니 빈 칸에다가 조준
			x[i] = n;
			y[i] = m-1;
		}
		LinkedList<Point> q= new LinkedList<Point>(); // 좌표를 저장하기 위해 Point이용 Point는 A.x A.y있음
		
		for(int v = 0; v<3; v++) {
			boolean visited[][] = new boolean[n+1][m]; // 궁수가 자신이 둘러본 곳을 다시 보지 않기 위해 setting
			q.add(new Point(A[v])); // 궁수의 위치 세팅
			while(q.size()!= 0) { // 일단 더 볼게 있다
				if(dist(A[v],q.get(0))>d)break; // 사거리가 벗어나는 거면 break => 왜냐면 그보다 짧은 사거리는 다 찾아봄
				int px = q.get(0).x; // 현재 x,y좌표
				int py = q.get(0).y;
				q.remove(0); // 지금 있는 head 삭제
				for(int i = 0 ;i<3; i++) {
					int nx = px + dx[i]; // 다음 좌표 x,y (여기서는 3가지만 보는게 뒤로 갈 필요 없어서 dx dy 를 좌, 상, 우 형식으로만 함
					int ny = py + dy[i];
					Point iP = new Point(nx,ny); // 다음 좌표를 point로 만들고
					if(nx<0  || ny <0 || ny >= m|| dist(iP,A[v]) >d) { // 범위가 벗어나거나 사거리 벗어나면 invalid..
						continue;
					}else {
						if(arr[nx][ny] == 1) { // 찾았다 요놈 -> 잡아야 하는 애
							x[v] = nx;
							y[v] = ny; // 중복으로 죽일 수 있기 때문에 한번에 죽이기 위해 저장
							q.clear(); // 더이상 찾아볼 필요 없으니 q.clear(). 그냥 break만하면 for문만 탈출
							break;
						}else {
							if(visited[nx][ny] == false) { // 내가 안찾아본곳이면
								q.add(iP); // 다음에 볼곳으로 setting
								visited[nx][ny] = true; // 봤다고 bfs 설정
							}
						}
					}
				}
			}
			q.clear(); // 무슨 문제 있을떄를 대비해 q clear해서 원소 없게 만듬
		}
		
		
		for(int i =0;i<3; i++) { // 이제 목표로 한 적들을 하나하나 죽인다.
			if(arr[x[i]][y[i]] ==1) { // 적이 있으면 죽이고 cnt ++
				cnt++;
				arr[x[i]][y[i]] = 0;
			}// 이미 다른 궁수가 죽었으면 그냥 넘어가는 것
		}
		for(int i = 0; i<m; i++) {
			arr[n-cur-1][i] = 0; // 지나갔거나 죽였거나 -> 궁수가 죽였으면 끝이고, 아니지만 지나갔어도 없는 것으로 취급 0으로 취금
		}
		return simul(cur+1,cnt,p1,p2,p3,arr); // 다음 것으로 넘어가되 몇번쨰 round인지 전달. round에 따라 최종 stop도 가능하며, 궁수 위치도 조정
	}
	public static int dist(Point A, Point B) { // x,y좌표를 비교해서 거리(사거리) 반환
		return Math.abs(A.x - B.x) + Math.abs(A.y - B.y); 
	}
*/
}
