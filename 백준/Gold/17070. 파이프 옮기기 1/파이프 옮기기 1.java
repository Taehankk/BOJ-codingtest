import java.io.*;
import java.util.*;

public class Main {
	static int N, result;
	static int[][] map;
	static Map<Integer, int[][]> move;
	
	static class Pipe {
		int[] loc;
		int nowDir;
		
		Pipe(int[] loc, int nowDir) {
			this.loc = loc;
			this.nowDir = nowDir;
		}
		
		Pipe(int[] loc) {
			this.loc = loc;
		}
		
		public String toString() {
			return "Pipe : " + Arrays.toString(loc);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		initMoveMap();
		if(map[N-1][N-1] == 1) {
			result = 0;
		}else {
			Pipe start = new Pipe(new int[] {0, 1}, 0);
			findToEndCase(start);
		}
		
		System.out.println(result);
	}
	
	public static void initMoveMap() {
		move = new HashMap<>();
		
		move.put(0, new int[][] {{0, 1}, {1, 1}});
		move.put(1, new int[][] {{1, 0}, {1, 1}});
		move.put(2, new int[][] {{0, 1}, {1, 0}, {1, 1}});
	}
	
	public static void findToEndCase(Pipe pipe) {
		int[][] nextMove = move.get(pipe.nowDir);

		int nr, nc;
		
		for(int[] d : nextMove) {
			nr = pipe.loc[0] + d[0];
			nc = pipe.loc[1] + d[1];
			
			
			if(nr < N && nc < N && map[nr][nc] == 0) {				
				Pipe newPipe = new Pipe(new int[] {nr, nc});
				
				if(d[0] == 1 && d[1] == 1) {
					if(map[nr - 1][nc] == 1 || map[nr][nc - 1] == 1) continue;
					newPipe.nowDir = 2;
				} else if(d[0] == 1) {
					newPipe.nowDir = 1;
				} else {
					newPipe.nowDir = 0;
				}
				
				if(nr == N - 1 && nc == N - 1) {
					result++;
					continue;
				}

				findToEndCase(newPipe);
			}
		}
	}
}
