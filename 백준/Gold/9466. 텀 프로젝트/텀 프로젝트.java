import java.io.*;
import java.util.*;

public class Main {
    static int result;
    static int[] select;
    static boolean[] done, visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        int n, teamNo;
        for (int test = 0; test < T; test++) {
            n = Integer.parseInt(br.readLine());
            result = n;
            select = new int[n + 1];
            done = new boolean[n + 1];
            visited = new boolean[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                select[i] = Integer.parseInt(st.nextToken());
                if (select[i] == i) {
                    done[i] = true;
                    result--;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (!done[i]) {
                    makeTeam(i);
                }
            }

            bw.append(result + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void makeTeam(int idx) {
        if (done[idx]) return;
        if (visited[idx]) {
            done[idx] = true;
            result--;
        }

        visited[idx] = true;
        makeTeam(select[idx]);
        visited[idx] = false;

        done[idx] = true;
    }
}