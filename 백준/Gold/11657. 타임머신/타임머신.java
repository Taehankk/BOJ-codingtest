import java.io.*;
import java.util.*;

public class Main {
    static class Bus {
        int start;
        int end;
        int time;

        Bus(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }

        public String toString() {
            return "Bus : " + start + " -> " + end + " / " + time + "h";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Bus[] busList = new Bus[M];
        long[] timeCost = new long[N + 1];

        boolean loop = false;

        for (int i = 2; i <= N; i++) {
            timeCost[i] = Long.MAX_VALUE;
        }

        int start, end, time;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());

            busList[i] = new Bus(start, end, time);
        }

        Bus check;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                check = busList[j];
                if (timeCost[check.start] != Long.MAX_VALUE && timeCost[check.end] > timeCost[check.start] + check.time) {
                    timeCost[check.end] = timeCost[check.start] + check.time;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            check = busList[i];
            if (timeCost[check.start] != Long.MAX_VALUE && timeCost[check.end] > timeCost[check.start] + check.time) {
                loop = true;
                break;
            }
        }

        if (loop) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (timeCost[i] == Long.MAX_VALUE) {
                    sb.append(-1).append("\n");
                    continue;
                }
                sb.append(timeCost[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}