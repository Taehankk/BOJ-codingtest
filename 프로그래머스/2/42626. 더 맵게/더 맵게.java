import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for(int i = 0; i < scoville.length; i++){
            queue.offer(scoville[i]);
        }
        
        int food1, food2, newScoville = 0;
        while(queue.size() > 1){
            food1 = queue.poll();
            if(food1 >= K){
                break;
            }
            
            food2 = queue.poll();
            newScoville = food1 + food2 * 2;
            queue.offer(newScoville);
            answer++;
        }
        
        food1 = queue.poll();
        if(food1 < K){
            answer = -1;
        }
        
        return answer;
    }
}