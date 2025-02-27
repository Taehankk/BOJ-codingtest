import java.util.*;

class Solution {
    Stack<Integer> delStack;
    int[] prevRow, nextRow;
    boolean[] deleted;
    public String solution(int n, int k, String[] cmd) {        
        delStack = new Stack<>();
        prevRow = new int[n];
        nextRow = new int[n];
        for(int i = 0; i < n; i++){
            prevRow[i] = i-1;
            nextRow[i] = i+1;
        }
        
        deleted = new boolean[n];
        
        StringTokenizer st;
        String command = "";
        int move = 0;        
        for(int i = 0; i < cmd.length; i++) {
            st = new StringTokenizer(cmd[i]);
            
            command = st.nextToken();
            if(st.hasMoreTokens()){
                move = Integer.parseInt(st.nextToken());                
            }
            
            switch(command){
                case "U":
                    k = upSelect(k, move);
                    break;
                case "D":
                    k = downSelect(k, move, n);
                    break;
                case "C":
                    k = delete(k, n);
                    break;
                case "Z":
                    k = ctrlZ(k, n);
                    break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < n; i++){
            if(deleted[i]){
                sb.append("X");
            }else {
                sb.append("O");
            }
        }
        
        return sb.toString();
    }
    
    int upSelect(int now, int x) {
        int cnt = 1;
        while(cnt <= x){
            now = prevRow[now];
            
            cnt++;
        }
        
        return now;
    }
    
    int downSelect(int now, int x, int max) {
        int cnt = 1;
        while(cnt <= x){
            now = nextRow[now];
            
            cnt++;
        }
        
        return now;
    }
    
    int delete(int now, int size) {
        delStack.push(now);
        deleted[now] = true;
        
        if(prevRow[now] != -1) nextRow[prevRow[now]] = nextRow[now];
        if(nextRow[now] != size) prevRow[nextRow[now]] = prevRow[now];
        
        if(nextRow[now] >= size){
            return prevRow[now];
        }
        return nextRow[now];
    }
    
    int ctrlZ(int now, int size) {
        int recover = delStack.pop();
        deleted[recover] = false;
        
        if(nextRow[recover] != size) prevRow[nextRow[recover]] = recover;
        if(prevRow[recover] != -1) nextRow[prevRow[recover]] = recover;
        
        return now;
    }
}