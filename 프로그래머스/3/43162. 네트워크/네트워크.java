class Solution {
    int[][] computers_copy;
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        computers_copy = computers;
        visited = new boolean[computers.length];
        
        for(int i = 0; i < computers.length; i++){
            if(!visited[i]){
                findNetwork(i);
                answer++;
            }            
        }
        return answer;
    }
    
    public void findNetwork(int idx){
        for(int i = 0; i < computers_copy.length; i++){
            if(i != idx && computers_copy[idx][i] == 1 && !visited[i]){
                visited[i] = true;
                findNetwork(i);
            }
        }
    }
}