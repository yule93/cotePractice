package programmers;

class SolutionChangeGualho {
    
    // 변환 과정에 맞게 u를 변환하는 메소드. 첫 번째와 마지막 문자를 제외하고 반복
    public static String reverse(String str){
        StringBuilder s = new StringBuilder();
        for(int i = 1; i<str.length()-1;i++){
            if(str.charAt(i) == '(') s.append(")");
            else s.append("(");
        }
        // 새로운 문자열을 반환
        return s.toString();
    }
    
    // 올바른 괄호 문자열 판단 메소드
    public static boolean checkRight(String str){
        int open = 0;       // ( 의 개수를 세는 변수.
        
        if(str.charAt(0) ==')') return false;       // 첫 문자가 )인 경우는 올바른 괄호 문자열인 경우가 아니므로 false
        for(int i =0;i<str.length();i++){
            // (의 개수를 카운팅
            if(str.charAt(i) == '(') open++;
            else {
                open--;     // )를 만나면 open 감소
                if(open < 0) return false;    // open이 음수가 되면 (의 개수보다 )의 개수가 많아지므로 올바른 괄호 문자열이 아님
            }
        }
        return true;
    }
    
    // 균형잡힌 괄호 문자열 -> 올바른 괄호 문자열 변환 메소드
    public static String split(String w) {
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();
        
        if(w.length() == 0) return "";
        int open = 0;   // (의 개수를 저장하는 변수

        for(int i =0;i<w.length();i++){ 
            if(w.charAt(i) == '(') open++; 
            else open--;
            
            // open이 0이 된 경우가 가장 작은 단위의 균형잡힌 괄호 문자열임 해당 인덱스를 기점으로 u와 v로 분리
            if(open == 0) {
                u.append(w.substring(0,i+1));
                v.append(w.substring(i+1,w.length()));
                
                // u가 올바른 괄호 문자열일 때
                if(checkRight(u.toString())){
                    u.append(split(v.toString())); 
                } else{
                    StringBuilder str = new StringBuilder();
                    str.append("(");
                    str.append(split(v.toString()));
                    str.append(")");
                    str.append(reverse(u.toString()));
                    return str.toString();
                }
                break;             
            }
        }
        return u.toString();        
    }
    
    public String solution(String p) {
        String answer;
        
        if(checkRight(p))
            return p;      
        answer = split(p);
    
        return answer;
    }
}