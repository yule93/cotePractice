package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// boj 16235 나무 재테크(시뮬레이션, bfs, 우선순위 큐)
// * 어린 나무부터 성장해야 한다.
// 1220ms
public class boj16235 {
  static int N, M, K, food[][], add[][];
  // ? 인접칸은 8방향
  static int[] dy = { 1, -1, 0, 0, 1, -1, 1, -1 };
  static int[] dx = { 0, 0, 1, -1, 1, 1, -1, -1 };

  // ? 어린 나무부터 양분 먹음
  static class Tree implements Comparable<Tree> {
    int y, x, age;

    public Tree(int y, int x, int age) {
      this.y = y;
      this.x = x;
      this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
      return Integer.compare(this.age, o.age);
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // ? 땅 크기
    M = Integer.parseInt(st.nextToken()); // ? 처음에 구입한 나무 개수
    K = Integer.parseInt(st.nextToken()); // ? 연도

    food = new int[N][N]; // ? 양분 정보
    add = new int[N][N]; // ? 추가 양분

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        add[i][j] = Integer.parseInt(st.nextToken());
        food[i][j] = 5;
      }
    }

    PriorityQueue<Tree> infos = new PriorityQueue<Tree>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken()) - 1;
      int x = Integer.parseInt(st.nextToken()) - 1;
      int age = Integer.parseInt(st.nextToken());
      infos.add(new Tree(y, x, age));
    }

    for (int y = 0; y < K; y++) {
      Queue<Tree> q = new LinkedList<Tree>();
      Queue<Tree> die = new LinkedList<Tree>();
      Queue<Tree> spread = new LinkedList<Tree>();

      // * 1. 봄: 자신의 나이만큼 양분을 먹고 나이가 1 증가한다. 어린 나무부터 먹고 나이만큼 못 먹으면 죽음
      while (infos.size() > 0) {
        Tree info = infos.poll();

        if (food[info.y][info.x] < info.age) {
          die.add(new Tree(info.y, info.x, info.age));
        } else {
          food[info.y][info.x] -= info.age;
          q.offer(new Tree(info.y, info.x, info.age + 1));

          if ((info.age + 1) % 5 == 0) {
            spread.add(new Tree(info.y, info.x, info.age + 1));
          }
        }
      }

      // * 2. 여름: 죽은 나무가 양분으로 변함
      while (die.size() > 0) {
        Tree info = die.poll();
        food[info.y][info.x] += (info.age / 2);
      }

      // * 3. 가을: 나무 번식
      while (spread.size() > 0) {
        Tree info = spread.poll();

        for (int d = 0; d < 8; d++) {
          int ny = info.y + dy[d];
          int nx = info.x + dx[d];

          if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
            continue;
          }

          q.offer(new Tree(ny, nx, 1));
        }
      }

      // * 4. 겨울: S2D2가 양분 추가
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          food[i][j] += add[i][j];
        }
      }

      while (q.size() > 0) {
        Tree info = q.poll();
        infos.add(new Tree(info.y, info.x, info.age));
      }

      // for (int i = 0; i < N; i++) {
      // for (int j = 0; j < N; j++) {
      // System.out.print(food[i][j] + " ");
      // }
      // System.out.println();
      // }
      // System.out.println();
    }

    System.out.println(infos.size());
  }
}
