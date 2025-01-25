class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        int startSec = h1 * 3600 + m1 * 60 + s1;
        int endSec = h2 * 3600 + m2 * 60 + s2;
        
        answer += endSec * 719 / 43200 + endSec * 59 / 3600;
        answer -= startSec * 719 / 43200 + startSec * 59 / 3600;
        
        if(startSec * 719 / 43200 == 0 || startSec * 59 / 3600 == 0){
            answer++;
        }
        
        if(startSec < 43200 && endSec > 43200){
            answer--;
        }
        
        if(startSec == 43200){
            answer++;
        }
        
        if(endSec == 43200){
            answer--;
        }
        return answer;
    }
}