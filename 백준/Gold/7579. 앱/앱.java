import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] memories = new int[N];
        int[] cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int maxCost = 0;
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            maxCost += cost[i];
        }

        int[][] dpArray = new int[N][maxCost + 1];
        for (int i = 0; i < maxCost + 1; i++) {
            if (i >= cost[0]) {
                dpArray[0][i] = memories[0];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < maxCost + 1; j++) {
                if (j < cost[i]) {
                    dpArray[i][j] = dpArray[i - 1][j];
                } else {
                    dpArray[i][j] = Math.max(dpArray[i - 1][j], dpArray[i - 1][j - cost[i]] + memories[i]);
                }
            }
        }

        for (int i = 0; i < maxCost + 1; i++) {
            if (dpArray[N - 1][i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}