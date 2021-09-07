package boj;
import java.util.*;
import java.io.*;

// BOJ 2336, 세그먼트 트리
public class boj2336 {
	static long[] arr, tree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		String [] grades = new String [N];
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		int bestStudent = 0;
		
		for (int i = 0; i < N; i++) {
			grades[i] = br.readLine();	// 학생수 N에 따라 학생의 등수 입력받음
		}
		
		for(int i = 0; i < N; i++) {
			
		}

		bw.write(bestStudent+"\n");
		bw.flush();
		br.close();
		bw.close();
	}

	long init(int start, int end, int node) {
        if(start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        // 어떤 node의 번호가 x일 때, 왼쪽 자식은 2x, 오른쪽 자식은 2x+1이 된다.
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    long sum(int start, int end, int left, int right, int node) {
        if(left > end || right < start) return 0;
        if(left <= start && end <= right) return tree[node];
        int mid = (start+end)/2;

        return sum(start, mid, left, right, node*2) + sum(mid + 1, end, left, right, node*2+1);
    }

    void update(int start, int end, int index, long diff, int node) {
        if(index < start || index > end) return;
        tree[node] = diff;      // tree[node] + diff 방법도 있긴 한데 왜 굳이 diff를 val-a[index]로 정한건지 아리송....
        int mid = (start + end) / 2;

        if(start != end) {
            update(start, mid, index, diff, node*2);
            update(mid+1, end, index, diff, node*2+1);
        }
    }
}