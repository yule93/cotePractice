package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// boj 12100 2048(easy)(dfs, 시뮬레이션)
// 맵이 주어졌을 때, 최대 5번을 움직여 만들 수 있는 최대값을 구하기
// 1156ms
public class boj12100 {
  static int N, answer;
  static int[][] map;
  static int[] data;
  static ArrayList<Integer>[] list;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    answer = 0;
    data = new int[5]; // 최대 다섯 번 움직일 수 있다고 해서 순서를 중복 순열로 저장해두는 배열
    list = new ArrayList[N]; // 맵 정보 저장
    for (int i = 0; i < N; i++) {
      list[i] = new ArrayList<>();
    }

    dfs(0, 0);

    System.out.println(answer);
  }

  // * 중복 순열 세우는 함수
  public static void dfs(int depth, int index) {
    if (depth == 5) {
      // * 5개 다 세웠으면
      int[][] copyMap = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          copyMap[i][j] = map[i][j];
        }
      }

      for (int t = 0; t < 5; t++) {
        switch (data[t]) {
          case 0:
            // 위로 이동
            for (int y = 0; y < N; y++) {
              for (int x = 0; x < N; x++) {
                if (copyMap[y][x] != 0) {
                  list[x].add(copyMap[y][x]);
                  copyMap[y][x] = 0;
                }
              }
            }
            for (int i = 0; i < N; i++) {
              int r = 0;
              while (list[i].size() != 0) {
                if (list[i].size() != 1 && list[i].get(0).equals(list[i].get(1))) {
                  copyMap[r++][i] = list[i].get(0) << 1;
                  list[i].remove(0);
                  list[i].remove(0);
                } else {
                  copyMap[r++][i] = list[i].get(0);
                  list[i].remove(0);
                }
              }
            }
            break;
          case 1: // 하
            for (int r = N - 1; r >= 0; r--) {
              for (int c = 0; c < N; c++) {
                if (copyMap[r][c] != 0) {
                  list[c].add(copyMap[r][c]);
                  copyMap[r][c] = 0;
                }
              }
            }

            for (int i = 0; i < N; i++) {
              int r = N - 1;
              while (list[i].size() != 0) {
                if (list[i].size() != 1 && list[i].get(0).equals(list[i].get(1))) {
                  copyMap[r--][i] = list[i].get(0) << 1;
                  list[i].remove(0);
                  list[i].remove(0);
                } else {
                  copyMap[r--][i] = list[i].get(0);
                  list[i].remove(0);
                }
              }
            }
            break;
          case 2: // 좌
            for (int r = 0; r < N; r++) {
              for (int c = 0; c < N; c++) {
                if (copyMap[r][c] != 0) {
                  list[r].add(copyMap[r][c]);
                  copyMap[r][c] = 0;
                }
              }
            }

            for (int i = 0; i < N; i++) {
              int c = 0;
              while (list[i].size() != 0) {
                if (list[i].size() != 1 && list[i].get(0).equals(list[i].get(1))) {
                  copyMap[i][c++] = list[i].get(0) << 1;
                  list[i].remove(0);
                  list[i].remove(0);
                } else {
                  copyMap[i][c++] = list[i].get(0);
                  list[i].remove(0);
                }
              }
            }
            break;
          case 3: // 우
            for (int r = 0; r < N; r++) {
              for (int c = N - 1; c >= 0; c--) {
                if (copyMap[r][c] != 0) {
                  list[r].add(copyMap[r][c]);
                  copyMap[r][c] = 0;
                }
              }
            }

            for (int i = 0; i < N; i++) {
              int c = N - 1;
              while (list[i].size() != 0) {
                if (list[i].size() != 1 && list[i].get(0).equals(list[i].get(1))) {
                  copyMap[i][c--] = list[i].get(0) << 1;
                  list[i].remove(0);
                  list[i].remove(0);
                } else {
                  copyMap[i][c--] = list[i].get(0);
                  list[i].remove(0);
                }
              }
            }
            break;
        } // end of switch
      }

      int max = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          max = Math.max(max, copyMap[i][j]);
        }
      }

      answer = Math.max(answer, max);
      return;
    } // ! depth가 5가 되었을 때 진행하는 로직 끝

    for (int i = 0; i < 4; i++) {
      if (data[depth] == 0) {
        data[depth] = i;
        dfs(depth + 1, index);
        data[depth] = 0;
      }
    }

  } // ! dfs 함수 끝 부분
}
