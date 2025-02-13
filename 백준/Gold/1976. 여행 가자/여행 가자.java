import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String result = "YES";

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int connect;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                connect = Integer.parseInt(st.nextToken());

                if (connect == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int check = findParent(Integer.parseInt(st.nextToken()));
        int city;
        for (int i = 1; i < M; i++) {
            city = Integer.parseInt(st.nextToken());
            if (findParent(city) != check) {
                result = "NO";
                break;
            }
        }

        System.out.println(result);
    }

    public static void union(int a, int b) {
        int aParent = findParent(a);
        int bParent = findParent(b);

        if (aParent != bParent) {
            parent[bParent] = aParent;
        }
    }

    public static int findParent(int idx) {
        if (parent[idx] != idx) {
            parent[idx] = findParent(parent[idx]);
        }
        return parent[idx];
    }
}
