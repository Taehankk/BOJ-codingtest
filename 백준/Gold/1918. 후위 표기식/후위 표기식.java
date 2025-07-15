import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        int length = expression.length();

        Stack<Character> stack = new Stack<>();
        
        Map<Character, Integer> priority = new HashMap<>();
        priority.put('*', 2);
        priority.put('/',2);
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('(', 0);

        
        String answer = "";
        for(int i = 0; i < length; i++) {
            char check = expression.charAt(i);
            if(check - 'A' >= 0) {
                answer += check;
            }else if(check == '(') {
                stack.push(check);
            }else if(check == ')') {
                while(stack.peek() != '('){
                    answer += stack.pop();
                }
                stack.pop();
            }else {
                if(stack.isEmpty()) {
                    stack.push(check);
                }else {
                    while(true) {
                        if(stack.isEmpty() || priority.get(stack.peek()) < priority.get(check)) {
                            stack.push(check);
                            break;
                        }else {
                            answer += stack.pop();
                        }
                    }
                }
            }
        }

        while(!stack.isEmpty()) {
            answer += stack.pop();
        }

        System.out.println(answer);
    }
}
