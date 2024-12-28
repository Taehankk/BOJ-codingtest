class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int min = 1;
        int max = 0;
        
        for(int i = 0; i < times.length; i++){
            if(max < diffs[i]){
                max = diffs[i];
            }
        }
        
        int level = 1;
        long time = 0;
        
        while(max >= min){
            level = (min + max) / 2;
            
            // System.out.println(min + " / " + max);
            
            time = 0;
            for(int i = 0; i < diffs.length; i++){  
                if(diffs[i] <= level){
                    time += times[i];
                }else {
                    time += (times[i-1] + times[i]) * (diffs[i] - level) + times[i];
                }
            }
            
            if(time > limit){
                min = level + 1;
            } else {
                max = level - 1;
            }
        }

        answer = min;
        
        
        return answer;
    }
}