package programmers;
class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int[] coordinate[] = new int[][] { {3,1}, {0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2} };	// 각 버튼 별 좌표 등록
        int rightHandC[] = {3, 2};		// 오른손 시작 버튼 *의 좌표
        int leftHandC[] = {3, 0};		// 왼손 시작 버튼 #의 좌표
        
        for(int i = 0; i < numbers.length; i++) {
			
            int lengR = Math.abs(coordinate[numbers[i]][0]-rightHandC[0]) + Math.abs(coordinate[numbers[i]][1]-rightHandC[1]);	// 현재 오른손으로부터 이동할 버튼간의 거리
            int lengL = Math.abs(coordinate[numbers[i]][0]-leftHandC[0]) + Math.abs(coordinate[numbers[i]][1]-leftHandC[1]);	// 현재 왼손으로부터 이동할 버튼간의 거리
            
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {		// 왼손 움직이기
                answer = answer + "L";
                leftHandC = coordinate[numbers[i]];
            } else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {		// 오른손 움직이기
                answer = answer + "R";
                rightHandC = coordinate[numbers[i]];
            } else {
                if(lengR < lengL) {		// 오른손 길이가 더 짧을 때
                    answer = answer + "R";
                    rightHandC = coordinate[numbers[i]];
                } else if (lengL < lengR) {		// 왼손 길이가 더 짧을 때
                    answer = answer + "L";
                    leftHandC = coordinate[numbers[i]];
                } else if(lengL == lengR) {		// 오른손과 왼손 길이가 같을 때
                    if(hand.equals("right")) {		// 오른손 잡이
                        answer = answer + "R";
                        rightHandC = coordinate[numbers[i]];
                    } else if(hand.equals("left")) {	// 왼손 잡이
                        answer = answer + "L";
                        leftHandC = coordinate[numbers[i]];
                    }
                }
            }
        }
        
        return answer;
    }
}