package leetCodeWeeklyChallenge;

public class leetcode253Week1 {
    public boolean isPrefixString(String s, String[] words) {
        int len = words.length;
        boolean answer = false;
        String sentence = "";
        for(int i = 0; i < len; i++) {
            if(s.contains(words[i])) {
                sentence.concat(words[i]);
            }
        }
        return answer;
    }
}
