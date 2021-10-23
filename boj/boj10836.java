package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// boj 10836 여왕벌 (시뮬레이션)
// 왜 골드 4지...........................?
public class boj10836 {
    static int arr[][], n, m, order[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // 초기화
        arr = new int[m][m];
        order = new int[2 * m]; // 가장 왼쪽 열과 가장 위쪽 행의 개수만큼 order를 만듦
        for (int t = 0; t < n; t++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());
            int idx3 = Integer.parseInt(st.nextToken());
            order[idx1]++;
            order[idx2 + idx1]++;
        }
        int sum = 0, idx = 0;
        for (int i = m - 1; i >= 0; i--) {
            sum += order[idx++];
            arr[i][0] = sum;
        }
        for (int i = 1; i < m; i++) {
            sum += order[idx++];
            arr[0][i] = sum;
        }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < m; j++)
                arr[i][j] = arr[0][j];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(arr[i][j] + 1 + " ");
            System.out.println();
        }
    }
}
