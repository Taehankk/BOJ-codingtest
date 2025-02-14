import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int node1, node2, value;

        Edge(int node1, int node2, int value) {
            this.node1 = node1;
            this.node2 = node2;
            this.value = value;
        }

        public String toString() {
            return "(" + node1 + "," + node2 + "," + value + ")";
        }

        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int result = 0;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        int node1, node2, value;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            value = Integer.parseInt(st.nextToken());

            edges.offer(new Edge(node1, node2, value));
        }

        Edge edge;
        while (!edges.isEmpty()) {
            edge = edges.poll();
            if (findParent(edge.node1) != findParent(edge.node2)) {
                union(edge.node1, edge.node2);
                result += edge.value;
            }
        }

        System.out.println(result);
    }

    public static void union(int node1, int node2) {
        int parent1 = findParent(node1);
        int parent2 = findParent(node2);

        if (parent1 != parent2) {
            parent[parent2] = parent1;
        }
    }

    public static int findParent(int node) {
        if (node != parent[node]) {
            parent[node] = findParent(parent[node]);
        }

        return parent[node];
    }
}