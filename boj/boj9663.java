package boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// N-Queen
// N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
// N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
public class boj9663 {
    static int N, ans, cnt;
    static int map[];
    static boolean[] col, slash, backslash;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        col = new boolean[N];
        map = new int[N + 1];
        cnt = 0;
        ans = 0;
        slash = new boolean[2 * N - 1];
        backslash = new boolean[2 * N - 1];

        //solve(0);
        setQueens(1);
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int r) {
        if (r == N) {
            ans++;
            return;
        }
        for (int c = 0; c < N; c++) {
            if (check(r, c)) {
                col[c] = slash[r + c] = backslash[r - c + N - 1] = true;
                solve(r + 1);
                col[c] = slash[r + c] = backslash[r - c + N - 1] = false;
            }
        }
    }
    public static boolean check(int r, int c) {
        if (col[c] || slash[r + c] || backslash[r - c + N - 1])
            return false;
        return true;
    }

    public static void setQueens(int rowNo) {
        // if(!isAvailable(rowNo - 1)) return;
        if(rowNo > N) {
            // 1행부터 시작했기 때문에 N 이상이 되어야 종료
            cnt++;
            return;
        }
        for(int i = 1; i <= N; i++) {
            map[rowNo] = i;     // queen을 놓은 자리를 저장. 마치 조합과 같군뇽
            if(isAvailable(rowNo)){
                setQueens(rowNo + 1);
            }
        }
    }

    public static boolean isAvailable(int rowNo) {
        for(int k = 1; k < rowNo; k++) {
            if(map[rowNo] == map[k] || Math.abs(map[rowNo] - map[k]) == (rowNo - k)) return false;
        }
        return true;
    }
}
