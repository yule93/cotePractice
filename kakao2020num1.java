class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int[] coordinate[] = new int[][] { {3,1}, {0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2} };
        int rightHandC[] = {3, 2};
        int leftHandC[] = {3, 0};
        
        for(int i = 0; i < numbers.length; i++) {
            int lengR = Math.abs(coordinate[numbers[i]][0]-rightHandC[0]) + Math.abs(coordinate[numbers[i]][1]-rightHandC[1]);
            int lengL = Math.abs(coordinate[numbers[i]][0]-leftHandC[0]) + Math.abs(coordinate[numbers[i]][1]-leftHandC[1]);
            
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                answer = answer + "L";
                leftHandC = coordinate[numbers[i]];
            } else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
                answer = answer + "R";
                rightHandC = coordinate[numbers[i]];
            } else {
                if(lengR < lengL) {
                    answer = answer + "R";
                    rightHandC = coordinate[numbers[i]];
                } else if (lengL < lengR) {
                    answer = answer + "L";
                    leftHandC = coordinate[numbers[i]];
                } else if(lengL == lengR) {
                    if(hand.equals("right")) {
                        answer = answer + "R";
                        rightHandC = coordinate[numbers[i]];
                    } else if(hand.equals("left")) {
                        answer = answer + "L";
                        leftHandC = coordinate[numbers[i]];
                    }
                }
            }
        }
        
        return answer;
    }
}