import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int cp = Integer.parseInt(br.readLine());
		int[][] white = new int[101][101];
		int x = 0;
		int y = 0;
		int result = 0;
		
		for(int test = 0; test < cp; test++) {
			st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			for(int i = x; i < x+10; i++) {
				for(int j = y; j < y+10; j++) {
					white[i][j] = 1;
				}
			}
		}
		
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				if(white[i][j] == 1) {
					result++;
				}
			}
		}
		
		System.out.println(result);
	}
}
