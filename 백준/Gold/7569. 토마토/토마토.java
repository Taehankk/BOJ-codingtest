import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	static int[][][] tomato;
	static Queue<int[]> queue;
	static int[] dh = {0, 0, 0, 0, 1, -1};
	static int[] dr = {-1, 1, 0, 0, 0, 0};
	static int[] dc = {0, 0, -1, 1, 0, 0};
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 토마토 상자 열(N), 행(M), 높이(H) 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// 토마토 정보 입력받을 배열
		tomato = new int[H][M][N];
		
		// BFS 에 사용할 QUEUE
		queue = new LinkedList<>();		
		
		// 토마토 정보를 입력받고, 1이면 QUEUE에 저장
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < N; k++) {
					tomato[i][j][k] = Integer.parseInt(st.nextToken());
					
					if(tomato[i][j][k] == 1) {
						queue.add(new int[] {i, j, k});
					}
				}
			}
		}
		
		// BFS 실행
		bfs();
		
		// BFS가 끝나고 익지 않은 토마토가 있는지 확인
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < N; k++) {					
					if(tomato[i][j][k] == 0) {
						result = -1;
						break;
					}
				}
			}
		}
		
		// 최종 결과 출력
		System.out.println(result);
	}
	
	public static void bfs() {
		// QUEUE에서 뽑아내서 위치 저장할 배열
		int[] check;
		
		// 임시 변수들
		int h = 0, r = 0, c = 0, nh, nr, nc;
		while(!queue.isEmpty()) {
			check = queue.poll();
			// 현재 확인하는 높이, 행, 열 값 저장
			h = check[0];
			r = check[1];
			c = check[2];
			
			for(int i = 0; i < 6; i++) {
				// 상, 하, 좌, 우, 위층, 아래층 돌면서 토마토 숙성
				// 현재 탐색중인 위치의 값에 +1 해서 일자 저장
				// 실제 일자보다 +1 되어서 저장됨
				nh = h + dh[i];
				nr = r + dr[i];
				nc = c + dc[i];
				if(0 <= nh && nh < H && 0 <= nr && nr < M && 0 <= nc && nc < N && tomato[nh][nr][nc] == 0) {
					tomato[nh][nr][nc] = tomato[h][r][c] + 1;
					
					queue.add(new int[] {nh, nr, nc});
				}
			}
		}
		
		// 최종 결과는 마지막에 탐색한 위치의 값에서 1을 뺸 값
		result = tomato[h][r][c] - 1;
	}
}
