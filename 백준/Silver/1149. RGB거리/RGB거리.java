import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] costArr, result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		costArr = new int[N][3];
		result = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 3; j++) {
				costArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
        // RGB 각각의 비용 저장
		result[0][0] = costArr[0][0];
		result[0][1] = costArr[0][1];
		result[0][2] = costArr[0][2];
		
        // 이전 색깔과 다른 2개의 색깔을 칠하고, 그 중 최소비용을 저장
		for(int i = 1; i < N; i++) {
			result[i][0] = Math.min(result[i-1][1] + costArr[i][0], result[i-1][2] + costArr[i][0]);
			result[i][1] = Math.min(result[i-1][0] + costArr[i][1], result[i-1][2] + costArr[i][1]);
			result[i][2] = Math.min(result[i-1][0] + costArr[i][2], result[i-1][1] + costArr[i][2]);
		}
		
        // 마지막 행의 3개 중 최소비용 출력
		System.out.println(Math.min(result[N-1][0], Math.min(result[N-1][1], result[N-1][2])));
		
	}
	
}
