import java.io.*;
import java.util.*;

public class Main {
	static int N, M, k;
	static Loc[][] map;
	static Shark[] sharks;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Shark {
		int idx, r, c, dir;
		int[][] priority;
		
		Shark(int idx, int r, int c) {
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.dir = 0;
			this.priority = new int[4][4];
		}
		
		@Override
		public String toString() {
			return "Shark : <nowDir " + dir + ">";
		}
	}
	
	static class Loc {
		int sharkIdx;
		int traceTime;
		Shark shark;
		
		Loc() {}
		
		@Override
		public String toString() {
			return sharkIdx + " " + shark + " / remain " + traceTime;
		}
	}
	
	public static void main(String[] args) throws Exception {
		getInput();
		
		int result = -1;
		for(int time = 1; time <= 1000; time++) {
			moveShark();
			updateSharkLoc();
			updateMap();
			
			if(checkRemainShark()) {
				result = time;
				break;
			}
		}
		
		System.out.println(result);
	}
	
	public static void getInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new Loc[N][N];
		sharks = new Shark[M + 1];
		
		int sharkIdx = 0;
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				sharkIdx = Integer.parseInt(st.nextToken());
				map[r][c] = new Loc();
				
				if(sharkIdx > 0) {
					sharks[sharkIdx] = new Shark(sharkIdx, r, c);
					
					map[r][c].sharkIdx = sharkIdx;
					map[r][c].traceTime = k;
					map[r][c].shark = sharks[sharkIdx];
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) {
			sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
		}
		
		for(int idx = 1; idx <= M; idx++) {
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 4; j++) {
					sharks[idx].priority[i][j] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
	}
	
	public static void moveShark() {
		Shark shark;
		int prevR, prevC, nr, nc;
		int[] pr;
		next: for(int idx = 1; idx <= M; idx++) {
			shark = sharks[idx];
			
			if(shark == null) continue;
			
			prevR = shark.r;
			prevC = shark.c;
			pr = shark.priority[shark.dir];
			
			for(int i = 0; i < 4; i++) {
				nr = prevR + dr[pr[i]];
				nc = prevC + dc[pr[i]];
				
				if(0 <= nr && nr < N && 0 <= nc && nc < N) {
					if(map[nr][nc].sharkIdx == 0) {
						shark.r = nr;
						shark.c = nc;
						shark.dir = pr[i];
						continue next;
					}
					
					if(map[nr][nc].sharkIdx == idx && shark.r == prevR && shark.c == prevC) {
						shark.r = nr;
						shark.c = nc;
						shark.dir = pr[i];
					}
				}
			}
		}
	}
	
	public static void updateSharkLoc() {
		Shark shark;		
		for(int idx = 1; idx <= M; idx++) {
			shark = sharks[idx];
			if(shark == null) continue;
			
			if(map[shark.r][shark.c].shark != null && map[shark.r][shark.c].sharkIdx < shark.idx) {
				sharks[idx] = null;
				continue;
			}
			
			map[shark.r][shark.c].shark = shark;
			map[shark.r][shark.c].sharkIdx = shark.idx;
			map[shark.r][shark.c].traceTime = k;
		}
	}
	
	public static void updateMap() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c].shark != null) {
					if(map[r][c].shark.r != r || map[r][c].shark.c != c) {
						map[r][c].shark = null;
					} else {
						continue;
					}
				}
				
				if(map[r][c].traceTime > 0) {
					map[r][c].traceTime--;
					
					if(map[r][c].traceTime == 0) {
						map[r][c].sharkIdx = 0;
					}
				}
			}
		}
	}
	
	public static boolean checkRemainShark() {
		for(int idx = 2; idx <= M; idx++) {
			if(sharks[idx] != null) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void printMap() {
		for(int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
		System.out.println();
	}
}
