import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static class Lecture implements Comparable<Lecture> {
        int start;
        int end;

        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }

        @Override
        public String toString() {
            return "Lecture [start=" + start + ", end=" + end + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        Queue<Lecture> queue = new PriorityQueue<>();
        Queue<Integer> endTimeQueue = new PriorityQueue<>();

        int start, end;

        next:
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            queue.offer(new Lecture(start, end));
        }

        endTimeQueue.add(queue.poll().end);

        Lecture temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (endTimeQueue.peek() > temp.start) {
                endTimeQueue.add(temp.end);
            } else {
                endTimeQueue.poll();
                endTimeQueue.add(temp.end);
            }
        }

        System.out.println(endTimeQueue.size());

    }
}
