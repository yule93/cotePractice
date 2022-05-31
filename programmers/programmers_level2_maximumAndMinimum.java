package programmers;

import java.util.Arrays;

public class programmers_level2_maximumAndMinimum {
  public static void main(String[] args) {
    System.out.println(solution("1 2 3 4"));
  }
  
  public static String solution(String s) {
        String answer = "";
        String[] arr = s.split(" ");
        int[] arr2 = new int[arr.length];
        
        for(int i = 0; i < arr.length; i++) {
            arr2[i] = Integer.parseInt(arr[i]);
        }
        
        Arrays.sort(arr2);
        answer += (arr2[0]+" "+arr2[arr.length-1]);
        
        return answer;
    }
}
