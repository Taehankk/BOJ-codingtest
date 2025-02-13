import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> edges;
    static char[] group;
    static String result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());

        int V, E, a, b;
        next: for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            result = "YES";

            edges = new HashMap<>();
            for (int j = 1; j <= V; j++) {
                edges.put(j, new ArrayList<>());
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                edges.get(a).add(b);
                edges.get(b).add(a);
            }

            group = new char[V + 1];
            for (int j = 1; j <= V; j++) {
                if (group[j] == 0) {
                    group[j] = 'a';
                    partialGroup(j);
                    if(result.equals("NO")){
                        System.out.println(result);
                        continue next;
                    }
                }
            }

            System.out.println(result);
        }
    }

    public static void partialGroup(int node) {
        int size = edges.get(node).size();

        int check;
        for (int i = 0; i < size; i++) {
            check = edges.get(node).get(i);

            if(group[check] == 0){
                group[check] = group[node] == 'a' ? 'b' : 'a';
                partialGroup(check);
            }else {
                if(group[check] == group[node]){
                    result = "NO";
                    return;
                }
            }
        }
    }
}