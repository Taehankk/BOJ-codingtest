import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int[] result = new int[N];

        st = new StringTokenizer(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int number = 0;
        stack.push(idx++);
        while (idx < N) {
            while (!stack.isEmpty()) {
                if (numbers[stack.peek()] < numbers[idx]) {
                    number = stack.pop();
                    result[number] = numbers[idx];
                } else {
                    break;
                }
            }
            stack.push(idx++);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        for (int i = 0; i < N; i++) {
            bw.write(result[i] + " ");
        }

        bw.write("\n");
        bw.flush();
    }
}