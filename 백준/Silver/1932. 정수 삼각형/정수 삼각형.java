import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int size = N % 2 == 0 ? (1 + N) * (N/2) : (1 + N) * (N/2) + (1 + N) / 2;
        int[] dp = new int[size];
    
        dp[0] = Integer.parseInt(br.readLine());
        int idx = 1;
        int start = 0, end = 0, num = 0;
        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++) {
                num = Integer.parseInt(st.nextToken());

                if(start <= idx - i - 1) {
                    dp[idx] = dp[idx - i - 1] + num > dp[idx] ? dp[idx - i - 1] + num : dp[idx];
                }

                if(idx - i <= end) {
                    dp[idx] = dp[idx - i] + num > dp[idx] ? dp[idx - i] + num : dp[idx];
                }

                idx++;
            }
            
            start = idx - i - 1;
            end = idx - 1;
        }

        // System.out.println(Arrays.toString(dp));
        int result = 0;
        for(int i = size - N; i < size; i++) {
            result = dp[i] > result ? dp[i] : result;
        }

        System.out.println(result);
    }
}
