import java.io.*;
import java.util.*;

public class Main {
    static class City implements Comparable<City> {
        int cost;
        int people;

        City(int cost, int people) {
            this.cost = cost;
            this.people = people;
        }

        @Override
        public int compareTo(City o) {
            if(this.cost == o.cost) {
                return o.people - this.people;
            }

            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "City : " + people + "P BY $" + cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[C+100];
        Arrays.fill(dp, 100000000);
        dp[0] = 0;

        PriorityQueue<City> queue = new PriorityQueue<>();
        int cost, people;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost = Integer.parseInt(st.nextToken());
            people = Integer.parseInt(st.nextToken());

            queue.offer(new City(cost, people));
        }

        City check;
        while(!queue.isEmpty()) {
            check = queue.poll();

            for(int i = check.people; i < C+100; i++) {
                if(dp[i] > dp[i - check.people] + check.cost) {
                    dp[i] = dp[i - check.people] + check.cost;
                }
            }
        }

        int min = dp[C];
        for(int i = C+1; i < C+100; i++) {
            if(min > dp[i]) {
                min = dp[i];
            }
        }

        System.out.println(min);
    }
}