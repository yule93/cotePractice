package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 14719 빗물(단순 탐색)
// 2차원 세계에 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다. 비는 충분히 많이 올 때, 고이는 빗물의 총량은 얼마일까?
// 1초 제한. O(NlogN)의 시간 탐색으로, W의 경우 최악에는 500이므로 O(500log500) = 해봤자 25만도 되지 않는다.
// 144ms
public class boj14719 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int H = Integer.parseInt(st.nextToken()); // 2차원 세계 세로 길이. 1~500
    int W = Integer.parseInt(st.nextToken()); // 2차원 세계 가로 길이. 1~500

    int[] map = new int[W]; // 가로길이만큼 세계가 이뤄짐
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < W; i++) {
      map[i] = Integer.parseInt(st.nextToken());
    }

    // * 세계의 맨 밑 행은 모조리 막혀있다고 본다.
    int answer = 0;
    // ! 자기 자신을 기준으로 왼쪽과 오른쪽을 계속 탐색해서 가장 높은 높이를 구하고, 그 중에서 두 번째로 큰 것과 자신과 차이를 구해 값에 더해준다.
    for (int i = 0; i < W; i++) {
      int left = i;
      for (int l = i; l >= 0; l--) { // 왼쪽에서 가장 큰 높이 구하기
        if (map[l] > map[left])
          left = l;
      }
      int right = i;
      for (int r = i; r < W; r++) { // 오른쪽에서 가장 큰 높이
        if (map[r] > map[right])
          right = r;
      }
      int size = Math.min(map[left], map[right]) - map[i];
      if (size > 0)
        answer += size;
    }

    System.out.println(answer);
  }
}
