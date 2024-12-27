import java.util.*;
import java.io.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int max_time = Integer.parseInt(video_len.split(":")[0]) * 60
                    + Integer.parseInt(video_len.split(":")[1]);
        
        int op_start_sec = Integer.parseInt(op_start.split(":")[0]) * 60
                    + Integer.parseInt(op_start.split(":")[1]);
        int op_end_sec = Integer.parseInt(op_end.split(":")[0]) * 60 
                    + Integer.parseInt(op_end.split(":")[1]);
        
        String[] time = pos.split(":");
        int minutes = Integer.parseInt(time[0]);
        int seconds = Integer.parseInt(time[1]);
        int[] after_func = new int[2];
        
        String command = "";
        
        if(op_start_sec <= minutes * 60 + seconds && minutes * 60 + seconds <= op_end_sec){
            minutes = Integer.parseInt(op_end.split(":")[0]);
            seconds = Integer.parseInt(op_end.split(":")[1]);
        }
        
        for(int i = 0; i < commands.length; i++){
            command = commands[i];
            
            if(command.equals("prev")) {
                after_func = prev_func(minutes, seconds);
                
                if(after_func[0] < 0) {
                    after_func[0] = 0;
                    after_func[1] = 0;
                }
            }else if(command.equals("next")) {
                after_func = next_func(minutes, seconds);
                
                if(after_func[0] * 60 + after_func[1] > max_time){
                    after_func[0] = Integer.parseInt(video_len.split(":")[0]);
                    after_func[1] = Integer.parseInt(video_len.split(":")[1]);
                }
            }
            
            minutes = after_func[0];
            seconds = after_func[1];
            
            if(op_start_sec <= minutes * 60 + seconds && minutes * 60 + seconds <= op_end_sec){
                minutes = Integer.parseInt(op_end.split(":")[0]);
                seconds = Integer.parseInt(op_end.split(":")[1]);
            }
        }
        
        if(0 <= minutes && minutes < 10){
            answer += "0" + minutes;
        }else {
            answer += minutes;
        }
        
        answer += ":";
        
        if(0 <= seconds && seconds < 10){
            answer += "0" + seconds;
        }else {
            answer += seconds;
        }
        
        return answer;
    }
    
    public int[] prev_func(int minutes, int seconds){
        int[] time = new int[] {minutes, seconds};
        
        if(seconds < 10){
            time[0] -= 1;
            time[1] += 50;
        }else {
            time[1] -= 10;
        }
        
        return time;
    }
    
    public int[] next_func(int minutes, int seconds){
        int[] time = new int[] {minutes, seconds};
        
        if(seconds >= 50){
            time[0] += 1;
            time[1] -= 50;
        }else {
            time[1] += 10;
        }
        
        return time;
    }
}