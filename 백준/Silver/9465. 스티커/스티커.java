import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int cols = 0;
        for(int test = 1; test <= T; test++) {
            cols = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][cols];
            int[][] sum = new int[2][cols];

            for(int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < cols; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sum[0][0] = stickers[0][0];
            sum[1][0] = stickers[1][0];

            for(int i = 1; i < cols; i++) {
                sum[0][i] = sum[1][i - 1] + stickers[0][i];
                sum[1][i] = sum[0][i - 1] + stickers[1][i];

                if(i > 1) {
                    sum[0][i] = sum[1][i - 2] + stickers[0][i] > sum[0][i] ? sum[1][i - 2] + stickers[0][i] : sum[0][i];
                    sum[1][i] = sum[0][i - 2] + stickers[1][i] > sum[1][i] ? sum[0][i - 2] + stickers[1][i] : sum[1][i];
                }
            }

            bw.write((sum[0][cols - 1] > sum[1][cols - 1] ? sum[0][cols - 1] : sum[1][cols - 1]) + "\n");
        }

        bw.flush();
        bw.close();
    }    
}