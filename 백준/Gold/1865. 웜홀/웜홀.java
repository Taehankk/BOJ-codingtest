import java.io.*;
import java.util.*;

public class Main {
    static class Path {
        int start;
        int end;
        Long time;
        
        Path(int start, int end, Long time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    
        @Override
        public String toString() {
            return "[Path] : " + start + " -> " + end + " / $" + time;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());       
        
        int N, M, W, S, E;
        Long T;
        for(int test = 1; test <= TC; test++) {
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            Queue<Path> queue = new LinkedList<>();

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken()) - 1;
                E = Integer.parseInt(st.nextToken()) - 1;
                T = Long.parseLong(st.nextToken());

                queue.offer(new Path(S, E, T));
                queue.offer(new Path(E, S, T));
            }

            for(int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken()) - 1;
                E = Integer.parseInt(st.nextToken()) - 1;
                T = -Long.parseLong(st.nextToken());

                queue.offer(new Path(S, E, T));
            }
            
            Long[] time = new Long[N];
            Arrays.fill(time, (long) 0);
            
            boolean update;
            Path path;
            int cnt = 0, size = queue.size();
            while(cnt < N-1) {
                update = false;
                for(int i = 0; i < size; i++) {
                    path = queue.poll();
                    
                    if(time[path.end] > time[path.start] + path.time) {
                        time[path.end] = time[path.start] + path.time;
                        update = true;
                    }

                    queue.offer(path);
                }                

                if(!update) {
                    break;
                }

                cnt++;
            }

            String result = "NO";
            for(int i = 0; i < size; i++) {
                    path = queue.poll();
                    
                    if(time[path.end] > time[path.start] + path.time) {
                        result = "YES";
                        break;
                    }
            }
            
            System.out.println(result);
        }
    }
}
