class Solution {
    public int solution(String s) {
        int answer = 0;
        String finalAnswer = "";
        
        char check;
        String midNum = "";
        for(int i = 0; i < s.length(); i++){
            check = s.charAt(i);
            
            if(0 <= check - '0' && check - '0' <= 9) {
                finalAnswer += check;
            }else {
                midNum += check;
            }
            
            if(midNum.equals("zero")){
                finalAnswer += "0";
                midNum = "";
            }else if(midNum.equals("one")){
                finalAnswer += "1";
                midNum = "";
            }else if(midNum.equals("two")){
                finalAnswer += "2";
                midNum = "";
            }else if(midNum.equals("three")){
                finalAnswer += "3";
                midNum = "";
            }else if(midNum.equals("four")){
                finalAnswer += "4";
                midNum = "";
            }else if(midNum.equals("five")){
                finalAnswer += "5";
                midNum = "";
            }else if(midNum.equals("six")){
                finalAnswer += "6";
                midNum = "";
            }else if(midNum.equals("seven")){
                finalAnswer += "7";
                midNum = "";
            }else if(midNum.equals("eight")){
                finalAnswer += "8";
                midNum = "";
            }else if(midNum.equals("nine")){
                finalAnswer += "9";
                midNum = "";
            }
        }
        
        answer = Integer.parseInt(finalAnswer);
        return answer;
    }
}