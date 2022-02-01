package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// boj 23290 마법사 상어와 복제 (브루트포스 총망라, 시뮬레이션)
// * 상어의 이동은 한 횟수에 총 세 번으로, 가장 물고기를 많이 먹을 수 있는 방향으로 이동해야 한다.
// 476ms
public class boj23290 {
  static int M, S;
  static Fish shark;
  static ArrayList<Integer>[][] resetFish;
  static Deque<Integer>[][] smell;
  static ArrayList<Fish> fishList;
  static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
  static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
  static int[] sd = { -1, 2, 0, 6, 4 };

  static class Fish {
    int r, c, d;

    Fish(int r, int c, int d) {
      this.r = r;
      this.c = c;
      this.d = d;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    M = Integer.parseInt(st.nextToken()); // 처음 물고기의 수
    S = Integer.parseInt(st.nextToken()); // 상어 마법 연습 횟수

    resetFish = new ArrayList[4][4]; // 물고기 저장 리스트
    smell = new LinkedList[4][4]; // 잡아먹힌 물고기가 남긴 냄새 리스트
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        resetFish[r][c] = new ArrayList<Integer>();
        smell[r][c] = new LinkedList<Integer>();
      }
    }

    for (int m = 0; m < M; m++) {
      st = new StringTokenizer(br.readLine());
      int fy = Integer.parseInt(st.nextToken()) - 1;
      int fx = Integer.parseInt(st.nextToken()) - 1;
      int s = Integer.parseInt(st.nextToken()) - 1;

      resetFish[fy][fx].add(s);
    }

    st = new StringTokenizer(br.readLine());
    int sy = Integer.parseInt(st.nextToken()) - 1;
    int sx = Integer.parseInt(st.nextToken()) - 1;
    shark = new Fish(sy, sx, -1);

    fishList = new ArrayList<Fish>();
    int result = 0;
    for (int s = 0; s < S; s++) {
      copyFish();
      moveFish();
      moveShark(s);
      removeSmell(s);
      completeCopy(); // * dfs 식으로 상어가 먹은 물고기를 다 계산한 후, 초기화
    }

    result = countFish();
    System.out.println(result);
  }

  //
  private static void copyFish() {
    fishList.clear();
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        for (int d : resetFish[r][c]) {
          fishList.add(new Fish(r, c, d));
        }
      }
    }

  }

  private static void moveFish() {
    ArrayList<Integer>[][] tempGrid = new ArrayList[4][4];
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        tempGrid[r][c] = new ArrayList<Integer>();
      }
    }

    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        for (int d : resetFish[r][c]) {
          int nr = r, nc = c;

          boolean isMoved = false;
          for (int i = 0; i < 8; i++) {
            int nd = ((d - i) + 8) % 8;
            nr = r + dy[nd];
            nc = c + dx[nd];

            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4)
              continue;
            if (smell[nr][nc].size() != 0)
              continue;
            if (nr == shark.r && nc == shark.c)
              continue;

            tempGrid[nr][nc].add(nd);
            isMoved = true;
            break;
          }
          if (!isMoved)
            tempGrid[r][c].add(d);
        }
      }
    }
    resetFish = tempGrid;
  }

  // ! 상어가 물고기를 최대로 잡아먹을 수 있는 경우를 구하는 3번 이동 함수
  public static void moveShark(int time) {
    int move = 0, maxMove = 0;
    int fishNum = 0, maxFishNum = 0;
    int nr = shark.r, nc = shark.c;
    boolean isThree = false;

    boolean[][] visited;
    for (int i = 1; i < 5; i++) {
      visited = new boolean[4][4];

      nr += dy[sd[i]];
      nc += dx[sd[i]];
      if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
        nr = shark.r;
        nc = shark.c;
        continue;
      }

      move = i;
      visited[nr][nc] = true;
      fishNum = resetFish[nr][nc].size();
      for (int j = 1; j < 5; j++) {
        nr += dy[sd[j]];
        nc += dx[sd[j]];
        if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
          nr -= dy[sd[j]];
          nc -= dx[sd[j]];
          continue;
        }

        move = (move * 10) + j;
        if (!visited[nr][nc])
          fishNum += resetFish[nr][nc].size();
        for (int k = 1; k < 5; k++) {
          nr += dy[sd[k]];
          nc += dx[sd[k]];
          if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
            nr -= dy[sd[k]];
            nc -= dx[sd[k]];
            continue;
          }

          if (!visited[nr][nc])
            fishNum += resetFish[nr][nc].size();
          int finalFishNum = fishNum;
          if (!visited[nr][nc])
            fishNum -= resetFish[nr][nc].size();

          nr -= dy[sd[k]];
          nc -= dx[sd[k]];

          if (isThree && maxFishNum >= finalFishNum)
            continue;
          move = (move * 10) + k;
          maxMove = move;
          maxFishNum = finalFishNum;
          isThree = true;
          move /= 10;
        }

        if (!visited[nr][nc])
          fishNum -= resetFish[nr][nc].size();
        visited[nr][nc] = false;
        nr -= dy[sd[j]];
        nc -= dx[sd[j]];
        move /= 10;
      }

      visited[nr][nc] = false;
      nr = shark.r;
      nc = shark.c;
    }

    nr = shark.r;
    nc = shark.c;
    int idx = 2;
    while (idx >= 0) {
      int base = (int) Math.pow(10, idx--);
      int m = maxMove / base;
      maxMove %= base;

      nr += dy[sd[m]];
      nc += dx[sd[m]];

      if (resetFish[nr][nc].size() == 0)
        continue;
      smell[nr][nc].add(time + 2);
      resetFish[nr][nc].clear();
    }
    shark.r = nr;
    shark.c = nc;
  }

  // * 2년 지나서 냄새 사라지고 물고기가 이동 가능해짐
  private static void removeSmell(int time) {
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        while (!smell[r][c].isEmpty()) {
          int s = smell[r][c].poll();
          if (time - s < 0) {
            smell[r][c].addFirst(s);
            break;
          }
        }
      }
    }
  }

  // * 알 낳는 함수
  private static void completeCopy() {
    for (Fish f : fishList) {
      resetFish[f.r][f.c].add(f.d);
    }
  }

  private static int countFish() {
    int count = 0;
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        count += resetFish[r][c].size();
      }
    }
    return count;
  }
}
