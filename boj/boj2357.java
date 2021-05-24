package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.*;

// BOJ 2357: 최솟값과 최댓값, 세그먼트 트리 사용해야 함
public class boj2357 {
    static int[] arr, minTree, maxTree;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		minTree = new int[N * 4];   // 세그먼트 트리는 주어진 자료의 수*4의 크기를 최대 크기로 갖는다.
		maxTree = new int[N * 4];

		minInit(1, N, 1);
		maxInit(1, N, 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			sb.append(minFind(1, N, 1, left, right) + " " + maxFind(1, N, 1, left, right) + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	// 각 구간 별로 최솟값을 저장.
	public static int minInit(int start, int end, int node) {
		if (start == end) {
			return minTree[node] = arr[start];      // minTree[node]에 arr[start]값을 저장하고 minTree값을 리턴
		}

		int mid = (start + end) / 2;
		return minTree[node] = Math.min(minInit(start, mid, node * 2), minInit(mid + 1, end, node * 2 + 1));
	}

	// 각 구간 별로 최댓값을 저장.
	public static int maxInit(int start, int end, int node) {
		if (start == end) {
			return maxTree[node] = arr[start];
		}

		int mid = (start + end) / 2;
		return maxTree[node] = Math.max(maxInit(start, mid, node * 2), maxInit(mid + 1, end, node * 2 + 1));
	}

	// left ~ right 범위 내에 최솟값을 찾음.
	public static int minFind(int start, int end, int node, int left, int right) {
		// 범위를 벗어난 경우
		if (right < start || end < left) {
			return Integer.MAX_VALUE;
		}

		// 범위 안에 있는 경우
		if (left <= start && end <= right) {
			return minTree[node];
		}

		int mid = (start + end) / 2;

		return Math.min(minFind(start, mid, node * 2, left, right), minFind(mid + 1, end, node * 2 + 1, left, right));
	}

	// left ~ right 범위 내에 최댓값을 찾음.
	public static int maxFind(int start, int end, int node, int left, int right) {
		// 범위를 벗어난 경우
		if (right < start || end < left) {
			return Integer.MIN_VALUE;
		}

		// 범위 안에 있는 경우
		if (left <= start && end <= right) {
			return maxTree[node];
		}

		int mid = (start + end) / 2;

		return Math.max(maxFind(start, mid, node * 2, left, right), maxFind(mid + 1, end, node * 2 + 1, left, right));
	}
}
