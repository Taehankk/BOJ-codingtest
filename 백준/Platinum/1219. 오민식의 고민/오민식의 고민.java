import java.io.*;
import java.util.*;

public class Main {
    static class Transport {
        int start;
        int end;
        int price;

        Transport(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }

        public String toString() {
            return "Transport : " + start + " -> " + end + " / $" + price;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Transport[] trans_list = new Transport[M];
        int[] earn = new int[N];
        long[] cost = new long[N];

        int a, b, price;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            price = Integer.parseInt(st.nextToken());

            trans_list[i] = new Transport(a, b, price);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            earn[i] = Integer.parseInt(st.nextToken());
            cost[i] = Long.MIN_VALUE;
        }

        cost[start] = earn[start];

        Transport check;
        for (int i = 0; i <= N + 100; i++) {
            for (int j = 0; j < M; j++) {
                check = trans_list[j];

                if (cost[check.start] == Long.MAX_VALUE) {
                    cost[check.end] = Long.MAX_VALUE;
                    continue;
                }

                if (cost[check.start] != Long.MIN_VALUE && cost[check.end] < cost[check.start] + earn[check.end] - check.price) {
                    if(i >= N){
                        cost[check.end] = Long.MAX_VALUE;
                        continue;
                    }
                    cost[check.end] = cost[check.start] + earn[check.end] - check.price;
                }
            }
        }


        if (cost[destination] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else if (cost[destination] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else {
            System.out.println(cost[destination]);
        }

    }
}