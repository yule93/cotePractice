import java.io.*;

public class boj5430 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        loop:
        for(int i = 0; i < T; i++) {
            String[] commands = br.readLine().split("");
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();

            // 배열 크기가 0이면 그냥 error 리턴
            if(n == 0) {
                sb.append("error");
                continue loop;
            } else {
                arr = arr.substring(1, arr.length() - 1);
                for(int j = 0; j < commands.length; j++) {
                    if(n > commands.length - j - 1) {
                        switch(commands[j]) {
                            case "R":
                                StringBuilder reverseArr = new StringBuilder(arr).reverse();
                                arr = reverseArr.toString();
                                break;
                            case "D":
                                arr = arr.substring(2, arr.length());
                                break;
                        }
                    } else {
                        arr = "error";
                        break;
                    }
                }
                sb.append(arr == "error" ? arr+"\n": "["+arr+"]\n");
            }
            
        }
        //bw.write(sb.toString());
        System.out.print(sb.toString());
    }
}