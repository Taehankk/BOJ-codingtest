import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		jump();
		
		System.out.println("Hing");
	}
	
	public static void jump() {
		Queue<int[]> jjelly = new LinkedList<>();
		
		jjelly.offer(new int[] {0 ,0});	
		visited[0][0] = true;	
		
		while(!jjelly.isEmpty()) {
			int[] now = jjelly.poll();
            // 점프 가능한 배수 저장(map에 기록됨)
			int go = map[now[0]][now[1]];
			
            // 도달지점에 왔으면 HaruHaru 출력 후 종료
			if(go == -1) {
				System.out.println("HaruHaru");
				System.exit(0);
			}
			
            // 이동 가능한만큼 점프해서 갈 수 있는지 탐색
			for(int i = 0; i < 2; i++) {
				int nr = now[0] + dr[i] * go;
				int nc = now[1] + dc[i] * go;
				
				if(nr < N && nc < N && visited[nr][nc] == false) {
					jjelly.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}
}
