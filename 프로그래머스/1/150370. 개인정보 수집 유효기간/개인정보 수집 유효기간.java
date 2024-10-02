import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer;
        List<Integer> tempAnswer = new ArrayList<>();
        
        String[] splitToday = today.split("\\.");
        int year = Integer.parseInt(splitToday[0]);
        int month = Integer.parseInt(splitToday[1]);
        int day = Integer.parseInt(splitToday[2]);
        
        Map<String, Integer> splitTerms = new HashMap<>();
        
        String[] temp = new String[2];
        for(int i = 0; i < terms.length; i++){
            temp = terms[i].split(" ");
            splitTerms.put(temp[0], Integer.parseInt(temp[1]));
        }
        
        String[] privDate;
        int privYear, privMonth, privDay, addMonth, addYear;
        for(int i = 0; i < privacies.length; i++){
            temp = privacies[i].split(" ");
            privDate = temp[0].split("\\.");
            privYear = Integer.parseInt(privDate[0]);
            privMonth = Integer.parseInt(privDate[1]);
            privDay = Integer.parseInt(privDate[2]);
            addMonth = splitTerms.get(temp[1]);
            
            privMonth = privMonth + addMonth;
            
            while(privMonth > 12){
                privMonth -= 12;
                privYear += 1;
            }
            
            if(privMonth > 12){
                privYear += 1;
                privMonth -= 12;
            }
            
            privDay = privDay - 1;
            if(privDay == 0) {
                privMonth -= 1;
                privDay = 28;
                
                if(privMonth == 0){
                    privMonth = 12;
                    privYear -= 1;                    
                }
            }
            
            
            
            if(year < privYear){
                continue;
            }else if(year > privYear){
                tempAnswer.add(i+1);
            }else {
                if(month < privMonth){
                    continue;
                }else if(month > privMonth){
                    tempAnswer.add(i+1);
                }else {
                    if(day < privDay){
                        continue;
                    }else if(day > privDay){
                        tempAnswer.add(i+1);
                    }
                }
            }
        }
        
        answer = new int[tempAnswer.size()];
        
        for(int i = 0; i < tempAnswer.size(); i++){
            answer[i] = tempAnswer.get(i);
        }
        
        return answer;
    }
}