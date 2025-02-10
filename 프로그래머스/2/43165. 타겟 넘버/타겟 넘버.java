class Solution {
    int answer, target_num;
    int[] numbers_copy;
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        numbers_copy = new int[numbers.length];
        
        numbers_copy = numbers;
        target_num = target;
        
        dfs(0, 0);
        return answer;
    }
    
    public void dfs(int idx, int sum) {
        if(idx >= numbers_copy.length){
            if(sum == target_num){
                answer++;
            }
            return;
        }
        
        dfs(idx + 1, sum + numbers_copy[idx]);
        dfs(idx + 1, sum - numbers_copy[idx]);
    }
}