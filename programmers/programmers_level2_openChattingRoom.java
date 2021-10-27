package programmers;

import java.util.*;

public class programmers_level2_openChattingRoom {
    public static void main(String[] args) {
        String[] example1 = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
                "Change uid4567 Ryan" };
        for (String str : solution(example1)) {
            System.out.println(str);
        }
    }
    
    public static String[] solution(String[] record) {
        String[] answer = {};
        StringTokenizer st;
        
        // key에는 유저 아이디, val에는 유저 네임 저장
        HashMap<String, String> userList = new HashMap<>();
        for(int i = 0; i < record.length; i++) {
            st = new StringTokenizer(record[i], " ");
            String command = st.nextToken();
            if ("Leave".equals(command))
                continue;

            String userId = st.nextToken();
            String userName = st.nextToken();
            
            if("Enter".equals(command) || "Change".equals(command)) {
                userList.put(userId, userName);
            }
        }
        
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < record.length; i++) {
            st = new StringTokenizer(record[i], " ");
            String command = st.nextToken();
            String userId = st.nextToken();
            StringBuilder sb = new StringBuilder();
            
            if("Change".equals(command)) continue;
            else if("Enter".equals(command)) {
                sb.append(userList.get(userId)).append("님이 들어왔습니다.");
            } else if("Leave".equals(command)) {
                sb.append(userList.get(userId)).append("님이 나갔습니다.");
            }
            list.add(sb.toString());
        }
        
        answer = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
