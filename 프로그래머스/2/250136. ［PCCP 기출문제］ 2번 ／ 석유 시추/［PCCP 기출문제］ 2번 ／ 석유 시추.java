import java.util.*;
class Solution {
    int[][] copy_land;
    
    int max_r, max_c, cnt;
    int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
    int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        copy_land = land;
        max_r = land.length;
        max_c = land[0].length;
        int answer = 0;
        
        Map<Integer, Integer> oil = new HashMap<>();
        int region = 1;
        
        Set<Integer> visited = new HashSet<>();
        int mid_result = 0;
        
        for(int c = 0; c < max_c; c++){
            mid_result = 0;
            for(int r = 0; r < max_r; r++){
                if(copy_land[r][c] == 1){
                    region++;
                    cnt = 1;
                    bfs(region, r, c);
                    
                    oil.put(region, cnt);
                    
                    mid_result += cnt;
                    visited.add(region);
                } else if(copy_land[r][c] > 1 
                          && !visited.contains(copy_land[r][c])) {
                    mid_result += oil.get(copy_land[r][c]);
                    visited.add(copy_land[r][c]);                    
                }
            }
            
            if(mid_result > answer){
                answer = mid_result;
            }
            
            visited = new HashSet<>();
        }
        
        return answer;
    }
    
    public void bfs(int region, int r, int c) {
        copy_land[r][c] = region;
        
        for(int i = 0; i < 4; i++){
            if(0 <= r + dr[i] && r + dr[i] < max_r 
               && 0 <= c + dc[i] && c + dc[i] < max_c 
               && copy_land[r+dr[i]][c+dc[i]] == 1){
                cnt++;
                bfs(region, r + dr[i], c + dc[i]);
            }
        }
    }
}