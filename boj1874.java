import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;

public class boj1874 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int i=1;
        int cnt = 0;
        int a = Integer.parseInt(br.readLine());
        while(true) {
            // stack이 비어있지 않다면
            if(!stack.isEmpty()) {
                // stack의 맨 위의 수와 다음 수열의 수가 같은지 확인
                if(stack.peek() == a) {
                    // 같다면 pop
                    sb.append("-").append("\n");
                    stack.pop();
                    cnt++;
                    // 수열을 모두 생성한 경우
                    if(cnt == n)
                        break;
                    // 다음 수 입력받기
                    a = Integer.parseInt(br.readLine());
                    continue;
                }                
            }
            stack.add(i++);
            sb.append("+").append("\n");
            // i가 n+1이 넘으면 수열을 생성할 수 없는 경우
            if(i > n+1)
                break;
        }

        if(stack.size() == 0)
            System.out.print(sb);
        else
            System.out.println("NO");    // stack에 남아 있다면 수열을 생성하지 못함
    }
}