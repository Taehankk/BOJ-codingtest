import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] table = new int[N][N];
        int[][] sum = new int[N][N];

        int rowSum = 0, colSum = 0;
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                table[r][c] = Integer.parseInt(st.nextToken());

                if(r == 0) {
                    rowSum += table[r][c];
                    sum[r][c] = rowSum;

                    if(c == 0) {
                        colSum += table[r][c];
                    }
                }else if(c == 0) {
                    colSum += table[r][c];
                    sum[r][c] = colSum;
                }else {
                    sum[r][c] = sum[r - 1][c] + sum[r][c - 1] - sum[r - 1][c - 1] + table[r][c];
                }
            }
        }
        
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0, tcSum = 0;
        for(int tc = 0; tc < M; tc++) {
            st = new StringTokenizer(br.readLine());

            x1 = Integer.parseInt(st.nextToken()) - 1;
            y1 = Integer.parseInt(st.nextToken()) - 1;
            x2 = Integer.parseInt(st.nextToken()) - 1;
            y2 = Integer.parseInt(st.nextToken()) - 1;

            tcSum = sum[x2][y2];
            if(x1 > 0) {
                tcSum -= sum[x1 - 1][y2];
            }

            if(y1 > 0) {
                tcSum -= sum[x2][y1 - 1];
            }

            if(x1 > 0 && y1 > 0) {
                tcSum += sum[x1 - 1][y1 - 1];
            }

            bw.write(tcSum + "\n");
        }

        bw.flush();
        bw.close();
    }
}