package simplePractice;

import java.util.Arrays;

public class DisjointSetTest {
    static int N;
    static int[] parents;
    
    private static void makeSet() {
        // 모든 원소의 대표자를 자신으로 만든다.
        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int a) {
        // a가 속한 집합의 대표자 찾기
        if(a == parents[a]) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        // 두 원소를 하나의 집합으로 합치기(대표자 이용)
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot == bRoot) return false;    // 이미 같은 집합

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) {
        N = 5;
        parents = new int[N];

        // make set
        makeSet();

        // union
        System.out.println(union(0, 1));
        System.out.println(Arrays.toString(parents));
        System.out.println(union(2, 1));
        System.out.println(Arrays.toString(parents));
        System.out.println(union(3, 2));
        System.out.println(Arrays.toString(parents));
        System.out.println(union(4, 3));
        System.out.println(Arrays.toString(parents));

        System.out.println("=============find=============");
        System.out.println(findSet(4));
        System.out.println(Arrays.toString(parents));
        System.out.println(findSet(3));
        System.out.println(Arrays.toString(parents));
        System.out.println(findSet(2));
        System.out.println(Arrays.toString(parents));
        System.out.println(findSet(0));
        System.out.println(Arrays.toString(parents));
        System.out.println(findSet(1));
        System.out.println(Arrays.toString(parents));
    }
}
