import java.io.*;
import java.util.*;

public class Main {
    static int N, W;
    static int[] buildTimes, maxTimes, indegree;
    static List<Integer>[] routes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int K;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            buildTimes = new int[N + 1];
            maxTimes = new int[N + 1];
            indegree = new int[N + 1];
            routes = new ArrayList[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                buildTimes[j] = Integer.parseInt(st.nextToken());
                routes[j] = new ArrayList<>();
            }

            int a, b;
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                routes[a].add(b);
                indegree[b]++;
            }

            W = Integer.parseInt(br.readLine());

            building();

            System.out.println(maxTimes[W]);
        }
    }

    public static void building() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                maxTimes[i] = buildTimes[i];
            }
        }

        int idx;
        while (!queue.isEmpty()) {
            idx = queue.poll();

            for (int i = 0; i < routes[idx].size(); i++) {
                maxTimes[routes[idx].get(i)] = Math.max(maxTimes[idx] + buildTimes[routes[idx].get(i)], maxTimes[routes[idx].get(i)]);
                indegree[routes[idx].get(i)]--;
                if(indegree[routes[idx].get(i)] == 0){
                    queue.offer(routes[idx].get(i));
                }
            }
        }
    }
}