package programmers;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class programmers_level2_newsClustering {
  public static void main(String[] args) {
    System.out.println(solution("FRANCE", "french"));
  }

  public static int solution(String str1, String str2) {
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();

    if (str1.equals(str2))
      return 65536;

    ArrayList<String> str1List = new ArrayList<>();
    ArrayList<String> str2List = new ArrayList<>();

    devideString(str1List, str1);
    devideString(str2List, str2);

    float intersect = 0;
    int maxSize = str2List.size();

    for (int i = 0; i < str1List.size(); i++) {
      for (int j = 0; j < str2List.size(); j++) {
        if (str1List.get(i).equals(str2List.get(j))) {
          intersect++;
          str2List.remove(j);
          break;
        }
      }
    }

    if (str1List.size() == 0 && str2List.size() == 0)
      return 65536;

    return (int)(intersect/(str1List.size() + maxSize - intersect) * 65536);
  }

  public static void devideString(ArrayList<String> list,String str) {
    int len = str.length();

    for (int j = 0; j < len - 1; j++) {
      if (Pattern.matches("^[a-z]*$", str.substring(j, j + 2))) {
        list.add(str.substring(j, j + 2));
      }
    }    
  }
}
