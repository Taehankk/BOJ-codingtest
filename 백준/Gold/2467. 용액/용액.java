import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] solutions = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solutions);

        int i = 0;
        int j = N - 1;
        int value = Integer.MAX_VALUE;
        int[] result = new int[2];
        while (i < j) {
            if (Math.abs(solutions[i] + solutions[j]) < Math.abs(value)) {
                result[0] = solutions[i];
                result[1] = solutions[j];
                value = solutions[i] + solutions[j];
            }

            if (Math.abs(solutions[i]) < Math.abs(solutions[j])) {
                j--;
            } else {
                i++;
            }

        }

        System.out.println(result[0] + " " + result[1]);
    }

}