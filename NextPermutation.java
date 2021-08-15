import java.util.Arrays;

public class NextPermutation {
	public static void main(String[] args) {
		int[] input = {7, 1, 4};
		Arrays.sort(input);
		do {		// 순열 사용
			System.out.println(Arrays.toString(input));
		}while(np(input));
	}
	/*
	 * 	// 조합(nCr)으로 사용하는 방법
	 * 	int[] p = new int[N];
		int cnt = 0;
		
		do {
			for(int i = 0; i < N; i++) {
				if(p[i] == 1) System.out.print(input[i]+" ");
			}
			System.out.println();
		}while(np(p));
	 * */
	
	
	// 다음 큰 순열이 있으면 true, 없으면 false
	private static boolean np(int[] numbers) {
		int N = numbers.length;		// 비교할 횟수
		int i = N -1;
		
		// 1. 꼭대기(i)를 찾는다. 교환위치(i-1) 찾기.
		while(i > 0 && numbers[i-1] >= numbers[i]) --i;
		if(i == 0) return false;
		
		// 2. i-1 위치값과 교환할 큰 값 찾아서 교환
		int j = N - 1;
		while(numbers[i-1] >= numbers[j]) --j;
		swap(numbers, i - 1, j);
		
		// 3. 꼭대기부터 맨 뒤까지 내림차순 형태의 순열을 오름차순으로 다시 재배치
		int k = N-1;
		while(i < k) {
			swap(numbers, i++, k--);
		}
		return true;
	}
	
	private static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}