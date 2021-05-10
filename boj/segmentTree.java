package boj;

public class segmentTree {
    static long[] arr, tree;
    public static void main(String[] args) {}

    // 세그먼트 트리를 만드는 함수. 노드의 높이는 H=logN이고, 필요한 배열의 크기는 2^(H+1)이다.
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
