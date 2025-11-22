import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static Child[] children;
	static boolean[] visited;
	static PriorityQueue<StealCase> queue;
	
	static class Child{
		int idx;
		int candy;
		List<Child> friends;
		
		Child(int idx, int candy) {
			this.idx = idx;
			this.candy = candy;
			this.friends = new ArrayList<>();
		}
		
		@Override
		public String toString() {
			return "Child " + idx + " : " + candy + " Candy";
		}
	}
	
	static class StealCase implements Comparable<StealCase> {
		int victim;
		int candy;
		
		StealCase(int victim, int candy) {
			this.victim = victim;
			this.candy = candy;
		}
		
		@Override
		public int compareTo(StealCase o) {
			if(this.victim == o.victim) {
				return o.candy - this.candy;
			}
			
			return this.victim - o.victim;
		}
		
		@Override
		public String toString() {
			return "Crying " + victim + "people, " + candy + " Candy Stolen";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		children = new Child[N];
		visited = new boolean[N];
		queue = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		int candy;
		for(int i = 0; i < N; i++) {
			candy = Integer.parseInt(st.nextToken());
			children[i] = new Child(i, candy);
		}
		
		int f1, f2;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			f1 = Integer.parseInt(st.nextToken()) - 1;
			f2 = Integer.parseInt(st.nextToken()) - 1;
			
			children[f1].friends.add(children[f2]);
			children[f2].friends.add(children[f1]);
		}
		
		startSteal();
		System.out.println(findMaxCandy());
	}
	
	public static void startSteal() {
		StealCase result;
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				result = stealCandy(children[i], 1, children[i].candy);
				
				if(result.victim < K) {
					queue.offer(result);					
				}
			}
		}
	}
	
	public static StealCase stealCandy(Child child, int victim, int stolenCandy) {
		visited[child.idx] = true;
		
		List<Child> friends = child.friends;
		int length = friends.size();
		
		Child friend;
		StealCase midResult = new StealCase(victim, stolenCandy);
		for(int i = 0; i < length; i++) {
			friend = friends.get(i);
			if(!visited[friend.idx]) {
				midResult = stealCandy(friend, midResult.victim + 1, midResult.candy + friend.candy);
			}
		}		
		
		return midResult;
	}
	
	public static int findMaxCandy() {
		int[] maxCandy = new int[K];
		
		StealCase stealCase;
		while(!queue.isEmpty()) {
			stealCase = queue.poll();
			
			for(int i = K - 1; i >= stealCase.victim ; i--) {
				if(maxCandy[i] < maxCandy[i - stealCase.victim] + stealCase.candy) {
					maxCandy[i] = maxCandy[i - stealCase.victim] + stealCase.candy;
				}
			}
		}
		
		return maxCandy[K - 1];
	}
}