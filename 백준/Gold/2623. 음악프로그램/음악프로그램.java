import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> timeTable = new HashMap<>();
        int[] indegree = new int[N+1];

        for (int i = 1; i <= N; i++) {
            timeTable.put(i, new LinkedList<>());
        }

        int num = 0;
        int prev, next;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            num = Integer.parseInt(st.nextToken());
            prev = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num - 1; j++) {
                next = Integer.parseInt(st.nextToken());
                timeTable.get(prev).add(next);
                indegree[next]++;

                prev = next;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        Queue<Integer> answer = new LinkedList<>();
        int singer = 0;
        while(!queue.isEmpty()){
            singer = queue.poll();

            answer.offer(singer);
            for(int i = 0; i < timeTable.get(singer).size(); i++){
                indegree[timeTable.get(singer).get(i)]--;
                if(indegree[timeTable.get(singer).get(i)] == 0) {
                    queue.offer(timeTable.get(singer).get(i));
                }
            }
        }

        if(answer.size() != N){
            System.out.println(0);
        }else{
            while(!answer.isEmpty()){
                System.out.println(answer.poll());
            }
        }

    }
}