package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// boj 17140 이차원 배열과 연산(찐 시뮬레이션)
// 228ms
public class boj17140 {
  static class Node implements Comparable<Node> {
    int no, count;

    Node(int no, int count) {
      this.no = no;
      this.count = count;
    }

    public int compareTo(Node o) {
      if (this.count == o.count)
        return this.no - o.no;
      return this.count - o.count;
    }
  }

  static int r, c, k;
  static int rowNum = 3, colNum = 3;
  static int[][] A = new int[101][101];

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    // ? A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    for (int i = 1; i <= 3; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 1; j <= 3; j++)
        A[i][j] = Integer.parseInt(st.nextToken());
    }

    System.out.println(solve());
  }

  public static int solve() {
    for (int count = 0; count <= 100; count++) {
      if (A[r][c] == k)
        return count;
      if (rowNum >= colNum) {
        // * R 연산
        int newColNum = 0;
        for (int i = 1; i <= rowNum; i++) {
          Map<Integer, Integer> sortMap = new HashMap<>();
          List<Node> list = new ArrayList<>();
          for (int j = 1; j <= colNum; j++) {
            if (A[i][j] == 0)
              continue;
            if (!sortMap.containsKey(A[i][j]))
              sortMap.put(A[i][j], 1);
            else
              sortMap.replace(A[i][j], sortMap.get(A[i][j]) + 1);
            A[i][j] = 0;
          }

          for (int key : sortMap.keySet()) {
            list.add(new Node(key, sortMap.get(key)));
          }
          newColNum = Math.max(newColNum, list.size() * 2);
          Collections.sort(list);

          for (int j = 0, index = 1; j < list.size(); j++, index += 2) {
            if (index > 100)
              break;
            A[i][index] = list.get(j).no;
            A[i][index + 1] = list.get(j).count;
          }
        }

        colNum = newColNum;

      } else {
        // * C 연산
        int newRowNum = 0;
        for (int i = 1; i <= colNum; i++) {
          Map<Integer, Integer> sortMap = new HashMap<>();
          List<Node> list = new ArrayList<>();
          for (int j = 1; j <= rowNum; j++) {
            if (A[j][i] == 0)
              continue;
            if (!sortMap.containsKey(A[j][i]))
              sortMap.put(A[j][i], 1);
            else
              sortMap.replace(A[j][i], sortMap.get(A[j][i]) + 1);
            A[j][i] = 0;
          }

          for (int key : sortMap.keySet()) {
            list.add(new Node(key, sortMap.get(key)));
          }
          newRowNum = Math.max(newRowNum, list.size() * 2);
          Collections.sort(list);

          // * 늘어난 수만큼 상하 인덱스 늘려주기
          for (int j = 0, index = 1; j < list.size(); j++, index += 2) {
            if (index > 100)
              break;
            A[index][i] = list.get(j).no;
            A[index + 1][i] = list.get(j).count;
          }
        }

        rowNum = newRowNum;
      }
    }

    return -1;
  }
}
