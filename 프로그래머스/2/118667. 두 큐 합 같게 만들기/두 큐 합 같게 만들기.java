import java.io.*;
import java.util.*;

class Solution {
    Deque<Integer> q1, q2;
    int minValue;
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        minValue = queue1.length * 4;
        
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
        
        long sum1 = 0;
        long sum2 = 0;
        
        for(int i = 0; i < queue1.length; i++){
            q1.offerLast(queue1[i]);
            sum1 += queue1[i];
            
            q2.offerLast(queue2[i]);
            sum2 += queue2[i];
        }
        
        int cnt = 0;
        if((sum1 + sum2) % 2 == 0){
            int num = 0;
            while(sum1 != sum2){
                if(sum1 > sum2){
                    num = q1.pollFirst();
                    q2.offerLast(num);
                    sum1 -= num;
                    sum2 += num;
                    cnt++;
                }else {
                    num = q2.pollFirst();
                    q1.offerLast(num);
                    sum1 += num;
                    sum2 -= num;
                    cnt++;
                }
                
                if(cnt > minValue){
                    break;
                }
            }       
        } 
        
        if(cnt <= queue1.length * 4){
            answer = cnt;
        }
        
        return answer;
    }
    
    public void task(long sum1, long sum2, int cnt){
        if(cnt >= minValue){
            return;
        }
        
        if(sum1 == sum2) {
            if(cnt < minValue){
                minValue = cnt;
            }
            return;
        }
        
        if(q1.isEmpty() || q2.isEmpty()){
            return;
        }
        
        int num = 0;
        
        
    }
}