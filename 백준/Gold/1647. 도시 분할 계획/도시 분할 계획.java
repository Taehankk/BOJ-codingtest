import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static PriorityQueue<Road> queue;
	static int[] parent;
	
	static class Road implements Comparable<Road> {
		int cityA;
		int cityB;
		int cost;
		
		Road(int cityA, int cityB, int cost) {
			this.cityA = cityA;
			this.cityB = cityB;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Road o) {
			return this.cost - o.cost;
		}
		
		@Override
		public String toString() {
			return "Road : " + cityA + " <-> " + cityB + " $" + cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		queue = new PriorityQueue<>();
		parent = new int[N + 1];
		
		int cityA, cityB, cost;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			cityA = Integer.parseInt(st.nextToken());
			cityB = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			queue.offer(new Road(cityA, cityB, cost));
		}
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		System.out.println(linkedCity());
	}
	
	public static int linkedCity() {
		Road road;
		int sumCost = 0;
		int lastCost = 0;
		int aParent, bParent;
		while(!queue.isEmpty()) {
			road = queue.poll();
			
			aParent = findParent(road.cityA);
			bParent = findParent(road.cityB);
			
			if(aParent == bParent) continue;
			
			parent[bParent] = aParent;
			sumCost += road.cost;
			lastCost = road.cost;
		}
		
		return sumCost - lastCost;
	}
	
	public static int findParent(int idx) {
		if(idx == parent[idx]) return idx;
		
		parent[idx] = findParent(parent[idx]);
		return parent[idx];
	}
}
