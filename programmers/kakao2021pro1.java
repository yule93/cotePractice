package programmers;

// 아이디 추천
public class kakao2021pro1 {
    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder();     // 답

        for(char ch : new_id.toCharArray()) {
            if(!isValid(ch)) continue;
            
            boolean last = false;       // 마지막 문자는 점이 되면 안 되니까 여부를 확인해줘야 함.
            if(ch == '.') {
                if(sb.length() == 0 || last) continue;      // 처음, 혹은 마지막 문자를 확인하는 것인지 판별하는 조건문
                last = true;
            } else {
                last = false;
            }

            ch = Character.toLowerCase(ch);
            sb.append(ch);
        }

        if(sb.length() >= 16) sb.setLength(15);
        if(sb.length() == 0) sb.append("a");
        if(sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length()-1);

        while(sb.length() < 3) {
            sb.append(sb.charAt(sb.length() - 1));
        }

        return sb.toString();
    }

    boolean isValid(char c) {
        if(Character.isLetterOrDigit(c)) return true;
        if(c == '-' || c == '_' || c == '.') return true;

        return false;
    }
}
