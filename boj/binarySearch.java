package boj;

public class binarySearch {
    static int N, M;
    static int[] originArr;

    static int binarySearchFunction(int compareNum) {
        int start, end, mid;
        start = 0;
        end = N - 1;
        while(start <= end) {
            mid = (start + end) / 2;
            int val = originArr[mid];
            if(compareNum == val) return 1;
            else if(compareNum > val) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return 0;
    }
}
