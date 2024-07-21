import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static int N;
	static int[][] box;
	static int[][] visited;
	static int day;
	static Queue<int[]> queue;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		visited = new int[N][M];
		queue = new LinkedList<>();
		day = 1;		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(box[i][j] == 1) {
					queue.offer(new int[] {i, j});
					visited[i][j] = day;
				} else if(box[i][j] == -1) {
					visited[i][j] = -1;
				}
			}
		}

		bfs();
		
        // bfs 종료 후, 익지 않은 곳이 있다면 day를 1로 표시 후 break
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j] == 0) {
					day = 1;
					break;
				}
			}
		}
		
        // 처음에 익은 토마토는 0일차로 봐야하고
        // bfs 를 마지막에 빠져나올 때, 1이 더 증가하므로
        // 최종 출력 시 2를 빼줘야 함
		System.out.println(day-2);
	}

	public static void bfs() {		
		int[] now = new int[2];
		int nr;
		int nc;
        
        // bfs 로 사방탐색하면서 day +1 을 visited배열에 새김
		while(!queue.isEmpty()) {
			now = queue.poll();
			day = visited[now[0]][now[1]] + 1;
			
			for(int j = 0; j < 4; j++) {
				nr = now[0] + dr[j];
				nc = now[1] + dc[j];
				
				if(0 <= nr && nr < N && 0 <= nc && nc < M) {
					if(box[nr][nc] == 0 && visited[nr][nc] == 0) {
						visited[nr][nc] = day;
						queue.offer(new int[] {nr, nc});
					}
				}
			}	
			
		}
		
	}
}
