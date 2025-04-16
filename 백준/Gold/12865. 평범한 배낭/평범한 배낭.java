import java.io.*;
import java.util.*;

public class Main {
    static class Item implements Comparable<Item> {
        int W;
        int V;

        Item(int W, int V) {
            this.W = W;
            this.V = V;
        }

        public int compareTo(Item o) {
            if (this.W == o.W) {
                return o.V - this.V;
            }
            return this.W - o.W;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Item> queue = new PriorityQueue<>();

        int W = 0, V = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            queue.offer(new Item(W, V));
        }

        int[] dp = new int[K + 1];

        Item now;
        while (!queue.isEmpty()) {
            now = queue.poll();

            for (int i = K; i >= now.W; i--) {
                if (dp[i] < dp[i - now.W] + now.V) {
                    dp[i] = dp[i - now.W] + now.V;
                }
            }
        }

        System.out.println(dp[K]);
    }
}