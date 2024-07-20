import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] adjlist = new ArrayList[N+1];
		int[] adjcount = new int[N+1];
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i < N+1; i++) {
			adjlist[i] = new ArrayList<>();
		}
		
		int from, to;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			adjlist[from].add(to);
			adjcount[to]++;
		}
		
		for(int i = 1; i < N+1; i++) {
			if(adjcount[i] == 0) {
				queue.offer(i);
			}
		}
		
		int now;
		while(!queue.isEmpty()) {
			now = queue.poll();
			sb.append(now).append(" ");
			
			for(int i = 0; i < adjlist[now].size(); i++) {
				if(adjcount[adjlist[now].get(i)] != 0) {
					adjcount[adjlist[now].get(i)]--;
					if(adjcount[adjlist[now].get(i)] == 0) {
						queue.offer(adjlist[now].get(i));
					}
				}
			}
		}
		System.out.println(sb);
	}
}
