import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 초기 간선을 저장하는 클래스
    // queue를 돌면서는 시작점에서 to 까지의 최단거리를 cost 에 저장
	static class Edge implements Comparable<Edge> {
		int to;
		int cost;
		
		// 도착지, 비용
		Edge(int to, int cost){
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	static int V, E, start;
	static int[] costArr;
	static List<Queue<Edge>> edges;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		
        // 시작 지점에서 각각의 지점까지 최단거리 저장 배열
		costArr = new int[V+1];
		Arrays.fill(costArr, Integer.MAX_VALUE);
		
		visited = new boolean[V+1];
		
        // 간선의 정보를 저장하는 arraylist
        // index == i 인 리스트는 i에 연결된 간선들
		edges = new ArrayList<>();
		
		for(int i = 0; i <= V; i++) {
			edges.add(new LinkedList<>());
		}
		
		int u, v, w;
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			edges.get(u).offer(new Edge(v, w));
		}
		
		costArr[start] = 0;
		
		minRoute(start);
		
		for(int i = 1; i <= V; i++) {
			if(costArr[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			}else {
				sb.append(costArr[i]).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static void minRoute(int node) {
		Queue<Edge> queue = new PriorityQueue<>();
		
		queue.offer(new Edge(node, 0));
		
		Edge now, check;
		int size = 0;
		while(!queue.isEmpty()) {
			now = queue.poll();
			
			size = edges.get(now.to).size();
			
            // 현재 탐색중인 노드에 연결된 간선을 모두 탐색
			for(int i = 0; i < size; i++) {
				check = edges.get(now.to).poll();
				
                // 현재 노드까지의 최소비용에 연결된 간선까지의 cost 를 더한 값과
                // 최소비용들이 저장된 배열의 값을 비교해서 작으면 갱신
				if(now.cost + check.cost < costArr[check.to]) {
					costArr[check.to] = now.cost + check.cost;
					
					queue.offer(new Edge(check.to, costArr[check.to]));
					
					edges.get(now.to).offer(check);
				}
			}
			
		}
		
	}
}
