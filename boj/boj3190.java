package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 3190 뱀(시뮬레이션)
// * ?? 아... 왜 틀렸는지 깨달음 나는 linkedlist에 헤드를 자꾸 넣었다 뺐다 하는 식으로 햇는데 그러다보니 head랑 tail 위치가 바뀌어갖고 tail을 head로 착각한 바람에 이 사단이...
// * 그래도 찾아서 다행...^^
// 128ms
public class boj3190 {
  static int[] dy = {0, 1, 0, -1};
  static int[] dx = {1, 0, -1, 0};
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());    //보드의 크기
    int K = Integer.parseInt(br.readLine()); // 사과의 개수
    
    int[][] map = new int[N][N];
    StringTokenizer st;
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken())-1;
      int x = Integer.parseInt(st.nextToken())-1;
      map[y][x] = 1; // 사과는 1
    }
    
    int L = Integer.parseInt(br.readLine());

    Queue<int[]> moveTime = new LinkedList<>();
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int time = Integer.parseInt(st.nextToken());
      String dir = st.nextToken();
      if (dir.equals("D")) {
        moveTime.add(new int[] { time, 1 });
      } else {
        moveTime.add(new int[] { time, -1 });
      }
    }

    int answer = 0; // 게임이 끝나는 시간
    LinkedList<int[]> snake = new LinkedList<>();
    snake.add(new int[] { 0, 0 });
    map[0][0] = 2;
    int nd = 0;   // 맨 처음에 오른쪽!
    int[] head = {0, 0};
    while (true) {
      answer++;
      /*
      * 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
      * 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
      * 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
       */
      int[] tail = snake.peek();

      int ny = head[0] + dy[nd];
      int nx = head[1] + dx[nd];

      if (ny < 0 || ny >= N || nx < 0 || nx >= N || map[ny][nx] == 2)
        break;

      if (map[ny][nx] == 0) {
        // 아무것도 없을 때
        map[tail[0]][tail[1]] = 0;
        snake.poll();
      }
      head[0] = ny;
      head[1] = nx;
      snake.add(new int[] { ny, nx });
      map[ny][nx] = 2;

      // x초가 끝난 뒤...!
      if (moveTime.size() > 0 && moveTime.peek()[0] == answer) {
        if (moveTime.peek()[1] == 1) {
          nd = (nd + 1) % 4;
        } else {
          nd = (4 + nd - 1) % 4;
        }
        moveTime.poll();
      }
    }

    System.out.println(answer);
  }
}

/*
8
3
5 4
5 8
2 5
6
7 D
11 D
15 D
18 D
19 D
20 D

5
0
5
4 D
8 D
12 D
15 D
20 L

 */