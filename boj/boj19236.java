package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 19236 청소년 상어(dfs를 이용한 시뮬레이션(공간뛰어넘기뭐냐고))
// * 맵의 크기가 4x4=16인 이유가 있었음. dfs로 최대 탐색이 16개만 가능하도록 제한
public class boj19236 {
  // ? 반시계 방향으로 돌아가유
  static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
  static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };

  static int answer;
  public static Fish[] fish; // 물고기 정보 저장

  static class Fish {
    int num;
    int x;
    int y;
    int dir;
    int alive; // 0 죽음, 1 살아있음

    Fish(int num, int x, int y, int dir, int alive) {
      this.num = num;
      this.x = x;
      this.y = y;
      this.dir = dir;
      this.alive = alive;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // ? map은 무조건 4x4 크기, 각 칸마다 물고기의 크기와 방향을 저장함
    int[][][] map = new int[4][4][2];
    int[][] fishList = new int[16][3];
    for (int i = 0; i < 4; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        // ? a는 물고기 번호, b는 방향
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken()) - 1;
        map[i][j][0] = a;
        map[i][j][1] = b;
        fishList[a - 1][0] = i;
        fishList[a - 1][1] = j;
        fishList[a - 1][2] = b;
      }
    }

    answer = 0; // ? 상어가 먹을 수 있는 물고기 번호 합의 최대값
    int dist = map[0][0][1];
    dfs(new int[] { 0, 0 }, dist, map, map[0][0][0], fishList);

    System.out.println(answer);
  }

  public static boolean check(int fny, int fnx, int[][][] copyMap) {
    if (fny < 0 || fny >= 4 || fnx < 0 || fnx >= 4)
      return false;
    if(copyMap[fny][fnx][0] == -1 && copyMap[fny][fnx][1] == -1)
      return false;
    
    return true;
  }

  public static void dfs(int[] sharkPos, int dist, int[][][] copyMap, int count, int[][] fishList) {
    // * 1. 물고기 움직여유
    for (int i = 0; i < fishList.length; i++) {
      if (fishList[i][2] == -1)
        continue;
      int foy = fishList[i][0];
      int fox = fishList[i][1];
      int fnd = copyMap[foy][fox][1];
      if (fnd == -1)
        continue;
      int fny = foy + dy[fnd];
      int fnx = fox + dx[fnd];
      if (!check(fny, fnx, copyMap)) {
        // * 움직인 곳에 상어가 있거나 갈 수 없는 곳이라면 방향을 틀어야 함. 그런데도 갈 곳이 계속 없다면 제자리행
        for (int d = 1; d < 8; d++) {
          fnd = (fnd + 1) % 8;
          fny = foy + dy[fnd];
          fnx = fox + dx[fnd];
          if (check(fny, fnx, copyMap))
            break;
        }
        if (fnd == copyMap[foy][fox][1]) {
          // * 회전했음에도 갈 곳이 없다면 제자리
          continue;
        }
      }

      // * 이동한 곳의 물고기와 위치 스와핑
      int nextFNum = copyMap[fny][fnx][0];
      int nextFDist = copyMap[fny][fnx][1];

      copyMap[fny][fnx][0] = copyMap[foy][fox][0];
      copyMap[fny][fnx][1] = copyMap[foy][fox][1];
      copyMap[foy][fox][0] = nextFNum;
      copyMap[foy][fox][1] = nextFDist;
    }

    // * 2. 상어 먹어유
    int sny = sharkPos[0];
    int snx = sharkPos[1];
    for (int m = 0; m < 3; m++) {
      sny += dy[dist] * m;
      snx += dx[dist] * m;

      if (sny < 0 || sny >= 4 || snx < 0 || snx >= 4) {
        // * 상어가 바깥을 벗어나는 경우,
        answer = Math.max(answer, count);
        continue;
      } else if (copyMap[sny][snx][0] == 0 && copyMap[sny][snx][1] == 0) {
        // * 상어가 움직여도 먹을 물고기가 없을 경우,
        answer = Math.max(answer, count);
        continue;
      }

      int nd = copyMap[sny][snx][1];
      fishList[copyMap[sny][snx][0] - 1][2] = -1;
      copyMap[sny][snx][0] = -1;
      copyMap[sny][snx][1] = -1;
      copyMap[sharkPos[0]][sharkPos[1]][0] = 0;
      copyMap[sharkPos[0]][sharkPos[1]][1] = 0;
      dfs(new int[] { sny, snx }, nd, copyMap, count + copyMap[sny][snx][0], fishList);
    }
  }
}
