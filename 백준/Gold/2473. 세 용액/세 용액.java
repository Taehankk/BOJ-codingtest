import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static long value;
    static long[] solutions, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        solutions = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(solutions);

        result = new long[3];

        value = Long.MAX_VALUE;

        int first, second, third;
        long check;

        fin:
        for (int i = 0; i < N - 2; i++) {
            first = i;
            second = i + 1;
            third = N - 1;

            while (second < third) {
                check = solutions[first] + solutions[second] + solutions[third];

                if (Math.abs(check) < Math.abs(value)) {
                    value = check;
                    result[0] = solutions[first];
                    result[1] = solutions[second];
                    result[2] = solutions[third];
                }

                if (check > 0) {
                    third--;
                } else if (check < 0) {
                    second++;
                } else {
                    break fin;
                }
            }
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}