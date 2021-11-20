package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// BOJ 17136 색종이 붙이기 (완탐+백트래킹)
// 280ms
public class boj17136 {

  static int[] dx = { 1, 0, -1, 0 }; // 우하좌상 순으로 돌며 정사각형인지 체크(달팽이껍질처럼동그랗게안으로들어가기)
  static int[] dy = { 0, 1, 0, -1 };
  static int[] paperUnit = { 0, 5, 5, 5, 5, 5 }; // 0x0, 1x1, 2x2, 3x3, 4x4, 5x5 크기의 색종이
  static int[][] paperMap;
  static int answer;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    paperMap = new int[10][10];
    boolean noOne = true;
    for (int i = 0; i < 10; i++) {
      String str = br.readLine().replaceAll(" ", "");
      for (int j = 0; j < 10; j++) {
        paperMap[i][j] = str.charAt(j) - '0';
        if (paperMap[i][j] == 1)
          noOne = false;
      }
    }

    // 만약 모든 종이가 0이라면 굳이 완탐할 필요 없이 0 출력 후 종료
    if (noOne) {
      System.out.println(0);
      return;
    }

    answer = 1_000_000;
    dfs(0, 0, 0);

    // 1이 남아있는데 정사각형 색종이로 덮지 못하면 -1 리턴을 위해 삼중연산자 사용
    System.out.println(answer == 1_000_000 ? -1 : answer);
  }

  public static void dfs(int x, int y, int count) {
    if (x == 9 && y == 10) {
      answer = Math.min(answer, count);
      return;
    }
    if (y == 10) {
      dfs(x + 1, 0, count);
      return;
    }
    if (count >= answer)
      return;

    if (paperMap[x][y] == 1) {
      for (int j = 5; j >= 1; j--) {
        if (isAttach(x, y, j) && paperUnit[j] > 0) {
          attach(x, y, j);
          paperUnit[j]--;
          dfs(x, y + 1, count + 1);
          detach(x, y, j);
          paperUnit[j]++;
        }
      }
    } else {
      dfs(x, y + 1, count);
    }
  }
  
  //x, y위치에서 부터 size만큼의 색종이를 붙이는 과정.
    public static void attach(int x, int y, int size) {
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                paperMap[i][j] = 2; //색종이 붙였음을 표시
            }
        }
    }
    
    //x, y위치에서 부터 size만큼의 색종이를 떼는 과정.
    public static void detach(int x, int y, int size) {
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                paperMap[i][j] = 1; 
            }
        }
    }
    
    //x, y위치로 부터 size만큼의 색종이를 붙일 수 있는지 확인하는 과정.
    public static boolean isAttach(int x, int y, int size) {
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(i >= 10 || j >= 10) return false;
                if(paperMap[i][j] != 1) return false; 
            }
        }
        return true;
    }
}
