import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = {1, 1, 1, 1, 1};
        
        int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dc = {0, -1, 0, 1};
        
        char[][] room = new char[5][5];
        String reader = "";
        Queue<int[]> queue;
        int[] check;
        int nr, nc;
        next: for(int i = 0; i < 5; i++){
            queue = new LinkedList<>();
            for(int j = 0; j < 5; j++){
                reader = places[i][j];
                for(int k = 0; k < 5; k++){
                    room[j][k] = reader.charAt(k);
                    
                    if(room[j][k] == 'P'){
                        queue.offer(new int[] {j, k, 0});
                    }
                }
            }
            
            while(!queue.isEmpty()){
                check = queue.poll();
                
                for(int d = 0; d < 4; d++){
                    nr = check[0] + dr[d];
                    nc = check[1] + dc[d];
                    
                    if(0 <= nr && nr < 5 && 0 <= nc && nc < 5){
                        if(room[nr][nc] == 'O'){
                            for(int d2 = 0; d2 < 4; d2++){
                                if((d2 + 2) % 4 != d){
                                    if(0 <= nr + dr[d2] && nr + dr[d2] < 5 && 0 <= nc + dc[d2] && nc + dc[d2] < 5){
                                        if(room[nr+dr[d2]][nc+dc[d2]] == 'P'){                                                                                     answer[i] = 0;
                                            continue next;
                                        }
                                    }
                                }
                            }
                        }else if(room[nr][nc] == 'P'){                                                                                             answer[i] = 0;
                            continue next;
                        }
                    }
                }
            }
            
            answer[i] = 1;
        }
        return answer;
    }
}