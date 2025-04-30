import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] tree;
    static boolean[] visited;
    static int max, endNode;

    static class Node {
        int idx;
        int value;

        Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        public String toString() {
            return "Node : " + idx + " / " + value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        int node1, node2, value;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            value = Integer.parseInt(st.nextToken());

            tree[node1].add(new Node(node2, value));
            tree[node2].add(new Node(node1, value));
        }

        max = endNode = 0;
        dfs(1, 0);

        max = 0;
        dfs(endNode, 0);

        System.out.println(max);
    }

    public static void dfs(int idx, int value) {
        int child_cnt = tree[idx].size();

        visited[idx] = true;

        Node check;
        for (int i = 0; i < child_cnt; i++) {
            check = tree[idx].get(i);
            if(!visited[check.idx]) {
                visited[check.idx] = true;
                dfs(check.idx, value + check.value);
            }
        }

        if(max < value) {
            max = value;
            endNode = idx;
        }

        visited[idx] = false;
    }
}
