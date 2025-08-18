import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        long[] dot1 = new long[2], dot2 = new long[2], dot3 = new long[2];
        
        st = new StringTokenizer(br.readLine());
        dot1[0] = Long.parseLong(st.nextToken());
        dot1[1] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        dot2[0] = Long.parseLong(st.nextToken());
        dot2[1] = Long.parseLong(st.nextToken());

        long result = 0;
        for(int i = 2; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dot3[0] = Long.parseLong(st.nextToken());
            dot3[1] = Long.parseLong(st.nextToken());

            result += calArea(dot1, dot2, dot3);

            dot2[0] = dot3[0];
            dot2[1] = dot3[1];
        }
        
        System.out.printf("%.1f%n", (Math.abs(result) / 2.0));
    }

    public static long calArea(long[] dot1, long[] dot2, long[] dot3) {
        long cal = dot1[0] * (dot2[1] - dot3[1]) + dot2[0] * (dot3[1] - dot1[1]) + dot3[0] * (dot1[1] - dot2[1]);
        return cal;
    }
}