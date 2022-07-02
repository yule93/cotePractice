package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// boj 5052 전화번호 목록
// 532 ms
public class boj5052 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
      Trie trie = new Trie();
      boolean flag = true;

      int n = Integer.parseInt(br.readLine());
      for (int j = 0; j < n; j++) {
        if (!trie.insert(br.readLine())) {
          flag = false;
          continue;
        }
      }
      sb.append(flag == true ? "YES\n" : "NO\n");
    }
    System.out.print(sb.toString());
  }

  static class TrieNode {
    Map<Character, TrieNode> childNodes = new HashMap<>();
    boolean isLast;
  }

  static class Trie {
    TrieNode root = new TrieNode();

    boolean insert(String word) {
      TrieNode thisNode = root;
      for (int i = 0; i < word.length(); i++) {
        char n = word.charAt(i);
        if (thisNode.childNodes.get(n) == null) {
          thisNode.childNodes.put(n, new TrieNode());
        }
        thisNode = thisNode.childNodes.get(n);
        if (thisNode.isLast == true)
          return false;
      }
      if (thisNode.childNodes.size() != 0)
        return false;
      thisNode.isLast = true;
      return true;
    }
  }
}
