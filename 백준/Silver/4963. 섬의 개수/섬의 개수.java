import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int w;
	static int h;
	static int[][] map;
	static int[][] visited;
	static Queue<int[]> queue;
	static int idx;
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		while(w != 0 && h != 0) {
			map = new int[h][w];
			visited = new int[h][w];
			queue = new LinkedList<>();
			idx = 0;
			
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(map[i][j] == 1 && visited[i][j] == 0) {
						queue.offer(new int[] {i, j});
						idx++;
						visited[i][j] = idx;
						bfs();
					}
				}
			}
			
			System.out.println(idx);
			
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
		}
		
	}
	private static void bfs() {
		int[] now;
		int nr;
		int nc;
		
		while(!queue.isEmpty()) {
			now = queue.poll();
			
			for(int i = 0; i < 8; i++) {
				nr = now[0] + dr[i];
				nc = now[1] + dc[i];
				if(0 <= nr && nr < h && 0 <= nc && nc < w) {
					if(visited[nr][nc] == 0 && map[nr][nc] == 1) {
						visited[nr][nc] = idx;
						queue.offer(new int[] {nr, nc});
					}
				}
			}
		}
	}
}
