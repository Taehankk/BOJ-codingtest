import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] region;
	static int[][] region_copy;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int index;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		region = new int[N][N];
		int min_h = Integer.MAX_VALUE;
		int max_h = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				region[i][j] = Integer.parseInt(st.nextToken());
				
				if(region[i][j] < min_h) {
					min_h = region[i][j];
				}
				
				if(region[i][j] > max_h) {
					max_h = region[i][j];
				}
			}
		}
		
		int rg_cnt_max = 1;
		
		for(int rain = min_h; rain < max_h; rain++) {
			index = 0;
			region_copy = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {					
					if(region[i][j] > rain) {
						if(region_copy[i][j] != 0) {
							continue;
						}
						index--;
						safety(i, j, rain);
					}					
				}
			}
			
			if(-index > rg_cnt_max) {
				rg_cnt_max = -index;
			}
		}
        
		System.out.println(rg_cnt_max);
	}
	
	public static void safety(int row, int col, int rain) {
		// base
		
		
		// recursive
		for(int i = 0; i < 4; i++) {
			if(row+dr[i] >= 0 && row+dr[i] < N && col+dc[i] >= 0 && col+dc[i] < N) {
				if(region[row+dr[i]][col+dc[i]] > rain && region_copy[row+dr[i]][col+dc[i]] == 0) {
					region_copy[row+dr[i]][col+dc[i]] = index;
					safety(row+dr[i], col+dc[i], rain);
				}
			}
		}
	}
}
