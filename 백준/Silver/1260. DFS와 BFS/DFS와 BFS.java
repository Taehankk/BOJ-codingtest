import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][] line;
	public static int[] visited;
	public static Queue<Integer> breadth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		line = new int[N+1][N+1];
		visited = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			line[a][b] = 1;
			line[b][a] = 1;
		}
		
		dfs(V);
		System.out.println();
		
		visited = new int[N+1];
		
		breadth = new LinkedList<>();
		breadth.offer(V);
		visited[V] = 1;
		bfs();
	}
	
	public static void dfs(int node) {
		visited[node] = 1;
		System.out.print(node + " ");
		
		for(int i = 1; i < visited.length; i++) {
			if(line[node][i] == 1 && visited[i] == 0) {
				dfs(i);
			}
		}
	}
	
	public static void bfs() {
		if(breadth.isEmpty()) {
			return;
		}
		
		for(int i = 1; i < visited.length; i++) {
			if(line[breadth.peek()][i] == 1 && visited[i] == 0) {
				visited[i] = 1;
				breadth.offer(i);
			}
		}
		
		System.out.print(breadth.poll() + " ");
		
		bfs();
	}
}
