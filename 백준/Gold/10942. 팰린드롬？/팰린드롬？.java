import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int S, E, left, right = 0;
        next:
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            while (S <= E) {
                if (numbers[S] != numbers[E]) {
                    bw.append("0");
                    bw.append("\n");
                    continue next;
                }
                S++;
                E--;
            }

            bw.append("1");
            bw.append("\n");
        }

        bw.flush();
        bw.close();
    }
}