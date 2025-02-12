import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> edges;
    static int[] subTree;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        edges = new HashMap<>();
        subTree = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            edges.put(i, new ArrayList<>());
            subTree[i] = 1;
        }

        int a, b = 0;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        findTree(R);

        int U = 0;
        for (int i = 0; i < Q; i++) {
            U = Integer.parseInt(br.readLine());
            System.out.println(subTree[U]);
        }
    }

    public static void findTree(int node) {
        visited[node] = true;

        int size = edges.get(node).size();

        if (size == 0) {
            return;
        }

        int num = 0;
        for (int i = 0; i < size; i++) {
            num = edges.get(node).get(i);
            if (!visited[num]) {
                findTree(num);
                subTree[node] += subTree[num];
            }
        }
    }
}
