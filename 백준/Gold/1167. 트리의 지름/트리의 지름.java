import java.io.*;
import java.util.*;

public class Main {
    static List<List<int[]>> edge;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        edge = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            edge.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];

        int start, end, cost;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            while(end > 0) {
                cost = Integer.parseInt(st.nextToken());

                edge.get(start).add(new int[] {end, cost});
                
                end = Integer.parseInt(st.nextToken());
            }
        }
        
        int[] result = bfs(1);

        Arrays.fill(visited, false);

        result = bfs(result[0]);

        System.out.println(result[1]);
    }

    public static int[] bfs(int start) {
        visited[start] = true;
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] {start, 0});
        
        List<int[]> nextEdge = edge.get(start);
        int[] check, next;
        int maxIdx = 0, maxCost = 0;
        while(!queue.isEmpty()) {
            check = queue.poll();

            if(check[1] > maxCost) {
                maxIdx = check[0];
                maxCost = check[1];
            }

            nextEdge = edge.get(check[0]);
            for(int i = 0; i < nextEdge.size(); i++) {
                next = nextEdge.get(i);

                if(!visited[next[0]]) {
                    visited[next[0]] = true;
                    queue.offer(new int[] {next[0], check[1] + next[1]});                    
                }
            }
        }

        return new int[] {maxIdx, maxCost};
    }
}