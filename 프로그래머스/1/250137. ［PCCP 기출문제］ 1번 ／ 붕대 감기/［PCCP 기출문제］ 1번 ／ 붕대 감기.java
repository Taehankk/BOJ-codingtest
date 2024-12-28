class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int max_health = health;
        
        int last_attack = 0;
        
        int attack_time = 0;
        int damage = 0;
        
        int heal_time = 0;
        int heal = bandage[1];
        
        for(int i = 0; i < attacks.length; i++){
            attack_time = attacks[i][0];
            damage = attacks[i][1];
            
            heal_time = attack_time - last_attack - 1;
            
            if(heal_time >= bandage[0]) {
                health += (heal_time/bandage[0]) * bandage[2];
            }
            
            health += heal_time * bandage[1];
            
            if(health > max_health) {
                health = max_health;
            }
            
            health -= damage;
            
            if(health <= 0) {
                answer = -1;
                break;
            }
            
            last_attack = attack_time;
        }
        
        if(answer != -1){
            answer = health;    
        }        
        
        return answer;
    }
}