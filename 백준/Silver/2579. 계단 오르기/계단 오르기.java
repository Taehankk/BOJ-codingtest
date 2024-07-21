import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] stair = new int[N+1];
		int[][] check = new int[2][N+1];
		
		for(int i = 1; i < N+1; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		
		// check[0][N] 은 N번째 계단을 밟지 않은 경우 중 최댓값
		// check[1][N] 은 N번째 계단을 밟은 경우 중 최댓값
		check[0][N] = stair[N];
		check[1][N] = stair[N];
		check[0][N-1] = stair[N];
		check[1][N-1] = stair[N] + stair[N-1];
		if(N > 1) {
			check[0][N-2] = check[1][N-1];
			check[1][N-2] = check[0][N-1] + stair[N-2];
		}
		
		for(int i = N-3; i >= 0; i--) {
			// 안 밟으려면, 저번 거가 밟혀있어야 함
			check[0][i] = check[1][i+1];
			// 밟는 경우는, 1. 이전에서 안 밟았거나, 2. 전전에서 안 밟고 이전거 밟고 와야 함
			check[1][i] = Math.max(check[0][i+1] + stair[i], check[0][i+2] + stair[i+1] + stair[i]);
		}
		
		System.out.println(Math.max(check[0][0], check[1][0]));
	}
}
