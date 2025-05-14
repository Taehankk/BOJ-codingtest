import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String sub1 = br.readLine();
        String sub2 = br.readLine();

        int length1 = sub1.length();
        int length2 = sub2.length();

        int[][] dp = new int[length1 + 1][length2 + 1];
        int lcs_length = 0;

        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if (sub1.charAt(i) == sub2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        System.out.println(dp[length1][length2]);
    }
}