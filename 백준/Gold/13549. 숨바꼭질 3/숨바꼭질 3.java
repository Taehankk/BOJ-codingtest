import java.io.*;
import java.util.*;

public class Main {
    static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = Integer.MAX_VALUE;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {N, 0});

        int[] max_time = new int[100001];
        Arrays.fill(max_time, Integer.MAX_VALUE);
        max_time[N] = 0;

        int[] check;
        while(!queue.isEmpty()) {
            check = queue.poll();

            if(check[0] == K && result > check[1]) {
                result = check[1];
            } else {
                if(check[0] > 0 && max_time[check[0] - 1] > check[1] + 1) {
                    max_time[check[0] - 1] = check[1] + 1;
                    queue.offer(new int[] {check[0] - 1, check[1] + 1});
                }
                
                if(check[0] < 100000 && max_time[check[0] + 1] > check[1] + 1) {
                    max_time[check[0] + 1] = check[1] + 1;
                    queue.offer(new int[] {check[0] + 1, check[1] + 1});
                }
                
                if(check[0] <= 50000 && max_time[check[0] * 2] > check[1]) {
                    max_time[check[0] * 2] = check[1];
                    queue.offer(new int[] {check[0] * 2, check[1]});
                }
            }
            
        }

        System.out.println(result);
    }
}