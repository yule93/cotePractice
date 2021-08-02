package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// BOJ 17478: 재귀함수가 뭔가요? (재귀, 문자열, 구현)
public class boj17478 {
    public static int num;
    public static StringBuilder build;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        

        num = Integer.parseInt(br.readLine());        // 재귀를 반복해야하는 숫자

        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        printReturn(num);
        printAnswer(num);
    }

    public static void printReturn(int n) {
        // 몇 번 반복할지 int n을 입력받아서 사용한다.
        build = new StringBuilder("");
        if(n < 0) return;
        if(n == 0) {
            for(int i = 0; i < (num-n)*4; i++) {
                build.append("_");
            }
            build.append("\"재귀함수가 뭔가요?\"\n");
            for(int i = 0; i < (num-n)*4; i++) {
                build.append("_");
            }
            build.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
            System.out.println(build.toString());
            return;
        }

        for(int i = 0; i < (num-n)*4; i++) {
            System.out.print("_");
        }
        System.out.println("\"재귀함수가 뭔가요?\"");
        for(int i = 0; i < (num-n)*4; i++) {
            System.out.print("_");
        }
        System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
        for(int i = 0; i < (num-n)*4; i++) {
            System.out.print("_");
        }
        System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
        for(int i = 0; i < (num-n)*4; i++) {
            System.out.print("_");
        }
        System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
        
        printReturn(n - 1);
    }

    public static void printAnswer(int n) {
        if(n < 0) return;

        for(int i = 0; i < n*4; i++) {
            System.out.print("_");
        }
        System.out.println("라고 답변하였지.");
        printAnswer(n - 1);
    }
}
