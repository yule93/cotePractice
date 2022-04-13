package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 4256 트리(분할 정복, 재귀, 트리 구조)
// * 왼쪽과 오른쪽으로 분할 정복을 하는 게 키 포인트!! (이분탐색이랑 비슷)
// 636ms
public class boj4256 {
  public class Node {
    int v, left, right;

    public Node(int v, int left, int right) {
      this.v = v;
      this.left = left;
      this.right = right;
    }
  }

  static Node[] nodeList;
  static StringBuilder sb;
  static int[] pre, in;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      int vNum = Integer.parseInt(br.readLine()); // 노드의 개수
      nodeList = new Node[vNum];
      // top-down 식으로 올더가 진행됐으니 반대로 bottom-up 방식으로 순서 찾기...???
      // ! 전위 순회: root > left > right 순
      // ! 중위 순회: left > root > right 순
      pre = new int[vNum + 1];
      in = new int[vNum + 1];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < vNum; i++) {
        pre[i] = Integer.parseInt(st.nextToken());
      }

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < vNum; i++) {
        in[i] = Integer.parseInt(st.nextToken());
      }

      traversal(0, 0, vNum);
      sb.append("\n");
    }

    System.out.print(sb.toString());
  }

  static void traversal(int root, int s, int e) {
    // * 전위 순회와 중위 순회에서 같은 노드를 보고 있는데 만약 전위 순회의 i+1번째 노드가 중위 순회의 i-(s-e)이나 i+(s-e)에 없다면 연결된 노드가 아님!!
    // * 즉, 주어진 예시로 들자면 3 6 5 4 // 8 7 1 2 와 5 6 8 4 // 3 1 2 7 을 살펴보면 전위는 5 옆에 4가 있는데 중위는 5 옆에 4가 없다. 여기서 갈려서 
    /*
    * if(in[i] == root) inorder 데이터에서 루트 노드의 위치를 찾는다
    * 왼쪽트리는 [s~i)의 범위를 가지고 root노드는 pre[현재 루트 노드 idx+1]를 가진다.
    * 오른쪽트리는 [i+1,e)의 범위를 가지고 root노드는 pre[현재 루트 노드 idx+i-s+1]을 가진다.
     */
    int rootIdx = pre[root];
    for (int i = s; i < e; i++) {
      if (in[i] == rootIdx) {
        traversal(root + 1, s, i);
        traversal(root + i + 1 - s, i + 1, e);
        sb.append(rootIdx + " ");
      }
    }
  }

  public static void postOrder(int n) {
    if (nodeList[n].left != 0)
      postOrder(nodeList[n].left);
    sb.append(n).append(" ");
    if (nodeList[n].right != 0)
      postOrder(nodeList[n].right);
  }
}
