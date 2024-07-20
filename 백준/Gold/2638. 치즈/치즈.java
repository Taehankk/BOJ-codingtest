import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, sum;
	static int[][] cheese;
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// N : 행의 수 / M : 열의 수 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// cheese : 치즈 정보, visited : 방문 배열
		cheese = new int[N][M];
		visited = new boolean[N][M];
		queue = new LinkedList<>();
		// 녹이고 난 후의 전체 cheese 개수 체크하는 변수
		sum = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				// 초기 치즈 개수 계산 (처음에 치즈가 아예 없을수도?)
				sum += cheese[i][j];
			}
		}
		
		int year = 0;
		
		// 다 녹을 때까지 반복
		while(sum != 0) {
			sum = 0;
			// visited 배열 연도 바뀔때마다 초기화
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
						visited[i][j] = false;
				}
			}
			
			// 내부 공간 체크를 위해 치즈 외부에 있는 0 탐색 후 innercheck bfs 진행
			out : for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(cheese[i][j] == 0) {
						queue.offer(new int[] {i, j});
						visited[i][j] = true;
						innerCheck();
						break out;
					}
				}
			}
			
			// 치즈 녹이는 메서드
			meltCheese();

			year++;
		}
		
		System.out.println(year);
	}
	
	public static void innerCheck() {
		int nr, nc;
		int[] now;
		
		while(!queue.isEmpty()) {
			now = queue.poll();
			// 사방탐색, 0이면 queue에 offer, visited 처리
			for(int i = 0; i < 4; i++) {
				nr = now[0] + dr[i];
				nc = now[1] + dc[i];
				// 외부 공기 지역만 true 처리
				if(0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]) {
					if(cheese[nr][nc] == 0) {
						queue.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
		}
		
	}
	
	public static void meltCheese() {
		int nr, nc, count;
		
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < M; j++) {
				// 치즈를 만나면 사방 탐색해서 외부 공간 수 탐색(외부는 true 인 0)
				if(cheese[i][j] == 1) {
					count = 0;
					for(int k = 0; k < 4; k++) {
						nr = i + dr[k];
						nc = j + dc[k];
						if(0 <= nr && nr < N && 0 <= nc && nc < M && cheese[nr][nc] == 0 && visited[nr][nc]) {
							count++;
						}
					}
					// 외부 접촉면이 2 이상이면 녹임 && 방문처리는 필요 없음(기존에 치즈였던 공간은 녹이고 false로 놔둬야 이후에 count 안 함)
					if(count >= 2) {
						cheese[i][j] = 0;
					}
					
				}
				
				// 남아있는 치즈 수 체크
				sum += cheese[i][j];
			}
		}
	}
}
