package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// boj 3108 로고 (DFS, 시뮬레이션)
// 이어져있는 직사각형인지, 안에 그려진 직사각형인지 판단하는 게 필요! 이어져있으면 PU과 PD를 안 해도 되지만 내포된 직사각형이면 해야함.
// 즉, 시뮬레이션도 아니고 그냥... dfs로 끝까지 파고드는 문제
// 204ms 
public class boj3108 {

  static boolean[] check;
  static ArrayList<int[]> rectangleList;
  static int N;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine()); // 사각형의 개수
    int answer = 0;
    check = new boolean[N];

    // 사각형 리스트 저장
    rectangleList = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());

      if ((x1 == 0 && (y1 <= 0 && y2 >= 0)) || (x2 == 0 && (y1 <= 0 && y2 >= 0)))
        answer = -1;
      rectangleList.add(new int[] { x1, y1, x2, y2 });
    }

    // DFS
    for (int i = 0; i < N; i++) {
      if (!check[i]) {
        check[i] = true;
        dfs(i);
        answer += 1;
      }
    }

    System.out.println(answer);
  }

  public static void dfs(int index) {
    for (int i = 0; i < N; i++) {
      if (!check[i] && interSector(rectangleList.get(i), rectangleList.get(index))) {
        check[i] = true;
        dfs(i);
      }
    }
  }

  // 다음 사각형이 한 선으로 이어진 사각형인지, 아니면 내포된 사각형인지 판별하기 위해서 조건문 실행
  public static boolean interSector(int[] now, int[] next) {
    int nowLeft, nowRight, nowTop, nowBottom;
    int nextLeft, nextRight, nextTop, nextBottom;

    // find a left, right
    if (now[0] < now[2]) {
      nowLeft = now[0];
      nowRight = now[2];
    } else {
      nowLeft = now[2];
      nowRight = now[0];
    }

    // find a top, bottom
    if (now[1] < now[3]) {
      nowBottom = now[1];
      nowTop = now[3];
    } else {
      nowBottom = now[3];
      nowTop = now[1];
    }

    // find b left, right
    if (next[0] < next[2]) {
      nextLeft = next[0];
      nextRight = next[2];
    } else {
      nextLeft = next[2];
      nextRight = next[0];
    }

    // find b top, bottom
    if (next[1] < next[3]) {
      nextBottom = next[1];
      nextTop = next[3];
    } else {
      nextBottom = next[3];
      nextTop = next[1];
    }

    if (nowLeft > nextRight || nowRight < nextLeft || nowTop < nextBottom || nowBottom > nextTop ||
        (nowLeft < nextLeft && nowRight > nextRight && nowTop > nextTop && nowBottom < nextBottom) || // a가 b를 내포
        (nextLeft < nowLeft && nextRight > nowRight && nextTop > nowTop && nextBottom < nowBottom)) { // b가 a를 내포
      return false;
    } else {
      return true;
    }
  }
}
