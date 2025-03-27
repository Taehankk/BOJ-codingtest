import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());

        PriorityQueue<Integer> smSharks = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> bigSharks = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        int shark = 0;
        for (int i = 0; i < N; i++) {
            shark = Integer.parseInt(st.nextToken());
            if (shark < T) {
                smSharks.offer(shark);
            } else {
                bigSharks.offer(shark);
            }
        }

        int cnt = 0;
        while (cnt < K) {
            if (!bigSharks.isEmpty() && bigSharks.peek() >= T) {
                if (!smSharks.isEmpty()) {
                    T += smSharks.poll();
                } else {
                    break;
                }
                cnt++;
            } else {
                if (!bigSharks.isEmpty()) {
                    smSharks.offer(bigSharks.poll());
                } else if (!smSharks.isEmpty()) {
                    T += smSharks.poll();
                    cnt++;
                } else {
                    break;
                }
            }
        }

        System.out.println(T);
    }
}
