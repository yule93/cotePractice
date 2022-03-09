package programmers;

import java.util.LinkedList;
import java.util.Queue;

// 프로그래머스 캐시 (큐)
// LRF를 구현한 Queue로 단순 구현만 하면 되는 문제
// cache는 0~30, 도시수는 10만개 이하로 O(N)의 시간복잡도를 가져 고려 안해도 괜찮다.
// 추가적으로 0일 때 로직 구현을 조심해야 함.
public class programmers_level2_cache {
  public static void main(String[] args) {
    System.out.println(solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
  }

  static final int CACHE_HIT = 1;
  static final int CACHE_MISS = 5;

  public static int solution(int cacheSize, String[] cities) {
    if (cacheSize == 0)
      return 5 * cities.length;
    int answer = 0;

    Queue<String> cache = new LinkedList<>();

    for (int i = 0; i < cities.length; ++i) {
      String city = cities[i].toUpperCase();

      // 캐시 적중
      if (cache.remove(city)) {
        cache.add(city);
        answer += CACHE_HIT;

        // 캐시 미스. 가득 찼을 때 가장 참고한지 오래된 페이지 삭제 및 다시 삽입
        // 캐시 자리가 있으면 맨 앞자리에 다시 삽입
      } else {
        int currentSize = cache.size();
        if (currentSize == cacheSize) {
          cache.poll();
        }
        cache.add(city);
        answer += CACHE_MISS;
      }
    }
    return answer;
  }
}
