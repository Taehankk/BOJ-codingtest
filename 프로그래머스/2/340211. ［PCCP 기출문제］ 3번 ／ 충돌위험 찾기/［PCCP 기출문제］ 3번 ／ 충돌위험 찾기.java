import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        Set<String> location = new HashSet<>();
        Set<String> collision = new HashSet<>();
        
        int now;
        int r_time = 0;
        int c_time = 0;
        int r, c, nr, nc;
        String loc;
        int before_size;
        for(int i = 0; i < routes.length; i++){
            now = 0;
            loc = Integer.toString(now) + "-" + Integer.toString(points[routes[i][0]-1][0]) + "-" + Integer.toString(points[routes[i][0]-1][1]);
            
            before_size = location.size();
            location.add(loc);      

            if(before_size == location.size()){
                collision.add(loc);
            }
            
            for(int j = 1; j < routes[i].length; j++){
                r = points[routes[i][j-1] - 1][0];
                c = points[routes[i][j-1] - 1][1];
                
                r_time = Math.abs(points[routes[i][j] - 1][0] - points[routes[i][j-1] - 1][0]);
                c_time = Math.abs(points[routes[i][j] - 1][1] - points[routes[i][j-1] - 1][1]);
                
                nr = r;
                nc = c;
                for(int t = 0; t < r_time; t++){
                    loc = "";
                    now++;
                    if(points[routes[i][j] - 1][0] - points[routes[i][j-1] - 1][0] > 0){
                        nr++;                        
                    }else {
                        nr--;
                    }
                    
                    loc = Integer.toString(now) + "-" + Integer.toString(nr) + "-" + Integer.toString(nc);
                    
                    before_size = location.size();
                    location.add(loc);
                    
                    if(before_size == location.size()){
                        collision.add(loc);
                    }
                }
                
                for(int t = 0; t < c_time; t++) {
                    loc = "";
                    now++;
                    
                    if(points[routes[i][j] - 1][1] - points[routes[i][j-1] - 1][1] > 0){
                        nc++;                        
                    }else {
                        nc--;
                    }
                    
                    loc = Integer.toString(now) + "-" + Integer.toString(nr) + "-" + Integer.toString(nc);
                    
                    before_size = location.size();
                    location.add(loc);
                    
                    if(before_size == location.size()){
                        collision.add(loc);
                    }
                }
            }
        }
        
        answer = collision.size();
        return answer;
    }
}