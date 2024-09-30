import java.util.*;
import java.io.*;

class Solution {
    int users_length, emoticons_length;
    Map<Integer, Integer> user_disc;
    
    int[][] emo_disc;
    int[] user_max, select_arr;
    
    int[] answer;
    
    public int[] solution(int[][] users, int[] emoticons) {
        users_length = users.length;
        emoticons_length = emoticons.length;
        answer = new int[2];
        
        user_disc = new HashMap<>();
        emo_disc = new int[emoticons_length][5];
        
        user_max = new int[users_length];
        select_arr = new int[emoticons.length];
        
        for(int i = 0; i < users_length; i++){
            user_max[i] = users[i][1];
        }
        
        int temp;
        for(int i = 0; i < users.length; i++){
            temp = users[i][0] / 10;
            
            if(users[i][0] % 10 > 0) {
                temp += 1;
            }
            
            user_disc.put(i, temp);
        }
        
        
        for(int i = 0; i < emoticons.length; i++){
            for(int j = 0; j < 5; j++){
                emo_disc[i][j] = emoticons[i] * (100 - 10 * j) / 100;
            }
        }
        
               
        
        selection(0, 1);
        
        return answer;
    }
    
    public void selection(int idx, int discount){
        if(discount >= 5) return;
        if(idx >= emoticons_length){
            calc();
            return;
        }
        
        select_arr[idx] = discount;
        
        selection(idx+1, 1);
        selection(idx, ++discount);
        
    }
    
    public void calc(){
        int user_cnt = 0;
        int price = 0;
        int user_price = 0;
        for(int i = 0; i < users_length; i++){
            user_price = 0;
            for(int j = 0; j < emoticons_length; j++) {
                if(select_arr[j] < user_disc.get(i)){
                    continue;
                }
                
                user_price += emo_disc[j][select_arr[j]];
            }
            
            
            if(user_max[i] <= user_price){
                user_cnt++;
            }else{
                price += user_price;
            }
        }
        
        //System.out.println("user : " + user_cnt);
        //System.out.println("price : " + price);
        if(answer[0] < user_cnt){
            answer[0] = user_cnt;
            answer[1] = price;
        }else if(answer[0] == user_cnt){
            if(answer[1] < price){
                answer[1] = price;
            }
        }
    }
}