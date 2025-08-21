import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] num = new int[N];
        int result = N;

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            sum += num[i];

            if(num[i] >= S) {
                result = 1;
                break;
            }
        }

        if(sum < S) {
            result = 0;
        } else if (result != 1) {
            int left = 0, right = 1;
            sum = num[0] + num[1];

            while(left < right) {
                if(sum < S && right < N - 1) {
                    right++;
                    sum += num[right];
                } else {                    
                    sum -= num[left++];
                }

                if(sum >= S && result > right - left + 1) {
                    result = right - left + 1;
                }
            }
        }

        System.out.println(result);
    }
}