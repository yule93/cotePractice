package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj14003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) arr[i]=Integer.parseInt(st.nextToken());

        int[] index=new int[n];
        for(int i = 0; i < n; i++) index[i]=i;
        
        int[] list=new int[n];
        int idx = 0;

        list[idx++] = arr[0];
        for(int i=1;i<n;i++){
            if(list[idx-1]<arr[i]) {
                index[i]=idx;
                list[idx++]=arr[i];
            }
            else{
                int s=0;
                int e=idx-1;
                while(s<e){
                    int m=(s+e)>>1;
                    if(list[m]<arr[i]) s=m+1;
                    else e=m;
                }
                index[i]=e;
                list[e]=arr[i];
            }
        }
        int t=idx-1;
        Stack<Integer> s=new Stack<>();
        for(int i=n-1;i>=0;i--){
            if(index[i]==t){
                s.push(arr[i]);
                t--;
            }
        }
        sb.append(idx+"\n");
        
        while(!s.isEmpty()) sb.append(s.pop()+" ");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
