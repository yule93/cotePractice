// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");


class test1 {
    public String solution(String S, String C) {
        // write your code in Java SE 8
        String sp = "; ";
        String[] names = S.split(sp);
        int len = names.length;
        List<String> emails = new ArrayList<>();
        List<Integer> emailDupli = new ArrayList<>();

        for(int i = 0; i < len; i++) {
            StringBuilder sb = new StringBuilder("");
            String[] personName = names[i].split(" ");
            
            sb.append(personName[personName.length - 1].toLowerCase().replace("-", ""));
            sb.append("_");
            sb.append(personName[0].toLowerCase().replace("-", ""));
            
            if(emails.indexOf(sb.toString()) != -1) {
                int index = emails.indexOf(sb.toString());
                int num = emailDupli.get(index);
                sb.append(++num);
                emailDupli.set(index, num);
                emails.add(sb.toString());
            }
            else {
                emails.add(sb.toString());
                emailDupli.add(1);
            }
        }

        StringBuilder answer = new StringBuilder("");
        for(int i = 0; i < len; i++) {
            if(i != len-1) answer.append(names[i] + " <"+emails.get(i) + "@" + C.toLowerCase() +".com>; ");
            else answer.append(names[i] + " <"+emails.get(i) + "@" + C.toLowerCase() +".com>");
        }

        //System.out.println(answer);

        return answer.toString();
    }
}

class test2 {
    public int solution(int[] A) {
        // write your code in Java SE 8
        // Math.max() 사용 예정
        int answer = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int data : A) {
            if(!numbers.contains(data)) {
                numbers.add(data);
            }
        }

        int size = numbers.size();      // 중복 제거한 숫자들이 총 몇 개인지

        for(int i = 0; i < size - 1; i++) {
            int firstNum = numbers.get(i);
            int secondNum = numbers.get(i+1);
            int count = 0;
            for(int j = 0 ; j < A.length; j++) {
                if(A[j] == firstNum || A[j] == secondNum) {
                    count++;
                    answer = Math.max(answer, count);
                } else {
                    count = 0;
                }
            }
        }

        if(size == 1) answer = A.length;

        return answer;
    }
}