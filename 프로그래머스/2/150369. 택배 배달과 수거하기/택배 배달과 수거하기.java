import java.util.*;
import java.io.*;

class Solution {    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        Stack<int[]> deliverStack = new Stack<>();
        Stack<int[]> pickupStack = new Stack<>();
        
        for(int i = 0; i < n; i++){
            if(deliveries[i] != 0){
                deliverStack.push(new int[] {i+1, deliveries[i]});
            }            
        }
        
        for(int i = 0; i < n; i++){
            if(pickups[i] != 0){
                pickupStack.push(new int[] {i+1, pickups[i]});
            }            
        }
        
        int[] deliverHouse = new int[2];
        int[] pickupHouse = new int[2];
        int deliverCount;
        int pickupCount;
        int houseIndex = 0;
        
        while(!deliverStack.isEmpty() || !pickupStack.isEmpty()){                   
            
            deliverCount = cap;
            while(!deliverStack.isEmpty() && 0 < deliverCount && deliverCount <= cap){                         deliverHouse = deliverStack.pop();
                                                                                      
                if(deliverHouse[1] > deliverCount){
                    deliverHouse[1] -= deliverCount;
                    deliverCount = 0;

                    deliverStack.push(new int[] { deliverHouse[0], deliverHouse[1] });

                    if(deliverHouse[0] > houseIndex){
                        houseIndex = deliverHouse[0];
                    }
                }else {
                    deliverCount -= deliverHouse[1];

                    if(deliverHouse[0] > houseIndex){
                        houseIndex = deliverHouse[0];
                    }
                }
                
            }
            
            pickupCount = cap;
            while(!pickupStack.isEmpty() && 0 < pickupCount && pickupCount <= cap){
                pickupHouse = pickupStack.pop();

                if(pickupHouse[1] > pickupCount){
                    pickupHouse[1] -= pickupCount;
                    pickupCount = 0;

                    pickupStack.push(new int[] { pickupHouse[0], pickupHouse[1] });

                    if(pickupHouse[0] > houseIndex){
                        houseIndex = pickupHouse[0];
                    }
                }else {
                    pickupCount -= pickupHouse[1];

                    if(pickupHouse[0] > houseIndex){
                        houseIndex = pickupHouse[0];
                    }
                }
                
            }
            
            answer += houseIndex * 2;
            houseIndex = 0;
        }
        
        return answer;
    }
}